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
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class SuspectVerificationForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

	public static int TX_OTHER_INDEX = 9;
	public static int DIAG_OTHER_INDEX = 9;
	
	//private TBReachMainMIDlet tbrMidlet;
	TextField idField;
	TextField idConfirm;
	TextField chwIdField;
	DateField dateField;

	TextField gpIdField;
	ChoiceGroup didYouSeePatientGroup;
	ChoiceGroup whyNotSeenGroup;
	TextField otherWhyNotSeenField;
	//---->
	ChoiceGroup takenTreatmentGroup;
	ChoiceGroup whereTreatmentGroup;
	TextField whereTreatmentField;
	
	ChoiceGroup diagnosedInLastMonthGroup;
	ChoiceGroup whereDiagnosedGroup;
	TextField whereDiagnosedText;
	
	ChoiceGroup indusLocationGroup;
	TextField otherIndusLocationField;
	//----------<---
	ChoiceGroup conclusion;
	TextField whyNotVerifiedField;
	
	Command cmdOK;
	Command cmdBack;
	
	public SuspectVerificationForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		idField = null;
		idConfirm = null;
		
		chwIdField = null;
		
		gpIdField = null;
		dateField = null;
		didYouSeePatientGroup = null;
		whyNotSeenGroup = null;
		otherWhyNotSeenField = null;
		conclusion = null;
		whyNotVerifiedField = null;
		
		cmdOK = null;
		cmdBack = null;
	}
	
	public void init() {
		idField = new TextField("Suspect ID:", "", 10, TextField.NUMERIC);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 10, TextField.NUMERIC);
		chwIdField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		gpIdField = new TextField("GP ID:", "",12, TextField.ANY);
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		
		didYouSeePatientGroup = new ChoiceGroup("Did you see the suspect?", ChoiceGroup.EXCLUSIVE);
		didYouSeePatientGroup.append("Yes",null);
		didYouSeePatientGroup.append("No", null);
		
		whyNotSeenGroup = new ChoiceGroup("Why not?", ChoiceGroup.EXCLUSIVE);
		whyNotSeenGroup.append("Patient left", null);
		whyNotSeenGroup.append("Other", null);
		
		otherWhyNotSeenField = new TextField("Why not?", "", 20, TextField.ANY);
		
		//--------
		takenTreatmentGroup = new ChoiceGroup("Has suspect taken TB treatment in the last month?", ChoiceGroup.POPUP);
		takenTreatmentGroup.append("Yes",null);
		takenTreatmentGroup.append("No", null);
		takenTreatmentGroup.append("Don't know", null);
		
		whereTreatmentGroup = new ChoiceGroup("If yes, which center provided treatment?", ChoiceGroup.POPUP);
		whereTreatmentGroup.append("N/A",null);
		whereTreatmentGroup.append("Indus Hospital",null);
		whereTreatmentGroup.append("SGH Korangi", null);
		whereTreatmentGroup.append("CDG-SGH I.Haideri", null);
		whereTreatmentGroup.append("CDG-THO Office Korangi", null);
		whereTreatmentGroup.append("NGO-Sir Syed Medical University and Hospital", null);
		whereTreatmentGroup.append("Infaq Foundation", null);
		whereTreatmentGroup.append("Beechum Trust Hospital", null);
		whereTreatmentGroup.append("Don't Know",null);
		whereTreatmentGroup.append("Other",null);
		
		whereTreatmentField = new TextField("Specify Treatment Center?", "",50,TextField.ANY);
		whereTreatmentField.setConstraints(TextField.UNEDITABLE);
		
		
		diagnosedInLastMonthGroup = new ChoiceGroup("Diagnosed with TB in the last month?", ChoiceGroup.POPUP);
		diagnosedInLastMonthGroup.append("Yes",null);
		diagnosedInLastMonthGroup.append("No",null);
		diagnosedInLastMonthGroup.append("Don't Know",null);
		
		whereDiagnosedGroup = new ChoiceGroup("If yes, from which diagnostic center?", ChoiceGroup.POPUP);
		whereDiagnosedGroup.append("N/A",null);
		whereDiagnosedGroup.append("Indus Hospital", null);
		whereDiagnosedGroup.append("SGH Korangi",null);
		whereDiagnosedGroup.append("CDG-SGH I.Haideri", null);
		whereDiagnosedGroup.append("CDG-THO Office Korangi", null);
		whereDiagnosedGroup.append("NGO-Sir Syed Medical University and Hospital", null);
		whereDiagnosedGroup.append("Infaq Foundation", null);
		whereDiagnosedGroup.append("Beechum Trust Hospital", null);
		whereDiagnosedGroup.append("Don't Know",null);
		whereDiagnosedGroup.append("Other",null);
	
		whereDiagnosedText = new TextField("If yes, from which diagnostic center?","",50,TextField.ANY);
		whereDiagnosedText.setConstraints(TextField.UNEDITABLE);
		
		indusLocationGroup = new ChoiceGroup("Which Indus clinic/doctor was the suspect planning on visiting today?", ChoiceGroup.POPUP);
		indusLocationGroup.append("N/A", null);
		indusLocationGroup.append("Filter Clinic", null);
		indusLocationGroup.append("Ghauri Clinic", null);
		indusLocationGroup.append("ER",null);
		indusLocationGroup.append("Other", null);
		
		otherIndusLocationField = new TextField("Specify: ", "", 50, TextField.ANY);
		otherIndusLocationField.setConstraints(TextField.UNEDITABLE);
		//--------
		
		
		conclusion = new ChoiceGroup("Conclusion", Choice.EXCLUSIVE);
		conclusion.append("Verified", null);
		conclusion.append("Not Verified", null);
		
		whyNotVerifiedField = new TextField("Why not?", "", 20, TextField.ANY);
		
		cmdOK = new Command("Save", Command.SCREEN, 1);
		cmdBack = new Command("Home", Command.BACK, 2);

		append(idField);
		append(idConfirm);
		append(chwIdField);
		append(gpIdField);
		append(dateField);
		append(didYouSeePatientGroup);
		append(takenTreatmentGroup);
		append(whereTreatmentGroup);
		append(whereTreatmentField);
		append(diagnosedInLastMonthGroup);
		append(whereDiagnosedGroup);
		append(whereDiagnosedText);
		append(indusLocationGroup);
		append(otherIndusLocationField);
		append(conclusion);
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
	}
	
	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
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
					
					/*else if(status.equals(XmlStrings.ERROR)) {
						tbrMidlet.showAlert((String)model.get("msg"),null);
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
		String request = null;
		int seenChoice = didYouSeePatientGroup.getSelectedIndex();
		int whyNotSeenChoice = whyNotSeenGroup.getSelectedIndex();
		int conclusionChoice = conclusion.getSelectedIndex();
		
		request = "type=svf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + "P" + idField.getString();
    	request += "&chwid=" +   chwIdField.getString();
    	request += "&gpid=" + gpIdField.getString();
    	request += "&seen=" + didYouSeePatientGroup.getString(seenChoice);
    	if(seenChoice==1) {
    		if(whyNotSeenChoice==0) {
    			request += "&wns=" + whyNotSeenGroup.getString(whyNotSeenChoice);
    		}
    		
    		else {
    			request += "&wns=" + otherWhyNotSeenField.getString();
    		}
    		
    	}
    	
    	else {
    		int takenIndex = takenTreatmentGroup.getSelectedIndex();
    		
    		request += "&tt=" + takenTreatmentGroup.getString(takenIndex);
    		if(takenIndex==0) {
    			
    			if(whereTreatmentGroup.getSelectedIndex()==TX_OTHER_INDEX)
    				request += "&wt=Other:" + whereTreatmentField.getString();
    			else
    				request += "&wt=" + whereTreatmentGroup.getString(whereTreatmentGroup.getSelectedIndex());
    		}
    		
    		int diagIndex = diagnosedInLastMonthGroup.getSelectedIndex();
    		
    		request += "&pd=" + diagnosedInLastMonthGroup.getString(diagIndex);
    		if(diagIndex==0) {
    			if(whereDiagnosedGroup.getSelectedIndex()==DIAG_OTHER_INDEX)
    				request += "&wd=Other:" + whereDiagnosedText.getString();
    			else
    				request += "&wd=" + whereDiagnosedGroup.getString(whereDiagnosedGroup.getSelectedIndex());
    		}
    		
    		int indusIndex = indusLocationGroup.getSelectedIndex();
    		
    		request += "&il=" + indusLocationGroup.getString(indusIndex);
    		if(indusIndex==4) {
    			request += "&wl=" + otherIndusLocationField.getString();
    		}
    		
    		
    	}
    	
    	request += "&conc=" + conclusion.getString(conclusionChoice);
    	if(conclusionChoice==1) {
    		request += "&wnv=" + whyNotVerifiedField.getString();
    	}
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
    	return request;
	}
	
	public boolean validate() {
		boolean result = true;
		int check = -1;
		if(idField.getString()==null || idField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.ID_MISSING,null);
			result = false;
		}
		
		else if((check=IdValidateUtil.validateId(idField.getString()))!=IdValidateUtil.ID_VALID) {
			if(check==IdValidateUtil.BAD_ID_LENGTH) {
				tbrMidlet.showAlert(ErrMsg.INVALID_ID_LENGTH,null);
			}
			
			else if(check==IdValidateUtil.BAD_GP_NUMBER) {
				tbrMidlet.showAlert(ErrMsg.INVALID_GP_NUMBER_IN_ID,null);
			}
			
			else if(check==IdValidateUtil.BAD_YEAR) {
				tbrMidlet.showAlert(ErrMsg.INVALID_YEAR_IN_ID,null);
			}
			
			result = false;
		}
		
		else if(!idField.getString().equals(idConfirm.getString())) {
			tbrMidlet.showAlert(ErrMsg.ID_MISMATCH,null);
			result = false;
		}
		
		else if(chwIdField.getString()==null || chwIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING, null);
			result = false;
		}
		
		else if (gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		
		else if(didYouSeePatientGroup.getSelectedIndex()==0 && whereTreatmentGroup.getSelectedIndex()==TX_OTHER_INDEX && (whereTreatmentField.getString()==null || whereTreatmentField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.TREATMENT_LOCATION_MISSING, null);
			result = false;
		}
		
		else if(didYouSeePatientGroup.getSelectedIndex()==0 && whereDiagnosedGroup.getSelectedIndex()==DIAG_OTHER_INDEX && (whereDiagnosedText.getString()==null || whereDiagnosedText.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.DIAG_LOCATION_MISSING, null);
			result = false;
		}
		
		else if(didYouSeePatientGroup.getSelectedIndex()==0 && indusLocationGroup.getSelectedIndex()==4 && (otherIndusLocationField.getString()==null || otherIndusLocationField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.INDUS_LOCATION_MISSING, null);
			result = false;
		}
		
		else if(didYouSeePatientGroup.getSelectedIndex()==0 && indusLocationGroup.getSelectedIndex()==2 ) {
			tbrMidlet.showAlert(ErrMsg.GHAURI_NOT_ALLOWED, null);
			result = false;
		}
		
		else if(didYouSeePatientGroup.getSelectedIndex()==1 && whyNotSeenGroup.getSelectedIndex()==1 && (otherWhyNotSeenField.getString()==null || otherWhyNotSeenField.getString().length()==0)) {
				tbrMidlet.showAlert(ErrMsg.MUST_ENTER_WHY_NOT_SEEN, null);
				result = false;
		}
		
		else if(conclusion.getSelectedIndex()==0 && didYouSeePatientGroup.getSelectedIndex()==1) {
			tbrMidlet.showAlert(ErrMsg.MUST_SEE_TO_VERIFY, null);
			result = false;
		}
		
		else if(conclusion.getSelectedIndex()==1 && (whyNotVerifiedField.getString()==null || whyNotVerifiedField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.MISSING_NOT_VERIFIED_REASON, null);
			result = false;
		}
		
		return result;
	}
	
	public void itemStateChanged(Item i) {
		if(i==didYouSeePatientGroup) {
			if(didYouSeePatientGroup.getSelectedIndex()==1) {
				
				for(int index=6;index<14;index++)
					delete(6);
				
				insert(6, whyNotSeenGroup);
				
				
			}
			
			else if(didYouSeePatientGroup.getSelectedIndex()==0) {
				delete(6);
				whyNotSeenGroup.setSelectedIndex(0,true);
				if(get(6)==otherWhyNotSeenField){
					delete(6);
				}
				
				insert(6,takenTreatmentGroup);
				insert(7, whereTreatmentGroup);
				insert(8, whereTreatmentField);
				insert(9, diagnosedInLastMonthGroup);
				insert(10, whereDiagnosedGroup);
				insert(11, whereDiagnosedText);
				insert(12, indusLocationGroup);
				insert(13, otherIndusLocationField);
			}
		}
		
		else if(i==whyNotSeenGroup) {
			if(whyNotSeenGroup.getSelectedIndex()==1) {
				insert(7, otherWhyNotSeenField);
			}
			
			else if(whyNotSeenGroup.getSelectedIndex()==0) {
				if(get(7)==otherWhyNotSeenField) {
					delete(7);
				}
			}
		}
		
		else if(i==conclusion) {
			if(conclusion.getSelectedIndex()==1) {
				append(whyNotVerifiedField);
			}
			
			else if((get(size()-1))==whyNotVerifiedField) {
				delete(size()-1);
			}
		}
		
		else if(i==takenTreatmentGroup) {
			int takenIndex = takenTreatmentGroup.getSelectedIndex();
			
			if(takenIndex==0) {
				
					if(whereTreatmentGroup.size()==1) {
						whereTreatmentGroup.append("Indus Hospital",null);
						whereTreatmentGroup.append("SGH Korangi", null);
						whereTreatmentGroup.append("CDG-SGH I.Haideri", null);
						whereTreatmentGroup.append("CDG-THO Office Korangi", null);
						whereTreatmentGroup.append("NGO-Sir Syed Medical University and Hospital", null);
						whereTreatmentGroup.append("Infaq Foundation", null);
						whereTreatmentGroup.append("Beechum Trust Hospital", null);
						whereTreatmentGroup.append("Don't Know",null);
						whereTreatmentGroup.append("Other",null);
					}
				
			}
			else {
				whereTreatmentField.setConstraints(TextField.UNEDITABLE);
				whereTreatmentField.setString("");
				whereTreatmentGroup.setSelectedIndex(0, true);
				if(whereTreatmentGroup.size()>1) {
					int kMax = whereTreatmentGroup.size();
				for(int k=1;k<kMax;k++) {
					whereTreatmentGroup.delete(1);
				}
				}
			}
		}
		
		else if(i==whereTreatmentGroup) {
			if(whereTreatmentGroup.getSelectedIndex()==TX_OTHER_INDEX) {
				whereTreatmentField.setConstraints(TextField.ANY);
			}
			
			else {
				whereTreatmentField.setConstraints(TextField.UNEDITABLE);
				whereTreatmentField.setString("");
			}
		}
		
		else if(i==diagnosedInLastMonthGroup) {
			int diagIndex = diagnosedInLastMonthGroup.getSelectedIndex();
			
			if(diagIndex==0) {
				if(whereDiagnosedGroup.size()==1) {
					whereDiagnosedGroup.append("Indus Hospital", null);
					whereDiagnosedGroup.append("SGH Korangi",null);
					whereDiagnosedGroup.append("CDG-SGH I.Haideri", null);
					whereDiagnosedGroup.append("CDG-THO Office Korangi", null);
					whereDiagnosedGroup.append("NGO-Sir Syed Medical University and Hospital", null);
					whereDiagnosedGroup.append("Infaq Foundation", null);
					whereDiagnosedGroup.append("Beechum Trust Hospital", null);
					whereDiagnosedGroup.append("Don't Know",null);
					whereDiagnosedGroup.append("Other",null);
				}
			}
				
			else {
				whereDiagnosedText.setConstraints(TextField.UNEDITABLE);
				whereDiagnosedText.setString("");
				whereDiagnosedGroup.setSelectedIndex(0, true);
				if(whereDiagnosedGroup.size()>1) {
					int kMax = whereDiagnosedGroup.size();
					for(int k=1;k<kMax;k++) {
						whereDiagnosedGroup.delete(1);
					}
				}
			}
		}
		
		else if(i==whereDiagnosedGroup) {
			if(whereDiagnosedGroup.getSelectedIndex()==DIAG_OTHER_INDEX) {
				whereDiagnosedText.setConstraints(TextField.ANY);
			}
			
			else {
				whereDiagnosedText.setConstraints(TextField.UNEDITABLE);
				whereDiagnosedText.setString("");
			}
		}
		
		else if(i==indusLocationGroup) {
			int indusIndex = indusLocationGroup.getSelectedIndex();
			
			if(indusIndex==4)
				otherIndusLocationField.setConstraints(TextField.ANY);
			else {
				otherIndusLocationField.setConstraints(TextField.UNEDITABLE);
				otherIndusLocationField.setString("");
				if(indusIndex==2) {
					tbrMidlet.showAlert(ErrMsg.GHAURI_NOT_ALLOWED, null);
				}
			}	
		}
		//else if()
	}

}
