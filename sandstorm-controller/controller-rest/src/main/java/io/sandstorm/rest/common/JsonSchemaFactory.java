package io.sandstorm.rest.common;

import io.sandstorm.common.ApplicationException;
import io.sandstorm.common.CaseCode;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;

/**
 * Create by zhangzheng on 6/17/18
 * Email:zhangzheng@youzan.com
 */
public class JsonSchemaFactory {

  public static Map<String, Schema> schemaCache = new HashMap<>();

  private static final String SCHEMA_BASE_PATH = "/api-defs/";

  public static Schema getSchema(String path){
    try {
      if(schemaCache.containsKey(path)){
        return schemaCache.get(path);
      }
      InputStream inputStream = JsonSchemaFactory.class.getResourceAsStream(SCHEMA_BASE_PATH + path);
      String jsonSchema = IOUtils.toString(inputStream);
      JSONObject rawSchema = new JSONObject(new JSONTokener(jsonSchema));
      Schema schema = SchemaLoader.load(rawSchema);
      schemaCache.put(path, schema);
      return schema;
    } catch (Exception e) {
      throw new ApplicationException(CaseCode.load_schema_error, "failed to load schma");
    }

  }

}
