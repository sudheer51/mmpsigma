package org.iit.healthcare.mmpsigma;

import java.util.HashMap;

import org.iit.healthcare.patientmodule.HomePage;
import org.iit.healthcare.patientmodule.LoginPage;
import org.iit.healthcare.patientmodule.ScheduleAppointmentPage;
import org.iit.healthcare.util.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests_MD_DP extends BaseClass {

	 
	@Parameters({"doctorName"})
	@Test
	public void validateScheduleAppointment(String doctorName)
	{
		 
		LoginPage lPage = new LoginPage(driver);
		lPage.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage homePage = new HomePage(driver);
		homePage.navigateToModule("Schedule Appointment");
		ScheduleAppointmentPage schedulePage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expectedHMap =schedulePage.bookAppointment(doctorName);
		HashMap<String,String> actualHMap =homePage.fetchPatientPortalValues();
		Assert.assertEquals(actualHMap, expectedHMap);

	}
	@Test
	public void a1()
	{
		
	}

}
