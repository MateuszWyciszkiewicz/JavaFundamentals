package Exceptions;
public class BadNumberOfArgumentsException extends Exception {
    public int wantLength;
    public int receivedLength;
    public BadNumberOfArgumentsException(String message, int want, int received){
        super(message);
        this.wantLength = want;
        this.receivedLength = received;
    }
}
