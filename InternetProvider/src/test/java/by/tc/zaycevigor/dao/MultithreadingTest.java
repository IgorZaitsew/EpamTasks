package by.tc.zaycevigor.dao;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.Thread.sleep;

public class MultithreadingTest {

     @Test
    void run() throws InterruptedException, ExecutionException {
         Supplier newsSupplier = () -> NewsService.getMessage();

         CompletableFuture<String> reader = CompletableFuture.supplyAsync(newsSupplier);
         CompletableFuture.completedFuture("!!")
                 .thenCombine(reader, (a, b) -> b + a)
                 .thenAccept(result -> System.out.println(result))
                 .get();
     }
    public static class NewsService {
        public static String getMessage() {
            try {
                Thread.currentThread().sleep(3000);
                return "Message";
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }
    public <T> void func(List <? extends String> val,List<? super Integer> val2){
        return;
    }
}
