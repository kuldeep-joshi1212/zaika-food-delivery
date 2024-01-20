package com.kuldeep.zaika;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("zaika")
public record ZaikaConfigProperties(String key,Long expiry,String issuer,String db_username,String db_password,String db_url ){
}