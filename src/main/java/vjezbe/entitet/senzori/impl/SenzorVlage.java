package vjezbe.entitet.senzori.impl;

import vjezbe.entitet.RadSenzora;
import vjezbe.entitet.senzori.Senzor;

import java.math.BigDecimal;

public class SenzorVlage extends Senzor {

    public SenzorVlage(BigDecimal vrijednost, RadSenzora radSenzora) {
        super("m3", vrijednost,radSenzora);

    }

    public String dohvatiPodatkeSenzora() {
        return "Vrijednost senzora je: " + getVrijednost() + getMjernaJedinica()+ ", rad senzora je: " + getRadSenzora();
    }
}
