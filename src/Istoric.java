import java.io.Serializable;
import java.util.Date;
/**
 * Clasa care reprezintă o tranzacție.
 *
 * <p>O tranzacție constă în vânzarea sau cumpărarea unui autovehicul, înregistrată într-un istoric.</p>
 *
 * <p>Implementează interfața {@code Serializable} pentru a permite serializarea obiectelor.</p>
 */
public class Istoric implements Serializable {
    private Autovehicul autovehicul;
    private Client client;
    private Date dataTranzactie;
    private boolean vanzare; // true pentru vânzare, false pentru cumpărare
    
    /**
     * Constructor pentru crearea unei instanțe de Istoric.
     *
     * @param autovehicul    Autovehiculul implicat în tranzacție.
     * @param client         Clientul implicat în tranzacție.
     * @param dataTranzactie Data tranzacției.
     * @param vanzare        Tipul de tranzacție (true pentru vânzare, false pentru cumpărare).
     */
 
    public Istoric(Autovehicul autovehicul, Client client, Date dataTranzactie, boolean vanzare) {
        this.autovehicul = autovehicul;
        this.client = client;
        this.dataTranzactie = dataTranzactie;
        this.vanzare = vanzare;
    }
    
    /**
     *      
     * @return Autovehiculul implicat în tranzacție.
     */
    public Autovehicul getAutovehicul() {
        return autovehicul;
    }

    /**
     *
     * @return Clientul implicat în tranzacție.
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @return Data tranzacției.
     */
    public Date getDataTranzactie() {
        return dataTranzactie;
    }

    /**
     * Verifică tipul tranzacției.
     *
     * @return {@code true} pentru vânzare, {@code false} pentru cumpărare.
     */
    public boolean isVanzare() {
        return vanzare;
    }

    /**
     *
     * @return String ce descrie tranzacția.
     */
    @Override
    public String toString() {
        String tipTranzactie = vanzare ? "vânzare" : "cumpărare";
        return "Tranzacție de " + tipTranzactie + " la data " + dataTranzactie + " pentru autovehiculul: " + autovehicul + "\nClient: " + client;
    }
}