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
import javax.microedition.media.control.MetaDataControl;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.GPSUtil;

public class PatientVerificationForm extends BaseTBReachForm implements CommandListener, ItemStateListener {
	
	//private TBReachMainMIDlet tbrMidlet;
	
	TextField monitorIdField;
	DateField dateField;

	ChoiceGroup didYouSeePatientGroup;
	ChoiceGroup whyNotSeenGroup;
	
	ChoiceGroup verifyNameGroup;
	TextField firstNameField;
	TextField lastNameField;
	
	ChoiceGroup verifyGenderGroup;
	ChoiceGroup genderGroup;
	
	ChoiceGroup takenTreatmentGroup;
	ChoiceGroup fourMonthsTreatmentGroup;
	
	ChoiceGroup verifyAgeGroup;
	TextField ageField;
	
	ChoiceGroup verifyAddressGroup;
	
	ChoiceGroup verifyTBTypeGroup;
	ChoiceGroup tbTypeGroup;
	
	ChoiceGroup verifyPatientTypeGroup;
	ChoiceGroup patientTypeGroup;
	
	ChoiceGroup verifyPatientCategoryGroup;
	ChoiceGroup patientCategoryGroup;
	
	ChoiceGroup moneyGroup;
	ChoiceGroup meetGPGroup;
	ChoiceGroup whyNotMeetGPGroup;
	
	TextField 	otherWhyNotMeetGPReason;
	
	TextField	numSamplesField;
	TextField	commentsField;
	
	
	private Hashtable queryData;
	
	private String patientId;
	
	Command cmdOK;
	Command cmdBack;
	
	public Hashtable getQueryData()
	{
		return queryData;
	}

	public void setQueryData(Hashtable queryData)
	{
		this.queryData = queryData;
	}

	public String getPatientId()
	{
		return patientId;
	}

	public void setPatientId(String patientId)
	{
		this.patientId = patientId;
	}
	
	public PatientVerificationForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		
		monitorIdField = null;
		
		
		dateField = null;
		didYouSeePatientGroup = null;
		whyNotSeenGroup = null;
		fourMonthsTreatmentGroup = null;
		
		verifyNameGroup = null;
		firstNameField = null;
		lastNameField = null;
		
		verifyGenderGroup = null;
		genderGroup = null;
		
		verifyAddressGroup = null;
		
		verifyPatientTypeGroup = null;
		patientTypeGroup = null;
		
		verifyPatientCategoryGroup = null;
		patientCategoryGroup = null;
		
		verifyTBTypeGroup = null;
		tbTypeGroup = null;
		
		verifyPatientTypeGroup = null;
		patientTypeGroup = null;
		
		verifyPatientCategoryGroup = null;
		patientCategoryGroup = null;
		
		moneyGroup = null;
		meetGPGroup = null;
		whyNotMeetGPGroup = null;
		
		otherWhyNotMeetGPReason = null;
		
		numSamplesField = null;
		commentsField = null;
		
