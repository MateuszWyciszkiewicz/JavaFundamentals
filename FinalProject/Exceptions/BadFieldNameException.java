package Exceptions;
public class BadFieldNameException extends Exception{
    private String fieldName;
    public BadFieldNameException(String message, String fieldName){
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldname(){
        return this.fieldName;
    }
}
