package io.sandstorm.agent.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JobResourceServiceTest extends AbstractTest {

    private JobResourceService jobResourceService = JobResourceService.getInstance();

    @Test(expected = Test.None.class)
    public void testDownloadForTestJob() {
        jobResourceService.startDownloadResourcesFor(localTestJob());
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
