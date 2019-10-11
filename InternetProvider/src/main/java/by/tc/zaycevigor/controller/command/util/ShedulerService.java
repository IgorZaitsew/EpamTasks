package by.tc.zaycevigor.controller.command.util;

import by.tc.zaycevigor.controller.Controller;

import javax.naming.ldap.Control;
import java.sql.Connection;
import java.util.concurrent.*;

public class ShedulerService {
    private ScheduledExecutorService pool= Executors.newSingleThreadScheduledExecutor();
    public static final int DEFAULT_PERIOD=10;

    public void shedulerLaunch(Runnable task)
    {
        pool.scheduleAtFixedRate(task, 0,DEFAULT_PERIOD, TimeUnit.SECONDS);
    }
    public void shedulerLaunch(Thread task, int initDelay, int period) {
        pool.scheduleAtFixedRate(task, initDelay, period, TimeUnit.SECONDS);
    }
}
