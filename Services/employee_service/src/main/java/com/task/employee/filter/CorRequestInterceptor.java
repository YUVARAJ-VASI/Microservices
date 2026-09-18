package com.task.employee.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class CorRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        String corId = MDC.get("CorrelationId");

        if (!StringUtils.isBlank(corId))
            template.header("X-Correlation-ID", corId);
    }
}
