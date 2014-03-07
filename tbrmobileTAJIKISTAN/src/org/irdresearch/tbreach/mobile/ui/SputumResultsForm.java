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
import org.irdresearch.tbreach.mobile.ui.BaseTBReachForm;
import org.irdresearch.tbreach.mobile.ui.TBReachMainMIDlet;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.IdValidateUtil;

class SputumResultsForm extends BaseTBReachForm implements CommandListener, ItemStateListener
{
	private static final int	MTB_SEVERITY_INDEX		= 5;

	private static final String	GXP_NOT_DETECTED_text	= "MTB Not Detected";
	private static final String	GXP_NOT_DETECTED_value	= "MTB Negative";
	private static final String	GXP_DETECTED_text		= "MTB Detected";
	private static final String	GXP_DETECTED_value		= "MTB Positive";
	private static final String	GXP_INDETERMINATE_text	= "MTB Indeterminate";
	private static final String	GXP_INDETERMINATE_value	= "Indeterminate";

	private static final String	RIF_NOT_DETECTED_text	= "Not Detected";
	private static final String	RIF_NOT_DETECTED_value	= "RIF Sensitive";
	private static final String	RIF_DETECTED_text		= "Detected";
	private static final String	RIF_DETECTED_value		= "RIF Resistant";
	private static final String	RIF_INDETERMINATE_text	= "Indeterminate";
	private static final String	RIF_INDETERMINATE_value	= "Indeterminate";

	DateField					dateField;
	// TextField labIdField;
	// TextField labBarCodeField;
	TextField					chwIdField;
	ChoiceGroup					whichTestGroup;
	ChoiceGroup					smearResultsGroup;
	ChoiceGroup					geneXpertResultsGroup;
	ChoiceGroup					geneXpertMTBSeverity;
	ChoiceGroup					geneXpertResistanceGroup;
	TextField					gxpResultErrorField;
	TextField					gxpResistanceErrorField;

	Command						cmdOK;
	Command						cmdBack;

	private Hashtable			queryData;
	private String				patientId;
	private String				labid;
	private String				labbarcode;

	public String getLabid ()
	{
		return labid;
	}

	public void setLabid (String labid)
	{
		this.labid = labid;
	}

	public String getLabbarcode ()
	{
		return labbarcode;
	}

	public void setLabbarcode (String labbarcode)
	{
		this.labbarcode = labbarcode;
	}

	public SputumResultsForm (String title, TBReachMainMIDlet tbrMidlet)
	{
		super (title, tbrMidlet);
		dateField = null;
		// labIdField = null;
		// labBarCodeField = null;
		chwIdField = null;
		whichTestGroup = null;
		smearResultsGroup = null;
		geneXpertResultsGroup = null;
		geneXpertMTBSeverity = null;
		geneXpertResistanceGroup = null;
		gxpResultErrorField = null;
		gxpResistanceErrorField = null;

		cmdOK = null;
		cmdBack = null;
	}

	public Hashtable getQueryData ()
	{
		return queryData;
	}

	public void setQueryData (Hashtable queryData)
	{
		this.queryData = queryData;
	}

	public String getPatientId ()
	{
		return patientId;
	}

	public void setPatientId (String patientId)
	{
		this.patientId = patientId;
	}

