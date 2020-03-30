package com.lianlian.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserEntity {
    private Long id;

    private String avatar;

    private String nickName;

    private Integer gender;

    private String province;

    private String city;

    private String unionid;

    private String mallOpenid;

    private String yuerOpenid;

    private String serviceNumberOpenid;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private Integer quantity;

    private Integer isleaguer;

    private String source;

    private Long recommenderid;

    private String openid;

    private BigDecimal balance;

    private Integer drawnum;

    private Date lastdrawtime;

    private Integer todaydrawnum;

    private Integer fansnum;

    private Date lastsharetime;

    private String wechatid;

    private Integer level;

    private Boolean disablestatus;

    private BigDecimal withdrawal;

    private BigDecimal ordermoney;

    private Long customerserverid;

    private String institutionsid;

    private String realname;

    private String auditstatus;

    private Date applytime;

    private String partner;

    private Date lasttime;

    private Byte newman;

}