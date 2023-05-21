//Det skal utvikles klient- og serverkode for å logge på en resultatløsning. HTML-skjemaet som
// skal brukes for dette er vist nedenfor. Bruk den samme tabellen for utover som i forrige
// oppgave. Feilhåndtering og logging må gjøres, og eventuelle feilmeldinger skal vises til
// brukeren.

// a) Skriv en JavaScript-funksjon som kaller en metode på server som sjekker om
// innloggingsinformasjonen er korrekt. Det skal vises informasjon om innloggingen var korrekt
// eller ikke i taggen "loggetInn"
function giris(){
    const kullanici={
        epost: $("#epost").val(),
        sifre: $("#sifre").val()
    };

    if(dogrulaOK()){
        $.post("/giris",kullanici,function (girisYapildi){
            if(girisYapildi){
                //giris basarili ise index e gondersin diye de dusunebiliriz ama soruda div de mesaj olarak yazsin yazdigi
                // icin bunu kullanmadik : window.location.href="index.html";
                $("#girisYapildi").html("Giris basarili");
            }else {
                $("#girisYapildi").html("Epost ya da sifre hatali");
            }
        })
            //feilhåndtering istedigi icin burayi ekledik
            .fail(function (jqXHR){
                const json=$.parseJSON((jqXHR.responseText));
                $("#hata").html(json.message);
            });
    }

}