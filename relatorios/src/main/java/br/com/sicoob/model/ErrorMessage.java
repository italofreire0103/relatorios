package br.com.sicoob.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorMessage {

	private Date currentDate;
	private String message;
	private String cod;
	
	public ErrorMessage() {}
	
	public ErrorMessage(Date currentDate, String message, String cod) {
		this.currentDate = currentDate;
		this.message = message;
		this.cod = cod;
	}
}
