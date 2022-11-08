public class Person extends TelephoneEntry {
    private String name;
    private String lastName;
    private String address;

    public Person(String name, String lastName, String address, int countryCode, int localNumber){
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = new TelephoneNumber(countryCode, localNumber);
    }

    public void description() {
        System.out.println("Name: " + this.name);
        System.out.println("Last name: " + this.lastName);
        System.out.println("Address: " + this.address);
        System.out.println("Telephone:" + this.telephoneNumber.returnNumberString());
    }
}
