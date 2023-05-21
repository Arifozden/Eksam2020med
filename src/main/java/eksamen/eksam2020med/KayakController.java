package eksamen.eksam2020med;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
//Det skal lages et registreringsprogram for skiutøvere med lagring i en relasjonsdatabase.
//HTML-skjemaet og utdrag av ER-diagram for databasen er gitt nedenfor. Data fra HTML-
//skjemaet skal lagres i tabellen utover. Feilhåndtering og logging må gjøres, og eventuelle
//feilmeldinger skal vises til brukeren. Klientvalidering av input behøver ikke gjøres nå, det er
//tema i siste oppgave. Servervalidering av input er ikke nødvendig. Passordet bør "hash-es" og saltes

//b) Lag en Spring-controller og en metode i denne som mottar dataene fra JavaScriptet i
//oppgave 1a og lagrer dataene i databasen. Anta du har en POJO (Plain Old Java Object) som
//stemmer overens med kolonnene i tabellen utover, som vist i vedlegget. Det er ikke
//nødvendig å lage et eget repository-lag (legg SQL-koden som ellers ville vært i repository-
//laget direkte inn i kontrolleren).
@RestController
public class KayakController {


    // Oppgave 2 :
    // Det skal utvikles klient- og serverkode for å logge på en resultatløsning. HTML-skjemaet som
    //skal brukes for dette er vist nedenfor. Bruk den samme tabellen for utover som i forrige
    //oppgave. Feilhåndtering og logging må gjøres, og eventuelle feilmeldinger skal vises til
    //brukeren.
    //b)
    // Skriv den ekstra javakoden som må legges til i Spring-controlleren fra oppgave 2a for å
    //håndtere innlogging, som også tar vare på innloggingsstatus via sessions. Skriv også POJO-en
    //du trenger for å overføre data (e-postadresse og passord) mellom klient og server.
    @Autowired
    private HttpSession session;
    @Autowired
    //sql islemlerini otomatik yapmasi icin Autowired ekledik
    //veritabani kullanacagimiz icin db
    private JdbcTemplate db;


    private Logger logger= LoggerFactory.getLogger(KayakController.class);


    //giris fonksiyonu icin if-else ve try-catch ile hata yonetimi
    @PostMapping("/giris")
    public boolean giris(Kullanici kullanici, HttpServletResponse response) throws IOException {
        String sql="SELECT * FROM kullanici WHERE epost=?";
        try {
            Sporcu dbBruker=db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Sporcu.class),new Object[]{kullanici.getEpost()});
            if(sifreKontrol(kullanici.getEpost(), dbBruker.getSifre())){
                session.setAttribute("Giris yapildi",kullanici);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
String hataMesaji="Giriste hata" +e;
logger.error(hataMesaji);
response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), hataMesaji);
return false;
        }
    }

    private boolean sifreKontrol(String sifre, String hashSifre){
        boolean ok=BCrypt.checkpw(sifre, hashSifre);
        return ok;
    }
    //javascriptten gonderdigimiz metodun ne yapacagini yaziyoruz
    @PostMapping("/kaydet") //HttpServletResponse klient e bir sey gondermek icin response=yanit
    public void mesajKayit(Sporcu sporcu, HttpServletResponse response) throws IOException {
        //sifre hash-salt islemleri icin yeni fonk olusturduk
        String hash=sifreSifrele(sporcu.getSifre()); //burada sifre hash-salt icin fonk. gonderiliyor
        //sql kodu ile veritabanina gelen verileri kaydediyoruz
        String sql="INSERT INTO Sporcu (ad,soyad,klup,epost,sifre) VALUES (?,?,?,?,?)";
        //feilhåndtering istedigi icin sql islemini try-catch blogu icinde yapiyoruz
        try{
            //buradaki kodla sql kodu al ve devamindaki degerleri yaz demis oluyoruz
            //sifreyi digerleri gibi almiyoruz cunku hash islemine tabi tutulmasi istendi soruda
            db.update(sql,sporcu.getAd(),sporcu.getSoyad(),sporcu.getKlup(),sporcu.getEpost(),hash);
        }catch (Exception e){
            //hata durumunda logg da ve status de hata metnini yazdiriyoruz
            String hataMesaji="sporcu kayitta hata"+e;
            logger.error(hataMesaji);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),hataMesaji); //burada hata mesajini geri klient e gonderiyoruz json a
        }
    }
//bu fonk. da sifre, hash ve salt islemlerine tabi tutulacak
    //BCrypt sinifini kullaniyoruz. BCrypt.hashpw hash;gensalt ise salt islemi icin
    private String sifreSifrele(String sifre){
        String sifrelenmisSifre=BCrypt.hashpw(sifre,BCrypt.gensalt(10));
        return sifrelenmisSifre;
    }
}
