package model;
/**
 * La classe [Person] est une classe abstraite, elle ne peut être instancier.
 * @author acasara
 *
 */
public abstract class Person {
	/**
	 * personIdt		[int]		: Identifiant de la personne
	 * personName		[String]	: Nom 
	 * personFirstName	[String]	: Prénom 
	 * personMobile		[String]	: Téléphone portable
	 * personPhone		[String]	: Téléphone
	 * personEmail		[String]	: Email
	 * personCivility	[int]		: Identifiant de la civilité
	 * civility			[Civility]	: Civilité
	 */
	private int  		personIdt;
	private String 		personName;
	private String 		personFirstName;
	private String 		personMobile;
	private String 		personPhone;
	private String 		personEmail;
	private int    		personCivility;
	private Civility	civility;
	/**
	 * 
	 * @param newPersonIdt
	 * @param newPersonName
	 * @param newPersonFirstName
	 * @param newPersonMobile
	 * @param newPersonPhone
	 * @param newPersonEmail
	 * @param newPersonCivility
	 * @param newCivility
	 */
	public Person(int newPersonIdt, String newPersonName, String newPersonFirstName, 
			String newPersonMobile, String newPersonPhone,
			String newPersonEmail, int newPersonCivility, Civility newCivility) {
		super();
		this.personIdt 			= newPersonIdt;
		this.personName 		= newPersonName;
		this.personFirstName 	= newPersonFirstName;
		this.personMobile 		= newPersonMobile;
		this.personPhone 		= newPersonPhone;
		this.personEmail 		= newPersonEmail;
		this.personCivility 	= newPersonCivility;
		this.civility			= newCivility;
	}
	public int getPersonIdt() {
		return personIdt;
	}
	public void setPersonIdt(int personIdt) {
		this.personIdt = personIdt;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonFirstName() {
		return personFirstName;
	}
	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}
	public String getPersonMobile() {
		return personMobile;
	}
	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}
	public String getPersonPhone() {
		return personPhone;
	}
	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public int getPersonCivility() {
		return personCivility;
	}
	public void setPersonCivility(int personCivility) {
		this.personCivility = personCivility;
	}
	public Civility getCivility() {
		return civility;
	}
	public void setCivility(Civility civility) {
		this.civility = civility;
	}
	@Override
	public String toString() {
		return "Person [personIdt=" + personIdt + ", personName=" + personName + ", personFirstName=" + personFirstName
				+ ", personMobile=" + personMobile + ", personPhone=" + personPhone + ", personEmail=" + personEmail
				+ ", personCivility=" + personCivility + ", civility=" + civility + "]";
	}
	
	
}