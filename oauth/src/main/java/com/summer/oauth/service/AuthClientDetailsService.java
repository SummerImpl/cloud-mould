package com.summer.oauth.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author pipe
 * @Date 2024/4/9 16:41
 */
@Service
public class AuthClientDetailsService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("");
        clientDetails.setClientSecret("");

        return clientDetails;
    }
}
