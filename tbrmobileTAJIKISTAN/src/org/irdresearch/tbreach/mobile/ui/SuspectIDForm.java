package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.m3g.Group;
import javax.microedition.media.control.TempoControl;
import org.irdresearch.tbreach.mobile.constants.SuccessMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.model.FormType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;
import org.irdresearch.tbreach.mobile.util.StringUtil;
import java.util.*;

public class SuspectIDForm extends BaseTBReachForm implements CommandListener ,
		ItemStateListener {

	private static final String	LESS_THAN_string			= "less than";
	private static final String	GREATER_THAN_string			= "greater than";

	/** 'is TB MEDICATION taken?' question choiceGroup/stringItem INDEX on form */
	// private static final int TB_MEDindex = 11;
	/**
	 * 'what was TB TREATMENT DURATION?' question choiceGroup/stringItem INDEX
	 * on form
	 */
	// private static final int TB_TRT_DURindex = 12;
	/** 'what was COUGH DURATION?' question choiceGroup/stringItem INDEX on form */
	private static final int	COUGH_DURindex				= 5;
	/** 'is PRODUCTIVE COUGH?' question choiceGroup/stringItem INDEX on form */
	private static final int	PROD_COUGHindex				= 6;

	private boolean				IS_SUSPECT					= false;
	private boolean				isPatientInfoFieldsVisible	= false;
	private int					SUSPECT_INDEX				= 0;
	private int					NOT_SUSPECT_INDEX			= 1;
	private ChoiceGroup			list;
	// private TBReachMainMIDlet tbrMidlet;
	DateField					dateField;
	TextField					chwIdField;
	// TextField labField;

	TextField					idField;
	TextField					idConfirm;
	TextField					gpIdField;
	TextField					firstNameField;
	TextField					lastNameField;
	TextField					ageField;
	// TextField phoneField;
	// TextField labTestOtherField;
	TextField					currentTreatmentDurField;
	// TextField otherField;

	// ChoiceGroup currentlyOnTreatmentChoice;

	// StringItem tbMedicationString;
	// StringItem tbTreatmentString;
	StringItem					coughDurationString;
	StringItem					productiveCoughString;
	// ChoiceGroup tbMedicationChoice;
	// ChoiceGroup tbTreatmentChoice;
	ChoiceGroup					sexGroup;
	/*
	 * ChoiceGroup howHearGroup; ChoiceGroup whoToldGroup; ChoiceGroup
	 * someoneElseAdvertGroup; ChoiceGroup adFormGroup;
	 */
	ChoiceGroup					coughGroup;
	ChoiceGroup					coughDurationGroup;
	// ChoiceGroup labTestName;
	ChoiceGroup					productiveCoughGroup;
	ChoiceGroup					tbHistoryGroup;
	ChoiceGroup					tbFamilyHistoryGroup;
	ChoiceGroup					locationGroup;
	ChoiceGroup					feverGroup;
	ChoiceGroup					nightSweatGroup;
	ChoiceGroup					weightLossGroup;
	ChoiceGroup					haemoptysisGroup;
	TextField					conclusionGroup;
	String						englishconclusionGroup;
	/*
	 * ChoiceGroup gpConfirmed; ChoiceGroup consentRead;
	 */
	Command						cmdOK;
	Command						cmdBack;
	Item[]						formItems;
	TextField					doctorName;
	TextField					indexHospital;
	TextField					districtregistration;
	ChoiceGroup					treatmentquestion;

	// boolean symptoms;

	public SuspectIDForm( String title , TBReachMainMIDlet tbrMidlet ) {
		super( title , tbrMidlet );
		dateField = null;
		chwIdField = null;
		// labField = null;
		list = null;
		idField = null;
		idConfirm = null;
		gpIdField = null;
		firstNameField = null;
		lastNameField = null;
		ageField = null;
		// phoneField= null;
		// labTestOtherField= null;
		// tbMedicationChoice= null;
		// tbTreatmentChoice= null;
		// tbMedicationString= null;
		// tbTreatmentString= null;

		/*
		 * howHearGroup = null; whoToldGroup = null; adFormGroup = null;
		 */
		// otherField = null;
		sexGroup = null;
		coughGroup = null;
		coughDurationGroup = null;
		coughDurationString = null;
		// labTestName= null;
		productiveCoughGroup = null;
		productiveCoughString = null;
		tbHistoryGroup = null;
		tbFamilyHistoryGroup = null;
		feverGroup = null;
		nightSweatGroup = null;
		weightLossGroup = null;
		haemoptysisGroup = null;
		conclusionGroup = null;
		englishconclusionGroup = null;
		/*
		 * gpConfirmed = null; consentRead = null;
		 */
		locationGroup = null;
		cmdOK = null;
		cmdBack = null;
		doctorName = null;
		indexHospital = null;
		districtregistration = null;
		treatmentquestion = null;

	}

	public void init() {
		IS_SUSPECT = false;
		isPatientInfoFieldsVisible = false;

		// TODO is it OK or needs to be NUMERIC
		chwIdField = new TextField( "Чойгиршави:" ,
				tbrMidlet.getCurrentUserId() , IdValidateUtil.MAX_CHWID_LENGTH ,
				TextField.ANY );

		// gpIdField = new TextField("GP ID:", "",12, TextField.ANY);
		/*
		 * if(tbrMidlet.getSettings().getActiveUserType()==UserType.USER_TYPE_GP)
		 * { gpIdField.setString(tbrMidlet.getCurrentUserId());
		 * chwIdField.setConstraints(TextField.UNEDITABLE); }
		 */
		dateField = new DateField( "Санаи мусохиба" , DateField.DATE );
		dateField.setDate( new Date() );
		list = new ChoiceGroup( "Як-то интихоб" , Choice.EXCLUSIVE );
		list.append( "Руз" , null );
		list.append( "Мох" , null );
		list.append( "Сол" , null );
		list.setSelectedIndex( 2 , true );

		/*
		 * doctorName = new TextField( "Doctor's Name" , "" , 25 ,
		 * TextField.ANY) ; indexHospital = new TextField( "Index # of Hospital"
		 * , "" , 15 , TextField.ANY); districtregistration = new TextField(
		 * "District Registration on TB03" , "" , 15 , TextField.ANY);
		 */
		firstNameField = new TextField( "Ном:" , "" , 25 , TextField.ANY );
		lastNameField = new TextField( "Насаб:" , "" , 25 , TextField.ANY );
		// labTestOtherField = new TextField("If other, specify:", "", 15,
		// TextField.ANY);

		sexGroup = new ChoiceGroup( "Чинс:" , Choice.POPUP );
		sexGroup.append( "M" , null );
		sexGroup.append( "З" , null );

		ageField = new TextField( "Сину сол:" , "" , 3 , TextField.NUMERIC );
		// phoneField = new TextField("Phone:" , "", 20, TextField.ANY);

		treatmentquestion = new ChoiceGroup( "Табобати зидди сили мегиред?" ,
				ChoiceGroup.POPUP );
		treatmentquestion.append( "He" , null );
		treatmentquestion.append( "Ха" , null );
		treatmentquestion.setSelectedIndex( 0 , true );

		coughGroup = new ChoiceGroup( "Сулфа доред?" , ChoiceGroup.POPUP );
		coughGroup.append( "Не" , null );
		coughGroup.append( "Ха" , null );
		coughGroup.setSelectedIndex( 0 , true );

		coughDurationGroup = new ChoiceGroup( "Чанд хафта Шумо месулфед?" ,
				ChoiceGroup.POPUP );
		coughDurationGroup.append( "Камтар аз 2" , null );
		coughDurationGroup.append( "2-3 хафта" , null );
		coughDurationGroup.append( "Зиёд аз 3 хафта" , null );
		coughDurationGroup.append( "Намедонам" , null );
		// coughDurationGroup.append("Чавоб нест", null);
		coughDurationGroup.setSelectedIndex( 0 , true );

		coughDurationString = new StringItem( "" , "" );

		productiveCoughGroup = new ChoiceGroup(
				"Хангоми сулфидан балгам доред?" , ChoiceGroup.POPUP );
		productiveCoughGroup.append( "Не" , null );
		productiveCoughGroup.append( "Ха" , null );
		productiveCoughGroup.append( "Чавоб нест" , null );
		// productiveCough.append("Don't know", null);
		productiveCoughGroup.setSelectedIndex( 0 , true );

		productiveCoughString = new StringItem( "" , "" );

		tbHistoryGroup = new ChoiceGroup( "Бемории сил шудаед?" ,
				ChoiceGroup.POPUP );
		tbHistoryGroup.append( "Не" , null );
		tbHistoryGroup.append( "Ха" , null );
		tbHistoryGroup.append( "Чавоб нест" , null );
		// tbHistory.append("Don't Know", null);
		tbHistoryGroup.setSelectedIndex( 0 , true );

		tbFamilyHistoryGroup = new ChoiceGroup( "Дар оила бемори сил хаст?" ,
				ChoiceGroup.POPUP );
		tbFamilyHistoryGroup.append( "Не" , null );
		tbFamilyHistoryGroup.append( "Ха" , null );
		tbFamilyHistoryGroup.append( "Чавоб нест" , null );
		// tbFamilyHistory.append("Don't Know", null);
		tbFamilyHistoryGroup.setSelectedIndex( 0 , true );

		/*
		 * gpConfirmed = new
		 * ChoiceGroup("Did the GP confirm that this person is an eptb suspect?"
		 * , Choice.POPUP); gpConfirmed.append("No", null);
		 * gpConfirmed.append("Yes",null);
		 */

		conclusionGroup = new TextField( "Шубха ба сил хаст ё не?" , "" , 15 ,
				TextField.UNEDITABLE );

		// conclusionGroup.setString("Suspect");
		//
		SUSPECT_INDEX = 0;
		NOT_SUSPECT_INDEX = 1;
		// conclusionGroup.setSelectedIndex(NOT_SUSPECT_INDEX, true);
		conclusionGroup.setString( "Сил нест" );
		// idField = new TextField("Раками шахсии иштирокчи:", "",
		// IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);
		// idConfirm = new TextField("Раками шахсии иштирокчи:", "",
		// IdValidateUtil.MAX_PAT_LENGTH, TextField.NUMERIC);

		locationGroup = new ChoiceGroup( "Чойгиршави:" , Choice.POPUP );
		locationGroup.append( "Poly Clinic Dushanbe 1" , null );
		locationGroup.append( "Poly Clinic Dushanbe 2" , null );
		locationGroup.append( "Poly Clinic Dushanbe 3" , null );
		locationGroup.append( "Poly Clinic Dushanbe 4" , null );
		locationGroup.append( "Poly Clinic Dushanbe 5" , null );
		locationGroup.append( "Poly Clinic Dushanbe 6" , null );
		locationGroup.append( "Poly Clinic Dushanbe 7" , null );
		locationGroup.append( "Poly Clinic Dushanbe 8" , null );
		locationGroup.append( "Poly Clinic Dushanbe 9" , null );
		locationGroup.append( "Poly Clinic Dushanbe 10" , null );
		locationGroup.append( "Poly Clinic Dushanbe 11" , null );
		locationGroup.append( "Poly Clinic Dushanbe 12" , null );
		locationGroup.append( "Poly Clinic Dushanbe 13" , null );
		locationGroup.append( "Poly Clinic Dushanbe 14" , null );
		locationGroup.append( "Poly Clinic Rudaki" , null );
		locationGroup.append( "Poly Clinic Tursunzade" , null );
		locationGroup.append( "Diabetes center Dushanbe" , null );
		locationGroup.append( "Prison system" , null );

		feverGroup = new ChoiceGroup( "Ду хафтаи охир тасб доред?" ,
				Choice.POPUP );
		feverGroup.append( "Не" , null );
		feverGroup.append( "Ха" , null );
		feverGroup.append( "Чавоб нест" , null );
		// fever.append("Don't know", null);

		nightSweatGroup = new ChoiceGroup(
				"Ду хафтаи охир шабхо \nарак мекунед?" , Choice.POPUP );
		nightSweatGroup.append( "Не" , null );
		nightSweatGroup.append( "Ха" , null );
		nightSweatGroup.append( "Чавоб нест" , null );
		// nightSweat.append("Don't know", null);

		weightLossGroup = new ChoiceGroup( "Пастшавии иштихо ва вазн хаст?" ,
				Choice.POPUP );
		weightLossGroup.append( "Не" , null );
		weightLossGroup.append( "Ха" , null );
		weightLossGroup.append( "Чавоб нест" , null );
		// weightLoss.append("Don't know", null);

		haemoptysisGroup = new ChoiceGroup( "Хунтуфкуни хангоми сулфа хаст?" ,
				Choice.POPUP );
		haemoptysisGroup.append( "Не" , null );
		haemoptysisGroup.append( "Ха" , null );
		haemoptysisGroup.append( "Чавоб нест" , null );
		// haemoptysis.append("Don't know", null);

		cmdOK = new Command( "Сабт" , Command.OK , 1 );
		cmdBack = new Command( "Ба аввал" , Command.BACK , 2 );

		Item items[] = {/* dateField, *//* chwIdField, *//* list, */ageField ,
				firstNameField , lastNameField , sexGroup , treatmentquestion ,
				coughGroup , coughDurationGroup , productiveCoughGroup ,
				tbHistoryGroup , tbFamilyHistoryGroup , conclusionGroup ,
				feverGroup , nightSweatGroup , weightLossGroup ,
				haemoptysisGroup };
		formItems = items;
		addCommand( cmdOK );
		addCommand( cmdBack );

		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		initialShow();
		setCommandListener( this );
		setItemStateListener( this );
		englishconclusionGroup = "NONSUSPECT";
	}

	private int indexOf(Item item) {
		for (int i = 0; i < formItems.length; i++)
			if (formItems[i] == item)
				return i;
		return -1;
	}

	private void show(Item item) {
		int i = indexOf( item );
		delete( i );
		insert( i , item );
	}

	private void hide(Item item) {
		int i = indexOf( item );
		delete( i );
		StringItem tmp = new StringItem( "" , "" );
		insert( i , tmp );
	}

	private void initialShow() {
		// Add Items to form
		for (int i = 0; i < formItems.length; i++)
			append( formItems[i] );
		hide( firstNameField );
		hide( lastNameField );
		hide( coughDurationGroup );
		hide( productiveCoughGroup );
		// hide (idField);
		// hide (idConfirm);
		// hide (locationGroup);
		hide( feverGroup );
		hide( nightSweatGroup );
		hide( weightLossGroup );
		hide( haemoptysisGroup );
	}

	public void commandAction(Command c , Displayable d) {

		if (c == cmdOK) {
			if (validate()) {
				/*
				 * if(!IS_SUSPECT){
				 * tbrMidlet.showAlert(ErrMsg.SUSPECT_IS_UNSAVEABLE,null);
				 * return; }
				 */
				endTime = DateTimeUtil.getTime();
				String request = createRequestPayload();
				System.out.println( request );
				Hashtable model = tbrMidlet.sendToServer( request );

				if (model != null) {
					String status = (String) model.get( "status" );
					String uniqueID = (String) model.get( "uniqueID" );

					if (status.equals( XmlStrings.SUCCESS )) {
						System.out.println( "success" );
						if (IS_SUSPECT) {
							deleteAll();
							removeCommand( cmdOK );
							removeCommand( cmdBack );
							init();
							tbrMidlet
									.showAlert(
											"Маълумотхо сабт шудаанд. Барои бозгашт ба Менюи аввал тугмачаи Done-ро зер намоед. Раками бакайдгирии ин шахс "
													+ uniqueID + " аст" , null );

						}
						else {
							deleteAll();
							removeCommand( cmdOK );
							removeCommand( cmdBack );
							init();
							tbrMidlet
									.showAlert(
											"Ин шахс гумонбар ба сил нест. Маълумотхо сабт шудаанд. Барои бозгашт ба Менюи аввал тугмачаи Done-ро зер намоед" ,
											null );

						}

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

	public String createRequestPayload() {
		String request = "";

		// request for suspect and not suspect both getting handled by only one
		// method
		request = "type=" + FormType.SUSPECT_ID;

		if (IS_SUSPECT) {
			request += "&fn=" + firstNameField.getString();
			request += "&ln=" + lastNameField.getString();
			request += "&fev="
					+ feverGroup.getString( feverGroup.getSelectedIndex() );
			request += "&ns="
					+ nightSweatGroup.getString( nightSweatGroup
							.getSelectedIndex() );
			request += "&wl="
					+ weightLossGroup.getString( weightLossGroup
							.getSelectedIndex() );
			request += "&ha="
					+ haemoptysisGroup.getString( haemoptysisGroup
							.getSelectedIndex() );

		}
		request += "&chwid=" + chwIdField.getString();
		request += "&sex=" + sexGroup.getString( sexGroup.getSelectedIndex() );
		request += "&age=" + ageField.getString();
		int coughIndex = coughGroup.getSelectedIndex();
		request += "&agetype=" + list.getString( list.getSelectedIndex() );

		request += "&cough=" + coughGroup.getString( coughIndex );
		if (coughIndex == 1) {// if cough is yes
			int coughDurationIndex = coughDurationGroup.getSelectedIndex();
			String coughDurString = coughDurationGroup
					.getString( coughDurationIndex );
			request += "&cd=" + coughDurString;

			request += "&pc="
					+ productiveCoughGroup.getString( productiveCoughGroup
							.getSelectedIndex() );
		}
		request += "&tbh="
				+ tbHistoryGroup.getString( tbHistoryGroup.getSelectedIndex() );

		request += "&ftbh="
				+ tbFamilyHistoryGroup.getString( tbFamilyHistoryGroup
						.getSelectedIndex() );
		request += "&conc=" + englishconclusionGroup;

		request += "&sd=" + startDate;
		request += "&st=" + startTime;
		request += "&et=" + endTime;
		request += "&ed=" + DateTimeUtil.getDate( dateField.getDate() );

		return request;
	}

	private boolean validate() {
		boolean result = true;
		int check = -1;

		if (chwIdField.getString() == null
				|| chwIdField.getString().length() == 0) {
			tbrMidlet.showAlert( ErrMsg.CHW_ID_MISSING , null );
			result = false;
		}
		else if (chwIdField.getString().length() < IdValidateUtil.MIN_CHWID_LENGTH) {
			tbrMidlet.showAlert( ErrMsg.CHW_ID_INVALID , null );
			result = false;
		}
		else if (ageField.getString() == null
				|| ageField.getString().length() == 0) {
			tbrMidlet.showAlert( ErrMsg.AGE_MISSING , null );
			result = false;
		}

		else if (dateField.getDate() == null) {
			tbrMidlet.showAlert( ErrMsg.DATE_NOT_ENTERED , null );
			result = false;
		}
		else if (treatmentquestion.getSelectedIndex() == 1) {
			deleteAll();
			removeCommand( cmdOK );
			removeCommand( cmdBack );
			init();
			tbrMidlet
					.showAlert(
							"Ин бемор дар табобат карор дорад, гумонбар нест. Барои бозгашт ба Менюи аввал тугмачаи Done-ро зер намоед" ,
							null );
			result = false;
		}
		// TODO id validations not implemented

		else if (IS_SUSPECT) {

			if (firstNameField.getString() == null
					|| firstNameField.getString().length() == 0) {
				tbrMidlet.showAlert( ErrMsg.FIRST_NAME_MISSING , null );
				result = false;
			}
			else if (lastNameField.getString() == null
					|| lastNameField.getString().length() == 0) {
				tbrMidlet.showAlert( ErrMsg.LAST_NAME_MISSING , null );
				result = false;
			}
		}
		else if (DateTimeUtil.isDateInFuture( dateField.getDate() )) {
			tbrMidlet.showAlert( ErrMsg.DATE_IN_FUTURE , null );
			result = false;
		}

		return result;
	}

	public void itemStateChanged(Item i) {
		if (i == coughGroup) {

			int indexx = 1;

			if (coughGroup.getSelectedIndex() == indexx) {
				show( coughDurationGroup );
				show( productiveCoughGroup );

			}
			else {
				hide( coughDurationGroup );
				hide( productiveCoughGroup );

			}
			setSuspect();
		}
		else if (i == coughDurationGroup) {
			setSuspect();
		}

		else if (i == productiveCoughGroup) {
			setSuspect();
		}

		else if (i == tbHistoryGroup) {
			setSuspect();
		}

		else if (i == tbFamilyHistoryGroup) {
			setSuspect();
		}
	}

	public void setSuspect() {
		IS_SUSPECT = false;
		int localIndex = 1;

		if (coughGroup.getSelectedIndex() == localIndex) {

			if (coughDurationGroup.getSelectedIndex() == 2) {
				IS_SUSPECT = true;
			}
			else if (coughDurationGroup.getSelectedIndex() == 3) {
				IS_SUSPECT = true;
			}
			else if (coughDurationGroup.getSelectedIndex() == 1
					&& productiveCoughGroup.getSelectedIndex() == 1) {
				IS_SUSPECT = true;
			}
		}

		if ((tbHistoryGroup.getSelectedIndex() == 1)){
			IS_SUSPECT = true;
		} else if (tbFamilyHistoryGroup.getSelectedIndex() == 1) {
			IS_SUSPECT = true;
		}

		if (IS_SUSPECT) {
			conclusionGroup.setString( "Шубха ба сил" );
			englishconclusionGroup = "SUSPECT";
			// if(!isPatientInfoFieldsVisible){
			append( firstNameField );
			append( lastNameField );
			// append(locationGroup);
			append( feverGroup );
			append( nightSweatGroup );
			append( weightLossGroup );
			append( haemoptysisGroup );
			// isPatientInfoFieldsVisible=true;
			// }
		}
		else {
			conclusionGroup.setString( "Сил нест" );
			englishconclusionGroup = "NONSUSPECT";
			// if(isPatientInfoFieldsVisible){
			while (true) {// delete all till conclusion group
				if (get( size() - 1 ) != conclusionGroup) {
					delete( size() - 1 );
				}
				else {
					break;
				}
			}
			// isPatientInfoFieldsVisible=false;
			// }
		}
	}
}
