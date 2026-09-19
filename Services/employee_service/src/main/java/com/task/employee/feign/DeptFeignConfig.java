package com.task.employee.feign;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

public class DeptFeignConfig {

    @Bean
    public Request.Options options(){
        return new Request.Options(2, TimeUnit.SECONDS, 3, TimeUnit.SECONDS, true);
    }

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }

    @Bean
    @Scope("prototype")
    public Retryer retryer(){
        return new Retryer.Default(1000, 1000, 3);
    }
}
