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

public class MRNumberForm extends BaseTBReachForm implements CommandListener {

	public TextField idField;
	public TextField mrField;
	public TextField mrField2;
	public TextField midField;
	public DateField dateField;
	
	Command cmdOK;
	Command cmdBack;
	
	public MRNumberForm(String title, TBReachMainMIDlet tbrMidlet) {
			super(title, tbrMidlet);
			idField = null;
			mrField = null;
			mrField2 = null;
			midField = null;
			dateField = null;
			
			cmdOK = null;
			cmdBack = null;
			
	}
	
	public void init() {
		idField = new TextField("Patient ID:", "", 10, TextField.NUMERIC);
		mrField = new TextField("MR Number:", "", 11, TextField.NUMERIC);
		mrField2 = new TextField("Confirm MR:","", 11, TextField.NUMERIC);
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		midField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		
		cmdOK = new Command("Save", Command.OK, 0);
		cmdBack = new Command("Home", Command.BACK, 1);

		append(dateField);
		append(midField);
		append(idField);
		append(mrField);
		append(mrField2);
		
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
	}
	
	private boolean validate() {
		boolean result = true;
		int check = -1;
		
		if (midField.getString()==null || midField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING, null);
			result = false;
		}
		
		else if(idField.getString()==null || idField.getString().length()==0) {
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
		
		else if(mrField.getString()==null || mrField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MR_MISSING,null);
			result = false;
		}
		
		else if(mrField.getString().length()!=11) {
			tbrMidlet.showAlert(ErrMsg.BAD_MR_LENGTH,null);
			result = false;
		}
		
		else if(!mrField.getString().equals(mrField2.getString())) {
			tbrMidlet.showAlert(ErrMsg.MR_MISMATCH,null);
			result = false;
		}
		
	
		
		return result;
	}
	
	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		
		if(c==cmdOK) {
			if(validate()) {
				endTime = DateTimeUtil.getTime();
				String request = createRequestPayload();
				System.out.println(request);
				Hashtable model = tbrMidlet.sendToServer(request);
				
				if(model!=null) {
					String status = (String)model.get("status");
					
					if(status.equals(XmlStrings.SUCCESS)) {
						System.out.println("success");
						tbrMidlet.showAlert(SuccessMsg.SAVE_SUCCESS,null);
						
					}
					
					else if(status.equals(XmlStrings.ERROR)) {
						System.out.println((String)model.get("msg"));
						tbrMidlet.showAlert("ERROR: " + (String)model.get("msg"),null);
					}
				}
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
		String request = "";
		request = "type=maf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + idField.getString();
    	request += "&mid=" +   midField.getString();
    	request += "&mr=" + mrField.getString();
    	
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}

}
