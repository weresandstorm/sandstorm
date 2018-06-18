package io.sandstorm.controller.app;

import io.sandstorm.controller.domain.job.LoadProfile;
import io.sandstorm.controller.domain.job.TestJob;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.TestScript;
import java.util.function.Function;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Create by zhangzheng on 6/17/18
 * Email:zhangzheng@youzan.com
 */
public class TestJobCmd {

  public static class Create extends JSONObject {

    public String name() {
      return super.getString("name");
    }
    public ObjectId scriptId(){
      return new ObjectId(getString("scriptId"));
    }
    public String simulationToRun(){
      return getString("simulationToRun");
    }
    public ObjectId dataSetId(){
      return new ObjectId(getString("dataSetId"));
    }

    public TestJob toTestJob(Function<ObjectId,TestScript> scriptFunction,
        Function<ObjectId, DataSet> dataSetFunction){
      TestJob testJob = CommandConverter.convert(this, TestJob.class);
      testJob.setScript(scriptFunction.apply(scriptId()));
      if(dataSetId()!=null){
        testJob.setDataSet(dataSetFunction.apply(dataSetId()));
      }
      return testJob;
    }

  }

  public static class Update extends JSONObject{

    public String name(){
      return getString("name");
    }

    public String remark(){
      return getString("remark");
    }

  }

}
