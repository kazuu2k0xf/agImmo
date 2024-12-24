package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InfoEntete {

	/**
	 * infoEnteteIdt			[int]		: Identifiant de l'entête
	 * infoEnteteKey			[String]	: Clé de recherche de l'information
	 * infoEnteteDescription	[String]	: Description de l'information
	 * infoEnteteCbx			[String]	: Combobox rattachée à l'information
	 * infoEnteteNbreDetailMax	[int]		: Nombre de lignes de détail de l'information (facultatif)
	 */
	private int 	infoEnteteIdt;
	private StringProperty	infoEnteteKey;
	private StringProperty  infoEnteteDescription;
	private String  infoEnteteCbx;
	private int 	infoEnteteNbreDetailMax;
	/**
	 * @param newInfoEnteteIdt
	 * @param newInfoEnteteKey
	 * @param newInfoEnteteDescription
	 * @param newInfoEnteteCbx
	 * @param newInfoEnteteNbreDetailMax
	 */
	public InfoEntete(int newInfoEnteteIdt, String newInfoEnteteKey, String newInfoEnteteDescription, String newInfoEnteteCbx,
			int newInfoEnteteNbreDetailMax) {
		super();
		this.infoEnteteIdt 				= newInfoEnteteIdt;
		this.infoEnteteKey 				= new SimpleStringProperty(newInfoEnteteKey);
		this.infoEnteteDescription 		= new SimpleStringProperty(newInfoEnteteDescription);
		this.infoEnteteCbx 				= newInfoEnteteCbx;
		this.infoEnteteNbreDetailMax 	= newInfoEnteteNbreDetailMax;
	}
	public int getInfoEnteteIdt() {
		return infoEnteteIdt;
	}
	public void setInfoEnteteIdt(int infoEnteteIdt) {
		this.infoEnteteIdt = infoEnteteIdt;
	}
	
	public String getInfoEnteteKey() {
		return infoEnteteKey.get();
	}
	public StringProperty getInfoEnteteKeyProperty() {
		return infoEnteteKey;
	}
	
	public void setInfoEnteteKey(String infoEnteteKey) {
		this.infoEnteteKey = new SimpleStringProperty(infoEnteteKey);
	}
	
	
	public String getInfoEnteteDescription() {
		return infoEnteteDescription.get();
	}
	public StringProperty getInfoEnteteDescriptionProperty() {
		return infoEnteteDescription;
	}
	
	
	public void setInfoEnteteDescription(String infoEnteteDescription) {
		this.infoEnteteDescription = new SimpleStringProperty(infoEnteteDescription);
	}
	public String getInfoEnteteCbx() {
		return infoEnteteCbx;
	}
	public void setInfoEnteteCbx(String infoEnteteCbx) {
		this.infoEnteteCbx = infoEnteteCbx;
	}
	public int getInfoEnteteNbreDetailMax() {
		return infoEnteteNbreDetailMax;
	}
	public void setInfoEnteteNbreDetailMax(int infoEnteteNbreDetailMax) {
		this.infoEnteteNbreDetailMax = infoEnteteNbreDetailMax;
	}
	
	@Override
	public String toString() {
		return "InfoEntete [infoEnteteIdt=" + infoEnteteIdt + ", infoEnteteKey=" + infoEnteteKey
				+ ", infoEnteteDescription=" + infoEnteteDescription + ", infoEnteteCbx=" + infoEnteteCbx
				+ ", infoEnteteNbreDetailMax=" + infoEnteteNbreDetailMax + "]";
	}
	

}