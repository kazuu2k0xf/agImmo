package controller;

import static bdd.FenetresBdd.selectOneFenetre;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;

import java.sql.SQLException;
import java.util.Observable;

import bdd.InfoDetailBdd;
import javafx.beans.binding.ListExpression;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Agent;
import model.Fenetres;
import model.InfoDetail;
import model.LoaderFXML;
import resources.Cstes;
import utilities.DialogBox;

public class DashboardController {
	/** *************************************************************
	 * Déclaration des variables
	 * **************************************************************/
	private Agent agent;
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private Label		lblAgentNom;
	@FXML private Label		lblAgentPoste;
	@FXML private Label		lblMessageRdv;
	@FXML private Label		lblMessageEtatsDesLieux;
	@FXML private Stage 	dialogStage;
	@FXML private ImageView imvParametresAgent;
	@FXML private ImageView imvAgent;
	@FXML private Label 	lblVersion;
	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@FXML public void initialize() {	
		gestionRendezVous();
		gestionEtatsDesLieux();
	}
	/**
	 * Méthode servant à définir le stage pour pouvoir fermer la fenêtre
	 * @param dialogStage	[Stage]
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	/**
	 * Méthode permettant de recevoir un agent en paramètre de la fenêtre appelante
	 * @param agent	[Agent] : objet Agent correspondant à l'agent connecté.
	 */
	public void setAgent(Agent agent) {

		this.agent = agent;
		String agentNom = this.agent.getPersonName();
		String agentPrenom = this.agent.getPersonFirstName();

		lblAgentPoste.setText(agent.getTypeAgent().getTypeAgentLbl());

		lblAgentNom.setText(agentNom + " " + agentPrenom);


		
		//Ajout du nom de la photo de l'agent connecter dans la variable agentImage
        String agentImage = this.agent.getAgentImage();


        //Affichage de la photo de l'agent connecter
        InfoDetail list  = InfoDetailBdd.selectOneInfoDetailDescription(Cstes.DOSSIERS, Cstes.DOSSIERPORTRAITS);

        String imageUrl = (list.getInfoDetailLbl() + agentImage);

        try {
            Image image = new Image("file:" + imageUrl);

            imvAgent.setImage(image);

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
        }


		
	}

		/**
		 * Méthode gérant et affichant le nombre de Rdv en cours
		 */
		public void gestionRendezVous() {
			String messageRdv = "Aucun rendez-vous";
			lblMessageRdv.setTextAlignment(TextAlignment.LEFT);
			lblMessageRdv.setText(messageRdv);
		}
		/**
		 * Méthode gérant et affichant le nombre d'état de lieux à faire
		 */
		public void gestionEtatsDesLieux() {
			String messageEdl = "Aucun etats des lieux";
			lblMessageEtatsDesLieux.setTextAlignment(TextAlignment.LEFT);
			lblMessageEtatsDesLieux.setText(messageEdl);
		}
		/** Choix de menu : ajout d'un nouveau propriétaire */
		@FXML public void evtOnActionMnuNouveauProprietaire() {}
		/** Choix de menu : Liste des propriétaires existants */
		@FXML public void evtOnActionMnuListeProprietaires() {}
		/** Choix de menu : ajout d'une nouvelle location */
		@FXML public void evtOnActionMnuNouvelleLocation(){}
		/** Choix de menu : Liste des locations existantes */
		@FXML public void evtOnActionMnuListeLocations() {}
		/** Choix de menu : ajout d'un nouveau locataire */
		@FXML public void evtOnActionMnuNouveauLocataire() {}
		/** Choix de menu : Liste des locataires existants */
		@FXML public void evtOnActionMnuListeLocataires() {}
		/** Choix de menu : ajout d'un nouveau bail */
		@FXML public void evtOnActionMnuNouveauBail() {}
		/** Choix de menu : Liste des baux existants */
		@FXML public void evtOnActionMnuListeBaux() {}
		/** Choix de menu : Visualiser les visites de la semaine */
		@FXML public void evtOnMouseClickedImvAppointement() {}
		/** Choix de menu : Visualiser les baux en fin de contrat */
		@FXML public void evtOnMouseClickedImvCtrlEndLease() {}


		/** Choix modification des données de l'agent **/
		@FXML
		public void evtOnMouseClickedImvParametresAgent() {

			Fenetres fenetre	 				= selectOneFenetre(Cstes.MODIFICATIONAGENT);
			if(fenetre!=null) {
				LoaderFXML loaderFxml 			= new LoaderFXML(fenetre);
				Stage primaryStage 	  				= loaderFxml.createLoaderBorderPane();
				AgentDefinitionController controler = loaderFxml.getLoader().getController();
				controler.setDialogStage(primaryStage);
				controler.setAgent(agent);
				primaryStage.showAndWait();
				if(controler.isValiderClicked()) {
					setAgent(agent);
				}
			}

		}


		/** Choix de menu : déconnexion, quitter l'application après validation par l'utilisateur**/
		@FXML public void evtOnActionMnuDeconnexion() {
			DialogBox dialogBox = new DialogBox("Fermeture de l'application", "", "Voulez-vous quitter l'application", AlertType.CONFIRMATION, ButtonType.CANCEL);
			ButtonType reponse = dialogBox.showDialogConfirmation();
			if(reponse == ButtonType.OK) {
				this.dialogStage.close();
			}	
		}
	}
