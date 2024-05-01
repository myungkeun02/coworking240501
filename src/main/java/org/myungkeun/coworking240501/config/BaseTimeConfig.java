package org.myungkeun.coworking240501.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@Configuration
@EnableJpaAuditing

public class BaseTimeConfig {
    @PostConstruct
    public void setSeoulTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
