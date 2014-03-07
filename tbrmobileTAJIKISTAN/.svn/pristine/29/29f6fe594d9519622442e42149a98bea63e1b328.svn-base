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
import org.irdresearch.tbreach.mobile.util.GPSUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class PatientGPSForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

	//private TBReachMainMIDlet tbrMidlet;
	Command cmdOK;
	Command cmdBack;
	Command cmdGPS;
	
	TextField idField;
	TextField idConfirm;
	TextField chwIdField;
	DateField dateField;
	ChoiceGroup encTypeGroup;
	
	TextField structNumberField;
	
	TextField gpsLatField;
	TextField gpsLongField;

	//StringItem gpsButton;
	
	public PatientGPSForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		idField = null;
		idConfirm = null;
		chwIdField = null;
		dateField = null;
		encTypeGroup = null;
		structNumberField = null;
		
		idField = null;
		idConfirm = null;
		cmdOK = null;
		cmdBack = null;
		cmdGPS = null;
	}
	
	public void init() {
	
		idField = new TextField("Suspect ID:", "", 10, TextField.NUMERIC);
		idConfirm = new TextField("Re-enter Suspect ID:", "", 10, TextField.NUMERIC);
		chwIdField = new TextField("Field Worker ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		dateField = new DateField("Date:", DateField.DATE);
		dateField.setDate(new Date());
		
		structNumberField = new TextField("IRD Structure Number", "", 6, TextField.NUMERIC);
		gpsLatField = new TextField("GPS Latitude:", "", 30, TextField.DECIMAL|TextField.UNEDITABLE);
		gpsLongField = new TextField("GPS Longitude:", "", 30, TextField.DECIMAL|TextField.UNEDITABLE);
		encTypeGroup = new ChoiceGroup("Encounter Type:",ChoiceGroup.POPUP);
		encTypeGroup.append("Baseline", null);
		encTypeGroup.append("Follow-up", null);
		//encTypeGroup.append("DOT",null);
		encTypeGroup.append("Sign-in", null);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		cmdGPS = new Command("GPS", Command.OK, 3);
		
		append(encTypeGroup);
		append(idField);
		append(idConfirm);
		append(chwIdField);
		append(dateField);
		append(structNumberField);
		append(gpsLatField);
		append(gpsLongField);
		//append(gpsButton);
		addCommand(cmdOK);
		addCommand(cmdBack);
		addCommand(cmdGPS);
		
		
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
					String status = XmlStrings.SUCCESS;// (String)model.get("status");
					
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
			removeCommand(cmdGPS);
			tbrMidlet.setDisplay(prevDisplayable);
		}
		
		else if(c==cmdGPS) {
			GPSUtil gu = new GPSUtil();
			
			try {
				gu.getLocation();
				gpsLatField.setString(gu.getLat().toString());
				gpsLongField.setString(gu.getLng().toString());
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public boolean validate() {
		boolean result = true;
		int check = -1;
		
		if(encTypeGroup.getSelectedIndex()!=2)
		{
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
		}
		
		else if(chwIdField.getString()==null || chwIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING,null);
			result = false;
		}
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=pgf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	if(encTypeGroup.getSelectedIndex()!=2) {
    		request += "&id=" + idField.getString();
    	}
    	request += "&chwid=" + chwIdField.getString();
    	request += "&sn=" + structNumberField.getString();
    	request += "&lat=" + gpsLatField.getString();
    	request += "&lng=" + gpsLongField.getString();
    	request += "&enc=" + encTypeGroup.getString(encTypeGroup.getSelectedIndex());
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}
	
	public void itemStateChanged(Item i) {
		if(i==encTypeGroup) {
			if(encTypeGroup.getSelectedIndex()==2) {
				idField.setString("");
				idField.setConstraints(TextField.UNEDITABLE);
				idConfirm.setString("");
				idConfirm.setConstraints(TextField.UNEDITABLE);
			}
			
			else {
				idField.setConstraints(TextField.NUMERIC);
				idConfirm.setConstraints(TextField.NUMERIC);
			}
			
		}
	}

}
