package org.irdresearch.tbreach.mobile.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.microedition.io.Connector;
import javax.microedition.io.OutputConnection;
import javax.microedition.io.file.FileConnection;

import org.irdresearch.tbreach.mobile.ui.TBReachMainMIDlet;

public class FileWriter {
	
	public static void rite(String msg, TBReachMainMIDlet tbrMidlet) {
		try {
			FileConnection filecon = (FileConnection)
	         Connector.open("file:///SDCard/mynewfile.txt");
	      // Always check whether the file or directory exists.
	      // Create the file if it doesn't exist.
	      if(!filecon.exists()) {
	         filecon.create();
	      }
	      
	      OutputStream out = filecon.openOutputStream();
	      PrintStream ps = new PrintStream(out);
	      ps.println(msg);
	      ps.flush();
	      
	      out.close();
	      
		}
		
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
