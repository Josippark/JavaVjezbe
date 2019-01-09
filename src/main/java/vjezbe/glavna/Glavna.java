package vjezbe.glavna;

import vjezbe.entitet.*;
import vjezbe.entitet.senzori.Senzor;
import vjezbe.entitet.senzori.impl.SenzorTemperature;
import vjezbe.entitet.senzori.impl.SenzorTlaka;
import vjezbe.entitet.senzori.impl.SenzorVlage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Glavna {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<MjernaPostaja> listaMjernihPostaja = new ArrayList<MjernaPostaja>();

        unosMjernihPostaja(scanner, listaMjernihPostaja);

        ispisMjernihPostaja(listaMjernihPostaja);


    }

    public static void unosMjernihPostaja(Scanner scanner, List<MjernaPostaja> listaMjernihPostaja) {
        int i;
        for (i = 1; i <= 3; i++) {
            if (i != 3) {
                System.out.println("Unesite naziv " + i + ". mjerne postaje:");
            } else {
                System.out.println("Unesite naziv " + i + ". radio sondažne mjerne postaje:");
            }

            String nazivMjernePostaje = scanner.nextLine();
            System.out.println("Unesite naziv mjesta:");
            String nazivMjesta = scanner.nextLine();
            System.out.println("Unesite naziv županije:");
            String nazivZupanije = scanner.nextLine();
            System.out.println("Unesite naziv države:");
            String nazivDrzave = scanner.nextLine();
            System.out.println("Unesite površinu države:");
            BigDecimal povrsinaDrzave = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesite x koordinatu:");
            BigDecimal kordX = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesite y koordinatu:");
            BigDecimal kordY = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesite naziv komponente senzore:");
            String nazivSenzora = scanner.nextLine();
            System.out.println("Unesite vrijednost senzora temperature:");
            BigDecimal vrijednostSenzoraTemp = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesite vrijednost senzora vlage:");
            BigDecimal vrijednostSenzoraVlage = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Unesite vrijednost senzora tlaka:");
            BigDecimal vrijednostSenzoraTlaka = scanner.nextBigDecimal();
            scanner.nextLine();

            SenzorTemperature senzorTemperature = new SenzorTemperature(vrijednostSenzoraTemp, nazivSenzora);
            SenzorVlage senzorVlage = new SenzorVlage(vrijednostSenzoraVlage);
            SenzorTlaka senzorTlaka = new SenzorTlaka(vrijednostSenzoraTlaka);

            List<Senzor> senzori = new ArrayList<>();
            senzori.add(senzorTemperature);
            senzori.add(senzorVlage);
            senzori.add(senzorTlaka);


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

            System.out.println("Naziv" + i + ". mjerne postaje:" +
                    listaMjernihPostaja.get(i).getNaziv());
            System.out.println("Mjesto " + i + ". mjerne postaje:" +
                    listaMjernihPostaja.get(i).getMjesto().getNaziv() + ", županija " +
                    listaMjernihPostaja.get(i).getMjesto().getZupanija().getNaziv() + ", država " +
                    listaMjernihPostaja.get(i).getMjesto().getZupanija().getDrzava().getNaziv());
            System.out.println("Geo tocka x koordinata" + i + ". mjerne postaje:" +
                    listaMjernihPostaja.get(i).getGeografskaTocka().getX());
            System.out.println("Geo tocka y koordinata" + i + ". mjerne postaje:" +
                    listaMjernihPostaja.get(i).getGeografskaTocka().getY());
            for(j=0;j<3;j++){
                System.out.println("Senzori su: "+ listaMjernihPostaja.get(i).dohvatiSenzore().get(j).dohvatiPodatkeSenzora());
            }

        }
    }
}
