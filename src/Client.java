import java.io.Serializable;

/**
 * Clasa care reprezintă un client în contextul tranzacțiilor cu autovehicule.
 *
 * <p>Fiecare client are un nume, prenume, adresă și număr de telefon.</p>
 *
 * <p>Implementează interfața {@code Serializable} pentru a permite serializarea obiectelor.</p>
 */

public class Client implements Serializable {
    private String nume;
    private String prenume;
    private String adresa;
    private String numarTelefon;

    /**
     * Constructor implicit care inițializează clientul cu valori implicite.
     * Numele și prenumele sunt setate la "Nume" și "Prenume",
     * adresa la "Adresa", iar numărul de telefon la "Numar telefon".
     */
    
    public Client() {
        this.nume = "Nume";
        this.prenume = "Prenume";
        this.adresa = "Adresa";
        this.numarTelefon = "Numar telefon";
    }
    
    /**
     * Constructor care inițializează clientul cu valorile specificate.
     *
     * @param nume         
     * @param prenume       
     * @param adresa        
     * @param numarTelefon 
     */
    
    public Client(String nume, String prenume, String adresa, String numarTelefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.numarTelefon = numarTelefon;
    }

    //setteri si getteri
    
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
    
    /**
     *
     * @return String care conține informații despre client.
     */

    @Override
    public String toString() {
        return "Clientul " + prenume + " " + nume + " locuieste la adresa " + adresa + " si poate fi contactat la numarul de telefon " + numarTelefon + ".";
    }
}
