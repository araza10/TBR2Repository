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
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class ClinicalVisitForm extends BaseTBReachForm implements CommandListener , ItemStateListener{
	
	DateField			dateField;
	TextField			monthField;
	TextField			chwIdField;
	TextField			gpIdField;
	TextField			heightField;
	TextField			weightField;
	
	StringItem			recomendedRegimenString;

	ChoiceGroup			regimenChangeChoice;
	ChoiceGroup			phaseChoice;
	ChoiceGroup			regimenChoice;
	ChoiceGroup			tabletsChoice;
	ChoiceGroup			streptoChoice;

	Command				cmdOK;
	Command				cmdBack;

	private Hashtable	queryData;
	private String		patientId;
	private String		cat;
	private String 		txStartDate;
	private int			month;
	private String 		monthStr;
	
	public ClinicalVisitForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		queryData = null;
		patientId = null;

		chwIdField = null;
		gpIdField = null;
		dateField = null;
		monthField = null;
		weightField = null;
		heightField = null;
		regimenChangeChoice = null;
		recomendedRegimenString = null;

		phaseChoice = null;
		regimenChoice = null;
		tabletsChoice = null;
		streptoChoice = null;

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
	
		//cat = "CAT 1";
		//if(queryData!=null) {
			/*monthStr = (String)queryData.get("tx_month");
			month = Integer.parseInt(monthStr);*/
			cat = "CAT 1";
			//txStartDate = (String)queryData.get("tx_date");
			data += "Patient ID: " + patientId;
			data += "\nMR Number: " + "20110001234";
			data += "\nGP ID: " + "G-TEST-99";
			data += "\nPatient Type: " + "NEW";
			data += "\nCategory: " + "CAT 1";
			data += "\nTx Start Date: " + "08/10/2011";
			data += "\nTx Phase: " + "INTENSIVE";
			//data += "\nTx Month: " + month;
			data += "\nCurrent Regimen: " + "RHE";
			data += "\nFDCs Number: " + "2";
			data += "\nAmount of S: " + "";
			data += "\nBaseline CXR: " + "SUGGESTIVE OF TB";
			data += "\nBaseline GXP: ";
			data += "\nBaseline GXP Resistance: ";
			data += "\nBaseline Sputum: " + "1+ 1+";
			data += "\n2nd/3rd Month Sputum: " + "NEGATIVE, NEGATIVE";
			data += "\n5th Month Sputum: ";
			data += "\n7th Month Sputum: ";
			append(data);

			
			
		//}
		
		chwIdField = new TextField("CHW ID:", tbrMidlet.getCurrentUserId(), IdValidateUtil.MAX_CHWID_LENGTH, TextField.ANY);
		gpIdField = new TextField("GP ID:", "" , IdValidateUtil.MAX_GP_ID_LENGTH, TextField.ANY);

		dateField = new DateField("Date:", DateField.DATE);
		dateField.setDate(new Date());
		
		int testMonth = 3;//DateTimeUtil.calculateMonthOfTreatment(txStartDate, dateField.getDate());
		
		monthField = new TextField("Month:",new Integer(testMonth).toString(),2,TextField.UNEDITABLE);
		
		
		heightField = new TextField("Height", "", 5, TextField.DECIMAL);
		weightField = new TextField("Weight", "", 5, TextField.DECIMAL);

		regimenChangeChoice = new ChoiceGroup( "Did the patient's treatment regimen change?" , ChoiceGroup.POPUP );
		regimenChangeChoice.append( "Yes" , null );
		regimenChangeChoice.append( "No" , null );
		regimenChangeChoice.setSelectedIndex( 0 , true );

		phaseChoice = new ChoiceGroup( "What is the patient's current treatment phase?" , ChoiceGroup.POPUP );
		phaseChoice.append( "Intensive" , null );
		phaseChoice.append( "Continuous" , null );
		
		regimenChoice = new ChoiceGroup( "Current regimen" , ChoiceGroup.POPUP );
		regimenChoice.append( "RHZE" , null );
		regimenChoice.append( "RHZES" , null );
		regimenChoice.append( "EH" , null );
		regimenChoice.append( "RHE" , null );
		regimenChoice.append( "RH" , null );
		
		recomendedRegimenString = new StringItem( "Recommended:" , "" );

		tabletsChoice = new ChoiceGroup( "No. of tablets" , ChoiceGroup.POPUP );
		tabletsChoice.append( "1" , null );
		tabletsChoice.append( "1.5" , null );
		tabletsChoice.append( "2" , null );
		tabletsChoice.append( "3" , null );
		tabletsChoice.append( "4" , null );
		tabletsChoice.append( "5" , null );

		streptoChoice = new ChoiceGroup( "Streptomycin (mg)" , ChoiceGroup.POPUP );
		streptoChoice.append( "250" , null );
		streptoChoice.append( "500" , null );
		streptoChoice.append( "750" , null );

		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		
		append(dateField);
		append(monthField);
		append(gpIdField);
		append(chwIdField);
		append(weightField);
		append(heightField);
		append(regimenChangeChoice);
		append(phaseChoice);
		append(regimenChoice);
		append( recomendedRegimenString );

		append(tabletsChoice);
		
		updateRegimenItem();
		updateStreptoItem();
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener( this );
	}

	public void commandAction(Command c, Displayable d) {
		if(c==cmdOK) {
			if(validate()) {
				endTime = DateTimeUtil.getTime();
				String request = createRequestPayload();
				System.out.println(request);
				/*Hashtable model = tbrMidlet.sendToServer(request);
				
				if(model!=null) {*/
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
		else if(gpIdField.getString()==null || gpIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_MISSING, null);
			result = false;
		}
		else if(gpIdField.getString().length()<IdValidateUtil.MIN_GP_ID_LENGTH) {
			tbrMidlet.showAlert(ErrMsg.GP_ID_INVALID, null);
			result = false;
		}
		else if(weightField.getString()==null || weightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.WEIGHT_MISSING,null);
			result = false;
		}
		else if(heightField.getString()==null || heightField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.HEIGHT_MISSING, null);
			result = false;
		}
		
		if(result) {
			int month = Integer.parseInt(monthField.getString());
			
			if(month == DateTimeUtil.BAD_ENTERED_DATE) {
				tbrMidlet.showAlert(ErrMsg.ENTERED_DATE_BEFORE_TX_START, null);
				result = false;
			}
			
			else if(month == DateTimeUtil.DATE_CALC_ERROR || month < 0) {
				tbrMidlet.showAlert(ErrMsg.MONTH_CALC_ERROR, null);
				result = false;
			}
		}
		
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		
		request = "type=clivis";
    	request += "&id=" + patientId;
    	request += "&chwid=" + chwIdField.getString();
    	request += "&gpid=" + gpIdField.getString();

    	request += "&dt=" + DateTimeUtil.getDateTime(dateField.getDate());
   		request += "&wt=" + weightField.getString();
    	request += "&ht=" + heightField.getString();
    	
    	request += "&mnt=" + monthField.getString();
    	request += "&regchg=" + regimenChangeChoice.getString(regimenChangeChoice.getSelectedIndex());
    	request += "&phs=" + phaseChoice.getString(phaseChoice.getSelectedIndex());
    	//request += "&phs=" + phaseChoice.getString(phaseChoice.getSelectedIndex());
    	request += "&reg=" + regimenChoice.getString(regimenChoice.getSelectedIndex());
    	request += "&fdc=" + tabletsChoice.getString(tabletsChoice.getSelectedIndex());
    	
    	if(regimenChoice.getString(regimenChoice.getSelectedIndex()).equals("RHZES"))
    			request += "&strp=" + streptoChoice.getString(streptoChoice.getSelectedIndex());
  
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		
    	return request;
	}

	private void updateRegimenItem(){
		//if category 1 then just give RHZE and remove pregnancy question and strepto question from Form
		if(cat.toUpperCase().equals("CAT I")){

			regimenChoice.deleteAll();
			
			if(phaseChoice.getString(phaseChoice.getSelectedIndex()).toLowerCase().startsWith("intensive")){
				recomendedRegimenString.setText( "RHZE Fixed Dose Combination Tablets" );
			}
			else{
				recomendedRegimenString.setText( "EH Fixed Dose Combination Tablets" );
			}
			
			if(phaseChoice.getString(phaseChoice.getSelectedIndex()).toLowerCase().startsWith("intensive")){
				regimenChoice.append("RHZE", null);
				regimenChoice.setSelectedIndex(0, true);
			}
			else{
				regimenChoice.append("RH", null);
				regimenChoice.append("EH", null);
				regimenChoice.append("RHE", null);
				regimenChoice.setSelectedIndex(1, true);
			}
			/*if(get(PREGNANCY_INDEX) != pregnantString){
				if(get(PREGNANCY_INDEX) == pregnantGroup) delete(PREGNANCY_INDEX);
				
				insert(PREGNANCY_INDEX, pregnantString);
			}*/
			
		}else if(cat.toUpperCase().equals("CAT II")){
			regimenChoice.deleteAll();
			
			if(phaseChoice.getString(phaseChoice.getSelectedIndex()).toLowerCase().startsWith("intensive")){
				recomendedRegimenString.setText( "RHZES Fixed Dose Combination Tablets + Injections" );
			}
			else{
				recomendedRegimenString.setText( "RHE Fixed Dose Combination Tablets" );
			}
			
			if(phaseChoice.getString(phaseChoice.getSelectedIndex()).toLowerCase().startsWith("intensive")){
				regimenChoice.append( "RHZE" , null );
				regimenChoice.append("RHZES", null);
				regimenChoice.setSelectedIndex(1, true);
			}
			else{
				regimenChoice.append("RHE", null);
				regimenChoice.setSelectedIndex(0, true);
			}
		}
	}
	
	private void updateStreptoItem(){
		if(regimenChoice.getString( regimenChoice.getSelectedIndex() ).toLowerCase().indexOf( 's' ) != -1){
			if(get(size()-1) != streptoChoice){
				append( streptoChoice );
			}
		}
		else{
			if(get(size()-1) == streptoChoice){
				delete( size()-1 );
			}
		}
	}
	
	public void itemStateChanged(Item i) {
		if(i == regimenChangeChoice){
			if(regimenChangeChoice.getString( regimenChangeChoice.getSelectedIndex() ).toLowerCase().equals("yes")){
				if(get(size()-1) == regimenChangeChoice){
					append(phaseChoice);
					regimenChoice.setSelectedIndex( 0 , true );//so that any strepto doesnt get selected without being added to form
					append(regimenChoice);
					append( recomendedRegimenString );
					append(tabletsChoice);
				}
				
				updateRegimenItem();
				updateStreptoItem();
			}
			else {
				while (get(size()-1) != regimenChangeChoice) {
					delete( size()-1 );
				}
			}
		}
		else if(i == regimenChoice){
			updateStreptoItem();
		}
		else if(i == phaseChoice){
			updateRegimenItem();
			updateStreptoItem();
		}
		
		else if(i==dateField) {
			int testMonth = DateTimeUtil.calculateMonthOfTreatment(txStartDate, dateField.getDate());
			monthField.setString(new Integer(testMonth).toString());
		}
	}
}
