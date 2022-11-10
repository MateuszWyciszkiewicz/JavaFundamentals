public class TelephoneTreeMapTest {
    public static void main(String[] Args){
        TelephoneTreeMap telT = new TelephoneTreeMap();
        telT.addPerson("Mateusz", "Wyciszkiewicz", "Lodz", "lakowa 4", "95-245", "Polska", 48,534785123);
        telT.addPerson("Adam", "Nowak", "Gdansk", "Sloneczna 8", "93-756", "Polska", 45,528403127);
        telT.addPerson("Tomasz", "Kowalski", "Wroclaw", "Gdanska 9", "91-758", "Polska", 23,278364758);
        telT.addCompany("Allegro", "Poznan", "Wolska 4", "93-758", "Polska", 18, 284657382);
        telT.addCompany("Amazon", "Krakow", "Poprzeczna 25/6", "12-758", "Polska", 34, 264758937);
        telT.addCompany("Qbik", "Katowice", "Spizowa 3", "26-756", "Polska", 27, 18567584);
        telT.printContent();

    }
}
