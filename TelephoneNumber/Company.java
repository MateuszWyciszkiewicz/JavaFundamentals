public class Company extends TelephoneEntry {
    public String companyName;
    public Address address;

    public Company(String companyName, Address address){
        this.companyName = companyName;
        this.address = address;
    }

    public void description() {
        System.out.println("Name: " + this.companyName);
        this.address.displayAddress();
    }
}
