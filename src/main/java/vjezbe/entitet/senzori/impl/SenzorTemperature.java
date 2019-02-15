package vjezbe.entitet.senzori.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vjezbe.entitet.RadSenzora;
import vjezbe.entitet.senzori.Senzor;
import vjezbe.iznimke.NiskaTemperaturaException;
import vjezbe.iznimke.VisokaTemperaturaException;

import java.math.BigDecimal;
import java.util.Random;

public class SenzorTemperature extends Senzor {

    private String naziv;
    public static final int MAX_NUM = 50;
    public static final int RANGE = 100;
    public static int generateNumber = 0;
    private static final Logger logger = LoggerFactory.getLogger(SenzorTemperature.class);

    public SenzorTemperature(BigDecimal vrijednost, String naziv, RadSenzora radSenzora) {
        super("C", vrijednost, radSenzora);
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String dohvatiPodatkeSenzora() {
        return "Naziv senzora je : " + getNaziv() + ", vrijednost senzora je: " + getVrijednost() + getMjernaJedinica() + ", rad senzora je: " + getRadSenzora();
    }

    public static void generirajVrijednost() throws VisokaTemperaturaException, NiskaTemperaturaException {

        Random random = new Random();
        generateNumber = random.nextInt(MAX_NUM + 1 + 50) - 50;
        logger.info("Generiran je broj" + generateNumber);

        if (generateNumber < (-10)) {
            throw new NiskaTemperaturaException();
        } else if (generateNumber > 40) {
            throw new VisokaTemperaturaException();
        }
    }
}