	public void init ()
	{
		String data = "";
		String gxpOrdered = "Yes";

		if (queryData != null)
		{
			patientId = (String) queryData.get ("pid");
			data += "--Patient ID: " + patientId;
			data += "\n--First Name: " + (String) queryData.get ("fname");
			data += "\n--Last Name: " + (String) queryData.get ("lname");
			data += "\n--Lab ID: " + labid;
			data += "\n--Lab Barcode: " + labbarcode;
			gxpOrdered = (String) queryData.get ("gxp");
			append (data);
		}
		dateField = new DateField ("Date", DateField.DATE);
		dateField.setDate (new Date ());
		chwIdField = new TextField ("CHW ID:", tbrMidlet.getCurrentUserId (), IdValidateUtil.MAX_CHWID_LENGTH, TextField.ANY);

		whichTestGroup = new ChoiceGroup ("Which sputum test results are you entering?", ChoiceGroup.POPUP);
		whichTestGroup.append ("Smear Microscopy", null);
		if (gxpOrdered.toLowerCase ().equals ("yes"))
		{
			whichTestGroup.append ("GeneXpert", null);
		}

		smearResultsGroup = new ChoiceGroup ("Smear Microscopy Results", ChoiceGroup.POPUP);
		smearResultsGroup.append ("Negative", null);
		smearResultsGroup.append ("1-9 AFB", null);
		smearResultsGroup.append ("1+", null);
		smearResultsGroup.append ("2+", null);
		smearResultsGroup.append ("3+", null);

		geneXpertResultsGroup = new ChoiceGroup ("GeneXpert Results", ChoiceGroup.POPUP);
		geneXpertResultsGroup.append (GXP_NOT_DETECTED_text, null);
		geneXpertResultsGroup.append (GXP_DETECTED_text, null);
		geneXpertResultsGroup.append (GXP_INDETERMINATE_text, null);
		geneXpertResultsGroup.append ("Invalid", null);
		geneXpertResultsGroup.append ("Error", null);

		geneXpertMTBSeverity = new ChoiceGroup ("MTB Severity", ChoiceGroup.POPUP);
		geneXpertMTBSeverity.append ("Very Low", null);
		geneXpertMTBSeverity.append ("Low", null);
		geneXpertMTBSeverity.append ("Medium", null);
		geneXpertMTBSeverity.append ("High", null);
		geneXpertMTBSeverity.append ("Very High", null);

		gxpResultErrorField = new TextField ("Error code: ", "", 6, TextField.UNEDITABLE);

		geneXpertResistanceGroup = new ChoiceGroup ("GeneXpert RIF Resistance", ChoiceGroup.POPUP);
		geneXpertResistanceGroup.append (RIF_NOT_DETECTED_text, null);
		///will be enabled in item listenerr with event MTB Detected
		//geneXpertResistanceGroup.append (RIF_DETECTED_text, null);
		//geneXpertResistanceGroup.append (RIF_INDETERMINATE_text, null);
		//geneXpertResistanceGroup.append ("Invalid", null);
		//geneXpertResistanceGroup.append ("Error", null);

		gxpResistanceErrorField = new TextField ("Error code: ", "", 6, TextField.UNEDITABLE);

		cmdOK = new Command ("Save", Command.OK, 1);
		cmdBack = new Command ("Home", Command.BACK, 2);

		append (dateField);
		append (chwIdField);
		// append(labIdField);
		// append(labBarCodeField);
		append (whichTestGroup);
		append (smearResultsGroup);

		addCommand (cmdOK);
		addCommand (cmdBack);

		startDate = DateTimeUtil.getDate ();
		startTime = DateTimeUtil.getTime ();

		setCommandListener (this);
		setItemStateListener (this);
	}

	public boolean validate ()
	{
		boolean result = true;

		/*
		 * if(labIdField.getString()==null ||
		 * labIdField.getString().length()==0) {
		 * tbrMidlet.showAlert(ErrMsg.LAB_ID_MISSING,null); result = false; }
		 * else
		 * if(labIdField.getString().length()<IdValidateUtil.MIN_LABID_LENGTH){
		 * tbrMidlet.showAlert(ErrMsg.LAB_ID_INVALID, null); result = false; }
		 * else if(labBarCodeField.getString()==null ||
		 * labBarCodeField.getString().length()==0) {
		 * tbrMidlet.showAlert(ErrMsg.LAB_BARCODE_MISSING,null); result = false;
		 * }
		 */

		int gxpIndex = geneXpertResultsGroup.getSelectedIndex ();
		int gxpResIndex = geneXpertResistanceGroup.getSelectedIndex ();

		// TODO: Add check for Lab Barcode Length
		if (chwIdField.getString () == null || chwIdField.getString ().length () == 0)
		{
			tbrMidlet.showAlert (ErrMsg.CHW_ID_MISSING, null);
			result = false;
		}
		else if (chwIdField.getString ().length () < IdValidateUtil.MIN_CHWID_LENGTH)
		{
			tbrMidlet.showAlert (ErrMsg.CHW_ID_INVALID, null);
			result = false;
		}
		else if (DateTimeUtil.isDateInFuture (dateField.getDate ()))
		{
			tbrMidlet.showAlert (ErrMsg.DATE_IN_FUTURE, null);
			result = false;
		}
		else if ((geneXpertResultsGroup.getString (geneXpertResultsGroup.getSelectedIndex ()).toLowerCase ().equals ("error"))
				&& (gxpResultErrorField.getString () == null || gxpResultErrorField.getString ().length () == 0))
		{
			//tbrMidlet.showAlert (ErrMsg.MISSING_GXP_RESULTS_ERROR, null);
			result = false;
		}
		else if ((geneXpertResistanceGroup.getString (geneXpertResistanceGroup.getSelectedIndex ()).toLowerCase ().equals ("error"))
				&& (gxpResistanceErrorField.getString () == null || gxpResistanceErrorField.getString ().length () == 0))
		{
			//tbrMidlet.showAlert (ErrMsg.MISSING_GXP_RESISTANCE_ERROR, null);
			result = false;
		}
		else if ((geneXpertResistanceGroup.getString (geneXpertResistanceGroup.getSelectedIndex ()).toLowerCase ().equals ("error"))
				&& (gxpResistanceErrorField.getString () == null || gxpResistanceErrorField.getString ().length () == 0))
		{
			//tbrMidlet.showAlert (ErrMsg.MISSING_GXP_RESISTANCE_ERROR, null);
			result = false;
		}
		else if ((geneXpertResultsGroup.getString (gxpIndex).toLowerCase ().equals ("error") || geneXpertResultsGroup.getString (gxpIndex).toLowerCase ().equals ("invalid") || geneXpertResultsGroup
				.getString (gxpIndex).toLowerCase ().equals (GXP_NOT_DETECTED_text.toLowerCase ()))
				&& geneXpertResistanceGroup.getString (gxpResIndex).toLowerCase ().equals (RIF_DETECTED_text.toLowerCase ()))
		{
		//	tbrMidlet.showAlert (ErrMsg.NO_RIF_IF_NOT_POSITIVE, null);
			result = false;
		}

		return result;
	}

