package com.sivakg200.ecom.userservice.util;

import java.util.Arrays;
import java.util.List;

import com.sivakg200.ecom.userservice.model.APIResponse;

public class ResponseUtil {
	
	public static <T> APIResponse<T> success(T data,String message,String path){
		APIResponse<T> resp=new APIResponse<T>();
		resp.setSuccess(true);
		resp.setMessage(message);
		resp.setData(data);
		resp.setErrorCode(0);
		resp.setErrorCode(0);
		resp.setTimestamp(System.currentTimeMillis());
		resp.setPath(path);
		return resp;
	}
	
	public static <T> APIResponse<T> error(List<String> errors,String message,int errorCode,String path){
		APIResponse<T> resp=new APIResponse<T>();
		resp.setSuccess(false);
		resp.setMessage(message);
		resp.setData(null);
		resp.setErrors(errors);
		resp.setErrorCode(errorCode);
		resp.setTimestamp(System.currentTimeMillis());
		resp.setPath(path);
		return resp;
	}
	public static<T> APIResponse<T> error(String error,String message,int errorCode,String path){
		return error(Arrays.asList(error),message,errorCode,path);
	}

}
