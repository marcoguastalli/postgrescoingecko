package net.marco27.api.postgrescoingecko.controller;

import net.marco27.api.postgrescoingecko.config.ApplicationYmlConfig;
import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import net.marco27.api.postgrescoingecko.service.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ApiControllerTest {

    @SpyBean
    @Autowired
    ApplicationYmlConfig applicationYmlConfig;
    @Mock
    ApiService apiService;
    ApiController apiController;

    @BeforeEach
    void init() {
        apiController = new ApiController(applicationYmlConfig, apiService);
    }

    @Test
    void testGetLatterBlockchainBlocks() throws DocumentNotFoundException {
        // when
        when(apiService.getJson(anyString())).thenReturn(new byte[1]);
        // given
        final ResponseEntity<byte[]> response = apiController.getLatterBlockchainBlocks();
        // then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new byte[1]));
        verify(applicationYmlConfig, times(1)).getUrlToCall();
        verify(apiService, times(1)).getJson(anyString());
    }
}
