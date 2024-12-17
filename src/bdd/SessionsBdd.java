package bdd;

import static bdd.AgentBdd.selectAgentByIdt;
import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Agent;
import model.Sessions;

/** ***********************************************************************************************
 * CLASSE : SessionsBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [Sessions] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Sessions
 */
public class SessionsBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= SessionsBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de récupérer une liste des sessions pour un agent
	 * **********************************************************************************
	 * @param agentId	[int] 						: identifiant de l'agent
	 * @return			[ObservableList<Sessions>]	: Collection d'instances créée
	 */
	public static ObservableList<Sessions> selectAllSessionsByAgent(int agentIdt){
		/** Déclaration des variables **/
		ObservableList<Sessions> listeDonnees 	= FXCollections.observableArrayList();
		Sessions sessions						= null;
		/** Initialisation de la requête **/
		String SQL		= "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, agentIdt);
			ResultSet resultSet   				= preparedStatement.executeQuery();
			while (resultSet.next()) {
				sessions = map(resultSet);
				if(sessions!=null) listeDonnees.add(sessions);
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
	 * Méthode permettant de récupérer une session à partir de l'UUID de session
	 * **********************************************************************************
	 * @param uuid		[String]	: UUID 	
	 * @return			[Sessions]	: instance créée
	 */
	public static Sessions selectOneSessionsByUuid(String uuid) {
		/** Déclaration des variables **/
		Sessions sessions						= null;
		/** Initialisation de la requête **/
		String SQL		 = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, uuid);
			ResultSet resultSet   				= preparedStatement.executeQuery();
			while (resultSet.next()) {
				sessions = map(resultSet);
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
		return sessions;
	}
	/** *********************************************************************************
	 * Méthode permettant d'insérer une session pour un agent dans la base de données
	 * **********************************************************************************
	 * @param sessions		[Sessions]	: sessions à insérer
	 * @return				[int]		: nombre d'enregistrements insérés
	 */
	public static int insertSessions(Sessions sessions) {
		/** Déclaration des variables */
		int nbreEnreg	= 0;
		/** Initialisation de la requête */
		String SQL		   = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false
												,sessions.getSessionsAgentIdt()
												,sessions.getSessionsUuid()
												,sessions.getSessionsDateDebut()
												,sessions.getSessionsTimeDebut()
												,sessions.getSessionsDateFin()
												,sessions.getSessionsTimeFin()
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
	 * Méthode permettant de modifier une session pour un agent dans la base de données
	 * **********************************************************************************
	 * @param sessions		[Sessions]	: sessions à insérer
	 * @return				[int]		: nombre d'enregistrements modifiés
	 */
	public static int updateSessions(Sessions sessions) {
		/** Déclaration des variables */
		int nbreEnreg	= 0;
		/** Initialisation de la requête */
		String SQL		   = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false
												,sessions.getSessionsAgentIdt()
												,sessions.getSessionsUuid()
												,sessions.getSessionsDateDebut()
												,sessions.getSessionsTimeDebut()
												,sessions.getSessionsDateFin()
												,sessions.getSessionsTimeFin()
												,sessions.getSessionsIdt()
												);
			nbreEnreg							= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}
		return nbreEnreg;
	}	
	/** *********************************************************************************
	 * Méthode permettant de créer un objet de type [Sessions] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[Sessions]		: instance Sessions créée
	 */
	private static Sessions map(ResultSet resultset) {
		/** Initialisation des variables **/
		Sessions sessions				= null;
		try {
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return sessions;
	}
}
