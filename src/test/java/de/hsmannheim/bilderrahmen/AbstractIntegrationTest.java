package de.hsmannheim.bilderrahmen;

import de.hsmannheim.bilderrahmen.client.AbstractStompFrameHandlerClient;
import de.hsmannheim.bilderrahmen.builder.StompSessionBuilder;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.springframework.test.context.TestPropertySource;

/**
 * Created by mjando on 19.06.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test.properties")
public abstract class AbstractIntegrationTest<DatatypeToTest>{

    protected BlockingQueue<DatatypeToTest> blockingQueueClient1;
    protected BlockingQueue<DatatypeToTest> blockingQueueClient2;
    protected BlockingQueue<DatatypeToTest> blockingQueueClient3;


    private String WEBSOCKET_URI;

    @LocalServerPort
    private int port;

    protected void generalSetup(AbstractStompFrameHandlerClient stompFrameHandlerClient) throws Exception {

        blockingQueueClient1 = new LinkedBlockingDeque<>();
        blockingQueueClient2 = new LinkedBlockingDeque<>();
        blockingQueueClient3 = new LinkedBlockingDeque<>();

        WEBSOCKET_URI = "ws://localhost:" + port + "/websocket";

        StompSessionBuilder sessionBuilder = new StompSessionBuilder().build(WEBSOCKET_URI);

        StompSession sessionClient1 = sessionBuilder.buildStompSession(getSessionString(), blockingQueueClient1, stompFrameHandlerClient);
        StompSession sessionClient2 = sessionBuilder.buildStompSession(getSessionString(), blockingQueueClient2, stompFrameHandlerClient);

        sessionClient1.send(getSendString(), getObjectToTest());

    }


    protected abstract String getSessionString();

    protected abstract String getSendString();

    protected abstract Object getObjectToTest();


}
