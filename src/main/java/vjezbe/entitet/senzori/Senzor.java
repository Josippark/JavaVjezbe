package vjezbe.entitet.senzori;

import vjezbe.entitet.RadSenzora;

import java.math.BigDecimal;

public abstract class Senzor {

    private String mjernaJedinica;
    private BigDecimal vrijednost;
    private RadSenzora radSenzora;

    public RadSenzora getRadSenzora() {
        return radSenzora;
    }

    public void setRadSenzora(RadSenzora radSenzora) {
        this.radSenzora = radSenzora;
    }

    public Senzor(String mjernaJedinica, BigDecimal vrijednost, RadSenzora radSenzora) {
        this.mjernaJedinica = mjernaJedinica;
        this.vrijednost = vrijednost;
        this.radSenzora = radSenzora;
    }

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
