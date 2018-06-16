package io.sandstorm.agent.portadapter.service;

import io.sandstorm.agent.domain.AbstractTest;
import io.sandstorm.agent.domain.JobEngineService;
import io.sandstorm.agent.domain.model.LocalTestJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JobEngineServiceTest extends AbstractTest {

    private static JobEngineService jobEngineService = new JobEngineService();

    @Test
    public void testGatlingExecutor() {

        LocalTestJob localTestJob = localTestJob();
        try {
            jobEngineService.startEngine(localTestJob);
            Thread.sleep(4000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            jobEngineService.stopEngine(localTestJob);
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
