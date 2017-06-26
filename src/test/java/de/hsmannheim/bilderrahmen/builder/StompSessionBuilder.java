package de.hsmannheim.bilderrahmen.builder;

import de.hsmannheim.bilderrahmen.client.AbstractStompFrameHandlerClient;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.concurrent.BlockingQueue;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by mjando on 29.05.17.
 */
public class StompSessionBuilder {

    String WEBSOCKET_URI;

    public StompSession buildStompSession(String path, BlockingQueue blockingClient, AbstractStompFrameHandlerClient stompFrameHandlerClient) throws Exception {
        StompSession sessionClient = getSessionClient();
        sessionClient.subscribe(path, stompFrameHandlerClient.getClass().newInstance().buildClient(blockingClient));
        return sessionClient;
    }

    protected StompSession getSessionClient() throws InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(
                asList(new WebSocketTransport(new StandardWebSocketClient()))));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient
                .connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {
                })
                .get(1, SECONDS);
    }


    public StompSessionBuilder build(String WEBSOCKET_URI) {
        this.WEBSOCKET_URI = WEBSOCKET_URI;
        return this;
    }


}
