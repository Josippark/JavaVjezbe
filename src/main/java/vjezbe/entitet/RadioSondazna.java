package vjezbe.entitet;

public interface RadioSondazna {

    void podesiVisinuPostaje(int visina);

    int dohvatiVisinuPostaje();

    static int provjeriVisinu(int visina) {
        if (visina > 1000) {
            visina = 1000;
        }
        return visina;
    }

    default int povecajVisinu(int visina) {
        visina = visina + 1;
        return provjeriVisinu(visina);


    }
}
