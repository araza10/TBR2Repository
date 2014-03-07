package org.irdresearch.tbreach.mobile.model;

import org.irdresearch.tbreach.mobile.constants.MenuItem;
import org.irdresearch.tbreach.mobile.constants.UserType;
import org.irdresearch.tbreach.mobile.constants.UserRights;

public class Settings {
	private static final String DEFAULT_BASE_URL = "http://localhost:8080/tbr2mobileweb/tbrmobile.jsp";
	
	private static final String DEFAULT_SECONDARY_URL = "http://localhost:15000/tbrmobileweb/tbrmobile.jsp";
	private static final String DEFAULT_SCAN_DELAY = "2000";
	private static final String DEFAULT_CONNECTION_TIMEOUT = "360000"; // 2 minutes
	private static final String DEFAULT_PHONE_NUMBER = "0300123456";
	private String phoneNumber;
	private String baseURL;
	private String secondaryURL;
	private String scanDelay;
	private String connectionTimeout;
	private ListItem listItems[] = null;
	private int activeUserType;
	//private Role[] roles = null;
	
	public Settings() {
		baseURL = DEFAULT_BASE_URL;
		secondaryURL = DEFAULT_SECONDARY_URL;
		phoneNumber = DEFAULT_PHONE_NUMBER;
		scanDelay = DEFAULT_SCAN_DELAY;
		connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
		activeUserType= -1;
		//defaultRoles();
		defaultListItems();		
	}
	
	/*private void defaultRoles() {
		roles = new Role[UserRole.ROLE_MAX];
		
		roles[UserRole.ROLE_DMU] = new Role(UserRole.ROLE_DMU, "DMU", true);		
		roles[UserRole.ROLE_EPI] = new Role(UserRole.ROLE_EPI, "EPI", false);		
		roles[UserRole.ROLE_REGISTRATION_AND_PICKUP] = new Role(UserRole.ROLE_REGISTRATION_AND_PICKUP, "Registration", false);
		roles[UserRole.ROLE_GP] = new Role(UserRole.ROLE_GP, "GP", false);		
		roles[UserRole.ROLE_SMO] = new Role(UserRole.ROLE_SMO, "SMO", false);		
		roles[UserRole.ROLE_PHLEBOTOMIST] = new Role(UserRole.ROLE_PHLEBOTOMIST, "Phlebotomist", false);
		roles[UserRole.ROLE_SCAN_STATION] = new Role(UserRole.ROLE_SCAN_STATION, "Scan Station", false);
		roles[UserRole.ROLE_LAB] = new Role(UserRole.ROLE_LAB, "Lab", false);
		roles[UserRole.ROLE_SERVICE] = new Role(UserRole.ROLE_SERVICE, "Service", false);
	}*/
	
