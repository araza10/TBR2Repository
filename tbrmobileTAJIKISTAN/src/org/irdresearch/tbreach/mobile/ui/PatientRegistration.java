package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.model.FormType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

import java.util.Hashtable;

public class PatientRegistration extends BaseTBReachForm implements CommandListener, ItemCommandListener, ItemStateListener{
	
	TextField participantID;
	TextField participantIDConfirm;
	DateField startTreatment;
	TextField healthWorkerID;
	TextField phoneNumber1;
	TextField phoneNumber1Owner;
	TextField phoneNumber2;
	TextField phoneNumber2Owner;
	TextField addressStreet;
	TextField addressDistrict;
	TextField addressHouse;
	TextField addressFlat;
	TextField firstNameField;
	TextField lastNameField;
	DateField dobField;
	ChoiceGroup gender;
	ChoiceGroup relationshipFamilyVolunteer;
	ChoiceGroup maritalStatus;
	ChoiceGroup education;
	ChoiceGroup incomeFamilyMember;
	Command		cmdOK;
	Command		cmdBack;
	Item[]		formItems;
	TextField relationshipOther;
	private 	Hashtable queryData;
	
	
	

	public PatientRegistration( String title , TBReachMainMIDlet tbrMidlet ) {
		super( title , tbrMidlet );
		//participant ID should be displayed prior to opening this form, not here!
		participantID = null;
		participantIDConfirm = null;
		
		startTreatment = null;
		healthWorkerID = null;
		phoneNumber1 = null;
		phoneNumber1Owner = null;
		phoneNumber2 = null;
		phoneNumber2Owner = null;
		addressStreet = null;
		addressDistrict = null;
		addressHouse = null;
		addressFlat = null;
		firstNameField = null;
		lastNameField = null;
		dobField = null;
		gender = null;
		relationshipFamilyVolunteer = null;
		maritalStatus = null;
		education = null;
		incomeFamilyMember = null;
		cmdOK = null;
		cmdBack = null;
		relationshipOther = null;
		
	}
	
	public Hashtable getQueryData ()
	{
		return queryData;
	}

	public void setQueryData (Hashtable queryData)
	{
		this.queryData = queryData;
	}
	
	public void init()
	{
		
		
		
		healthWorkerID = new TextField( "Health Worker ID" , tbrMidlet.getCurrentUserId() , 10 , TextField.UNEDITABLE);
		participantID = new TextField( "Participant ID" , "" , 8 , TextField.NUMERIC );
		participantIDConfirm = new TextField( "Reenter Participant ID" , "" , 8 , TextField.NUMERIC );
		startTreatment = new DateField( "Date of Start of treatment" ,  DateField.DATE);
		
		phoneNumber1 = new TextField( "Phone Number 1" , "" , 25  , TextField.NUMERIC );
		phoneNumber1Owner = new TextField( "Phone Number 1 Owner" , "" , 25  , TextField.ANY );
		phoneNumber2 = new TextField( "Phone Number 2" , "" , 25  , TextField.NUMERIC );
		phoneNumber2Owner = new TextField( "Phone Number 2 Owner" , "" , 25  , TextField.ANY );
		addressStreet = new TextField( "Address Street Name" , "" , 25  , TextField.ANY );
		addressDistrict = new TextField( "Address Microdistrict Number" , "" , 25 , TextField.ANY );
		addressHouse = new TextField( "Address House Number" , "" , 20 , TextField.ANY );
		addressFlat = new TextField( "Address Flat Number" , "" , 20 , TextField.ANY );
		relationshipFamilyVolunteer = new ChoiceGroup( "Relationship to Family Volunteer" , ChoiceGroup.POPUP );
		relationshipFamilyVolunteer.append( "Child" , null );
		relationshipFamilyVolunteer.append( "Parent" , null );
		relationshipFamilyVolunteer.append( "Sibling" , null );
		relationshipFamilyVolunteer.append( "Spouse" , null );
		relationshipFamilyVolunteer.append( "Grandparent" , null );
		relationshipFamilyVolunteer.append( "Grandchild" , null );
		relationshipFamilyVolunteer.append( "Other" , null );
		
		relationshipOther = new TextField( "Relationship Other" , "" , 25 , TextField.ANY);
		
		maritalStatus = new ChoiceGroup( "Maritul Status" , ChoiceGroup.POPUP );
		maritalStatus.append( "Single" ,null );
		maritalStatus.append( "Married" ,null );
		maritalStatus.append( "Divorced" ,null );
		maritalStatus.append( "Widow" ,null );
		maritalStatus.append( "Widower" ,null );
		maritalStatus.append( "Refused" ,null );
		education = new ChoiceGroup( "Education" , ChoiceGroup.POPUP );
		education.append( "Secondary" ,null );
		education.append( "College" ,null );
		education.append( "University" ,null );
		education.append( "None" ,null );
		incomeFamilyMember = new ChoiceGroup( "Income per Family Member" , ChoiceGroup.POPUP );
		incomeFamilyMember.append( "Up to 200 Somoni" ,null );
		incomeFamilyMember.append( "Up to 300 Somoni" ,null );
		incomeFamilyMember.append( "Up to 400 Somoni" ,null );
		incomeFamilyMember.append( "More than 400 Somoni" ,null );
		incomeFamilyMember.append( "Refused" ,null );
		
		cmdOK = new Command( "Сабт" , Command.OK , 1 );
		cmdBack = new Command( "Ба аввал" , Command.BACK , 2 );

		Item items[] = {healthWorkerID , participantID, participantIDConfirm, startTreatment , phoneNumber1 , phoneNumber1Owner ,
				phoneNumber2 , phoneNumber2Owner , addressStreet ,
				addressDistrict, addressHouse, addressFlat, relationshipFamilyVolunteer, relationshipOther,  maritalStatus, education, incomeFamilyMember  };
		formItems = items;
		addCommand( cmdOK );
		addCommand( cmdBack );

		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		initialShow();
		setCommandListener( this );
		setItemStateListener( this );
	}
	
