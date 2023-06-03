package ru.job4j.urlshortcut.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    /**
     * ObjectMapper object.
     * @param objectMapper ObjectMapper. Type {@link com.fasterxml.jackson.databind.ObjectMapper}
     */
    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * NPE handler
     * @param e Exception. Type {@link java.lang.Exception}
     * @param response Response. Type {@link javax.servlet.http.HttpServletResponse}
     * @throws IOException exception. Type {@link java.io.IOException}
     */
    @ExceptionHandler(value = {NullPointerException.class})
    public void npeHandler(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", "Some fields is empty");
                put("details", e.getMessage());
            }
        }));
        log.error(e.getMessage());
    }

    /**
     * Additional handler.
     * @param e Exception. Type {@link java.lang.Exception}
     * @param request Request. Type {@link javax.servlet.http.HttpServletRequest}
     * @param response Response. Type {@link javax.servlet.http.HttpServletResponse}
     * @throws IOException exception. Type {@link java.io.IOException}
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void additionalHandler(Exception e,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
    }

    /**
     * MethodArgumentNotValidException handler.
     * @param e MethodArgumentNotValidException. Type {@link org.springframework.web.bind.MethodArgumentNotValidException}
     * @return ResponseEntity<?>.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                e.getFieldErrors().stream()
                        .map(f -> Map.of(
                                f.getField(),
                                String.format("%s. Actual value: %s",
                                        f.getDefaultMessage(), f.getRejectedValue())
                        ))
                        .collect(Collectors.toList())
        );
    }
}
