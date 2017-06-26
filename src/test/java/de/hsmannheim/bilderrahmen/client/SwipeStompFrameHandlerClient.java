package de.hsmannheim.bilderrahmen.client;

import de.hsmannheim.bilderrahmen.swipe.Swipe;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mjando on 19.06.17.
 */
public class SwipeStompFrameHandlerClient extends AbstractStompFrameHandlerClient {

    public SwipeStompFrameHandlerClient buildClient(BlockingQueue blockingClient) {
        this.blockingClient = blockingClient;
        return this;
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return Swipe.class;
    }
}
