package Exceptions;
public class BadSyntaxException extends Exception{
    private String received;
    private String want;

    public BadSyntaxException(String message, String want,  String received){
        super(message);
        this.want = want;
        this.received = received;
    }

    public String getWant(){
        return this.want;
    }

    public String getReceived(){
        return this.received;
    }
}
