package com.cts.it.model;
import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetail {
	private String erCode;
	private String erMsg;
	public Object set;
	public String getErCode() {
		return erCode;
	}
	public void setErCode(String erCode) {
		this.erCode = erCode;
	}
	public String getErMsg() {
		return erMsg;
	}
	public void setErMsg(String erMsg) {
		this.erMsg = erMsg;
	}
	
	
}