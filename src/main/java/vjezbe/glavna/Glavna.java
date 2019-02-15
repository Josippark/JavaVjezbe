package vjezbe.glavna;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vjezbe.entitet.*;
import vjezbe.entitet.senzori.Senzor;
import vjezbe.entitet.senzori.impl.SenzorTemperature;
import vjezbe.entitet.senzori.impl.SenzorTlaka;
import vjezbe.entitet.senzori.impl.SenzorVlage;
import vjezbe.iznimke.NiskaTemperaturaException;
import vjezbe.iznimke.VisokaTemperaturaException;
import vjezbe.sortiranje.ZupanijaSorter;
import vjezbe.util.Validator;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Glavna {

    private static final int BROJ_MJERNIH_POSTAJA = 2;
    private static final int BROJ_SONDAZNIH_MJERNIH_POSTAJA = 1;


    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);
    private static List<MjernaPostaja> listaMjernihPostaja;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Integer brojPostaja = BROJ_MJERNIH_POSTAJA + BROJ_SONDAZNIH_MJERNIH_POSTAJA;

        listaMjernihPostaja = new ArrayList<>();


        for (int i = 0; i < brojPostaja; i++) {
            System.out.println("Unesite " + (i + 1) + ". mjernu postaju:");

            if (i < BROJ_MJERNIH_POSTAJA) {
                listaMjernihPostaja.add(kreirajMjernuPostaju(scanner));
            } else {
                listaMjernihPostaja.add(kreirajRadioSondaznuMjernuPostaju(scanner));
            }
        }
        for (MjernaPostaja mjernaPostaja : listaMjernihPostaja) {
            System.out.println("\n--------------------");
            System.out.println("Naziv mjerne postaje: " + mjernaPostaja.getNaziv());

            System.out.println("Postaja se nalazi u mjestu " + mjernaPostaja.getMjesto().getNaziv() + ", županiji "
                    + mjernaPostaja.getMjesto().getZupanija().getNaziv() + ", državi "
                    + mjernaPostaja.getMjesto().getZupanija().getDrzava().getNaziv());

            System.out.println("Točne koordinate postaje su x:" + mjernaPostaja.getGeografskaTocka().getX() + " y:"
                    + mjernaPostaja.getGeografskaTocka().getY());

            List<Senzor> sortiraniSenzori = mjernaPostaja.dohvatiSenzore();

            for (Senzor senzor : sortiraniSenzori) {
                System.out.println(senzor.dohvatiPodatkeSenzora());
            }

        }


        // Ispis sortiranih zupanija bez duplikata
        System.out.println("Ispis sortiranih županija:");

        List<Zupanija> listaSvihZupanijaIzDrzava = new ArrayList<>();
        for (MjernaPostaja mjernaPostaja : listaMjernihPostaja) {
            listaSvihZupanijaIzDrzava.addAll(mjernaPostaja.getMjesto().getZupanija().getDrzava().getZupanije());
        }

        Stream<Zupanija> distinctStream = listaSvihZupanijaIzDrzava.stream().distinct();
        List<Zupanija> distinctList = distinctStream.collect(Collectors.toList());
        distinctList.sort(new ZupanijaSorter());
        distinctList.forEach(z -> System.out.println(z.getNaziv()));


        // Ispis mape mjernih postaja sa vrijednostima senzora
        Map<Mjesto, List<Senzor>> collect = listaMjernihPostaja.stream()
                .collect(Collectors.toMap(MjernaPostaja::getMjesto, MjernaPostaja::dohvatiSenzore,
                        (mp1, mp2) -> {
                            //System.out.println("Dupli ključevi");
                            return mp1;
                        }));

        for (Mjesto mjesto : collect.keySet()) {
            System.out.println("U mjestu " + mjesto.getNaziv() + " su sljedeći senzori:");
            for (Senzor senzor : collect.get(mjesto)) {
                if (senzor instanceof SenzorTemperature) {
                    System.out.println("Senzor temperature");
                }
                if (senzor instanceof SenzorTlaka) {
                    System.out.println("Senzor tlaka");
                }
                if (senzor instanceof SenzorVlage) {
                    System.out.println("Senzor vlage");
                }
            }

        }

    }
    public static Drzava kreirajDrzavu(Scanner scanner) {

        System.out.println("Unesite naziv države:");
        String nazivDrzave = scanner.nextLine();

        System.out.println("Unesite površinu države:");
        BigDecimal povrsinaDrzave = Validator.unesiBigDecimal(scanner);

        return new Drzava(nazivDrzave, povrsinaDrzave);
    }

    public static Zupanija kreirajZupaniju(Scanner scanner) {

        Drzava drzava = kreirajDrzavu(scanner);
        System.out.println("Unesite naziv županije:");
        String nazivZupanije = scanner.nextLine();

        Optional<MjernaPostaja> postaja = listaMjernihPostaja.stream()
                .filter(p -> p.getMjesto().getZupanija().getNaziv().equals(nazivZupanije)).findFirst();
        if (postaja.isPresent())
            return postaja.get().getMjesto().getZupanija();

        Zupanija zupanija = new Zupanija(nazivZupanije, drzava);
        drzava.getZupanije().add(zupanija);

        return zupanija;
    }
    public static Mjesto kreirajMjesto(Scanner scanner) {

        Zupanija zupanija = kreirajZupaniju(scanner);
        System.out.println("Unesite naziv mjesta:");
        String nazivMjesta = scanner.nextLine();

        Optional<MjernaPostaja> postaja = listaMjernihPostaja.stream()
                .filter(p -> p.getMjesto().getNaziv().equals(nazivMjesta)).findFirst();
        if (postaja.isPresent())
            return postaja.get().getMjesto();

        VrstaMjesta vrstaMjesta = kreirajVrstuMjesta(scanner);
        Mjesto mjesto = new Mjesto(nazivMjesta, zupanija, vrstaMjesta);
        zupanija.getMjesta().add(mjesto);

        return mjesto;
    }
    private static VrstaMjesta kreirajVrstuMjesta(Scanner scanner) {
        VrstaMjesta vrstaMjesta;
        for (int i = 0; i < VrstaMjesta.values().length - 1; i++) {
            System.out.println((i + 1) + ". " + VrstaMjesta.values()[i]);
        }

        Integer redniBrojSenzora = null;

        while (true) {
            System.out.print("Odabir vrste mjesta >> ");
            try {
                redniBrojSenzora = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Neispravan unos!");
                logger.error("Neispravan unos vrste mjesta!", ex);
            }
        }

        if (redniBrojSenzora >= 1 && redniBrojSenzora < VrstaMjesta.values().length) {
            vrstaMjesta = VrstaMjesta.values()[redniBrojSenzora - 1];
        } else {
            vrstaMjesta = VrstaMjesta.OSTALO;
        }
        return vrstaMjesta;
    }

    public static GeografskaTocka kreirajGeografskuTocku(Scanner scanner) {

        System.out.println("Unesite Geo koordinatu X:");
        BigDecimal x = Validator.unesiBigDecimal(scanner);

        System.out.println("Unesite Geo koordinatu Y:");
        BigDecimal y = Validator.unesiBigDecimal(scanner);

        return new GeografskaTocka(x, y);
    }

    public static List<Senzor> kreirajSenzore(Scanner scanner) {
        List<Senzor> senzori = new ArrayList<>();

        senzori.add(kreirajSenzorTemperature(scanner));
        senzori.add(kreirajSenzorVlage(scanner));
        senzori.add(kreirajSenzorTlaka(scanner));

        return senzori;
    }

    private static SenzorTlaka kreirajSenzorTlaka(Scanner scanner) {

        System.out.println("Unesite vrijednost senzora tlaka");
        BigDecimal vrijednostSenzoraTlaka = Validator.unesiBigDecimal(scanner);
        RadSenzora radSenzora = kreirajRadSenzora(scanner);
        SenzorTlaka senzorTlaka = new SenzorTlaka(vrijednostSenzoraTlaka, radSenzora);

        return senzorTlaka;
    }

    private static SenzorVlage kreirajSenzorVlage(Scanner scanner) {
        System.out.println("Unesite vrijednost senzora vlage:");
        BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);

        RadSenzora radSenzora = kreirajRadSenzora(scanner);
        SenzorVlage senzorVlage = new SenzorVlage(vrijednost,radSenzora);

        return senzorVlage;
    }

    private static SenzorTemperature kreirajSenzorTemperature(Scanner scanner) {
        System.out.println("Unesite elektroničku komponentu za senzor temperature:");
        String naziv = scanner.nextLine();

        System.out.println("Unesite vrijednost senzora temperature:");
        BigDecimal vrijednost = Validator.unesiBigDecimal(scanner);
        RadSenzora radSenzora = kreirajRadSenzora(scanner);

        SenzorTemperature senzorTemperature = new SenzorTemperature(vrijednost,naziv, radSenzora);
        return senzorTemperature;
    }

    private static RadSenzora kreirajRadSenzora(Scanner scanner) {
        RadSenzora radSenzora;
        for (int i = 0; i < RadSenzora.values().length - 1; i++) {
            System.out.println((i + 1) + ". " + RadSenzora.values()[i]);
        }

        Integer redniBrojSenzora = null;

        while (true) {
            System.out.print("Odabir rada senzora >> ");
            try {
                redniBrojSenzora = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Neispravan unos!");
                logger.error("Neispravan unos rada senzora!", ex);
            }
        }

        if (redniBrojSenzora >= 1 && redniBrojSenzora < RadSenzora.values().length) {
            radSenzora = RadSenzora.values()[redniBrojSenzora - 1];
        } else {
            radSenzora = RadSenzora.OSTALO;
        }
        return radSenzora;
    }
    public static MjernaPostaja kreirajMjernuPostaju(Scanner scanner) {

        System.out.println("Unesite naziv mjerne postaje:");
        String nazivMjernePostaje = scanner.nextLine();

        Optional<MjernaPostaja> postaja = listaMjernihPostaja.stream().filter(p -> p.getNaziv().equals(nazivMjernePostaje))
                .findFirst();
        if (postaja.isPresent())
            return postaja.get();

        Mjesto mjesto = kreirajMjesto(scanner);
        GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
        List<Senzor> senzori = kreirajSenzore(scanner);

        MjernaPostaja mjernaPostaja = new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori);
        mjesto.getMjernePostaje().add(mjernaPostaja);

        return mjernaPostaja;
    }

    public static MjernaPostaja kreirajRadioSondaznuMjernuPostaju(Scanner scanner) {

        System.out.println("Unesite naziv radio sondažne mjerne postaje:");
        String nazivMjernePostaje = scanner.nextLine();

        Mjesto mjesto = kreirajMjesto(scanner);
        GeografskaTocka geografskaTocka = kreirajGeografskuTocku(scanner);
        List<Senzor> senzori = kreirajSenzore(scanner);

        RadioSondaznaMjernaPostaja radioSondaznaMjernaPostaja = new RadioSondaznaMjernaPostaja(nazivMjernePostaje,
                mjesto, geografskaTocka, senzori);
        mjesto.getMjernePostaje().add(radioSondaznaMjernaPostaja);

        return radioSondaznaMjernaPostaja;
    }


    //GENERIRANJE EXCEPTIONA SVAKE SEKUNDE
        /*while (true) {
            try {
                SenzorTemperature.generirajVrijednost();
                Thread.sleep(1000);
            } catch (VisokaTemperaturaException | NiskaTemperaturaException ex) {
                logger.info(ex.getMessage());

            } catch (InterruptedException ex) {
                logger.info("Interrupted exception");
            }
        }*/
}

    /

