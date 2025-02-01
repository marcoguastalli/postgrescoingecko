package net.marco27.api.postgrescoingecko.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LogFileCleanerTest {

    @Mock
    LogFileCleaner logFileCleaner;

    @Test
    void testCreateXMLGregorianCalendarFromDate() throws IOException {
        assertThat(logFileCleaner, notNullValue());
        logFileCleaner.deleteLogFile();
        verify(logFileCleaner, times(1)).deleteLogFile();
    }
}
