public class TelephoneTreeMapTest {
    public static void main(String[] Args){
        TelephoneTreeMap telT = new TelephoneTreeMap();
        telT.addPerson("Mateusz", "Wyciszkiewicz", "Lakowa 4, Lodz", 48,534785123);
        telT.addPerson("Adam", "Nowak", "Gdanska 8, Warszawa", 45,528403127);
        telT.addPerson("Tomasz", "Kowalski", "Wyjazdowa 3, Gdansk", 23,278364758);
        telT.addCompany("Allegro", "Wolska 4, Poznan", 18, 284657382);
        telT.addCompany("Amazon", "Parkowa 456/8, Krakow", 34, 264758937);
        telT.addCompany("Qbik", "Sloneczna 4, Wrocalaw", 27, 18567584);
        telT.printContent();

    }
}
