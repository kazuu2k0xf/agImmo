package bdd;

import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InfoEntete;

/** ***********************************************************************************************
 * CLASSE : InfoEnteteBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [InfoEntete] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.InfoEntete
 */
public class InfoEnteteBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= InfoEnteteBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de récupérer une liste d'entêtes 
	 * **********************************************************************************
	 * @return		[ObservableList<InfoEntete>]
	 */
	public static ObservableList<InfoEntete> selectAllInfoEntete(){
		/** Déclaration des variables **/
		ObservableList<InfoEntete> listeDonnees 	= FXCollections.observableArrayList();
		InfoEntete infoEntete						= null;	
		/** Initialisation de la requête **/
		String SQL		= "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				infoEntete = map(resultSet);
				listeDonnees.add(infoEntete);
			}	
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}
		return listeDonnees;			
	}
	/** *********************************************************************************
	 * Méthode permettant de récupérer une entête à partir de son identifiant
	 * **********************************************************************************
	 * @param 	infoEnteteIdt 	[int]			: identifiant de l'entête
	 * @return					[InfoEntete]	: instance créée
	 */
	public static InfoEntete selectOneInfoEntete(int infoEnteteIdt){
		/** Déclaration des variables **/
		InfoEntete infoEntete						= null;	
		/** Initialisation de la requête **/
		String SQL		= "SELECT * FROM InfoEntete WHERE infoEnteteIdt = " + infoEnteteIdt;
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				infoEntete = map(resultSet);
			}	
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}
		return infoEntete;			
	}	

	/** *********************************************************************************
	 * Méthode permettant de créer un objet de type [InfoEntete] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[InfoEntete]	: instance InfoEntete créée
	 */
	private static InfoEntete map(ResultSet resultset) {
		/** Initialisation de l'entête **/
		InfoEntete infoEntete				= null;
		try {
			int infoEnteteIdt = resultset.getInt("infoEnteteIdt");
			String infoEnteteKey = resultset.getString("infoEnteteIdt");
			String infoEnteteDescription = resultset.getString("infoEnteteDescription");
			String infoEnteteCbx = resultset.getString("infoEnteteCbx");
			int infoEnteteNbreDetailMax = resultset.getInt("infoEnteteNbreDetailMax");
			infoEntete = new InfoEntete(infoEnteteIdt, infoEnteteKey, infoEnteteDescription, infoEnteteCbx, infoEnteteNbreDetailMax);
			
			
			
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return infoEntete;
	}
}
