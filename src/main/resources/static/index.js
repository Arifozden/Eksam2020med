//Oppgave1
// Det skal lages et registreringsprogram for skiutøvere med lagring i en relasjonsdatabase.
// HTML-skjemaet og utdrag av ER-diagram for databasen er gitt nedenfor. Data fra HTML-
// skjemaet skal lagres i tabellen utover. Feilhåndtering og logging må gjøres, og eventuelle
// feilmeldinger skal vises til brukeren. Klientvalidering av input behøver ikke gjøres nå, det er
// tema i siste oppgave. Servervalidering av input er ikke nødvendig. Passordet bør "hash-es" og saltes
//Oppgave 1a
//Lag et JavaScript som mottar dataene fra HTML-skjemaet og kaller en metode på server
// som lagrer dataene i databasen. Bruk jQuery og Ajax (HTTP GET eller POST). Indiker i en
// kildekodekommentar hva som må endres i HTML-skjemaet for å kalle dette JavaScriptet.
//Feilhåndtering må gjøres, og eventuelle feilmeldinger skal vises til brukeren

function mesajKayit(){
    //html den verileri aliyoruz
    const mesaj={
        ad:$("#ad").val(),
        soyad:$("#soyad").val(),
        klup:$("#klup").val(),
        epost:$("#epost").val(),
        sifre:$("#sifre").val(),
    }
    //verileri aldiktan sonra,serverdan post metodunu cagiriyoruz ve server a post metoduyla gonderiyoruz
    $.post("/kaydet",mesaj,function (){
    })
        //hata yonetimi(error handling) istedigi icin burayi ekliyoruz
        .fail(function (jqXHR){
            const json=$.parseJSON(jqXHR.responseText);
            $("#hata").html(json.message); //serverdan response ile gelen hata mesaji burada yazdirilir
        });
}
//html e degistirilmesi gereken kod : <td><input type="submit" id="kayit" onclick="mesajKayit()"></td>