		cmdOK = null;
		cmdBack = null;
	}
	
	public void init() {
		
		String data = "";
		
		//if(queryData!=null) {
		
			data += "Patient ID: " + patientId;
			data += "\nGP ID: " + "G-TEST-00";
			data += "\nMonitor ID: " + "M-TEST-00";
			data += "\nCHW ID: " + "C-TEST-00";
			data += "\nMR Number: " + "20110001234";
			data += "\nFirst Name: " + "TEST";
			data += "\nLast Name: " + "PATIENT";
			data += "\nGender: " + "F";
			data += "\nDate of Birth: " + "15/01/1970";
			data += "\nAge: " + "42";
			data += "\nPatient Type: " + "NEW";
			data += "\nPatient Category: " + "CAT 1";
			data += "\nTreatment Phase: " + "CONTINUOUS";
			data += "\nCurrent Regimen: " + "RHE";
			data += "\nDisease Site: " + "PULMONARY";
			/*data += "\nMarital Status: " + (String)queryData.get("mstatus");
			data += "\nReligion: " + (String)queryData.get("religion");
			data += "\nNIC: " + (String)queryData.get("nic");*/
			data += "\nPhone: " + "921234567890";
			data += "\nHouse No: " + "5";
			data += "\nStreet: " + "N/A";
			data += "\nSector: " + "X";
			data += "\nColony: " + "TEST COLONY"; 
			data += "\nTown: " + "N/A"; 
			data += "\nUC: " + "6"; 
			data += "\nLandmark: " + "CHAI KI DOKAAN"; 
			/*data += "\nAdults in HH: " + (String)queryData.get("numadults");
			data += "\nChildren in HH:" + (String)queryData.get("numchildren");
			data += "\nState: " + (String)queryData.get("state");*/
			
		//}			
		append(data);
		
		monitorIdField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		
		didYouSeePatientGroup = new ChoiceGroup("Did you meet the patient?", ChoiceGroup.POPUP);
		didYouSeePatientGroup.append("Yes",null);
		didYouSeePatientGroup.append("No", null);
		
		whyNotSeenGroup = new ChoiceGroup("Why did you not meet the patient?", ChoiceGroup.POPUP);
		whyNotSeenGroup.append("Patient not home", null);
		whyNotSeenGroup.append("Patient moved", null);
		whyNotSeenGroup.append("Couldn't find address", null);
		whyNotSeenGroup.append("Patient not verified", null);
		whyNotSeenGroup.append("Refused", null);
		
		verifyNameGroup = new ChoiceGroup("Verify the patient name?",ChoiceGroup.POPUP);
		verifyNameGroup.append("Correct",null);
		verifyNameGroup.append("Incorrect",null);
		
		firstNameField = new TextField("Patient First Name:", "", 15, TextField.UNEDITABLE);
		lastNameField = new TextField("Patient Last Name:", "", 15, TextField.UNEDITABLE);
		
		verifyGenderGroup = new ChoiceGroup("Verify the patient gender?", ChoiceGroup.POPUP);
		verifyGenderGroup.append("Correct",null);
		verifyGenderGroup.append("Incorrect",null);
		
		genderGroup = new ChoiceGroup("Patient gender:", ChoiceGroup.POPUP);
		
		
		
		verifyAgeGroup = new ChoiceGroup("Verify the patient age?",ChoiceGroup.POPUP);
		verifyAgeGroup.append("Correct",null);
		verifyAgeGroup.append("Incorrect",null);
		
		ageField = new TextField("Patient age:", "", 3, TextField.UNEDITABLE);
		
		verifyAddressGroup = new ChoiceGroup("Verify the patient address?", ChoiceGroup.POPUP);
		verifyAddressGroup.append("Correct",null);
		verifyAddressGroup.append("Incorrect",null);
		
		verifyTBTypeGroup = new ChoiceGroup("Verify the TB type?", ChoiceGroup.POPUP);
		verifyTBTypeGroup.append("Correct",null);
		verifyTBTypeGroup.append("Incorrect",null);
		
		tbTypeGroup = new ChoiceGroup("TB Type?", ChoiceGroup.POPUP);
		
		
		takenTreatmentGroup = new ChoiceGroup("Have you been treated for TB before?", ChoiceGroup.POPUP);
		takenTreatmentGroup.append("Yes",null);
		takenTreatmentGroup.append("No", null);
		
		fourMonthsTreatmentGroup = new ChoiceGroup("Have you taken drugs for more than 4 weeks before?", ChoiceGroup.POPUP);
		fourMonthsTreatmentGroup.append("Yes",null);
		fourMonthsTreatmentGroup.append("No",null);
		
		verifyPatientTypeGroup = new ChoiceGroup("Verify the Patient type",ChoiceGroup.POPUP);
		verifyPatientTypeGroup.append("Correct",null);
		verifyPatientTypeGroup.append("Incorrect",null);
		
		patientTypeGroup = new ChoiceGroup("Patient type",ChoiceGroup.POPUP);
		
		
		verifyPatientCategoryGroup = new ChoiceGroup("Verify the Patient category",ChoiceGroup.POPUP);
		verifyPatientCategoryGroup.append("Correct",null);
		verifyPatientCategoryGroup.append("Incorrect",null);
		
		patientCategoryGroup = new ChoiceGroup("Patient category",ChoiceGroup.POPUP);
		
		
		moneyGroup = new ChoiceGroup("Did the CHW ask for any money for tests or drugs?",ChoiceGroup.POPUP);
		moneyGroup.append("Yes",null);
		moneyGroup.append("No",null);
		
		meetGPGroup = new ChoiceGroup("Did you meet the GP?",ChoiceGroup.POPUP);
		meetGPGroup.append("Yes",null);
		meetGPGroup.append("No",null);
		
		whyNotMeetGPGroup = new ChoiceGroup("Why could you not meet the GP?",ChoiceGroup.POPUP);
		
	
		otherWhyNotMeetGPReason = new TextField("Specify other:", "",30,TextField.UNEDITABLE);
		
		numSamplesField = new TextField("How many sputum samples did the CHW collect from you?","",3,TextField.NUMERIC);
		commentsField = new TextField("Any other comments?","",50,TextField.ANY);
		
		cmdOK = new Command("Save", Command.SCREEN, 1);
		cmdBack = new Command("Home", Command.BACK, 2);

		append(monitorIdField);
		
		append(dateField);
		append(didYouSeePatientGroup);
		
		append(verifyNameGroup);
		append(firstNameField);
		append(lastNameField);
		
		append(verifyGenderGroup);
		append(genderGroup);
		
		append(verifyAgeGroup);
		append(ageField);
		
		append(verifyAddressGroup);
		
		append(verifyTBTypeGroup);
		append(tbTypeGroup);
		
		append(takenTreatmentGroup);
		append(fourMonthsTreatmentGroup);
		
		append(verifyPatientTypeGroup);
		append(patientTypeGroup);
		
		append(verifyPatientCategoryGroup);
		append(patientCategoryGroup);
		
		append(moneyGroup);
		
		
		
		append(meetGPGroup);
		append(whyNotMeetGPGroup);
		append(otherWhyNotMeetGPReason);
		
		append(numSamplesField);
		append(commentsField);
		
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
	
	public String createRequestPayload() {
		String request = null;
		int nameVerifyIndex = verifyNameGroup.getSelectedIndex();
		int ageVerifyIndex = verifyAgeGroup.getSelectedIndex();
		int addressVerifyIndex = verifyAddressGroup.getSelectedIndex();
		int genderVerifyIndex = verifyGenderGroup.getSelectedIndex();
		int catVerifyIndex = verifyPatientCategoryGroup.getSelectedIndex();
		int pTypeVerifyIndex = verifyPatientTypeGroup.getSelectedIndex();
		int tTypeVerifyIndex = verifyTBTypeGroup.getSelectedIndex();
		int sawGPIndex = meetGPGroup.getSelectedIndex();
		
		
		request = "type=pvf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" +  patientId;
    	request += "&mid=" +   monitorIdField.getString();
    	
    	request += "&metp=" + didYouSeePatientGroup.getString(didYouSeePatientGroup.getSelectedIndex());
    	
    	if(whyNotSeenGroup.size()>0) {
    		request += "&whyNotMet" + whyNotSeenGroup.getString(whyNotSeenGroup.getSelectedIndex());
    	}
    	
    	request += "&vername=" + verifyNameGroup.getString(nameVerifyIndex);
    	
    	if(nameVerifyIndex==1) {
    		request += "&fname=" + firstNameField.getString();
    		request += "&lname=" + lastNameField.getString();
    	}
    	
    	request += "&vergender=" + verifyGenderGroup.getString(genderVerifyIndex);
    	
    	if(genderVerifyIndex == 1) {
    		request += "&gender=" + genderGroup.getString(genderGroup.getSelectedIndex());
    	}
    	
    	request += "&verage=" + verifyAgeGroup.getString(ageVerifyIndex);
    	
    	if(ageVerifyIndex == 1) {
    		request += "&age=" + ageField.getString();
    	}
    	
    	request += "&veraddr=" + verifyAddressGroup.getString(addressVerifyIndex);
    	
    	request += "&vertbtype=" + verifyTBTypeGroup.getString(tTypeVerifyIndex);
    	
    	if(tTypeVerifyIndex == 1) {
    		request += "&tbtype=" + tbTypeGroup.getString(tbTypeGroup.getSelectedIndex());
    	}
    	
    	request += "&verptype=" + verifyPatientTypeGroup.getString(pTypeVerifyIndex);
    	
    	if(pTypeVerifyIndex == 1) {
    		request += "&ptype=" + patientTypeGroup.getString(patientTypeGroup.getSelectedIndex());
    	}
    	
    	request += "&tt=" + takenTreatmentGroup.getString(takenTreatmentGroup.getSelectedIndex());
    	
    	request += "&fm=" + fourMonthsTreatmentGroup.getString(fourMonthsTreatmentGroup.getSelectedIndex());
    	
    	
    	request += "&vercat=" + verifyPatientCategoryGroup.getString(catVerifyIndex);
    	
    	if(catVerifyIndex == 1) {
    		request += "&cat=" + patientCategoryGroup.getString(patientCategoryGroup.getSelectedIndex());
    	}
    	
    	request += "&chwmoney=" + moneyGroup.getString(moneyGroup.getSelectedIndex());
    	request += "&metgp=" + meetGPGroup.getString(sawGPIndex);
    	
    	if(sawGPIndex==1) {
    		request += "&wng=" + whyNotMeetGPGroup.getString(whyNotMeetGPGroup.getSelectedIndex());
    		
    		if(whyNotMeetGPGroup.getSelectedIndex()==2) {
    			request += "&otrwhy=" + otherWhyNotMeetGPReason.getString();
    		}
    	}
    	
    	request += "&ns=" + numSamplesField.getString();
    	request += "&comm=" + commentsField.getString();
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
    	return request;
	}
	
	public boolean validate() {
		boolean result = true;
		int check = -1;
		int nameVerifyIndex = verifyNameGroup.getSelectedIndex();
		int ageVerifyIndex = verifyAgeGroup.getSelectedIndex();
		int addressVerifyIndex = verifyAddressGroup.getSelectedIndex();
		int genderVerifyIndex = verifyGenderGroup.getSelectedIndex();
		int catVerifyIndex = verifyPatientCategoryGroup.getSelectedIndex();
		int pTypeVerifyIndex = verifyPatientTypeGroup.getSelectedIndex();
		int tTypeVerifyIndex = verifyTBTypeGroup.getSelectedIndex();
		int sawGPIndex = meetGPGroup.getSelectedIndex();
		
		//future date check
		if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		
		else if(monitorIdField.getString()==null || monitorIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING, null);
			result = false;
		}
    	
		else if(nameVerifyIndex==1 && (firstNameField.getString()==null || firstNameField.getString().length()==0)) {
    		tbrMidlet.showAlert(ErrMsg.FIRST_NAME_MISSING, null);
    		result = false;
    	}
    		
		else if(nameVerifyIndex==1 && (lastNameField.getString()==null ||lastNameField.getString().length()==0)) {
    		tbrMidlet.showAlert(ErrMsg.LAST_NAME_MISSING, null);
    		result = false;
    	}
    	
    	else if(ageVerifyIndex==1 && (ageField.getString()==null ||ageField.getString().length()==0)) {
    		tbrMidlet.showAlert(ErrMsg.AGE_MISSING, null);
    		result = false;
    	}
    	
    	else if(sawGPIndex==1 && whyNotMeetGPGroup.getSelectedIndex()==2 && (otherWhyNotMeetGPReason.getString()==null || otherWhyNotMeetGPReason.getString().length()==0)) {
    		tbrMidlet.showAlert(ErrMsg.MUST_ENTER_OTHER_REASON, null);
    		result = false;
    	}
    	
		
		return result;
	}
	
	public void itemStateChanged(Item i) {
		
		if(i==verifyNameGroup) {
			if(verifyNameGroup.getSelectedIndex()==0) {
				firstNameField.setString("");
				firstNameField.setConstraints(TextField.UNEDITABLE);
				lastNameField.setString("");
				lastNameField.setConstraints(TextField.UNEDITABLE);
			}
			
			else {
				firstNameField.setConstraints(TextField.ANY);
				lastNameField.setConstraints(TextField.ANY);
			}
		}
		
		else if(i==verifyGenderGroup) {
			if(verifyGenderGroup.getSelectedIndex()==0) {
				if(genderGroup.size()!=0) {
					genderGroup.deleteAll();
				}
			}
			
			else if(genderGroup.size()==0) {
				genderGroup.append("Male",null);
				genderGroup.append("Female",null);
			}
		}
		
		else if(i==verifyAgeGroup) {
			if(verifyAgeGroup.getSelectedIndex()==0) {
				ageField.setString("");
				ageField.setConstraints(TextField.UNEDITABLE);
				
			}
			
			else {
				ageField.setConstraints(TextField.NUMERIC);		
			}
		}
		
		else if(i==verifyTBTypeGroup) {
			if(verifyTBTypeGroup.getSelectedIndex()==0) {
				if(tbTypeGroup.size()!=0) {
					tbTypeGroup.deleteAll();
				}
			}
			
			else if(tbTypeGroup.size()==0) {
				tbTypeGroup.append("Pulmonary",null);
				tbTypeGroup.append("Extra-pulmonary",null);
				tbTypeGroup.append("MDR",null);
			}
		}
		
		if(i==verifyPatientTypeGroup) {
			if(verifyPatientTypeGroup.getSelectedIndex()==0) {
				if(patientTypeGroup.size()!=0) {
					patientTypeGroup.deleteAll();
				}
			}
			
			else if(patientTypeGroup.size()==0) {
				patientTypeGroup.append("New",null);
				patientTypeGroup.append("Relapse",null);
				patientTypeGroup.append("Treatment after default",null);
				patientTypeGroup.append("Treatment after failure",null);
				patientTypeGroup.append("Transfer in",null);
				patientTypeGroup.append("Other",null);
			}
		}
		
		if(i==verifyPatientCategoryGroup) {
			if(verifyPatientCategoryGroup.getSelectedIndex()==0) {
				if(patientCategoryGroup.size()!=0) {
					patientCategoryGroup.deleteAll();
				}
			}
			
			else if(patientCategoryGroup.size()==0) {
				patientCategoryGroup.append("CAT I",null);
				patientCategoryGroup.append("CAT II",null);
			}
		}
		
		else if(i==meetGPGroup) {
			if(meetGPGroup.getSelectedIndex()==0) {
				if(whyNotMeetGPGroup.size()!=0) {
					whyNotMeetGPGroup.deleteAll();
					otherWhyNotMeetGPReason.setString("");
					otherWhyNotMeetGPReason.setConstraints(TextField.UNEDITABLE);
				}
			}
			
			else if(whyNotMeetGPGroup.size()==0) {
				whyNotMeetGPGroup.append("GP wasn't there",null);
				whyNotMeetGPGroup.append("CHW said not to",null);
				whyNotMeetGPGroup.append("Other",null);
			}
		}
		
		else if(i==whyNotMeetGPGroup) {
			if(whyNotMeetGPGroup.getSelectedIndex()!=2) {
				otherWhyNotMeetGPReason.setString("");
				otherWhyNotMeetGPReason.setConstraints(TextField.UNEDITABLE);
			}
			
			else {
				otherWhyNotMeetGPReason.setConstraints(TextField.ANY);
			}
		}
		
		else if(i==verifyAddressGroup) {
			if(verifyAddressGroup.getSelectedIndex()==1) {
				tbrMidlet.showAlert(ErrMsg.FILL_ADDRESS_UPDATE_FORM, null);
			}
		}
		
	}

}
