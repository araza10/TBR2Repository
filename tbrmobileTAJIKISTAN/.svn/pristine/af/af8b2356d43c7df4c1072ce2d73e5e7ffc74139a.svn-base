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

public class EndFollowupForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

	//TextField idField;
	//TextField idConfirm;
	TextField gpIdField;
	DateField dateField;
	ChoiceGroup reasonGroup;
	TextField reasonField;
	
	Command cmdOK;
	Command cmdBack;
	
	private Hashtable queryData;
	private String patientId;
	
	
	
	public EndFollowupForm(String title, TBReachMainMIDlet tbrMidlet) {
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
	
	public void init() {
		
		String data = "";
		
		//if(queryData!=null) {
			
			//cxr =  (String)queryData.get("cxr");
			data += "Patient ID: " + patientId;
			data += "\nTx Start: " + "01/01/2012";
			data += "\nBase Smear: " + "+";
			data += "\nBase GX Result: " + "Positive";
			data += "\nRIF Resistance: " + "RIF Susceptible";
			data += "\nCXR: ";
			data += "\nSmear - 2m: " + "++";
			data += "\nSmear - 3m: " + "";
			data += "\nSmear - 5m: " + "";
			data += "\nSmear - 7m: " + "";
			data += "\nDisease Site: " + "PULMONARY";
			data += "\nPatient Type: " + "NEW"; 
			data += "\nCategory: " + "CAT 1";
			data += "\nHistory of TB Drugs: " + "NO";
			append(data);
		//}
		
/*	idField = new TextField("Suspect ID:", "", 11, TextField.ANY);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 11, TextField.ANY);*/
		
		gpIdField = new TextField("GP ID:", tbrMidlet.getCurrentUserId(),12, TextField.ANY);
		dateField = new DateField("Date:",DateField.DATE);
		dateField.setDate(new Date());
		
		reasonGroup = new ChoiceGroup("Reason for End Follow-up", Choice.POPUP);
		reasonGroup.append("Cured",null);
		reasonGroup.append("Tx Completed", null);
		reasonGroup.append("Failure", null);
		reasonGroup.append("Transferred out", null);
		reasonGroup.append("Died", null);
		//reasonGroup.append("Not TB", null);
		reasonGroup.append("Defaulted", null);
		reasonGroup.append("Drug Resistant", null);
		/*reasonGroup.append("Not TB Reach", null);
		reasonGroup.append("Receiving treatment from reporting TB Center", null);
		reasonGroup.append("Cannot Contact", null);
		reasonGroup.append("Contacted 3 times, patient has not come", null);
		reasonGroup.append("Refused treatment", null);
		reasonGroup.append("MDR Patient", null);	*/
		reasonGroup.append("Other", null);
		
		reasonField = new TextField("Reason: ", "", 30, TextField.ANY);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);

		/* append(idField);
		append(idConfirm);*/
		
		append(gpIdField);
		append(dateField);
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
		/*if(idField.getString()==null || idField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.ID_MISSING,null);
			result = false;
		}
		
		else if(!idField.getString().equals(idConfirm.getString())) {
			tbrMidlet.showAlert(ErrMsg.ID_MISMATCH,null);
			result = false;
		}*/
		
		if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		
		else if(reasonGroup.getSelectedIndex()==7 && (reasonField.getString()==null || reasonField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.EFF_REASON_MISSING, null);
			result = false;
		}
		
		
		return result;
	}
	
	public String createRequestPayload() {
		String request= null;
		int reasonChoice = reasonGroup.getSelectedIndex();
		
		request = "type=eff";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&gpid=" + gpIdField.getString();
    	/*if(reasonChoice==7) {
    		request += "&rsn=" + reasonField.getString();
    	}
    	else*/
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
			if(reasonGroup.getSelectedIndex()==7) {
				append(reasonField);
			}
			
			else if(get(size()-1)==reasonField) {
				delete(size()-1);
			}
			
		}
	}

}
