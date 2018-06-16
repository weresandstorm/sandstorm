package io.sandstorm.rest;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

@RunWith(JUnit4.class)
public class TestScriptRestTest extends OkHttpRestBase {

    @Test
    public void testCreate() throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost").port(8080)
                .addPathSegment("test-scripts")
                .build();

        String json = "{\"jarAlias\": \"gatling-examples.jar\", \"desc\": \"gatling scripts examples\", \"creator\": \"wuque\"}";
        File file = new File(TestScriptRestTest.class.getClassLoader().getResource("scripts/gatling-examples.jar").getFile());
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addPart(MultipartBody.Part.create(
                        Headers.of(
                                "Content-Disposition",
                                "form-data; name=\"testScript\""
                        ),
                        RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), json)
                ))
                .addFormDataPart(
                        "scriptJar",
                        "gatling-examples.jar",
                        RequestBody.create(MEDIA_TYPE_OCTET, file)
                ).build();

        Request.Builder reqBuilder = new Request
                .Builder()
                .url(httpUrl)
                .post(requestBody);

        sendReqAndDisplayResp(reqBuilder.build());
    }

    /**
     * 测试更新 test-script 基本信息（不包含脚本内容）
     */
    @Test
    public void testUpdate1() throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost").port(8080)
                .addPathSegment("test-scripts")
                .addPathSegment("5a80328e85fdb95be2c83e33")
                .build();

        String json = "{\"id\": \"5a80328e85fdb95be2c83e33\", \"jarAlias\": \"gatling-examples.jar\", \"desc\": \"A play ground for new hands of gatling\", \"creator\": \"wuque\"}";
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addPart(MultipartBody.Part.create(
                        Headers.of(
                                "Content-Disposition",
                                "form-data; name=\"testScript\""
                        ),
                        RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), json)
                )).build();

        Request.Builder reqBuilder = new Request
                .Builder()
                .url(httpUrl)
                .patch(requestBody);

        sendReqAndDisplayResp(reqBuilder.build());
    }

    /**
     * 测试一起更新 test-script 基本信息和脚本内容
     */
    @Test
    public void testUpdate2() throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost").port(8080)
                .addPathSegment("test-scripts")
                .addPathSegment("5a80328e85fdb95be2c83e33")
                .build();

        String json = "{\"id\": \"5a80328e85fdb95be2c83e33\", \"jarAlias\": \"gatling-examples.jar\", \"desc\": \"Add computer-search examples. 2222\", \"creator\": \"kingkong\"}";
        File scriptFile = fileFromResource("scripts/gatling-examples.jar");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addPart(MultipartBody.Part.create(
                        Headers.of(
                                "Content-Disposition",
                                "form-data; name=\"testScript\""
                        ),
                        RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), json)
                ))
                .addFormDataPart(
                        "scriptJar",
                        "gatling-examples.jar",
                        RequestBody.create(MEDIA_TYPE_OCTET, scriptFile)
                ).build();

        Request.Builder reqBuilder = new Request
                .Builder()
                .url(httpUrl)
                .patch(requestBody);

        sendReqAndDisplayResp(reqBuilder.build());
    }

}
