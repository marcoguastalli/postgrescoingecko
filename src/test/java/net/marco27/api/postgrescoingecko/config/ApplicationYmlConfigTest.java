package net.marco27.api.postgrescoingecko.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationYmlConfigTest {

    @Autowired
    ApplicationYmlConfig applicationYmlConfig;

    @Test
    void testGetVersion() {
        assertThat(applicationYmlConfig.getVersion(), notNullValue());
        assertThat(applicationYmlConfig.getVersion(), is("v1"));
    }

}
