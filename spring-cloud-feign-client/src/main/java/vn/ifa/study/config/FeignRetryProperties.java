package vn.ifa.study.config;

import lombok.Data;

@Data
public class FeignRetryProperties {

    private int maxAttempts;
    private long period;
    private long maxPeriod;

}
