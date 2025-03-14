package controller;

import static bdd.InfoDetailBdd.deleteInfoDetail;
import static bdd.InfoDetailBdd.insertInfoDetail;
import static bdd.InfoDetailBdd.selectAllInfoDetail;
import static bdd.InfoDetailBdd.updateInfoDetail;
import static bdd.InfoEnteteBdd.selectOneInfoEntete;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static utilities.UtilitiesControls.isTextFieldNumeric;
import static utilities.UtilitiesControls.isTextFieldNumericDouble;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.InfoDetail;
import model.InfoEntete;
import resources.Cstes;
import utilities.DialogBox;

public class InfoDetailAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<InfoDetail> listeDonnees						= FXCollections.observableArrayList();
	InfoDetail infoDetailSelected								= null;
	InfoEntete infoEntete										= null;
	/** Listes contenant les informations des Spinners **/
	private ObservableList<Integer> 		listeOrdreTri		= FXCollections.observableArrayList();	
	/** ValueFactory des différents Spinners **/
	private SpinnerValueFactory<Integer> valueFactoryOrdreTri 	= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField							txfInfoDetailDescription;
	@FXML private TextField							txfInfoDetailLbl;
	@FXML private TextField							txfInfoDetailLbc;
	@FXML private TextField							txfInfoDetailValueInt;
	@FXML private TextField							txfInfoDetailValueDouble;
	@FXML private Spinner<Integer> 					spiInfoDetailOrdreTri;	
	@FXML private Label								lblInfoDetailIdentifiant;
	@FXML private Label								lblInfoDetailEnteteIdt;
	@FXML private Label								lblInfoDetailKey;
	@FXML private CheckBox							chkInfoDetailDefaultValue;
	@FXML private TableView<InfoDetail>				tbvDonnees;
	@FXML private TableColumn<InfoDetail, String>	tbcInfoDetailDescription;
	@FXML private Button							btnAnnuler;


	@Override
	public void initialize() {
		lblTitre.setText("Gestion des détails");
		/** Raz des zones de saisie **/
		trtAffichageZones(null);
		/** Définition des colonnes **/
		tbcInfoDetailDescription.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getInfoDetailDescriptionProperty());
		gestionBtn(true, false, false);
	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
		listeDonnees.clear();
		tbvDonnees.getItems().clear();
		tbvDonnees.refresh();
		/** Valeur par defaut si la tableViews est vide **/
		tbvDonnees.setPlaceholder(new Label("Il n'y a pas de détail dans la base ..."));
		listeDonnees	= selectAllInfoDetail(infoEntete.getInfoEnteteKey());
		tbvDonnees.setItems(listeDonnees);
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
	 * Méthode permettant de gérer les zones de saisie 
	 * @param infoEntete [InfoEntete]	: InfoEntete a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(InfoDetail infoDetail) {
		if(infoDetail!=null) {
			lblInfoDetailIdentifiant.setText(Integer.toString(infoDetail.getInfoDetailIdt()));
			lblInfoDetailEnteteIdt.setText(Integer.toString(infoDetail.getInfoEnteteIdt()));
			lblInfoDetailKey.setText(Integer.toString(infoDetail.getInfoDetailKeyIdt()));
			txfInfoDetailDescription.setText(infoDetail.getInfoDetailDescription());
			txfInfoDetailLbl.setText(infoDetail.getInfoDetailLbl());
			txfInfoDetailLbc.setText(infoDetail.getInfoDetailLbc());
			txfInfoDetailValueInt.setText(Integer.toString(infoDetail.getInfoDetailValueInt()));
			txfInfoDetailValueDouble.setText(Double.toString(infoDetail.getInfoDetailValueDouble()));
			valueFactoryOrdreTri.setValue(infoDetail.getInfoDetailTri());
			spiInfoDetailOrdreTri.setValueFactory(valueFactoryOrdreTri);
			chkInfoDetailDefaultValue.setSelected(infoDetail.isInfoDetailDefault());
			index = 0;
			for(InfoDetail infoDetailTbv :listeDonnees) {
				if(infoDetailTbv.getInfoDetailIdt()==infoDetail.getInfoDetailIdt())break;
				index++;
			}
			tbvDonnees.getSelectionModel().select(index);
		} else {
			lblInfoDetailIdentifiant.setText("");
			lblInfoDetailEnteteIdt.setText("");
			lblInfoDetailKey.setText("");
			txfInfoDetailDescription.setText("");
			txfInfoDetailLbl.setText("");
			txfInfoDetailLbc.setText("");
			txfInfoDetailValueInt.setText("0");
			txfInfoDetailValueDouble.setText("0");
			chkInfoDetailDefaultValue.setSelected(false);
		}
		retraitErreurs(txfInfoDetailDescription);
		retraitErreurs(txfInfoDetailLbl);
		retraitErreurs(txfInfoDetailLbc);
		retraitErreurs(txfInfoDetailValueInt);
		retraitErreurs(txfInfoDetailValueDouble);
	}	
	/**
	 * Méthodes permettant de contrôler les différentes zones de saisie
	 * @return	[boolean]	: indicateur si présence d'erreurs dans les zones
	 */
	private boolean trtControlesZones() {
		messageErreur = "";
		if(isTextFieldEmpty(txfInfoDetailDescription)) {
			messageErreur = "La description de l'entête est obligatoire" + Cstes.CR;
		} 
		if(isTextFieldEmpty(txfInfoDetailLbl)) messageErreur += "Le libellé long est obligatoire" + Cstes.CR;
		if(!isTextFieldNumeric(txfInfoDetailValueInt)) messageErreur += "La valeur (int) doit être un nombre" + Cstes.CR;
		if(!isTextFieldNumericDouble(txfInfoDetailValueDouble)) messageErreur += "La valeur (double) doit être un nombre" + Cstes.CR;
		if(!messageErreur.isBlank()) {
			DialogBox dialogBox = new DialogBox("Erreur(s) de saisie", "Les informations suivantes sont incorrectes", messageErreur, AlertType.ERROR, null);
			dialogBox.showDialogError();
		} 
		boolean ctrlOk = (messageErreur.isBlank());
		return ctrlOk;
	}
	@Override
	public void evtOnMouseClickedBtnModifier() {
		if(trtControlesZones()) {
			infoDetailSelected.setInfoDetailDescription(txfInfoDetailDescription.getText());
			infoDetailSelected.setInfoDetailLbl(txfInfoDetailLbl.getText());
			infoDetailSelected.setInfoDetailLbc(txfInfoDetailLbc.getText());
			infoDetailSelected.setInfoDetailValueInt(Integer.parseInt(txfInfoDetailValueInt.getText()));
			infoDetailSelected.setInfoDetailValueDouble(Double.parseDouble(txfInfoDetailValueDouble.getText()));
			infoDetailSelected.setInfoDetailTri(spiInfoDetailOrdreTri.getValue());
			infoDetailSelected.setInfoDetailDefault(chkInfoDetailDefaultValue.isSelected());
			updateInfoDetail(infoDetailSelected);
			trtAffichageDonnees();
			trtAffichageZones(null);
			gestionBtn(true, false, false);
		}
	}
	@Override
	public void evtOnMouseClickedBtnAjouter() {
		if(trtControlesZones()) {
			int valeurMax = 0; 
			for(InfoDetail infoDetailListe : listeDonnees) {
				if(infoDetailListe.getInfoDetailTri()>valeurMax)valeurMax=infoDetailListe.getInfoDetailTri();
			}
			valeurMax++;
			int 	 infoDetailIdt			= 0;
			int 	 infoEnteteIdt			= infoEntete.getInfoEnteteIdt();		
			int 	 infoDetailKeyIdt		= valeurMax;
			String  infoDetailDescription	= txfInfoDetailDescription.getText();
			String  infoDetailLbl			= txfInfoDetailLbl.getText();
			String  infoDetailLbc			= txfInfoDetailLbc.getText();
			int	 infoDetailValueInt			= Integer.parseInt(txfInfoDetailValueInt.getText());
			double	 infoDetailValueDouble	= Double.parseDouble(txfInfoDetailValueDouble.getText());
			int 	 infoDetailTri			= valeurMax;
			boolean infoDetailDefault		= chkInfoDetailDefaultValue.isSelected();
			InfoEntete		infoEntete		= selectOneInfoEntete(infoEnteteIdt);
			infoDetailSelected				= new InfoDetail(infoDetailIdt, infoEnteteIdt, infoDetailKeyIdt, infoDetailDescription, infoDetailLbl, infoDetailLbc, infoDetailValueInt, infoDetailValueDouble, infoDetailTri, infoDetailDefault, infoEntete);
			insertInfoDetail(infoDetailSelected);		
			trtAffichageDonnees();
			/** Gestion du Spinner **/
			trtSpinner();
			trtAffichageZones(null);
			gestionBtn(true, false, false);			
		}
	}
	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		DialogBox dialogBox = new DialogBox("Confirmation de la suppression", "L'entête suivante va être supprimé", infoDetailSelected.getInfoDetailDescription(), AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			deleteInfoDetail(infoDetailSelected);
			trtAffichageDonnees();
			/** Gestion du Spinner **/
			trtSpinner();
		}		
		trtAffichageZones(null);
		gestionBtn(true, false, false);
	}
	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			infoDetailSelected	= tbvDonnees.getSelectionModel().getSelectedItem();
			if(infoDetailSelected!=null) {
				gestionBtn(false, true, true);
				trtAffichageZones(infoDetailSelected);
			}
		}
	}
	@FXML private void evtOnMouseClickedBtnAnnuler() {
		dialogStage.close();
	}
	/**
	 * Méthode recevant l'entete à modifier
	 * @param infoEntete	[InfoEntete]
	 */
	public void setInfoEntete(InfoEntete infoEntete) {
		lblTitre.setText(infoEntete.getInfoEnteteDescription());
		this.infoEntete	= infoEntete;
		/** Affichage des données **/
		trtAffichageDonnees();
		/** Gestion du Spinner **/
		trtSpinner();
	}
	/**
	 * Méthode permettant de mettre à jour le contrôle Spinner selon l'action ( insérer / supprimer )
	 */
	private void trtSpinner() {
		listeOrdreTri.clear();
		int ordreTriMax	= tbvDonnees.getItems().size() + 1;
		for(int i=1;i<=ordreTriMax;i++) listeOrdreTri.add(i);
		valueFactoryOrdreTri 	= new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(listeOrdreTri);
		valueFactoryOrdreTri.setValue(ordreTriMax);
		spiInfoDetailOrdreTri.setValueFactory(valueFactoryOrdreTri);
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
