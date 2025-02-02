package net.marco27.api.postgrescoingecko.exception;

import lombok.NonNull;

/**
 * Exception for document not found
 */
public class DocumentNotFoundException extends Exception {

    public DocumentNotFoundException(@NonNull String message, @NonNull Throwable cause) {
        super(message, cause);
    }

}
