package com.himanishkaushal.greenpad;

public final class AppAssistant {
	
	private static final String JAN = "Jan",
								FEB = "Feb",
								MAR = "Mar",
								APR = "Apr",
								MAY = "May",
								JUN = "Jun",
								JUL = "Jul",
								AUG = "Aug",
								SEP = "Sep",
								OCT = "Oct",
								NOV = "Nov",
								DEC = "Dec";
	
	// the seperator between year, month and day when displaying the date
	private static final String DASH = " - ";

	public static String getDateForDisplay(int year, int month, int day) {
		month++;
		return year + DASH + month + DASH + day;
	}
	
	public static int getDateFromDisplayString(String date) {
		
		date = date.replace(" - ", "");
		return Integer.parseInt(date);
	}
	
	public static String getDateForListDisplay(int year, int month, int day) {
		return day + " " + getMonthName(month) + ", " + year;
	}
	
	private static String getMonthName(int month) {
		
		switch(month + 1) {
		case 1:
			return JAN;
		case 2:
			return FEB;
		case 3: 
			return MAR;
		case 4:
			return APR;
		case 5:
			return MAY;
		case 6:
			return JUN;
		case 7: 
			return JUL;
		case 8:
			return AUG;
		case 9:
			return SEP;
		case 10:
			return OCT;
		case 11:
			return NOV;
		case 12:
			return DEC;
		}
		
		return null;
	}
	
	

}
