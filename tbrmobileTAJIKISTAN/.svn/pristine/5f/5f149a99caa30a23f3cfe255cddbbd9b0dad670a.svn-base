package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class BaselineTreatmentForm extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	//private static final int PREGNANCY_INDEX = 6;
	//not needed private static final int STREPTO_INDEX = 10;
	/*private static final int FDC_TAB_INDEX = 12;
	private static final int MED_FORM_INDEX = 9;

	private static final int permanent_item_index = 12;*/
	private static final int FDC_TAB_INDEX = 13;
	private static final int MED_FORM_INDEX = 10;

	private static final int permanent_item_index = 13;
	

	//private static final String SIBLING_text = "Brother/Sister";
	//private static final String SPOUSE_text = "Husband/Wife";

	TextField					gpIdField;
	DateField					dateField;
	// TextField labField;

	TextField					heightField;
	TextField					weightField;
	// TextField chwIdField;
	// StringItem pregnantString;
	StringItem					recomendedRegimenString;
	StringItem					fdcTabletString;
	StringItem					medicationFormString;

	// ChoiceGroup pregnantGroup;
	ChoiceGroup					phaseGroup;
	ChoiceGroup					patientTypeGroup;
	ChoiceGroup					patientCategoryGroup;

	ChoiceGroup 				diseaseSiteGroup;
	ChoiceGroup					medicationFormGroup;

	ChoiceGroup					regimenGroup;
	ChoiceGroup					regimenTypeGroup;
	ChoiceGroup					fdcTabletGroup;
	//ChoiceGroup					RHZ;
	//ChoiceGroup					RH;
	ChoiceGroup					R;
	ChoiceGroup					H;
	ChoiceGroup					Z;
	ChoiceGroup					E;

	ChoiceGroup					streptoGroup;
	//// not needed StringItem					streptoString;
	// TextField tsNameField;
	// ChoiceGroup tsRelationshipGroup;
	// TextField tsOtherRelationshipField;
	// TextField tsPhoneField;

	Command						cmdOK;
	Command						cmdBack;

	private Hashtable			queryData;
	private String				patientId;

	// private String gender;
	
	public BaselineTreatmentForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		queryData = null;
		patientId = null;
	//	chwIdField= null;          
		//labField = null;            

		gpIdField = null;
		dateField = null;
		weightField = null;
		heightField = null;
		//pregnantGroup = null;
		//pregnantGroup = null;
		phaseGroup = null;
		patientTypeGroup = null;
		patientCategoryGroup = null;
		//diseaseSiteGroup = null;
		regimenGroup = null;
		regimenTypeGroup = null;
		fdcTabletGroup = null;
		streptoGroup = null;
		// not needed streptoString = null;
		recomendedRegimenString = null;
		medicationFormString = null;
		medicationFormGroup = null;
		//tsNameField=null;
		//tsRelationshipGroup=null;
		//tsOtherRelationshipField=null;
		//tsPhoneField=null;
		
