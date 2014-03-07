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
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class RefusalForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

	//private TBReachMainMIDlet tbrMidlet;
	TextField idField;
	TextField idConfirm;
	TextField monitorIdField;
	DateField dateField;
	
	ChoiceGroup statusGroup;
	ChoiceGroup whatRefusedGroup;
	ChoiceGroup sputumMonthGroup;
	ChoiceGroup whichSampleGroup;
	
	TextField reasonField; 
	
	Command cmdOK;
	Command cmdBack;
	
	public RefusalForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		idField = null;
		idConfirm = null;
		monitorIdField = null;
		dateField = null;
		
		statusGroup = null;
		whatRefusedGroup = null;
		sputumMonthGroup = null;
		whichSampleGroup = null;
		
		reasonField = null;
		
		cmdOK = null;
		cmdBack = null;
	}
	
	public void init() {
		idField = new TextField("Suspect ID:", "", 10, TextField.NUMERIC);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 10, TextField.NUMERIC);
		monitorIdField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		dateField = new DateField("Date:",DateField.DATE);
		dateField.setDate(new Date());
		
		statusGroup = new ChoiceGroup("Patient Status", ChoiceGroup.EXCLUSIVE);
		statusGroup.append("Suspect",null);
		statusGroup.append("Confirmed", null);
		
		whatRefusedGroup = new ChoiceGroup("What did patient refuse?", ChoiceGroup.POPUP);
		whatRefusedGroup.append("Monitor Home Visit",null);
		whatRefusedGroup.append("Sputum Collection", null);
		whatRefusedGroup.append("Start of Treatment", null);
		whatRefusedGroup.append("Continuation of Treatment", null);
		whatRefusedGroup.append("Verification by Monitor (Consent)", null);
		
		sputumMonthGroup = new ChoiceGroup("Which month of sputum collection?", ChoiceGroup.POPUP);
		sputumMonthGroup.append("Baseline", null);
		sputumMonthGroup.append("2nd",null);
		sputumMonthGroup.append("3rd", null);
		sputumMonthGroup.append("5th", null);
		sputumMonthGroup.append("7th", null);
		
		whichSampleGroup = new ChoiceGroup("Which sample?", ChoiceGroup.POPUP);
		whichSampleGroup.append("1", null);
		whichSampleGroup.append("2", null);
		whichSampleGroup.append("3", null);
		
		reasonField = new TextField("Reason for Refusal","", 30, TextField.ANY);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(idField);
		append(idConfirm);
		append(monitorIdField);
		append(dateField);
		
		append(statusGroup);
		append(whatRefusedGroup);
		
		append(reasonField);
		
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
	
	public void itemStateChanged(Item i) {
		if(i==whatRefusedGroup) {
			if(whatRefusedGroup.getSelectedIndex()==1 && get(6)!=sputumMonthGroup) {
				insert(6, sputumMonthGroup);
				insert(7, whichSampleGroup);
			}
			
			else if(get(6)==sputumMonthGroup){
				delete(6);
				delete(6);
			}
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
	
		else if(monitorIdField.getString()==null || monitorIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING,null);
			result = false;
		}
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		
		int whatChoice = whatRefusedGroup.getSelectedIndex();
		
		
		request = "type=rf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + idField.getString();
    	request += "&mid=" + monitorIdField.getString();
    	request += "&ps=" + statusGroup.getString(statusGroup.getSelectedIndex());
    	
    	request += "&wr=" + whatRefusedGroup.getString(whatChoice); 
    	
    	if(whatChoice==1) {
    		request += "&cm=" + sputumMonthGroup.getString(sputumMonthGroup.getSelectedIndex());
    		request += "&ws=" + whichSampleGroup.getString(whichSampleGroup.getSelectedIndex());
    	}
    	
    	request += "&rr=" + reasonField.getString();
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		
		return request;
		
	}

}
