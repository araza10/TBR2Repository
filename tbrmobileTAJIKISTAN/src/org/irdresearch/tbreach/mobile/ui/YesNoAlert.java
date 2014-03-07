package org.irdresearch.tbreach.mobile.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;

public class YesNoAlert extends Form implements CommandListener {
	private Command yesCommand, noCommand;
	TBReachMainMIDlet tbrMidlet;
	Displayable yesDisplayable;
	Displayable noDisplayable;
	
	public YesNoAlert(String title, String alertString, TBReachMainMIDlet tbrMidlet, Displayable yesDisplayable, Displayable noDisplayable) {
		super(title);
		this.tbrMidlet = tbrMidlet;
		this.yesDisplayable = yesDisplayable;
		this.noDisplayable = noDisplayable;
		
		yesCommand = new Command("Yes", Command.SCREEN, 1);
		noCommand = new Command("No", Command.SCREEN, 1);
		
		append(alertString);
		addCommand(yesCommand);
		addCommand(noCommand);
		setCommandListener(this);
		
	}

	public void commandAction(Command cmd, Displayable d) {
		// TODO Auto-generated method stub
		if (cmd == yesCommand) {
			tbrMidlet.setDisplay(yesDisplayable);
		} else if (cmd == noCommand) {
			tbrMidlet.setDisplay(noDisplayable);
		}
	}
	
}

