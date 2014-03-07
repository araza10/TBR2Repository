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
import org.irdresearch.tbreach.mobile.constants.UserType;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.GPSUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

public class DrugDispensationForm extends BaseTBReachForm implements
		CommandListener, ItemStateListener {
	
	private static final int permanent_item_index = 3;
	private static final String DRUG_DELIVERED_STRING = "yes";
	private static final String DRUG_NOT_DELIVERED_STRING = "no";

	private static String CURRENT_DRUG_DELIVERED_STRING = "";

	TextField fieldWorkerIdField;
	//ChoiceGroup monthGroup;
	DateField dateField;
	ChoiceGroup drugDeliveredGroup;
	//TextField gpsLatField;
	//TextField gpsLngField;
	TextField pillNumField;
	private TextField	pillNumTimeField;
	//private ChoiceGroup	pillNumTimeTypeChoice;
	private TextField	streptoNumField;
	private TextField	streptoNumTimeField;
	//private ChoiceGroup	streptoTimeTypeChoice;
	private TextField	syrupNumField;
	private TextField	syrupNumTimeField;
	//private ChoiceGroup	syrupTimeTypeChoice;
	ChoiceGroup reasonNotDeliveredGroup;
	ChoiceGroup whomeDeliveredGroup;
	ChoiceGroup	patientPurchasingPvtGroup;

	TextField otherReasonNotDeliveredField;
	
	
	Command cmdOK;
	Command cmdBack;
	//Command cmdGPS;

	private Hashtable queryData;
	private String patientId;
	
	public DrugDispensationForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		queryData = null;
		patientId = null;
		
		fieldWorkerIdField = null;
		//monthGroup = null;
		dateField = null;
		patientPurchasingPvtGroup = null;
		drugDeliveredGroup = null;
		//gpsLatField = null;
		//gpsLngField = null;
		pillNumTimeField = null;       
		//pillNumTimeTypeChoice = null;  
		streptoNumField = null;        
		streptoNumTimeField = null;       
		//streptoTimeTypeChoice = null;  
		syrupNumField = null;          
		syrupNumTimeField = null;         
		//syrupTimeTypeChoice = null;    
		reasonNotDeliveredGroup = null;
		whomeDeliveredGroup = null;
		pillNumField = null;
		
		cmdOK = null;
		cmdBack = null;
		//cmdGPS = null;
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
			data += "\nCategory: " + "New";
			data += "\nTx Phase: " + "Intensive";
			data += "\nRegimen Type: " + "Adult";
			data += "\nRegimen: " + "RHZE";
			data += "\nForm of Medication: " + "Syrup form";
			data += "\nFDC Dosage: " + "3";
			data += "\nStreptomycin: " + "750";
			data += "\nMR Number: " + "2111121212";
			data += "\nGP ID: " + "G-ABC-0001";
		//}
		append(data);

		String fwId = "";
		if(tbrMidlet.getCurrentRole() == UserType.USER_TYPE_CHW 
				|| tbrMidlet.getCurrentRole() == UserType.USER_TYPE_MONITOR){
			fwId = 	tbrMidlet.getCurrentUserId();
		}
		fieldWorkerIdField = new TextField("Field Worker ID:", fwId
					, (IdValidateUtil.MAX_ID_LENGTH), TextField.ANY);

		dateField = new DateField("Date:", DateField.DATE);
		dateField.setDate(new Date());
		//gpsLatField = new TextField("GPS Lat:", "", 15, TextField.DECIMAL);
		//gpsLngField = new TextField("GPS Long:","", 15, TextField.DECIMAL);
		
		/*monthGroup = new ChoiceGroup("Treatment Month", ChoiceGroup.EXCLUSIVE);
		for(int i=1; i<9; i++) {
			monthGroup.append(new Integer(i).toString(), null);
		}*/
		
		drugDeliveredGroup = new ChoiceGroup("Did you deliver the drugs?", ChoiceGroup.POPUP);
		drugDeliveredGroup.append("Yes", null);
		drugDeliveredGroup.append("No", null);
		
		CURRENT_DRUG_DELIVERED_STRING = DRUG_DELIVERED_STRING;
		
		reasonNotDeliveredGroup = new ChoiceGroup("Reason for not delivering drugs", ChoiceGroup.POPUP );
		reasonNotDeliveredGroup.append("Medicine brand", null);
		reasonNotDeliveredGroup.append("Other", null);
		
		otherReasonNotDeliveredField = new TextField("Specify Other", "", 15, TextField.ANY);

		patientPurchasingPvtGroup = new ChoiceGroup( "Is the patient purchasing drugs privately?" , ChoiceGroup.POPUP );
		patientPurchasingPvtGroup.append( "Yes" , null );
		patientPurchasingPvtGroup.append( "No" , null );

		whomeDeliveredGroup = new ChoiceGroup("Who did you deliver the drugs to?", ChoiceGroup.POPUP);
		whomeDeliveredGroup.append("Monitor", null);
		whomeDeliveredGroup.append("CHW", null);
		whomeDeliveredGroup.append("Patient", null);
		whomeDeliveredGroup.append("Patient's HH member", null);
		
		pillNumField = new TextField("No. of pills given", "", 3, TextField.NUMERIC);
		
		pillNumTimeField = new TextField("PILLS: \nTotal number of days of dispensed drugs", "", 2, TextField.NUMERIC);
		
		/*pillNumTimeTypeChoice = new ChoiceGroup("Type of time period", ChoiceGroup.POPUP);
		pillNumTimeTypeChoice.append( "Days" , null );
		pillNumTimeTypeChoice.append( "Weeks" , null );
		pillNumTimeTypeChoice.append( "Months" , null );*/
		
		streptoNumField = new TextField("No. of streptomycin vials given", "", 3, TextField.NUMERIC);
		
		streptoNumTimeField = new TextField("STREPTOMYCIN: \nTotal number of days of streptomycin dispensed", "", 2, TextField.NUMERIC);
		
//		streptoTimeTypeChoice = new ChoiceGroup("Type of time period", ChoiceGroup.POPUP);
//		streptoTimeTypeChoice.append( "Days" , null );
//		streptoTimeTypeChoice.append( "Weeks" , null );
//		streptoTimeTypeChoice.append( "Months" , null );

		syrupNumField = new TextField("No. of syrup bottles given", "", 3, TextField.NUMERIC);
		
		syrupNumTimeField = new TextField("SYRUP: \nTotal number of days of dispensed syrup", "", 2, TextField.NUMERIC);
		
		/*syrupTimeTypeChoice = new ChoiceGroup("Type of time period", ChoiceGroup.POPUP);
		syrupTimeTypeChoice.append( "Days" , null );
		syrupTimeTypeChoice.append( "Weeks" , null );
		syrupTimeTypeChoice.append( "Months" , null );*/
		
		cmdOK = new Command("Save", Command.OK, 1);
		cmdBack = new Command("Home", Command.BACK, 2);
		//cmdGPS = new Command("GPS", Command.OK, 3);
			
		//append(monthGroup);
		/*append(gpsLatField);
		append(gpsLngField);*/
		append(dateField);
		append(fieldWorkerIdField);
		append(drugDeliveredGroup);
		append(whomeDeliveredGroup);

		append(pillNumTimeField);
		append(pillNumField);
		
		append(streptoNumTimeField);
		append(streptoNumField);
		
		append(syrupNumTimeField);
		append(syrupNumField);
		
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		
		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		
		setCommandListener(this);
		setItemStateListener(this);
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
	
	public void itemStateChanged(Item i) {
		if(i==reasonNotDeliveredGroup) {
			if(reasonNotDeliveredGroup.getString(reasonNotDeliveredGroup.getSelectedIndex()).toLowerCase().equals("other")){
				otherReasonNotDeliveredField.setString( "" );
				otherReasonNotDeliveredField.setConstraints( TextField.ANY );
			}
			else {
				otherReasonNotDeliveredField.setString( "" );
				otherReasonNotDeliveredField.setConstraints( TextField.UNEDITABLE );
			}
		}
		else if(i == drugDeliveredGroup){
			String drugDel = drugDeliveredGroup.getString(drugDeliveredGroup.getSelectedIndex());
			//if string val is changed in the process, this is just to avoid resetting of controls if user selects 
			//same option accidently or intentionally
			if(!drugDel.toLowerCase().equals(CURRENT_DRUG_DELIVERED_STRING.toLowerCase())){
				while ( size()-1 > permanent_item_index) {
					delete( size()-1 );
				}
				if(drugDel.toLowerCase().equals( DRUG_DELIVERED_STRING.toLowerCase() )){
					whomeDeliveredGroup.setSelectedIndex( 0 , true );
					append(whomeDeliveredGroup);

					pillNumField.setString( "" );
					pillNumTimeField.setString( "" );
					
					append(pillNumTimeField);
					append(pillNumField);
					
					streptoNumField.setString( "" );
					streptoNumTimeField.setString( "" );
					
					append(streptoNumTimeField);
					append(streptoNumField);
					
					syrupNumField.setString( "" );
					syrupNumTimeField.setString( "" );
					
					append(syrupNumTimeField);
					append(syrupNumField);
					
					
					
					CURRENT_DRUG_DELIVERED_STRING = DRUG_DELIVERED_STRING;
				}
				else if(drugDel.toLowerCase().equals( DRUG_NOT_DELIVERED_STRING.toLowerCase() )){
					patientPurchasingPvtGroup.setSelectedIndex( 0 , true );
					reasonNotDeliveredGroup.setSelectedIndex( 0 , true );
					otherReasonNotDeliveredField.setString( "" );
					otherReasonNotDeliveredField.setConstraints( TextField.UNEDITABLE );
					append( reasonNotDeliveredGroup );
					append( otherReasonNotDeliveredField );
					append( patientPurchasingPvtGroup );
					
					CURRENT_DRUG_DELIVERED_STRING = DRUG_NOT_DELIVERED_STRING;
				}
			}
		}
	}

	public boolean validate() {
		boolean result = true;
		
		if(fieldWorkerIdField.getString()==null || fieldWorkerIdField.getString().length()==0) {
			tbrMidlet.showAlert(ErrMsg.CHW_ID_MISSING,null);
			result = false;
		}
		else if(fieldWorkerIdField.getString().length()<IdValidateUtil.MIN_ID_LENGTH){
			tbrMidlet.showAlert(ErrMsg.FW_ID_INVALID, null);
			result = false;
		}
		String pillNumTimeString = pillNumTimeField.getString().trim();
		String streptoNumTimeString = streptoNumTimeField.getString().trim();
		String syrupNumTimeString = syrupNumTimeField.getString().trim();
		if(drugDeliveredGroup.getString( drugDeliveredGroup.getSelectedIndex() ).toLowerCase().equals( "yes" )){
			if(pillNumField.getString().trim().length()==0 
					|| pillNumTimeString.length()==0){
				tbrMidlet.showAlert(ErrMsg.PILL_DETAILS_MISSING,null);
				result = false;
			}
			else if((pillNumField.getString().equals( "0" ) 
					&& !pillNumTimeString.equals( "0" ))
						||(!pillNumField.getString().equals( "0" ) 
						&& pillNumTimeString.equals( "0" ))){
				tbrMidlet.showAlert(ErrMsg.PILL_DETAILS_INVALID,null);
				result = false;
			}
			else if(Integer.parseInt( pillNumTimeString) > IdValidateUtil.MAX_PILL_DAYS){
					tbrMidlet.showAlert(ErrMsg.PILL_NUM_TIME_DAYS_INVALID,null);
					result = false;
			}
			/*else if(pillNumTimeTypeChoice.getString(pillNumTimeTypeChoice.getSelectedIndex())
					.toLowerCase().equals( "weeks" )
					&& Integer.parseInt( pillNumTimeString) > IdValidateUtil.MAX_PILL_WEEKS){
					tbrMidlet.showAlert(ErrMsg.PILL_NUM_TIME_WEEKS_INVALID,null);
					result = false;
			}
			else if(pillNumTimeTypeChoice.getString(pillNumTimeTypeChoice.getSelectedIndex())
					.toLowerCase().equals( "months" )
					&& Integer.parseInt( pillNumTimeString) > IdValidateUtil.MAX_PILL_MONTHS){
					tbrMidlet.showAlert(ErrMsg.PILL_NUM_TIME_MONTHS_INVALID,null);
					result = false;
			}*/
			
			//for strepto
			else if(streptoNumField.getString().trim().length()==0 
					|| streptoNumTimeString.length()==0){
				tbrMidlet.showAlert(ErrMsg.STREPTO_DETAILS_MISSING,null);
				result = false;
			}
			else if((streptoNumField.getString().equals( "0" ) 
					&& !streptoNumTimeString.equals( "0" ))
						||(!streptoNumField.getString().equals( "0" ) 
						&& streptoNumTimeString.equals( "0" ))){
				tbrMidlet.showAlert(ErrMsg.STREPTO_DETAILS_INVALID,null);
				result = false;
			}
			else if(Integer.parseInt( streptoNumTimeString) > IdValidateUtil.MAX_STREPTO_DAYS){
					tbrMidlet.showAlert(ErrMsg.STREPTO_NUM_TIME_DAYS_INVALID,null);
					result = false;
			}
			/*else if(streptoTimeTypeChoice.getString(streptoTimeTypeChoice.getSelectedIndex())
					.toLowerCase().equals( "weeks" )
					&& Integer.parseInt( streptoNumTimeString) > IdValidateUtil.MAX_STREPTO_WEEKS){
					tbrMidlet.showAlert(ErrMsg.STREPTO_NUM_TIME_WEEKS_INVALID,null);
					result = false;
			}
			else if(streptoTimeTypeChoice.getString(streptoTimeTypeChoice.getSelectedIndex())
					.toLowerCase().equals( "months" )
					&& Integer.parseInt( streptoNumTimeString) > IdValidateUtil.MAX_STREPTO_MONTHS){
					tbrMidlet.showAlert(ErrMsg.STREPTO_NUM_TIME_MONTHS_INVALID,null);
					result = false;
			}*/
			//syrup bottles
			else if(syrupNumField.getString().trim().length()==0 
					|| syrupNumTimeString.length()==0){
				tbrMidlet.showAlert(ErrMsg.SYRUP_DETAILS_MISSING,null);
				result = false;
			}
			else if((syrupNumField.getString().equals( "0" ) 
					&& !syrupNumTimeString.equals( "0" ))
						||(!syrupNumField.getString().equals( "0" ) 
						&& syrupNumTimeString.equals( "0" ))){
				tbrMidlet.showAlert(ErrMsg.SYRUP_DETAILS_INVALID,null);
				result = false;
			}
			else if(Integer.parseInt( syrupNumTimeString) > IdValidateUtil.MAX_SYRUP_DAYS){
					tbrMidlet.showAlert(ErrMsg.SYRUP_NUM_TIME_DAYS_INVALID,null);
					result = false;
			}
			/*else if(syrupTimeTypeChoice.getString(syrupTimeTypeChoice.getSelectedIndex())
					.toLowerCase().equals( "weeks" )
					&& Integer.parseInt( syrupNumTimeString) > IdValidateUtil.MAX_SYRUP_WEEKS){
					tbrMidlet.showAlert(ErrMsg.SYRUP_NUM_TIME_WEEKS_INVALID,null);
					result = false;
			}
			else if(syrupTimeTypeChoice.getString(syrupTimeTypeChoice.getSelectedIndex())
					.toLowerCase().equals( "months" )
					&& Integer.parseInt( syrupNumTimeString) > IdValidateUtil.MAX_SYRUP_MONTHS){
					tbrMidlet.showAlert(ErrMsg.SYRUP_NUM_TIME_MONTHS_INVALID,null);
					result = false;
			}*/
		}
		else{
			if(reasonNotDeliveredGroup.getString(reasonNotDeliveredGroup.getSelectedIndex()).toLowerCase().equals( "other" )
					&& otherReasonNotDeliveredField.getString().trim().length()==0) {
				tbrMidlet.showAlert(ErrMsg.OTHER_REASON_DRUG_NOT_DELIVERED_MISSING, null);
				result = false;
			}
		}
		return result;
	}
	
	public String createRequestPayload() {
		String request = null;
		int notDeliveredChoice = reasonNotDeliveredGroup.getSelectedIndex();
		request = "type=drdf";
    	request += "&id=" + patientId;
    	request += "&chwid=" + fieldWorkerIdField.getString();
    	request += "&ddeliv=" + drugDeliveredGroup.getString(drugDeliveredGroup.getSelectedIndex());
    	request += "&rsn=" + reasonNotDeliveredGroup.getString(notDeliveredChoice);
    	request += "&otrrsn=" + otherReasonNotDeliveredField.getString();
    	request += "&priv=" + patientPurchasingPvtGroup.getString(patientPurchasingPvtGroup.getSelectedIndex());
    	request += "&who=" + whomeDeliveredGroup.getString(whomeDeliveredGroup.getSelectedIndex());
    	request += "&pillnum=" + pillNumField.getString();
    	request += "&pilltime=" + pillNumTimeField.getString();
    	request += "&strepnum=" + streptoNumField.getString();
    	request += "&streptime=" + streptoNumTimeField.getString();
    	request += "&syrnum=" + syrupNumField.getString();
    	request += "&syrtime=" + syrupNumTimeField.getString();
    
    	request += "&un=" + tbrMidlet.getCurrentUser();

    	request += "&dt=" + DateTimeUtil.getDateTime(dateField.getDate());
    	request += "&sd=" + startDate;
    	request += "&st=" + startTime;
    	request += "&et=" + endTime;
    	request += "&ed=" + DateTimeUtil.getDateTime(dateField.getDate());
		return request;
	}
}
