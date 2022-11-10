public class Person extends TelephoneEntry {
    private String name;
    private String lastName;
    private Address address;

    public Person(String name, String lastName, String city, String street, String zipCode, String country, int countryCode, int localNumber){
        this.name = name;
        this.lastName = lastName;
        this.address = new Address(city, street, zipCode, country);
        this.telephoneNumber = new TelephoneNumber(countryCode, localNumber);
    }

    public void description() {
        System.out.println("\tName: " + this.name);
        System.out.println("\tLast name: " + this.lastName);
        System.out.println("\tTelephone:" + this.telephoneNumber.returnNumberString());
        this.address.printAddress();
    }
}
