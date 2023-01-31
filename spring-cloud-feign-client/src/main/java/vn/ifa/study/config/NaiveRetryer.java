package vn.ifa.study.config;

import feign.RetryableException;
import feign.Retryer;

public class NaiveRetryer implements Retryer {

    private FeignRetryProperties feignRetryProperties;

    int attempt;
    long sleptForMillis;

    public NaiveRetryer(FeignRetryProperties feignRetryProperties) {

        this.feignRetryProperties = feignRetryProperties;
        this.attempt = 1;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {

        if (attempt++ >= feignRetryProperties.getMaxAttempts()) {
            throw e;
        }

        long interval;

        if (e.retryAfter() != null) {
            interval = e.retryAfter().getTime() - System.currentTimeMillis();

            if (interval > feignRetryProperties.getMaxPeriod()) {
                interval = feignRetryProperties.getMaxPeriod();
            }

            if (interval < 0) {
                return;
            }

        } else {
            interval = nextMaxInterval();
        }

        try {
            Thread.sleep(interval);
        }
        catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
            throw e;
        }

        sleptForMillis += interval;

    }

    long nextMaxInterval() {

        long interval = (long) (feignRetryProperties.getPeriod() * Math.pow(1.5, attempt - 1));
        return interval > feignRetryProperties.getMaxPeriod() ? feignRetryProperties.getMaxPeriod() : interval;
    }

    @Override
    public Retryer clone() {

        return new NaiveRetryer(feignRetryProperties);
    }

}
