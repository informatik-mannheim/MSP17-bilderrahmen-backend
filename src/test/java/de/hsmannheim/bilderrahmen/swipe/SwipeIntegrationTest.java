package de.hsmannheim.bilderrahmen.swipe;

import de.hsmannheim.bilderrahmen.AbstractIntegrationTest;
import de.hsmannheim.bilderrahmen.client.SwipeStompFrameHandlerClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;


public class SwipeIntegrationTest extends AbstractIntegrationTest {

    Swipe objectToTest;

    @Before
    public void setupObjects() throws Exception {
        objectToTest = new Swipe().buildSwipe("50");
    }


    @Override
    protected String getSessionString() {
        return "/queue/swipe/testtoken";
    }

    @Override
    protected String getSendString() {
        return "/bilderrahmen/swipe/testtoken";
    }

    @Override
    protected Object getObjectToTest() {
        return objectToTest;
    }


    @Test
    public void shouldReceiveAMessageFromTheServer() throws Exception {
        generalSetup(new SwipeStompFrameHandlerClient());
        Assert.assertEquals(objectToTest.getId(), ((Swipe) blockingQueueClient1.poll(5, SECONDS)).getId());
        Assert.assertEquals(objectToTest.getId(), ((Swipe) blockingQueueClient2.poll(5, SECONDS)).getId());
        Assert.assertNull(blockingQueueClient3.poll(1, MILLISECONDS));
    }

}
