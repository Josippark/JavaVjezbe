package vjezbe.entitet.senzori.impl;

import vjezbe.entitet.senzori.Senzor;

import java.math.BigDecimal;

public class SenzorTlaka extends Senzor {

    public SenzorTlaka(BigDecimal vrijednost) {
        super("hP", vrijednost);

    }

    public String dohvatiPodatkeSenzora() {
        return "Vrijednost senzora je: " + getVrijednost() + getMjernaJedinica();
    }
}