//		RHZ = null; 
//		RH = null;  
		R = null;   
		H = null;   
		Z = null;   
		E = null;   
		     
		cmdOK = null;
		cmdBack = null;
	}
	
	public Hashtable getQueryData() {
		return queryData;
	}

	public void setQueryData(Hashtable queryData) {
		this.queryData = queryData;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void commandAction(Command c, Displayable d) {
		if(c==cmdOK) {
			if(validate()) {
				endTime = DateTimeUtil.getTime();
				String request = createRequestPayload();
				System.out.println(request);
				//Hashtable model = tbrMidlet.sendToServer(request);
				
				//if(model!=null) {
					String status = XmlStrings.SUCCESS;//(String)model.get("status");
					
					if(status.equals(XmlStrings.SUCCESS)) {
						tbrMidlet.showAlert(SuccessMsg.SAVE_SUCCESS,null);
					}
					//else if(status.equals(XmlStrings.ERROR)) {
					//	tbrMidlet.showAlert((String)model.get("msg"),null);
					//}
				//}
			}
		}
		else if(c==cmdBack) {
			deleteAll();
			removeCommand(cmdOK);
			removeCommand(cmdBack);
			tbrMidlet.setDisplay(prevDisplayable);
		}
	}
	
	public void init() {
		
		String data = "";
		//gender = (String)queryData.get("gender");
		//if(queryData!=null) {
			data += "Patient ID: " + patientId;
			//data += "\nGender: " + gender;
			data += "\nBase Smear: " + "NEGATIVE"; //(String)queryData.get("basesmear");
			data += "\nCXR: " + "Suggestive";//(String)queryData.get("cxr");
			data += "\nBase GX Result: " + "+ve";//(String)queryData.get("basegxp");
			data += "\nRIF Resistance: " + "Sensitive";//(String)queryData.get("basedst");

			//data += "\nHistory of TB Drugs: " + (String)queryData.get("tbhist");
			//data += "\nPatient Type: " + (String)queryData.get("type"); 
			//data += "\nCategory: " + (String)queryData.get("cat");
		//}
		append(data);

		gpIdField = new TextField("GP ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		dateField = new DateField("Date:",DateField.DATE);
		dateField.setDate(new Date());
		//chwIdField = new TextField("CHW ID:", tbrMidlet.getCurrentUserId(), IdValidateUtil.MAX_CHWID_LENGTH, TextField.ANY);
		//labField = new TextField("Lab ID:", tbrMidlet.getLabId(), IdValidateUtil.MAX_LABID_LENGTH, TextField.NUMERIC);

		weightField = new TextField("Weight (kg):", "", 5, TextField.DECIMAL);
		heightField = new TextField("Height (in):", "", 5, TextField.DECIMAL);

		phaseGroup = new ChoiceGroup("Treatment Phase", ChoiceGroup.POPUP);
		phaseGroup.append("Intensive",null);
		//phaseGroup.append("Continuous", null);
		
		patientTypeGroup = new ChoiceGroup("Patient Type", ChoiceGroup.POPUP);
		patientTypeGroup.append("New", null);
		patientTypeGroup.append("Relapse", null);
		patientTypeGroup.append("Transfer in", null);
		patientTypeGroup.append("Treatment after default", null);
		patientTypeGroup.append("Treatment after failure", null);
		patientTypeGroup.append("Other", null);
		
		patientCategoryGroup = new ChoiceGroup("Patient Category", ChoiceGroup.POPUP);
		patientCategoryGroup.append("CAT I", null);
//NOTE : first Patient is New hence Cat1 and RHZE only
		//patientCategoryGroup.append("Category 2", null);
		patientCategoryGroup.setSelectedIndex(0, true);
		
		//pregnantGroup = new ChoiceGroup("Are you pregnant?", ChoiceGroup.POPUP);
		//pregnantGroup.append("No", null);
		//pregnantGroup.append("Yes", null);
		//pregnantString = new StringItem("", "");
		
		diseaseSiteGroup = new ChoiceGroup("Disease Site:", ChoiceGroup.POPUP);
		diseaseSiteGroup.append("Pulmonary", null);
		diseaseSiteGroup.append("Extra Pulmonary", null);
		
		regimenGroup = new ChoiceGroup("Regimen", ChoiceGroup.POPUP);
		regimenGroup.append("RHZE", null);
		/*regimenGroup.append("RHZES",null);
		regimenGroup.append("EH", null);
		regimenGroup.append("RHE", null);
		regimenGroup.append("RH", null);*/
		//regimenGroup.setSelectedIndex(0, true);
		
		regimenTypeGroup = new ChoiceGroup("Regimen Type", ChoiceGroup.POPUP);
		regimenTypeGroup.append("Adult", null);
		regimenTypeGroup.append("Peds", null);

		fdcTabletGroup = new ChoiceGroup("Fixed Dose Combination Tablets", ChoiceGroup.POPUP);
		fdcTabletGroup.append("1", null);
		fdcTabletGroup.append("1.5", null);
		fdcTabletGroup.append("2", null);
		fdcTabletGroup.append("3", null);
		fdcTabletGroup.append("4", null);
		fdcTabletGroup.append("5", null);
		fdcTabletGroup.append("6", null);
		fdcTabletGroup.append("7", null);
		fdcTabletGroup.append("Don't Know", null);
		
		fdcTabletString = new StringItem( "" , "" );
		
		medicationFormGroup = new ChoiceGroup("Form of Medication", ChoiceGroup.POPUP);
		medicationFormGroup.append("Tablet form", null);
		medicationFormGroup.append("Syrup form", null);
		medicationFormGroup.append("Both", null);
		medicationFormString = new StringItem( "" , "" );
		recomendedRegimenString = new StringItem( "Recommended:" , "" );
		
/*		RHZ = new ChoiceGroup("RHZ", ChoiceGroup.POPUP);
		RHZ.append("0", null);
		RHZ.append("1", null);
		RHZ.append("2", null);
		RHZ.append("3", null);
		RHZ.append("4", null);
		RHZ.append("5", null);
		RHZ.setSelectedIndex( 1 , true );
		
		RH = new ChoiceGroup("RH", ChoiceGroup.POPUP);
		RH.append("0", null);
		RH.append("1", null);
		RH.append("2", null);
		RH.append("3", null);
		RH.append("4", null);
		RH.append("5", null);
		RH.append("6", null);
		RH.append("7", null);
		RH.setSelectedIndex( 1 , true );
*/
		
		R = new ChoiceGroup("R (teaspoons)", ChoiceGroup.POPUP);
		R.append("0", null);
		R.append("1", null);
		R.append("1.5", null);
		R.append("2", null);
		R.append("2.5", null);
		R.append("3", null);
		
		H = new ChoiceGroup("H (teaspoons)", ChoiceGroup.POPUP);
		H.append("0", null);
		H.append("1", null);
		H.append("1.5", null);
		H.append("2", null);
		H.append("2.5", null);
		H.append("3", null);
		
		Z = new ChoiceGroup("Z (teaspoons)", ChoiceGroup.POPUP);
		Z.append("0", null);
		Z.append("0.5", null);
		Z.append("1", null);
		Z.append("1.5", null);
		Z.append("2", null);
		Z.append("2.5", null);
		Z.append("3", null);
		
		E = new ChoiceGroup("E", ChoiceGroup.POPUP);
		E.append("0.25", null);
		E.append("0.5", null);
		E.append("0.75", null);
		E.append("1", null);

		streptoGroup = new ChoiceGroup("Streptomycin Dosage (mg)", ChoiceGroup.POPUP);
		streptoGroup.append("250", null);
		streptoGroup.append("500", null);
		streptoGroup.append("750", null);
		streptoGroup.append("Don't Know", null);
		/// not needed streptoString=new StringItem("", "");
		
		/*tsNameField = new TextField("Name of Treatment Supporter", "", 25, TextField.ANY);
		tsRelationshipGroup = new ChoiceGroup("Relationship of Treatment Supporter to you", ChoiceGroup.POPUP);
		tsRelationshipGroup.append("Parent",null);
		tsRelationshipGroup.append(SPOUSE_text, null);
		tsRelationshipGroup.append(SIBLING_text,null);
		tsRelationshipGroup.append("Child",null);
		tsRelationshipGroup.append("Grandparent", null);
		tsRelationshipGroup.append("Grandchild", null);
		tsRelationshipGroup.append("Aunt/Uncle", null);
		tsRelationshipGroup.append("Other", null);
		
		tsOtherRelationshipField = new TextField("Specify relationship", "", 12, TextField.UNEDITABLE);
		tsPhoneField = new TextField("Treatment Supporter Phone:", "", 12, TextField.NUMERIC);*/
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(dateField);
		append(gpIdField);
		//append(chwIdField);
		//append(labField);

		//append(pregnantString);
		append(weightField);
		append(heightField);

		append(patientTypeGroup);
		append(patientCategoryGroup);
		append(diseaseSiteGroup);
		append(phaseGroup);
		
		append(regimenTypeGroup);
		append( medicationFormString );//first adult will be checked

		append(regimenGroup);
		//NOTE : first Patient is New hence Cat1 and RHZE only
		recomendedRegimenString.setText( "RHZE Fixed Dose Combination Tablets" );
		append( recomendedRegimenString );

		append(fdcTabletGroup);//first adult will be checked
//		append( RHZ );
//		append( E );
		
		///not needed append(streptoString);
	/*	append(tsNameField);
		append(tsRelationshipGroup);
		append(tsOtherRelationshipField);
		append(tsPhoneField);*/

		
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
	}
	
	public void itemStateChanged(Item i) {
		
		if(i==patientTypeGroup) {
			int index = patientTypeGroup.getSelectedIndex();
			if(index==0) {
				patientCategoryGroup.deleteAll();
				patientCategoryGroup.append("CAT I", null);
				patientCategoryGroup.setSelectedIndex(0,true);
				
				/*regimenGroup.deleteAll();
				regimenGroup.append("RHZE", null);
				regimenGroup.setSelectedIndex(0, true);*/
				
				/*if(get(size()-1)==streptoGroup) {
					delete(size()-1);
				}*/
			}
			else if(index==1 || index==3 || index==4) {
				patientCategoryGroup.deleteAll();
				patientCategoryGroup.append("CAT II", null);
				patientCategoryGroup.setSelectedIndex(0,true);
				
				/*regimenGroup.deleteAll();
				regimenGroup.append("RHZES", null);
				regimenGroup.setSelectedIndex(0, true);*/
				
				/*if(get(size()-1)!=streptoGroup) {
					append(streptoGroup);
				}*/
			}
			else {
				patientCategoryGroup.deleteAll();
				patientCategoryGroup.append("CAT I", null);
				patientCategoryGroup.append("CAT II", null);
				patientCategoryGroup.setSelectedIndex(0,true);
				
				/*regimenGroup.deleteAll();
				regimenGroup.append("RHZE", null);
				regimenGroup.append("RHZES", null);
				regimenGroup.setSelectedIndex(0, true);*/
				
				/*if(get(size()-1)==streptoGroup) {
					delete(size()-1);
				}*/
			}
			updateRegimenItem();
			
			updateMedForm();
		}
		else if(i==patientCategoryGroup) {
			updateRegimenItem();
			
			updateMedForm();
		}
		else if(i == phaseGroup){
			updateRegimenItem();
			
			updateMedForm();
		}
		else if(i == medicationFormGroup){
			// donot update regimen else it will reset regimen selected to recommeded.
			//updateRegimenItem();
			
			updateMedForm();
		}
		else if(i == regimenGroup){
			// donot update regimen else it will not allow to choose other than recommended
			//updateRegimenItem();
			
			updateMedForm();
		}
		/*else if(i == pregnantGroup){
			updatePregnancyAndRegimenItem();
			
			setTreatmentFlow();
		}*/
		/*else if(i==tsRelationshipGroup) {
			if(tsRelationshipGroup.getString(tsRelationshipGroup.getSelectedIndex()).toLowerCase().equals("other")) {
				tsOtherRelationshipField.setString("");
				tsOtherRelationshipField.setConstraints(TextField.ANY);
			}
			else {
				tsOtherRelationshipField.setConstraints(TextField.UNEDITABLE);
			}
		}
		*/
		/*if(i==regimenGroup) {
			if(regimenGroup.getSelectedIndex()==1 && get(size()-1)!=streptoGroup) {
				append(streptoGroup);
			}
			else if(regimenGroup.getSelectedIndex()!=1 && get(size()-1)==streptoGroup) {
				delete(size()-1);
			}
		}*/
		else if(i == regimenTypeGroup){
			if(regimenTypeGroup.getString( regimenTypeGroup.getSelectedIndex()).toLowerCase().indexOf("adult" ) != -1){
				if(get(MED_FORM_INDEX) != medicationFormString){
					if(get(MED_FORM_INDEX) == medicationFormGroup) delete(MED_FORM_INDEX);
					
					insert(MED_FORM_INDEX, medicationFormString);
				}
			}
			else{//if PEDS append medication form question
				if(get(MED_FORM_INDEX) != medicationFormGroup){
					if(get(MED_FORM_INDEX) == medicationFormString) delete(MED_FORM_INDEX);
					
					insert(MED_FORM_INDEX, medicationFormGroup);
				}
			}
			
			updateRegimenItem();
			
			updateMedForm();
		}
	}
	
	private void updateMedForm(){
		while((size()-1) != permanent_item_index){
			delete( size()-1 );
		}
		
		
		String MedString = "";
		//if Adult medication form will not be appended and only table can be given
		if(regimenTypeGroup.getString( regimenTypeGroup.getSelectedIndex()).toLowerCase().indexOf("adult" ) != -1){
			MedString = "tablet";
		}
		else{//if Peads medication form will be appended n have value
			MedString = medicationFormGroup.getString(medicationFormGroup.getSelectedIndex()).toLowerCase();
		}
		
		String Med = "";
		if(MedString.indexOf( "tablet" ) != -1){
			fdcTabletGroup.setSelectedIndex( 0 , true );
			
			Med = "tablet";
		}
		if(MedString.indexOf( "syrup" ) != -1){

			if(R.getString(0).equals("0")) 
				R.delete(0);
			if(H.getString(0).equals("0")) 
				H.delete(0);
			if(Z.getString(0).equals("0")) 
				Z.delete(0);
			
			Med = "syrup";
		}
		if(MedString.indexOf( "both" ) != -1){
			fdcTabletGroup.setSelectedIndex( 0 , true );

			if(!R.getString(0).equals("0")) 
				R.insert( 0 , "0" , null );
			if(!H.getString(0).equals("0")) 
				H.insert( 0 , "0" , null );
			if(!Z.getString(0).equals("0")) 
				Z.insert( 0 , "0" , null );

			Med = "tablet-syrup";
		}
		
		R.setSelectedIndex( 0 , true );
		H.setSelectedIndex( 0 , true );
		Z.setSelectedIndex( 0 , true );
		
		String reg = regimenGroup.getString( regimenGroup.getSelectedIndex() ).toLowerCase();

		if(Med.indexOf( "tablet" ) != -1){//if tablet insert FDC
			if(get(FDC_TAB_INDEX) != fdcTabletGroup){
				if(get(FDC_TAB_INDEX) == fdcTabletString) delete( FDC_TAB_INDEX );
				
				insert( FDC_TAB_INDEX , fdcTabletGroup );
			}
		}
		else{//if not tablet delete FDC
			if(get(FDC_TAB_INDEX) != fdcTabletString){
				if(get(FDC_TAB_INDEX) == fdcTabletGroup) delete( FDC_TAB_INDEX );
				
				insert( FDC_TAB_INDEX , fdcTabletString );
			}
		}
		if(Med.indexOf( "syrup" ) != -1){
			if(reg.indexOf( "r" ) != -1){
				append( R );
			}
			if(reg.indexOf( "h" ) != -1){
				append( H );
			}
			if(reg.indexOf( "z" ) != -1){
				append( Z );
			}
			if(reg.indexOf( "e" ) != -1){
				append( E );
			}
		}
		
		if(reg.indexOf( "s" ) != -1){
			append( streptoGroup );
		}
	}
/* not needed	private void updateStreptoItem(){
		if(regimenGroup.getString(regimenGroup.getSelectedIndex()).toLowerCase().indexOf('s') != -1){
			if(get(STREPTO_INDEX) != streptoGroup){
				if(get(STREPTO_INDEX) == streptoString) delete(STREPTO_INDEX);
				
				insert(STREPTO_INDEX, streptoGroup);
			}
		} else {
			if(get(STREPTO_INDEX) != streptoString){
				if(get(STREPTO_INDEX) == streptoGroup) delete(STREPTO_INDEX);
				
				insert(STREPTO_INDEX, streptoString);
			}
		}
	}*/
	
	private void updateRegimenItem(){
		//if category 1 then just give RHZE and remove pregnancy question and strepto question from Form
		if(patientCategoryGroup.getString(patientCategoryGroup.getSelectedIndex()).toUpperCase().equals("CAT I")){

			regimenGroup.deleteAll();
			
			if(phaseGroup.getString(phaseGroup.getSelectedIndex()).toLowerCase().startsWith("intensive")){
				recomendedRegimenString.setText( "RHZE Fixed Dose Combination Tablet" );
			}
			else{
				recomendedRegimenString.setText( "EH Fixed Dose Combination Tablet" );
			}
			
			//if adult
			if(regimenTypeGroup.getSelectedIndex() == 0){
				if(phaseGroup.getString(phaseGroup.getSelectedIndex()).toLowerCase().startsWith("intensive")){
					regimenGroup.append("RHZE", null);
					regimenGroup.setSelectedIndex(0, true);
				}
				else{
					regimenGroup.append("EH", null);
					regimenGroup.append("RHE", null);
					regimenGroup.append("RH", null);
					regimenGroup.append("RHZ", null);
					regimenGroup.setSelectedIndex(0, true);
				}
			}
			else{//if PEDS
				regimenGroup.append("RHZE", null);
				regimenGroup.append("RHZES", null);
				regimenGroup.append("EH", null);
				regimenGroup.append("RHE", null);
				regimenGroup.append("RH", null);
				regimenGroup.append("RHZ", null);
				if(phaseGroup.getString(phaseGroup.getSelectedIndex()).toLowerCase().startsWith("intensive")){
					regimenGroup.setSelectedIndex(0, true);
				}
				else{
					regimenGroup.setSelectedIndex(2, true);
				}
			}
			
			/*if(get(PREGNANCY_INDEX) != pregnantString){
				if(get(PREGNANCY_INDEX) == pregnantGroup) delete(PREGNANCY_INDEX);
				
				insert(PREGNANCY_INDEX, pregnantString);
			}*/
			
		}else if(patientCategoryGroup.getString(patientCategoryGroup.getSelectedIndex()).toUpperCase().equals("CAT II")){
			regimenGroup.deleteAll();
			
			if(phaseGroup.getString(phaseGroup.getSelectedIndex()).toLowerCase().startsWith("intensive")){
				recomendedRegimenString.setText( "RHZES Fixed Dose Combination Tablet + Injections" );
			}
			else{
				recomendedRegimenString.setText( "RHE Fixed Dose Combination Tablet" );
			}
			
			//if adult
			if(regimenTypeGroup.getSelectedIndex() == 0){
				if(phaseGroup.getString(phaseGroup.getSelectedIndex()).toLowerCase().startsWith("intensive")){
					regimenGroup.append("RHZES", null);
					regimenGroup.append("RHZE", null);

					regimenGroup.setSelectedIndex(0, true);
				}
				else{
					regimenGroup.append("RHE", null);
					regimenGroup.setSelectedIndex(0, true);
				}
			}
			else{//if PEDS
				regimenGroup.append("RHZE", null);
				regimenGroup.append("RHZES", null);
				regimenGroup.append("EH", null);
				regimenGroup.append("RHE", null);
				regimenGroup.append("RH", null);
				regimenGroup.append("RHZ", null);

				if(phaseGroup.getString(phaseGroup.getSelectedIndex()).toLowerCase().startsWith("intensive")){
					regimenGroup.setSelectedIndex(1, true);
				}
				else{
					regimenGroup.setSelectedIndex(3, true);
				}
			}
		}
		
		//else if category 2 and female append pregnancy question
		//female ???
		/*if(gender.toLowerCase().equals("f")){
			if(get(PREGNANCY_INDEX) != pregnantGroup){
				if(get(PREGNANCY_INDEX) == pregnantString) delete(PREGNANCY_INDEX);
				
				insert(PREGNANCY_INDEX, pregnantGroup);
			}
		}//not female remove this question if exists
		else{
			if(get(PREGNANCY_INDEX) != pregnantString){
				if(get(PREGNANCY_INDEX) == pregnantGroup) delete(PREGNANCY_INDEX);
				
				insert(PREGNANCY_INDEX, pregnantString);
			}
		}*/
		
		//if pregnant question is appended and yes
/*		if(get(PREGNANCY_INDEX) == pregnantGroup
				&& pregnantGroup.getString(pregnantGroup.getSelectedIndex()).toLowerCase().equals("yes")){
				regimenGroup.deleteAll();
				regimenGroup.append("RHZE", null);
				regimenGroup.setSelectedIndex(0, true);
		}//not pregnant then append strepto too
*/		//else{
		//if it is CAT II
			
		//}
	}
	
	public boolean validate() {
		boolean result = true;
		
		if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING,null);
			result = false;
		}
		else if(gpIdField.getString().length()<IdValidateUtil.MIN_GP_ID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.GP_ID_INVALID, null);
			result = false;
		}
		//future date check
		if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		/*else if(chwIdField.getString()==null || chwIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING, null);
			result = false;
		}
		else if(chwIdField.getString().length()<IdValidateUtil.MIN_CHWID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.CHW_ID_INVALID, null);
			result = false;
		}*/
		/*else if(labField.getString()==null || labField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.LAB_ID_MISSING, null);
			result = false;
		}
		else if(labField.getString().length()<IdValidateUtil.MIN_LABID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.LAB_ID_INVALID, null);
			result = false;
		}*/
		else if(weightField.getString()==null || weightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.WEIGHT_MISSING,null);
			result = false;
		}
		else if(heightField.getString()==null || heightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.HEIGHT_MISSING,null);
			result = false;
		}
		/*else if(tsNameField.getString()!=null && tsNameField.getString().length()!=0 && tsRelationshipGroup.getSelectedIndex()==6 && (tsOtherRelationshipField.getString()==null || tsOtherRelationshipField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.TS_RELATIONSHIP_MISSING,null);
			result = false;
		}
		else if(tsPhoneField != null && tsPhoneField.getString().trim().length() != 0 ) {
				if(tsNameField.getString()!=null && tsNameField.getString().trim().length()!=0){
					if(tsPhoneField.getString().length() != 12) {
						tbrMidlet.showAlert(ErrMsg.BAD_PHONE_LENGTH, null);
						result = false;
					}
					else if(!tsPhoneField.getString().substring(0,2).equals("92")) {
						tbrMidlet.showAlert(ErrMsg.BAD_COUNTRY_CODE, null);
						result = false;
					}
				}
				else{
					tbrMidlet.showAlert(ErrMsg.TS_PHONE_NAME_MISSING, null);
					result = false;
				}
		}*/
		return result;
	}
	
	public String createRequestPayload() {
		String request = "";
		
		request = "type=btf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&gpid=" + gpIdField.getString();
    	//request += "&chwid=" + chwIdField.getString();
    	//request += "&labid=" +   labField.getString();

    	request += "&wt=" + weightField.getString();
    	/*if(gender.toLowerCase().equals("f")
    			&& patientCategoryGroup.getString(patientCategoryGroup.getSelectedIndex()).toLowerCase().equals("category 2")){
    	request += "&pregn=" + pregnantGroup.getString(pregnantGroup.getSelectedIndex());
    	}*/
    	//request += "&pt=" + phaseGroup.getString(phaseGroup.getSelectedIndex());
    	request += "&ptp=" + patientTypeGroup.getString(patientTypeGroup.getSelectedIndex());
    	request += "&pc=" + patientCategoryGroup.getString(patientCategoryGroup.getSelectedIndex());
    	request += "&ds=" + diseaseSiteGroup.getString(diseaseSiteGroup.getSelectedIndex());
    	request += "&reg=" + regimenGroup.getString(regimenGroup.getSelectedIndex());
    	request += "&tab=" + fdcTabletGroup.getString(fdcTabletGroup.getSelectedIndex());
    	if(regimenGroup.getString(regimenGroup.getSelectedIndex()).toLowerCase().indexOf('s') != -1) {
    		request += "&str=" + streptoGroup.getString(streptoGroup.getSelectedIndex());
    	}
    	
    	/*request += "&tsName=" + tsNameField.getString();
    	
    	String tsrelVal = tsRelationshipGroup.getString(tsRelationshipGroup.getSelectedIndex());
    	if(tsrelVal.equals(SIBLING_text)){
    		tsrelVal = "SIBLING";
    	}
    	else if(tsrelVal.equals(SPOUSE_text)){
    		tsrelVal = "SPOUSE";
    	}
    	
    	request += "&tsRel=" + tsrelVal;
    	if(tsRelationshipGroup.getString(tsRelationshipGroup.getSelectedIndex()).toLowerCase().equals("other")){
    		request += "&tsOtherRel=" + tsOtherRelationshipField.getString();
    	}*/
    	//request += "&tsPhn=" + tsPhoneField.getString();
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		return request;
	}
}
