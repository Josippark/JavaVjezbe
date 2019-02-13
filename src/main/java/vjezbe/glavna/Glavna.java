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
import vjezbe.util.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Glavna {
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<MjernaPostaja> listaMjernihPostaja = new ArrayList<>();
        unosMjernihPostaja(scanner, listaMjernihPostaja);
        ispisMjernihPostaja(listaMjernihPostaja);
        while (true) {
            try {
                SenzorTemperature.generirajVrijednost();
                Thread.sleep(1000);
            } catch (VisokaTemperaturaException | NiskaTemperaturaException ex) {
                logger.info(ex.getMessage());

            } catch (InterruptedException ex) {
                logger.info("Interrupted exception");
            }
        }
    }

    public static void unosMjernihPostaja(Scanner scanner, List<MjernaPostaja> listaMjernihPostaja) {
        int i;
        for (i = 1; i <= 3; i++) {
            if (i != 3) {
                System.out.println("Unesite naziv " + i + ". mjerne postaje:");
            } else {
                System.out.println("Unesite naziv " + i + ". radio sondažne mjerne postaje:");
            }

            //UNOS POTREBNIH PODATAKA
            String nazivMjernePostaje = scanner.nextLine();
            System.out.println("Unesite naziv mjesta:");
            String nazivMjesta = scanner.nextLine();
            System.out.println("Unesite naziv županije:");
            String nazivZupanije = scanner.nextLine();
            System.out.println("Unesite naziv države:");
            String nazivDrzave = scanner.nextLine();


            BigDecimal povrsinaDrzave = Validator.checkBigDecimal(scanner, new BigDecimal(0), "Unesite površinu države:");
            BigDecimal kordX = Validator.checkBigDecimal(scanner, new BigDecimal(0), "Unesite X koordinatu:");
            BigDecimal kordY = Validator.checkBigDecimal(scanner, new BigDecimal(0), "Unesite Y koordinatu:");
            scanner.nextLine();
            System.out.println("Unesite naziv komponente senzore:");
            String nazivSenzora = scanner.nextLine();

            BigDecimal vrijednostSenzoraTemp = Validator.checkBigDecimal(scanner, new BigDecimal(0), "Unesite vrijednost senzora temperature:");
            BigDecimal vrijednostSenzoraVlage = Validator.checkBigDecimal(scanner, new BigDecimal(0), "Unesite vrijednost senzora vlage:");
            BigDecimal vrijednostSenzoraTlaka = Validator.checkBigDecimal(scanner, new BigDecimal(0), "Unesite vrijednost senzora tlaka:");
            scanner.nextLine();

            //INSTANCIRANJE SENZORA
            SenzorTemperature senzorTemperature = new SenzorTemperature(vrijednostSenzoraTemp, nazivSenzora);
            SenzorVlage senzorVlage = new SenzorVlage(vrijednostSenzoraVlage);
            SenzorTlaka senzorTlaka = new SenzorTlaka(vrijednostSenzoraTlaka);

            //DODAVANJE U LISTU
            List<Senzor> senzori = new ArrayList<>();
            senzori.add(senzorTemperature);
            senzori.add(senzorVlage);
            senzori.add(senzorTlaka);

            //INSTANCIRANJE OSTALIH OBJEKATA
            GeografskaTocka geografskaTocka = new GeografskaTocka(kordX, kordY);
            Drzava drzava = new Drzava(nazivDrzave, povrsinaDrzave);
            Zupanija zupanija = new Zupanija(nazivZupanije, drzava);
            Mjesto mjesto = new Mjesto(nazivMjesta, zupanija);

            if (i != 3) {
                listaMjernihPostaja.add(new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori));
            } else {
                System.out.println("Unesite visinu radio sondažne postaje:");
                int visinaPostaje = scanner.nextInt();
                listaMjernihPostaja.add(new RadioSondaznaMjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka, senzori, visinaPostaje));
            }
        }

    }


    public static void ispisMjernihPostaja(List<MjernaPostaja> listaMjernihPostaja) {
        int i;
        int j;
        for (i = 0; i < listaMjernihPostaja.size(); i++) {
            System.out.println("Naziv " + (i + 1) + ". mjerne postaje:" +
                    listaMjernihPostaja.get(i).getNaziv());
            System.out.println("Mjesto " + (i + 1) + ". mjerne postaje: " +
                    listaMjernihPostaja.get(i).getMjesto().getNaziv() + ", županija " +
                    listaMjernihPostaja.get(i).getMjesto().getZupanija().getNaziv() + ", država " +
                    listaMjernihPostaja.get(i).getMjesto().getZupanija().getDrzava().getNaziv());
            System.out.println("Geografska tocka X koordinata" + (i + 1) + ". mjerne postaje: " +
                    listaMjernihPostaja.get(i).getGeografskaTocka().getX());
            System.out.println("Geografska tocka Y koordinata" + (i + 1) + ". mjerne postaje: " +
                    listaMjernihPostaja.get(i).getGeografskaTocka().getY());
            for (j = 0; j < 3; j++) {
                System.out.println(listaMjernihPostaja.get(i).dohvatiSenzore().get(j).dohvatiPodatkeSenzora());
            }
            System.out.println("--------------------------------------------------------------------------------------");

        }
    }

}
