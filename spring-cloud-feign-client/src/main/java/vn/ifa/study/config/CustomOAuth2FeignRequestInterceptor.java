package vn.ifa.study.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOAuth2FeignRequestInterceptor implements RequestInterceptor {

    private final OAuth2AuthorizedClientManager authorizedClientManager;
    
    private final String clientRegistrationId;

    public static final Authentication ANONYMOUS_USER_AUTHENTICATION = new AnonymousAuthenticationToken("key",
            "anonymous", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

    private String getAuthenticationToken() {

        final OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId(this.clientRegistrationId)
                                                                     .principal(ANONYMOUS_USER_AUTHENTICATION)
                                                                     .build();
        OAuth2AuthorizedClient oauth2AuthorizedClient = this.authorizedClientManager.authorize(request);

        if (oauth2AuthorizedClient != null) {
            return "Bearer " + oauth2AuthorizedClient.getAccessToken().getTokenValue();
        }

        return "";
    }

    @Override
    public void apply(RequestTemplate template) {

        template.header(HttpHeaders.AUTHORIZATION, this.getAuthenticationToken());
    }

}
