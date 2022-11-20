public class Address {
    private String city;
    private String street;
    private String zipCode;
    private String country;

    public Address(String city, String street, String zipCode, String country){
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.country = country;
    }

    public void printAddress(){
        String addreString =  this.city + ", " + this.street + " " + this.zipCode + ", " + this.country;
        System.out.println("\tAddress: " + addreString);
    }
}
