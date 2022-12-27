public class BadSyntaxException extends Exception{
    public String received;
    public String want;

    BadSyntaxException(String message, String want,  String received){
        super(message);
        this.want = want;
        this.received = received;
    }
}
