package com.shop.api.properties;

public interface SecurityConstants {
    /**
     * 默认的认证URL
     */
    public static final String DEFAULT_AUTHENTICATION_URL_PREFIX = "/auth";
    /**
     * 默认的需要校验token的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_TOKEN = "access_token";
    /**
     * 默认的接收电话号码参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_PHONE = "phone";
    /**
     * 默认的接收clientId参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CLIENT = "clientId";
}