	public String createRequestPayload ()
	{
		String request = "";

		//request = "type=" + FormType.SPUTUM_RESULTS;
		request += "&id=" + patientId;
		request += "&un=" + tbrMidlet.getCurrentUser ();
		request += "&labid=" + labid;
		request += "&lbc=" + labbarcode;
		request += "&chwid=" + chwIdField.getString ();
		request += "&test=" + whichTestGroup.getString (whichTestGroup.getSelectedIndex ());
		if (whichTestGroup.getSelectedIndex () == 0)
		{
			request += "&smear=" + smearResultsGroup.getString (smearResultsGroup.getSelectedIndex ());
		}
		else
		{
			if (geneXpertResultsGroup.getString (geneXpertResultsGroup.getSelectedIndex ()).toLowerCase ().equals ("error"))
			{
				request += "&gxp=ERROR " + gxpResultErrorField.getString ();
			}
			else
			{
				String gxpString = geneXpertResultsGroup.getString (geneXpertResultsGroup.getSelectedIndex ());
				if (gxpString.toLowerCase ().equals (GXP_DETECTED_text.toLowerCase ()))
				{
					gxpString = GXP_DETECTED_value + ":" + geneXpertMTBSeverity.getString( geneXpertMTBSeverity.getSelectedIndex() );
				}
				else if (gxpString.toLowerCase ().equals (GXP_NOT_DETECTED_text.toLowerCase ()))
				{
					gxpString = GXP_NOT_DETECTED_value;
				}
				else if (gxpString.toLowerCase ().equals (GXP_INDETERMINATE_text.toLowerCase ()))
				{
					gxpString = GXP_INDETERMINATE_value;
				}
				request += "&gxp=" + gxpString;
			}

			if (geneXpertResistanceGroup.getString (geneXpertResistanceGroup.getSelectedIndex ()).toLowerCase ().equals ("error"))
			{
				request += "&gxpr=ERROR " + gxpResistanceErrorField.getString ();
			}
			else
			{
				String rifString = geneXpertResistanceGroup.getString (geneXpertResistanceGroup.getSelectedIndex ());
				if (rifString.toLowerCase ().equals (RIF_DETECTED_text.toLowerCase ()))
				{
					rifString = RIF_DETECTED_value;
				}
				else if (rifString.toLowerCase ().equals (RIF_NOT_DETECTED_text.toLowerCase ()))
				{
					rifString = RIF_NOT_DETECTED_value;
				}
				else if (rifString.toLowerCase ().equals (RIF_INDETERMINATE_text.toLowerCase ()))
				{
					rifString = RIF_INDETERMINATE_value;
				}
				request += "&gxpr=" + rifString;
			}
		}
		request += "&sd=" + startDate;
		request += "&st=" + startTime;
		request += "&et=" + endTime;
		request += "&ed=" + DateTimeUtil.getDateTime (dateField.getDate ());

		return request;
	}

