package vjezbe.entitet;

import vjezbe.entitet.senzori.Senzor;
import vjezbe.util.CustomComparator;

import java.util.*;

public class MjernaPostaja {

    private String naziv;
    private Mjesto mjesto;
    private GeografskaTocka geografskaTocka;
    private List<Senzor> senzori = new ArrayList<>();

    public MjernaPostaja() {
    }

    public MjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
        this.naziv = naziv;
        this.mjesto = mjesto;
        this.geografskaTocka = geografskaTocka;
        this.senzori = senzori;
    }

    public MjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka) {
        this.naziv = naziv;
        this.mjesto = mjesto;
        this.geografskaTocka = geografskaTocka;
    }

    public String getNaziv() {
        return naziv;
    }

    public List<Senzor> getSenzori() {
        return senzori;
    }

    public void setSenzori(List<Senzor> senzori) {
        this.senzori = senzori;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Mjesto getMjesto() {
        return mjesto;
    }

    public void setMjesto(Mjesto mjesto) {
        this.mjesto = mjesto;
    }

    public GeografskaTocka getGeografskaTocka() {
        return geografskaTocka;
    }

    public void setGeografskaTocka(GeografskaTocka geografskaTocka) {
        this.geografskaTocka = geografskaTocka;
    }

    public List<Senzor> dohvatiSenzore() {
        Collections.sort(senzori, new CustomComparator());
        return senzori;
    }
}
