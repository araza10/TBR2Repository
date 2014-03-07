package org.irdresearch.tbreach.mobile.util;

public class StringUtil
{
	public static String replace(String oldStr, String newStr, String inString) {
	    int start = inString.indexOf(oldStr);
	    if (start == -1) {
	      return inString;
	    }
	    StringBuffer sb = new StringBuffer();
	    sb.append(inString.substring(0, start));
	    sb.append(newStr);
	    sb.append(inString.substring(start+oldStr.length()));
	    return sb.toString();
	  }
}
