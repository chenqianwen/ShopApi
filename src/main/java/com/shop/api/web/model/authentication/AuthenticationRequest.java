package com.shop.api.web.model.authentication;


import com.shop.api.properties.SecurityConstants;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * created by ygl on 2018/1/26
 */
@Data
public class AuthenticationRequest {

    @NotBlank(message="phone cant be empty")
    private String phone;

    @NotBlank(message="clientId cant be empty")
    private String clientId;
}
