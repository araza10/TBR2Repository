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

public class ContactSputumCollectionForm extends BaseTBReachForm implements CommandListener, ItemStateListener {

	//private TBReachMainMIDlet tbrMidlet;
	TextField monitorIdField;
	DateField dateField;
	ChoiceGroup statusGroup;
	ChoiceGroup isMDRContact;
	ChoiceGroup sputumMonthGroup;
	ChoiceGroup whichSampleGroup;
	TextField otherSampleField;
	ChoiceGroup sputumCollectionGroup;
	TextField otherReasonField;
	
	TextField barCodeField;
	
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

	public ContactSputumCollectionForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		queryData = null;
		patientId = null;
		
		monitorIdField = null;
		dateField = null;
		
		statusGroup = null;
		sputumMonthGroup = null;
		whichSampleGroup = null;
		otherSampleField = null;
		sputumCollectionGroup = null;
		otherReasonField = null;
		
		barCodeField = null;
		
		cmdOK = null;
		cmdBack = null;
	}
	
	public void init() {
		
		String data = "";
		
		if(queryData!=null) {
			data += "Patient ID: " + patientId;
			data += "\nFirst Name: " + "Test";
			data += "\nLast Name: " + "Patient";
			data += "\nAddress: " + "Test Address";
			data += "\nTx Start Date: " + "2011-04-09";
			data += "\nCategory: " + "CAT II"; 
			append(data);
		}
		
		monitorIdField = new TextField("Field Worker ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		
		statusGroup = new ChoiceGroup("Patient Status", ChoiceGroup.EXCLUSIVE);
		statusGroup.append("Suspect",null);
		statusGroup.append("Confirmed", null);
		
		isMDRContact = new ChoiceGroup("MDR Patient Contact", ChoiceGroup.EXCLUSIVE);
		isMDRContact.append("Yes", null);
		isMDRContact.append("No", null);
		
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
		whichSampleGroup.append("Other", null);
		
		otherSampleField = new TextField("Sample Number:", "", 2, TextField.NUMERIC);
		
		sputumCollectionGroup = new ChoiceGroup("Sputum Collected?:", ChoiceGroup.POPUP);
		sputumCollectionGroup.append("Yes", null);
		sputumCollectionGroup.append("No, Unable to generate", null);
		sputumCollectionGroup.append("No, Refused", null);
		sputumCollectionGroup.append("No, Patient referred", null);
		sputumCollectionGroup.append("No, Address not found", null);
		sputumCollectionGroup.append("No, Patient not available/Did not show up", null);
		sputumCollectionGroup.append("No, Other", null);
		otherReasonField = new TextField("Reason why not collected","", 25, TextField.ANY);
		barCodeField = new TextField("Barcode Number","", 5, TextField.NUMERIC);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(monitorIdField);
		append(dateField);
		append(statusGroup);
		append(isMDRContact);
		append(sputumMonthGroup);
		append(whichSampleGroup);
		append(sputumCollectionGroup);
		append(barCodeField);
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
		
	}
	
	public void itemStateChanged(Item i) {
		if(i==whichSampleGroup) {
			 if(whichSampleGroup.getSelectedIndex()==3 && get(6)!=otherSampleField) {
				 insert(6, otherSampleField);
			 }
			 
			 else if (get(6)==otherSampleField){
				 delete(6);
			 }
		}
		//TODO: Check logic
		else if(i==sputumCollectionGroup) {//change to 6
			if(sputumCollectionGroup.getSelectedIndex()==6 && get(size()-2)!=otherReasonField) {
				insert(size()-1, otherReasonField);
			}
			
			else if(get(size()-2)==otherReasonField) {
				delete(size()-2);
			}
		}
	}
	
	public void commandAction(Command c, Displayable d) {
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
		int sputumChoice = sputumCollectionGroup.getSelectedIndex();
		int monthChoice = sputumMonthGroup.getSelectedIndex();
		int statusChoice = statusGroup.getSelectedIndex();
		
		if(monitorIdField.getString()==null || monitorIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING,null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
	
		else if(sputumChoice==0 && (barCodeField.getString()==null || barCodeField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.SAMPLE_BARCODE_MISSING,null);
			result = false;
		}
		
		else if(statusChoice==0 && monthChoice!=0) {
			tbrMidlet.showAlert(ErrMsg.ONLY_PATIENT_GETS_PAST_BASELINE, null);
			result = false;
		}
		
		else if(whichSampleGroup.getSelectedIndex()==3 && (otherSampleField.getString()==null || otherSampleField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.SAMPLE_NUMBER_MISSING, null);
			result = false;
		}
		//change to 6
		else if(sputumChoice==6 && (otherReasonField.getString()==null || otherReasonField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.SPUTUM_REASON_MISSING, null);
			result = false;
		}
		return result;
	}

	public String createRequestPayload() {
		String request = "";
		int sputumChoice = sputumCollectionGroup.getSelectedIndex();
		int sampleChoice = whichSampleGroup.getSelectedIndex();
		
		request = "type=cspcf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&mid=" + monitorIdField.getString();
    	request += "&ps=" + statusGroup.getString(statusGroup.getSelectedIndex());
    	request += "&mdrc=" + isMDRContact.getString(isMDRContact.getSelectedIndex());
    	request += "&cm=" + sputumMonthGroup.getString(sputumMonthGroup.getSelectedIndex());
    	
    	if(sampleChoice==3) {
    		request += "&ws=" + otherSampleField.getString();  	
    	}
    	request += "&ws=" + whichSampleGroup.getString(sampleChoice);  	
    	
    	//change to 6
    	if(sputumChoice==6) {
    		request += "&sc=No, other: " + otherReasonField.getString(); 
    	}
    	
    	else {
    		request += "&sc=" + sputumCollectionGroup.getString(sputumChoice);
    	}
    	request += "&sbc=" + barCodeField.getString();
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}
}
