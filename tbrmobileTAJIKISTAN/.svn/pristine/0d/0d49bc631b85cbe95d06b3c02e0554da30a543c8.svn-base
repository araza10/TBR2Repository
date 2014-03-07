package org.irdresearch.tbreach.mobile.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.GPSUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class AddressUpdateForm extends BaseTBReachForm implements CommandListener, ItemCommandListener, ItemStateListener {

	//private TBReachMainMIDlet tbrMidlet;
	TextField idField;
	TextField idConfirm;
	DateField dateField;
	//TextField monitorIdField;
	
	//TextField phoneField;
	
	
	
	TextField houseNumberField;
	TextField streetNameField;
	TextField sectorNumberField;
	TextField colonyField;
	TextField townField;
	TextField ucField;
	TextField landmarkField;
	TextField phoneField;
	
	//TextField gpsLatField;
	//TextField gpsLongField;

	
	Command cmdOK;
	Command cmdBack;
	//Command cmdGPS;
	//Command cmdNA;
	
	//boolean naActive;
	
	public AddressUpdateForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		idField = null;
		idConfirm = null; 
		//monitorIdField = null;
		dateField = null;
		
	
		houseNumberField = null;
		streetNameField = null;
		sectorNumberField = null;
		colonyField = null;
		townField = null;
		ucField = null;
		landmarkField = null;
		phoneField = null;
		
		
		cmdOK = null;
		cmdBack = null;
		//cmdGPS = null;
		//cmdNA = null;
		//naActive = false;

	}
	
	public void init() {
		idField = new TextField("Suspect ID:", "", IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);
		idConfirm = new TextField("Re-enter Suspect ID:", "", IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);
		//monitorIdField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(),12, TextField.ANY);
		
		dateField = new DateField("Date:" , DateField.DATE);
		dateField.setDate(new Date());
		
	
		
		ucField = new TextField("Address: UC:", "", 50, TextField.ANY);

		
		houseNumberField = new TextField("Address:House Number:", "", 50, TextField.ANY);
		
		streetNameField = new TextField("Address:Steet Name:", "", 50, TextField.ANY);
		sectorNumberField = new TextField("Address:Sector Number:", "", 50, TextField.ANY);
		colonyField =  new TextField("Address:Colony Name:", "", 50, TextField.ANY);
		townField = new TextField("Address:Town Name:", "", 50, TextField.ANY);
		landmarkField = new TextField("Address:Landmark:", "", 50, TextField.ANY);
		phoneField = new TextField("Phone Number:", "", 12, TextField.NUMERIC);
		
		//gpsLatField = new TextField("GPS Latitude:", "", 30, TextField.DECIMAL|TextField.UNEDITABLE);
		//gpsLongField = new TextField("GPS Longitude:", "", 30, TextField.DECIMAL|TextField.UNEDITABLE);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		//cmdGPS = new Command("GPS", Command.OK, 3);
		//cmdNA = new Command("Insert N/A", Command.OK, 1);
		append(dateField);
		append(idField);
		append(idConfirm);
		//append(monitorIdField);
		
		

		//houseNumberField.addCommand(cmdNA);
		houseNumberField.setItemCommandListener(this);
		append(houseNumberField);
		
		append(streetNameField);
	//	streetNameField.addCommand(cmdNA);
		streetNameField.setItemCommandListener(this);
		
		append(sectorNumberField);
		//sectorNumberField.addCommand(cmdNA);
		sectorNumberField.setItemCommandListener(this);
		
		append(colonyField);
	//	colonyField.addCommand(cmdNA);
		colonyField.setItemCommandListener(this);
		
		append(townField);
	//	townField.addCommand(cmdNA);
		townField.setItemCommandListener(this);
		
		append(ucField);
	//	ucField.addCommand(cmdNA);
		ucField.setItemCommandListener(this);
		
		append(landmarkField);
	//	landmarkField.addCommand(cmdNA);
		landmarkField.setItemCommandListener(this);
		
		append(phoneField);
		
	//	append(gpsLatField);
	//	append(gpsLongField);
		
		/*append(structNumberField);
		append(gpsLatField);
		append(gpsLongField);
		append(familyHistoryGroup);
		append(familyMembersGroup);
		append(patientHistoryGroup);
		append(previousTreatmentGroup);
		append(completedTreatmentGroup);
		append(patientTypeGroup);
		append(patientCategoryGroup);*/
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		//addCommand(cmdGPS);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
		

	}
	
	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if(c==cmdOK) {
			if (validate()) {
				endTime = DateTimeUtil.getTime();
				String request = createRequestPayload();
				System.out.println(request);
				//Hashtable model = tbrMidlet.sendToServer(request);

				//if (model != null) {
					String status = XmlStrings.SUCCESS;//(String) model.get("status");

					if (status.equals(XmlStrings.SUCCESS)) {
						tbrMidlet.showAlert(SuccessMsg.SAVE_SUCCESS, null);

					}

					/*else if (status.equals(XmlStrings.ERROR)) {
						tbrMidlet.showAlert((String) model.get("msg"), null);
					}
				}*/
			}
		}
	
		else if(c==cmdBack) {
			deleteAll();
			removeCommand(cmdOK);
			removeCommand(cmdBack);
			//removeCommand(cmdGPS);
			tbrMidlet.setDisplay(prevDisplayable);
		}
		
