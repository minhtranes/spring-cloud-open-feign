package vn.ifa.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.Retryer;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WeathersAuthConfig {
    
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    
    private static final String AUTHORIZE_PROVIDER = "wso2";

    @Bean
    public RequestInterceptor weathersAuthInterceptor() {

        return new CustomOAuth2FeignRequestInterceptor(authorizedClientManager, AUTHORIZE_PROVIDER);
    }
    
//    @Bean
//    Retryer naiveRetryer(FeignRetryProperties feignRetryProperties) {
//        return new NaiveRetryer(feignRetryProperties);
//    }
    
}
