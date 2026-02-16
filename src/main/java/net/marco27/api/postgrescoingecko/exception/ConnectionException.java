package net.marco27.api.postgrescoingecko.exception;

import lombok.NonNull;

/**
 * Exception for Http Connection errors
 */
public class ConnectionException extends RuntimeException {

    public ConnectionException(@NonNull String message, @NonNull Throwable cause) {
        super(message, cause);
    }
}
