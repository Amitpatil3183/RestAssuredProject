package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_009_Get_Single_User_Record extends TestBase {
	
	
	
	
	@BeforeClass
	void getSingleRecord() throws InterruptedException
	{
	
	logger.info("*********Started TC001_Get_Single_Users **********");
		
	RestAssured.baseURI = "https://reqres.in/";
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET,"/api/users/2");
	
	Thread.sleep(5);
	}
	
	
	@Test
	void checkResposeBody()
	{
		logger.info("***********  Checking Respose Body **********");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status Code is ==>" + statusCode); //200
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	void checkserverType()
	{
		logger.info("***********  Checking Server Type **********");
		
		String serverType = response.header("Server");
		logger.info("Server Type is =>" +serverType); 
		Assert.assertEquals(serverType, "cloudflare");
	
	}
	
	
	

}
