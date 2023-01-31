package vn.ifa.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import feign.RequestInterceptor;
import feign.Retryer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WeathersAuthConfig {

    private final OAuth2Provider oauth2Provider;
    private static final String AUTHZ_SERVER_NAME = "wso2";

    @Bean
    public RequestInterceptor weathersAuthInterceptor() {

        return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION,
                                                           oauth2Provider.getAuthenticationToken(AUTHZ_SERVER_NAME));
    }
    
    @Bean
    @ConfigurationProperties(prefix = "feign.native-retryer")
    FeignRetryProperties feignRetryProperties() {
        return new FeignRetryProperties();
    }
    
    @Bean
    Retryer naiveRetryer(FeignRetryProperties feignRetryProperties) {
        return new NaiveRetryer(feignRetryProperties);
    }
}
