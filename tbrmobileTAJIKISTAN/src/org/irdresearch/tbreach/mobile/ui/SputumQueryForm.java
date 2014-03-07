package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;

public class SputumQueryForm extends BaseTBReachForm implements CommandListener {

	Command cmdOK;
	Command cmdBack;
	
	TextField midField;
	DateField dateField;
	private String formType;
	
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public SputumQueryForm(String title, TBReachMainMIDlet tbrMidlet, String formType) {
		super(title, tbrMidlet);
		this.formType = formType;
		
		cmdOK = null;
		cmdBack = null;
		
		midField = null;
		dateField = null;
	}
	
	public void commandAction(Command c, Displayable d) {

		if (c == cmdOK) {
			if (validate()) {
				String request = createRequestPayload();
				Hashtable model = null;//tbrMidlet.sendToServer(request);

				if (model != null) {
					
					if((String)model.get("status")!=null && ((String)model.get("status")).equals("error")) {
						tbrMidlet.showAlert((String)model.get("msg"), null);
					}
					
					else {
						tbrMidlet.sf.setQueryData(model);
						tbrMidlet.sf.setPrevDisplayable(tbrMidlet.mainList);
						tbrMidlet.sf.setPatientId(null);
						cleanUp();
						tbrMidlet.startTBReachForm(tbrMidlet.sf);
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
	
	private String createRequestPayload() {
    	
    	String request = null;
    	String monitorId = midField.getString();
    	
    	request = "type=fq&qtype=" + formType;
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&mid=" + monitorId;
    	request += "&date=" + DateTimeUtil.getDate(dateField.getDate());
    	
    	return request;
    }
	
	
	public void init() {
		
		midField = new TextField("Monitor ID", "", 12, TextField.ANY);
		dateField = new DateField("Date", DateField.DATE);
		dateField.setDate(new Date());
		cmdOK = new Command("Submit", Command.OK, 0);
		cmdBack = new Command("Home", Command.BACK, 1);
		
		
		append(midField);
		append(dateField);
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		setCommandListener(this);
	}
	
	public boolean validate() {
		boolean result = true;
		
		if(midField.getString()==null || midField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.MONITOR_ID_MISSING,null);
			result = false;
		}
		
		//future date check
		else if(DateTimeUtil.isDateInFuture(dateField.getDate())) {
			
			tbrMidlet.showAlert(ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		
		
		
		
		return result;
	}
	
	public void cleanUp() {
		deleteAll();
		removeCommand(cmdOK);
		removeCommand(cmdBack);
	}
	
}
