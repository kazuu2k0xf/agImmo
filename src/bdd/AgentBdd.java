package bdd;

import static bdd.CivilityBdd.selectOneCivility;
import static bdd.TypeAgentBdd.selectOneTypeAgent;
import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Agent;
import model.Civility;
import model.TypeAgent;

/** ***********************************************************************************************
 * CLASSE : AgentBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [Agent] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Agent
 */
public class AgentBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= AgentBdd.class.getSimpleName();
	
	/** *********************************************************************************
	 * Méthode permettant de récupérer un agent à partir de son login et son mot de passe
	 * **********************************************************************************
	 * @param 	login	[String]	: login de l'agent
	 * @param 	pwd		[String]	: mot de passe
	 * @return			[Agent]		: instance Agent créée
	 */
	public static Agent selectAgentByLoginPwd(String login) {
		/** Déclaration des variables **/
		Agent agent 	= null;
		/** Initialisation de la requête **/
		String SQL = "SELECT * FROM Agent WHERE agentLogin LIKE ? ";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL avec gestion des Exceptions */
		try {
			PreparedStatement preparedStatement  = initialisationRequete(connexion, SQL, false, login);
			ResultSet resultSet  = preparedStatement.executeQuery();
			while (resultSet.next()) {
				agent = map(resultSet);
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
		return agent;		
	}
	/** *********************************************************************************
	 * Méthode permettant de récupérer un agent à partir de son identifiant
	 * **********************************************************************************
	 * @param  agentIdt	[int]		: Identifiant de l'agent
	 * @return			[Agent]		: instance Agent créée
	 */
	public static Agent selectAgentByIdt(int agentIdt) {
		/** Déclaration des variables **/
		Agent agent 	= null;
		/** Initialisation de la requête **/
		String SQL		 = "SELECT * FROM Agent WHERE agentIdt LIKE ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement  = initialisationRequete(connexion, SQL, false, agentIdt);
			ResultSet resultSet  = preparedStatement.executeQuery();
			while (resultSet.next()) {
				agent = map(resultSet);
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
		return agent;		
	}	
	/** *********************************************************************************
	 * Méthode permettant de modifier les informations d'un agent
	 * **********************************************************************************
	 * @param 	agent	[Agent]	: Agent à modifier
	 * @return			[int]	: nombre d'enregistrements modifiés
	 */
	public static int updateAgent(Agent agent) {
	    // Déclaration des variables
	    int nbreEnreg = 0;
	    // Initialisation de la requête
	    String SQL = "UPDATE Agent SET agentName = ?, agentFirstName = ?, agentMobile = ?, agentPhone = ?, agentEmail = ?, agentCivility = ?, agentType = ?, agentLogin = ?, agentPwd = ?, agentImage = ? WHERE agentIdt = ?";

	    // Connexion à la base de données
	    Connection connexion = trtConnexionBdd();

	    try {
	        // Initialisation de la requête préparée
	        PreparedStatement pstmt = initialisationRequete(connexion, SQL, false, 
	            agent.getPersonName(), 
	            agent.getPersonFirstName(), 
	            agent.getPersonMobile(), 
	            agent.getPersonPhone(), 
	            agent.getPersonEmail(), 
	            agent.getPersonCivility(), 
	            agent.getAgentType(), 
	            agent.getAgentLogin(), 
	            agent.getAgentPwd(), 
	            agent.getAgentImage(), 
	            agent.getPersonIdt()
	        );

	        // Exécuter la requête
	        nbreEnreg = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        /*
	        L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe
	        interne anonyme) renvoie un objet java.lang.reflect.Method qui contient des
	        informations sur la méthode immédiatement englobante.
	        */
	        class Dummy {};
	        String methodeName = Dummy.class.getEnclosingMethod().getName();
	        gestionDesExceptionsStates(e, SQL, classeName, methodeName);
	    }
	    return nbreEnreg;
	}

	/** *********************************************************************************
	 * Méthode permettant d'instancier un objet de type [Agent] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]	: resultat de la requête
	 * @return				[Agent]		: instance Agent créée
	 */
	private static Agent map(ResultSet resultset){
		/** Déclaration du nouvel objet **/
		Agent agent						= null;
		
		try {
			/** Initialisation des variables **/
			int agentIdt = resultset.getInt("agentIdt");
	        String agentName = resultset.getString("agentName");
	        String agentFirstName = resultset.getString("agentFirstName");
	        String agentMobile = resultset.getString("agentMobile");
	        String agentPhone = resultset.getString("agentPhone");
	        String agentEmail = resultset.getString("agentEmail");
	        int agentCivility = resultset.getInt("agentCivility");
	        Civility civility = selectOneCivility(agentCivility);
	        int agentType = resultset.getInt("agentType");
	        TypeAgent typeAgent = selectOneTypeAgent(agentType);
	        
	        String agentLogin = resultset.getString("agentLogin");
	        String agentPwd = resultset.getString("agentPwd");
	        String agentImage = resultset.getString("agentImage");

	        // Création de l'objet Agent
	        agent = new Agent(agentIdt, agentName, agentFirstName, agentMobile, 
	                          agentPhone, agentEmail, agentCivility, civility, 
	                          agentType, typeAgent, agentLogin, agentPwd, agentImage);
			
			
			/** Instanciation d'un nouvel objet */
			/** Création de l'agent **/
			
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}
		return agent;
	}
}
