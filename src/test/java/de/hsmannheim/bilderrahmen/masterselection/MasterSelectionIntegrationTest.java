package de.hsmannheim.bilderrahmen.masterselection;

import de.hsmannheim.bilderrahmen.AbstractIntegrationTest;
import de.hsmannheim.bilderrahmen.client.MasterSelectionStompFrameHandlerClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by mjando on 19.06.17.
 */
public class MasterSelectionIntegrationTest extends AbstractIntegrationTest<MasterSelection> {

    MasterSelection objectToTest;

    @Before
    public void setup() throws Exception {

        objectToTest = new MasterSelection().build("50");
        generalSetup(new MasterSelectionStompFrameHandlerClient());
    }

    @Test
    public void testFileChanges() throws Exception {
        Assert.assertEquals(objectToTest.getDeviceId(),  blockingQueueClient1.poll(5, SECONDS).getDeviceId());
        Assert.assertEquals(objectToTest.getDeviceId(),  blockingQueueClient2.poll(5, SECONDS).getDeviceId());
    }



    @Override
    protected String getSessionString() {
        return "/queue/master-selection/testtoken";
    }

    @Override
    protected String getSendString() {
        return "/bilderrahmen/master-selection/testtoken";
    }

    @Override
    protected Object getObjectToTest() {
        return objectToTest;
    }
}
