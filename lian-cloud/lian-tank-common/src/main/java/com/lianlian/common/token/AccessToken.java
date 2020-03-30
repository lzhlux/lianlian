package com.lianlian.common.token;

import lombok.Data;

@Data
public class AccessToken {
	private String access_token;
	private String token_type;
	private String userId;
	private String avatarUrl;
	private String nickName;
	private int isLeaguer;
	private int level;
	private long expires_in;


}
