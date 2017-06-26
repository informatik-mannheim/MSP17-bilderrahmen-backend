package de.hsmannheim.bilderrahmen.stresstest.util;

import de.hsmannheim.bilderrahmen.stresstest.config.TestConfig;
import de.hsmannheim.bilderrahmen.stresstest.thread.SendAndPollThread;
import de.hsmannheim.bilderrahmen.token.Token;

import java.util.ArrayList;

public class ThreadUtil {

    public static boolean success = true;

    static Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread th, Throwable ex) {
            success = false;
        }
    };

    public static void addThreadToPoolAndStartIt(Token token) {
        Thread thread = null;
        try {
            thread = new Thread(
                    new SendAndPollThread(
                            token.getId()
                    ),
                    "SendAndPollThread "
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        TestConfig.threads.add(thread);
        thread.setUncaughtExceptionHandler(h);
        thread.start();
    }

    public Thread getAlreadyDoneThread(ArrayList<Thread> threads) {
        Thread threadToRemove = null;
        for (Thread thread : threads)
            if (!thread.isAlive())
                threadToRemove = thread;
        return threadToRemove;
    }

    public boolean aThreadIsAlreadyDone(ArrayList<Thread> threads) {
        boolean check = false;
        for (Thread thread : threads) {
            if (!check)
                check = thread.isAlive();
        }
        return check;
    }

}