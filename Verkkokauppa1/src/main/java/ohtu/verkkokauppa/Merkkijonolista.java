package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface Merkkijonolista {
    void lisaa(String merkkijono);

    ArrayList<String> getLista();
}
