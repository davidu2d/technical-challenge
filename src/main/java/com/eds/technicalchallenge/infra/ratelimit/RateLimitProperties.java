package com.eds.technicalchallenge.infra.ratelimit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "api.rate-limiting")
public class RateLimitProperties {

    private boolean enabled;
}
