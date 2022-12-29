package Exceptions;
public class BadFieldNameException extends Exception{
    public String fieldName;
    public BadFieldNameException(String message, String fieldName){
        super(message);
        this.fieldName = fieldName;
    }
}
