package model;

public class Agent extends Person {
	/**
	 * agentType	[int]		: Identifiant du type de l'agent
	 * typeAgent	[TypeAgent]	: Type de l'agent 
	 * agentLogin	[String]	: Login
	 * agentPwd		[String]	: Mot de passe
	 * agentImage	[String]	: Nom du fichier image de l'agent
	 */
	private int    		agentType;
	private TypeAgent	typeAgent;
	private String 		agentLogin;
	private String 		agentPwd;
	private String 		agentImage;
	/**
	 * @param newPersonIdt
	 * @param newPersonName
	 * @param newPersonFirstName
	 * @param newPersonMobile
	 * @param newPersonPhone
	 * @param newPersonEmail
	 * @param newPersonCivility
	 * @param newCivility
	 * @param newAgentType
	 * @param newTypeAgent
	 * @param newAgentLogin
	 * @param newAgentPwd
	 * @param newAgentImage
	 */
	public Agent(int newPersonIdt, String newPersonName, String newPersonFirstName, String newPersonMobile,
			String newPersonPhone, String newPersonEmail, int newPersonCivility, Civility newCivility, int newAgentType, 
			TypeAgent newTypeAgent,String newAgentLogin, String newAgentPwd, String newAgentImage) {
		
		super(newPersonIdt, newPersonName, newPersonFirstName, newPersonMobile, newPersonPhone, newPersonEmail,
				newPersonCivility, newCivility);
		
		this.agentType 	= newAgentType;
		this.typeAgent	= newTypeAgent;
		this.agentLogin = newAgentLogin;
		this.agentPwd 	= newAgentPwd;
		this.agentImage = newAgentImage;
	}
	public int getAgentType() {
		return agentType;
	}
	public void setAgentType(int agentType) {
		this.agentType = agentType;
	}
	public TypeAgent getTypeAgent() {
		return typeAgent;
	}
	public void setTypeAgent(TypeAgent typeAgent) {
		this.typeAgent = typeAgent;
	}
	public String getAgentLogin() {
		return agentLogin;
	}
	public void setAgentLogin(String agentLogin) {
		this.agentLogin = agentLogin;
	}
	public String getAgentPwd() {
		return agentPwd;
	}
	public void setAgentPwd(String agentPwd) {
		this.agentPwd = agentPwd;
	}
	public String getAgentImage() {
		return agentImage;
	}
	public void setAgentImage(String agentImage) {
		this.agentImage = agentImage;
	}
	@Override
	public String toString() {
		return "Agent [agentType=" + agentType + ", typeAgent=" + typeAgent + ", agentLogin=" + agentLogin
				+ ", agentPwd=" + agentPwd + ", agentImage=" + agentImage + "]";
	}
	
	
}
