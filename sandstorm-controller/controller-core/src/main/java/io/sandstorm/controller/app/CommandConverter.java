package io.sandstorm.controller.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import org.json.JSONObject;

/**
 * Create by zhangzheng on 6/17/18
 * Email:zhangzheng@youzan.com
 */
public class CommandConverter {

  private static final ObjectMapper jsonMapper = new ObjectMapper();

  static {
    jsonMapper.registerModule(new JsonOrgModule());
  }

  public static<T> T convert(JSONObject command, Class<T> classType){
    return jsonMapper.convertValue(command, classType);
  }

  public static<T> T convert(JSONObject command, TypeReference<T> typeReference){
    return jsonMapper.convertValue(command, typeReference);
  }



}
