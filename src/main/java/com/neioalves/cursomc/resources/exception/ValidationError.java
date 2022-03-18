package com.neioalves.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List <FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);		
	}
	// nome alterado para getErrors pois no Java o nome que está no get que é convertido para o json.
	public List <FieldMessage> getErros() {
		return erros;
	}

	//o setList foi substituído pór um método que permite adicionar um erro por vez
	public void addError(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName, message));
	}
	

}
