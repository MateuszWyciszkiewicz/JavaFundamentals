import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;


public class TelephoneBook {
    private TreeMap<TelephoneNumber, TelephoneEntry> treemap = new TreeMap<TelephoneNumber, TelephoneEntry>(); 

    public void addPerson(String name, String surname, String address, int countryCode, int localNumber){
        TelephoneNumber tel = new TelephoneNumber(countryCode, localNumber);
        Person per = new Person(surname, name, address);
        treemap.put(tel, per);
    }

    public void addCompany(String companyName, String address, int countrycode, int localNumber){
        TelephoneNumber tel = new TelephoneNumber(countrycode, localNumber);
        Company com = new Company(companyName, address);
        treemap.put(tel, com);
    }

    public void printContent(){
        Set set = this.treemap.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry)i.next();
            TelephoneNumber telephoneNumber = (TelephoneNumber)entry.getKey();
            System.out.print(telephoneNumber.returnNumberString());
            TelephoneEntry telephoneEntry = (TelephoneEntry)entry.getValue();
            telephoneEntry.description();
        }

    }
}
