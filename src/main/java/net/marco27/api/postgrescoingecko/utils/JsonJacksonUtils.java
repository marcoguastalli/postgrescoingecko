package net.marco27.api.postgrescoingecko.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static net.marco27.api.postgrescoingecko.utils.LoggerUtils.logWarnTrackingId;

/**
 * Util class for json operations with the Jackson library
 */
@Slf4j
public class JsonJacksonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonJacksonUtils() {
        throw new UnsupportedOperationException("Do not instantiate Util class");
    }

    public static <T> List<T> getListOfObjectsFromJSonBytes(byte @NonNull [] jsonInBytes, @NonNull String trackingId, @NonNull Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonInBytes, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IllegalStateException | IOException e) {
            final String errorMessage = "Cannot create a json with the provided input";
            logWarnTrackingId(log, trackingId, errorMessage);
        }
        return null;
    }

    /**
     * @param inputStream the input Stream
     * @param trackingId  to track the user
     * @param clazz       type class to convert json
     * @param <T>         Java generics T
     * @return the corresponding clazz json object from the input Request
     */
    public static <T> T getObjectFromInputStream(@NonNull InputStream inputStream, @NonNull String trackingId, @NonNull Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, clazz);
        } catch (IllegalStateException | IOException e) {
            final String errorMessage = "Cannot create a json with the provided input";
            logWarnTrackingId(log, trackingId, errorMessage);
        }
        return null;
    }
}
