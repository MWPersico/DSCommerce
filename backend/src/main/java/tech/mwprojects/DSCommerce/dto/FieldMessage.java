package tech.mwprojects.DSCommerce.dto;


import java.util.ArrayList;
import java.util.List;

public class FieldMessage {
	private String field;
	private List<String> messages = new ArrayList<>();
	
	public FieldMessage(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addMessage(String message){messages.add(message);}
}
