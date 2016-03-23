package ohtu.verkkokauppa;

public class Kauppa {

    private Tuotesailio tuotesailio;
    private Maksupalvelu maksupalvelu;
    private Ostoskori ostoskori;
    private Lukugeneraattori lukugeneraattori;
    private String kaupanTili;

    public Kauppa(Maksupalvelu maksupalvelu, Lukugeneraattori lukugeneraattori, Tuotesailio tuotesailio) {
        this.tuotesailio = tuotesailio;
        this.maksupalvelu = maksupalvelu;
        this.lukugeneraattori = lukugeneraattori;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = tuotesailio.haeTuote(id);
        tuotesailio.palauta(t);
    }

    public void lisaaKoriin(int id) {
        if (tuotesailio.saldo(id)>0) {
            Tuote t = tuotesailio.haeTuote(id);
            ostoskori.lisaa(t);
            tuotesailio.ota(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = lukugeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return maksupalvelu.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
