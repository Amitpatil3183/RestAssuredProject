package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;

public class TC006_Get_Request_Authorization extends TestBase {
	
	
	@BeforeClass
	void AutorizationTest() throws InterruptedException
	{
	
	logger.info("*********Started TC001_Get_All_Employees **********");
		
	RestAssured.baseURI="http://restapi.demoqa.com/autentication/CheckForAuthentication";
	
	//Basic Authentication
	PreemptiveBasicAuthScheme authscheme = 	new PreemptiveBasicAuthScheme();
	authscheme.setUserName("ToolsQA");
	authscheme.setPassword("TestPassword");

	
	RestAssured.authentication = authscheme;
	
	httpRequest = RestAssured.given();
	
	response = httpRequest.request(Method.GET,"/");
	
	Thread.sleep(5);
	
	//print response in console
  	String responsebody =response.getBody().asString();
  	System.out.println("Responce Body is:" +responsebody);
  	
	}
	
	
	@Test
	void checkStatusCode()
	{
		logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); //200
		Assert.assertEquals(statusCode, 200);
		
	}
}
