package io.sandstorm.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import io.sandstorm.common.CaseCode;
import io.sandstorm.common.IllegalInputException;
import io.sandstorm.common.InputAssert;
import io.sandstorm.common.query.domain.Existence;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import io.sandstorm.rest.validation.TestScriptValidators;
import io.sandstorm.controller.app.CheckJobExecsEnded;
import io.sandstorm.controller.app.FindService;
import io.sandstorm.controller.app.TestScriptApp;
import io.sandstorm.controller.domain.resource.TestScript;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Optional;

@RestController
@RequestMapping("/test-scripts")
public class TestScriptRest {

    @Resource
    private TestScriptApp testScriptApp;
    @Resource
    private FindService findService;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    static {
        jsonMapper.registerModule(new JsonOrgModule());
    }

    @PostMapping("/test")
    public void create(@RequestBody JSONObject testScript) {
        try (InputStream inputStream = this.getClass().getResourceAsStream("/json-schema/TestScript.json")) {
            String jsonSchema = IOUtils.toString(inputStream);
            System.out.println(jsonSchema);
            JSONObject rawSchema = new JSONObject(new JSONTokener(jsonSchema));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(testScript);
            TestScript testScript1 = jsonMapper.convertValue(testScript, TestScript.class);
            System.out.println(">>>>>>" + testScript1.jarAlias());
        } catch (ValidationException e) {
            e.getCausingExceptions().stream()
                    .map(ValidationException::getMessage)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{jar_alias}/actions/check-existence")
    public Existence checkExistence(@PathVariable("jar_alias") String jarAlias) {
        InputAssert.notBlank(jarAlias, "path variable jarAlias");
        return testScriptApp.checkExistence(jarAlias);
    }

    @PostMapping
    public void create(@RequestPart("testScript") TestScript testScript,
                       @RequestPart("scriptJar") Part scriptJar) {
        InputAssert.notNull(testScript, "testScript");
        InputAssert.notNull(scriptJar, "scriptJar");
        testScript.accept(TestScriptValidators.creation_validator);

        byte[] jarContent = read(scriptJar);
        if (jarContent.length == 0) {
            throw new IllegalInputException("The chosen file has no content");
        }

        testScriptApp.create(testScript, jarContent);
    }

    @GetMapping
    public Page<TestScript> list(SimplePagingQParams params) {
        params.validate();
        return findService.findTestScripts(params);
    }

    @GetMapping("/{id}")
    public TestScript detail(@PathVariable("id") ObjectId scriptId) {
        return testScriptApp.detail(scriptId);
    }

    /**
     * 查看引用给定数据集的所有 job-execs 是否都执行结束
     * @param id
     */
    @GetMapping("/{id}/actions/check_referring_job_execs_ended")
    public CheckJobExecsEnded checkReferringJobExecsEnded(@PathVariable("id") ObjectId id) {
        return testScriptApp.checkReferringJobExecsEnded(id);
    }

    @PatchMapping("/{id}")
    public void update(@RequestPart("testScript") TestScript change,
                       @RequestPart(name="scriptJar", required = false) Part scriptJar) {
        InputAssert.notNull(change, "testScript in request body");
        change.accept(TestScriptValidators.update_validator);

        Optional<byte[]> jarContent;
        if (scriptJar == null) {
            jarContent = Optional.empty();
        } else {
            byte[] jarBytes = read(scriptJar);
            if (jarBytes.length == 0) {
                throw new IllegalInputException("The chosen file has no content");
            }
            jarContent = Optional.of(jarBytes);
        }

        testScriptApp.update(change, jarContent);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") ObjectId scriptId) {
        testScriptApp.delete(scriptId);
    }

    private byte[] read(Part scriptFile) {
        InputStream input = null;
        ByteBuffer byteBuf = ByteBuffer.allocate(Long.valueOf(scriptFile.getSize()).intValue());

        try {
            input = scriptFile.getInputStream();
            byte[] temp = new byte[1024];
            int count;
            while ((count = input.read(temp, 0, temp.length)) != -1) {
                byteBuf.put(temp, 0, count);
            }
        } catch (IOException e) {
            String message = String.format(
                    "Given script file %s is broken, try uploading it again",
                    scriptFile.getSubmittedFileName());
            throw new IllegalInputException(CaseCode.upload_io_error, message, e);
        } finally {
            IOUtils.closeQuietly(input);
        }

        return byteBuf.array();
    }

}
