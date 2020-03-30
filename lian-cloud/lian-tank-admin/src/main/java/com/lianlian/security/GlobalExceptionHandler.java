package com.lianlian.security;

import com.lianlian.common.result.ResultCode;
import com.lianlian.common.result.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultData defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			return ResultData.error(ResultCode.NOT_FOUND);
        }  else if (e instanceof  org.springframework.web.HttpRequestMethodNotSupportedException) {
        	return ResultData.error(ResultCode.NOT_SUPPORTED);
        } else {
        	return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
        }
	}
}
