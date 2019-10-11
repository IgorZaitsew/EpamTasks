package by.tc.zaycevigor.dao.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public enum MessageService {
    MESSAGE_SERVICE;

    private MessageSender sender = new MessageSender();
    private ExecutorService pool = Executors.newCachedThreadPool();

    public void sendMail(String subject, String text, String email) {
        pool.submit(() -> sender.send(subject, text, email));
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
