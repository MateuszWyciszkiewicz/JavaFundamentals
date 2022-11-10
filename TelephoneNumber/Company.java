public class Company extends TelephoneEntry {
    private String companyName;
    private Address address;

    public Company(String companyName, String city, String street, String zipCode, String country, int countryCode, int localNumber){
        this.companyName = companyName;
        this.address = new Address(city, street, zipCode, country);
        this.telephoneNumber = new TelephoneNumber(countryCode, localNumber);
    }

    public void description() {
        System.out.println("\tName: " + this.companyName);
        System.out.println("\tTelephone: " + this.telephoneNumber.returnNumberString());
        this.address.printAddress();
    }
}
