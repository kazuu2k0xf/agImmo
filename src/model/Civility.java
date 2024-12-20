package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Civility {

	/**
	 * civilityIdt	[int]		: Identifiant de la civilité
	 * civilityLbl	[String]	: Libellé long
	 * civilityLbc	[String]	: Libellé court
	 */
	private int		civilityIdt;
	private StringProperty	civilityLbl;
	private StringProperty	civilityLbc;
	/**
	 * @param newCivilityIdt
	 * @param newCivilityLbl
	 * @param newCivilityLbc
	 */
	public Civility(int newCivilityIdt, String newCivilityLbl, String newCivilityLbc) {
		super();
		this.civilityIdt = newCivilityIdt;
		this.civilityLbl = new SimpleStringProperty(newCivilityLbl);
		this.civilityLbc = new SimpleStringProperty(newCivilityLbc);
		
	}
	public int getCivilityIdt() {
		return civilityIdt;
	}
	public void setCivilityIdt(int civilityIdt) {
		this.civilityIdt = civilityIdt;
	}
	public StringProperty getCivilityLbl() {
		return civilityLbl;
	}
	public void setCivilityLbl(StringProperty civilityLbl) {
		this.civilityLbl = civilityLbl;
	}
	public StringProperty getCivilityLbc() {
		return civilityLbc;
	}
	public void setCivilityLbc(StringProperty civilityLbc) {
		this.civilityLbc = civilityLbc;
	}
	
	@Override
	public String toString() {
		return "Civility [civilityIdt=" + civilityIdt + ", civilityLbl=" + civilityLbl + ", civilityLbc=" + civilityLbc
				+ "]";
	}
	
	
	
	
	
}