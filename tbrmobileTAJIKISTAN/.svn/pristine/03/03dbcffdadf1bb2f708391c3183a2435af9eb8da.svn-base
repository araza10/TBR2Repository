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

public class NoActiveFollowupForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

	//TextField idField;
	//TextField idConfirm;
	TextField monitorIdField;
	TextField gpIdField;
	DateField dateField;
	ChoiceGroup reasonGroup;
	TextField reasonField;
	
	Command cmdOK;
	Command cmdBack;
	
	private Hashtable queryData;
	private String patientId;
	
	
	
	public NoActiveFollowupForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		//idField = null;
		//idConfirm = null;
		gpIdField = null;
		dateField = null;
		reasonGroup = null;
		reasonField = null;
		
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
		// TODO Auto-generated method stub
		if(c==cmdOK) {
			if(validate()) {
				endTime = DateTimeUtil.getTime();
				String request = createRequestPayload();
				System.out.println(request);
				/*Hashtable model = tbrMidlet.sendToServer(request);
				
				if(model!=null) {*/
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
	
	public void init() {
		
		String data = "";
		
		//if(queryData!=null) {
			
			//cxr =  (String)queryData.get("cxr");
			data += "Patient ID: " + patientId;
			data += "\nGP ID: " + "G-TEST-00";
			data += "\nCHW ID: " + "C-TEST-00"; 
			data += "\nMR: " + "20110001234";
			data += "\nStatus: " + "SUSPECT";
			data += "\nDiagnosis: " + "SMEAR POSITIVE PULMONARY TB";
			data += "\nNumber of Phone Calls: " + "3";
			data += "\nNumber of HH Visits: " + "2";
			append(data);
		//}
		
/*	idField = new TextField("Suspect ID:", "", 11, TextField.ANY);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 11, TextField.ANY);*/
		monitorIdField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(),12, TextField.ANY);
		gpIdField = new TextField("GP ID:", "",12, TextField.ANY);
		dateField = new DateField("Date:",DateField.DATE);
		dateField.setDate(new Date());
		
		reasonGroup = new ChoiceGroup("Reason for End Follow-up", Choice.POPUP);
		reasonGroup.append("Patient has not come",null);
		reasonGroup.append("Cannot contact", null);
		reasonGroup.append("Refused", null);
		reasonGroup.append("Other", null);
		
		reasonField = new TextField("Reason: ", "", 30, TextField.ANY);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);

		/* append(idField);
		append(idConfirm);*/
		
		append(monitorIdField);
		append(dateField);
		append(gpIdField);
		append(reasonGroup);
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
	}
	
	public boolean validate() {
		boolean result = true;
		
		
		if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		
		else if(monitorIdField.getString()==null || monitorIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING, null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		
		else if(reasonGroup.getSelectedIndex()==3 && (reasonField.getString()==null || reasonField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.EFF_REASON_MISSING, null);
			result = false;
		}
		
		
		return result;
	}
	
	public String createRequestPayload() {
		String request= null;
		int reasonChoice = reasonGroup.getSelectedIndex();
		
		request = "type=naaf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&gpid=" + gpIdField.getString();
    	request += "&mid=" + monitorIdField.getString();
    	
    	request += "&rsn=" + reasonGroup.getString(reasonChoice);;
    	request += "&otrrsn=" + reasonField.getString();
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}
	
	public void itemStateChanged(Item i) {
		if(i==reasonGroup) {
			if(reasonGroup.getSelectedIndex()==3) {
				append(reasonField);
			}
			
			else if(get(size()-1)==reasonField) {
				delete(size()-1);
			}
			
		}
	}

}
