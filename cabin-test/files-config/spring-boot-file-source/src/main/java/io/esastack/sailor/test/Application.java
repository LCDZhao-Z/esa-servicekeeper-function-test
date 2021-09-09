package io.esastack.sailor.test;

import io.esastack.cabin.support.bootstrap.CabinAppBootstrap;
import io.esastack.sailor.test.controller.ArgConcurrentLimitComponent;
import io.esastack.sailor.test.controller.ArgsCircuitBreakerComponent;
import io.esastack.sailor.test.controller.ArgsRateLimitComponent;
import io.esastack.sailor.test.controller.CircuitBreakerComponent;
import io.esastack.sailor.test.controller.ConcurrentLimitComponent;
import io.esastack.sailor.test.controller.GroupComponent;
import io.esastack.sailor.test.controller.RateLimitComponent;
import io.esastack.sailor.test.controller.RetryComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InterruptedException, IOException {
        CabinAppBootstrap.run(args);
        System.setProperty("servicekeeper.config.dir", "./cabin-test/spring-boot-file-source/conf");
        System.setProperty("servicekeeper.config.name", "service-keeper.properties");
        ApplicationContext context = SpringApplication.run(Application.class, args);
        //rateLimitTest(context);
        //concurrentTest(context);
        //circuitBreakerTest(context);
        //retryTest(context);
        //argsRateLimitTest(context);
        //argConcurrentTest(context);
        //argsCircuitBreakerTest(context);
        groupTest(context);
    }

    private static void groupTest(ApplicationContext context) throws InterruptedException {
        GroupComponent group = context.getBean(GroupComponent.class);
        System.out.println(group.group());
        System.out.println(group.group());
        System.out.println(group.group());
        System.out.println(group.group());
        System.out.println(group.group());
        System.out.println(group.group());
    }

    private static void argsCircuitBreakerTest(ApplicationContext context) throws InterruptedException {
        ArgsCircuitBreakerComponent argsCircuitBreaker = context.getBean(ArgsCircuitBreakerComponent.class);
        System.out.println(argsCircuitBreaker.withoutFallback("DDD"));
        System.out.println(argsCircuitBreaker.withoutFallback("DDD"));
        System.out.println(argsCircuitBreaker.withoutFallback("DDD"));
        System.out.println(argsCircuitBreaker.withoutFallback("DDD"));
        System.out.println(argsCircuitBreaker.withoutFallback("CCC"));
        System.out.println(argsCircuitBreaker.withoutFallback("CCC"));
        System.out.println(argsCircuitBreaker.withoutFallback("CCC"));
        System.out.println(argsCircuitBreaker.withoutFallback("CCC"));
    }

    private static void argConcurrentTest(ApplicationContext context) throws InterruptedException, IOException {
        ArgConcurrentLimitComponent argConcurrent = context.getBean(ArgConcurrentLimitComponent.class);
        System.out.println(argConcurrent.withFallbackToValue("LiSi"));
        System.out.println(argConcurrent.withFallbackToValue("LiSi"));
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("ZhangSan"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("ZhangSan"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("ZhangSan"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("wangwu"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("wangwu"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("wangwu"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(argConcurrent.withFallbackToValue("wangwu"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(20 * 1000);
    }

    private static void argsRateLimitTest(ApplicationContext context) throws InterruptedException, IOException {
        ArgsRateLimitComponent argsRateLimit = context.getBean(ArgsRateLimitComponent.class);
        System.out.println(argsRateLimit.withoutFallback("LiSi"));
        System.out.println(argsRateLimit.withoutFallback("LiSi"));
        System.out.println(argsRateLimit.withoutFallback("ZhangSan"));
        System.out.println(argsRateLimit.withoutFallback("ZhangSan"));
        System.out.println(argsRateLimit.withoutFallback("ZhangSan"));
        System.out.println(argsRateLimit.withoutFallback("wangwu"));
        System.out.println(argsRateLimit.withoutFallback("wangwu"));
        System.out.println(argsRateLimit.withoutFallback("wangwu"));
        System.out.println(argsRateLimit.withoutFallback("wangwu"));
    }

    private static void retryTest(ApplicationContext context) throws InterruptedException, IOException {
        RetryComponent retry = context.getBean(RetryComponent.class);
        System.out.println(retry.retryWithoutFallback());
        Thread.sleep(10 * 1000);
        System.out.println(retry.retryWithoutFallback());
        Thread.sleep(10 * 1000);
        System.out.println(retry.retryWithoutFallback());
    }

    private static void circuitBreakerTest(ApplicationContext context) throws InterruptedException {
        CircuitBreakerComponent circuitBreaker = context.getBean(CircuitBreakerComponent.class);
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
        System.out.println(circuitBreaker.withoutFallback());
    }

    private static void rateLimitTest(ApplicationContext context) throws InterruptedException {
        RateLimitComponent rateLimit = context.getBean(RateLimitComponent.class);
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
        ConcurrentLimitComponent concurrentLimit = context.getBean(ConcurrentLimitComponent.class);
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
