package com.shop.api.authentication;

import com.shop.api.properties.SecurityConstants;
import com.shop.api.properties.SecurityProperties;
import com.shop.api.web.exception.ErrorException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by ygl on 2018/1/26
 */
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 请求头中，存放token的参数名称
     */
    private String tokenParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_TOKEN;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String accessToken = httpRequest.getHeader(tokenParameter);
        String requestURI = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        httpResponse.setHeader("Access-Control-Allow-Methods","*");//cors相关配置的缓存
        httpResponse.setHeader("Access-Control-Max-Age","3600");//cors相关配置的缓存
        httpResponse.setHeader("Access-Control-Allow-Origin","*");//cors相关配置的缓存
        httpResponse.setHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type,Accept,"+SecurityConstants.DEFAULT_PARAMETER_NAME_TOKEN);
        if(method.equals(HttpMethod.OPTIONS) || StringUtils.startsWithIgnoreCase(requestURI,SecurityConstants.DEFAULT_AUTHENTICATION_URL_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        if (StringUtils.isBlank(accessToken)) {
            throw new ErrorException("header中取不到token");
        }
        String clientId = tokenUtils.getClientIdFromToken(accessToken);
        String phone = tokenUtils.getPhoneFromToken(accessToken);

        if (!tokenUtils.clientIsExist(clientId)) {
            throw new ErrorException("clientId错误:"+clientId);
        }
        if (StringUtils.isBlank(phone)) {
            throw new ErrorException("phone为空");
        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(phone);
            if (userDetails == null) {
                throw new ErrorException(phone+",对应的USER不存在");
            }
            if (!tokenUtils.validateToken(accessToken, userDetails)) {
                throw new ErrorException("token验证不通过");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
        return;
    }
}