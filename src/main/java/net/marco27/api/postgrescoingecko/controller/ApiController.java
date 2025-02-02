package net.marco27.api.postgrescoingecko.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.config.ApplicationYmlConfig;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import net.marco27.api.postgrescoingecko.model.Coin;
import net.marco27.api.postgrescoingecko.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.String.format;
import static net.marco27.api.postgrescoingecko.AppConstants.SLASH;
import static net.marco27.api.postgrescoingecko.domain.VersionBean.VERSION;
import static net.marco27.api.postgrescoingecko.utils.JsonJacksonUtils.getListOfObjectsFromJSonBytes;
import static net.marco27.api.postgrescoingecko.utils.LoggerUtils.logInfoTrackingId;
import static net.marco27.api.postgrescoingecko.utils.LoggerUtils.logWarnTrackingId;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(SLASH + VERSION)
@Slf4j
public class ApiController {

    private final ApplicationYmlConfig applicationYmlConfig;
    private final ApiService apiService;

    @Autowired
    public ApiController(@NonNull ApplicationYmlConfig applicationYmlConfig,
                         @NonNull ApiService apiService) {
        this.applicationYmlConfig = applicationYmlConfig;
        this.apiService = apiService;
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType(APPLICATION_JSON_VALUE);
    }

    @GetMapping("/coinslist")
    public ResponseEntity<byte[]> getLatterBlockchainBlocks(@NonNull HttpServletRequest httpServletRequest) throws DocumentNotFoundException {
        // start
        final StopWatch stopWatch = new StopWatch("postgrescoingcecko.coinslist");
        stopWatch.start();
        final String trackingId = httpServletRequest.getSession().getId();

        // do
        final byte[] jsonInBytes = apiService.getJson(applicationYmlConfig.getUrlToCall());
        final List<Coin> coins = getListOfObjectsFromJSonBytes(jsonInBytes, trackingId, Coin.class);
        if (null != coins) {
            logInfoTrackingId(log, trackingId, format("Found %s coins", coins.size()));
        }

        // end
        stopWatch.stop();
        log.info(stopWatch.shortSummary());

        return new ResponseEntity<>(jsonInBytes, HttpStatus.OK);
    }

}
