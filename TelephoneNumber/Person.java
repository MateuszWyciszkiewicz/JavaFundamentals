public class Person extends TelephoneEntry {
    public String name;
    public String lastName;
    public Address address;

    public Person(String name, String lastName, Address address){
        this.name = name;
        this.lastName = lastName;
        this.address = address;
    }

    public void description() {
        System.out.println("Name: " + this.name);
        System.out.println("Last name: " + lastName);
        this.address.displayAddress();
    }
}
