package net.marco27.api.postgrescoingecko.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.marco27.api.postgrescoingecko.config.ApplicationYmlConfig;
import net.marco27.api.postgrescoingecko.domain.JsonResponseResult;
import net.marco27.api.postgrescoingecko.domain.VersionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.marco27.api.postgrescoingecko.AppConstants.SLASH;
import static net.marco27.api.postgrescoingecko.domain.VersionBean.VERSION;

@RestController
@RequestMapping(SLASH + VERSION)
@Slf4j
public class VersionController {

    private final ApplicationYmlConfig applicationYmlConfig;

    @Autowired
    public VersionController(@NonNull ApplicationYmlConfig applicationYmlConfig) {
        this.applicationYmlConfig = applicationYmlConfig;
    }

    @GetMapping("/version")
    public ResponseEntity<JsonResponseResult> releaseNotes() {
        // start
        final StopWatch stopWatch = new StopWatch("VersionController.releaseNotes");
        stopWatch.start();

        // do
        final VersionBean versionBean = new VersionBean();
        log.debug("Version: {}", applicationYmlConfig.getVersion());

        // end
        stopWatch.stop();
        log.info(stopWatch.shortSummary());

        return ResponseEntity.ok(new JsonResponseResult(versionBean));
    }
}
