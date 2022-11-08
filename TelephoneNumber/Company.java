public class Company extends TelephoneEntry {
    private String companyName;
    private String address;

    public Company(String companyName, String address){
        this.companyName = companyName;
        this.address = address;
    }

    public void description() {
        System.out.println("Name: " + this.companyName);
        System.out.println("Address: " + this.address);
    }
}
