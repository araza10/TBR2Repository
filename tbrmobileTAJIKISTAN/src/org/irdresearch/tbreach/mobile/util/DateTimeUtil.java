package org.irdresearch.tbreach.mobile.util;

import java.util.Calendar;
import java.util.Date;
//import java.util.GregorianCalendar;

public class DateTimeUtil {

	public static final int BAD_ENTERED_DATE = -2;
	public static final int DATE_CALC_ERROR = -1;
	public static final int DAYS_IN_MONTH = 30;
	
	public static String getDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		
		
		
		String year = new Integer(c.get(Calendar.YEAR)).toString();
		String month =  new Integer(c.get(Calendar.MONTH) + 1).toString();
		String date =  new Integer(c.get(Calendar.DATE)).toString();
		
		String dateString = "";
		
		dateString = date.length()==2 ? dateString + date : dateString + "0" + date;
		dateString += "/";
		dateString = month.length()==2 ? dateString + month : dateString + "0" + month;
		dateString += "/" + year;
		
		return dateString;
	}

	public static String getDate(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		
		String year = new Integer(c.get(Calendar.YEAR)).toString();
		String month =  new Integer(c.get(Calendar.MONTH) + 1).toString();
		String date =  new Integer(c.get(Calendar.DATE)).toString();
		
		String dateString = "";
		
		dateString = date.length()==2 ? dateString + date : dateString + "0" + date;
		dateString += "/";
		dateString = month.length()==2 ? dateString + month : dateString + "0" + month;
		dateString += "/" + year;
		
		return dateString;
	}
	
	public static String getTime() {
		String timeString = "";
		Calendar c = Calendar.getInstance();
		
		c.setTime(new Date(System.currentTimeMillis()));
		String hour = new Integer(c.get(Calendar.HOUR_OF_DAY)).toString();
		System.out.println(hour);
		String minute =  new Integer(c.get(Calendar.MINUTE)).toString();
		String second =  new Integer(c.get(Calendar.SECOND)).toString();
				
		timeString = hour.length()==2 ? timeString + hour : timeString + "0" + hour;
		timeString += ":";
		timeString = minute.length()==2 ? timeString + minute : timeString + "0" + minute;
		timeString += ":";
		timeString = second.length()==2 ? timeString + second : timeString + "0" + second;
		
		
		return timeString;
	}
	
	public static String getDateTime(Date d) {
		String dateTimeString = "";
		Calendar c = Calendar.getInstance();
		
		c.setTime(d);
		String year = new Integer(c.get(Calendar.YEAR)).toString();
		String month =  new Integer(c.get(Calendar.MONTH) + 1).toString();
		String date =  new Integer(c.get(Calendar.DATE)).toString();
		String hour = new Integer(c.get(Calendar.HOUR_OF_DAY)).toString();
		String minute =  new Integer(c.get(Calendar.MINUTE)).toString();
		String second =  new Integer(c.get(Calendar.SECOND)).toString();
		
		dateTimeString = date.length()==2 ? dateTimeString + date : dateTimeString + "0" + date;
		dateTimeString += "/";
		dateTimeString = month.length()==2 ? dateTimeString + month : dateTimeString + "0" + month;
		dateTimeString += "/" + year;
		dateTimeString += " ";
		dateTimeString = hour.length()==2 ? dateTimeString + hour : dateTimeString + "0" + hour;
		dateTimeString += ":";
		dateTimeString = minute.length()==2 ? dateTimeString + minute : dateTimeString + "0" + minute;
		dateTimeString += ":";
		dateTimeString = second.length()==2 ? dateTimeString + second : dateTimeString + "0" + second;
		
		
		return dateTimeString;
		
	}
	
	public static boolean isDateInFuture(Date date) {
		boolean result = false;
		
		Date nowDate = new Date();
		if(date.getTime() > nowDate.getTime())
			return true;
		
		return result;
	}
	
	public static int calculateMonthOfTreatment(String treatmentStartDate, Date enteredDate){
		int monthOfTreatment  = -1;
		System.out.println ("TX START:------>" + treatmentStartDate);
		//Date txStart = null;
		
		if(treatmentStartDate==null || treatmentStartDate.equals("N/A")) {
			return 0;
		}
		
		String year = treatmentStartDate.substring(0,4);
		String month = treatmentStartDate.substring(5,7);
		String date = treatmentStartDate.substring(8,10);
		System.out.println("---------");
		System.out.println(year);
		System.out.println(month);
		System.out.println(date);
		System.out.println("---------");
		
		Calendar gcTx = Calendar.getInstance();
		Calendar gcNow = Calendar.getInstance();
		try {
		//Calendar gcTx = Calendar.getInstance();
		gcTx.set(Calendar.YEAR, Integer.parseInt(year));
		gcTx.set(Calendar.MONTH, Integer.parseInt(month)-1);
		gcTx.set(Calendar.DATE, Integer.parseInt(date));
		
		
		//Calendar gcNow = Calendar.getInstance();
		gcNow.setTime(enteredDate);
		}
		
		catch(Exception e) {
			return DATE_CALC_ERROR;
		}
		
		long diff = gcNow.getTime().getTime() - gcTx.getTime().getTime();
		if(diff < 0)
			return BAD_ENTERED_DATE;
		
		double diffInSeconds = diff/1000;
		//System.out.println(diffInSeconds);
		double diffInMinutes = diffInSeconds/60;
		//System.out.println(diffInMinutes);
		double diffInHours = diffInMinutes/60;
		//System.out.println(diffInHours);
		double diffInDays = diffInHours/24;
		System.out.println("----->" + new Double(diffInDays).toString());
		//double diffInMonths = diffInDays/DAYS_IN_MONTH;
		//System.out.println(diffInMonths);
		
		if(diffInDays >=0 && diffInDays <= 23)
			return 0;
		if(diffInDays >=24 && diffInDays <= 53)
			return 1;
		if(diffInDays >=54 && diffInDays <= 83)
			return 2;
		if(diffInDays >=84 && diffInDays <= 113)
			return 3;
		if(diffInDays >=114 && diffInDays <= 143)
			return 4;
		if(diffInDays >=144 && diffInDays <= 23)
			return 5;
		if(diffInDays >=174 && diffInDays <= 203)
			return 6;
		if(diffInDays >=204 && diffInDays <= 233)
			return 7;
		if(diffInDays >=234 && diffInDays <= 263)
			return 8;
		if(diffInDays >=264 && diffInDays <= 293)
			return 9;
		if(diffInDays >=294 && diffInDays <= 323)
			return 10;

		
		/*Double mnthDbl = new Double(diffInMonths);
		
		monthOfTreatment = mnthDbl.intValue();
		*/
		return monthOfTreatment;
	}
		
	/*
	 * Calendar c = Calendar.getInstance();
			c.setTime(new Date(System.currentTimeMillis()));
			int age = -1;
			String ageString = ageField.getString();
			
			if(ageString!=null && ageString.length()!=0) {
				age = Integer.parseInt(ageString);
				c.set(Calendar.YEAR, c.get(Calendar.YEAR)-age);
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
				dobField.setDate(c.getTime());
			}
	 */

}
