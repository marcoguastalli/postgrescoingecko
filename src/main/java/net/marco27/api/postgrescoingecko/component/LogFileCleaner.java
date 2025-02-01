package net.marco27.api.postgrescoingecko.component;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.marco27.api.postgrescoingecko.AppConstants.LOGS_FOLDER;

@Component
public class LogFileCleaner {

    @PostConstruct
    public void deleteLogFile() throws IOException {
        try (var dirStream = Files.walk(Paths.get(LOGS_FOLDER))) {
            dirStream.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> !file.isHidden())
                    .forEach(File::deleteOnExit);
        }
    }
}
