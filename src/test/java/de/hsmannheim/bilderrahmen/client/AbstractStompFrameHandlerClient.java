package de.hsmannheim.bilderrahmen.client;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.util.concurrent.BlockingQueue;

/**
 * Created by mjando on 19.06.17.
 */
public abstract class AbstractStompFrameHandlerClient implements StompFrameHandler {

    public abstract java.lang.reflect.Type getPayloadType(StompHeaders stompHeaders);


    BlockingQueue blockingClient;

    public abstract AbstractStompFrameHandlerClient buildClient(BlockingQueue blockingClient);

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        blockingClient.offer(o);
    }

}
