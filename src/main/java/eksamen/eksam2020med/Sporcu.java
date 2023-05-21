package eksamen.eksam2020med;

public class Sporcu {
    private String ad;
    private String soyad;
    private String klup;
    private String epost;
    private String sifre;

    public Sporcu(String ad, String soyad, String klup, String epost, String sifre) {
        this.ad = ad;
        this.soyad = soyad;
        this.klup = klup;
        this.epost = epost;
        this.sifre = sifre;
    }

    public Sporcu(){}

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getKlup() {
        return klup;
    }

    public void setKlup(String klup) {
        this.klup = klup;
    }

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

