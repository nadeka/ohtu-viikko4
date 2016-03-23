package ohtu.verkkokauppa;

import java.util.*;

public class Varasto implements Tuotesailio {
    
    private Merkkijonolista merkkijonolista;
    private HashMap<Tuote, Integer> saldot;  
    
    public Varasto(Merkkijonolista merkkijonolista) {
        this.merkkijonolista = merkkijonolista;
        saldot = new HashMap<Tuote, Integer>();
        alustaTuotteet();
    }
            
    @Override
    public Tuote haeTuote(int id){
        for (Tuote t : saldot.keySet()) {
            if ( t.getId()==id) return t;
        }
        
        return null;
    }

    @Override
    public int saldo(int id){
        return saldot.get(haeTuote(id));
    }
    
    @Override
    public void ota(Tuote t){
        saldot.put(t,  saldo(t.getId())-1 );
        merkkijonolista.lisaa("otettiin varastosta "+t);
    }
    
    @Override
    public void palauta(Tuote t){
        saldot.put(t,  saldo(t.getId())+1 );
        merkkijonolista.lisaa("palautettiin varastoon "+t);
    }    
    
    private void alustaTuotteet() {
        saldot.put(new Tuote(1, "Koff Portteri", 3), 100);
        saldot.put(new Tuote(2, "Fink Bräu I", 1), 25);
        saldot.put(new Tuote(3, "Sierra Nevada Pale Ale", 5), 30);
        saldot.put(new Tuote(4, "Mikkeller not just another Wit", 7), 40);
        saldot.put(new Tuote(5, "Weihenstephaner Hefeweisse", 4), 15);
    }
}
