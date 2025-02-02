package net.marco27.api.postgrescoingecko.service;

import net.marco27.api.postgrescoingecko.exception.DocumentNotFoundException;
import net.marco27.api.postgrescoingecko.repository.PricesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiServiceTest {
    @Mock
    RestTemplate restTemplate;
    @Mock
    PricesRepository pricesRepository;
    @InjectMocks
    ApiService apiService;

    @BeforeEach
    void init() {
        apiService = new ApiService(restTemplate, pricesRepository);
    }

    @Test
    void testGetJson() throws DocumentNotFoundException {
        // when
        when(restTemplate.getForObject(anyString(), eq(byte[].class))).thenReturn(new byte[27]);
        // given
        final byte[] imageInBytes = apiService.getJson("/call");
        // then
        assertThat(imageInBytes, notNullValue());
        assertThat(imageInBytes.length, is(27));
    }
}
