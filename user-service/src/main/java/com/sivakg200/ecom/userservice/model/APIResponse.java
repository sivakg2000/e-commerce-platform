package com.sivakg200.ecom.userservice.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class APIResponse<T> {
	private boolean success;
	private String message;
	private T data;
	private List<String> errors;
	private int errorCode;
	private long timestamp;
	private String path;

}
