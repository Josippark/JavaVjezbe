package vjezbe.glavna;

import vjezbe.enitet.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Glavna {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<MjernaPostaja> listaMjernihPostaja = new ArrayList<MjernaPostaja>();
        int i = 0;

        for (i = 1; i <= 3; i++) {
            System.out.println("Unesite naziv " + i + ". mjerne postaje:");
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

            GeografskaTocka geografskaTocka = new GeografskaTocka(kordX, kordY);
            Drzava drzava = new Drzava(nazivDrzave, povrsinaDrzave);
            Zupanija zupanija = new Zupanija(nazivZupanije, drzava);
            Mjesto mjesto = new Mjesto(nazivMjesta, zupanija);

            listaMjernihPostaja.add(new MjernaPostaja(nazivMjernePostaje, mjesto, geografskaTocka));

        }

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
        }


    }
}
