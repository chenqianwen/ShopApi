package com.shop.api.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "api.security")
public class SecurityProperties {
    /**
     * 默认的客户端参数
     */
    private String clientId = "shoppingmall";
    /**
     * 默认的token加密密钥
     */
    private String tokenSecret = "shopping123";
    /**
     * 默认的token过期秒数
     */
    private int tokenExpiresIn = 7200;

}
