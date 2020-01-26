package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;


import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_010_Post_Single_User_Record extends TestBase{
	
	
@Test(dataProvider = "userdataprovider", dataProviderClass = com.employeeapi.dataProviders.UserDataProvider.class)
	
	public void createuser(String ename, String ejob) throws InterruptedException {
		//logger.info("*********Started TC010_Create_user_Record **********");

		RestAssured.baseURI = "https://reqres.in/";
		httpRequest = RestAssured.given();///to set all the variables mentioned which wehavee set in rest assurued to http request

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", ename);
		requestParams.put("job", ejob);
		

		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/api/users");

		String responseBody = response.getBody().asString();

		System.out.println("Response body is:" + responseBody);

/*		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(eage), true);
		Assert.assertEquals(responseBody.contains(esal), true);*/

		Thread.sleep(5000);

		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 201);

		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
		
				
		String serverType = response.header("Server");
		logger.info("Server Type is =>" +serverType); 
		Assert.assertEquals(serverType, "cloudflare");
		
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		

	}

	

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC010_Create_User_Record **********");
	}

}
	
	


