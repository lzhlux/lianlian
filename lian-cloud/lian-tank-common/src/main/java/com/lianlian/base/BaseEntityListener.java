package com.lianlian.base;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 创建时间与更新时间自动
 * @author thc
 * 2019年8月8日
 */
public class BaseEntityListener {
	
	@PrePersist
	public void prePersist(Object object) {

		if (object instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) object;
			Calendar calNow = Calendar.getInstance();
			Date dtNow = calNow.getTime();
			/*baseEntity.setCreateTime(dtNow);
			baseEntity.setUpdateTime(dtNow);*/
		}
	}
	
	@PreUpdate
	public void preUpdate(Object object) {

		if (object instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) object;
			Calendar calNow = Calendar.getInstance();
			Date dtNow = calNow.getTime();
			//baseEntity.setUpdateTime(dtNow);
		
		}
	}

}
