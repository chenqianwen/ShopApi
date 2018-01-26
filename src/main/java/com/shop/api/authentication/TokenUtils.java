package com.shop.api.authentication;

import com.shop.api.properties.SecurityConstants;
import com.shop.api.properties.SecurityProperties;
import com.shop.api.web.exception.ErrorException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by ygl on 2018/1/26
 */
@Component
public class TokenUtils {

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 请求中，接受电话的参数名称
     */
    @Setter
    @Getter
    private String phoneParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_PHONE;
    /**
     * 请求中，接受clientId的参数名称
     */
    @Setter
    @Getter
    private String clientIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_CLIENT;

    /**
     * 验证clientId
     *
     */
    public Boolean clientIsExist(String clientId) {
        if (StringUtils.isNotBlank(clientId) && securityProperties.getClientId().equals(clientId)) {
            return true;
        }
        return false;
    }

    /**
     * 返回秒
     * @return
     */
    public int getTokenExpiresIn() {
        return securityProperties.getTokenExpiresIn();
    }
    /**
     *  生成token过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() +   getTokenExpiresIn()* 1000);
    }

    /**
     * 生成Token
     * @param phone
     * @param clientId
     * @return
     */
    public String generateToken(String phone,String clientId) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(phoneParameter, phone);
        claims.put(clientIdParameter, clientId);
        return generateToken(claims);
    }
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
                  .signWith(SignatureAlgorithm.HS512, securityProperties.getTokenSecret()).compact();
    }

    /**
     * 获取电话
     * @param token
     * @return
     */
    public String getPhoneFromToken(String token) {
        String phone;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            phone = claims.get(phoneParameter).toString();
        } catch (ExpiredJwtException e) {
            throw new ErrorException("token已过期");
        } catch (Exception e) {
            phone = null;
        }
        return phone;
    }
    /**
     * 获取clientId
     * @param token
     * @return
     */
    public String getClientIdFromToken(String token) {
        String clientId;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            clientId = claims.get(clientIdParameter).toString();
        } catch (ExpiredJwtException e) {
            throw new ErrorException("token已过期");
        } catch (Exception e) {
            clientId = null;
        }
        return clientId;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(securityProperties.getTokenSecret()).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null,null,null);
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 验证token
     * @param accessToken
     * @param userDetails
     * @return
     */
    public boolean validateToken(String accessToken, UserDetails userDetails) {
        if (userDetails == null) {
            return false;
        }
        String username = userDetails.getUsername();
        String phone = getPhoneFromToken(accessToken);
        // 验证phone相同
        if (StringUtils.isBlank(username) || StringUtils.isBlank(phone) || !phone.equals(username)) {
            return false;
        }
        // 验证是否过期
        return !isExpired(accessToken);
    }

    /**
     * 验证是否过期
     * @param token
     * @return
     */
    private Boolean isExpired(String token) {
        final Date expireDate = this.getExpireDateFromToken(token);
        return expireDate.before(new Date(System.currentTimeMillis()));
    }
    public Date getExpireDateFromToken(String token) {
        Date expireDate;
        try {
            Claims claims = this.getClaimsFromToken(token);
            expireDate = claims.getExpiration();
        } catch (ExpiredJwtException e) {
            throw new ErrorException("token已过期");
        } catch (Exception e) {
            expireDate = null;
        }
        return expireDate;
    }
}
