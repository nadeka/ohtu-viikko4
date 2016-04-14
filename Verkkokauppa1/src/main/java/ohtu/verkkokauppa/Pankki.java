package ohtu.verkkokauppa;

public class Pankki implements Maksupalvelu {

    private Merkkijonolista merkkijonolista;

    public Pankki(Merkkijonolista merkkijonolista) {
        this.merkkijonolista = merkkijonolista;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        merkkijonolista.lisaa("tilisiirto: tililtä " + tililta + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
