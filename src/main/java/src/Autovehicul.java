import java.io.Serializable;
public class Autovehicul implements Serializable {
	private String marca;
    private String model;
    private int anFabricatie;
    private double kilometriParcursi;
    private double pret;
    
    Autovehicul(){
    	this.marca = "marca";
    	this.model = "model";
    	this.anFabricatie = 0;
    	this.kilometriParcursi = 0;
    	this.pret = 0;
    }
    
    Autovehicul(String marca, String model, int an, double km, double pret){
    	this.marca = marca;
    	this.model = model;
    	this.anFabricatie = an;
    	this.kilometriParcursi = km;
    	this.pret = pret;
    }
    
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
    
    @Override
    public String toString() {
    	return "Autovehiculul marca " + this.marca + ", model " + this.model + " are anul de fabricatie " + this.anFabricatie + " si a parcurs in total " + this.kilometriParcursi + " km. Pretul autovehiculului este de " + this.pret + " euro, TVA inclus."; 
    }
    
}

