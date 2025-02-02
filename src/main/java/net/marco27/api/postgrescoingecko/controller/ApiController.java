package net.marco27.api.postgrescoingecko.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.config.ApplicationYmlConfig;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import net.marco27.api.postgrescoingecko.model.ApiTransaction;
import net.marco27.api.postgrescoingecko.model.Coin;
import net.marco27.api.postgrescoingecko.service.ApiService;
import net.marco27.api.postgrescoingecko.service.ApiTransactionService;
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
import static net.marco27.api.postgrescoingecko.utils.LoggerUtils.logDebugTrackingId;
import static net.marco27.api.postgrescoingecko.utils.LoggerUtils.logInfoTrackingId;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(SLASH + VERSION)
@Slf4j
public class ApiController {

    private final ApplicationYmlConfig applicationYmlConfig;
    private final ApiTransactionService apiTransactionService;
    private final ApiService apiService;

    @Autowired
    public ApiController(@NonNull ApplicationYmlConfig applicationYmlConfig,
                         @NonNull ApiTransactionService apiTransactionService,
                         @NonNull ApiService apiService) {
        this.applicationYmlConfig = applicationYmlConfig;
        this.apiTransactionService = apiTransactionService;
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

        // track transaction
        final String trackingId = httpServletRequest.getSession().getId();
        ApiTransaction apiTransaction = new ApiTransaction();
        apiTransaction.setTransactionId(trackingId);

        // do
        int numberOfCoins = 0;
        final byte[] jsonInBytes = apiService.getJson(applicationYmlConfig.getUrlToCall());
        final List<Coin> coins = getListOfObjectsFromJSonBytes(jsonInBytes, trackingId, Coin.class);
        if (null != coins) {
            numberOfCoins = coins.size();
            logInfoTrackingId(log, trackingId, format("Found %s coins", numberOfCoins));
        }
        // fulfill apiTransaction object
        apiTransaction.setResult(HttpStatus.OK.value());
        apiTransaction.setNumberOfCoins(numberOfCoins);
        final ApiTransaction apiTransactionPersisted = apiTransactionService.save(apiTransaction);
        logDebugTrackingId(log, trackingId, format("Stored transaction at %s", apiTransactionPersisted.getCreated()));

        // end
        stopWatch.stop();
        log.info(stopWatch.shortSummary());

        return new ResponseEntity<>(jsonInBytes, HttpStatus.OK);
    }

}
