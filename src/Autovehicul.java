import java.io.Serializable;

/**
 * Clasa care reprezintă un autovehicul.
 *
 * <p>Un autovehicul are următoarele caracteristici: marcă, model, an de fabricație,
 * kilometri parcurși și preț.</p>
 *
 * <p>Implementează interfața {@code Serializable} pentru a permite serializarea obiectelor.</p>
 */

public class Autovehicul implements Serializable {
	private String marca;
    private String model;
    private int anFabricatie;
    private double kilometriParcursi;
    private double pret;
    
    /**
     * Constructor implicit ce inițializează un autovehicul cu valori implicite.
     * Marca și modelul sunt setate la "marca" și "model", anul de fabricație la 0,
     * kilometrii parcurși și prețul la 0.
     */
    Autovehicul() {
        this.marca = "marca";
        this.model = "model";
        this.anFabricatie = 0;
        this.kilometriParcursi = 0;
        this.pret = 0;
    }

    /**
     * Constructor care inițializează autovehiculul cu valorile specificate.
     *
     * @param marca            
     * @param model            
     * @param an               
     * @param km               
     * @param pret             
     */
    Autovehicul(String marca, String model, int an, double km, double pret) {
        this.marca = marca;
        this.model = model;
        this.anFabricatie = an;
        this.kilometriParcursi = km;
        this.pret = pret;
    }
    
    // setteri si getteri
    
    public void setMarca(String marca) {
    	this.marca = marca;
    }
    
    public String getMarca() {
    	return this.marca;
    }
    
    public void setModel(String model) {
    	this.model = model;
    }
    
    public String getModel() {
    	return this.model;
    }
    
    public void setAn(int anFabricatie) {
    	this.anFabricatie = anFabricatie;
    }
    
    public int getAn() {
    	return this.anFabricatie;
    }
    
    public void setKm(double kilometriParcursi) {
    	this.kilometriParcursi = kilometriParcursi;
    }
    
    public double getKm() {
    	return this.kilometriParcursi;
    }
    
    public void setPret(double pret) {
    	this.pret = pret;
    }
    
    public double getPret() {
    	return this.pret;
    }
    /**
     *
     * @return String care conține informații despre autovehicul.
     */
    @Override
    public String toString() {
    	return "Autovehiculul marca " + this.marca + ", model " + this.model + " are anul de fabricatie " + this.anFabricatie + " si a parcurs in total " + this.kilometriParcursi + " km. Pretul autovehiculului este de " + this.pret + " euro, TVA inclus."; 
    }
    
}

