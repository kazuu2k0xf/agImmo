package controller;

import static bdd.CivilityBdd.selectAllCivility;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;
import static bdd.TypeAgentBdd.selectAllTypeAgent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import bdd.AgentBdd;
import bdd.InfoDetailBdd;
import interfaces.GestionCbxInfos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Agent;
import model.Civility;
import model.InfoDetail;
import model.TypeAgent;
import resources.Cstes;
import utilities.DialogBox;
import utilities.UtilitiesControls;

/** ***********************************************************************************************
 * CLASSE : AgentDefinitionController
 * ************************************************************************************************
 * Cette classe est la classe de lancement de l'application
 * Elle hérite de la Classe Application qui est la classe Abstraite à implémenter pour une application 
 * JavaFX..  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Agent
 */
public class AgentDefinitionController extends GeneralDefinitionController implements GestionCbxInfos{
	/** *************************************************************
	 * Déclaration des variables
	 * **************************************************************/
	final FileChooser fileChooser 						= new FileChooser();	/** Objet de sélection d'un dossier **/
	Agent agent 			  	  						= null;
	private ObservableList<TypeAgent> listeTypeAgent	= FXCollections.observableArrayList(); 
	private ObservableList<Civility>  listeCivility		= FXCollections.observableArrayList(); 
	private boolean validerClicked 						= false;
	InfoDetail dossierPortraits 						= InfoDetailBdd.selectOneInfoDetailDescription(Cstes.DOSSIERS, Cstes.DOSSIERPORTRAITS);
	private boolean valid 								= true;
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private TextField txfAgentNom;
	@FXML private TextField txfAgentPrenom;
	@FXML private TextField txfAgentTelephone;
	@FXML private TextField txfAgentMobile;
	@FXML private TextField txfAgentEmail;
	@FXML private TextField txfAgentLogin;
	@FXML private ComboBox<Civility> cbxCivilite;
	@FXML private ComboBox<TypeAgent> cbxTypeAgent;
	@FXML private PasswordField pwfAgentPwd;
	@FXML private PasswordField pwfAgentPwdConfirme;
	@FXML private Button btnAgentImage;
	@FXML private Label LblAgentImage;
	@FXML private Label lblAgentDossier;
	@FXML private Label lblTitre;
	@FXML private Button btnValider;
	@FXML private Button btnAnnuler;
	@FXML private Tooltip tooltipTelephone;
	@FXML private Tooltip tooltipEmail;
	@FXML private Tooltip tooltipPwd;
	@FXML private TextArea txaErreur;


	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@Override
	@FXML public void initialize() {
		/** Initialisation du titre **/

		lblTitre.setText("Modification d'un Agent");
		/** Dossier des portraits **/
		/** Initialisation des tooltip sur les contrôles ayant un format particulier **/
		// ToolTip Pour le telephone
		Tooltip tooltipTelephone = new Tooltip("Le N° doit avoir le format suivant : XX-XX-XX-XX-XX");

		txfAgentMobile.setTooltip(tooltipTelephone);
		txfAgentTelephone.setTooltip(tooltipTelephone);

		// ToolTip email
		Tooltip tooltipEmail = new Tooltip("Le mail doit avoir le format suivant : XXXXXXX@YYYY.ZZZ");
		txfAgentEmail.setTooltip(tooltipEmail);

		// ToolTip mots de passe
		Tooltip tooltipPwd = new Tooltip("Le mot de passe doit contenir au moins : " + Cstes.CR 
				+ "- Un chiffre entre 0 et 9" + Cstes.CR
				+ "- Une lettre en minuscule" + Cstes.CR
				+ "- Une lettre en majuscule" + Cstes.CR 
				+ "- Un caractère spécial" + Cstes.CR
				+ "- Une longueur entre 8 et 20 caractères");
		pwfAgentPwd.setTooltip(tooltipPwd);


		/** Un Tooltip est un élément de l'interface utilisée pour afficher des informations supplémentaires pour un contrôle
		 *  lorsque vous déplacez la souris sur toute la surface du composant.
		 **/

		//Ajout de l'icones sur les ToolTip
		Image image 				= new Image(getClass().getResourceAsStream("/images/icones/information.jpg"));
		ImageView imageView 		= new ImageView(image);
		imageView.setFitWidth(20); 
		imageView.setFitHeight(20);

		tooltipTelephone.setGraphic(imageView);
		tooltipEmail.setGraphic(imageView);
		tooltipPwd.setGraphic(imageView);


		/** Rajout d'un évènement sur le bouton btnAgentImage pour afficher l'explorateur Windows **/
		btnAgentImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Sélectionnez un dossier");
				/** Dossier racine **/
				fileChooser.setInitialDirectory(new File(dossierPortraits.getInfoDetailLbl()));
				File file = fileChooser.showOpenDialog(dialogStage);
				if (file != null) {
					LblAgentImage.setText(file.getName());
					lblAgentDossier.setText("Dans le dossier : " + file.getParent() + "\\");
				}
			}
		});
		/** Le login est généré automatiquement avec le format suivant :
		 *    première lettre du prénom en minuscule
		 *  + nom en minuscule. s'il y a des espaces dans le nom, ils sont supprimés.
		 * **/
		/** Rajout de listener pour gérer les changements sur le nom **/
		txfAgentNom.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
			}
		});
		/** Rajout de listener pour gérer les changements sur le prénom **/
		txfAgentPrenom.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
			}
		});
		/** Initialisation des combobox **/

		ObservableList<Civility> testCivility = selectAllCivility();
		listeCivility.addAll(testCivility);
		System.out.println("Liste Civilities: " + listeCivility);

		ObservableList<TypeAgent> testTypeAgent = selectAllTypeAgent();
		listeTypeAgent.addAll(testTypeAgent);
		System.out.println("Liste TypeAgents: " + listeTypeAgent);

		cbxCivilite.setItems(listeCivility);
		cbxTypeAgent.setItems(listeTypeAgent);

	}


	/**
	 * Méthode permettant de recevoir un agent en paramètre de la fenêtre appelante
	 * @param agent	[Agent] : objet Agent correspondant à l'agent connecté.
	 */
	public void setAgent(Agent agent) {
		index	= 0;
		this.agent = agent;

		//Récupération des info de l'agent
		String agentNom = this.agent.getPersonName();
		String agentPrenom = this.agent.getPersonFirstName();
		String agentMobile = this.agent.getPersonMobile(); 
		String agentPhone = this.agent.getPersonPhone();
		String agentEmail = this.agent.getPersonEmail();
		int agentCivility = this.agent.getPersonCivility();
		//Civility civility = selectOneCivility(agentCivility);
		int agentType = this.agent.getAgentType();
		//TypeAgent typeAgent = selectOneTypeAgent(agentType);
		String agentLogin = this.agent.getAgentLogin();
		String agentPwd = this.agent.getAgentPwd();

		//Affichage des TextField
		txfAgentNom.setText(agentNom);
		txfAgentPrenom.setText(agentPrenom);
		txfAgentMobile.setText(agentMobile);
		txfAgentTelephone.setText(agentPhone);
		txfAgentLogin.setText(agentLogin);
		txfAgentEmail.setText(agentEmail);
		pwfAgentPwd.setText(agentPwd);
		LblAgentImage.setText(agent.getAgentImage());

		//Affichage du chemins de la photo
		InfoDetail list  = InfoDetailBdd.selectOneInfoDetailDescription(Cstes.DOSSIERS, Cstes.DOSSIERPORTRAITS);

		lblAgentDossier.setText("Dans le dossier : " + list.getInfoDetailLbl());

		//Sélection ComboBox
		for (Civility item : listeCivility) {
			if (item.getCivilityIdt() == agentCivility) {
				cbxCivilite.setValue(item);  
				break;
			}
		}

		for (TypeAgent item : listeTypeAgent) {
			if (item.getTypeAgentIdt() == agentType) {
				cbxTypeAgent.setValue(item);  
				break;
			}
		}
	}




	/**
	 * Méthode contrôlant si au moins une information de l'agent a été modifié
	 * @return	[boolean]	: valeur de retour
	 */
	private boolean traitementControleModification() {
		/** Initialisation des variables **/
		boolean	objetModifier	= false;
		/** Traitements de contrôles **/
		int     modification	= 0;
		modification += (cbxCivilite.getSelectionModel().getSelectedItem().getCivilityIdt()!=agent.getPersonCivility())? 1 : 0;
		modification += (!txfAgentNom.getText().equals(agent.getPersonName()))? 1:0;
		modification += (!txfAgentPrenom.getText().equals(agent.getPersonFirstName()))? 1:0;
		modification += (!txfAgentMobile.getText().equals(agent.getPersonMobile()))? 1:0;
		modification += (!txfAgentTelephone.getText().equals(agent.getPersonPhone()))? 1:0;
		modification += (!txfAgentEmail.getText().equals(agent.getPersonEmail()))? 1:0;
		modification += (cbxTypeAgent.getSelectionModel().getSelectedItem().getTypeAgentIdt()!=agent.getAgentType())? 1:0;
		modification += (!txfAgentLogin.getText().equals(agent.getAgentLogin()))? 1:0;
		modification += (!pwfAgentPwd.getText().equals(agent.getAgentPwd()))? 1:0;
		modification += (!LblAgentImage.getText().equals(agent.getAgentImage()))? 1:0;
		objetModifier = (modification>0);
		return objetModifier;
	}
	/**
	 * Méthode permettant à la fenêtre appelante de savoir si le bouton Valider à été sélectionné.
	 * @return	[boolean]
	 */
	public boolean isValiderClicked() {
		return validerClicked;
	}    
	/**
	 * Cette méthode permet de générer les cartes de visite d'un agent
	 */
	@FXML private void evtOnMouseClickedImvCartesVisite() {
		/** Initialisation des variables **/
		boolean insertionNom 		= false;
		boolean insertionPoste 		= false;
		boolean insertionPortable 	= false;
		boolean insertionEmail 		= false;
		String  path				= "";
		/** Recherche du dossier contenant les modèles word et du dossier des documents **/
		InfoDetail dossierModeleWord 	= selectOneInfoDetailDescription(Cstes.DOSSIERS,Cstes.DOSSIERMODELEWORD);
		InfoDetail dossierDocuments 	= selectOneInfoDetailDescription(Cstes.DOSSIERS,Cstes.DOSSIERDOCUMENTS);	
		if(dossierModeleWord!=null && dossierDocuments!=null) {
			XWPFDocument doc = null;
			try {
				path								= dossierModeleWord.getInfoDetailLbl();
				if(!path.endsWith("/")) path	  	= path + "/";
				doc 								= new XWPFDocument(OPCPackage.open(path + "CarteDeVisite.docx"));
				XWPFWordExtractor we 				= new XWPFWordExtractor(doc);
				List<XWPFTable> tables 				= doc.getTables();
				for (XWPFTable table: tables){
					for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++)
					{
						insertionNom		= (rowIndex==2 || ((rowIndex-2)%7==0));
						insertionPoste		= (rowIndex==3 || ((rowIndex-3)%7==0));
						insertionPortable	= (rowIndex==4 || ((rowIndex-4)%7==0));
						insertionEmail		= (rowIndex==5 || ((rowIndex-5)%7==0));
						XWPFTableRow row 	= table.getRow(rowIndex);
						int numberOfCell 	= row.getTableCells().size();
						for (int colIndex = 0; colIndex < numberOfCell; colIndex++){
							XWPFTableCell cell 		= row.getCell(colIndex);
							if(insertionNom) {
								cell.setText(agent.getPersonName() + " " + agent.getPersonFirstName());
							} else if(insertionPoste) {
								cell.setText(cbxTypeAgent.getSelectionModel().getSelectedItem().getTypeAgentLbl());
							} else if(insertionPortable) {
								cell.setText(agent.getPersonMobile());
							} else if(insertionEmail){
								cell.setText(agent.getPersonEmail());
							}
						}
					}
				}
				try {
					path 					  		  = dossierDocuments.getInfoDetailLbl() + Cstes.CARTESVISITES;
					if(!path.endsWith("/")) path	  = path + "/";
					FileOutputStream fileOutputStream = new FileOutputStream(path+ "CarteDeVisite_" + agent.getPersonName() + "_" + agent.getPersonFirstName() + ".docx"); 
					doc.write(fileOutputStream);
					fileOutputStream.close();
					we.close();
					doc.close();
					/** Dialog Box informant de la fin de l'impression **/
					DialogBox dialogBox = new DialogBox("Impression des cartes de visites", "Impression terminée", "", AlertType.INFORMATION, null);
					dialogBox.showDialogError();
				} catch (IOException e) {
					System.out.println("Erreur lors de la sauvegarde du fichier Word - génénration des cartes de visite : " + e);
				}
			} catch (InvalidFormatException | IOException e) {
				System.out.println("Erreur dans la création du fichier Word - génénration des cartes de visite : " + e);
			}
		}
	}

	/**
	 * Méthode effectuant les différents contrôles de saisie sur les zones obligatoires et sur les formats de saisie
	 * S'il n'y a pas d'erreur, la méthode met à jour l'agent
	 * Cette méthode est créée par héritage
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnValider() {
		/** Initialisation de la variable qui contiendra les messgaes d'erreurs **/
		messageErreur = "";



		/** Contrôle des zones obligatoires et des formats **/

		//Contrôle du champ Nom
		if (UtilitiesControls.isTextFieldEmpty(txfAgentNom)) {
			messageErreur += "Le nom est obligatoire \n";
			DialogBox dialogbox = new DialogBox("Erreur", "Champ obligatoire manquant", messageErreur, AlertType.ERROR, null);
			dialogbox.showDialogError();
		}

		//Contrôle du champ prénom
		if (UtilitiesControls.isTextFieldEmpty(txfAgentPrenom)) {
			messageErreur += "Le prénom est obligatoire \n";
			DialogBox dialogbox = new DialogBox("Erreur", "Champ obligatoire manquant", messageErreur, AlertType.ERROR, null);
			dialogbox.showDialogError();
		}

		//Contrôle du champ mobile
		if (!UtilitiesControls.validatePhoneNumber(txfAgentMobile)) {
			messageErreur += "Le téléphone est obligatoire ou ne respecte pas le format requis";
			DialogBox dialogbox = new DialogBox("Erreur", "Champ invalide", messageErreur, AlertType.ERROR, null);
			dialogbox.showDialogError();
		}

		//Contrôle du champ telephone
		if (!UtilitiesControls.validatePhoneNumber(txfAgentTelephone)) {
			messageErreur += "Le portable est obligatoire ou ne respecte pas le format requis";
			DialogBox dialogbox = new DialogBox("Erreur", "Champ invalide", messageErreur, AlertType.ERROR, null);
			dialogbox.showDialogError();
		}

		//Contrôle du champ email
		if (!UtilitiesControls.isEmailAdress(txfAgentEmail)) {
			messageErreur += "L'adresse email est obligatoire ou ne respecte pas le format requis";
			DialogBox dialogbox = new DialogBox("Erreur", "Champ invalide", messageErreur, AlertType.ERROR, null);
			dialogbox.showDialogError();
		}

		//Contrôle du champ mots de passe
		if (!UtilitiesControls.validatePwd(pwfAgentPwd)) {
			messageErreur += "Le mot de passe est obligatoire ou ne respecte pas le format requis";
			DialogBox dialogbox = new DialogBox("Erreur", "Champ invalide", messageErreur, AlertType.ERROR, null);
			dialogbox.showDialogError();
		}

		//Contrôle du champ confirmation de mots de passe
		if (!pwfAgentPwdConfirme.getText().equals(pwfAgentPwd.getText())) {
			messageErreur += "La confirmation du mot de passe ne correspond pas";
			DialogBox dialogbox = new DialogBox("Erreur", "Champ invalide", messageErreur, AlertType.ERROR, null);
			dialogbox.showDialogError();
		}




		/** S'il y a une erreur, on l'affiche dans la textArea **/
		if(!messageErreur.isEmpty()) {

		} else {
			/** Contrôle s'il y a eu une modification pour mémorisation , dans le cas contraire on ne fait rien **/
			if(traitementControleModification()) {

				/** Modification de l'agent **/
				int agentIdt = agent.getPersonIdt();
				String agentNom = txfAgentNom.getText();
				String agentPrenom = txfAgentPrenom.getText();
				String agentMobile = txfAgentMobile.getText();
				String agentTelephone = txfAgentTelephone.getText();
				String agentEmail = txfAgentEmail.getText();
				int agentCivility = agent.getPersonCivility();
				Civility agentCivilite = cbxCivilite.getValue();
				int agentTypeInt = agent.getAgentType();
				TypeAgent agentType = cbxTypeAgent.getValue();
				String agentLogin = txfAgentLogin.getText();
				String agentPwd = pwfAgentPwd.getText();
				String agentImage = LblAgentImage.getText();

				// Création d'un nouvel objet Agent
				Agent agent = new Agent(agentIdt, agentNom, agentPrenom, agentMobile, agentTelephone, agentEmail, agentCivility, agentCivilite, agentTypeInt, agentType, agentLogin, agentPwd, agentImage
						);

				// Mise à jour des informations de l'agent dans la base de données
				AgentBdd.updateAgent(agent);

				validerClicked = true;

			}
			/** Sortie de la fenêtre **/
			dialogStage.close();
		}
	}

}


