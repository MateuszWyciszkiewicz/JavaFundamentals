public class BadNumberOfArgumentsException extends Exception {
    int wantLength;
    int receivedLength;
    BadNumberOfArgumentsException(String message, int want, int received){
        super(message);
        this.wantLength = want;
        this.receivedLength = received;
    }
}
