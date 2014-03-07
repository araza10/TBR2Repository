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
import org.irdresearch.tbreach.mobile.constants.UserType;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class ContactTracingNewSuspect extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	DateField dateField;
	TextField idField;
	TextField idConfirm;
	TextField chwIdField;
	TextField gpIdField;
	TextField firstNameField;
	TextField lastNameField;
	TextField ageField;
	
	ChoiceGroup sexGroup;
	ChoiceGroup cough;
	ChoiceGroup coughDuration;
	ChoiceGroup productiveCough;
	ChoiceGroup tbHistory;
	
	ChoiceGroup fever;
	ChoiceGroup nightSweat;
	ChoiceGroup weightLoss;
	ChoiceGroup haemoptysis;
	ChoiceGroup diabetes;
	ChoiceGroup ageLessThanFive;
	
	Command cmdOK;
	Command cmdBack;

	public ContactTracingNewSuspect(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		idField = null;
		idConfirm = null;
		chwIdField = null;
		gpIdField = null;
		dateField = null;
		firstNameField = null;
		lastNameField = null;	
		sexGroup = null;
		ageField = null;
		
		dateField = null;
		productiveCough = null;
		fever = null;
		nightSweat = null;
		weightLoss = null;
		haemoptysis = null;
		diabetes = null;
		ageLessThanFive = null;
	}
	
	public void init() {
		idField = new TextField("Suspect ID:", "", 10, TextField.NUMERIC);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 10, TextField.NUMERIC);
		chwIdField = new TextField("CHW ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		
		gpIdField = new TextField("GP ID:", "",12, TextField.ANY);
		
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		firstNameField = new TextField("Suspect First Name:", "", 15, TextField.ANY);
		lastNameField = new TextField("Suspect Last Name:", "", 15, TextField.ANY);
		
		sexGroup = new ChoiceGroup("Gender:", Choice.POPUP);
		sexGroup.append("Male",null);
		sexGroup.append("Female", null);
		
		ageField = new TextField("Age:", "", 3, TextField.NUMERIC);
		//phoneField = new TextField("Phone:" , "", 20, TextField.ANY);
		
		cough = new ChoiceGroup("Do you have a cough?", ChoiceGroup.POPUP);
		cough.append("Yes",null);
		cough.append("No",null);
		cough.append("Refused",null);
		cough.append("Don't Know", null);
		
		coughDuration=  new ChoiceGroup("How many weeks have you had the cough?", ChoiceGroup.POPUP);
		coughDuration.append("less than 2 weeks", null);
		coughDuration.append("2 to 3 weeks", null);
		coughDuration.append("more than 3 weeks", null);
		coughDuration.append("Don't Know", null);
		coughDuration.setSelectedIndex(1,true);
		
		
		productiveCough = new ChoiceGroup("Do you have a productive cough?", ChoiceGroup.POPUP);
		productiveCough.append("Yes",null);
		productiveCough.append("No", null);
		productiveCough.append("Refused", null);
		productiveCough.append("Don't know", null);
		productiveCough.setSelectedIndex(0, true);
		
		tbHistory = new ChoiceGroup("Have you ever had TB before?", ChoiceGroup.POPUP);
		tbHistory.append("Yes",null);
		tbHistory.append("No", null);
		tbHistory.append("Refused", null);
		tbHistory.append("Don't Know", null);
		
		fever = new ChoiceGroup("Have you had fever during the last two weeks?", Choice.POPUP);
		fever.append("Yes",  null);
		fever.append("No", null);
		fever.append("Refused", null);
		fever.append("Don't know", null);
		
		nightSweat = new ChoiceGroup("Have you had night sweats within the last two weeks?", Choice.POPUP);
		nightSweat.append("Yes", null);
		nightSweat.append("No", null);
		nightSweat.append("Refused", null);
		nightSweat.append("Don't know", null);
		
		weightLoss = new ChoiceGroup("Have you had unexplained weightloss within the last two weeks??", Choice.POPUP);
		weightLoss.append("Yes", null);
		weightLoss.append("No", null);
		weightLoss.append("Refused", null);
		weightLoss.append("Don't know", null);
		
		haemoptysis = new ChoiceGroup("Do you have blood in your cough?", Choice.POPUP);
		haemoptysis.append("Yes", null);
		haemoptysis.append("No", null);
		haemoptysis.append("Refused", null);
		haemoptysis.append("Don't know", null);
		
		diabetes = new ChoiceGroup("Have you been diagnosed with diabetes?", Choice.POPUP);
		diabetes.append("Yes, Verified", null);
		diabetes.append("No", null);
		diabetes.append("Refused", null);
		diabetes.append("Don't know", null);
		
		ageLessThanFive = new ChoiceGroup("Is the household contact age 5 years or below?", Choice.POPUP);
		ageLessThanFive.append("Yes, Verified", null);
		ageLessThanFive.append("No", null);
		ageLessThanFive.append("Refused", null);
		ageLessThanFive.append("Don't know", null);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);

		append(dateField);
		append(idField);
		append(idConfirm);
		append(chwIdField);
		append(gpIdField);
		append(firstNameField);
		append(lastNameField);
		append(sexGroup);
		append(ageField);
		append(cough);
		append(coughDuration);
		append(productiveCough);
		append(tbHistory);
		
		append(fever);
		append(nightSweat);
		append(weightLoss);
		append(haemoptysis);
		append(diabetes);
		append(ageLessThanFive);
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
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
		
		else if(firstNameField.getString()==null || firstNameField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.FIRST_NAME_MISSING, null);
			result = false;
		}
		
		else if(lastNameField.getString()==null || lastNameField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.LAST_NAME_MISSING, null);
			result = false;	
		}
		
		else if(ageField.getString()==null || ageField.getString().length() ==0) {
			tbrMidlet.showAlert(ErrMsg.AGE_MISSING, null);
			result = false;	
		}
		
		if(result) {
			if(!isSuspect()) {
				tbrMidlet.showAlert(ErrMsg.NOT_A_TB_SUSPECT, null);
				result = false;
			}
		}
		
		return result;
	}
	
	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if(c==cmdOK) {
			if(validate()) {
				endTime = DateTimeUtil.getTime();
				//String request = createRequestPayload();
				//System.out.println(request);
				//Hashtable model = tbrMidlet.sendToServer(request);
				
				/*if(model!=null) { */
					String status = XmlStrings.SUCCESS;// (String)model.get("status");
					
					if(status.equals(XmlStrings.SUCCESS)) {
						System.out.println("success");
						tbrMidlet.showAlert(SuccessMsg.SAVE_SUCCESS,null);
						
					}
					/*
					else if(status.equals(XmlStrings.ERROR)) {
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

	public void itemStateChanged(Item i) {
		
		if(i == cough) {
			int coughIndex = cough.getSelectedIndex();
			
			if(coughIndex!=0) {
				if(get(10)==coughDuration)
					delete(10);
				if(get(10)==productiveCough)
					delete(10);
			}
			
			else {
				if(get(10)!=coughDuration)
					insert(10,coughDuration);
				if(get(11)!=productiveCough)
					insert(11, productiveCough);
			}
		}
		
		else if(i==coughDuration) {
			int coughDurationIndex = coughDuration.getSelectedIndex();
			
			if(coughDurationIndex==1 || coughDurationIndex==2) {
				
				if(get(11)!=productiveCough) {
					insert(11,productiveCough);
				}
			}
			
			else {
				if(get(11)==productiveCough) {
					delete(11);
				}
			}
			
			if(coughDurationIndex==2) {
				
					tbrMidlet.showAlert(SuccessMsg.REFER_TO_GP,null);
				
			}
			
			else if(coughDurationIndex==1 && productiveCough.getSelectedIndex()==0) {
				tbrMidlet.showAlert(SuccessMsg.REFER_TO_GP,null);
			}
		}
		
		else if(i==productiveCough) {
			if(productiveCough.getSelectedIndex()==0) {
				if(coughDuration.getSelectedIndex()==1 || coughDuration.getSelectedIndex()==2)
					tbrMidlet.showAlert(SuccessMsg.REFER_TO_GP, null);		
			}
			
			else if(productiveCough.getSelectedIndex()==1) {
				if(coughDuration.getSelectedIndex()==2)
					tbrMidlet.showAlert(SuccessMsg.REFER_TO_GP, null);
			}
		}
		
		else if(i==tbHistory) {
			if(tbHistory.getSelectedIndex()==0) {
				
					tbrMidlet.showAlert(SuccessMsg.REFER_TO_GP, null);
				
			}
			
			
		}
	
		
		
		
		
		}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=ctns";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + idField.getString();
    	request += "&chwid=" +   chwIdField.getString();
    	request += "&gpid=" + gpIdField.getString();
    	request += "&fn=" + firstNameField.getString();
    	request += "&ln=" + lastNameField.getString();
    	request += "&sex=" + sexGroup.getString(sexGroup.getSelectedIndex());
    	request += "&age=" + ageField.getString();
    	
    	int coughIndex = cough.getSelectedIndex();
    	
    	request += "&cough=" + cough.getString(coughIndex);
    	if(coughIndex==0) {
    		int coughDurationIndex = coughDuration.getSelectedIndex();
    		
    		request += "&cd=" + coughDuration.getString(coughDurationIndex);
    		
    		if(coughDurationIndex==1 || coughDurationIndex==2) { //check condition
    			int productiveCoughIndex = productiveCough.getSelectedIndex();
    			
    			request += "&pc=" + productiveCough.getString(productiveCoughIndex);
    		}		
    	}
    	
    	request += "&tbh=" + tbHistory.getString(tbHistory.getSelectedIndex());
    	
    	
    	//request += "&pc=" + productiveCough.getString(productiveCough.getSelectedIndex());
    	/*if(size()>10) {
    		request += "&fev=" + fever.getString(fever.getSelectedIndex());
    		request += "&ns=" + nightSweat.getString(nightSweat.getSelectedIndex());
    		request += "&wl=" + weightLoss.getString(weightLoss.getSelectedIndex());
    		request += "&ha=" + haemoptysis.getString(haemoptysis.getSelectedIndex());
    		
    	}*/
    
    		request += "&fev=" + fever.getString(fever.getSelectedIndex());
    		request += "&ns=" + nightSweat.getString(nightSweat.getSelectedIndex());
    		request += "&wl=" + weightLoss.getString(weightLoss.getSelectedIndex());
    		request += "&ha=" + haemoptysis.getString(haemoptysis.getSelectedIndex());
    		request += "&diab=" + diabetes.getString(diabetes.getSelectedIndex());
    		request += "&cage=" + ageLessThanFive.getString(ageLessThanFive.getSelectedIndex());
    	
    	
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
    	return request;
	}
	private boolean isSuspect() {
		return (cough.getSelectedIndex()==0 && coughDuration.getSelectedIndex()==2) || (cough.getSelectedIndex()==0 && coughDuration.getSelectedIndex()==1 && productiveCough.getSelectedIndex()==0)
			|| tbHistory.getSelectedIndex()==0 || fever.getSelectedIndex()==0 || nightSweat.getSelectedIndex()==0
			|| weightLoss.getSelectedIndex()==0 || haemoptysis.getSelectedIndex()==0 || diabetes.getSelectedIndex()==0
			|| ageLessThanFive.getSelectedIndex()==0;
	}
		
		
		/*if (i == productiveCough) {
			String answer = productiveCough.getString(productiveCough
					.getSelectedIndex());

			if (tbrMidlet.getSettings().getActiveUserType() != UserType.USER_TYPE_GP) {
				if (answer.equals("No")) {
					for (int count = 0; count < 4; count++) {
						delete(size() - 1);
					}
				}

				else if (answer.equals("Yes") && get(size() - 1) != haemoptysis) {
					append(fever);
					append(nightSweat);
					append(weightLoss);
					append(haemoptysis);
					tbrMidlet.showAlert(ErrMsg.ARE_YOU_SURE, null);
				}
				
				else if(get(size() - 1) != haemoptysis) {
					append(fever);
					append(nightSweat);
					append(weightLoss);
					append(haemoptysis);
				}
				
				else if(answer.equals("Yes")) {
					tbrMidlet.showAlert(ErrMsg.ARE_YOU_SURE, null);
				}
			}
			
			else {
				if (answer.equals("No")) {
					for (int count = 0; count < 4; count++) {
						delete(size() - 2);
					}
				}

				else if (answer.equals("Yes") && get(size() - 2) != haemoptysis) {
					insert(size()-1,fever);
					insert(size()-1,nightSweat);
					insert(size()-1,weightLoss);
					insert(size()-1,haemoptysis);
					tbrMidlet.showAlert(ErrMsg.ARE_YOU_SURE, null);
				}
				
				else if(get(size()-2) != haemoptysis) {
					insert(size()-1,fever);
					insert(size()-1,nightSweat);
					insert(size()-1,weightLoss);
					insert(size()-1,haemoptysis);
				}
			}
		}
		*/
	

}
