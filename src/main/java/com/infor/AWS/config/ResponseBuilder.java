package com.infor.AWS.config;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

/**
 * Central place for shaping every API response - success or error - so every
 * endpoint returns the same envelope instead of ad-hoc structures per controller.
 */
public final class ResponseBuilder {

    private ResponseBuilder() {
        // static-only utility class, never instantiated
    }

    // ---- Success responses ----

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return build(HttpStatus.OK, "Success", data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return build(HttpStatus.OK, message, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return build(HttpStatus.CREATED, "Created", data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent(String message) {
        return build(HttpStatus.OK, message, null);
    }

    // ---- Error responses ----

    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message) {
        return build(status, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return build(HttpStatus.NOT_FOUND, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> conflict(String message) {
        return build(HttpStatus.CONFLICT, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        return build(HttpStatus.UNAUTHORIZED, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return build(HttpStatus.BAD_REQUEST, message, null);
    }

    // ---- Fully custom, for anything the named helpers above don't cover ----

    public static <T> ResponseEntity<ApiResponse<T>> build(HttpStatus status, String message, T data) {
        ApiResponse<T> body = new ApiResponse<>(
                status.is2xxSuccessful(),
                message,
                data,
                Instant.now(),
                MDC.get("requestId")
        );
        return ResponseEntity.status(status).body(body);
    }
}