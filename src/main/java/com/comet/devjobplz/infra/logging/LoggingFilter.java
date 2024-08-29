package com.comet.devjobplz.infra.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    private static final List<String> DO_NOT_LOG_URI = List.of("/actuator/prometheus");
    private static final List<String> DO_NOT_LOG_PARAM = List.of("base64EncodedImage");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("traceId", UUID.randomUUID().toString());
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response), filterChain);
        }
        MDC.clear();
    }

    protected void doFilterWrapped(RequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!DO_NOT_LOG_URI.contains(request.getRequestURI())) {
                logRequest(request);
            }

            filterChain.doFilter(request, response);
        } finally {
            if (!DO_NOT_LOG_URI.contains(request.getRequestURI())) {
                logResponse(response);
            }

            response.copyBodyToResponse();
        }
    }

    private static void logRequest(RequestWrapper request) throws IOException {
        String queryString = request.getQueryString();
        log.info("Ip: {} Request: {} [{}]",
                getClientIP(request),
                request.getMethod(),
                queryString == null ? request.getRequestURI() : request.getRequestURI() + queryString);

        logPayload(request.getContentType(), request.getInputStream());
    }

    private static String getClientIP(RequestWrapper request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    private static void logResponse(ContentCachingResponseWrapper response) {
        log.info("Response : {}", response.getStatus());
    }

    private static void logPayload(String contentType, InputStream inputStream) throws IOException {
        boolean visible = isVisible(MediaType.valueOf(contentType == null ? "application/json" : contentType));
        if (visible) {
            byte[] content = StreamUtils.copyToByteArray(inputStream);
            if (content.length > 0) {
                String contentString = new String(content);
                log.info("Payload: {}", removeUnnecessaryParams(contentString));
            }
        } else {
            log.info("Payload: Binary Content");
        }
    }

    private static String removeUnnecessaryParams(String content) {
        StringBuilder sb = new StringBuilder();

        for (String i : content.split(",")) {
            String[] params = i.split(":");

            if (DO_NOT_LOG_PARAM.contains(params[0].replace("\"", ""))) {
                continue;
            }

            sb.append(i);
        }

        return sb.toString();
    }


    private static boolean isVisible(MediaType mediaType) {
        final List<MediaType> VISIBLE_TYPES = Arrays.asList(
                MediaType.valueOf("text/*"),
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.valueOf("application/*+json"),
                MediaType.valueOf("application/*+xml"),
                MediaType.MULTIPART_FORM_DATA
        );

        return VISIBLE_TYPES.stream()
                .anyMatch(visibleType -> visibleType.includes(mediaType));
    }
}