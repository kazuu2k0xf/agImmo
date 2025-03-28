package controller;

import static bdd.AddressBdd.insertAddress;
import static bdd.AddressBdd.selectOneAdresse;
import static bdd.AddressBdd.selectOneAdresseByKey;
import static bdd.AddressBdd.updateAddress;
import static bdd.CompanyBdd.controleSiret;
import static bdd.CompanyBdd.insertCompany;
import static bdd.CompanyBdd.selectOneCompanyAdminSeat;
import static bdd.CompanyBdd.updateCompany;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;
import static bdd.LegalRegimeBdd.selectAllLegalRegime;
import static bdd.TownBdd.selectAllTown;
import static utilities.UtilitiesControls.isEmailAdress;
import static utilities.UtilitiesControls.isItemNotSelected;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static utilities.UtilitiesControls.isTextFieldNumeric;
import static utilities.UtilitiesControls.validatePhoneNumber;
import static utilities.UtilitiesPassay.generateKey;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Address;
import model.Company;
import model.InfoDetail;
import model.LegalRegime;
import model.Town;
import resources.Cstes;
import utilities.DialogBox;

public class CompanyDefinitionController extends GeneralDefinitionController {
	/** *************************************************************
	 * Déclaration des variables
	 * **************************************************************/
	private boolean validerClicked 					= false;								
	private String  codeAction						= "";
	private ObservableList<LegalRegime> listeLegalRegimes 	= FXCollections.observableArrayList();	
	private ObservableList<Town> 		listeTown			= FXCollections.observableArrayList();	
	private Address address							= null;
	private Company company							= null;
	private Company companySelected 				= null;
	int index										= 0;
	FilteredList<Town> filteredTowns 				;
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private TextField				txfCompanyIdt;
	@FXML private TextField				txfCompanyName;
	@FXML private TextField				txfCompanyTelephone;
	@FXML private TextField				txfCompanyEmail;
	@FXML private TextField				txfCompanyWebSite;
	@FXML private TextField				txfCompanySiren;
	@FXML private TextField				txfCompanySiret;
	@FXML private TextField				txfAddressDeliveryPoint;
	@FXML private TextField				txfAddressNumber;
	@FXML private TextField				txfAddressPortLabel;
	@FXML private TextField				txfAddressNext;
	@FXML private TextField				txfTownPostCode;
	@FXML private TextField				txfCompanyMaps;
	@FXML private TextField				txfSelectTown;
	@FXML private ComboBox<LegalRegime> cbxCompanyLegalRegime;
	@FXML private ComboBox<Town>		cbxAddressTown;
	@FXML private DatePicker			dapCompanyCreationDate;
	@FXML private CheckBox				chkCompanyAdminSeat;
	@FXML private WebView				wbvMaps;	

	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@SuppressWarnings("unused")
	@Override
	public void initialize() {
		/** Remplissage de la combobox sur les Regimes juridiques et initialisation avec la valeur par defaut
		 * definie dans la table informations
		 **/ 
		listeLegalRegimes    = selectAllLegalRegime();
		cbxCompanyLegalRegime.getItems().addAll(listeLegalRegimes);
		/** Remplissage de la combobox sur les villes **/
		listeTown			= selectAllTown();
		cbxAddressTown.getItems().addAll(listeTown);
		txfSelectTown.textProperty().addListener((observable, oldValue, newValue) -> {
			List<Town> filteredTowns = listeTown.stream()
					.filter(town -> town.getTownName().toLowerCase().contains(newValue.toLowerCase())
							|| town.getTownPostCode().contains(newValue))
					.collect(Collectors.toList());
			cbxAddressTown.setItems(FXCollections.observableArrayList(filteredTowns));
			if (!cbxAddressTown.isShowing()) {
				cbxAddressTown.show();
			}
		});
		/** Initialisation des Tooltips **/
		String infoTelephoneString  = "Le téléphone doit avoir le format suivant : XX-XX-XX-XX-XX ";

		txfCompanyTelephone.setTooltip(new Tooltip(infoTelephoneString));

		// ToolTip email
		Tooltip tooltipEmail = new Tooltip("Le mail doit avoir le format suivant : XXXXXXX@YYYY.ZZZ");
		txfCompanyEmail.setTooltip(tooltipEmail);

		// ToolTip SIREN
		Tooltip tooltipSiren = new Tooltip("Le SIREN doit faire 9 de long et ne contenir que des chiffres");
		txfCompanySiren.setTooltip(tooltipSiren);

		// ToolTip SIREN
		Tooltip tooltipSiret = new Tooltip("Le SIRET doit faire 14 de long, ne contenir que des chiffres et contenir le SIREN sur les premiers caracteres");
		txfCompanySiret.setTooltip(tooltipSiret);


		/** Initialisation de la  carte google maps **/	
		/*if (!company.getCompanyMaps().isEmpty()) {
			String urlMaps = Cstes.WEBMAPS;

			urlMaps.replace("{carte}", company.getCompanyMaps());

			System.out.println(urlMaps);

			WebEngine webEngine = wbvMaps.getEngine();
			webEngine.loadContent(urlMaps);
		}
		 */
	}
	@Override
	public void evtOnMouseClickedBtnValider() {
		if(!messageErreur.isEmpty()) {

		} else {
			/**
			 * Contrôle du siège
			 * Il ne peut avoir qu'une agence de type siège dans la Bdd
			 * En création, si le contrôle CheckBox est sélectionné, il faut contrôler la présence d'un siège
			 * En modification, si le contrôle CheckBox est sélectionné et l'agence n'est pas le siège, il faut contrôler la présence d'un siège
			 * En modification, si le contrôle CheckBox est non sélectionné et l'agence est le siège, il faut le signaler
			 */
			messageErreur				= "";
			Boolean majSiege			= false;
			Company companyAdminSeat 	= selectOneCompanyAdminSeat();
			if(codeAction.equals("create")) {
				if(chkCompanyAdminSeat.isSelected()) {
					if(companyAdminSeat!=null) {
						messageErreur  += "Le siège du groupe est déjà défini sur l'agence de " + companyAdminSeat.getCompanyName() + Cstes.CR;
						majSiege		= true;
					}
				}
			} else {
				if(chkCompanyAdminSeat.isSelected() && !company.isCompanyAdminSeat()) {
					if(companyAdminSeat!=null) { 
						messageErreur  += "Le siège du groupe est déjà définition sur l'agence de " + companyAdminSeat.getCompanyName() + Cstes.CR;
						majSiege		= true;
					}
				} else if(!chkCompanyAdminSeat.isSelected() && company.isCompanyAdminSeat()) {
					messageErreur  += "Attention, le groupe n'a plus de siège " + Cstes.CR;
				}
			}
			if(!messageErreur.isEmpty()) {
				DialogBox dialogBox = new DialogBox("Information", "Modification du siège social à confirmer", messageErreur, AlertType.CONFIRMATION, ButtonType.CANCEL);
				ButtonType reponse = dialogBox.showDialogConfirmation();
				if(reponse == ButtonType.OK) {
					if(majSiege) {
						companyAdminSeat.setCompanyAdminSeat(false);
						updateCompany(companyAdminSeat);
					}
				}
			}
			if(codeAction.equals("create")) {
				company = new Company();
				address = new Address();
			}
			/** Traitements de mise à  jour des informations de l'agence **/

			/** Initialisation des variables **/
			int addressIdt 		= 0;
			String key = generateKey();
			/** *************************************************************************************************************
			 *  Mise a jour de l'objet Address avec les donnees de la fenetre
			 ** *************************************************************************************************************/ 
			/** Le traitement se fait selon le code action **/
			if(codeAction.equals("create")) {
				address.setAddressDeliveryPoint(txfAddressDeliveryPoint.getText());
				address.setAddressNumber(txfAddressNumber.getText());
				address.setAddressPortLabel(txfAddressPortLabel.getText());
				address.setAddressNext(txfAddressNext.getText());
				address.setAddressGenerationKey(key);
				
				Town townStringIdt = cbxAddressTown.getSelectionModel().getSelectedItem();
				int townIdt = townStringIdt.getTownIdt();
				address.setAddressTownIdt(townIdt);
				
				insertAddress(address);
			} else {
				address = new Address(
						company.getAdress().getAddressIdt(),
						txfAddressDeliveryPoint.getText(),
					    txfAddressNumber.getText(),
					    txfAddressPortLabel.getText(),
					    txfAddressNext.getText(),
					    cbxAddressTown.getValue().getTownIdt(), 
					    cbxAddressTown.getValue(),               
					    null
					);

						
				


				updateAddress(address);		
				 

			}
			/** *************************************************************************************************************
			 *  Mise a jour de l'objet company avec les donnees de la fenetre
			 ** *************************************************************************************************************/ 
			if(codeAction.equals("create")) {
				addressIdt = selectOneAdresseByKey(key);
				company.setCompanyAddressIdt(addressIdt);
			    company.setCompanyName(txfCompanyName.getText());
			    company.setCompanyTelephone(txfCompanyTelephone.getText());
			    company.setCompanyEmail(txfCompanyEmail.getText());
			    company.setCompanyWebSite(txfCompanyWebSite.getText());
			    company.setCompanyLegalRegime(cbxCompanyLegalRegime.getValue().getLegalRegimeIdt());
			    company.setCompanyCreationDate(dapCompanyCreationDate.getValue());
			    company.setCompanySiren(txfCompanySiren.getText());
			    company.setCompanySiret(txfCompanySiret.getText());
			    company.setCompanyAdminSeat(chkCompanyAdminSeat.isSelected());
			    company.setCompanyMaps(txfCompanyMaps.getText());
			    insertCompany(company);
				 
			} else {

				company = new Company(
					    Integer.parseInt(txfCompanyIdt.getText()),
					    txfCompanyName.getText(),
					    company.getAdress().getAddressIdt(),
					    txfCompanyTelephone.getText(),
					    txfCompanyEmail.getText(),
					    txfCompanyWebSite.getText(),
					    cbxCompanyLegalRegime.getValue().getLegalRegimeIdt(),
					    dapCompanyCreationDate.getValue(),
					    txfCompanySiren.getText(),
					    txfCompanySiret.getText(),
					    chkCompanyAdminSeat.isSelected(),
					    null,
					    null,
					    txfCompanyMaps.getText()
					);

					updateCompany(company);
			}
		}
		this.dialogStage.close();
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de recuperer l'agence a modifier
	 *                en affichant ses informations dans la fenetre. 
	 * @param company	[Company]
	 */
	public void setCompany(Company company) {
		this.company = company;

		int companyIdt = this.company.getCompanyIdt();
		String companyName = this.company.getCompanyName();
		String companyTelephone = this.company.getCompanyTelephone();
		String companyEmail = this.company.getCompanyEmail();
		String companyWebSite = this.company.getCompanyWebSite();
		LegalRegime companyLegalRegime = this.company.getLegalRegime();
		LocalDate companyCreationDate = this.company.getCompanyCreationDate();
		String companySiren = this.company.getCompanySiren();
		String companySiret = this.company.getCompanySiret();
		boolean companyAdminSeat = this.company.isCompanyAdminSeat();
		String companyMaps = this.company.getCompanyMaps();
		String addressPointDeLivraison = this.company.getAdress().getAddressDeliveryPoint();
		String addressNumero = this.company.getAdress().getAddressNumber();
		String addresseLibelle = this.company.getAdress().getAddressPortLabel();
		String addressSuite = this.company.getAdress().getAddressNext();
		Town addressTown = this.company.getAdress().getTown();


		txfCompanyIdt.setText(String.valueOf(companyIdt));
		txfCompanyName.setText(companyName);
		txfCompanyTelephone.setText(companyTelephone);
		txfCompanyEmail.setText(companyEmail);
		txfCompanyWebSite.setText(companyWebSite);
		txfCompanySiren.setText(companySiren);
		txfCompanySiret.setText(companySiret);
		cbxCompanyLegalRegime.setValue(companyLegalRegime);
		dapCompanyCreationDate.setValue(companyCreationDate);
		chkCompanyAdminSeat.setSelected(companyAdminSeat);
		txfCompanyMaps.setText(companyMaps);
		txfAddressDeliveryPoint.setText(addressPointDeLivraison);
		txfAddressNumber.setText(addressNumero);
		txfAddressPortLabel.setText(addresseLibelle);
		txfAddressNext.setText(addressSuite);
		cbxAddressTown.setValue(addressTown);
		
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de definir l'action CRU en cours
	 * @param	codeAction	[String]
	 */
	public void setAction(String codeAction) {
		switch (codeAction) {
		case "create": 
			lblTitre.setText("Creation d'une nouvelle agence");
			break;
		case "update": 
			lblTitre.setText("Modification d'une agence");
			break;
		}
		this.codeAction = codeAction;
	}
	/**
	 * Description 	: Cette Methode renvoie la valeur de l'attribut [validerClicked]
	 *							  permettant a l'ecran appelant de savoir si le bouton [Valider]
	 *							  a ete clique
	 * @return	boolean
	 */
	public boolean isValiderClicked() {
		return validerClicked;
	}    
	/**
	 * Methode 	: evtOnActionCbxAddressTownIdt
	 * Description 	: Cette Methode permet d'afficher dans la zone texte le code postal de la ville
	 */
	@FXML private void evtOnActionCbxAddressTown() {
		Town selectedTown = cbxAddressTown.getSelectionModel().getSelectedItem();
		if (selectedTown != null) {
			txfTownPostCode.setText(selectedTown.getTownPostCode());
		}
	}
}
