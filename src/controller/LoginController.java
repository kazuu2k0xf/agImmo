package controller;

import static bdd.AgentBdd.selectAgentByLoginPwd;
import static batch.TraitementsBatch.traitementChiffrementDonneesPersonnelles;
import static bdd.FenetresBdd.selectOneFenetre;
import static utilities.UtilitiesFermeture.fenetreFermeture;


import static utilities.UtilitiesBlowFish.encrypt;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Agent;
import model.Fenetres;
import model.InfoEntete;
import model.LoaderFXML;
import resources.Cstes;
import utilities.DialogBox;

/** ***********************************************************************************************
 * CLASSE : LoginController
 * ************************************************************************************************
 * Cette classe est le controleur de la fenêtre de Login
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public class LoginController {
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private Stage dialogStage;
	@FXML private Label lblErreur;
	@FXML private Label lblDepassement;
	@FXML private TextField txfLogin;
	@FXML private PasswordField pwfPwd;
	@FXML private Button btnLogin;
	@FXML private Button btnQuitter;
	
	//Déclaration de la variable pour compter le nombre de tentatives de connexion effectuées par l'agent
	private int tentativeConnexion = 0;
	private boolean compteBloque = false;



	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@FXML public void initialize() {
		
		//Initialise les labels non visible
		lblErreur.setVisible(false);
		lblDepassement.setVisible(false);

		//Appel de la méthode traitement chiffrement données personnelles
		traitementChiffrementDonneesPersonnelles();

	}
	/**
	 * Méthode permettant de récupérer le stage initialisé par la fenêtre appelante 
	 * Permet de fermer la fenêtre en cours
	 * @param dialogStage	[Stage]	: Stage   
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	/**
	 * Cette méthode permet de gérer la touche [Entrée] sur le fenêtre.
	 * @param 	keyEvent	[KeyEvent] : Evènement clique sur une touche du clavier
	 */
	@FXML public void evtOnKeyPressedBtnLogin(KeyEvent keyEvent){
		if(keyEvent.getCode().equals(KeyCode.ENTER)) {
			traitementConnexion();
		}
	}
	/**
	 * Cette méthode permet de gérer le clic sur le bouton [Valider]
	 */
	@FXML public void evtMouseClickedBtnLogin() {
		traitementConnexion();
	}
	/**
	 * Méthode permettant contrôler les données saisies ( login & pwd ) et d'afficher un message d'erreur si besoin 
	 * ou d'appeler le tableau de bord
	 */
	public void traitementConnexion() {
	    if (compteBloque) {
	        lblDepassement.setVisible(true);
	        lblDepassement.setText("Le compte est bloqué. Veuillez patienter.");
	        return;
	    }

	    String login = encrypt(txfLogin.getText());
	    String pwd = pwfPwd.getText();
	    
	    Agent agent = selectAgentByLoginPwd(login);

	    if (agent == null || (agent != null && !BCrypt.verifyer().verify(pwd.toCharArray(), agent.getAgentPwd()).verified)) {
	        tentativeConnexion++;
	        txfLogin.setText("");
	        pwfPwd.setText("");
	        lblErreur.setVisible(true);

	        if (tentativeConnexion >= 3) {
	            compteBloque = true;
	            btnLogin.setDisable(true);
	            btnQuitter.setDisable(true);

	            Timeline timeline = new Timeline();
	            final int[] countdownStarter = {30};

	            timeline.getKeyFrames().add(
	                new KeyFrame(Duration.seconds(1),
	                    new EventHandler<ActionEvent>() {
	                        @Override
	                        public void handle(ActionEvent event) {
	                            countdownStarter[0]--;
	                            lblDepassement.setVisible(true);
	                            lblDepassement.setText("Le compte est bloqué pendant encore " + countdownStarter[0] + " seconde(s)");

	                            if (countdownStarter[0] <= 0) {
	                                btnLogin.setDisable(false);
	                                btnQuitter.setDisable(false);
	                                lblDepassement.setVisible(false);
	                                lblErreur.setVisible(false);
	                                compteBloque = false;
	                                tentativeConnexion = 0;
	                                timeline.stop();
	                            }
	                        }
	                    }
	                )
	            );

	            timeline.setCycleCount(Animation.INDEFINITE);
	            timeline.play();
	        }
	        return;
	    }

	    // Si l'authentification est réussie
	    Fenetres fenetre = selectOneFenetre(Cstes.TABLEAUDEBORD);
	    if (fenetre != null) {
	        LoaderFXML loaderFxml = new LoaderFXML(fenetre);
	        Stage primaryStage = loaderFxml.createLoaderBorderPane();
	        DashboardController controler = loaderFxml.getLoader().getController();
	        controler.setDialogStage(primaryStage);
	        controler.setAgent(agent);
	        fenetreFermeture(primaryStage);
	        primaryStage.show();
	    }

	    dialogStage.close();
	}

	 
	 
	/** Fermeture de la page en cliquant sur le bouton quitter**/
	@FXML public void evtMouseClickedBtnQuitter() {
			this.dialogStage.close();
			
	}
}
