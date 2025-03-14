package bdd;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Town;

/**
 * Cette classe contient les methodes CRUD de l'entite [Town]. 
 * Elle herite de la Classe ConnexionBdd qui gere le driver et la connexion a la base de donnees
 * 
 * @author 		Alain CASARA
 * @version		1.0
 * Date			Juillet 2023
 */
public class TownBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= TownBdd.class.getSimpleName();
	/**
	 * Description 	: Methode permettant de recuperer la liste villes triee par nom.
	 * @return  	[ObservableList<Town>]		            
	 */
	public static ObservableList<Town> selectAllTown(){
		/** Initialisation des variables **/
		ObservableList<Town> listeDonnees 	= FXCollections.observableArrayList();
		Town town							= null;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return listeDonnees;			
	}
	/**
	 * Description 	: Methode permettant de recuperer une ville à partir de son identifiant.
	 * @return  	[Town]		            
	 */
	public static Town selectOneTown(int townIdt){
		/** Initialisation des variables **/
		Town town							= null;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return town;			
	}
	/**
	 * Methode 	: map
	 * Description 	: Methode permettant de d'instancier un objet [Town] a partir d'un enregistrement
	 *                de la base de donnees.
	 * @param 	resultset	[Resultset]		: resultat de la requete
	 * @return				[Town]			: instance Town creee
	 */
	private static Town map(ResultSet resultset) {
		/** Initialisation des variables **/
		Town town				= null;
		try {
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture de la ville : " + e);
			e.printStackTrace();
		}		
		return town;
	}
}