package de.hsmannheim.bilderrahmen.client;

import de.hsmannheim.bilderrahmen.masterselection.MasterSelection;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mjando on 19.06.17.
 */
public class MasterSelectionStompFrameHandlerClient extends  AbstractStompFrameHandlerClient {
    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return MasterSelection.class;
    }

    @Override
    public AbstractStompFrameHandlerClient buildClient(BlockingQueue blockingClient) {
        this.blockingClient = blockingClient;
        return this;
    }
}
