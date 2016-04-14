package ohtu.verkkokauppa;

public interface Maksupalvelu {
    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
