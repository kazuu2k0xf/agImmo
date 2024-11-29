package model;

public class TypeAgent {

	/**
	 * typeAgentIdt	[int]		: Identifiant du type d'agent
	 * typeAgentLbl	[String]	: Libellé long
	 * typeAgentLbc	[String]	: Libellé court
	 */
	private int		typeAgentIdt;
	private String	typeAgentLbl;
	private String	typeAgentLbc;
	/**
	 * @param newTypeAgentIdt
	 * @param newTypeAgentLbl
	 * @param newTypeAgentLbc
	 */
	public TypeAgent(int newTypeAgentIdt, String newTypeAgentLbl, String newTypeAgentLbc) {
		super();
		this.typeAgentIdt = newTypeAgentIdt;
		this.typeAgentLbl = newTypeAgentLbl;
		this.typeAgentLbc = newTypeAgentLbc;
	}
	public int getTypeAgentIdt() {
		return typeAgentIdt;
	}
	public void setTypeAgentIdt(int typeAgentIdt) {
		this.typeAgentIdt = typeAgentIdt;
	}
	public String getTypeAgentLbl() {
		return typeAgentLbl;
	}
	public void setTypeAgentLbl(String typeAgentLbl) {
		this.typeAgentLbl = typeAgentLbl;
	}
	public String getTypeAgentLbc() {
		return typeAgentLbc;
	}
	public void setTypeAgentLbc(String typeAgentLbc) {
		this.typeAgentLbc = typeAgentLbc;
	}
	
	
	@Override
	public String toString() {
	    return typeAgentLbc; 
	}
	
	
}
