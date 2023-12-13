package org.iit.healthcare.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AppLibrary {
	public static String getDate(String format)
	{
		//Date Func
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 3);
		Date d  = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(d);
		System.out.println("Date:::::::::::"+ date);
		return date;
	}
	public void readXLSX(String fileName,String sheetName)
	{
		
	}
	public static String generateRandomString(String str)
	{
		
		Random random = new Random();
		char upperCase = (char)(65 + random.nextInt(26));
		char lowerCase = (char)(97+random.nextInt(26));
		String randomString = str+upperCase+lowerCase;
		System.out.println("RandomString:: " + randomString);
		return randomString;
		
	}

}
