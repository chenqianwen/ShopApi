package com.shop.api.web.controller.authentication;

import com.shop.api.authentication.TokenUtils;
import com.shop.api.properties.SecurityConstants;
import com.shop.api.web.exception.ErrorException;
import com.shop.api.web.model.authentication.AuthenticationRequest;
import com.shop.api.web.model.authentication.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * created by ygl on 2018/1/26
 */
@RestController
@RequestMapping(SecurityConstants.DEFAULT_AUTHENTICATION_URL_PREFIX)
public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping
    public AuthenticationResponse authentication(@Valid @RequestBody AuthenticationRequest authRequest){
        String clientId = authRequest.getClientId();
        String phone = authRequest.getPhone();
        if (tokenUtils.clientIsExist(clientId)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(phone);
            if (userDetails == null) {
                throw new ErrorException("未找到对应的USER:"+phone);
            }
            String accessToken = tokenUtils.generateToken(phone, clientId);
            int expiresIn = tokenUtils.getTokenExpiresIn();
            return new AuthenticationResponse(accessToken,expiresIn);
        } else {
            throw new ErrorException("clientId错误:"+clientId);
        }
    }
}
