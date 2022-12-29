public class BadFieldNameException extends Exception{
    String fieldName;
    BadFieldNameException(String message, String fieldName){
        super(message);
        this.fieldName = fieldName;
    }
}
