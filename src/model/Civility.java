package model;

public class Civility {

	/**
	 * civilityIdt	[int]		: Identifiant de la civilité
	 * civilityLbl	[String]	: Libellé long
	 * civilityLbc	[String]	: Libellé court
	 */
	private int		civilityIdt;
	private String	civilityLbl;
	private String	civilityLbc;
	/**
	 * @param newCivilityIdt
	 * @param newCivilityLbl
	 * @param newCivilityLbc
	 */
	public Civility(int newCivilityIdt, String newCivilityLbl, String newCivilityLbc) {
		super();
		this.civilityIdt = newCivilityIdt;
		this.civilityLbl = newCivilityLbl;
		this.civilityLbc = newCivilityLbc;
	}
	public int getCivilityIdt() {
		return civilityIdt;
	}
	public void setCivilityIdt(int civilityIdt) {
		this.civilityIdt = civilityIdt;
	}
	public String getCivilityLbl() {
		return civilityLbl;
	}
	public void setCivilityLbl(String civilityLbl) {
		this.civilityLbl = civilityLbl;
	}
	public String getCivilityLbc() {
		return civilityLbc;
	}
	public void setCivilityLbc(String civilityLbc) {
		this.civilityLbc = civilityLbc;
	}
	@Override
	public String toString() {
	    return civilityLbc; 
	}
	
	
}