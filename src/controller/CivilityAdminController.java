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
		
		tbcCivilityLbc.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCivilityLbcProperty());
		tbcCivilityLbl.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getCivilityLblProperty());

		//Ajout des civilité de la TableView
		tbvDonnees.setItems(listeDonnees);

		lblCivilityIdt.setText("");
		lblMessage.setText("");
		
		//Initilisation des bouton
		gestionBtn(true, true, false);
	}


	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
		tbvDonnees.getItems().clear();
		listeDonnees.clear();

		listeDonnees.addAll(selectAllCivility());

		tbvDonnees.setItems(listeDonnees);


	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param civility [Civility]	: Civiliy a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(Civility civility) {

		//Affichage de l'identifiant de la civilité selectionnée
		if (civility == null) {
			
			lblCivilityIdt.setText("");
			txfCivilityLbc.setText("");
			txfCivilityLbl.setText("");
			lblMessage.setText("");
			gestionBtn(true, true,false);
			
		} else {
			
			lblCivilityIdt.setText(String.valueOf(civility.getCivilityIdt()));
			txfCivilityLbl.setText(civility.getCivilityLbl());
			txfCivilityLbc.setText(civility.getCivilityLbc());
			lblMessage.setText("");
		}
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
		if (!isTextFieldEmpty(txfCivilityLbl) && !isTextFieldEmpty(txfCivilityLbc)) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public void evtOnMouseClickedBtnModifier() {
		
		if(trtControlesZones()) {
			
			civilitySelected.setCivilityLbc(txfCivilityLbc.getText());
			civilitySelected.setCivilityLbl(txfCivilityLbl.getText());
			civilitySelected.setCivilityIdt(Integer.parseInt(lblCivilityIdt.getText()));
			
			updateCivility(civilitySelected);
			trtAffichageDonnees();
			
			civilitySelected = null;
			trtAffichageZones(civilitySelected);
			
		}

	}
	@Override
	public void evtOnMouseClickedBtnAjouter() {
		
		 if(trtControlesZones()) {
			 
			//Creation de l'objet 
			Civility civilite = new Civility(0, txfCivilityLbl.getText() , txfCivilityLbc.getText());
			insertCivility(civilite);			
			trtAffichageDonnees();
			civilite = null;
			trtAffichageZones(civilite);
		}
	}
	
	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		if(selectNbreCivility(civilitySelected.getCivilityIdt()) == 0) {

			//Affichage de la dialogBox lors de la suppression 
			DialogBox dialogBox = new DialogBox("Suppression de la civilité " + civilitySelected.getCivilityLbl(), "", "Voulez-vous la supprimer", AlertType.CONFIRMATION, ButtonType.CANCEL);
			ButtonType reponse = dialogBox.showDialogConfirmation();

			if(reponse == ButtonType.OK) {
				deleteCivility(civilitySelected);
				trtAffichageDonnees();
				civilitySelected = null;
				trtAffichageZones(civilitySelected);
				gestionBtn(true, true, false);
			}	
		}
	}

	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {

		if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {

			civilitySelected = tbvDonnees.getSelectionModel().getSelectedItem();

			if(civilitySelected != null) {
				trtAffichageZones(civilitySelected);

				if (selectNbreCivility(civilitySelected.getCivilityIdt()) != 0) {
					gestionBtn(true, true, false);
					lblMessage.setText("le type d'agent ne peut pas être supprimé, il est utilisé !!");
				} else {
					gestionBtn(true, true, true);
				}
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
		btnAjouter.setDisable(!ajouter);
		btnModifier.setDisable(!modifier);
		btnSupprimer.setDisable(!supprimer);
	}


}