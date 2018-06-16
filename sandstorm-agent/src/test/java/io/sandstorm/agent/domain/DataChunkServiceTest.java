package io.sandstorm.agent.domain;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DataChunkServiceTest extends AbstractTest {

    private DataChunkService dataChunkService = DataChunkService.getInstance();

    @Test
    public void testDataChunkDir() {
        String dirpath = dataChunkService.dataChunkDir(localTestJob());
        Assert.assertEquals(feederFileDir, dirpath);
    }

    @Test
    public void testdataChunkFilePath() {
        String filePath = dataChunkService.dataChunkFilePath(localTestJob(), "instant-kill-1.json.gz");
        Assert.assertEquals(feederFileDir + File.separator + "instant-kill-1.json.gz", filePath);
    }

    @Test
    public void testDataChunkNotExist() {
        boolean isExist = dataChunkService.dataChunkNotExist(localTestJob(), "instant-kill-1.json.gz", "7a126566b58eba1ba2b079aff1377cf8");
        Assert.assertFalse(isExist);
    }

    @Test(expected = Test.None.class)
    public void testDataChunkMerged() {
        dataChunkService.mergeDataChunks(localTestJob());
    }
}