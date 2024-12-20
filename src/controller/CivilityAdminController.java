package controller;

import static bdd.CivilityBdd.deleteCivility;
import static bdd.CivilityBdd.selectAllCivility;
import static bdd.CivilityBdd.updateCivility;
import static bdd.CivilityBdd.selectNbreCivility;
import static bdd.CivilityBdd.insertCivility;
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
import model.Civility;
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
	@FXML private TableView<Civility>			tbvDonnees;
	@FXML private TableColumn<Civility, String>	tbcCivilityLbl;
	@FXML private TableColumn<Civility, String>	tbcCivilityLbc;
	@Override
	public void initialize() {

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