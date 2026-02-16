package net.marco27.api.postgrescoingecko.controller;

import net.marco27.api.postgrescoingecko.config.ApplicationYmlConfig;
import net.marco27.api.postgrescoingecko.domain.JsonResponseResult;
import net.marco27.api.postgrescoingecko.domain.VersionBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static net.marco27.api.postgrescoingecko.AppConstantsTest.JUST_A_TIMESTAMP;
import static net.marco27.api.postgrescoingecko.domain.VersionBean.VERSION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class VersionControllerTest {

    @Autowired
    ApplicationYmlConfig applicationYmlConfig;

    VersionController versionController;

    @BeforeEach
    void init() {
        versionController = new VersionController(applicationYmlConfig);
    }

    @Test
    void testReleaseNotes() {
        final ResponseEntity<JsonResponseResult> response = versionController.releaseNotes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        final JsonResponseResult jsonResponseResult = response.getBody();
        assertThat(jsonResponseResult, notNullValue());
        final VersionBean versionBean = (VersionBean) jsonResponseResult.getResult();
        assertThat(versionBean, notNullValue());
        assertThat(versionBean.getVersion(), is(VERSION));
        assertThat(versionBean.getLastUpdate().toString(), is(JUST_A_TIMESTAMP));
    }

}
