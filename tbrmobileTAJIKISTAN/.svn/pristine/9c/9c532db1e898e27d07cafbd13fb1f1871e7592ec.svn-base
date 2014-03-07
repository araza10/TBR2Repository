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

import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;

public class SuspectConfirmationForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

//private TBReachMainMIDlet tbrMidlet;
	DateField dateField;
	TextField gpIdField;
	
	ChoiceGroup cough;
	ChoiceGroup coughDuration;
	ChoiceGroup productiveCough;
	ChoiceGroup tbHistory;
	ChoiceGroup tbFamilyHistory;
	ChoiceGroup fever;
	ChoiceGroup nightSweat;
	ChoiceGroup weightLoss;
	ChoiceGroup haemoptysis;
	ChoiceGroup conclusion;
	
	Command cmdOK;
	Command cmdBack;
	
	private Hashtable queryData;
	private String patientId;
	
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

	public SuspectConfirmationForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		/*idField = null;
		idConfirm = null;*/
		queryData = null;
		gpIdField = null;
		dateField = null;
		
		productiveCough = null;
		fever = null;
		nightSweat = null;
		weightLoss = null;
		haemoptysis = null;
		conclusion = null;
		
		cmdOK = null;
		cmdBack = null;
		
	}
	
	public void init() {
		
		String data="";
		
		//if(queryData!=null) {
			data += "Patient ID: " + patientId;
			data += "\nFirst Name: Test";// + (String)queryData.get("fname");
			data += "\nLast Name: Patient";// + (String)queryData.get("lname");
			append(data);
		//}
		
		/*idField = new TextField("Suspect ID:", "", 8, TextField.ANY);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 8, TextField.ANY);*/
		
		gpIdField = new TextField("GP ID:", tbrMidlet.getCurrentUserId(),12, TextField.ANY);
		
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		
		cough = new ChoiceGroup("Do you have a cough?", ChoiceGroup.POPUP);
		cough.append("Yes",null);
		cough.append("No",null);
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
		//productiveCough.append("Refused", null);
		productiveCough.append("Don't know", null);
		productiveCough.setSelectedIndex(2, true);
		
		tbHistory = new ChoiceGroup("Have you ever had TB before?", ChoiceGroup.POPUP);
		tbHistory.append("Yes",null);
		tbHistory.append("No", null);
		tbHistory.append("Don't Know", null);
		
		tbFamilyHistory = new ChoiceGroup("Has anyone in your family had TB before?", ChoiceGroup.POPUP);
		tbFamilyHistory.append("Yes",null);
		tbFamilyHistory.append("No", null);
		tbFamilyHistory.append("Don't Know", null);
		
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
		
		conclusion = new ChoiceGroup("Conclusion", Choice.EXCLUSIVE);
		conclusion.append("Confirmed", null);
		conclusion.append("Not Confirmed", null);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);

		/*append(idField);
		append(idConfirm);*/
		
		append(gpIdField);
		append(dateField);
		append(cough);
		append(coughDuration);
		append(productiveCough);
		append(tbHistory);
		append(tbFamilyHistory);
		append(fever);
		append(nightSweat);
		append(weightLoss);
		append(haemoptysis);
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
					String status =  XmlStrings.SUCCESS;//(String)model.get("status");
					
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
	
	public void itemStateChanged(Item i) {
		if(i == cough) {
			int coughIndex = cough.getSelectedIndex();
			
			if(coughIndex!=0) {
				if(get(4)==coughDuration)
					delete(4);
				if(get(4)==productiveCough)
					delete(4);
			}
			
			else {
				if(get(4)!=coughDuration)
					insert(4,coughDuration);
				if(get(5)!=productiveCough)
					insert(5, productiveCough);
			}
		}
		
		else if(i==coughDuration) {
			int coughDurationIndex = coughDuration.getSelectedIndex();
			
			if(coughDurationIndex==1 || coughDurationIndex==2) {
				
				if(get(5)!=productiveCough) {
					insert(5,productiveCough);
				}
			}
			
			else {
				if(get(5)==productiveCough) {
					delete(5);
				}
			}
		}
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=scf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	
    	request += "&gpid=" + gpIdField.getString();
    	
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
    	request += "ftbh=" + tbFamilyHistory.getString(tbFamilyHistory.getSelectedIndex());
    	request += "&fev=" + fever.getString(fever.getSelectedIndex());
		request += "&ns=" + nightSweat.getString(nightSweat.getSelectedIndex());
		request += "&wl=" + weightLoss.getString(weightLoss.getSelectedIndex());
		request += "&ha=" + haemoptysis.getString(haemoptysis.getSelectedIndex());
    	
    	request += "&conc=" + conclusion.getString(conclusion.getSelectedIndex());
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
    	return request;
	}
	
	private boolean validate() {
		boolean result = true;
		
		if (gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
	
		return result;
	}
}
