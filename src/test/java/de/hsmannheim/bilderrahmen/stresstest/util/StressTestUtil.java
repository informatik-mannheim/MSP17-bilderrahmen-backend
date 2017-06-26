package de.hsmannheim.bilderrahmen.stresstest.util;

import de.hsmannheim.bilderrahmen.client.SwipeStompFrameHandlerClient;
import de.hsmannheim.bilderrahmen.stresstest.config.TestConfig;
import de.hsmannheim.bilderrahmen.swipe.Swipe;
import de.hsmannheim.bilderrahmen.token.Token;
import de.hsmannheim.bilderrahmen.token.TokenGenerator;
import org.springframework.messaging.simp.stomp.StompSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class StressTestUtil {

    public static Token[] buildTokenList() throws Exception {
        generateTokens();
        return TestConfig.tokenList;
    }

    protected static Map buildClientList(Token token) throws Exception {
        ArrayList clientList = new ArrayList();
        Map clientToSwipeObjectMap = new HashMap();
        Swipe swipe = new Swipe().buildSwipe(token.getId());
        for (int i = 0; i < TestConfig.DEVICES_PER_FAMILY_COUNT; i++)
            clientList.add(addMasterAndSlaves(token, i));
        clientToSwipeObjectMap.put(swipe, clientList);
        return clientToSwipeObjectMap;
    }

    protected static Object addMasterAndSlaves(Token token, int deviceCount) throws Exception {
        BlockingQueue<Swipe> blockingClient = new LinkedBlockingDeque<>();
        StompSession stompSession = TestConfig.sessionBuilder.buildStompSession(
                TestConfig.SWIPE_PREFIX_URL_STRING + token.getId(), blockingClient, new SwipeStompFrameHandlerClient());
        return deviceCount == 0 ? stompSession : blockingClient;
    }

    static void generateTokens() {
        TestConfig.tokenGenerator = new TokenGenerator();
        for (int tokenStelle = 0; tokenStelle < TestConfig.FAMILY_COUNT; tokenStelle++)
            TestConfig.tokenList[tokenStelle] = TestConfig.tokenGenerator.generateTokenForGoogleAccount("msp.ss17@gmail.com");
    }
}