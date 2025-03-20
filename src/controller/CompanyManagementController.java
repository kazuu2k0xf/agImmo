package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Agent;
import model.Company;
import model.Fenetres;
import model.LoaderFXML;
import model.TypeAgent;
import resources.Cstes;

import static bdd.AddressBdd.deleteAddress;
import static bdd.CompanyBdd.*;
import static bdd.FenetresBdd.selectOneFenetre;
import static bdd.TypeAgentBdd.selectOneTypeAgent;
import static bdd.AddressBdd.selectOneAdresse;

import utilities.DialogBox;

public class CompanyManagementController extends GeneralManagementController {
	/** *************************************************************
	 * Déclaration des variables
	 * **************************************************************/
	ObservableList<Company> listeDonnees 	= FXCollections.observableArrayList();
	Agent agent								= null;
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private TableView<Company> tbvDonnees;
	@FXML private Label							lblTitre;
	@FXML private TableColumn<Company, String> 	tbcCompanyName;
	@FXML private TableColumn<Company, String> 	tbcCompanyTelephone;
	@FXML private TableColumn<Company, String> 	tbcCompanyEmail;
	@FXML private TableColumn<Company, String> 	tbcCompanyAddress;
	@FXML private TableColumn<Company, String> 	tbcCompanyTown;
	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@Override
	public void initialize() {
		
		lblTitre.setText("Gestion des Agences");
		
		listeDonnees.addAll(selectAllCompany());
		
		tbcCompanyName.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCompanyNameProperty());
		tbcCompanyTelephone.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCompanyTelephoneProperty());
		tbcCompanyEmail.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCompanyEmailProperty());
		tbcCompanyAddress.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getAdress().getAdressCompleteProperty());
		tbcCompanyTown.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getAdress().getTown().getTownCompleteProperty());

	
		
		
		tbvDonnees.setItems(listeDonnees);
		
	}
	/**
	 * Méthode permettant :
	 * 1. Raz ObservableList
	 * 2. Raz TableView
	 * 3. Remplissage de l'ObservableList
	 * 4. Mise à jour de la TableView
	 */
	private void trtAffichage() {
		listeDonnees.clear();
		tbvDonnees.getItems().clear();
		listeDonnees	= selectAllCompany();
		tbvDonnees.getItems().addAll(listeDonnees);
	}
	

	@Override
	public void evtOnMouseClickedImvSelection() {
		// TODO Auto-generated method stub
		// Méthode non utilisée pour l'instant mais obligatoire.
	}

	/**
	 * Méthode permettant d'appeler la fenêtre de définition d'une agence en modification
	 */
	@Override
	public void evtOnMouseClickedBtnModifier() {
		  if(agent.getAgentType() == 1) {
			  Fenetres fenetre 								  = selectOneFenetre(Cstes.AGENCEDEFINITION);

				if (fenetre != null) {
					LoaderFXML loaderFxml 						  = new LoaderFXML(fenetre);
					Stage primaryStage 							  = loaderFxml.createLoaderBorderPane();
					CompanyDefinitionController controler = loaderFxml.getLoader().getController();
					controler.setDialogStage(primaryStage);
					controler.setAction("update");
					primaryStage.showAndWait();
				} 
		  }		
	}
	/**
	 * Méthode permettant d'appeler la fenêtre de définition d'une agence en ajout
	 */
	@Override
	public void evtOnMouseClickedBtnAjouter() {
		 if(agent.getAgentType() == 3) {
			 Fenetres fenetre	  							= selectOneFenetre(Cstes.AGENCEDEFINITION);
				if(fenetre!=null) {
					LoaderFXML loaderFxml 					= new LoaderFXML(fenetre);
					Stage primaryStage 	  					= loaderFxml.createLoaderBorderPane();
					CompanyDefinitionController controler 	= loaderFxml.getLoader().getController();
					controler.setDialogStage(primaryStage);
					controler.setAction("create");
					primaryStage.showAndWait();
				} 
		  }		
	}
	/**
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Supprimer]
	 * La suppression doit être validée par un choix utilisateur au travers d'une dialogBox
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnSupprimer() {
	}
	/**
	 * Description	: Methode gérant le double clic sur la liste, elle correspond à  l'action Modifier.
	 * @param  event	[MouseEvent]	: action sur la souris par l'utilisateur
	 */
	@Override
	@FXML public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			evtOnMouseClickedBtnModifier();
		}
	}
	/**
	 * Méthode permettant de recevoir un agent en paramètre de la fenêtre appelante
	 * @param agent	[Agent] : objet Agent correspondant à l'agent connecté.
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
}