	private void defaultListItems() {
		listItems = new ListItem[MenuItem.MENU_ITEM_MAX];
		
		listItems[MenuItem.MENU_SUSPECT_ID] = new ListItem(MenuItem.MENU_SUSPECT_ID, "Скрининг", UserRights.getUserRights (getActiveUserType (), MenuItem.MENU_SUSPECT_ID));
		listItems[MenuItem.MENU_SUSPECT_CONFIRM] = new ListItem(MenuItem.MENU_SUSPECT_CONFIRM, "Confirm Suspect", true);
		listItems[MenuItem.MENU_SUSPECT_VERIFY] = new ListItem(MenuItem.MENU_SUSPECT_VERIFY, "Verify Suspect ", true);
		listItems[MenuItem.MENU_PATIENT_INFO] = new ListItem(MenuItem.MENU_PATIENT_INFO, "New Patient", true);
		listItems[MenuItem.MENU_PATIENT_TB_INFO] = new ListItem(MenuItem.MENU_PATIENT_TB_INFO, "New Patient TB History", true);
		listItems[MenuItem.MENU_PATIENT_GPS] = new ListItem(MenuItem.MENU_PATIENT_GPS, "Patient GPS", true);
		listItems[MenuItem.MENU_PATIENT_SPUTUM] = new ListItem(MenuItem.MENU_PATIENT_SPUTUM, "Sputum Collection", true);
		listItems[MenuItem.MENU_SPUTUM_RESULT] = new ListItem(MenuItem.MENU_SPUTUM_RESULT, "Sputum Result", true);
		listItems[MenuItem.MENU_REFUSAL] = new ListItem(MenuItem.MENU_REFUSAL, "Refusal", true);
		listItems[MenuItem.MENU_BASELINE_TREATMENT] = new ListItem(MenuItem.MENU_BASELINE_TREATMENT, "Baseline Treatment", true);
		listItems[MenuItem.MENU_FOLLOWUP_TREATMENT] = new ListItem(MenuItem.MENU_FOLLOWUP_TREATMENT, "Followup Treatment", true);
		listItems[MenuItem.MENU_CLINICAL_DIAGNOSIS] = new ListItem(MenuItem.MENU_CLINICAL_DIAGNOSIS, "Adult Clinical Diagnosis", true);
		//both below eliminated
		listItems[MenuItem.MENU_DRUG_ADMIN] = new ListItem(MenuItem. MENU_DRUG_ADMIN, "Drug Administration", false);
		listItems[MenuItem.MENU_END_FOLLOWUP ] = new ListItem(MenuItem.MENU_END_FOLLOWUP , "End Follow-up", false);	
		
		listItems[MenuItem.MENU_MR_NUM] = new ListItem(MenuItem.MENU_MR_NUM, "Assign MR Number", true);
		listItems[MenuItem.MENU_DFR] = new ListItem(MenuItem.MENU_DFR, "Daily Field Report", true);
		listItems[MenuItem.MENU_SEARCH] = new ListItem(MenuItem.MENU_SEARCH, "Search", true);
		listItems[MenuItem.MENU_SPUTUM_RESULT_SEARCH] = new ListItem(MenuItem.MENU_SPUTUM_RESULT_SEARCH,"Sputum Barcode Search", true);
		listItems[MenuItem.MENU_LAB_SEARCH] = new ListItem(MenuItem.MENU_LAB_SEARCH, "Lab Result Search", true);
		listItems[MenuItem.MENU_FORM_COUNT] = new ListItem(MenuItem.MENU_FORM_COUNT, "Forms Submitted", true);
		listItems[MenuItem.MENU_PCF] = new ListItem(MenuItem.MENU_PCF, "Paed. Confirmation", true); 
		listItems[MenuItem.MENU_NEW_CT_SUSPECT] = new ListItem(MenuItem.MENU_NEW_CT_SUSPECT, "New Contact Tracing Suspect", true);
		listItems[MenuItem.MENU_PCDF] = new ListItem(MenuItem.MENU_PCDF, "Paed. Clinical Diagnosis", true); 
		listItems[MenuItem.MENU_CONTACT_SPUTUM] = new ListItem(MenuItem.MENU_CONTACT_SPUTUM, "Contact Sputum Collection", true);
		listItems[MenuItem.MENU_PATIENT_FUP_EFFORT] = new ListItem(MenuItem.MENU_PATIENT_FUP_EFFORT, "Patient FUP Effort", true);
		listItems[MenuItem.MENU_PEADS_CLINICAL_VISIT] = new ListItem(MenuItem.MENU_PEADS_CLINICAL_VISIT, "Peads Clinical Visit", true);
		listItems[MenuItem.MENU_CLINICAL_VISIT] = new ListItem(MenuItem.MENU_CLINICAL_VISIT, "Clinical Visit", true);
		listItems[MenuItem.MENU_DRUG_DISPENSAL] = new ListItem(MenuItem.MENU_DRUG_DISPENSAL, "Drug Dispensation", true);
		listItems[MenuItem.MENU_DOTS_ASSIGN] = new ListItem(MenuItem.MENU_DOTS_ASSIGN,"Assign DOTS Number",true);
		listItems[MenuItem.MENU_ADDR_UPDATE] = new ListItem(MenuItem.MENU_DOTS_ASSIGN,"Address Update",true);
		listItems[MenuItem.MENU_PAT_VERIFY] = new ListItem(MenuItem.MENU_PAT_VERIFY,"Verify Patient",true);
		listItems[MenuItem.MENU_NO_ACTIVE_FOLLOWUP] = new ListItem(MenuItem.MENU_PAT_VERIFY,"No Active Followup",true);
		listItems[MenuItem.MENU_PATIENT_REGISTRATION] = new ListItem(MenuItem.MENU_PATIENT_REGISTRATION,"Patient Registration",UserRights.getUserRights (getActiveUserType (), MenuItem.MENU_PATIENT_REGISTRATION));

	}
/*
 * public static final int MENU_SUSPECT_ID = 0;
	public static final int MENU_SUSPECT_CONFIRM = 1;
	public static final int MENU_SUSPECT_VERIFY = 2;
	public static final int MENU_PATIENT_INFO = 3;
	public static final int MENU_PATIENT_GPS = 4;
	public static final int MENU_PATIENT_SPUTUM = 5;
	public static final int MENU_REFUSAL = 6;
	public static final int MENU_PATIENT_RESULT = 7;
	public static final int MENU_BASELINE_TREATMENT = 8;
	public static final int MENU_TREATMENT_REGIMEN = 9;
	public static final int MENU_NEW_TREATMENT = 10;
	public static final int MENU_DOT = 11;
	public static final int MENU_GPS_FORM = 12;
 */
	
	
	
