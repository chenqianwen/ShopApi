package com.shop.api.web.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by ygl on 2018/1/26
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {
    /**
     * 授权令牌，Access_Token。
     */
    private String access_token;
    /**
     * 该access token的有效期，单位为秒。
     */
    private int expires_in;
}
