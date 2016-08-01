package com.ezdi.multitenency.exception;

public class TenantIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	
	public TenantIdNotFoundException(String msg) {
		super(msg);
	}
	
	public TenantIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

}