	//  scan, enter, write, new, reissue, deactivate, status, configure, settings
	private void loadSettings(boolean suspectId, boolean suspectConfirm, boolean suspectVerify, boolean patientInfo, boolean patientTBInfo, boolean patientGPS, boolean patientSputum, boolean sputumResult,
			boolean refusal, boolean baselineTreatment, boolean followupTreatment, boolean drugAdmin, boolean endFollowup, boolean mrAssign, boolean dfr, boolean search, boolean sputumSearch,
			boolean labSearch,boolean formCount, boolean cdf, boolean nctsf, boolean pcf, boolean pcdf, boolean patFupEffrot, boolean peadClVisit, boolean clVisit, 
			boolean drgdisp, boolean dotsAssign, boolean addrUpdate, boolean patVerify, boolean naaf, boolean patReg) {
		listItems[MenuItem.MENU_SUSPECT_ID].setShow(suspectId);
		listItems[MenuItem.MENU_SUSPECT_CONFIRM].setShow(suspectConfirm);
		listItems[MenuItem.MENU_SUSPECT_VERIFY].setShow(suspectVerify);
		listItems[MenuItem.MENU_PATIENT_INFO].setShow(patientInfo);
		listItems[MenuItem.MENU_PATIENT_TB_INFO].setShow(patientTBInfo);
		listItems[MenuItem.MENU_PATIENT_GPS].setShow(patientGPS);
		listItems[MenuItem.MENU_PATIENT_SPUTUM].setShow(patientSputum);
		listItems[MenuItem.MENU_SPUTUM_RESULT].setShow(sputumResult);
		listItems[MenuItem.MENU_REFUSAL].setShow(refusal);
		listItems[MenuItem.MENU_BASELINE_TREATMENT].setShow(baselineTreatment);
		listItems[MenuItem.MENU_FOLLOWUP_TREATMENT].setShow(false);
		listItems[MenuItem.MENU_DRUG_ADMIN].setShow(drugAdmin);
		listItems[MenuItem.MENU_END_FOLLOWUP].setShow(endFollowup);
		listItems[MenuItem.MENU_MR_NUM].setShow(mrAssign);
		listItems[MenuItem.MENU_DFR].setShow(dfr);
		listItems[MenuItem.MENU_SEARCH].setShow(search);
		listItems[MenuItem.MENU_SPUTUM_RESULT_SEARCH].setShow(sputumSearch);
		listItems[MenuItem.MENU_LAB_SEARCH].setShow(labSearch);
		listItems[MenuItem.MENU_FORM_COUNT].setShow(formCount);
		listItems[MenuItem.MENU_CLINICAL_DIAGNOSIS].setShow(cdf);
		listItems[MenuItem.MENU_PCF].setShow(pcf);
		listItems[MenuItem.MENU_PCDF].setShow(pcdf);
		/*listItems[MenuItem.MENU_NEW_CT_SUSPECT].setShow(nctsf);
		listItems[MenuItem.MENU_CONTACT_SPUTUM].setShow(patientSputum);//same permissions as sputum collection form
*/		listItems[MenuItem.MENU_NEW_CT_SUSPECT].setShow(false);
		listItems[MenuItem.MENU_CONTACT_SPUTUM].setShow(false);
		listItems[MenuItem.MENU_PATIENT_FUP_EFFORT].setShow(patFupEffrot);
		//listItems[MenuItem.MENU_PEADS_CLINICAL_VISIT].setShow(peadClVisit);
		listItems[MenuItem.MENU_PEADS_CLINICAL_VISIT].setShow(false);
		listItems[MenuItem.MENU_CLINICAL_VISIT].setShow(clVisit);
		//listItems[MenuItem.MENU_DRUG_DISPENSAL].setShow(drgdisp);
		listItems[MenuItem.MENU_DRUG_DISPENSAL].setShow(drgdisp);
		listItems[MenuItem.MENU_DOTS_ASSIGN].setShow(dotsAssign);
		listItems[MenuItem.MENU_ADDR_UPDATE].setShow(addrUpdate);
		listItems[MenuItem.MENU_PAT_VERIFY].setShow(patVerify);
		listItems[MenuItem.MENU_NO_ACTIVE_FOLLOWUP].setShow(naaf);
		listItems[MenuItem.MENU_PATIENT_REGISTRATION].setShow(patReg);
		

	}
	public void reloadItems() {
		int currentType = getActiveUserType();
		switch (currentType) {
		case UserType.USER_TYPE_CHW:
//boolean suspectId, boolean suspectConfirm, boolean suspectVerify, boolean patientInfo, boolean patientTBInfo, boolean patientGPS, boolean patientSputum, boolean refusal, boolean baselineTreatment, boolean followupTreatment, boolean drugAdmin, boolean endFollowup) {			
			//loadSettings(true,false,false,true,true,true,true,true, true,false, false,true,false,true,true,false,true,true,false,true,false,false,true,true,true,true,true,false,false,false);
			loadSettings(true,false,false,false,false,false,false,false,false, false,false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false, true);
		 	break;
		
		case UserType.USER_TYPE_GP:
			loadSettings(true,true,false,false,false,false,false,false,false,true,false,false,true,false,false,false,false,true,true,true,false,true,true,false,false,true,true,false,false,false,false, true);
			break;
		
		case UserType.USER_TYPE_MONITOR:
			loadSettings(false,false,true,true,true,true,true,true,true,true,false,false,true,true,false,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true, false);
			break;
					
		case UserType.USER_TYPE_ADMIN:
			loadSettings(true, false, false, false, false, false, false, false, false, false, false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false, true);
			break;
		}
	}
	
