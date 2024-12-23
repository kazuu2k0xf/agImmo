package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeAgent {

	/**
	 * typeAgentIdt	[int]		: Identifiant du type d'agent
	 * typeAgentLbl	[String]	: Libellé long
	 * typeAgentLbc	[String]	: Libellé court
	 */
	private int		typeAgentIdt;
	private StringProperty	typeAgentLbl;
	private StringProperty	typeAgentLbc;
	/**
	 * @param newTypeAgentIdt
	 * @param newTypeAgentLbl
	 * @param newTypeAgentLbc
	 */
	public TypeAgent(int newTypeAgentIdt, String newTypeAgentLbl, String newTypeAgentLbc) {
		super();
		this.typeAgentIdt = newTypeAgentIdt;
		this.typeAgentLbl = new SimpleStringProperty(newTypeAgentLbl);
		this.typeAgentLbc = new SimpleStringProperty(newTypeAgentLbc);
	}
	public int getTypeAgentIdt() {
		return typeAgentIdt;
	}
	public void setTypeAgentIdt(int typeAgentIdt) {
		this.typeAgentIdt = typeAgentIdt;
	}
	
	public String getTypeAgentLbl() {
		return typeAgentLbl.get();
	}
	public StringProperty getTypeAgentLblProperty() {
		return typeAgentLbl;
	}
	

	public void setTypeAgentLblProperty(String typeAgentLbl) {
		this.typeAgentLbl = new SimpleStringProperty(typeAgentLbl);
	}
	public void setTypeAgentLbl(StringProperty typeAgentLbl) {
		this.typeAgentLbl = typeAgentLbl;
	}
	
	
	public String getTypeAgentLbc() {
		return typeAgentLbc.get();
	}
	public StringProperty getTypeAgentLbcProperty() {
		return typeAgentLbc;
	}
	
	public void setTypeAgentLbc(String typeAgentLbc) {
		this.typeAgentLbc = new SimpleStringProperty(typeAgentLbc);
	}
	public void setTypeAgentLbcProperty(StringProperty typeAgentLbc) {
		this.typeAgentLbc = (typeAgentLbc);
	}
	
	
	@Override
	public String toString() {
	    return typeAgentLbc.get(); 
	}
	
	
}
