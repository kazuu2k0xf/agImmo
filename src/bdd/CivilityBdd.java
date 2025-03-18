package bdd;

import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Civility;
import model.InfoEntete;
import model.Sessions;

/** ***********************************************************************************************
 * CLASSE : CivilityBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [Civility] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Civility
 */
public class CivilityBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= CivilityBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de récupérer la liste des civilités triée par libellé long
	 * **********************************************************************************
	 * @return		[ObservableList<Civility>]	: Collection d'instances créée
	 */
	public static ObservableList<Civility> selectAllCivility(){
		/** Déclaration des variables **/
		ObservableList<Civility> listeDonnees 	= FXCollections.observableArrayList();
		Civility civility						= null;	
		/** Initialisation de la requête **/
		String SQL		 = "SELECT * FROM Civility ORDER BY civilityLbl";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement  = initialisationRequete(connexion, SQL, false);
			ResultSet resultSet  = preparedStatement.executeQuery();
			while (resultSet.next()) {
				civility = map(resultSet);
				listeDonnees.add(civility);
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
	 * Méthode permettant de récupérer une civilité à partir de son identifiant
	 * **********************************************************************************
	 * @param civilityIdt	[int]		: identifiant de la civilité
	 * @return				[Civility]	: instance Civility Créée
	 */
	public static Civility selectOneCivility(int civilityIdt) {
		/** Déclaration des variables **/
		Civility civility						= null;	
		/** Initialisation de la requête **/
		String SQL		 = "SELECT * FROM Civility WHERE civilityIdt LIKE ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement  = initialisationRequete(connexion, SQL, false, civilityIdt);
			ResultSet resultSet  = preparedStatement.executeQuery();
			while (resultSet.next()) {
				civility = map(resultSet);
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
		return civility;	
	}


	/** *********************************************************************************
	 * Méthode permentant de contôler si la civilité est utilisée
	 * **********************************************************************************
	 * @return 		[int]   : Nombre d'enregistrement trouvée
	 */	
	public static int selectNbreCivility(int civilityIdt) {
		/** Déclaration des variables **/
		int civility							= 0;	
		/** Initialisation de la requête **/
		String SQL = "SELECT COUNT(*) FROM Agent WHERE agentCivility = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try (   PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, civilityIdt);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				civility = resultSet.getInt(1);
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
		return civility;	
	}

	/** *********************************************************************************
	 * Méthode permettant de supprimer une civility
	 * **********************************************************************************
	 * @param civility		[Civility]	: Civility à supprimer
	 * @return				[int]		: nombre d'enregistrement supprimés 
	 */
	public static int deleteCivility(Civility civility) {
		/** Déclaration des variables */
		int nbreEnreg	= 0;
		// Initialisation de la requête
		String SQL		 = "DELETE FROM Civility WHERE civilityIdt = ?";
		// Connexion à la base de données
		Connection connexion = trtConnexionBdd();
		try (
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, civility.getCivilityIdt())) {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}

		return nbreEnreg;
	}


	/** **********************************************************************************
	 * Méthode permettant de mofifier une civility
	 * **********************************************************************************
	 * @param civility 	[Civility]  	: Civility à modifier
	 * @return 			[int]			: nombre d'enregistrement supprimés
	 */
	public static int updateCivility(Civility civility) {
		/** Déclaration des variables */
		int nbreEnreg	= 0;
		/** Initialisation de la requête */
		String SQL = "UPDATE Civility SET civilityLbl = ?, civilityLbc = ? WHERE civilityIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false
					,civility.getCivilityLbl()
					,civility.getCivilityLbc()
					,civility.getCivilityIdt()
					);
			nbreEnreg							= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}
		return nbreEnreg;
	}	


	/** **********************************************************************************
	 * Méthode permettant d'insérer une civility
	 * **********************************************************************************
	 * @param civility 	[Civility] 	: Civility à modifier
	 * @return 			[int] 		: nombre d'enregistrements supprimés
	 */
	public static int insertCivility(Civility civility) {
		/** Déclaration des variables */
		int nbreEnreg	= 0;
		/** Initialisation de la requête */
		String SQL = "INSERT INTO Civility (civilityLbl, civilityLbc) VALUES (?, ?)";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false
					,civility.getCivilityLbl()
					,civility.getCivilityLbc()
					);
			nbreEnreg							= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}
		return nbreEnreg;
	}

	/** *********************************************************************************
	 * Méthode permettant de créer un objet de type [Civility] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[Civility]		: instance Civility créée
	 */
	private static Civility map(ResultSet resultset) {
		/** Déclaration du nouvel objet **/
		Civility civility 			= null;
		try {
			int civilityIdt = resultset.getInt("civilityIdt");
			String civilityLbl = resultset.getString("civilityLbl");
			String civilityLbc = resultset.getString("civilityLbc");
			civility = new Civility(civilityIdt, civilityLbl, civilityLbc);

		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return civility;
	}
}
