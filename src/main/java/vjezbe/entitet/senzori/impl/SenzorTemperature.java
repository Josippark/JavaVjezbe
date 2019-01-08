package vjezbe.entitet.senzori.impl;

import vjezbe.entitet.senzori.Senzor;

import java.math.BigDecimal;

public class SenzorTemperature extends Senzor {

    private String naziv;

    public SenzorTemperature(BigDecimal vrijednost, String naziv) {
        super("C", vrijednost);
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String dohvatiPodatkeSenzora() {
        return "Naziv senzora je : " + getNaziv() + ", vrijednost senzora je: " + getVrijednost() + getMjernaJedinica();
    }
}
