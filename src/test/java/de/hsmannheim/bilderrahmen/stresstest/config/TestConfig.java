package de.hsmannheim.bilderrahmen.stresstest.config;

import de.hsmannheim.bilderrahmen.builder.StompSessionBuilder;
import de.hsmannheim.bilderrahmen.token.Token;
import de.hsmannheim.bilderrahmen.token.TokenGenerator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestConfig {

    // url building for accessing the backend
    final static String HOST_URL = "ws://5.189.157.9:";
    final static int PORT = 8090;
    final static String WEBSOCKET_SUFFIX = "/websocket";
    final static String REQUEST_URL = HOST_URL + PORT + WEBSOCKET_SUFFIX;

    //Session Builder for master and slaves devices
    public final static StompSessionBuilder sessionBuilder = new StompSessionBuilder().build(REQUEST_URL);

    // thread count to simulate multi request at the same time
    public final static int THREAD_COUNT = 3;
    public final static int WEBSOCKET_TIMEOUT_VALUE = 5;
    public final static TimeUnit WEBSEOCKET_TIMEOUT_TIME = SECONDS;

    // how many families should be to simulate and how many devices each family should have
    public final static int DEVICES_PER_FAMILY_COUNT = 3;
    public final static int FAMILY_COUNT = 10;
    public static final String SWIPE_PREFIX_URL_STRING = "/queue/swipe/";
    public static final String SEND_PREFIX_URL_STRING = "/bilderrahmen/swipe/";
    public static ArrayList<Thread> threads = new ArrayList();
    //1 Token for each family
    public static Token[] tokenList = new Token[FAMILY_COUNT];
    public static TokenGenerator tokenGenerator;

}