package net.marco27.api.postgrescoingecko.exception;

/**
 * Exception for document not found
 */
public class DocumentNotFoundException extends Exception {

    public DocumentNotFoundException(final String message, Throwable cause) {
        super(message, cause);
    }

}