	public void commandAction (Command c, Displayable d)
	{
		if (c == cmdOK)
		{
			if (validate ())
			{
				endTime = DateTimeUtil.getTime ();
				String request = createRequestPayload ();
				System.out.println (request);
				Hashtable model = tbrMidlet.sendToServer (request);

				if (model != null)
				{
					String status = (String) model.get ("status");

					if (status.equals (XmlStrings.SUCCESS)
							&& geneXpertResistanceGroup.getString (geneXpertResistanceGroup.getSelectedIndex ()).toLowerCase ().equals (RIF_DETECTED_text.toLowerCase ()))
					{
						//tbrMidlet.showAlert (SuccessMsg.RIF_WARNING, null);
					}
					else if (status.equals (XmlStrings.SUCCESS))
					{
						tbrMidlet.showAlert (SuccessMsg.SAVE_SUCCESS, null);
					}
					else if (status.equals (XmlStrings.ERROR))
					{
						tbrMidlet.showAlert ((String) model.get ("msg"), null);
					}
				}
			}
		}
		else if (c == cmdBack)
		{
			deleteAll ();
			removeCommand (cmdOK);
			removeCommand (cmdBack);
			tbrMidlet.setDisplay (prevDisplayable);
		}
	}

	public void itemStateChanged (Item i)
	{
		if (i == whichTestGroup)
		{
			if (whichTestGroup.getSelectedIndex () == 0)
			{
				if (get (size () - 1) == gxpResistanceErrorField)
				{
					delete (size () - 1);
					delete (size () - 1);
					delete (size () - 1);
					delete (size () - 1);
					append (smearResultsGroup);
				}
			}
			else
			{
				if (get (size () - 1) == smearResultsGroup)
				{
					delete (size () - 1);
					append (geneXpertResultsGroup);
					append (gxpResultErrorField);
					append (geneXpertResistanceGroup);
					append (gxpResistanceErrorField);
				}
			}
		}
		else if (i == geneXpertResultsGroup)
		{
			String gxpResult = geneXpertResultsGroup.getString (geneXpertResultsGroup.getSelectedIndex ()).toLowerCase ();
			
			if (gxpResult.equals (GXP_DETECTED_text.toLowerCase ()))
			{
				geneXpertResistanceGroup.deleteAll();
				geneXpertResistanceGroup.append (RIF_NOT_DETECTED_text, null);
				geneXpertResistanceGroup.append (RIF_DETECTED_text, null);
				geneXpertResistanceGroup.append (RIF_INDETERMINATE_text, null);
				geneXpertResistanceGroup.append ("Invalid", null);
				geneXpertResistanceGroup.append ("Error", null);
				
				if (get (MTB_SEVERITY_INDEX) == gxpResultErrorField)
				{
					delete (MTB_SEVERITY_INDEX);
					insert (MTB_SEVERITY_INDEX, geneXpertMTBSeverity);
				}
				else if(get (MTB_SEVERITY_INDEX) != geneXpertMTBSeverity)
				{
					insert (MTB_SEVERITY_INDEX, geneXpertMTBSeverity);
				}
			}
			else{
				if (get (MTB_SEVERITY_INDEX) != gxpResultErrorField)
				{
					if(get (MTB_SEVERITY_INDEX) == geneXpertMTBSeverity) delete (MTB_SEVERITY_INDEX);
					insert (MTB_SEVERITY_INDEX, gxpResultErrorField);
				}
				gxpResultErrorField.setString ("");
				gxpResultErrorField.setConstraints (TextField.UNEDITABLE);
				
				if (gxpResult.equals (GXP_NOT_DETECTED_text.toLowerCase ()))
				{
					geneXpertResistanceGroup.deleteAll();
					geneXpertResistanceGroup.append (RIF_NOT_DETECTED_text, null);
				}
				else{
					if (gxpResult.equals ("error"))
					{
						gxpResultErrorField.setConstraints (TextField.NUMERIC);
					}

					geneXpertResistanceGroup.deleteAll();
					geneXpertResistanceGroup.append ("Invalid", null);
					geneXpertResistanceGroup.append ("Error", null);
				}
			}
			
		}
		else if (i == geneXpertResistanceGroup)
		{
			if (geneXpertResistanceGroup.getString (geneXpertResistanceGroup.getSelectedIndex ()).toLowerCase ().equals ("error"))
			{
				gxpResistanceErrorField.setConstraints (TextField.NUMERIC);
			}
			else
			{
				gxpResistanceErrorField.setString ("");
				gxpResistanceErrorField.setConstraints (TextField.UNEDITABLE);
			}
		}
	}
}
