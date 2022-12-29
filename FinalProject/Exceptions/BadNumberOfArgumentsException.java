package Exceptions;
public class BadNumberOfArgumentsException extends Exception {
    private int wantLength;
    private int receivedLength;
    public BadNumberOfArgumentsException(String message, int want, int received){
        super(message);
        this.wantLength = want;
        this.receivedLength = received;
    }

    public int getWant(){
        return this.wantLength;
    }

    public int getReceived(){
        return this.receivedLength;
    }
}
