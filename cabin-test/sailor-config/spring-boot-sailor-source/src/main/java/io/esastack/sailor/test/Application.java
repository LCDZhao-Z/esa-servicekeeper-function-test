package io.esastack.sailor.test;


import io.esastack.cabin.support.bootstrap.CabinAppBootstrap;
import io.esastack.sailor.test.controller.ArgConcurrentLimitController;
import io.esastack.sailor.test.controller.ArgsCircuitBreakerController;
import io.esastack.sailor.test.controller.ArgsRateLimitController;
import io.esastack.sailor.test.controller.CircuitBreakerController;
import io.esastack.sailor.test.controller.ConcurrentLimitController;
import io.esastack.sailor.test.controller.RateLimitController;
import io.esastack.sailor.test.controller.RetryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InterruptedException, IOException {
        CabinAppBootstrap.run(args);
        ApplicationContext context = SpringApplication.run(Application.class, args);
        rateLimitTest(context);
//        concurrentTest(context);
//        circuitBreakerTest(context);
//        retryTest(context);
//        argsRateLimitTest(context);
//        argConcurrentTest(context);
        argsCircuitBreakerTest(context);
    }

    private static void argsCircuitBreakerTest(ApplicationContext context) throws InterruptedException {
        ArgsCircuitBreakerController argsCircuitBreaker = context.getBean(ArgsCircuitBreakerController.class);
        System.out.println(argsCircuitBreaker.withoutFallback("Liming"));
        System.out.println(argsCircuitBreaker.withoutFallback("Liming"));
        System.out.println(argsCircuitBreaker.withoutFallback("Liming"));
    }

    private static void argConcurrentTest(ApplicationContext context) throws InterruptedException, IOException {
        ArgConcurrentLimitController argConcurrent = context.getBean(ArgConcurrentLimitController.class);
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("aaa"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("aaa"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("aaa"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("aaa"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("aaa"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(20 * 1000);
    }

    private static void argsRateLimitTest(ApplicationContext context) throws InterruptedException, IOException {
        ArgsRateLimitController argsRateLimit = context.getBean(ArgsRateLimitController.class);
        System.out.println(argsRateLimit.withoutFallback("LiMing"));
        System.out.println(argsRateLimit.withoutFallback("LiMing"));
        System.out.println(argsRateLimit.withoutFallback("LiMing"));
        System.out.println(argsRateLimit.withoutFallback("LiMing"));
        System.out.println(argsRateLimit.withoutFallback("WangWu"));
        System.out.println(argsRateLimit.withoutFallback("WangWu"));
        System.out.println(argsRateLimit.withoutFallback("WangWu"));
        System.out.println(argsRateLimit.withoutFallback("WangWu"));
    }

    private static void retryTest(ApplicationContext context) throws InterruptedException, IOException {
        RetryController retry = context.getBean(RetryController.class);
        System.out.println(retry.retryWithoutFallback());
        Thread.sleep(10 * 1000);
        System.out.println(retry.retryWithoutFallback());
        Thread.sleep(10 * 1000);
        System.out.println(retry.retryWithoutFallback());
    }

    private static void circuitBreakerTest(ApplicationContext context) throws InterruptedException {
        CircuitBreakerController circuitBreaker = context.getBean(CircuitBreakerController.class);
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
    }

    private static void rateLimitTest(ApplicationContext context) throws InterruptedException {
        RateLimitController rateLimit = context.getBean(RateLimitController.class);
        System.out.println(rateLimit.withoutFallback());
        System.out.println(rateLimit.withoutFallback());
        System.out.println(rateLimit.withoutFallback());
        System.out.println(rateLimit.withoutFallback());
        System.out.println(rateLimit.withoutFallback());
        System.out.println(rateLimit.withoutFallback());
        Thread.sleep(2000);
        System.out.println("2s:" + rateLimit.withoutFallback());
        Thread.sleep(3000);
        System.out.println("5s:" + rateLimit.withoutFallback());
    }

    private static void concurrentTest(ApplicationContext context) throws InterruptedException {
        ConcurrentLimitController concurrentLimit = context.getBean(ConcurrentLimitController.class);
        new Thread(() ->
        {
            try {
                System.out.println("1:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        new Thread(() ->
        {
            try {
                System.out.println("2:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        new Thread(() ->
        {
            try {
                System.out.println("3:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        new Thread(() ->
        {
            try {
                System.out.println("4:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        new Thread(() ->
        {
            try {
                System.out.println("5:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        new Thread(() ->
        {
            try {
                System.out.println("6:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        new Thread(() ->
        {
            try {
                System.out.println("7:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(20 * 1000);
        new Thread(() ->
        {
            try {
                System.out.println("20s:" + concurrentLimit.withoutFallback());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(21 * 1000);
    }
}
