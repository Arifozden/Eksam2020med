package eksamen.eksam2020med;

public class Kullanici {
    private String epost;
    private String sifre;

    public Kullanici(String epost, String sifre) {
        this.epost = epost;
        this.sifre = sifre;
    }

    public Kullanici(){}

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
