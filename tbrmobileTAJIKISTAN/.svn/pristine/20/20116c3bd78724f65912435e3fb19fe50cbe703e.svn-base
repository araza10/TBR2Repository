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

public class PaedConfirmationForm extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	DateField dateField;
	TextField gpIdField;
	TextField weightField;
	ChoiceGroup weightPercentileGroup;
	ChoiceGroup cough;
	ChoiceGroup coughDuration;
	ChoiceGroup productiveCough;
	ChoiceGroup fever;
	ChoiceGroup nightSweat;
	ChoiceGroup weightLoss;
	ChoiceGroup haemoptysis;
	ChoiceGroup appetite;
	
	ChoiceGroup chestExam;
	ChoiceGroup lymphExam;
	ChoiceGroup abdominalMass;
	ChoiceGroup otherExam;
	TextField otherDetail;
	ChoiceGroup bcgScar;
		
	ChoiceGroup currentFamilyHistory;
	TextField numCurrentFamilyHistory;
	
	ChoiceGroup previousFamilyHistory;
	TextField numPreviousFamilyHistory;
	
	ChoiceGroup conclusion;
	
	PCFFormlet[] currentHistory;
	PCFFormlet[] previousHistory;
	
	private Hashtable queryData;
	
	private String patientId;
	
	
	Command cmdOK;
	Command cmdBack;
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Hashtable getQueryData() {
		return queryData;
	}

	public void setQueryData(Hashtable queryData) {
		this.queryData = queryData;
	}
	
	public PaedConfirmationForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
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
		
		if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING,null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		
		else if(weightField.getString()==null || weightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.WEIGHT_MISSING,null);
			result = false;
		}
		
		else if(otherExam.getSelectedIndex()!=3 && (otherDetail.getString()==null || otherDetail.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.OTHER_EXAM_MISSING,null);
			result = false;
		}
		
		else if(currentFamilyHistory.getSelectedIndex()==0 && (numCurrentFamilyHistory.getString()==null || numCurrentFamilyHistory.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.MUST_ENTER_NUM_CURRENT_HISTORY, null);
			result = false;
		}
		
		else if(previousFamilyHistory.getSelectedIndex()==0 && (numPreviousFamilyHistory.getString()==null || numPreviousFamilyHistory.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.MUST_ENTER_NUM_PREVIOUS_HISTORY, null);
			result = false;
		}
		
		if(result && currentHistory!=null) {
			for(int i=0; i<currentHistory.length;i++) {
				
				if(currentHistory[i].relationshipGroup.getSelectedIndex()==8 && (currentHistory[i].otherField.getString() ==null || currentHistory[i].otherField.getString().length()==0)) {
					tbrMidlet.showAlert("Current TB history walay Family Member " + (i+1) + " ki patient se rishtadari likhna zaroori hai", null);
					result = false;
					break;
				}
			}
		}
		
		if(result && previousHistory!=null) {
			for(int i=0; i<previousHistory.length;i++) {
				if(previousHistory[i].relationshipGroup.getSelectedIndex()==8 && (previousHistory[i].otherField.getString() ==null || previousHistory[i].otherField.getString().length()==0)) {
					tbrMidlet.showAlert("Previous TB history walay Family Member " + (i+1) + " ki patient se rishtadari likhna zaroori hai", null);
					result = false;
					break;
				}
			}
		}
		
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = "";
		
		request = "type=pcf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&gpid=" + gpIdField.getString();
    	
    	request += "&wt=" + weightField.getString();
    	request += "&wp=" + weightPercentileGroup.getString(weightPercentileGroup.getSelectedIndex());
    	
    	//get cough data
    	int coughIndex = cough.getSelectedIndex();
    	
    	request += "&cough=" + cough.getString(coughIndex);
    	
    	int coughDurationIndex = coughDuration.getSelectedIndex();
    		
    	request += "&cd=" + coughDuration.getString(coughDurationIndex);
    	int productiveCoughIndex = productiveCough.getSelectedIndex();
    			
    	request += "&pc=" + productiveCough.getString(productiveCoughIndex);
    	request += "&fev=" + fever.getString(fever.getSelectedIndex());
		request += "&ns=" + nightSweat.getString(nightSweat.getSelectedIndex());
		request += "&wl=" + weightLoss.getString(weightLoss.getSelectedIndex());
		request += "&ha=" + haemoptysis.getString(haemoptysis.getSelectedIndex());	
    	request += "&app=" + appetite.getString(appetite.getSelectedIndex());
    	request += "&chest=" + chestExam.getString(chestExam.getSelectedIndex());
    	request += "&lymph=" + lymphExam.getString(lymphExam.getSelectedIndex());
    	request += "&abdm=" + abdominalMass.getString(abdominalMass.getSelectedIndex());
    	request += "&otherEx=" + otherExam.getString(otherExam.getSelectedIndex());
    	if(otherExam.getSelectedIndex()!=3) {
    		request += "&otherDet=" + otherDetail.getString();
    	}
    	
    	request += "&bcg=" + bcgScar.getString(bcgScar.getSelectedIndex());
    	request += "&cfh=" + currentFamilyHistory.getString(currentFamilyHistory.getSelectedIndex());
    	if(currentFamilyHistory.getSelectedIndex()==0) {
    		int numCfh = Integer.parseInt(numCurrentFamilyHistory.getString());
    		
    		request += "&numcfh=" + numCfh;
    		
    		for (int q=0; q<currentHistory.length;q++) {
    			PCFFormlet temp = currentHistory[q];
    			request += "&crel" + (q+1) + "=" + temp.relationshipGroup.getString(temp.relationshipGroup.getSelectedIndex()).toUpperCase();
    			request += "&cothrel" + (q+1) + "=" + temp.otherField.getString().toUpperCase();
    			request += "&ctbf" + (q+1) + "=" + temp.tbFormGroup.getString(temp.tbFormGroup.getSelectedIndex()).toUpperCase();
    			request += "&ctbt" + (q+1) + "=" + temp.tbTypeGroup.getString(temp.tbTypeGroup.getSelectedIndex()).toUpperCase();
    			request += "&css" + (q+1) + "=" + temp.ssPositiveGroup.getString(temp.ssPositiveGroup.getSelectedIndex()).toUpperCase();
    		}
    	}
    	
    	request += "&pfh=" + previousFamilyHistory.getString(previousFamilyHistory.getSelectedIndex());
    	if(previousFamilyHistory.getSelectedIndex()==0) {
    		int numPfh = Integer.parseInt(numPreviousFamilyHistory.getString());
    		
    		request += "&numpfh=" + numPfh;
    		
    		for (int q=0; q<previousHistory.length;q++) {
    			PCFFormlet temp = previousHistory[q];
    			request += "&prel" + (q+1) + "=" + temp.relationshipGroup.getString(temp.relationshipGroup.getSelectedIndex()).toUpperCase();
    			request += "&pothrel" + (q+1) + "=" + temp.otherField.getString().toUpperCase();
    			request += "&ptbf" + (q+1) + "=" + temp.tbFormGroup.getString(temp.tbFormGroup.getSelectedIndex()).toUpperCase();
    			request += "&ptbt" + (q+1) + "=" + temp.tbTypeGroup.getString(temp.tbTypeGroup.getSelectedIndex()).toUpperCase();
    			request += "&pss" + (q+1) + "=" + temp.ssPositiveGroup.getString(temp.ssPositiveGroup.getSelectedIndex()).toUpperCase();
    		}
    	}
    	   	
    	request += "&conc=" + conclusion.getString(conclusion.getSelectedIndex());
  
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}
	
	public void itemStateChanged(Item i) {
		
		//set current and previous history to "No" and disable fields by default
		if(i==cough) {
			if(cough.getSelectedIndex()==0) {
				if(coughDuration.size()!=5) {
					coughDuration.deleteAll();
					coughDuration.append("less than 2 weeks", null);
					coughDuration.append("2 to 3 weeks", null);
					coughDuration.append("more than 3 weeks", null);
					coughDuration.append("Refused", null);
					coughDuration.append("Don't Know", null);
					coughDuration.setSelectedIndex(1,true);
				}
				
				if(productiveCough.size()!=4) {
					productiveCough.deleteAll();
					productiveCough.append("Yes", null);
					productiveCough.append("No", null);
					productiveCough.append("Refused", null);					
					productiveCough.append("Don't Know", null);
					productiveCough.setSelectedIndex(1,true);
				}
			}
			
			else {
				if(coughDuration.size()!=1) {
					coughDuration.deleteAll();
					coughDuration.append("N/A", null);
					
				}
				
				if(productiveCough.size()!=1) {
					productiveCough.deleteAll();
					productiveCough.append("N/A", null);
					
				}
			}
		}
		
		else if(i==otherExam) {
			if(otherExam.getSelectedIndex()!=3) {
				otherDetail.setConstraints(TextField.ANY);
			}
			
			else {
				otherDetail.setString("");
				otherDetail.setConstraints(TextField.UNEDITABLE);
			}
		}
		
		else if(i==currentFamilyHistory) {
			if(currentFamilyHistory.getSelectedIndex()==0) {
				numCurrentFamilyHistory.setConstraints(TextField.NUMERIC);
			}
			else {
				numCurrentFamilyHistory.setConstraints(TextField.UNEDITABLE);
				numCurrentFamilyHistory.setString("");
			}
		}
		
		else if(i==previousFamilyHistory) {
			if(previousFamilyHistory.getSelectedIndex()==0) {
				numPreviousFamilyHistory.setConstraints(TextField.NUMERIC);
			}
			else {
				numPreviousFamilyHistory.setConstraints(TextField.UNEDITABLE);
				numPreviousFamilyHistory.setString("");
			}
		}
		
		else if(i==numCurrentFamilyHistory) {
			if(currentHistory!=null) {
				int size = currentHistory.length;
				for(int del = 0; del<size; del++) {
					delete(20);
					delete(20);
					delete(20);
					delete(20);
					delete(20);
					delete(20);
				}
				
				currentHistory = null;
			}
			
			if(numCurrentFamilyHistory.getString()!=null && numCurrentFamilyHistory.getString().length()!=0) {
			int num = Integer.parseInt((numCurrentFamilyHistory.getString()));
			currentHistory = new PCFFormlet[num];
			for(int index=0;index<num;index++) {
				currentHistory[index] = new PCFFormlet();
				currentHistory[index].init();
				currentHistory[index].addToForm(this, (index*6) + 21, index+1);
			}
			
		}
			
		}
		
		if(i==numPreviousFamilyHistory) {
			if(previousHistory!=null) {
			 	int size = previousHistory.length;
				for(int del = 0; del<size; del++) {
					delete(size()-2);
					delete(size()-2);
					delete(size()-2);
					delete(size()-2);
					delete(size()-2);
					delete(size()-2);
				}
				
				previousHistory = null;
			}
			
			if(numPreviousFamilyHistory.getString()!=null && numPreviousFamilyHistory.getString().length()!=0) {
			int num = Integer.parseInt((numPreviousFamilyHistory.getString()));
			previousHistory = new PCFFormlet[num];
			for(int index=0;index<num;index++) {
				previousHistory[index] = new PCFFormlet();
				previousHistory[index].init();
				previousHistory[index].addToForm(this, size()-1, index+1);
			}
			
			}
		}
		
		if(currentHistory!=null) {
		for(int q=0; q<currentHistory.length; q++) {
			if(i==currentHistory[q].relationshipGroup) {
				if(currentHistory[q].relationshipGroup.getSelectedIndex()==8) {
					currentHistory[q].otherField.setConstraints(TextField.ANY);
				}
				
				else {
					currentHistory[q].otherField.setString("");
					currentHistory[q].otherField.setConstraints(TextField.UNEDITABLE);
				}
			}
			
			else if(i==currentHistory[q].tbTypeGroup) {
				if(currentHistory[q].tbTypeGroup.getSelectedIndex()==0) {
					if(currentHistory[q].ssPositiveGroup.size()!=4) {
						currentHistory[q].ssPositiveGroup.deleteAll();
						currentHistory[q].ssPositiveGroup.append("Yes",null);
						currentHistory[q].ssPositiveGroup.append("No",null);
						currentHistory[q].ssPositiveGroup.append("Don't Know", null);
						currentHistory[q].ssPositiveGroup.append("Refused",null);
						
					}
				}
					
					else if(currentHistory[q].ssPositiveGroup.size()!=1) {
						currentHistory[q].ssPositiveGroup.deleteAll();
						currentHistory[q].ssPositiveGroup.append("N/A",null);
					}
				
			}
		}
		}
		
		if(previousHistory!=null) {
			
		for(int q=0; q<previousHistory.length; q++) {
			if(i==previousHistory[q].relationshipGroup) {
				if(previousHistory[q].relationshipGroup.getSelectedIndex()==8) {
					previousHistory[q].otherField.setConstraints(TextField.ANY);
				}
				
				else {
					previousHistory[q].otherField.setString("");
					previousHistory[q].otherField.setConstraints(TextField.UNEDITABLE);
				}
			}
			
			else if(i==previousHistory[q].tbTypeGroup) {
				if(previousHistory[q].tbTypeGroup.getSelectedIndex()==0) {
					if(previousHistory[q].ssPositiveGroup.size()!=4) {
						previousHistory[q].ssPositiveGroup.deleteAll();
						previousHistory[q].ssPositiveGroup.append("Yes",null);
						previousHistory[q].ssPositiveGroup.append("No",null);
						previousHistory[q].ssPositiveGroup.append("Don't Know", null);
						previousHistory[q].ssPositiveGroup.append("Refused",null);
						
					}
				}
				else if(previousHistory[q].ssPositiveGroup.size()!=1) {
						previousHistory[q].ssPositiveGroup.deleteAll();
						previousHistory[q].ssPositiveGroup.append("N/A",null);
					}
				
			}
		}
		}
	}
	
	public void init() {
		
		String data = "";
		
		if(queryData!=null) {
			data += "Patient ID: " + patientId;
			data += "\nMR No: " + "20110001234";
			data += "\nFirst Name: " + "Test";
			data += "\nLast Name: " + "Patient";
			data += "\nAge: " + "45";
			data += "\nGender: " + "Female";
			
			append(data);
		}
		
		dateField = new DateField("Date:",DateField.DATE);
		dateField.setDate(new Date());
		gpIdField = new TextField("GP ID:", "", 12, TextField.ANY);
		weightField = new TextField("Weight (kg):", "", 8, TextField.DECIMAL);
		
		weightPercentileGroup =  new ChoiceGroup("Weight Percentile", ChoiceGroup.POPUP);
		weightPercentileGroup.append("<=5th Percentile", null);
		weightPercentileGroup.append("6-<=10th  Percentile", null);
		weightPercentileGroup.append("11-<=25th Percentile", null);
		weightPercentileGroup.append("26-<=50th Percentile", null);
		weightPercentileGroup.append(">50th Percentile", null);
		weightPercentileGroup.append("Don't Know", null);
		
		cough = new ChoiceGroup("Cough", ChoiceGroup.POPUP);
		cough.append("Yes",null);
		cough.append("No",null);
		cough.append("Refused", null);
		cough.append("Don't Know", null);
		
		coughDuration=  new ChoiceGroup("How many weeks of cough?", ChoiceGroup.POPUP);
		coughDuration.append("less than 2 weeks", null);
		coughDuration.append("2 to 3 weeks", null);
		coughDuration.append("more than 3 weeks", null);
		coughDuration.append("Refused", null);
		coughDuration.append("Don't Know", null);
		coughDuration.setSelectedIndex(1,true);
		
		productiveCough = new ChoiceGroup("Is the cough productive?", ChoiceGroup.POPUP);
		productiveCough.append("Yes",null);
		productiveCough.append("No", null);
		productiveCough.append("Refused", null);
		productiveCough.append("Don't know", null);
		productiveCough.setSelectedIndex(1, true);
		
		fever = new ChoiceGroup("Fever?", Choice.POPUP);
		fever.append("Yes",  null);
		fever.append("No", null);
		fever.append("Refused", null);
		fever.append("Don't know", null);
		
		nightSweat = new ChoiceGroup("Night Sweats?", Choice.POPUP);
		nightSweat.append("Yes", null);
		nightSweat.append("No", null);
		nightSweat.append("Refused", null);
		nightSweat.append("Don't know", null);
		
		weightLoss = new ChoiceGroup("Unexplained Weight Loss?", Choice.POPUP);
		weightLoss.append("Yes", null);
		weightLoss.append("No", null);
		weightLoss.append("Refused", null);
		weightLoss.append("Don't know", null);
		
		haemoptysis = new ChoiceGroup("Haemoptysis?", Choice.POPUP);
		haemoptysis.append("Yes", null);
		haemoptysis.append("No", null);
		haemoptysis.append("Refused", null);
		haemoptysis.append("Don't know", null);
		
		appetite = new ChoiceGroup("Appetite of Patient?", Choice.POPUP);
		appetite.append("Poor", null);
		appetite.append("OK", null);
		appetite.append("Refused", null);
		appetite.append("Don't know", null);
		
		chestExam = new ChoiceGroup("Chest Examination", ChoiceGroup.POPUP);
		chestExam.append("Suggestive",null);
		chestExam.append("Strongly Suggestive",null);
		chestExam.append("Not Suggestive",null);
		chestExam.append("N/A",null);
		
		lymphExam = new ChoiceGroup("Lymph Node Examination", ChoiceGroup.POPUP);
		lymphExam.append("Suggestive",null);
		lymphExam.append("Strongly Suggestive",null);
		lymphExam.append("Not Suggestive",null);
		lymphExam.append("N/A",null);
		
		abdominalMass = new ChoiceGroup("Abdominal Mass", ChoiceGroup.POPUP);
		abdominalMass.append("Suggestive",null);
		abdominalMass.append("Strongly Suggestive",null);
		abdominalMass.append("Not Suggestive",null);
		abdominalMass.append("N/A",null);
		
		otherExam = new ChoiceGroup("Other Examination", ChoiceGroup.POPUP);
		otherExam.append("Suggestive",null);
		otherExam.append("Strongly Suggestive",null);
		otherExam.append("Not Suggestive",null);
		otherExam.append("N/A",null);
		otherExam.setSelectedIndex(3, true);
		
		otherDetail = new TextField("Other Examination (specify)","",50, TextField.ANY);
		otherDetail.setConstraints(TextField.UNEDITABLE);
		
		bcgScar = new ChoiceGroup("Has the Child received BCG and do they have a scar?", ChoiceGroup.POPUP);
		bcgScar.append("Received, scar present",null);
		bcgScar.append("Received, no scar", null);
		bcgScar.append("Not Received",null);
		bcgScar.append("Refused", null);
		bcgScar.append("Don't Know", null);
		
		currentFamilyHistory = new ChoiceGroup("Currently does an adult family member have TB?",ChoiceGroup.POPUP);
		currentFamilyHistory.append("Yes", null);
		currentFamilyHistory.append("No", null);
		currentFamilyHistory.append("Refused", null);
		currentFamilyHistory.append("Don't know", null);
		currentFamilyHistory.setSelectedIndex(1,true);
		
		numCurrentFamilyHistory = new TextField("Number of adult family members with TB","",3,TextField.NUMERIC);
		numCurrentFamilyHistory.setConstraints(TextField.UNEDITABLE);
				
		previousFamilyHistory = new ChoiceGroup("Has an adult family member ever had TB",ChoiceGroup.POPUP);
		previousFamilyHistory.append("Yes", null);
		previousFamilyHistory.append("No", null);
		previousFamilyHistory.append("Refused", null);
		previousFamilyHistory.append("Don't know", null);
		previousFamilyHistory.setSelectedIndex(1,true);
		
		numPreviousFamilyHistory = new TextField("Number of adult family members who have had TB","",3,TextField.NUMERIC);
		numPreviousFamilyHistory.setConstraints(TextField.UNEDITABLE);
		
		conclusion = new ChoiceGroup("Conclusion", Choice.POPUP);
		conclusion.append("Confirmed", null);
		conclusion.append("Not Confirmed", null);
		
		append(dateField);//1
		append(gpIdField);//2
		append(weightField);//3
		append(weightPercentileGroup);//4
		append(cough);//5
		append(coughDuration);//6
		append(productiveCough);//7
		append(fever);//8
		append(nightSweat);//9
		append(weightLoss);//10
		append(haemoptysis);//11
		append(appetite);//12
		append(chestExam);//13
		append(lymphExam);//14
		append(abdominalMass);//15
		append(otherExam);//16
		append(otherDetail);//17
		append(bcgScar);//18
		append(currentFamilyHistory);//19
		append(numCurrentFamilyHistory);//20
		
		append(previousFamilyHistory);//size()-3
		append(numPreviousFamilyHistory);//size()-2
		append(conclusion);//size()-1
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
	}

}
