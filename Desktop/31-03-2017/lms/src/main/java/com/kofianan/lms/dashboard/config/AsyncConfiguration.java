package com.kofianan.lms.dashboard.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.kofianan.lms.dashboard.async.ExceptionHandlingAsyncTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer {
    private final Logger log = LoggerFactory.getLogger(AsyncConfiguration.class);

    private final LmsProperties lmsProperties;

    public AsyncConfiguration(LmsProperties lmsProperties) {
        this.lmsProperties = lmsProperties;
    }

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        log.debug("Creating Async Task Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(lmsProperties.getAsync().getCorePoolSize());
        executor.setMaxPoolSize(lmsProperties.getAsync().getMaxPoolSize());
        executor.setQueueCapacity(lmsProperties.getAsync().getQueueCapacity());
        executor.setThreadNamePrefix("lms-Executor-");
        return new ExceptionHandlingAsyncTaskExecutor(executor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
