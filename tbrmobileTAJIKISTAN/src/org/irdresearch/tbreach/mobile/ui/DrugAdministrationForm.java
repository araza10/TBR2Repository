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
import org.irdresearch.tbreach.mobile.constants.UserType;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.GPSUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class DrugAdministrationForm extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	TextField fieldWorkerIdField;
	
	//ChoiceGroup monthGroup;
	DateField dateField;
	ChoiceGroup doseGroup;
	//TextField gpsLatField;
	//TextField gpsLngField;
	
	ChoiceGroup observeQuestionGroup;
	ChoiceGroup whereGroup;
	TextField otherLocationField;
	TextField whyNotReasonField;
	
	Command cmdOK;
	Command cmdBack;
	Command cmdGPS;

	private Hashtable queryData;
	private String patientId;
	
	public DrugAdministrationForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		queryData = null;
		patientId = null;
		
		fieldWorkerIdField = null;
		//monthGroup = null;
		dateField = null;
		doseGroup = null;
		//gpsLatField = null;
		//gpsLngField = null;
		
		observeQuestionGroup = null;
		whereGroup = null;
		whyNotReasonField = null;
		
		cmdOK = null;
		cmdBack = null;
		cmdGPS = null;
		
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
	
	public void init() {
		String data = "";
		
		if(queryData!=null) {
			data += "Patient ID: " + patientId;
			data += "\nCategory: " + "New";
			data += "\nTx Phase: " + "IP";
			data += "\nRegimen Type: " + "Adult";
			data += "\nRegimen: " + "RHZE";
			data += "\nForm of Medication: " + "Syrup form";
			data += "\nFDC Dosage: " + "3";
			data += "\nStreptomycin: " + "750";
			data += "\nMR Number: " + "2111121212";
			data += "\nGP ID: " + "G-ABC-0001";

			append(data);
		}
		
		String fwId = "";
		if(tbrMidlet.getCurrentRole() == UserType.USER_TYPE_CHW 
				|| tbrMidlet.getCurrentRole() == UserType.USER_TYPE_MONITOR){
			fwId = 	tbrMidlet.getCurrentUserId();
		}
		fieldWorkerIdField = new TextField("Field Worker ID:", fwId
					, (IdValidateUtil.MAX_ID_LENGTH), TextField.ANY);

		dateField = new DateField("Date:", DateField.DATE);
		dateField.setDate(new Date());
		//gpsLatField = new TextField("GPS Lat:", "", 15, TextField.DECIMAL);
		//gpsLngField = new TextField("GPS Long:","", 15, TextField.DECIMAL);
		
		/*monthGroup = new ChoiceGroup("Treatment Month", ChoiceGroup.EXCLUSIVE);
		for(int i=1; i<9; i++) {
			monthGroup.append(new Integer(i).toString(), null);
		}*/
		
		doseGroup = new ChoiceGroup("Dose Administered", ChoiceGroup.POPUP);
		doseGroup.append("Full", null);
		doseGroup.append("Partial", null);
		doseGroup.append("None", null);
		
		observeQuestionGroup = new ChoiceGroup("Did you observe the patient taking the drug?", ChoiceGroup.EXCLUSIVE );
		observeQuestionGroup.append("Yes", null);
		observeQuestionGroup.append("No", null);
		
		whereGroup = new ChoiceGroup("Where?", ChoiceGroup.POPUP);
		whereGroup.append("Patient's Home", null);
		whereGroup.append("TS Home", null);
		whereGroup.append("GP Clinic", null);
		whereGroup.append("Other", null);
		
		otherLocationField = new TextField("Where?", "", 15, TextField.ANY);
				
		whyNotReasonField = new TextField("Why not?", "", 20, TextField.ANY);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		cmdGPS = new Command("GPS", Command.OK, 3);
		
		append(fieldWorkerIdField);

		
		//append(monthGroup);
		append(dateField);
		append(doseGroup);
		/*append(gpsLatField);
		append(gpsLngField);*/
		append(observeQuestionGroup);
		append(whereGroup);
		
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
		
		/*else if(c==cmdGPS) {
			GPSUtil gu = new GPSUtil();
			
			try {
				gu.getLocation();
				gpsLatField.setString(gu.getLat().toString());
				gpsLngField.setString(gu.getLng().toString());
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public void itemStateChanged(Item i) {
		if(i==observeQuestionGroup) {
			if(observeQuestionGroup.getSelectedIndex()==1) {
				if(get(size()-1)==otherLocationField)
					delete(size()-1);
				if(get(size()-1)==whereGroup) {
					delete(size()-1);
				}
				append(whyNotReasonField);
			}
			
			else {
				if(get(size()-1)==whyNotReasonField) {
					delete(size()-1);
				}
				append(whereGroup);
			}
		}
		
		else if(i==whereGroup) {
			if(whereGroup.getSelectedIndex()==3) {
				append(otherLocationField);
			}
			
			else if(get(size()-1)==otherLocationField) {
				delete(size()-1);
			}
		}
		
		
	}

	public boolean validate() {
		boolean result = true;
		int observeChoice = observeQuestionGroup.getSelectedIndex();
		
		if(fieldWorkerIdField.getString()==null || fieldWorkerIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING,null);
			result = false;
		}
		
		else if(observeChoice==1 && (whyNotReasonField.getString()==null || whyNotReasonField.getString().length()==0)){
			tbrMidlet.showAlert(ErrMsg.REASON_MISSING,null);
			result = false;
		}
		
		else if(observeChoice==0 && whereGroup.getSelectedIndex()==3 && (otherLocationField.getString()==null || otherLocationField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.OTHER_LOCATION_MISSING, null);
			result = false;
		}
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		int observeChoice = observeQuestionGroup.getSelectedIndex();
		request = "type=daf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&chwid=" + fieldWorkerIdField.getString();
    	request += "&dt=" + DateTimeUtil.getDateTime(dateField.getDate());
    	request += "&dose=" + doseGroup.getString(doseGroup.getSelectedIndex());
    	/*request += "&lat=" + gpsLatField.getString();
    	request += "&lng=" + gpsLngField.getString();*/
    	request += "&od=" + observeQuestionGroup.getString(observeChoice);
    	if(observeChoice==1) {
    		request += "&rsn=" + whyNotReasonField.getString();
    	}
    	else {
    		if(whereGroup.getSelectedIndex()==3) {
    			request += "&loc=" + otherLocationField.toString();
    		}
    		else {
    			request += "&loc=" + whereGroup.getString(whereGroup.getSelectedIndex());
    		}
    	}
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
		return request;
	}
}
