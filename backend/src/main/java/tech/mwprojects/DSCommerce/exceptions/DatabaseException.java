package tech.mwprojects.DSCommerce.exceptions;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = 1l;
	
	public DatabaseException(String message) {
		super(message);
	}
}
