package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.Choice;
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
import org.irdresearch.tbreach.mobile.constants.UserType;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class PatientFollowupEffortForm extends BaseTBReachForm implements CommandListener, ItemStateListener {
	private static final String EFFORT_CALL = "call";
	private static final String EFFORT_VISIT = "visit";
	private static String CURRENT_EFFORT_TYPE = EFFORT_CALL;
	
	private static final int permanent_item_index = 6;
	private static String CURRENT_PATIENT_STATUS = "";//TO check if patient status changed. it is to avoid resetting of all fields if user selected same choice again

	/** 'What month of FUP?' question choiceGroup/stringItem INDEX on form */
	private static final int MONTHindex = 5;
	private static boolean is_calloutcome_visible = true;
	private static boolean is_pat_spoke_visible = false;
	private static boolean is_hhoutcome_visible = false;


	DateField dateField;
	TextField fieldWorkerIdField;
	//TextField gpIdField;

	TextField callOutcomeOtherField;
	TextField hhVisitOtherField;

	ChoiceGroup hhVisitChoice;

	TextField patientSpokeOtherField;
	StringItem monthString;
	ChoiceGroup callOutcomeChoice;
	ChoiceGroup patientSpokeChoice;
	ChoiceGroup effortReasonChoice;
	ChoiceGroup monthChoice;
	ChoiceGroup effortTypeChoice;
	ChoiceGroup patientStatus;

	Command cmdOK;
	Command cmdBack;
	
	private Hashtable queryData;
	private String patientId;
	
	public PatientFollowupEffortForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		dateField = null;           
		fieldWorkerIdField= null;   
		//gpIdField = null;
		callOutcomeOtherField= null; 
		callOutcomeChoice= null;        
		patientSpokeChoice= null;   
		patientSpokeOtherField= null;         
		                     
		effortReasonChoice = null;               
		monthChoice= null;  
		monthString = null;
		effortTypeChoice = null;           
		patientStatus= null;   
		
		cmdOK = null;
		cmdBack = null;
		
		queryData = null;
		patientId = null;
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
	
	public void init() {
		String data = "";
		
		//if(queryData!=null) {
			data += "Patient ID: " + patientId;
			data += "\nMR Number: " + "20111234567";//(String)queryData.get("mr");
			data += "\nGP ID: " + "G-TEST-01";//(String)queryData.get("gpid");

			//data += "\nRegimen: " + "RHZE";
			
		//}
		append(data);

		//TODO is it OK or needs to be NUMERIC
		String fwId = "";
		if(tbrMidlet.getCurrentRole() == UserType.USER_TYPE_CHW 
				|| tbrMidlet.getCurrentRole() == UserType.USER_TYPE_MONITOR){
			fwId = 	tbrMidlet.getCurrentUserId();
		}
		fieldWorkerIdField = new TextField("Field Worker ID:", fwId
					, (IdValidateUtil.MAX_CHWID_LENGTH), TextField.ANY);

		/*gpIdField = new TextField("GP ID:", "",12, TextField.ANY);
		if(tbrMidlet.getSettings().getActiveUserType()==UserType.USER_TYPE_GP) {
			gpIdField.setString(tbrMidlet.getCurrentUserId());
			chwIdField.setConstraints(TextField.UNEDITABLE);
		}*/
		dateField = new DateField("Date of Effort", DateField.DATE);
		dateField.setDate(new Date());
		
		patientStatus = new ChoiceGroup("Patient Status", Choice.POPUP);
		patientStatus.append("Suspect", null);
		patientStatus.append("Confirmed", null);
		patientStatus.setSelectedIndex(0, true);
		
		CURRENT_PATIENT_STATUS = "";
		CURRENT_EFFORT_TYPE = EFFORT_CALL;
		
		effortReasonChoice = new ChoiceGroup("What is this follow up effort for?", ChoiceGroup.POPUP);
		resetEffortReason();

		monthChoice=  new ChoiceGroup("What month of FUP?", ChoiceGroup.POPUP);
		monthChoice.append("2nd", null);
		monthChoice.append("3rd", null);
		monthChoice.append("5th", null);
		monthChoice.append("7th", null);
		monthChoice.setSelectedIndex(0,true);
		
		monthString=new StringItem("", "");
		
		effortTypeChoice = new ChoiceGroup("What type of effort did you conduct?", ChoiceGroup.POPUP);
		effortTypeChoice.append("Phone Call", null);
		effortTypeChoice.append("Household visit",null);
		effortTypeChoice.setSelectedIndex(0, true);
		
		callOutcomeChoice=new ChoiceGroup("Phone call outcome", ChoiceGroup.POPUP);
		callOutcomeChoice.append("Wrong number", null);
		callOutcomeChoice.append("Cell off", null);
		callOutcomeChoice.append("No answer", null);
		callOutcomeChoice.append("Spoke to patient", null);
		callOutcomeChoice.append("Other", null);
		callOutcomeChoice.setSelectedIndex(0, true);
		
		callOutcomeOtherField = new TextField( "If Other, Specify" , "" , 25 , TextField.ANY );
		
		patientSpokeChoice=new ChoiceGroup("What did patient say? ", ChoiceGroup.POPUP);
		patientSpokeChoice.append("Agreed to come", null);
		patientSpokeChoice.append("Has moved", null);
		patientSpokeChoice.append("Not taking drugs", null);
		patientSpokeChoice.append("Patient refused", null);
		patientSpokeChoice.append("Taking medicine from another center", null);
		patientSpokeChoice.append("Other", null);
		patientSpokeChoice.setSelectedIndex(0, true);
		
		patientSpokeOtherField=new TextField( "if Other, Specify" , "" , 25 , TextField.UNEDITABLE );
		
		hhVisitChoice = new ChoiceGroup("Household visit outcome", ChoiceGroup.POPUP);
		hhVisitChoice.append("Incomplete address", null);
		hhVisitChoice.append("Could not find patient home", null);
		hhVisitChoice.append("Wrong address", null);
		hhVisitChoice.append("Patient has moved", null);
		hhVisitChoice.append("Patient not home", null);
		hhVisitChoice.append("Patient agreed to come", null);
		hhVisitChoice.append("Patient refused", null);
		hhVisitChoice.append("Other", null);

		hhVisitOtherField = new TextField("If Other, Specify", "", 12, TextField.UNEDITABLE);
	
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);

		append(dateField);
		//append(gpIdField);
		append(fieldWorkerIdField);
		append(patientStatus);
		append(effortReasonChoice);
		append(monthString);
		append(effortTypeChoice);
		append(callOutcomeChoice);
		callOutcomeOtherField.setConstraints(TextField.UNEDITABLE);
		append(callOutcomeOtherField);
	
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
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
						System.out.println("success");
						tbrMidlet.showAlert(SuccessMsg.SAVE_SUCCESS,null);
					}
					/*else if(status.equals(XmlStrings.ERROR)) {
						System.out.println((String)model.get("msg"));
						tbrMidlet.showAlert("ERROR: " + (String)model.get("msg"),null);
					}
				}*/
			}
		}
		else if(c==cmdBack) {
			deleteAll();
			removeCommand(cmdOK);
			removeCommand(cmdBack);
			tbrMidlet.setDisplay(prevDisplayable);
		}
	}

	public String createRequestPayload() {
		String request = "";

		request = "type=pfupeff";
		request += "&id=" + patientId;
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&chw-mon-id=" +   fieldWorkerIdField.getString();
    	request += "&hhvis=" +   hhVisitChoice.getString(hhVisitChoice.getSelectedIndex());
		request += "&pstat=" + patientStatus.getString(patientStatus.getSelectedIndex());

    	if(hhVisitChoice.getString(hhVisitChoice.getSelectedIndex()).toLowerCase().equals("other")){
    		request += "&hhvisother=" +   hhVisitOtherField.getString();
    	}
    	//request += "&gpid=" + gpIdField.getString();
    	
    	int effortreasonIndex = effortReasonChoice.getSelectedIndex();
    	
    	request += "&effrsn=" + effortReasonChoice.getString(effortreasonIndex);
    	if(effortReasonChoice.getString(effortreasonIndex).toLowerCase().startsWith( "pending fup" )) {//if cough is yes
    		request += "&mon=" + monthChoice.getString(monthChoice.getSelectedIndex());
    	}
    	
    	request += "&efftype=" + effortTypeChoice.getString(effortTypeChoice.getSelectedIndex());
    	
    	if(effortTypeChoice.getString(effortTypeChoice.getSelectedIndex()).toLowerCase().equals("phone call")){
        	request += "&coutcm=" + callOutcomeChoice.getString(callOutcomeChoice.getSelectedIndex());

    		if(callOutcomeChoice.getString(callOutcomeChoice.getSelectedIndex()).toLowerCase().equals("other")){
        		request += "&coutcmother=" + callOutcomeOtherField.getString();
    		}
    		else if(callOutcomeChoice.getString(callOutcomeChoice.getSelectedIndex()).toLowerCase().equals("spoke to patient")){
    			request += "&patspok=" + patientSpokeChoice.getString(patientSpokeChoice.getSelectedIndex());
        		if(patientSpokeChoice.getString(patientSpokeChoice.getSelectedIndex()).toLowerCase().equals( "other" )){
            		request += "&patspokother=" + patientSpokeOtherField.getString();
        		}        		
        	}
		}
		else if(effortTypeChoice.getString(effortTypeChoice.getSelectedIndex()).toLowerCase().endsWith( "visit" )){
			request += "&hhvisoutcm=" + hhVisitChoice.getString(hhVisitChoice.getSelectedIndex());

    		if(hhVisitChoice.getString(hhVisitChoice.getSelectedIndex()).toLowerCase().equals("other")){
        		request += "&hhvisoutcmother=" + hhVisitOtherField.getString();
    		}
    	}

    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
    	return request;
	}
	
	private boolean validate() {
		boolean result = true;
		
		if(fieldWorkerIdField.getString()==null || fieldWorkerIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.FW_ID_MISSING, null);
			result = false;
		}
		else if(fieldWorkerIdField.getString().length()<IdValidateUtil.MIN_ID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.FW_ID_INVALID, null);
			result = false;
		}
		/*else if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		else if(gpIdField.getString().length()<IdValidateUtil.MAX_GP_ID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.GP_ID_INVALID, null);
			result = false;
		}*/
		else if(is_calloutcome_visible
				&& callOutcomeChoice.getString(callOutcomeChoice.getSelectedIndex()).toLowerCase().equals("other")&&
				(callOutcomeOtherField ==null || callOutcomeOtherField.getString().trim().toLowerCase().equals(""))){
			tbrMidlet.showAlert(ErrMsg.CALL_OUTCOME_OTHER_MISSING, null);
			result = false;	
		}
		else if(is_pat_spoke_visible
				&& patientSpokeChoice.getString(patientSpokeChoice.getSelectedIndex()).toLowerCase().equals("other")&&
				(patientSpokeOtherField ==null || patientSpokeOtherField.getString().trim().toLowerCase().equals(""))){
			tbrMidlet.showAlert(ErrMsg.PATIENT_SPOKEN_OTHER_MISSING, null);
			result = false;	
		}
		else if(is_hhoutcome_visible
				&& hhVisitChoice.getString(hhVisitChoice.getSelectedIndex()).toLowerCase().equals("other")&&
				(hhVisitOtherField ==null || hhVisitOtherField.getString().trim().toLowerCase().equals(""))){
			tbrMidlet.showAlert(ErrMsg.HOUSEHOLD_VISIT_OTHER_MISSING, null);
			result = false;	
		}
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		return result;
	}
	
	public void itemStateChanged(Item i) {
		if(i == patientStatus){
			resetEffortReason();
		}
		else if(i == effortReasonChoice) {
			String effortReasonStringVal = effortReasonChoice.getString(effortReasonChoice.getSelectedIndex());
			
			if(effortReasonStringVal.toLowerCase().startsWith( "pending fup" )) {
				if(get(MONTHindex)!=monthChoice){
					if(get(MONTHindex) == monthString) delete(MONTHindex);
					
					insert(MONTHindex,monthChoice);
				}
			}
			else {
				if(get(MONTHindex)!=monthString){
					if(get(MONTHindex) == monthChoice) delete(MONTHindex);
					
					insert(MONTHindex, monthString);
				}
			}
		}
		else if(i==effortTypeChoice) {
			if(effortTypeChoice.getString(effortTypeChoice.getSelectedIndex()).toLowerCase().equals("phone call")){
				handleCall();
			}
			else if(effortTypeChoice.getString(effortTypeChoice.getSelectedIndex()).toLowerCase().endsWith( "visit" )){
				handleVisit();
			}
		}
		else if(i == callOutcomeChoice){
			if(callOutcomeChoice.getString(callOutcomeChoice.getSelectedIndex()).toLowerCase().equals("other")){
				callOutcomeOtherField.setString( "" );
				callOutcomeOtherField.setConstraints( TextField.ANY );
			}else{
				callOutcomeOtherField.setString( "" );
				callOutcomeOtherField.setConstraints( TextField.UNEDITABLE );
			}
			if(callOutcomeChoice.getString(callOutcomeChoice.getSelectedIndex()).toLowerCase().equals("spoke to patient")){
				if(get(size()-1) != patientSpokeOtherField){
					append( patientSpokeChoice );
					append( patientSpokeOtherField );
				}
				is_pat_spoke_visible = true;
			}
			else {
				if(get(size()-1)==patientSpokeOtherField){
					delete( size()-1 );//delete patientspoke other field
					delete( size()-1 );//delete patientspoke choice
				}
				is_pat_spoke_visible = false;
			}
		}
		else if(i == hhVisitChoice){
			if(hhVisitChoice.getString( hhVisitChoice.getSelectedIndex() ).toLowerCase().equals( "other" )){
				hhVisitOtherField.setString( "" );
				hhVisitOtherField.setConstraints( TextField.ANY );
			}
			else{
				hhVisitOtherField.setString( "" );
				hhVisitOtherField.setConstraints( TextField.UNEDITABLE );
			}
		}
		else if(i == patientSpokeChoice){
			if(patientSpokeChoice.getString( patientSpokeChoice.getSelectedIndex() ).toLowerCase().equals( "other" )){
				patientSpokeOtherField.setString( "" );
				patientSpokeOtherField.setConstraints( TextField.ANY );
			}
			else{
				patientSpokeOtherField.setString( "" );
				patientSpokeOtherField.setConstraints( TextField.UNEDITABLE );
			}
		}
	}
	private void resetEffortReason(){
		if(!CURRENT_PATIENT_STATUS.toLowerCase().equals( patientStatus.getString(patientStatus.getSelectedIndex()).toLowerCase() )){
			effortReasonChoice.deleteAll();
			
			if(patientStatus.getString( patientStatus.getSelectedIndex() ).toLowerCase().equals( "suspect" )){
				effortReasonChoice.append("Pending Baseline Sputum",null);
				effortReasonChoice.append("Pending Baseline CXR",null);
			}
				effortReasonChoice.append("Pending Baseline Visit",null);
			if(patientStatus.getString( patientStatus.getSelectedIndex() ).toLowerCase().equals( "confirmed" )){
				effortReasonChoice.append("Pending FUP Sputum",null);
				effortReasonChoice.append("Pending FUP Clinical Visit",null);
			}
				effortReasonChoice.setSelectedIndex(0, true);
			
				if(monthString != null && size() >=MONTHindex && get(MONTHindex)!=monthString){
					if(get(MONTHindex) == monthChoice) delete(MONTHindex);
					
					insert(MONTHindex, monthString);
				}
				
				CURRENT_PATIENT_STATUS = patientStatus.getString(patientStatus.getSelectedIndex());
		}
	}
	
	private void handleCall(){
		if(CURRENT_EFFORT_TYPE.equals( EFFORT_VISIT) ){
			while ( size()-1 > permanent_item_index) {
				delete( size()-1 );
			}
			CURRENT_EFFORT_TYPE = EFFORT_CALL;
			
			is_hhoutcome_visible = false;
			is_calloutcome_visible = true;
			is_pat_spoke_visible = false;
			callOutcomeChoice.setSelectedIndex( 0 , true );
			patientSpokeChoice.setSelectedIndex( 0 , true );
			patientSpokeOtherField.setString( "" );
			patientSpokeOtherField.setConstraints( TextField.UNEDITABLE );
			callOutcomeOtherField.setString( "" );
			callOutcomeOtherField.setConstraints( TextField.UNEDITABLE );
			
			append( callOutcomeChoice );
			append( callOutcomeOtherField );
		}
	}
	
	private void handleVisit(){
		if(CURRENT_EFFORT_TYPE.equals( EFFORT_CALL) ){
			while ( size()-1 > permanent_item_index) {
				delete( size()-1 );
			}
			CURRENT_EFFORT_TYPE = EFFORT_VISIT;
			
			is_hhoutcome_visible = true;
			is_calloutcome_visible = false;
			is_pat_spoke_visible = false;
			hhVisitChoice.setSelectedIndex( 0 , true );
			hhVisitOtherField.setString( "" );
			hhVisitOtherField.setConstraints( TextField.UNEDITABLE );
			
			append( hhVisitChoice );
			append( hhVisitOtherField );
		}	
	}
}