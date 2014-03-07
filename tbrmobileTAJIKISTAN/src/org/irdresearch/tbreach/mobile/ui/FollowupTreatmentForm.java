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

public class FollowupTreatmentForm extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	TextField gpIdField;
	DateField dateField;
	TextField weightField;
	TextField heightField;
	
	ChoiceGroup phaseGroup;
	ChoiceGroup regimenGroup;
	ChoiceGroup numTabletGroup;
	ChoiceGroup streptoGroup;
	
	Command cmdOK;
	Command cmdBack;
	
	private Hashtable queryData;
	private String patientId;
	
	private String cat;
	
	public FollowupTreatmentForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		queryData = null;
		patientId = null;
		cat = null;
		
		gpIdField = null;
		dateField = null;
		weightField = null;
		heightField = null;
		
		phaseGroup = null;
		regimenGroup = null;
		numTabletGroup = null;
		streptoGroup = null;
		
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
					}*/
				//}
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
			data += "\nTx Start: 01/01/2011";
			data += "\nBase Smear: 2+,3+";
			data += "\nBase GX Result: Positive";
			data += "\nBase GX DST: SUSECPTIBLE TB";
			data += "\nCXR: Bilateral Cavitation";
			data += "\nSmear - 2m: 2+,2+";
			data += "\nSmear - 3m: ";
			data += "\nSmear - 5m: Nil,Nil";
			data += "\nSmear - 7m: ";
			data += "\nDisease Site: PULMONARY";
			data += "\nPatient Type: New"; 
			data += "\nCategory: Cat 11";
			append(data);
			
			cat = "CAT II";
		//}
		
		gpIdField = new TextField("GP ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		dateField = new DateField("Date:",DateField.DATE);
		dateField.setDate(new Date());
		
		weightField = new TextField("Weight (kg):", "", 8, TextField.DECIMAL);
		heightField = new TextField("Height (cm):", "", 8, TextField.DECIMAL);
		
		phaseGroup = new ChoiceGroup("Treatment Phase", ChoiceGroup.EXCLUSIVE);
		phaseGroup.append("Intensive",null);
		phaseGroup.append("Continuous", null);
		
		regimenGroup = new ChoiceGroup("Regimen", ChoiceGroup.POPUP);
		regimenGroup.append("RHZE", null);
		regimenGroup.append("RHZES",null);
		regimenGroup.append("EH", null);
		regimenGroup.append("RHE", null);
		regimenGroup.append("RH", null);
		
		numTabletGroup = new ChoiceGroup("Fixed Dose Combination Tablets", ChoiceGroup.POPUP);
		numTabletGroup.append("1", null);
		numTabletGroup.append("1.5", null);
		numTabletGroup.append("2", null);
		numTabletGroup.append("3", null);
		numTabletGroup.append("4", null);
		numTabletGroup.append("5", null);
		
		streptoGroup = new ChoiceGroup("Streptomycin Dosage", ChoiceGroup.POPUP);
		streptoGroup.append("0", null);
		streptoGroup.append("250", null);
		streptoGroup.append("500", null);
		streptoGroup.append("750", null);
		
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(gpIdField);
		append(dateField);
		append(weightField);
		append(heightField);
		
		append(phaseGroup);
		append(regimenGroup);
		append(numTabletGroup);
		//append(streptoGroup);
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
		
	}
	
	public void itemStateChanged(Item i) {
		if(i==regimenGroup) {
			if(regimenGroup.getSelectedIndex()==0 && get(size()-1)!=streptoGroup) {
				append(streptoGroup);
			}
			
			else if(regimenGroup.getSelectedIndex()!=0 && get(size()-1)==streptoGroup) {
				delete(size()-1);
			}
		}
		
		else if(i==this.phaseGroup) {
			if(phaseGroup.getSelectedIndex()==0 ) {
				if(cat!=null && cat.equals("CAT II")) {
					regimenGroup.deleteAll();
					regimenGroup.append("RHZES", null);
					regimenGroup.setSelectedIndex(0, true);
					if(regimenGroup.getSelectedIndex()==0 && get(size()-1)!=streptoGroup) {
						append(streptoGroup);
					}
					
				}
				
				else {
					regimenGroup.deleteAll();
					regimenGroup.append("RHZES", null);
					regimenGroup.append("RHZE", null);
					regimenGroup.append("EH", null);
					regimenGroup.append("RHE", null);
					regimenGroup.append("RH", null);
					regimenGroup.setSelectedIndex(0, true);
					if(regimenGroup.getSelectedIndex()==0 && get(size()-1)!=streptoGroup) {
						append(streptoGroup);
					}
				}
			}
			
			else {
				regimenGroup.deleteAll();
				regimenGroup.append("RHZES", null);
				regimenGroup.append("RHZE", null);
				regimenGroup.append("EH", null);
				regimenGroup.append("RHE", null);
				regimenGroup.append("RH", null);
				regimenGroup.setSelectedIndex(0, true);
				if(regimenGroup.getSelectedIndex()==0 && get(size()-1)!=streptoGroup) {
					append(streptoGroup);
				}
			}
		}
	}
	
	public boolean validate() {
		boolean result = true;
		
		if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING,null);
			result = false;
		}
	
		else if(weightField.getString()==null || weightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.WEIGHT_MISSING,null);
			result = false;
		}
		
		else if(heightField.getString()==null || heightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.HEIGHT_MISSING,null);
			result = false;
		}
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=ftf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&gpid=" + gpIdField.getString();
    	request += "&ht=" + heightField.getString();
    	request += "&wt=" + weightField.getString();
    	request += "&pt=" + phaseGroup.getString(phaseGroup.getSelectedIndex());
    	request += "&reg=" + regimenGroup.getString(regimenGroup.getSelectedIndex());
    	request += "&tab=" + numTabletGroup.getString(numTabletGroup.getSelectedIndex());
    	if(regimenGroup.getSelectedIndex()==1) {
    		request += "&str=" + streptoGroup.getString(streptoGroup.getSelectedIndex());
    	}
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}

}
