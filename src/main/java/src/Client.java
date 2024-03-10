public class Client {
    private String nume;
    private String prenume;
    private String adresa;
    private String numarTelefon;

    
    public Client() {
        this.nume = "Nume";
        this.prenume = "Prenume";
        this.adresa = "Adresa";
        this.numarTelefon = "Numar telefon";
    }

    public Client(String nume, String prenume, String adresa, String numarTelefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.numarTelefon = numarTelefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    @Override
    public String toString() {
        return "Clientul " + prenume + " " + nume + " locuieste la adresa " + adresa + " si poate fi contactat la numarul de telefon " + numarTelefon + ".";
    }
}