/*		else if(c==cmdGPS) {
			GPSUtil gu = new GPSUtil();
			
			try {
				gu.getLocation();
				gpsLatField.setString(gu.getLat().toString());
				gpsLongField.setString(gu.getLng().toString());
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public boolean validate() {
		boolean result = true;
		int check = -1;
		
		if(idField.getString()==null || idField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.ID_MISSING,null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
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
		
/*		else if(monitorIdField.getString()==null || monitorIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING, null);
			result = false;
		}*/
		
		else if(houseNumberField.getString()==null || houseNumberField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.HOUSE_NUM_MISSING, null);
			result = false;
		}
		
		else if(streetNameField.getString()==null || streetNameField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.STREET_NAME_MISSING, null);
			result = false;
		}
		
		else if(sectorNumberField.getString()==null || sectorNumberField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.SECTOR_NUMBER_MISSING, null);
			result = false;
		}
		
		else if(colonyField.getString()==null || colonyField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.COLONY_NAME_MISSING, null);
			result = false;
		}
		
		else if(townField.getString()==null || townField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.TOWN_MISSING, null);
			result = false;
		}
		
		else if(landmarkField.getString()==null || landmarkField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.LANDMARK_MISSING, null);
			result = false;
		}
		
		else if(ucField.getString()==null || ucField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.UC_MISSING, null);
			result = false;
		}
		
		else if(phoneField.getString()==null || phoneField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.PHONE_MISSING, null);
			result = false;
		}
		
		else if(phoneField.getString().length() != 12) {
			tbrMidlet.showAlert(ErrMsg.BAD_PHONE_LENGTH, null);
			result = false;
		}
		
		else if(!phoneField.getString().equals("888888888888") && !phoneField.getString().substring(0,2).equals("92")) {
			tbrMidlet.showAlert(ErrMsg.BAD_COUNTRY_CODE, null);
			result = false;
		}
		System.out.println(result);
		
		
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=auf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + idField.getString();
    	//request += "&mid=" + monitorIdField.getString();
    	
    	
    	//request += "&phn=" + phoneField.getString();
    	
    	request += "&hn=" + houseNumberField.getString();
    	request += "&sn=" + streetNameField.getString();
    	request += "&cn=" + colonyField.getString();
    	request += "&sec=" + sectorNumberField.getString();
    	request += "&tn=" + townField.getString();
    	request += "&lm=" + landmarkField.getString();
    	request += "&uc=" + ucField.getString();
    	request += "&phn=" + phoneField.getString();
    /*	request += "&lat=" + gpsLatField.getString();
    	request += "&lng=" + gpsLongField.getString();*/
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
		return request;
	}
	
	public void commandAction(Command c, Item i) {
/*		if(c==cmdNA) {
			if(i.equals(houseNumberField)) {
				houseNumberField.setString("N/A");
			}
			
			else if(i.equals(streetNameField)) {
				streetNameField.setString("N/A");
			}
			
			else if(i.equals(sectorNumberField)) {
				sectorNumberField.setString("N/A");
			}
			
			else if(i.equals(colonyField)) {
				colonyField.setString("N/A");
			}
			
			else if(i.equals(townField)) {
				townField.setString("N/A");
			}
			
			else if(i.equals(landmarkField)) {
				landmarkField.setString("N/A");
			}
			
			else if(i.equals(landmarkField)) {
				landmarkField.setString("N/A");
			}
			
			else if(i.equals(ucField)) {
				ucField.setString("N/A");
			}
			
		}*/
		
	}
	
	public void itemStateChanged(Item i) {
		
	}

}
