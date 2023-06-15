package com.cts.it.exception;

public class IdNotFoundException extends RuntimeException {
	public IdNotFoundException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}
