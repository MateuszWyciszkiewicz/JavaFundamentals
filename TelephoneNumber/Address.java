public class Address {
    private String street;
    private String city;
    private String postalCode;
    private TelephoneNumber number;

    public Address(String street, String city, String postalCode, TelephoneNumber number){
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.number = number;
    }

    public void displayAddress(){
        System.out.println("Address:" );
        System.out.println("    street: " + this.street);
        System.out.println("    city: " + this.city);
        System.out.println("    postal code: " + this.postalCode);
        System.out.println("    number: "+this.number.returnNumberString());
    }
}