	public int getItemIndex(String displayName) {
		int index = -1;
		for (int i = 0; i < listItems.length; i++) {
			if (listItems[i].getDisplayName().equals(displayName))  {
				index = i;
				break;
			}
		}
		return index;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getSecondaryURL() {
		return secondaryURL;
	}

	public void setSecondaryURL(String secondaryURL) {
		this.secondaryURL = secondaryURL;
	}

	public String getScanDelay() {
		return scanDelay;
	}

	public void setScanDelay(String scanDelay) {
		this.scanDelay = scanDelay;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public ListItem[] getListItems() {
		return listItems;
	}

	public void setListItems(ListItem[] listItems) {
		this.listItems = listItems;
	}
	
	public ListItem getListItem(int index) {
		return this.listItems[index];
	}
	
	public void setListItem(int index, ListItem item) {
		this.listItems[index] = item;
	}
	
	public void setActiveUserType(int type) {
		this.activeUserType = type;
	}
	
	public int getActiveUserType() {
		return activeUserType;
	}

	/*public Role[] getRoles() {
		return roles;
	}

	public void setRoles(Role[] roles) {
		this.roles = roles;
	}
	
	public Role getRole(int index) {
		return this.roles[index];
	}
	
	public void setRole(int index, Role role) {
		this.roles[index] = role;
	}
	
	public Role getActiveRole() {
		for (int i = 0; i < roles.length; i++) {
			if (roles[i].isActive()) return roles[i];
		}
		return null;
	}
	
	public int getActiveRoleId() {
		int index = -1;
		for (int i = 0; i < roles.length; i++) {
			if (roles[i].isActive()) {
				index = i;
				break;
			}
		}
		return index;		
	}
*/
}
