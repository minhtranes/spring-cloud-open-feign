package vn.ifa.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "custom.native-retryer")
public class FeignRetryProperties {

    private int maxAttempts;
    private long period;
    private long maxPeriod;

}
