package com.luna.domain;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Response {

	private boolean isSuccess;
	private Object dataObject;
	private HttpStatus httpStatus;
	public Response(Object data, boolean isSuccess, HttpStatus httpStatus) {
		this.dataObject = data;
		this.isSuccess = isSuccess;
		this.httpStatus = httpStatus;
	}
}
