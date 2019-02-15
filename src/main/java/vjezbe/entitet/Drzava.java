package vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Drzava {

    private String naziv;
    private BigDecimal povrsina;
    List<Zupanija> zupanije;

    public Drzava(String naziv, BigDecimal povrsina) {
        this.naziv = naziv;
        this.povrsina = povrsina;
        this.zupanije = new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(BigDecimal povrsina) {
        this.povrsina = povrsina;
    }

    public List<Zupanija> getZupanije() {
        return zupanije;
    }
}
