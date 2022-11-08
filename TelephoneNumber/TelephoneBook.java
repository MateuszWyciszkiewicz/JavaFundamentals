import java.util.TreeMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;


public class TelephoneBook {
    private TreeMap<TelephoneNumber, TelephoneEntry> treemap = new TreeMap<TelephoneNumber, TelephoneEntry>(); 

    public void addPerson(String name, String surname, String address, int countryCode, int localNumber){
        TelephoneNumber tel = new TelephoneNumber(countryCode, localNumber);
        Person per = new Person(surname, name, address, countryCode, localNumber);
        treemap.put(tel, per);
    }

    public void addCompany(String companyName, String address, int countrycode, int localNumber){
        TelephoneNumber tel = new TelephoneNumber(countrycode, localNumber);
        Company com = new Company(companyName, address, countrycode, localNumber);
        treemap.put(tel, com);
    }

    public void printContent(){
        Set <Map.Entry<TelephoneNumber, TelephoneEntry>>set = this.treemap.entrySet();
        Iterator <Map.Entry<TelephoneNumber, TelephoneEntry>>iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<TelephoneNumber, TelephoneEntry> entry = iterator.next();
            TelephoneNumber telephoneNumber = (TelephoneNumber)entry.getKey();
            System.out.print(telephoneNumber.returnNumberString() + ":\n");
            TelephoneEntry telephoneEntry = (TelephoneEntry)entry.getValue();
            telephoneEntry.description();
        }

    }
}
