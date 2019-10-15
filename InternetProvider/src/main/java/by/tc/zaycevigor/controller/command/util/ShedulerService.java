package by.tc.zaycevigor.controller.command.util;

import java.util.concurrent.*;

import static by.tc.zaycevigor.controller.command.util.Constant.DEFAULT_SHEDULE_PERIOD;
import static by.tc.zaycevigor.controller.command.util.Constant.THREAD_CHECK_SLEEP_TIME_MILLIS;

public class ShedulerService implements Runnable {

    private ScheduledExecutorService pool= Executors.newSingleThreadScheduledExecutor();
    private Thread controllerThread;
    public ShedulerService(Thread controllerThread){
        this.controllerThread = controllerThread;
    }
    @Override
    public void run(){
         shedulerLaunch(new BalanceSheduler());
         terminationController();
    }

    private void shedulerLaunch(Runnable task)
    {
        pool.scheduleAtFixedRate(task, 0,DEFAULT_SHEDULE_PERIOD, TimeUnit.SECONDS);
    }
    private void shedulerLaunch(Thread task, int initDelay, int period) {
        pool.scheduleAtFixedRate(task, initDelay, period, TimeUnit.SECONDS);
    }

    private void terminationController(){
        while(controllerThread.isAlive()){
            try {
                Thread.sleep(THREAD_CHECK_SLEEP_TIME_MILLIS);
            } catch (InterruptedException e) {
                shutdownAndAwaitTermination(pool);
            }
        }
        shutdownAndAwaitTermination(pool);
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
