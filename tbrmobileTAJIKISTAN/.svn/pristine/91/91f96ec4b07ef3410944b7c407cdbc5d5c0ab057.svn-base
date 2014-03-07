package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;
import java.util.Hashtable;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDletStateChangeException;
import org.irdresearch.tbreach.mobile.model.Settings;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.Tbr2Version;
import org.irdresearch.tbreach.mobile.constants.UserType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;


public class LoginForm extends BaseTBReachForm implements CommandListener {
	
	//private TBReachMainMIDlet tbrMidlet;
	private TextField username;
	private TextField password;
	private Command cmdOK;
	private Command cmdExit;
	private Settings s;
	
	public LoginForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		username = new TextField("Username", "", 50, TextField.ANY);
		password = new TextField("Password", "", 50, TextField.PASSWORD);
		cmdOK = new Command("Ха", Command.OK, 1);
		cmdExit = new Command("Баромад", Command.BACK, 0);
		
	}
	
	public boolean validate ()
	{
		if (username == null || username.getString ().trim ().length () == 0)
		{
			tbrMidlet.showAlert ("Enter Username", null);
			return false;
		}
		else if (password == null || password.getString ().trim ().length () == 0)
		{
			tbrMidlet.showAlert ("Enter Password", null);
			return false;
		}
		return true;
	}

	public void commandAction(Command c, Displayable d) {
		System.out.println("entering commandaction");
		if (c == cmdOK) {
			
			if(validate())
			{
			
			//BEGIN DUMMY LOGIN CODE
/*			if(username.getString()!=null && username.getString().toUpperCase().equals("G-DUMMY-01")) {
				if(password.getString()!=null && password.getString().equals("g-dummy-01")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_GP);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_GP);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else if(username.getString()!=null && username.getString().toUpperCase().equals("C-DUMMY-01")) {
				if(password.getString()!=null && password.getString().equals("c-dummy-01")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_CHW);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_CHW);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else if(username.getString()!=null && username.getString().toUpperCase().equals("M-DUMMY-01")) {
				if(password.getString()!=null && password.getString().equals("m-dummy-01")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_MONITOR);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_MONITOR);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else if(username.getString()!=null && username.getString().toUpperCase().equals("ADMIN")) {
				if(password.getString()!=null && password.getString().equals("admin")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_ADMIN);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_ADMIN);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else {
				tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
			}*/
			
			//END DUMMY LOGIN CODE
			
			//BEGIN COMMENTED ACTUAL LOGIN CODE
			String request = createRequestPayload();
			Hashtable model = tbrMidlet.sendToServer(request);
			if (model != null) {

				if ((String) model.get("status") != null && ((String) model.get("status")).equals("error")) {
						
						tbrMidlet.showAlert((String) model.get("msg"), null);

				}
				
				else if((String) model.get("status") != null && ((String) model.get("status")).equals("tajikerror"))
				{
					tbrMidlet.showAlert( "Сана е вакт дар телефони шумо нодуруст аст. Ислох кунед ва такроран дароед" , null );
				}
				
				else if((String) model.get("status") != null && ((String) model.get("status")).equals("ErrorLogging"))
				{
					tbrMidlet.showAlert( "Воридшави нодуруст аст. Такрор кунед" , null );
				}
				
				else if((String) model.get("status") != null && ((String) model.get("status")).equals("AccountSuspended"))
				{
					tbrMidlet.showAlert( "Ин инстифодабаранда махкам карда шуд" , null );
				}
				
				else if((String) model.get("status") != null && ((String) model.get("status")).equals("InvalidUser"))
				{
					tbrMidlet.showAlert( "Хатогии номи истифодабаранда еки парол. Такрор кунед" , null );
				}

				else {
					

					String role = (String) model.get("role");
					String uid = (String)model.get("uid");
					String user = username.getString();
					System.out.println(uid);
					if (role.equals("LABTECH")) {
						
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_CHW);
					}

					else if (role.equals("CHW")) {
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_CHW);

					}

					else if (role.equals("MONITOR")) {
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_MONITOR);
					}

					else if (role.equals("ADMIN")) {
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_ADMIN);
					}
				}
			}

			else
				tbrMidlet.showAlert(ErrMsg.LOGIN_ERROR, null);
			//END COMMENTED ACTUAL LOGIN CODE 
			 
			 
			}
		}
		
		else if(c==cmdExit) {
			System.out.println("entering handler");
			deleteAll();
			
			try {
				tbrMidlet.destroyApp(false);
				tbrMidlet.notifyDestroyed();
				
			}
			catch(MIDletStateChangeException e) {
				System.out.println("BAAAA->" + e.toString());
			}
			System.out.println("leaving handler");
			
		}
			
	}
	
    public void init() {
		setCommandListener(this);

    	addCommand(cmdOK);
    	addCommand(cmdExit);
    	append(username);
    	append(password);
    }
    
    private String createRequestPayload() {
    	
    	String request = null;
    	
    	String usernameString = username.getString();
    	String passwordString = password.getString();
    	
    	request = "type=lg&";
    	request += "username=" + usernameString + "&";
    	request += "phoneTime=" + DateTimeUtil.getDateTime (new Date()) + "&";
    	request += "password=" + passwordString;
    	
    	return request;
    }

}
