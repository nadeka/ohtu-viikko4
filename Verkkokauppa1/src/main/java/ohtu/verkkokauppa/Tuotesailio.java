package ohtu.verkkokauppa;

public interface Tuotesailio {
    Tuote haeTuote(int id);

    int saldo(int id);

    void ota(Tuote t);

    void palauta(Tuote t);
}
