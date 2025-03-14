package bdd;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LegalRegime;

/**
 * Cette classe contient les méthodes CRUD de l'entité [LegalRegime]. Elle hêrite de la Classe ConnexionBdd
 * contenant les données de connexion à la base de données.
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class LegalRegimeBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= LegalRegimeBdd.class.getSimpleName();
	/**
	 * Description 	: Méthode permettant de récupérer la liste des régimes légaux triée par libellé.
	 * @return  	[ObservableList<LegalRegime>]		            
	 */
	public static ObservableList<LegalRegime> selectAllLegalRegime(){
		/** Initialisation des variables **/
		ObservableList<LegalRegime> listeDonnees 	= FXCollections.observableArrayList();
		LegalRegime legalRegime						= null;
		/** Initialisation de la requête **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return listeDonnees;			
	}
	/**
	 * Description 	: Méthode permettant de récupérer un régime légal.
	 * @return  	[LegalRegime]		            
	 */
	public static LegalRegime selectOneLegalRegime(int legalRegimeIdt){
		/** Initialisation des variables **/
		LegalRegime legalRegime						= null;
		/** Initialisation de la requête **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return legalRegime;			
	}
	/**
	 * Description 	: Methode permettant de d'instancier un objet [LegalRegime] a partir d'un enregistrement
	 *                de la base de donnees.
	 * @param 	resultset	[Resultset]		: resultat de la requete
	 * @return				[LegalRegime]	: instance de LegalRegime creee
	 */
	private static LegalRegime map(ResultSet resultset) throws SQLException {
		/** Initialisation des variables **/
		LegalRegime legalRegime	= null;
		try {
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture des donnees du proprietaire : " + e);
			e.printStackTrace();
		}
		return legalRegime;
	}
}