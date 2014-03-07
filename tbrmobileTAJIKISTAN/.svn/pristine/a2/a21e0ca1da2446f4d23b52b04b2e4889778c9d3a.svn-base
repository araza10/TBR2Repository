package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class PaedClinicalVisitForm extends BaseTBReachForm implements CommandListener {
	
	DateField dateField;
	TextField chwIdField;
	//TextField gpIdField;
	TextField heightField;
	TextField weightField;
	
	Command cmdOK;
	Command cmdBack;

	private Hashtable queryData;
	private String patientId;
	
	public PaedClinicalVisitForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		queryData = null;
		patientId = null;
		
		chwIdField = null;
		//gpIdField = null;
		dateField = null;
		weightField = null;
		heightField = null;
		
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
	
	public void init() {
		String data = "";
		
		//if(queryData!=null) {
			data += "Patient ID: " + patientId;
			data += "\nMR Number: " + "112112112";
			data += "\nTx Start date: " + "2011/10/21";
			data += "\nPatient Type: " + "New";
			data += "\nCategory: " + "Cat I";
			data += "\nTx Month: " + "7th";
			data += "\nGP ID: " + "G-ABC-0002";
			
			append(data);
		//}
		
		chwIdField = new TextField("CHW ID:", tbrMidlet.getCurrentUserId(), IdValidateUtil.MAX_CHWID_LENGTH, TextField.ANY);
		//gpIdField = new TextField("GP ID:", "", IdValidateUtil.MAX_GP_ID_LENGTH, TextField.ANY);

		dateField = new DateField("Date:", DateField.DATE);
		dateField.setDate(new Date());
		
		heightField = new TextField("Height", "", 5, TextField.DECIMAL);
		weightField = new TextField("Weight", "", 5, TextField.DECIMAL);
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(dateField);
		//append(gpIdField);
		append(chwIdField);
		append(weightField);
		append(heightField);

		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
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
		//future date check
		if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		else if(chwIdField.getString()==null || chwIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING,null);
			result = false;
		}
		else if(chwIdField.getString().length()<IdValidateUtil.MIN_CHWID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.CHW_ID_INVALID, null);
			result = false;
		}
		/*else if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		else if(gpIdField.getString().length()<IdValidateUtil.MIN_GP_ID_LENGTH) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_INVALID, null);
			result = false;
		}*/
		else if(weightField.getString()==null || weightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.WEIGHT_MISSING,null);
			result = false;
		}
		else if(heightField.getString()==null || heightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.HEIGHT_MISSING, null);
			result = false;
		}
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		request = "type=pdclivis";
    	request += "&id=" + patientId;
    	request += "&chwid=" + chwIdField.getString();
    	//request += "&gpid=" + gpIdField.getString();

    	request += "&dt=" + DateTimeUtil.getDateTime(dateField.getDate());
   		request += "&wt=" + weightField.getString();
    	request += "&ht=" + heightField.toString();

    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
		return request;
	}
}
