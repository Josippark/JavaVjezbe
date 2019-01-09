package vjezbe.util;

import vjezbe.entitet.senzori.Senzor;

import java.util.Comparator;

public class CustomComparator implements Comparator<Senzor> {

    @Override
    public int compare(Senzor o1, Senzor o2) {
        return o1.getMjernaJedinica().compareTo(o2.getMjernaJedinica());
    }
}
