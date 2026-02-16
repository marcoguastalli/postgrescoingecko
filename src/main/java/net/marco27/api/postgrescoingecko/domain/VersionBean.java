package net.marco27.api.postgrescoingecko.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static net.marco27.api.postgrescoingecko.AppConstants.JUST_A_DATE;
import static net.marco27.api.postgrescoingecko.AppConstants.JUST_A_TIME;

@Data
public class VersionBean {

    public static final String VERSION = "v1";

    private final String version;

    public VersionBean() {
        this.version = VERSION;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime lastUpdate = VERSION_DATE;

    public static final LocalDateTime VERSION_DATE = LocalDate.parse(JUST_A_DATE).atTime(LocalTime.parse(JUST_A_TIME));

}
