package net.marco27.api.postgrescoingecko.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.dto.ErrorResponse;
import net.marco27.api.postgrescoingecko.exception.ConnectionException;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Handler fot Exceptions
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandlerController extends ResponseEntityExceptionHandler {

    ResponseEntity<ErrorResponse> handleError(@NonNull HttpStatus status, @NonNull Exception e) {
        log.error("Exception: ", e);
        ErrorResponse error = new ErrorResponse(
                e.getMessage(),
                e.getClass().getSimpleName(),
                status.value()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(@NonNull IllegalArgumentException e) {
        log.warn("Bad request: ", e);
        return handleError(BAD_REQUEST, e);
    }

    @ExceptionHandler(ConnectionException.class)
    public ResponseEntity<ErrorResponse> handleConnectionException(@NonNull ConnectionException e) {
        return handleError(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDocumentNotFound(@NonNull DocumentNotFoundException e) {
        return handleError(NOT_FOUND, e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(@NonNull Exception e) {
        return handleError(INTERNAL_SERVER_ERROR, e);
    }
}
