package io.sandstorm.rest;

import static org.junit.Assert.*;

import com.google.gson.JsonObject;
import io.sandstorm.controller.app.FindService;
import io.sandstorm.controller.app.TestJobApp;
import io.sandstorm.controller.app.TestJobCmd;
import io.sandstorm.controller.app.TestJobCmd.Create;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Create by zhangzheng on 6/17/18
 * Email:zhangzheng@youzan.com
 */
@RunWith(MockitoJUnitRunner.class)
public class TestJobRestTest {

  @Mock
  TestJobApp testJobApp;
  @Mock
  FindService findService;
  @InjectMocks
  TestJobRest testJobRest;

  @Test(expected = ValidationException.class)
  public void testSchemaRequired() {
    TestJobCmd.Create create = new Create();
    create.put("name","someName");
    testJobRest.create(create);
  }
  @Test
  public void testSchemaValdiationSuccess(){
    TestJobCmd.Create create = new Create();
    create.put("name","someName");
    create.put("scriptId","someid");
    create.put("simulationToRun","someSimulation");
    JSONObject loadProfile = new JSONObject();
    loadProfile.put("totalInjectors",10);
    loadProfile.put("scnRepeatTimes",5);
    JSONObject execPlan = new JSONObject();
    execPlan.put("execMode","on_demand");
    loadProfile.put("execPlan",execPlan);
    create.put("loadProfile", loadProfile);
    testJobRest.create(create);
  }
}
