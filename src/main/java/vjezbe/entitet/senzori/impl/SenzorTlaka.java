package vjezbe.entitet.senzori.impl;

import vjezbe.entitet.RadSenzora;
import vjezbe.entitet.senzori.Senzor;

import java.math.BigDecimal;

public class SenzorTlaka extends Senzor {

    public SenzorTlaka(BigDecimal vrijednost, RadSenzora radSenzora) {
        super("hP", vrijednost, radSenzora);

    }

    public String dohvatiPodatkeSenzora() {
        return "Vrijednost senzora je: " + getVrijednost() + getMjernaJedinica()+ ", rad senzora je: " + getRadSenzora();
    }
}
