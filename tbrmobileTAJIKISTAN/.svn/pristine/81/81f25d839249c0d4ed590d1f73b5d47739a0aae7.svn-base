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

public class PaedClinicalDiagnosis extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	
	ChoiceGroup afbSmear;
	ChoiceGroup ppd;
	TextField ppdResult;
	DateField dateField;
	TextField gpIdField;
	
	ChoiceGroup cxr;
	ChoiceGroup granuloma;

	ChoiceGroup ppaDone;
	TextField initPPA;
	TextField finalPPA;
	
	ChoiceGroup conclusion;
	TextField antiBioticTime;
	TextField otherDisease;
	TextField otherField;
	
	private Hashtable queryData;
	
	private String patientId;
	
	Command cmdOK;
	Command cmdBack;
	
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
	
	public PaedClinicalDiagnosis(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
	}
	
	public void init() {
		
		if(queryData!=null) {
			
			append((String) queryData.get("data"));
		}
			dateField = new DateField("Date:",DateField.DATE);
			dateField.setDate(new Date());
			gpIdField = new TextField("GP ID:", "", 12, TextField.ANY);	
			
			afbSmear  = new ChoiceGroup("AFB Smear", ChoiceGroup.POPUP);
			afbSmear.append("Positive", null);
			afbSmear.append("Negative", null);
			afbSmear.append("Pending", null);
			afbSmear.append("Could not produce", null);
			
			ppd = new ChoiceGroup("PPD done", ChoiceGroup.POPUP);
			ppd.append("Yes", null);
			ppd.append("No", null);
			
			ppdResult = new TextField("PPD Result (nm)","",3,TextField.NUMERIC);
			
			cxr  = new ChoiceGroup("CXR", ChoiceGroup.POPUP);
			cxr.append("Non Specific", null);
			cxr.append("Suggestive of TB", null);
			cxr.append("Normal", null);
			cxr.append("N/A", null);
			
			granuloma  = new ChoiceGroup("Granuloma", ChoiceGroup.POPUP);
			granuloma.append("Non Specific", null);
			granuloma.append("Suggestive of TB", null);
			granuloma.append("Normal", null);
			granuloma.append("N/A", null);
			
			ppaDone = new ChoiceGroup("PPA Done?", ChoiceGroup.POPUP);
			ppaDone.append("Yes", null);
			ppaDone.append("No", null);
			
			initPPA = new TextField("Initial PPA", "", 3, TextField.NUMERIC);
			finalPPA = new TextField("Final PPA", "", 3, TextField.NUMERIC);
			
			conclusion = new ChoiceGroup("Conclusion", ChoiceGroup.POPUP);
			conclusion.append("Smear Positve Pulmonary Pediatric TB", null);
			conclusion.append("Smear Negative Pulmonary Pediatric TB", null);
			conclusion.append("Extra Pulmonary Pediatric TB", null);
			conclusion.append("Given Antibiotic Followup", null);
			conclusion.append("No Follow up required (other disease)", null);
			conclusion.append("Other", null);
			
			antiBioticTime = new TextField("Antibiotic Followup Time (days)", "", 2, TextField.UNEDITABLE);
			otherDisease = new TextField("Specify Disease:", "", 50, TextField.UNEDITABLE);
			otherField = new TextField("Specify other:","",50,TextField.UNEDITABLE);
			
			
			append(dateField);//1
			append(gpIdField);//2
			append(afbSmear);//3
			append(ppd);//4
			append(ppdResult);//5
			append(cxr);//6
			append(granuloma);//7
			append(ppaDone);//8
			append(initPPA);//9
			append(finalPPA);//10
			append(conclusion);//11
			append(antiBioticTime);//12
			append(otherDisease);//13
			append(otherField);//14
				
			cmdOK = new Command("Save", Command.OK, 1);
			cmdBack = new Command("Home", Command.BACK, 2);
			
			addCommand(cmdOK);
			addCommand(cmdBack);
			
			startDate = DateTimeUtil.getDate();
			startTime = DateTimeUtil.getTime();
			
			setCommandListener(this);
			setItemStateListener(this);
			
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=pcdf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + patientId;
    	request += "&gpid=" + gpIdField.getString();
    	
    	request += "&afb=" + afbSmear.getString(afbSmear.getSelectedIndex());
    	
    	request += "&ppd=" + ppd.getString(ppd.getSelectedIndex());
    	if(ppd.getSelectedIndex()==0) {
    		request += "&ppdr=" + ppdResult.getString();
    	}
    	request += "&cxr=" + cxr.getString(cxr.getSelectedIndex());
    	request += "&gran=" + granuloma.getString(granuloma.getSelectedIndex());
    	
    	request += "&ppa=" + ppaDone.getString(ppaDone.getSelectedIndex());
    	
    	if(ppaDone.getSelectedIndex()==0) {
    		request += "&initppa=" + initPPA.getString();
    		request += "&finalppa=" + finalPPA.getString();
    	}
    	
    	
    	request += "&conc=" + conclusion.getString(conclusion.getSelectedIndex());
    	if(conclusion.getSelectedIndex()==3) {
    		request += "&antitime=" + antiBioticTime.getString();
    	}
    	
    	else if(conclusion.getSelectedIndex()==4) {
    		request += "&otherdis=" + otherDisease.getString();
    	}
    	
    	else if(conclusion.getSelectedIndex()==5) {
    		request += "&other=" + otherField.getString();
    	}
  
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}

	public boolean validate() {
		boolean result = true;
		if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING,null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		
		else if(ppd.getSelectedIndex()==0 && (ppdResult.getString()==null || ppdResult.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.PPD_RESULT_MISSING,null);
			result = false;
		}
		
		else if(ppaDone.getSelectedIndex()==0 && (initPPA.getString()==null || initPPA.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.INITPPA_MISSING,null);
			result = false;
		}
		
		else if(ppaDone.getSelectedIndex()==0 && (finalPPA.getString()==null || finalPPA.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.FINALPPA_MISSING,null);
			result = false;
		}
		
		else if(conclusion.getSelectedIndex()==3 && (antiBioticTime.getString()==null || antiBioticTime.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.ANTI_TIME_MISSING,null);
			result = false;
		}
		
		else if(conclusion.getSelectedIndex()==4 && (otherDisease.getString()==null || otherDisease.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.OTHER_DISEASE_MISSING,null);
			result = false;
		}
		
		else if(conclusion.getSelectedIndex()==5 && (otherField.getString()==null || otherField.getString().length()==0)) {
			tbrMidlet.showAlert(ErrMsg.OTHER_CONCLUSION_MISSING,null);
			result = false;
		}
		
		return result;
	}
	
	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
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

	public void itemStateChanged(Item i) {
		// TODO Auto-generated method stub
		if(i==ppd) {
			if(ppd.getSelectedIndex()==0) {
				ppdResult.setConstraints(TextField.NUMERIC);
			}
			
			else {
				ppdResult.setString("");
				ppdResult.setConstraints(TextField.UNEDITABLE);
			}
		}
		
		else if(i==ppaDone) {
			if(ppaDone.getSelectedIndex()==0) {
				initPPA.setConstraints(TextField.NUMERIC);
				finalPPA.setConstraints(TextField.NUMERIC);
			}
			
			else {
				initPPA.setString("");
				initPPA.setConstraints(TextField.UNEDITABLE);
				finalPPA.setString("");
				finalPPA.setConstraints(TextField.UNEDITABLE);
			}
		}
		
		else if(i==conclusion) {
			if(conclusion.getSelectedIndex()==3) {
				antiBioticTime.setConstraints(TextField.NUMERIC);
				otherDisease.setString("");
				otherDisease.setConstraints(TextField.UNEDITABLE);
				otherField.setString("");
				otherField.setConstraints(TextField.UNEDITABLE);
			}
			
			else if(conclusion.getSelectedIndex()==4) {
				otherDisease.setConstraints(TextField.ANY);
				antiBioticTime.setString("");
				antiBioticTime.setConstraints(TextField.UNEDITABLE);
				otherField.setString("");
				otherField.setConstraints(TextField.UNEDITABLE);
			}
			
			else if(conclusion.getSelectedIndex()==5) {
				otherField.setConstraints(TextField.ANY);
				antiBioticTime.setString("");
				antiBioticTime.setConstraints(TextField.UNEDITABLE);
				otherDisease.setString("");
				otherDisease.setConstraints(TextField.UNEDITABLE);
			}
			
			else {
				antiBioticTime.setString("");
				antiBioticTime.setConstraints(TextField.UNEDITABLE);
				otherDisease.setString("");
				otherDisease.setConstraints(TextField.UNEDITABLE);
				otherField.setString("");
				otherField.setConstraints(TextField.UNEDITABLE);
			}
		}
	}

}
