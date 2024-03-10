import java.util.Date;

public class Istoric {
    private Autovehicul autovehicul;
    private Client client;
    private Date dataTranzactie;
    private boolean vanzare; // true pentru vânzare, false pentru cumpărare

    public Istoric(Autovehicul autovehicul, Client client, Date dataTranzactie, boolean vanzare) {
        this.autovehicul = autovehicul;
        this.client = client;
        this.dataTranzactie = dataTranzactie;
        this.vanzare = vanzare;
    }

    public Autovehicul getAutovehicul() {
        return autovehicul;
    }

    public Client getClient() {
        return client;
    }

    public Date getDataTranzactie() {
        return dataTranzactie;
    }

    public boolean isVanzare() {
        return vanzare;
    }

    @Override
    public String toString() {
        String tipTranzactie = vanzare ? "vânzare" : "cumpărare";
        return "Tranzacție de " + tipTranzactie + " la data " + dataTranzactie + " pentru autovehiculul: " + autovehicul + "\nClient: " + client;
    }
}
