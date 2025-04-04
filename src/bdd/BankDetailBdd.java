package bdd;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;
import static utilities.UtilitiesBlowFish.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BankDetail;

/**
 * Cette classe contient les methodes CRUD de l'entite [BankDetail]. 
 * Elle herite de la Classe ConnexionBdd qui gere le driver et la connexion a la base de donnees
 * 
 * @author 		Alain CASARA
 * @version		1.0
 * Date			Juillet 2023
 */
public class BankDetailBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= BankDetailBdd.class.getSimpleName();
	/**
	 * Description 	: Methode permettant de recuperer une coordonnee bancaire.
	 * @param	bankDetailIdt	[int]
	 * @return  				[BankDetail]	: Instance de BankDetail creee 			            
	 */
	public static BankDetail selectOneBankDetail(int bankDetailIdt) {
		/** Initialisation des variables **/
		BankDetail bankDetail = null;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return bankDetail;		
	}
	/**
	 * Description 	: Methode permettant de recuperer l'identifiant d'une coordonnee bancaire a partir de sa cle de generation
	 * @param	bankDetailGenerateKey	[String]
	 * @return  						[int]	: identifiant du detail bancaire		            
	 */
	public static int selectOneBankDetailByKey(String bankDetailGenerateKey) {
		/** Initialisation des variables **/
		int bankDetailIdt		= 0;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return bankDetailIdt;		
	}	
	/**
	 * Description 	: Methode permettant d'inserer une coordonnee bancaire dans la base de donnees avec une requete INSERT.
	 * @param	bankDetail	[BankDetail]   	
	 * @return  			[int]			: nombre d'enregistrements inseres, permet de controler que l'insertion s'est bien effectuee	            
	 */
	public static int insertBankDetail(BankDetail bankDetail) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;		
	}
	/**
	 * Description 	: Methode permettant de modifier une coordonnee bancaire dans la base de donnees avec une requete UPDATE.
	 * @param	bankDetail	[BankDetail]   	
	 * @return  			[int]			: nombre d'enregistrements modifies, permet de controler que la modification s'est bien effectuee	            
	 */
	public static int updateBankDetail(BankDetail bankDetail) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;		
	}
	/**
	 * Description 	: Methode permettant de supprimer une coordonnee bancaire dans la base de donnees avec une requete DELETE.
	 * @param	bankDetailIdt	[int]   	
	 * @return  				[int]	: nombre d'enregistrements supprimes, permet de controler que la suppression s'est bien effectuee	            
	 */
	public static int deleteBankDetail(int bankDetailIdt) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;		
	}
	/**
	 * Description 	: Methode permettant de d'instancier un objet [BankDetail] a partir d'un enregistrement
	 *                de la base de donnees.
	 * @param 	resultset	[Resultset]		: resultat de la requete
	 * @return				[BankDetail]	: instance de BankDetail creee
	 */
	private static BankDetail map(ResultSet resultset){
		/** Initialisation des variables **/
		BankDetail bankDetail			= null;
		try {		
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture des donnees des donnees bancaire : " + e);
			e.printStackTrace();
		}
		return bankDetail;
	}
}
