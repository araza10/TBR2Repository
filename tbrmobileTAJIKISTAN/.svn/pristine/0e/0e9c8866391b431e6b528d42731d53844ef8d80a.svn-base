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


public class DFR extends BaseTBReachForm implements CommandListener, ItemStateListener {

	DateField dateField;
	TextField idField;
	
	ChoiceGroup location;
	TextField locationDetail;
	TextField gpIdField;
	TextField attemptedField;
	TextField screenedField;
	TextField missedField;
	TextField refusedField;
	TextField suspectsField;
	
	Command cmdOK;
	Command cmdBack;
	
	public DFR(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		dateField = null;
		idField = null;
		location = null;
		locationDetail = null;
		gpIdField = null;
		attemptedField = null;
		screenedField = null;
		missedField = null;
		refusedField = null;
		suspectsField = null;
	}
	
	public void init() {
		idField = new TextField("CHW ID:", tbrMidlet.getCurrentUserId(),12, TextField.ANY);
		
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		
		gpIdField = new TextField("GP ID:", "", 12, TextField.ANY);
		
		location = new ChoiceGroup("Location Type: ",ChoiceGroup.POPUP);
		location.append("GP Clinic", null);
		location.append("Contact Tracing", null);
		location.append("Camp", null);
		location.append("LHW", null);
		location.append("Hospital OPD", null);
		location.append("Indus Consultant Clinic", null);
		location.append("Lab", null);
		location.append("Outreach", null);
		
		locationDetail = new TextField("Location Detail: ", "", 50, TextField.ANY);
		
		attemptedField = new TextField("Number of people attempted: ", "0", 3, TextField.NUMERIC);
		screenedField = new TextField("Number of people screened: ", "0", 3, TextField.NUMERIC);
		missedField = new TextField("Number of people missed: ", "0", 3, TextField.NUMERIC);
		refusedField  = new TextField("Number of people refused: ", "0", 3, TextField.NUMERIC);
		suspectsField = new TextField("Number of suspects: ", "0", 3, TextField.NUMERIC);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(idField);
		append(dateField);
		append(location);
		append(locationDetail);
		append(gpIdField);
		append(attemptedField);
		append(screenedField);
		append(missedField);
		append(refusedField);
		append(suspectsField);
		
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
				
				/*if(model!=null) {*/
					String status = XmlStrings.SUCCESS; //(String)model.get("status");
					
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
	
	public void itemStateChanged(Item i) {
		
	}
	
	public boolean validate() {
		boolean result = true;
		
		if (idField.getString()==null || idField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING, null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		else if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		
		else if(attemptedField.getString()==null || attemptedField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.NUM_ATTEMPTED_MISSING, null);
			result = false;
		}
		
		else if(screenedField.getString()==null || screenedField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.NUM_SCREENED_MISSING, null);
			result = false;
		}
		
		else if(missedField.getString()==null || missedField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.NUM_MISSED_MISSING, null);
			result = false;
		}
		
		else if(refusedField.getString()==null || refusedField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.NUM_REFUSED_MISSING, null);
			result = false;
		}
		
		else if(suspectsField.getString()==null || suspectsField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.NUM_SUSPECTS_MISSING, null);
			result = false;
		}
		
		if(result) {
			int attempted = Integer.parseInt(attemptedField.getString());
			int missed = Integer.parseInt(missedField.getString());
			int refused = Integer.parseInt(refusedField.getString());
			int screened = Integer.parseInt(screenedField.getString());
			int suspects = Integer.parseInt(suspectsField.getString());
			
			if(attempted != missed + refused + screened) {
				tbrMidlet.showAlert(ErrMsg.PEOPLE_MISMATCH, null);
				result = false;
			}
			
			else if(suspects > screened) {
				tbrMidlet.showAlert(ErrMsg.SUSPECT_SCREENED_MISMATCH, null);
				result = false;
			}
			
		}
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=dfr";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + idField.getString();
    	request += "&loc=" + location.getString(location.getSelectedIndex());
    	request += "&locd=" + locationDetail.getString();
    	request += "&gpid=" + gpIdField.getString();
    	request += "&att=" + attemptedField.getString();
    	request += "&scr=" + screenedField.getString();
    	request += "&ref=" + refusedField.getString();
    	request += "&mis=" + missedField.getString();
    	request += "&ref=" + refusedField.getString();
    	request += "&sus=" + suspectsField.getString();
    	
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
    	return request;
	}

}
