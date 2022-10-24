public class TelephoneNumber implements Comparable<TelephoneNumber>{
    private int countryCode;
    private int localNumber;
    
    public int compareTo(TelephoneNumber num) {
        if(this.createNumber() > num.createNumber()){
            return 1;
        }if(this.createNumber() < num.createNumber()){
            return -1;
        }
        return 0;
    }

    private int createNumber(){
        String cod = Integer.toString(countryCode);
       String num = Integer.toString(localNumber);
        String teleNum = cod + num;
        return Integer.valueOf(teleNum);
    }

    public String returnNumberString(){
        String numberString = "+"+countryCode+" "+localNumber;
        return numberString;
    }
}