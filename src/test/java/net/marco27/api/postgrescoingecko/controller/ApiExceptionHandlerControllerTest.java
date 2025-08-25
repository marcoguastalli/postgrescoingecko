package net.marco27.api.postgrescoingecko.controller;

import net.marco27.api.postgrescoingecko.dto.ErrorResponse;
import net.marco27.api.postgrescoingecko.exception.ConnectionException;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApiExceptionHandlerControllerTest {

    private ApiExceptionHandlerController apiExceptionHandlerController;

    @BeforeEach
    void init() {
        apiExceptionHandlerController = new ApiExceptionHandlerController();
    }

    @Test
    void testHandleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("no input parameter");
        ResponseEntity<ErrorResponse> response = apiExceptionHandlerController.handleIllegalArgument(ex);

        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody().message(), is("no input parameter"));
        assertThat(response.getBody().exception(), is("IllegalArgumentException"));
        assertThat(response.getBody().status(), is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void testHandleConnectionException() {
        ConnectionException ex = new ConnectionException("connection exception", new Throwable());
        ResponseEntity<ErrorResponse> response = apiExceptionHandlerController.handleConnectionException(ex);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(response.getBody().message(), is("connection exception"));
        assertThat(response.getBody().exception(), is("ConnectionException"));
        assertThat(response.getBody().status(), is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    void testHandleDocumentNotFoundException() {
        DocumentNotFoundException ex = new DocumentNotFoundException("not found", new Throwable());
        ResponseEntity<ErrorResponse> response = apiExceptionHandlerController.handleDocumentNotFound(ex);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody().message(), is("not found"));
        assertThat(response.getBody().exception(), is("DocumentNotFoundException"));
        assertThat(response.getBody().status(), is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void testHandleErrorDirectly() {
        ResponseEntity<ErrorResponse> result = apiExceptionHandlerController.handleError(
            HttpStatus.NOT_FOUND,
            new DocumentNotFoundException("This is just a 404 Test", new Throwable())
        );

        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(result.getBody().message(), is("This is just a 404 Test"));
        assertThat(result.getBody().exception(), is("DocumentNotFoundException"));
        assertThat(result.getBody().status(), is(HttpStatus.NOT_FOUND.value()));
    }
}
