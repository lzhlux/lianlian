package com.lianlian.base;

import lombok.Data;

@Data
public class BaseDto {

	private Integer pageNo;
	private Integer pageSize;
    
	public Integer getPageNo() {
		if(pageNo==null||pageNo==0){
			this.pageNo=1;
		}
			
		return pageNo;
	}
	public Integer getPageSize() {
		if(pageSize==null||pageSize==0){
			this.pageSize=10;
		}
		return pageSize;
	}
	
}