	private void show(Item item) {
		int i = indexOf( item );
		delete( i );
		insert( i , item );
	}
	
	private int indexOf(Item item) {
		for (int i = 0; i < formItems.length; i++)
			if (formItems[i] == item)
				return i;
		return -1;
	}


	private void hide(Item item) {
		int i = indexOf( item );
		delete( i );
		StringItem tmp = new StringItem( "" , "" );
		insert( i , tmp );
	}

	public void itemStateChanged(Item i) {
		int indexx = 6;
		
		
		if(i == relationshipFamilyVolunteer)
		{
			if(relationshipFamilyVolunteer.getSelectedIndex() == indexx)
			{
				show(relationshipOther);
			}
			else
			{
				hide(relationshipOther);
			}
		}
	}
	

	private void initialShow() {
		
		for (int i = 0; i < formItems.length; i++)
			append( formItems[i] );
		
		hide( relationshipOther );
		
	}
	
	private boolean validate()
	{
		boolean result = true;
		
		if(participantID.getString().equals( "" ) || participantIDConfirm.getString().equals( "" ))
		{
			tbrMidlet.showAlert ("Please fill in the participant id", null);
			result = false;
		}
		
		if (!participantIDConfirm.getString ().equals (participantID.getString ())){
			tbrMidlet.showAlert ("IDs do not match", null);
			result = false;
		}
		
		if(startTreatment.getDate() == null)
		{
			tbrMidlet.showAlert ("Please fill in Date of Start of Treatment", null);
			result = false;
		}
		return result;
	}

	public void commandAction(Command c , Item arg1) {
		
		
	}

	public void commandAction(Command c , Displayable d) {
		
		if (c == cmdOK) {
			if(validate()){
			
			endTime = DateTimeUtil.getTime();
			String request = createRequestPayload();
			System.out.println( request );
			Hashtable model = tbrMidlet.sendToServer( request );
			
			if (model != null) {
				String status = (String) model.get( "status" );
				String uniqueID = (String) model.get( "uniqueID" );

				if (status.equals( XmlStrings.SUCCESS )) {
					System.out.println( "success" );
					
						deleteAll();
						removeCommand( cmdOK );
						removeCommand( cmdBack );
						init();
						tbrMidlet.showAlert("Successfully Saved!" , null );


				}
				else if (status.equals( XmlStrings.ERROR )) {
					System.out.println( (String) model.get( "msg" ) );
					tbrMidlet.showAlert(
							"ERROR: " + (String) model.get( "msg" ) , null );
				}
			}
			}
			
		}
		
		else if (c == cmdBack) {
			deleteAll();
			removeCommand( cmdOK );
			removeCommand( cmdBack );
			tbrMidlet.setDisplay( prevDisplayable );
		}
	}
	
	public String createRequestPayload()
	{
		String request = "";
		request = "type=" + FormType.PATIENT_REGISTRATION;
		request += "&parId=" + participantID.getString();
		request += "&sdate=" + DateTimeUtil.getDate( startTreatment.getDate() );
		request += "&startdate=" + startDate;
		request += "&stime=" + startTime;
		request += "&etime=" + endTime;
		request += "&phone1=" + phoneNumber1.getString();
		request += "&phoneowner1=" + phoneNumber1Owner.getString();
		request += "&phoneowner2=" + phoneNumber2Owner.getString();
		request += "&phone2=" + phoneNumber2.getString();
		request += "&street=" + addressStreet.getString();
		request += "&district=" + addressDistrict.getString();
		request += "&house=" + addressHouse.getString();
		request += "&flat=" + addressFlat.getString();
		request += "&relationship=" + relationshipFamilyVolunteer.getString( relationshipFamilyVolunteer.getSelectedIndex() );
		request += "&relationother=" + relationshipOther.getString();
		request += "&maritalstatus=" + maritalStatus.getString( maritalStatus.getSelectedIndex() );
		request += "&education=" + education.getString( education.getSelectedIndex() );
		request += "&incomeFamilyMember=" + incomeFamilyMember.getString( incomeFamilyMember.getSelectedIndex() );
		request += "&healthWorker=" + healthWorkerID.getString();
		
		return request;
		
	}

}
