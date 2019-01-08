package vjezbe.entitet.senzori.impl;

import vjezbe.entitet.senzori.Senzor;

import java.math.BigDecimal;

public class SenzorVlage extends Senzor {

    public SenzorVlage(BigDecimal vrijednost) {
        super("m3", vrijednost);

    }

    public String dohvatiPodatkeSenzora() {
        return "Vrijednost senzora je: " + getVrijednost() + getMjernaJedinica();
    }
}
