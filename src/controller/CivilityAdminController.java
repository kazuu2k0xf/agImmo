package controller;

import static bdd.CivilityBdd.deleteCivility;
import static bdd.CivilityBdd.selectAllCivility;
import static bdd.CivilityBdd.updateCivility;
import static bdd.CivilityBdd.selectNbreCivility;
import static bdd.CivilityBdd.insertCivility;
import static utilities.UtilitiesControls.isTextFieldEmpty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Civility;
import model.Person;
import resources.Cstes;
import utilities.DialogBox;

public class CivilityAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<Civility> listeDonnees	= FXCollections.observableArrayList();
	Civility civilitySelected				= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField						txfCivilityLbl;
	@FXML private TextField						txfCivilityLbc;
	@FXML private Label							lblCivilityIdt;
	@FXML private Label							lblMessage;
	@FXML private Label							lblTitre;
	@FXML private TableView<Civility>			tbvDonnees;
	@FXML private TableColumn<Civility, String>	tbcCivilityLbl;
	@FXML private TableColumn<Civility, String>	tbcCivilityLbc;
	@Override
	public void initialize() {
		
		lblTitre.setText("Gestion des Civilités");
		
	    listeDonnees.addAll(selectAllCivility());
	    
		tbcCivilityLbc.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCivilityLbc());
		tbcCivilityLbl.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCivilityLbl());

	    tbvDonnees.setItems(listeDonnees);
	}


	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
	
	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param civility [Civility]	: Civiliy a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(Civility civility) {
		
		//Affichage de l'identifiant de la civilité selectionnée
		lblCivilityIdt.setText(String.valueOf(civility.getCivilityIdt()));
		
		//TODO Completer pour le lbl et lbc
	}
	/**
	 * Méthode permettant de supprimer le contour en erreur
	 * @param textField	[TextField]
	 */
	private void retraitErreurs(TextField textField) {
		/** Initialisation des variables **/
		String classeErreur = "zoneEnErreur";
		/** Retrait de la classe erreur **/
		textField.getStyleClass().remove(classeErreur);
	}
	/**
	 * Méthodes permettant de contrôler les différentes zones de saisie
	 * @return	[boolean]	: indicateur si présence d'erreurs dans les zones
	 */
	private boolean trtControlesZones() {
		return false;
	}
	
	@Override
	public void evtOnMouseClickedBtnModifier() {
	}
	@Override
	public void evtOnMouseClickedBtnAjouter() {
	}
	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		deleteCivility(civilitySelected.getCivilityIdt());
	}
	
	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			civilitySelected = tbvDonnees.getSelectionModel().getSelectedItem();
			if(civilitySelected != null) {
				trtAffichageZones(civilitySelected);
				btnAjouter.setDisable(false);
				btnModifier.setDisable(false);
				btnSupprimer.setDisable(false);
			}
		}
	}
	/**
	 * Méthode permettant de gérer l'activité des boutons
	 * @param ajouter	[boolean]
	 * @param modifier	[boolean]
	 * @param supprimer	[boolean]
	 */
	private void gestionBtn(boolean ajouter, boolean modifier, boolean supprimer) {
	}
}