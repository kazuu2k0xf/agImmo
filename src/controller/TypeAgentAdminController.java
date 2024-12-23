package controller;


import static bdd.TypeAgentBdd.deleteTypeAgent;
import static bdd.TypeAgentBdd.insertTypeAgent;
import static bdd.TypeAgentBdd.selectAllTypeAgent;
import static bdd.TypeAgentBdd.selectNbreTypeAgent;
import static bdd.TypeAgentBdd.updateTypeAgent;
import static utilities.UtilitiesControls.isTextFieldEmpty;

import java.text.ParseException;

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
import model.TypeAgent;
import resources.Cstes;
import utilities.DialogBox;

public class TypeAgentAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<TypeAgent> listeDonnees	= FXCollections.observableArrayList();
	TypeAgent typeAgentSelected				= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField							txfTypeAgentLbl;
	@FXML private TextField							txfTypeAgentLbc;
	@FXML private Label								lblTypeAgentIdt;
	@FXML private Label								lblMessage;
	@FXML private TableView<TypeAgent>				tbvDonnees;
	@FXML private Label								lblTitre;
	@FXML private TableColumn<TypeAgent, String>	tbcTypeAgentLbl;
	@FXML private TableColumn<TypeAgent, String>	tbcTypeAgentLbc;
	@Override
	public void initialize() {

		lblTitre.setText("Gestion des types d'agents");

		listeDonnees.addAll(selectAllTypeAgent());

		tbcTypeAgentLbc.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getTypeAgentLbcProperty());
		tbcTypeAgentLbl.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getTypeAgentLblProperty());

		//Ajout des civilité de la TableView
		tbvDonnees.setItems(listeDonnees);

		lblTypeAgentIdt.setText("");
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

		listeDonnees.addAll(selectAllTypeAgent());

		tbvDonnees.setItems(listeDonnees);

	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param typeAgent [TypeAgent]	: TypeAgent a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(TypeAgent typeAgent) {

		//Affichage de l'identifiant de la civilité selectionnée
		if (typeAgent == null) {

			lblTypeAgentIdt.setText("");
			txfTypeAgentLbl.setText("");
			txfTypeAgentLbc.setText("");
			lblMessage.setText("");
			gestionBtn(true, true,false);

		} else {

			lblTypeAgentIdt.setText(String.valueOf(typeAgent.getTypeAgentIdt()));
			txfTypeAgentLbl.setText(typeAgent.getTypeAgentLbl());
			txfTypeAgentLbc.setText(typeAgent.getTypeAgentLbc());
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
		if (!isTextFieldEmpty(txfTypeAgentLbl) && !isTextFieldEmpty(txfTypeAgentLbc)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void evtOnMouseClickedBtnModifier() {

		if(trtControlesZones()) {

			typeAgentSelected.setTypeAgentLbc(txfTypeAgentLbc.getText());
			typeAgentSelected.setTypeAgentLbl(txfTypeAgentLbl.getText());
			typeAgentSelected.setTypeAgentIdt(Integer.parseInt(lblTypeAgentIdt.getText()));

			updateTypeAgent(typeAgentSelected);
			trtAffichageDonnees();

			typeAgentSelected = null;
			trtAffichageZones(typeAgentSelected);

		}
	}

	@Override
	public void evtOnMouseClickedBtnAjouter() {
		if(trtControlesZones()) {

			//Creation de l'objet 
			TypeAgent typeAgent = new TypeAgent(0, txfTypeAgentLbl.getText() , txfTypeAgentLbc.getText());
			insertTypeAgent(typeAgent);
			trtAffichageDonnees();
			typeAgent = null;
			trtAffichageZones(typeAgent);
		}
	}

	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		if(selectNbreTypeAgent(typeAgentSelected.getTypeAgentIdt()) == 0) {

			//Affichage de la dialogBox lors de la suppression 
			DialogBox dialogBox = new DialogBox("Suppression de la civilité " + typeAgentSelected.getTypeAgentLbl(), "", "Voulez-vous la supprimer", AlertType.CONFIRMATION, ButtonType.CANCEL);
			ButtonType reponse = dialogBox.showDialogConfirmation();

			if(reponse == ButtonType.OK) {
				deleteTypeAgent(typeAgentSelected);
				trtAffichageDonnees();
				typeAgentSelected = null;
				trtAffichageZones(typeAgentSelected);
				gestionBtn(true, true, false);
			}	
		}
	}

	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {

		if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {

			typeAgentSelected = tbvDonnees.getSelectionModel().getSelectedItem();

			if(typeAgentSelected != null) {
				trtAffichageZones(typeAgentSelected);

				if (selectNbreTypeAgent(typeAgentSelected.getTypeAgentIdt()) != 0) {
					gestionBtn(false, true, false);
					lblMessage.setText("Le type d'agent ne peut pas être supprimé, il est utilisé !!");
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
