package de.hsmannheim.bilderrahmen.client;

import de.hsmannheim.bilderrahmen.filesync.FileChanges;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mjando on 19.06.17.
 */
public class FileChangesStompFrameHandlerClient extends AbstractStompFrameHandlerClient {
    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return FileChanges.class;
    }

    @Override
    public AbstractStompFrameHandlerClient buildClient(BlockingQueue blockingClient) {
        this.blockingClient = blockingClient;
        return this;
    }
}
