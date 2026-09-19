package com.task.employee.feign;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;

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
}
