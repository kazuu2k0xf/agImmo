package bdd;

import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;
import static bdd.AddressBdd.selectOneAdresse;
import static bdd.LegalRegimeBdd.selectOneLegalRegime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Address;
import model.Company;
import model.LegalRegime;

public class CompanyBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= CompanyBdd.class.getSimpleName();
	/**
	 * Méthode permettant de récupérer une liste des compagnies du groupe triée par nom
	 * @return		[ObservableList<Company>]
	 */	
	public static ObservableList<Company> selectAllCompany(){
		/** Initialisation des variables **/
		ObservableList<Company> listeDonnees 	= FXCollections.observableArrayList();
		Company company							= null;
		/** Initialisation de la requête **/
		String SQL		= "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return listeDonnees;
	}
	/**
	 * Méthode permettant de rechercher l'agence qui est le siège social
	 * @return		[Company]	: instance Company créée
	 */
	public static Company selectOneCompanyAdminSeat() {
		/** Initialisation des variables **/
		Company company							= null;
		/** Initialisation de la requête **/
		String SQL		= "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return company;
	}
	/**
	 * Méthode permettant de contrôler si un code Siret est déjàa utilisé 
	 * @param codeSiret		[String]	: Code Siret à contrôler
	 * @param companyIdt	[int]		: Identifiant de l'agence
	 * @return				[int]		
	 */
	public static int controleSiret(String codeSiret, int companyIdt) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		SQL				+= "SELECT COUNT(*) FROM Company";
		SQL				+= " WHERE companySiret LIKE ? AND companyIdt <> ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;	
	}
	/**
	 * Méthode permettant d'insérer une company
	 * @param company	[Company]	: Company à insérer
	 * @return			[int]		: nombre d'enregistrements supprimés
	 */
	public static int insertCompany(Company company) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;
	}
	/**
	 * Méthode permettant de modifier une company
	 * @param company	[Company]	: Company à modifier
	 * @return			[int]		: nombre d'enregistrements supprimés
	 */
	public static int updateCompany(Company company) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;
	}	
	/**
	 * Méthode permettant de supprimer une company
	 * @param company	[Company]	: Company à supprimer
	 * @return			[int]		: nombre d'enregistrements supprimés
	 */
	public static int deleteCompany(Company company) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;
	}
	/**
	 * Méthode permettant de créer un objet de type [Company] à partir d'un enregistrement de la base de données
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[Company]		: instance Company créée
	 */
	private static Company map(ResultSet resultset) {
		/** Attributs de la classe **/
		Company company 					= null;
		try {
		} catch (SQLException e) {
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			System.out.println("Classe  : " + classeName);
			System.out.println("Méthode : " + methodeName);
			System.out.println("Erreur lors de la lecture de la compagnie : " + e);
		}		
		return company;
	}
}