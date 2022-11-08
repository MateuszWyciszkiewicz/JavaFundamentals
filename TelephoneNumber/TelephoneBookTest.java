public class TelephoneBookTest {
    public static void main(String[] Args){
        TelephoneBook telB = new TelephoneBook();
        telB.addPerson("Mateusz", "Wyciszkiewicz", "Lakowa 4, Lodz", 48,534785123);
        telB.addPerson("Adam", "Nowak", "Gdanska 8, Warszawa", 45,528403127);
        telB.addPerson("Tomasz", "Kowalski", "Wyjazdowa 3, Gdansk", 23,278364758);
        telB.addCompany("Allegro", "Wolska 4, Poznan", 18, 284657382);
        telB.addCompany("Amazon", "Parkowa 456/8, Krakow", 34, 264758937);
        telB.addCompany("Qbik", "Sloneczna 4, Wrocalaw", 27, 18567584);
        telB.printContent();

    }
}
