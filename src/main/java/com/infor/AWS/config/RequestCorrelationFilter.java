package com.infor.AWS.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class RequestCorrelationFilter extends OncePerRequestFilter {

    private static final String REQUEST_ID_HEADER = "X-Request-Id";
    private static final String MDC_REQUEST_ID_KEY = "requestId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Reuse an incoming request id if one was passed in (useful once you have
        // multiple services calling each other, or a load balancer that sets one),
        // otherwise generate a fresh one.
        String requestId = request.getHeader(REQUEST_ID_HEADER);
        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }

        response.setHeader(REQUEST_ID_HEADER, requestId);

        try {
            MDC.put(MDC_REQUEST_ID_KEY, requestId);
            filterChain.doFilter(request, response);
        } finally {
            // Critical: Tomcat reuses worker threads across requests. Without this,
            // a value set here can leak into the next unrelated request handled by
            // the same thread - a real bug, not a cleanliness nitpick.
            MDC.remove(MDC_REQUEST_ID_KEY);
        }
    }
}
