package vjezbe.sortiranje;

import vjezbe.entitet.Zupanija;

import java.util.Comparator;

public class ZupanijaSorter implements Comparator<Zupanija> {

    public ZupanijaSorter() {
    }

    @Override
    public int compare(Zupanija o1, Zupanija o2) {
        return o1.getNaziv().compareTo(o2.getNaziv());
    }
}
