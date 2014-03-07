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

public class DOTSNumberAssignmentForm extends BaseTBReachForm implements CommandListener {

	public TextField mrField;
	public TextField dotsField;
	public TextField dotsField2;
	public TextField midField;
	public DateField dateField;
	
	Command cmdOK;
	Command cmdBack;
	
	public DOTSNumberAssignmentForm(String title, TBReachMainMIDlet tbrMidlet) {
			super(title, tbrMidlet);
			mrField = null;
			dotsField = null;
			dotsField2 = null;
			midField = null;
			dateField = null;
			
			cmdOK = null;
			cmdBack = null;
			
	}
	
	public void init() {
		mrField = new TextField("Mr Number:", "", 11, TextField.NUMERIC);
		dotsField = new TextField("DOTS Number:", "", 6, TextField.NUMERIC);
		dotsField2 = new TextField("Confirm DOTS Number:","", 6, TextField.NUMERIC);
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		midField = new TextField("Monitor ID:", tbrMidlet.getCurrentUserId(), 12, TextField.ANY);
		
		cmdOK = new Command("Save", Command.OK, 0);
		cmdBack = new Command("Home", Command.BACK, 1);

		append(dateField);
		append(midField);
		append(mrField);
		append(dotsField);
		append(dotsField2);
		
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
	}
	
	private boolean validate() {
		boolean result = true;
		String dotsNum = dotsField.getString();
		int check = -1;
		
		if (midField.getString()==null || midField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING, null);
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
		
		else if(dotsField.getString()==null || dotsField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.DOTS_MISSING,null);
			result = false;
		}
		
		else if(dotsField.getString().length() != 6) {
			tbrMidlet.showAlert(ErrMsg.BAD_DOTS_LENGTH,null);
			result = false;
		}
		
		else if(!dotsField.getString().equals(dotsField2.getString())) {
			tbrMidlet.showAlert(ErrMsg.DOTS_MISMATCH,null);
			result = false;
		}
		
		else if(!dotsNum.substring(4,6).equals("11") && !dotsNum.substring(4,6).equals("12")) {
			tbrMidlet.showAlert(ErrMsg.BAD_DOTS_YEAR,null);
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
				//Hashtable model = tbrMidlet.sendToServer(request);
				
				//if(model!=null) {
					String status = XmlStrings.SUCCESS;//(String)model.get("status");
					
					if(status.equals(XmlStrings.SUCCESS)) {
						System.out.println("success");
						tbrMidlet.showAlert(SuccessMsg.SAVE_SUCCESS,null);
						
					}
					
					/*else if(status.equals(XmlStrings.ERROR)) {
						System.out.println((String)model.get("msg"));
						tbrMidlet.showAlert("ERROR: " + (String)model.get("msg"),null);
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
		
		String dotsNum = dotsField.getString();
		dotsNum = dotsNum.substring(0, 4) + "/" + dotsNum.substring(4,6);
		String request = "";
		request = "type=dnaf";
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&mr=" + mrField.getString();
    	request += "&mid=" +  midField.getString();
    	request += "&dots=" + dotsNum;
    	
    	
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
		return request;
	}

}
