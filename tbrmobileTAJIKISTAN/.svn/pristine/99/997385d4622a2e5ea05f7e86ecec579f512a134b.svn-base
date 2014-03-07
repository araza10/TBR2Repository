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

public class PatientTBHistoryForm extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	TextField idField;
	TextField idConfirm;
	TextField chwIdField;
	DateField dateField;
	
	ChoiceGroup currentFamilyHistoryGroup;
	ChoiceGroup currentFamilyMembersGroup;
	ChoiceGroup previousFamilyHistoryGroup;
	ChoiceGroup previousFamilyMembersGroup;
	
	ChoiceGroup pastDrugsGroup;
	TextField pastDrugsField;
	
	ChoiceGroup interruptedTreatmentGroup;
	TextField interruptedTreatmentField;
	
	ChoiceGroup streptomycinGroup;
	ChoiceGroup streptomycinWhyGroup;
	TextField streptomycinOtherDiseaseField;
	TextField streptomycinTimeField;
	
	ChoiceGroup urineColourGroup;
	ChoiceGroup urineColourWhyGroup;
	TextField urineColourOtherDiseaseField;
	TextField urineColourTimeField;
	

	/*ChoiceGroup patientHistoryGroup;
	ChoiceGroup previousTreatmentGroup;
	ChoiceGroup completedTreatmentGroup;
	ChoiceGroup patientTypeGroup;
	ChoiceGroup patientCategoryGroup;
	
	ChoiceGroup diseaseSiteGroup;*/
	
	Command cmdOK;
	Command cmdBack;
	
	public PatientTBHistoryForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		idField = null;
		idConfirm = null;
		chwIdField = null;
		currentFamilyHistoryGroup = null;
		currentFamilyMembersGroup = null;
		previousFamilyHistoryGroup = null;
		previousFamilyMembersGroup = null;
		
		/*patientHistoryGroup = null;
		previousTreatmentGroup = null;
		completedTreatmentGroup = null;
		patientTypeGroup = null;
		patientCategoryGroup = null;*/
		cmdOK = null;
		cmdBack = null;
		
	}
	
	public void init() {
		
		idField = new TextField("Suspect ID:", "", 10, TextField.NUMERIC);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 10, TextField.NUMERIC);
		chwIdField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		dateField = new DateField("Date:", DateField.DATE);
		dateField.setDate(new Date());
		
		currentFamilyHistoryGroup = new ChoiceGroup("Current Family History of TB", ChoiceGroup.POPUP);
		currentFamilyHistoryGroup.append("Yes", null);
		currentFamilyHistoryGroup.append("No", null);
		currentFamilyHistoryGroup.append("Refused", null);
		currentFamilyHistoryGroup.setSelectedIndex(1, true);
		
		currentFamilyMembersGroup = new ChoiceGroup("Family member(s) with current history of TB", ChoiceGroup.MULTIPLE);
		currentFamilyMembersGroup.append("Mother",null);
		currentFamilyMembersGroup.append("Father",null);
		currentFamilyMembersGroup.append("Brother",null);
		currentFamilyMembersGroup.append("Sister",null);
		currentFamilyMembersGroup.append("Daughter",null);
		currentFamilyMembersGroup.append("Son",null);
		currentFamilyMembersGroup.append("Grandfather",null);
		currentFamilyMembersGroup.append("Grandmother",null);
		currentFamilyMembersGroup.append("Granddaugher",null);
		currentFamilyMembersGroup.append("Grandson",null);
		currentFamilyMembersGroup.append("Uncle",null);
		currentFamilyMembersGroup.append("Aunt",null);
		currentFamilyMembersGroup.append("Other",null);
		
		previousFamilyHistoryGroup = new ChoiceGroup("Previous Family History of TB", ChoiceGroup.POPUP);
		previousFamilyHistoryGroup.append("Yes", null);
		previousFamilyHistoryGroup.append("No", null);
		previousFamilyHistoryGroup.append("Refused", null);
		previousFamilyHistoryGroup.setSelectedIndex(1, true);
		
		previousFamilyMembersGroup = new ChoiceGroup("Family member(s) with previous history of TB", ChoiceGroup.MULTIPLE);
		previousFamilyMembersGroup.append("Mother",null);
		previousFamilyMembersGroup.append("Father",null);
		previousFamilyMembersGroup.append("Brother",null);
		previousFamilyMembersGroup.append("Sister",null);
		previousFamilyMembersGroup.append("Daughter",null);
		previousFamilyMembersGroup.append("Son",null);
		previousFamilyMembersGroup.append("Grandfather",null);
		previousFamilyMembersGroup.append("Grandmother",null);
		previousFamilyMembersGroup.append("Granddaugher",null);
		previousFamilyMembersGroup.append("Grandson",null);
		previousFamilyMembersGroup.append("Uncle",null);
		previousFamilyMembersGroup.append("Aunt",null);
		previousFamilyMembersGroup.append("Other",null);
		
		pastDrugsGroup = new ChoiceGroup("Ever taken drugs for TB in the past?", ChoiceGroup.POPUP);
		pastDrugsGroup.append("Yes",null);
		pastDrugsGroup.append("No", null);
		pastDrugsGroup.append("Don't know", null);
		pastDrugsGroup.append("Refused", null);
		
		pastDrugsField = new TextField("If yes, For how long? (in months - write '44' if less than 1 month)","",2,TextField.NUMERIC);
		
		interruptedTreatmentGroup = new ChoiceGroup("Ever interrupted TB treatment?", ChoiceGroup.POPUP);
		interruptedTreatmentGroup.append("Yes",null);
		interruptedTreatmentGroup.append("No", null);
		interruptedTreatmentGroup.append("Don't know", null);
		
		interruptedTreatmentField = new TextField("If yes, For how long? (in months - write '44' if less than 1 month)","",2,TextField.NUMERIC);
	
		
		streptomycinGroup = new ChoiceGroup("Ever taken Streptomycin (powder or dry) injections?", ChoiceGroup.POPUP);
		streptomycinGroup.append("Yes", null);
		streptomycinGroup.append("No", null);
		streptomycinGroup.append("Don't know",null);
		
		streptomycinWhyGroup = new ChoiceGroup("If yes, For what disease?", Choice.EXCLUSIVE);
		streptomycinWhyGroup.append("TB",null);
		streptomycinWhyGroup.append("Other",null);
		
		streptomycinOtherDiseaseField = new TextField("Specify the disease:", "", 20, TextField.ANY);
		streptomycinTimeField = new TextField("If yes, For how long? (in months - write '44' if less than 1 month)","", 2, TextField.NUMERIC);
		
		urineColourGroup = new ChoiceGroup("Ever taken tablets which make urine colour red?", ChoiceGroup.POPUP);
		urineColourGroup.append("Yes", null);
		urineColourGroup.append("No", null);
		urineColourGroup.append("Don't know",null);
		urineColourGroup.append("Refused", null);

		
		urineColourWhyGroup = new ChoiceGroup("If yes, For what disease?", Choice.EXCLUSIVE);
		urineColourWhyGroup.append("TB",null);
		urineColourWhyGroup.append("Other",null);
		
		urineColourOtherDiseaseField = new TextField("Specify the disease:", "", 20, TextField.ANY);
		urineColourTimeField = new TextField("If yes, For how long? (in months - write '44' if less than 1 month)","", 2, TextField.NUMERIC);
		/*patientHistoryGroup = new ChoiceGroup("Patient History of TB", ChoiceGroup.EXCLUSIVE);
		patientHistoryGroup.append("Had TB Before", null);
		patientHistoryGroup.append("Never had TB", null);
		patientHistoryGroup.setSelectedIndex(1, true);

		previousTreatmentGroup = new ChoiceGroup("Received treatment for TB previously", ChoiceGroup.EXCLUSIVE);
		previousTreatmentGroup.append("Yes", null);
		previousTreatmentGroup.append("No", null);
		
		completedTreatmentGroup = new ChoiceGroup("Completed treatment for TB previously", ChoiceGroup.EXCLUSIVE);
		completedTreatmentGroup.append("Yes", null);
		completedTreatmentGroup.append("No", null);
		
		patientTypeGroup = new ChoiceGroup("Patient Type", ChoiceGroup.EXCLUSIVE);
		patientTypeGroup.append("New", null);
		patientTypeGroup.append("Relapse", null);
		patientTypeGroup.append("Transfer in", null);
		patientTypeGroup.append("Treatment after default", null);
		patientTypeGroup.append("Treatment after failure", null);
		
		patientCategoryGroup = new ChoiceGroup("Patient Category", ChoiceGroup.EXCLUSIVE);
		patientCategoryGroup.append("Cat I", null);
		patientCategoryGroup.append("Cat II", null);
		
		diseaseSiteGroup = new ChoiceGroup("Disease Site:", ChoiceGroup.EXCLUSIVE);
		diseaseSiteGroup.append("Pulmonary", null);
		diseaseSiteGroup.append("Extra Pulmonary", null);*/
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(idField);//0
		append(idConfirm);//1
		append(chwIdField);//2
		append(dateField);//3
		append(currentFamilyHistoryGroup);//4
		append(previousFamilyHistoryGroup);//5
		append(pastDrugsGroup);
		append(pastDrugsField);
		append(interruptedTreatmentGroup);
		append(interruptedTreatmentField);
		append(streptomycinGroup);// -8 OR -5 OR -2
		append(streptomycinWhyGroup);
		append(streptomycinOtherDiseaseField);
		streptomycinOtherDiseaseField.setConstraints(TextField.UNEDITABLE); 
		append(streptomycinTimeField);
		append(urineColourGroup); //size-4 OR size-1
		append(urineColourWhyGroup);
		append(urineColourOtherDiseaseField);
		urineColourOtherDiseaseField.setConstraints(TextField.UNEDITABLE); 
		append(urineColourTimeField); //size - 1
		
		//append(familyMembersGroup);//3
		//append(patientHistoryGroup);//4 If no, go to 7
		//append(previousTreatmentGroup);//5
		//append(completedTreatmentGroup);//6
		
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
	}
	
	public void itemStateChanged(Item i) {
		if(i == currentFamilyHistoryGroup) {
			if(currentFamilyHistoryGroup.getSelectedIndex()== 0 && get(5)!=currentFamilyMembersGroup) {
				insert(5, currentFamilyMembersGroup);
			}
			
			else if(currentFamilyHistoryGroup.getSelectedIndex()!= 0 && get(5)==currentFamilyMembersGroup){
				delete(5);
			}
		}
		
		else if(i==previousFamilyHistoryGroup) {
			if(previousFamilyHistoryGroup.getSelectedIndex()==0) {
				if(get(5)==previousFamilyHistoryGroup && get(6)!=previousFamilyMembersGroup) {
					insert(6, previousFamilyMembersGroup);
				}
				
				else if(get(6)==previousFamilyHistoryGroup && get(7)!=previousFamilyMembersGroup){
					insert(7, previousFamilyMembersGroup);
				}
			}
			
			else {
				if(get(6)==previousFamilyMembersGroup) {
					delete(6);
				}
				
				else if(get(7)==previousFamilyMembersGroup){
					delete(7);
				}
			}
		}
		
		else if(i==pastDrugsGroup) {
			if(pastDrugsGroup.getSelectedIndex()==1 || pastDrugsGroup.getSelectedIndex()==2 || pastDrugsGroup.getSelectedIndex()==3) {
				
				pastDrugsField.setString("");
				pastDrugsField.setConstraints(TextField.UNEDITABLE);
				interruptedTreatmentGroup.setSelectedIndex(1, true);
				interruptedTreatmentField.setString("");
				interruptedTreatmentField.setConstraints(TextField.UNEDITABLE);
			}
			
			else {
				pastDrugsField.setConstraints(TextField.NUMERIC);
			}
		}
		
		else if(i==interruptedTreatmentGroup) {
			if(interruptedTreatmentGroup.getSelectedIndex()==1 || interruptedTreatmentGroup.getSelectedIndex()==2) {
				
				interruptedTreatmentField.setString("");
				interruptedTreatmentField.setConstraints(TextField.UNEDITABLE);
			}
			
			else {
				interruptedTreatmentField.setConstraints(TextField.NUMERIC);
			}
		}
		
		else if(i==streptomycinGroup) {
			if(streptomycinGroup.getSelectedIndex()==1 ||  streptomycinGroup.getSelectedIndex()==2) {
				if(get(size()-5)==streptomycinGroup && get(size()-1)==urineColourGroup) { //urine colour stuff not expanded
					delete(size()-2);
					delete(size()-2);
					delete(size()-2);
				}
				
				else if(get(size()-8)==streptomycinGroup) { //urine stuff is expanded
					delete(size()-5);
					delete(size()-5);
					delete(size()-5);
				}
			}
			
			else if(streptomycinGroup.getSelectedIndex()==0) {
				if(get(size()-2)==streptomycinGroup ) {
										
					insert(size()-1, streptomycinWhyGroup);
					insert(size()-1, streptomycinOtherDiseaseField);
					insert(size()-1, streptomycinTimeField);
					
					
				}
				
				else if(get(size()-5)==streptomycinGroup && get(size()-1)==urineColourTimeField ) {
					insert(size()-4, streptomycinWhyGroup);
					insert(size()-4, streptomycinOtherDiseaseField);
					insert(size()-4, streptomycinTimeField);
				}
			}
		}
		
		else if(i==streptomycinWhyGroup) {
			if(streptomycinWhyGroup.getSelectedIndex()==1) {
				streptomycinOtherDiseaseField.setConstraints(TextField.ANY);
				
			}
			
			else {
				streptomycinOtherDiseaseField.setString("");
				streptomycinOtherDiseaseField.setConstraints(TextField.UNEDITABLE);
			}
		}
		
		else if(i==urineColourGroup) {
			if(urineColourGroup.getSelectedIndex()==1 || urineColourGroup.getSelectedIndex()==2) {
				if(get(size()-1)==urineColourTimeField) {
				delete(size()-1);
				delete(size()-1);
				delete(size()-1);
				}
			}
			
			else if(urineColourGroup.getSelectedIndex()==0 ) {
				if(get(size()-1)==urineColourGroup) {
					insert(size(), urineColourWhyGroup);
					insert(size(), urineColourOtherDiseaseField);
					insert(size(), urineColourTimeField);
				}
			}
			
		}
		
		else if(i==urineColourWhyGroup) {
			if(urineColourWhyGroup.getSelectedIndex()==1) {
				urineColourOtherDiseaseField.setConstraints(TextField.ANY);
				
			}
			
			else {
				urineColourOtherDiseaseField.setString("");
				urineColourOtherDiseaseField.setConstraints(TextField.UNEDITABLE);
			}
		}
		
		//else if
		
		/*else if(i==patientHistoryGroup) {
			if(patientHistoryGroup.getSelectedIndex()==0) {
				if(size()==8) {
					insert(5, previousTreatmentGroup);
					insert(6, completedTreatmentGroup);
				}
				else if(size()==7) {
					insert(4, previousTreatmentGroup);
					insert(5, completedTreatmentGroup);
				}
			}
			
			else {
				if(size()==10) {
					delete(5);
					delete(5);
				}
				
				else if(size()==9) {
					delete(4);
					delete(4);
				}
			}
		}*/
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
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING,null);
			result = false;
		}
		
		else if(pastDrugsGroup.getSelectedIndex()==0) {
			String pastDrugsMonths = pastDrugsField.getString();
			if(pastDrugsMonths==null || pastDrugsMonths.length()==0) {
				tbrMidlet.showAlert(ErrMsg.PAST_TREATMENT_TIME_MISSING, null);
				result = false;
			}
			
			else if(Integer.parseInt(pastDrugsMonths)==0) {
				tbrMidlet.showAlert(ErrMsg.PAST_TREATMENT_TIME_INVALID, null);
				result = false;
			}
		}
		
		if(result==true && interruptedTreatmentGroup.getSelectedIndex()==0) {
			String interruptedMonths = interruptedTreatmentField.getString();
			if(interruptedMonths==null || interruptedMonths.length()==0) {
				tbrMidlet.showAlert(ErrMsg.INTERRUPTED_TREATMENT_TIME_MISSING, null);
				result = false;
			}
			
			else if(Integer.parseInt(interruptedMonths)==0) {
				tbrMidlet.showAlert(ErrMsg.INTERRUPTED_TREATMENT_TIME_INVALID, null);
				result = false;
			}
		}
		
		if(result==true && streptomycinGroup.getSelectedIndex()==0) {
		
			if(streptomycinWhyGroup.getSelectedIndex()==1 && (streptomycinOtherDiseaseField.getString()==null || streptomycinOtherDiseaseField.getString().length()==0)) {
				tbrMidlet.showAlert(ErrMsg.STREPTO_DISEASE_MISSING, null);
				result = false;
			}
			
			else {
				String streptoMonths = streptomycinTimeField.getString();
				
				if(streptoMonths==null || streptoMonths.length()==0) {
					tbrMidlet.showAlert(ErrMsg.STREPTO_TIME_MISSING, null);
					result = false;
				}
				
				else if(Integer.parseInt(streptoMonths)==0) {
					tbrMidlet.showAlert(ErrMsg.STREPTO_TIME_INVALID, null);
					result = false;
				}
			}
		
		}
		
		
		if(result==true && urineColourGroup.getSelectedIndex()==0) {
			
			if(urineColourWhyGroup.getSelectedIndex()==1 && (urineColourOtherDiseaseField.getString()==null || urineColourOtherDiseaseField.getString().length()==0)) {
				tbrMidlet.showAlert(ErrMsg.TABLETS_DISEASE_MISSING, null);
				result = false;
			}
			
			else {
				String ucMonths = urineColourTimeField.getString();
				
				if(ucMonths==null || ucMonths.length()==0) {
					tbrMidlet.showAlert(ErrMsg.TABLETS_TIME_MISSING, null);
					result = false;
				}
				
				else if(Integer.parseInt(ucMonths)==0) {
					tbrMidlet.showAlert(ErrMsg.TABLETS_TIME_INVALID, null);
					result = false;
				}
			}
		
		}
	
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		int currentFamilyChoice = currentFamilyHistoryGroup.getSelectedIndex();
		int previousFamilyChoice = previousFamilyHistoryGroup.getSelectedIndex();
		//int patientChoice = patientHistoryGroup.getSelectedIndex();
		
		request = "type=ptbf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + idField.getString();
    	request += "&chwid=" + chwIdField.getString();
    	request += "&cfh=" + currentFamilyHistoryGroup.getString(currentFamilyChoice);
    	if(currentFamilyChoice==0) {
    		request += "&cfm=";
    		boolean[] currentChoices = new boolean[currentFamilyMembersGroup.size()];
    		currentFamilyMembersGroup.getSelectedFlags(currentChoices);
    		for(int i=0; i<currentChoices.length; i++) {
    			if(currentChoices[i]) {
    				request += i + "|";
    			}
    		}
    	
    		if(request.charAt((request.length())-1)=='|') {
    			request = request.substring(0, request.length()-1);
    		}
    	}
    	request += "&pfh=" + previousFamilyHistoryGroup.getString(previousFamilyChoice);
    	if(previousFamilyChoice==0) {
    		request += "&pfm=";//add here
    		boolean[] previousChoices = new boolean[previousFamilyMembersGroup.size()];
    		previousFamilyMembersGroup.getSelectedFlags(previousChoices);
    		for(int i=0; i<previousChoices.length; i++) {
    			if(previousChoices[i]) {
    				request += i + "|";
    			}
    		}
    	
    		if(request.charAt((request.length())-1)=='|') {
    			request = request.substring(0, request.length()-1);
    		}
    	}
    	
    	int pastDrugsChoice  = pastDrugsGroup.getSelectedIndex();
    	request += "&ptd=" + pastDrugsGroup.getString(pastDrugsChoice);
    	
    	if(pastDrugsChoice==0) {
    		request += "&pdttm=" + pastDrugsField.getString();
    	}
    	
    	int intTreatmentChoice  = interruptedTreatmentGroup.getSelectedIndex();
    	request += "&it=" + interruptedTreatmentGroup.getString(intTreatmentChoice);
    	if(intTreatmentChoice==0) {
    		request += "&ittm=" + interruptedTreatmentField.getString();
    	}
    	
    	int streptoChoice = streptomycinGroup.getSelectedIndex();
    	
    	request += "&strep=" + streptomycinGroup.getString(streptoChoice);
    	
    	if(streptoChoice==0) {
    		int streptoWhyChoice = streptomycinWhyGroup.getSelectedIndex();
    		
    		if(streptoWhyChoice==0) {
    			request += "&strepd=" + streptomycinWhyGroup.getString(streptoWhyChoice);
    		}
    		else {
    			request += "&strepd=" + streptomycinOtherDiseaseField.getString();
    		}
    		
    		request += "&strept=" + streptomycinTimeField.getString();
    	}
    	
    	int tabletChoice = urineColourGroup.getSelectedIndex();
    	
    	request += "&tab=" + urineColourGroup.getString(tabletChoice);
    	
    	if(tabletChoice==0) {
    		int tabletWhyChoice = urineColourWhyGroup.getSelectedIndex();
    		
    		if(tabletWhyChoice==0) {
    			request += "&tabd=" + urineColourWhyGroup.getString(tabletWhyChoice);
    		}
    		else {
    			request += "&tabd=" + urineColourOtherDiseaseField.getString();
    		}
    		
    		request += "&tabt=" + urineColourTimeField.getString();
    	}
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
		request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		return request;
	}

}
