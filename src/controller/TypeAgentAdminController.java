package controller;


import static bdd.TypeAgentBdd.deleteTypeAgent;
import static bdd.TypeAgentBdd.insertTypeAgent;
import static bdd.TypeAgentBdd.selectAllTypeAgent;
import static bdd.TypeAgentBdd.selectNbreTypeAgent;
import static bdd.TypeAgentBdd.updateTypeAgent;
import static utilities.UtilitiesControls.isTextFieldEmpty;

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

	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param typeAgent [TypeAgent]	: TypeAgent a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(TypeAgent typeAgent) {
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
	private boolean trtControlesZones() {
	}
	@Override
	public void evtOnMouseClickedBtnModifier() {
	}

	@Override
	public void evtOnMouseClickedBtnAjouter() {
	}

	@Override
	public void evtOnMouseClickedBtnSupprimer() {
	}

	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
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
