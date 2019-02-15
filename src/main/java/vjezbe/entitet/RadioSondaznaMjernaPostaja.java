package vjezbe.entitet;

import vjezbe.entitet.senzori.Senzor;

import java.util.List;

public class RadioSondaznaMjernaPostaja extends MjernaPostaja implements RadioSondazna {

    private int visina;

    public RadioSondaznaMjernaPostaja(int visina) {
        this.visina = visina;
    }

    public RadioSondaznaMjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori, int visina) {
        super(naziv, mjesto, geografskaTocka, senzori);
        this.visina = visina;
    }

    public RadioSondaznaMjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, int visina) {
        super(naziv, mjesto, geografskaTocka);
        this.visina = visina;
    }

    public RadioSondaznaMjernaPostaja(String naziv, Mjesto mjesto, GeografskaTocka geografskaTocka, List<Senzor> senzori) {
        super(naziv, mjesto, geografskaTocka, senzori);
    }

    @Override
    public void podesiVisinuPostaje(int visina) {
        this.visina = visina;
    }

    @Override
    public int dohvatiVisinuPostaje() {
        return visina;
    }
}
