//Oppgave 3 (20%)
// Oppdater JavaScript-koden fra oppgave 2a for å klientvalidere innloggingsskjemaet. (Du kan
// kopiere svaret ditt fra oppgave 2a, lime inn i denne oppgaven, og gjøre utvidelsene.) Det skal
// ikke utvikles servervalidering.
// JavaScript-koden skal ikke kalle metoden på server dersom valideringen av input feiler.
// Feilene legges ut i div-en med id="loggetInn"

function epostDogrula(){
    const epost=$("#epost").val();
    const regexp=/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    regexp.test(epost);
    if(!ok){
        $("#girisYapildi").html("e-postta hata");
        return false;
    }else {
        $("#girisYapildi").html("");
        return true;
    }
}

function sifreDogrula(){
    const sifre=$("#sifre").val();
    const regexp=/^(?=.*[A-ZÆØÅa-zæøå])(?=.*\d)[A-ZÆØÅa-zæøå\d]{6,}$/;
    regexp.test(sifre)
    if(!ok){
        $("#girisYapildi").html("sifrede hata");
        return false;
    }else {
        $("#girisYapildi").html("");
        return true;
    }
}

function dogrulaOK(){
if(epostDogrula()&&sifreDogrula()){
    return true;
}
return false;
}