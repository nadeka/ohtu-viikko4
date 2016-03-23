
package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Kirjanpito implements Merkkijonolista {
    
    private ArrayList<String> tapahtumat;

    public Kirjanpito() {
        tapahtumat = new ArrayList<String>();
    }
    
    @Override
    public void lisaa(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getLista() {
        return tapahtumat;
    }
}
