public class Company extends TelephoneEntry {
    private String companyName;
    private String address;

    public Company(String companyName, String address, int countryCode, int localNumber){
        this.companyName = companyName;
        this.address = address;
        this.telephoneNumber = new TelephoneNumber(countryCode, localNumber);
    }

    public void description() {
        System.out.println("\tName: " + this.companyName);
        System.out.println("\tAddress: " + this.address);
        System.out.println("\tTelephone: " + this.telephoneNumber.returnNumberString());
    }
}
