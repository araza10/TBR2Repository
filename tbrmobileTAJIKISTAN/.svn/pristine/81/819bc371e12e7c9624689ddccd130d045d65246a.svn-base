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

public class ClinicalDiagnosisForm extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	TextField gpIdField;// = null;
	DateField dateField;// = null;
	ChoiceGroup diagnosisGroup;
	TextField otherThanTBField;
	ChoiceGroup diagnosticBasisGroup;
	TextField paedScoreField;
	TextField otherDiagnosisField;
	TextField notesField;
	
	Command cmdOK;
	Command cmdBack;
	
	private Hashtable queryData;
	private Hashtable varNames;
	private String patientId;
	

	public ClinicalDiagnosisForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		gpIdField = null;
		dateField = null;
		diagnosisGroup = null;
		otherThanTBField = null;
		diagnosisGroup = null;
		paedScoreField = null;
		otherDiagnosisField = null;
		notesField = null;
		
		cmdOK = null;
		cmdBack = null;
		varNames = null;
	}
	
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
	
	public void init() {
		
		String data = "";
		
		//if(queryData!=null) {
			data += "Patient ID: " + patientId;
			data += "\nBase Smear: 2+,3+";
			data += "\nBase GX Result: Positive";
			data += "\nBase GX DST: SUSCEPTIBLE TB";
			data += "\nCXR: Bilateral Cavitation";
			data += "\nHistory of TB Drugs: YES";
			//data += "\nPatient Type: " + (String)queryData.get("type"); 
			//data += "\nCategory: " + (String)queryData.get("cat");
			append(data);
		//}
		
		gpIdField = new TextField("GP ID:", tbrMidlet.getCurrentUserId(),12, TextField.ANY);
		
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		
		diagnosisGroup = new ChoiceGroup("Clinical Diagnosis: ", ChoiceGroup.POPUP);
		diagnosisGroup.append("Adult Smear -ve Pulmonary TB",null);
		diagnosisGroup.append("Adult EP-TB",null);
		diagnosisGroup.append("Paediatric Smear +ve Pulmonary TB",null);
		diagnosisGroup.append("Paediatric Smear -ve Pulmonary TB",null);
		diagnosisGroup.append("Paediatric EP-TB",null);
		diagnosisGroup.append("Other than TB",null);
	
		otherThanTBField = new TextField("Specify Diagnosis: ","",50,TextField.ANY);
		otherThanTBField.setConstraints(TextField.UNEDITABLE);
		
		diagnosticBasisGroup = new ChoiceGroup("Reason for Diagnosis: ", ChoiceGroup.MULTIPLE);
		diagnosticBasisGroup.append("Clinical symptoms of TB",null);
		diagnosticBasisGroup.append("X-ray suggestive of TB",null);
		diagnosticBasisGroup.append("Past History of TB",null);
		diagnosticBasisGroup.append("Contact History of TB",null);
		diagnosticBasisGroup.append("Trial of Antibiotics with no response",null);
		diagnosticBasisGroup.append("Chronically enlarged lymph nodes",null);
		diagnosticBasisGroup.append("Lymph node biopsy positive for TB",null);
		diagnosticBasisGroup.append("Paediatric Scoring Scale",null);
		diagnosticBasisGroup.append("Mantoux test positive",null);
		diagnosticBasisGroup.append("Chronic Malnutrition (age <5 yrs)",null);
		diagnosticBasisGroup.append("Other", null);
		
		paedScoreField = new TextField("Paediatric Score: ","",2,TextField.NUMERIC);
		paedScoreField.setConstraints(TextField.UNEDITABLE);
		
		otherDiagnosisField = new TextField("Specify other diagnostic basis", "",50,TextField.ANY);
		otherDiagnosisField.setConstraints(TextField.UNEDITABLE);
		
		notesField = new TextField("Notes: ", "", 50, TextField.ANY);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(gpIdField);
		append(dateField);
		append(diagnosisGroup);
		append(otherThanTBField);
		append(diagnosticBasisGroup);
		append(paedScoreField);
		append(otherDiagnosisField);
		append(notesField);
		
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
		
		
		varNames = new Hashtable();
		varNames.put("Clinical symptoms of TB", "&clinTB");
		varNames.put("X-ray suggestive of TB", "&xray");
		varNames.put("Past History of TB", "&phist");
		varNames.put("Contact History of TB", "&chist");
		varNames.put("Trial of Antibiotics with no response", "&anti");
		varNames.put("Chronically enlarged lymph nodes", "&lymph");
		varNames.put("Lymph node biopsy positive for TB", "&lbiop");
		varNames.put("Paediatric Scoring Scale", "&pss");
		varNames.put("Mantoux test positive", "&mt");
		varNames.put("Chronic Malnutrition (age <5 yrs)", "&cm");
		varNames.put("Other", "&other");
		
	}
	
	public void itemStateChanged(Item i) {
		if(i==diagnosisGroup) {
			if(diagnosisGroup.getSelectedIndex()==5) {
				otherThanTBField.setConstraints(TextField.ANY);
				otherThanTBField.setMaxSize(50);
			}
			else {
				otherThanTBField.setString("");
				otherThanTBField.setConstraints(TextField.UNEDITABLE);
			}
		}
		
		else if(i==diagnosticBasisGroup) {
			if(diagnosticBasisGroup.isSelected(7)) {
				paedScoreField.setConstraints(TextField.NUMERIC);
				paedScoreField.setMaxSize(2);
			}
			
			else {
				paedScoreField.setString("");
				paedScoreField.setConstraints(TextField.UNEDITABLE);
				
			}
			
			if(diagnosticBasisGroup.isSelected(10)) {
				otherDiagnosisField.setConstraints(TextField.ANY);
				otherDiagnosisField.setMaxSize(50);
			}
			
			else {
				otherDiagnosisField.setString("");
				otherDiagnosisField.setConstraints(TextField.UNEDITABLE);
				
			}
			
			
		}
	}
	
	public boolean validate() {
		boolean result = true;
		if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		
		else if(diagnosisGroup.getSelectedIndex()==5 && (otherThanTBField.getString()==null || otherThanTBField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.OTHER_THAN_TB_MISSING,null);
			result = false;
		}
		
		else if(diagnosticBasisGroup.isSelected(7) && (paedScoreField.getString()==null || paedScoreField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.PAED_SCORE_MISSING, null);
			result = false;
		}
		
		else if(diagnosticBasisGroup.isSelected(10) && (otherDiagnosisField.getString()==null || otherDiagnosisField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.OTHER_DIAGNOSTIC_METHOD_MISSING, null);
			result = false;
		}
		
		if(result) {
			if(diagnosticBasisGroup.isSelected(7)) {
				int checkVal = Integer.parseInt(paedScoreField.getString());
				
				if(checkVal < 0 || checkVal > 25) {
					tbrMidlet.showAlert(ErrMsg.BAD_PAED_SCORE, patientId);
					result = false;
				}
			}
		}
		
		return result;
	}
	
	public String createRequestPayload(){
		String request = "";
		String temp = "";
		request = "type=cdf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&gpid=" + gpIdField.getString();
    	request += "&cd=" + diagnosisGroup.getString(diagnosisGroup.getSelectedIndex());
    	
    	if(diagnosisGroup.getSelectedIndex()==5) {
    		request += "&otd=" + otherThanTBField.getString();
    	}
    	
    	for(int i=0; i<diagnosticBasisGroup.size();i++) {
    		temp = (String)(varNames.get(diagnosticBasisGroup.getString(i)));
    		
    		if(diagnosticBasisGroup.isSelected(i)) {
    			request += temp + "=YES";
    		}
    		
    		else {
    			request += temp + "=NO";
    		}
    		
    	}
    	String paedScore = "";
    	String otherDiagnostic = "";
    	if(diagnosticBasisGroup.isSelected(7))
    		request += "&score=" + paedScoreField.getString();
    	
    	if(diagnosticBasisGroup.isSelected(10))
    		request += "&otherdiag=" + otherDiagnosisField.getString();
    			
    			
    	request+= "&notes=" + notesField.getString();
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}

}
