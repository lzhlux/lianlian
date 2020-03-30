package com.lianlian.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass  //解决 No identifier specified for entity 问题
@EntityListeners(value = { BaseEntityListener.class }) //  创建时间与更新时间
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})//解決返回json未序列化
public class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7593645332567934815L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

}
