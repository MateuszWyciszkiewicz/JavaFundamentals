public class TelephoneNumber implements Comparable<TelephoneNumber>{
    private int countryCode;
    private int localNumber;
    
    public TelephoneNumber(int countryCode, int localNumber){
        this.countryCode = countryCode;
        this.localNumber = localNumber;
    }
    public int compareTo(TelephoneNumber num) {
        return (this.returnNumberString()).compareTo(num.returnNumberString());
    }
    
    public String returnNumberString(){
        String numberString = "+"+countryCode+" "+localNumber;
        return numberString;
    }
}