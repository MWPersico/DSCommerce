package tech.mwprojects.DSCommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValidationError extends CustomError{
	
	private List<FieldMessage> fieldErrors = new ArrayList<>();
	
	public ValidationError(Instant timeStamp, Integer status, String error, String path) {
		super(timeStamp, status, error, path);
	}
	
	public List<FieldMessage> getFieldErrors(){
		return fieldErrors;
	}
	
	public void addFieldError(FieldMessage fieldMessage) {
		fieldErrors.add(fieldMessage);
	}
	
	public void addFieldError(String fieldName, String message) {
		Optional<FieldMessage> fieldMessage = fieldErrors.stream().filter(x -> x.getField().equals(fieldName)).findFirst();

		if(fieldMessage.isPresent()){
			fieldMessage.get().addMessage(message);
		}else{
			FieldMessage fieldError = new FieldMessage(fieldName);
			fieldError.addMessage(message);
			addFieldError(fieldError);
		}
	}
}
