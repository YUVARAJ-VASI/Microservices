package com.task.department.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NullMarked;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class MDCFilter extends OncePerRequestFilter {

    @Override
    @NullMarked
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        try {

            String corID = request.getHeader("X-Correlation-Id");

            if (StringUtils.isBlank(corID)) {
                commonErrorPage(response);
                return;
            }

            MDC.put("CorrelationId", corID);

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("", e);
        }
        finally {
            MDC.remove("CorrelationId");
        }
    }


    protected void commonErrorPage(HttpServletResponse response) throws IOException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("Bad Request");
    }
}
