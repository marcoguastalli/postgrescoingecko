package net.marco27.api.postgrescoingecko.utils;


import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.slf4j.Logger;

import java.util.Map;

import static java.lang.String.format;

/**
 * Util class for log operations
 */
public class LoggerUtils {

    private LoggerUtils() {
        throw new UnsupportedOperationException("Do not instantiate Util class");
    }

    /**
     * @param log        log to log using the instance class name and not this class
     * @param trackingId the HEADER_UNIQUE_ID
     * @param message    to write in the log
     */
    public static void logDebugTrackingId(@NonNull Logger log, @NonNull String trackingId, @NonNull String message) {
        log.debug("trID: {} - {}", trackingId, message);
    }

    /**
     * @param log        log to log using the instance class name and not this class
     * @param trackingId the HEADER_UNIQUE_ID
     * @param message    to write in the log
     */
    public static void logInfoTrackingId(@NonNull Logger log, @NonNull String trackingId, @NonNull String message) {
        log.info("trID: {} - {}", trackingId, message);
    }

    /**
     * @param log        log to log using the instance class name and not this class
     * @param trackingId the HEADER_UNIQUE_ID
     * @param message    to write in the log
     */
    public static void logWarnTrackingId(@NonNull Logger log, @NonNull String trackingId, @NonNull String message) {
        log.warn("trID: {} - {}", trackingId, message);
    }

    /**
     * @param log        log to log using the instance class name and not this class
     * @param trackingId the HEADER_UNIQUE_ID
     * @param message    to write in the log
     * @param e          the Exception
     */
    public static void logErrorTrackingId(@NonNull Logger log, @NonNull String trackingId, @NonNull String message, @NonNull Exception e) {
        log.error(format("trID: %s - %s", trackingId, message), e);
    }

    /**
     * Log just the error message, without the Exception
     *
     * @param log        log to log using the instance class name and not this class
     * @param trackingId the HEADER_UNIQUE_ID
     * @param message    to write in the log
     */
    public static void logErrorTrackingId(@NonNull Logger log, @NonNull String trackingId, @NonNull String message) {
        log.error(format("trID: %s - %s", trackingId, message));
    }

    /**
     * @param log                log to log using the instance class name and not this class
     * @param trackingId         the HEADER_UNIQUE_ID
     * @param httpServletRequest to be logged
     */
    public static void logHttpRequestParameters(@NonNull Logger log, @NonNull String trackingId, @NonNull HttpServletRequest httpServletRequest) {
        final Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (final Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            final String paramName = entry.getKey();
            final String[] paramValues = entry.getValue();
            for (final String value : paramValues) {
                logInfoTrackingId(log, trackingId, format("Request Parameter '%s' has value: %s", paramName, value));
            }
        }
    }
}
