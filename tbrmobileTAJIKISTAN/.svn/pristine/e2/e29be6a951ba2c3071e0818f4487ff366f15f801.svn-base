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
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class PatientInfoForm extends BaseTBReachForm implements CommandListener, ItemCommandListener, ItemStateListener {

	//private TBReachMainMIDlet tbrMidlet;
	TextField idField;
	TextField idConfirm;
	DateField dateField;
	TextField chwIdField;
	TextField nicField;
	ChoiceGroup nicTypeGroup;
	ChoiceGroup nicOwnerGroup;
	TextField nicOwnerName;
	TextField phoneField;
	TextField fatherFirstNameField;
	TextField fatherLastNameField;
	DateField dobField;
	TextField ageField;
	
	ChoiceGroup sexGroup;
	
	ChoiceGroup maritalStatusGroup;
	ChoiceGroup religionGroup;
	ChoiceGroup ethnicityGroup;
	TextField otherEthnicityField;
	TextField numPeopleField;
	TextField numAdultsField;
	TextField numChildrenField;
	TextField houseNumberField;
	TextField streetNameField;
	/*TextField sectorNumberField;
	TextField colonyField;
	TextField townField;
	TextField ucField;
	TextField landmarkField;
	TextField gasMeterField;
	ChoiceGroup pastDrugsGroup;*/
	/*TextField structNumberField;
	TextField gpsLatField;
	TextField gpsLongField;*/
	
	/*ChoiceGroup familyHistoryGroup;
	ChoiceGroup familyMembersGroup;
	ChoiceGroup patientHistoryGroup;
	ChoiceGroup previousTreatmentGroup;
	ChoiceGroup completedTreatmentGroup;
	ChoiceGroup patientTypeGroup;
	ChoiceGroup patientCategoryGroup;*/
	
	Command cmdOK;
	Command cmdBack;
	Command cmdNA;
	
	boolean naActive;
	
	public PatientInfoForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		idField = null;
		idConfirm = null; 
		chwIdField = null;
		dateField = null;
		nicField = null;
		nicOwnerGroup = null;
		nicTypeGroup = null;
		nicOwnerName = null;
		phoneField = null;
		fatherFirstNameField = null;
		fatherLastNameField = null;
		dobField = null;
		ageField = null;

		sexGroup = null;
		maritalStatusGroup = null;
		religionGroup = null;
		ethnicityGroup = null;
		otherEthnicityField = null;
		numPeopleField = null;
		numAdultsField = null;
		numChildrenField = null;
		houseNumberField = null;
		streetNameField = null;
		/*sectorNumberField = null;
		colonyField = null;
		townField = null;
		ucField = null;
		landmarkField = null;
		gasMeterField = null;*/
		/*structNumberField = null;
		gpsLatField = null;
		gpsLongField = null;

		familyHistoryGroup = null;
		familyMembersGroup = null;
		patientHistoryGroup = null;
		previousTreatmentGroup = null;
		completedTreatmentGroup = null;
		patientTypeGroup = null;
		patientCategoryGroup = null;*/
		
		cmdOK = null;
		cmdBack = null;
		cmdNA = null;
		naActive = false;

	}
	
	public void init() {
		idField = new TextField("Suspect ID:", "", IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);
		idConfirm = new TextField("Re-enter Suspect ID:", "", IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);
		chwIdField = new TextField("Health Worker ID:", tbrMidlet.getCurrentUserId(),12, TextField.ANY);
		nicField = new TextField("Suspect NIC:", "", 15, TextField.NUMERIC);
		dateField = new DateField("Date:" , DateField.DATE);
		dateField.setDate(new Date());
/*		nicTypeGroup = new ChoiceGroup("Is this an old NIC or a new one?", ChoiceGroup.POPUP);
		nicTypeGroup.append("New", null);
		nicTypeGroup.append("Old", null);
		nicTypeGroup.append("Refused", null);
		nicTypeGroup.append("Don't Know", null);
		
		nicOwnerGroup = new ChoiceGroup("Whose NIC is it?", ChoiceGroup.POPUP);
		nicOwnerGroup.append("Own", null);
		nicOwnerGroup.append("Other", null);
		nicOwnerGroup.append("Refused", null);
		nicOwnerGroup.append("Don't Know", null);
		
		nicOwnerName = new TextField("Name of NIC owner:", "", 25, TextField.ANY);
*/		phoneField = new TextField("Phone Number:", "", 12, TextField.NUMERIC);
		fatherFirstNameField = new TextField("Suspect Father's/Husband's First Name:", "", 15, TextField.ANY);
		fatherLastNameField = new TextField("Suspect Father's/Husband's Last Name:", "", 15, TextField.ANY);
		
		dobField = new DateField("Suspect Date of Birth", DateField.DATE);
	
		ageField = new TextField("Age:", "", 3, TextField.NUMERIC);
		
		sexGroup = new ChoiceGroup("Gender:", Choice.POPUP);
		sexGroup.append("Male:",null);
		sexGroup.append("Female:", null);
		
		//ucField = new TextField("Address: UC:", "", 50, TextField.ANY);

/*		maritalStatusGroup = new ChoiceGroup("Marital Status:",ChoiceGroup.POPUP);
		maritalStatusGroup.append("Single", null);
		maritalStatusGroup.append("Married", null);
		maritalStatusGroup.append("Divorced", null);
		maritalStatusGroup.append("Separated", null);
		maritalStatusGroup.append("Refused", null);
		maritalStatusGroup.append("Don't Know", null);
		
		religionGroup = new ChoiceGroup("Religion:",ChoiceGroup.POPUP);
		religionGroup.append("Muslim", null);
		religionGroup.append("Hindu", null);
		religionGroup.append("Christian", null);
		religionGroup.append("Parsi", null);
		religionGroup.append("Other", null);
		religionGroup.append("Refused", null);
		religionGroup.append("Don't Know", null);
		
		ethnicityGroup = new ChoiceGroup("Ethnicity", ChoiceGroup.POPUP);
		ethnicityGroup.append("Muhajir",null);
		ethnicityGroup.append("Sindhi",null);
		ethnicityGroup.append("Pakhtun",null);
		ethnicityGroup.append("Punjabi",null);
		ethnicityGroup.append("Balochi",null);
		ethnicityGroup.append("Bengali",null);
		ethnicityGroup.append("Bihari",null);
		ethnicityGroup.append("Memon",null);
		ethnicityGroup.append("Gujrati",null);
		ethnicityGroup.append("Brohi",null);
		ethnicityGroup.append("Hindko",null);
		ethnicityGroup.append("Saraiki",null);
		ethnicityGroup.append("Jadgal",null);
		ethnicityGroup.append("Kathiawari",null);
		ethnicityGroup.append("Other",null);
*/
//		otherEthnicityField = new TextField("Ethnicity:", "", 15, TextField.ANY);
/*		numPeopleField = new TextField("Number of People in Household:", "", 2, TextField.NUMERIC);
		numAdultsField = new TextField("Number of Adults in Household:", "", 2, TextField.NUMERIC);
		numChildrenField = new TextField("Number of Children in Household:", "", 2, TextField.NUMERIC);
*/		houseNumberField = new TextField("Street:", "", 50, TextField.ANY);
		
		streetNameField = new TextField("City:", "", 50, TextField.ANY);
		/*sectorNumberField = new TextField("Address:Sector Number:", "", 50, TextField.ANY);
		colonyField =  new TextField("Address:Colony Name:", "", 50, TextField.ANY);
		townField = new TextField("Address:Town Name:", "", 50, TextField.ANY);
		landmarkField = new TextField("Address:Landmark:", "", 50, TextField.ANY);
//		gasMeterField = new TextField("Gas Meter Number:", "", 10, TextField.NUMERIC);
		
		
		pastDrugsGroup = new ChoiceGroup("Ever taken drugs for TB in the past?", ChoiceGroup.POPUP);
		pastDrugsGroup.append("Yes",null);
		pastDrugsGroup.append("No", null);
		pastDrugsGroup.append("Don't know", null);
		pastDrugsGroup.append("Refused", null);*/
		/*structNumberField = new TextField("IRD Structure Number", "", 6, TextField.NUMERIC);
		gpsLatField = new TextField("GPS Latitude", "", 20, TextField.DECIMAL);
		gpsLongField = new TextField("GPS Longitude", "", 20, TextField.DECIMAL);

		familyHistoryGroup = new ChoiceGroup("Family History of TB", Choice.EXCLUSIVE);
		familyHistoryGroup.append("Yes", null);
		familyHistoryGroup.append("No", null);
		
		familyMembersGroup = new ChoiceGroup("Family member(s) with history of TB", Choice.MULTIPLE);
		familyMembersGroup.append("Mother",null);
		familyMembersGroup.append("Father",null);
		familyMembersGroup.append("Brother",null);
		familyMembersGroup.append("Sister",null);
		familyMembersGroup.append("Daughter",null);
		familyMembersGroup.append("Son",null);
		familyMembersGroup.append("Grandfather",null);
		familyMembersGroup.append("Grandmother",null);
		familyMembersGroup.append("Granddaugher",null);
		familyMembersGroup.append("Grandson",null);
		familyMembersGroup.append("Uncle",null);
		familyMembersGroup.append("Aunt",null);
		familyMembersGroup.append("Other",null);
		
		patientHistoryGroup = new ChoiceGroup("Patient History of TB", Choice.EXCLUSIVE);
		patientHistoryGroup.append("Had TB Before", null);
		patientHistoryGroup.append("Never had TB", null);

		previousTreatmentGroup = new ChoiceGroup("Received treatment for TB previously", Choice.EXCLUSIVE);
		previousTreatmentGroup.append("Yes", null);
		previousTreatmentGroup.append("No", null);
		
		completedTreatmentGroup = new ChoiceGroup("Completed treatment for TB previously", Choice.EXCLUSIVE);
		completedTreatmentGroup.append("Yes", null);
		completedTreatmentGroup.append("No", null);
		
		
		patientTypeGroup = new ChoiceGroup("Patient Type", ChoiceGroup.EXCLUSIVE);
		patientTypeGroup.append("New", null);
		patientTypeGroup.append("Relapse", null);
		patientTypeGroup.append("Transfer in", null);
		patientTypeGroup.append("Treatment after default", null);
		patientTypeGroup.append("Treatment after failure", null);
		
		patientCategoryGroup = new ChoiceGroup("Patient Category", ChoiceGroup.EXCLUSIVE);
		patientTypeGroup.append("Cat I", null);
		patientTypeGroup.append("Cat II", null);*/
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		cmdNA = new Command("Insert N/A", Command.OK, 1);
		
		append(chwIdField);
		append(dateField);
		append(idField);
		append(idConfirm);
		
		
		append(phoneField);
		
//		append(nicField);
//		append(nicTypeGroup);
//		append(nicOwnerGroup);
//		append(fatherFirstNameField);
//		append(fatherLastNameField);
		append(ageField);
		append(dobField);
		append(sexGroup);
//		append(maritalStatusGroup);
//		append(religionGroup);
//		append(ethnicityGroup);
		//append(otherEthnicityField);
/*		append(numPeopleField);
		append(numAdultsField);
		append(numChildrenField);
*/		houseNumberField.addCommand(cmdNA);
		houseNumberField.setItemCommandListener(this);
		append(houseNumberField);
		
		append(streetNameField);
		streetNameField.addCommand(cmdNA);
		streetNameField.setItemCommandListener(this);
		
		/*append(sectorNumberField);
		sectorNumberField.addCommand(cmdNA);
		sectorNumberField.setItemCommandListener(this);
		
		append(colonyField);
		colonyField.addCommand(cmdNA);
		colonyField.setItemCommandListener(this);
		
		append(townField);
		townField.addCommand(cmdNA);
		townField.setItemCommandListener(this);
		
		append(ucField);
		ucField.addCommand(cmdNA);
		ucField.setItemCommandListener(this);
		
		append(landmarkField);
		landmarkField.addCommand(cmdNA);
		landmarkField.setItemCommandListener(this);*/
		
//		append(gasMeterField);
		//append(pastDrugsGroup);
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
				//System.out.println(request);
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
			tbrMidlet.setDisplay(prevDisplayable);
		}
		
		/*else if(c==cmdNA) {
			
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
		
		else if(chwIdField.getString()==null || chwIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING, null);
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
		
		else if (nicField.getString()==null || nicField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.NIC_MISSING, null);
			result = false;
		}
		
		//TODO: Add validation for length of NIC based on choice
		
		else if(nicOwnerGroup.getSelectedIndex()==1 && (nicOwnerName.getString()==null || nicOwnerName.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.NIC_OWNER_MISSING, null);
			result = false;
		}
		
		else if (fatherFirstNameField.getString()==null || fatherFirstNameField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.FATHER_FIRST_NAME_MISSING, null);
			result = false;
		}
		
		else if (fatherLastNameField.getString()==null || fatherLastNameField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.FATHER_LAST_NAME_MISSING, null);
			result = false;
		}
		
		else if(dobField.getDate()==null || dobField.getDate().toString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.DOB_MISSING, null);
			result = false;
		}
		
		else if(ageField.getString()==null || ageField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.AGE_MISSING, null);
			result = false;
		}
		
		else if(ethnicityGroup.getSelectedIndex() == 14 && (otherEthnicityField.getString()==null || otherEthnicityField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.ETHNICITY_MISSING, null);
			result = false;
		}
		
		else if(numPeopleField.getString()==null || numPeopleField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.PEOPLE_IN_HOUSE_MISSING, null);
			result = false;
		}
		
		else if(numAdultsField.getString()==null || numAdultsField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.ADULT_IN_HOUSE_MISSING, null);
			result = false;
		}
		
		else if(numChildrenField.getString()==null || numChildrenField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHILDREN_IN_HOUSE_MISSING, null);
			result = false;
		}
		
		
		
		else if(houseNumberField.getString()==null || houseNumberField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.HOUSE_NUM_MISSING, null);
			result = false;
		}
		
		else if(streetNameField.getString()==null || streetNameField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.STREET_NAME_MISSING, null);
			result = false;
		}
		
/*		else if(sectorNumberField.getString()==null || sectorNumberField.getString().length()==0) {
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
		
		else if(gasMeterField.getString()==null || gasMeterField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GAS_METER_MISSING, null);
			result = false;
		}*/
		System.out.println(result);
		if(result) {
			System.out.println("Checking...");
			int total = Integer.parseInt(numPeopleField.getString());
			int adults = Integer.parseInt(numAdultsField.getString());
			int kids = Integer.parseInt(numChildrenField.getString());
			
			System.out.println(total);
			System.out.println(adults);
			System.out.println(kids);
			if(total!=77 && total !=88 && adults!=77 && adults!=88 && kids!=77 && kids!=88 && total!=adults+kids) {
				tbrMidlet.showAlert(ErrMsg.PEOPLE_MUST_ADD_UP, null);
				result = false;
			}
		
		}
		
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=pif";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + idField.getString();
    	request += "&chwid=" + chwIdField.getString();
    	request += "&nic=" + nicField.getString();
    	request += "&nt=" + nicTypeGroup.getString(nicTypeGroup.getSelectedIndex());
    	
    	if(nicOwnerGroup.getSelectedIndex()==0) {
    		request += "&no=" + nicOwnerGroup.getString(0);
    	}
    	
    	else {
    		request += "&no=" + nicOwnerName.getString();
    	}
    	
    	request += "&ffn=" + fatherFirstNameField.getString();
    	request += "&fln=" + fatherLastNameField.getString();
    	request += "&dob=" + DateTimeUtil.getDate(dobField.getDate());
    	request += "&age=" + ageField.getString();
    	request += "&sex=" + sexGroup.getString(sexGroup.getSelectedIndex());
    	request += "&ms=" + maritalStatusGroup.getString(maritalStatusGroup.getSelectedIndex());
    	request += "&rel=" + religionGroup.getString(religionGroup.getSelectedIndex());
    	request += "&phn=" + phoneField.getString();
    	if(ethnicityGroup.getSelectedIndex()!=14) {
    		request += "&eth=" + ethnicityGroup.getString(ethnicityGroup.getSelectedIndex());
    	}
    	else {
    		request += "&eth=" + otherEthnicityField.getString();
    	}
    	request += "&ppl=" + numPeopleField.getString();
    	request += "&adl=" + numAdultsField.getString();
    	request += "&chl=" + numChildrenField.getString();
    	request += "&hn=" + houseNumberField.getString();
    	request += "&sn=" + streetNameField.getString();
