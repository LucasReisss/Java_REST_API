package com.example.demo.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date timestampDate;
	private String messageString;
	private String details;
	
	public ExceptionResponse(Date timestampDate, String messageString, String details) {
		this.timestampDate = timestampDate;
		this.messageString = messageString;
		this.details = details;
	}

	public Date getTimestampDate() {
		return timestampDate;
	}

	public String getMessageString() {
		return messageString;
	}

	public String getDetails() {
		return details;
	}
	
	
	
	
}
