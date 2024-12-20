package controller;

import static bdd.FenetresBdd.selectOneFenetre;
import static bdd.InfoEnteteBdd.deleteInfoEntete;
import static bdd.InfoEnteteBdd.insertInfoEntete;
import static bdd.InfoEnteteBdd.selectAllInfoEntete;
import static bdd.InfoEnteteBdd.selectNbreInfoDetail;
import static bdd.InfoEnteteBdd.selectOneInfoEntete;
import static bdd.InfoEnteteBdd.updateInfoEntete;
import static utilities.UtilitiesControls.isTextFieldEmpty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Fenetres;
import model.InfoEntete;
import model.LoaderFXML;
import resources.Cstes;
import utilities.DialogBox;

public class InfoEnteteAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<InfoEntete> listeDonnees					= FXCollections.observableArrayList();
	InfoEntete infoEnteteSelected							= null;
	/** Listes contenant les informations des Spinners **/
	private ObservableList<Integer> 		listeDetailMax	= FXCollections.observableArrayList();	
	/** ValueFactory des différents Spinners **/
	private SpinnerValueFactory<Integer> 	valueFactoryDetailMax 	= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField							txfInfoEnteteKey;
	@FXML private TextField							txfInfoEnteteDescription;
	@FXML private TextField							txfInfoEnteteCbx;
	@FXML private Label								lblInfoEnteteIdt;
	@FXML private Label								lblMessage;
	@FXML private Label								lblTotalLignesDetail;
	@FXML private Label								lblTitre;
	@FXML private Spinner<Integer> 					spiInfoEnteteNbreLigneMax;
	@FXML private TableView<InfoEntete>				tbvDonnees;
	@FXML private TableColumn<InfoEntete, String>	tbcInfoEnteteKey;
	@FXML private TableColumn<InfoEntete, String>	tbcInfoEnteteDescription;
	@FXML private ImageView							imvInfoEnteteDetail;

	@Override
	public void initialize() {
		lblTitre.setText("Gestion des entêtes d'info");

	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param infoEntete [InfoEntete]	: InfoEntete a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(InfoEntete infoEntete) {
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
	private boolean trtControlesZones(String traitement) {
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
	@FXML private void evtOnMouseClickedImvInfoEnteteDetail() {
		Fenetres fenetre	  = selectOneFenetre(Cstes.DETAILINFO);
		if(fenetre!=null) {
			LoaderFXML loaderFxml 					= new LoaderFXML(fenetre);
			Stage primaryStage 	  					= loaderFxml.createLoaderBorderPane();
			InfoDetailAdminController controler 	= loaderFxml.getLoader().getController();
			controler.setDialogStage(primaryStage);
			controler.setInfoEntete(infoEnteteSelected);
			primaryStage.showAndWait();
			int nbreDeLignesDetail = selectNbreInfoDetail(infoEnteteSelected.getInfoEnteteIdt());
			boolean affichage	= (nbreDeLignesDetail>0);
			lblTotalLignesDetail.setText("Nombre de ligne(s) de détail : " + nbreDeLignesDetail);
			lblMessage.setVisible(affichage);
			gestionBtn(true, false, affichage);			
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
