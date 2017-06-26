package de.hsmannheim.bilderrahmen.stresstest.thread;

import de.hsmannheim.bilderrahmen.client.SwipeStompFrameHandlerClient;
import de.hsmannheim.bilderrahmen.stresstest.config.TestConfig;
import de.hsmannheim.bilderrahmen.swipe.Swipe;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by mjando on 13.06.17.
 */
public class SendAndPollThread implements Runnable {

    ArrayList<BlockingQueue<Swipe>> clientList = new ArrayList();
    ArrayList<StompSession> sessionList = new ArrayList();
    Swipe swipe;
    private String token;


    public SendAndPollThread(String tokenString) throws Exception {
        this.token = tokenString;
        initiateClients();
        initiateSessions();
        swipe = new Swipe().buildSwipe(tokenString);
    }

    private void initiateSessions() throws Exception {
        for (int i = 0; i < TestConfig.DEVICES_PER_FAMILY_COUNT; i++)
            sessionList.add(TestConfig.sessionBuilder.buildStompSession(TestConfig.SWIPE_PREFIX_URL_STRING + token, clientList.get(i), new SwipeStompFrameHandlerClient()));
    }

    private void initiateClients() {
        for (int i = 0; i < TestConfig.DEVICES_PER_FAMILY_COUNT; i++)
            clientList.add(new LinkedBlockingDeque());
    }


    @Override
    public void run() throws AssertionFailedError {
        StompSession master = sessionList.get(0);
        master.send(TestConfig.SEND_PREFIX_URL_STRING + token, swipe);

        for (int i = 1; i < clientList.size(); i++) {
            try {
                Assert.assertEquals(swipe.getId(), clientList.get(i).poll(TestConfig.WEBSOCKET_TIMEOUT_VALUE, TestConfig.WEBSEOCKET_TIMEOUT_TIME).getId());
            } catch (InterruptedException e) {
                throw new AssertionFailedError();
            }
        }
    }
}




