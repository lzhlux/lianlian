package com.lianlian.common.token;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(value="classpath:config/token.properties")
@ConfigurationProperties
public class TokenConfig {
	private String header;
	private String secret;
	private long expiration;
	private String tokenHead;
	private String version;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
	public String getTokenHead() {
		return tokenHead;
	}
	public void setTokenHead(String tokenHead) {
		this.tokenHead = tokenHead;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
