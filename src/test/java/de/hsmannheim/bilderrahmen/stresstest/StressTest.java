package de.hsmannheim.bilderrahmen.stresstest;

import de.hsmannheim.bilderrahmen.stresstest.config.TestConfig;
import de.hsmannheim.bilderrahmen.stresstest.util.StressTestUtil;
import de.hsmannheim.bilderrahmen.stresstest.util.ThreadUtil;
import de.hsmannheim.bilderrahmen.token.Token;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by mjando on 13.06.17.
 */
public class StressTest {

    //Main test map.

    //Structure of the map
    //{
    //  swipe id: swipe device list relation {
    //                      swipe objectToTest : [clienst list (master and slave devices)]
    //  }
    //}
    private static Token[] tokenList;

    private final ThreadUtil threadUtil = new ThreadUtil();

    @BeforeClass
    public static void setUp() throws Exception {
        tokenList = StressTestUtil.buildTokenList();
    }


    /**
     * Test is executed with Threads, to simulate simultaneously connections
     * Thread can be configured at the top of the class. See {@link TestConfig#THREAD_COUNT}
     */
    @Test
    public void perfomanceTestForManyFamiliesAndDevices() throws Exception {
        for (Token token : tokenList) {

            while (!threadUtil.aThreadIsAlreadyDone(TestConfig.threads) &&
                    TestConfig.threads.size() >= TestConfig.THREAD_COUNT)
                Thread.sleep(100);

            if (threadUtil.aThreadIsAlreadyDone(TestConfig.threads))
                TestConfig.threads.remove(threadUtil.getAlreadyDoneThread(TestConfig.threads));


            ThreadUtil.addThreadToPoolAndStartIt(token);

        }

        Assert.assertEquals(ThreadUtil.success, true);

    }


}

