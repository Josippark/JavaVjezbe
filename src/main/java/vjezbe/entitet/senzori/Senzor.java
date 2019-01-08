package vjezbe.entitet.senzori;

import java.math.BigDecimal;

public abstract class Senzor {

    private String mjernaJedinica;
    private BigDecimal vrijednost;

    public Senzor(String mjernaJedinica, BigDecimal vrijednost) {
        this.mjernaJedinica = mjernaJedinica;
        this.vrijednost = vrijednost;
    }

    public String getMjernaJedinica() {
        return mjernaJedinica;
    }

    public void setMjernaJedinica(String mjernaJedinica) {
        this.mjernaJedinica = mjernaJedinica;
    }

    public BigDecimal getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(BigDecimal vrijednost) {
        this.vrijednost = vrijednost;
    }

    public abstract String dohvatiPodatkeSenzora();
}
