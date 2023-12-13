package org.iit.healthcare.util;

import java.util.Random;

public class Practise {
	
	int j ;

	public static void main(String[] args) {
		
		Random random = new Random();
		char upperCase = (char)(65 + random.nextInt(26));
		char lowerCase = (char)(97+random.nextInt(26));
		String randomString = "FNAME"+upperCase+lowerCase;
		System.out.println("RandomString:: " + randomString);
		
	}
	public void a1()
	{
		 j =10;
	}
	public void a2()
	{
		System.out.println(j);
	}

}
