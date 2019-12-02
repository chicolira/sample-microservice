package com.chicolira.clientservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class ResourceNotFoundRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -8539306567712395883L;

	public ResourceNotFoundRuntimeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceNotFoundRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundRuntimeException(String message) {
		super(message);
	}

	public ResourceNotFoundRuntimeException(Throwable cause) {
		super(cause);
	}
	
}
