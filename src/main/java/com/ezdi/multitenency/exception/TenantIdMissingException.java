package com.ezdi.multitenency.exception;

public class TenantIdMissingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TenantIdMissingException() {
	
	}

	public TenantIdMissingException(String msg) {
		super(msg);
	}

	
}
