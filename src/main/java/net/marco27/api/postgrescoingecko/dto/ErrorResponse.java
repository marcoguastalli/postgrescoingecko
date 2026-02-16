package net.marco27.api.postgrescoingecko.dto;

/**
 * Class returned as json when a Runtime Exception occur
 *
 * @param message   of the error
 * @param exception the Exception
 * @param status    to return
 */
public record ErrorResponse(String message, String exception, int status) {
}