/*    	request += "&cn=" + colonyField.getString();
    	request += "&sec=" + sectorNumberField.getString();
    	request += "&tn=" + townField.getString();
    	request += "&lm=" + landmarkField.getString();
    	request += "&uc=" + ucField.getString();
    	request += "&gmn=" + gasMeterField.getString();
    	request += "&ptd=" + pastDrugsGroup.getString(pastDrugsGroup.getSelectedIndex());
*/    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
    	
		return request;
	}
	
	public void commandAction(Command c, Item i) {
		if(c==cmdNA) {
			if(i.equals(houseNumberField)) {
				houseNumberField.setString("N/A");
			}
			
			else if(i.equals(streetNameField)) {
				streetNameField.setString("N/A");
			}
			
/*			else if(i.equals(sectorNumberField)) {
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
			*/
			else if(i.equals(phoneField)) {
				phoneField.setString("N/A");
			}
			
		}
		
	}
	
	public void itemStateChanged(Item i) {
		if(i==ethnicityGroup) {
			if(ethnicityGroup.getSelectedIndex()==14 && get(size()-12)!=otherEthnicityField) {
				insert(size()-12, otherEthnicityField);
			}
			
			else if(ethnicityGroup.getSelectedIndex()!=14 && get(size()-13)==otherEthnicityField) {
				System.out.println("here");
				delete(size()-13);
			}
		}
		
		else if(i==nicOwnerGroup) {
			if(nicOwnerGroup.getSelectedIndex()==1 && get(7)!=nicOwnerName) {
				insert(7, nicOwnerName);
				
			}
			
			else if(get(7)==nicOwnerName) {
				delete(7);
				
			}
		}
	
		else if(i==ageField) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date(System.currentTimeMillis()));
			int age = -1;
			String ageString = ageField.getString();
			
			if(ageString!=null && ageString.length()!=0) {
				age = Integer.parseInt(ageString);
				c.set(Calendar.YEAR, c.get(Calendar.YEAR)-age);
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
				dobField.setDate(c.getTime());
			}
			
			
		}
	}

}
