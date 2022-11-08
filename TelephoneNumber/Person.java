public class Person extends TelephoneEntry {
    private String name;
    private String lastName;
    private String address;

    public Person(String name, String lastName, String address){
        this.name = name;
        this.lastName = lastName;
        this.address = address;
    }

    public void description() {
        System.out.println("Name: " + this.name);
        System.out.println("Last name: " + this.lastName);
        System.out.println("Address: " + this.address);
        
    }
}
