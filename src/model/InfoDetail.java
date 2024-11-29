package model;

public class InfoDetail {
	/**
	 * infoDetailIdt			[int]		: Identifiant du détail de l'informatio
	 * infoEnteteIdt			[int]		: Identifiant de l'entête 
	 * infoDetailKeyIdt			[int]		: Clé de recherche du détail
	 * infoDetailDescription	[String]	: Description du détail
	 * infoDetailLbl			[String]	: Libellé long
	 * infoDetailLbc			[String]	: Libellé court
	 * infoDetailValueInt		[int]		: Valeur numérique entier
	 * infoDetailValueDouble	[double]	: Valeur numérique décimale
	 * infoDetailTri			[int]		: Ordre de tri des lignes de détail
	 * infoDetailDefault		[boolean]	: Encréation cette ligne est la ligne par défaut
	 * infoEntete				[InfoEntete]: Entête
	 */
	private int 			infoDetailIdt;
	private int 			infoEnteteIdt;		
	private int 			infoDetailKeyIdt;
	private String			infoDetailDescription;
	private String 			infoDetailLbl;
	private String 			infoDetailLbc;
	private int				infoDetailValueInt;
	private double			infoDetailValueDouble;
	private int 			infoDetailTri;
	private boolean 		infoDetailDefault;
	private InfoEntete		infoEntete;
	/**
	 * @param newInfoDetailIdt
	 * @param newInfoEnteteIdt
	 * @param newInfoDetailKeyIdt
	 * @param newInfoDetailLbl
	 * @param newInfoDetailLbc
	 * @param newInfoDetailValueInt
	 * @param newInfoDetailValueDouble
	 * @param newInfoDetailTri
	 * @param newInfoDetailDefault
	 * @param newInfoEntete
	 */
	public InfoDetail(int newInfoDetailIdt, int newInfoEnteteIdt, int newInfoDetailKeyIdt, String newInfoDetailDescription, String newInfoDetailLbl,
			String newInfoDetailLbc, int newInfoDetailValueInt, double newInfoDetailValueDouble, int newInfoDetailTri,
			boolean newInfoDetailDefault, InfoEntete newInfoEntete) {
		super();
		this.infoDetailIdt 			= newInfoDetailIdt;
		this.infoEnteteIdt 			= newInfoEnteteIdt;
		this.infoDetailKeyIdt 		= newInfoDetailKeyIdt;
		this.infoDetailDescription	= newInfoDetailDescription;
		this.infoDetailLbl 			= newInfoDetailLbl;
		this.infoDetailLbc 			= newInfoDetailLbc;
		this.infoDetailValueInt 	= newInfoDetailValueInt;
		this.infoDetailValueDouble 	= newInfoDetailValueDouble;
		this.infoDetailTri 			= newInfoDetailTri;
		this.infoDetailDefault 		= newInfoDetailDefault;
		this.infoEntete				= newInfoEntete;
	}
	public int getInfoDetailIdt() {
		return infoDetailIdt;
	}
	public void setInfoDetailIdt(int infoDetailIdt) {
		this.infoDetailIdt = infoDetailIdt;
	}
	public int getInfoEnteteIdt() {
		return infoEnteteIdt;
	}
	public void setInfoEnteteIdt(int infoEnteteIdt) {
		this.infoEnteteIdt = infoEnteteIdt;
	}
	public int getInfoDetailKeyIdt() {
		return infoDetailKeyIdt;
	}
	public void setInfoDetailKeyIdt(int infoDetailKeyIdt) {
		this.infoDetailKeyIdt = infoDetailKeyIdt;
	}
	public String getInfoDetailDescription() {
		return infoDetailDescription;
	}
	public void setInfoDetailDescription(String infoDetailDescription) {
		this.infoDetailDescription = infoDetailDescription;
	}
	public String getInfoDetailLbl() {
		return infoDetailLbl;
	}
	public void setInfoDetailLbl(String infoDetailLbl) {
		this.infoDetailLbl = infoDetailLbl;
	}
	public String getInfoDetailLbc() {
		return infoDetailLbc;
	}
	public void setInfoDetailLbc(String infoDetailLbc) {
		this.infoDetailLbc = infoDetailLbc;
	}
	public int getInfoDetailValueInt() {
		return infoDetailValueInt;
	}
	public void setInfoDetailValueInt(int infoDetailValueInt) {
		this.infoDetailValueInt = infoDetailValueInt;
	}
	public double getInfoDetailValueDouble() {
		return infoDetailValueDouble;
	}
	public void setInfoDetailValueDouble(double infoDetailValueDouble) {
		this.infoDetailValueDouble = infoDetailValueDouble;
	}
	public int getInfoDetailTri() {
		return infoDetailTri;
	}
	public void setInfoDetailTri(int infoDetailTri) {
		this.infoDetailTri = infoDetailTri;
	}
	public boolean isInfoDetailDefault() {
		return infoDetailDefault;
	}
	public void setInfoDetailDefault(boolean infoDetailDefault) {
		this.infoDetailDefault = infoDetailDefault;
	}
	public InfoEntete getInfoEntete() {
		return infoEntete;
	}
	public void setInfoEntete(InfoEntete infoEntete) {
		this.infoEntete = infoEntete;
	}
	
	@Override
	public String toString() {
		return "InfoDetail [infoDetailIdt=" + infoDetailIdt + ", infoEnteteIdt=" + infoEnteteIdt + ", infoDetailKeyIdt="
				+ infoDetailKeyIdt + ", infoDetailDescription=" + infoDetailDescription + ", infoDetailLbl="
				+ infoDetailLbl + ", infoDetailLbc=" + infoDetailLbc + ", infoDetailValueInt=" + infoDetailValueInt
				+ ", infoDetailValueDouble=" + infoDetailValueDouble + ", infoDetailTri=" + infoDetailTri
				+ ", infoDetailDefault=" + infoDetailDefault + ", infoEntete=" + infoEntete + "]";
	}
	
	

}