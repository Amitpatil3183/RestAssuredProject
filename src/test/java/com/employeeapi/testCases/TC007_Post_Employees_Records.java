	package com.employeeapi.testCases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.XLUtils;
import com.employeeapi.utilities.*;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_Post_Employees_Records extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	
	

	@Test(dataProvider = "empdataprovider", dataProviderClass = com.employeeapi.dataProviders.EmpDataProvider.class)
	
	public void createEmployees(String ename, String eage, String esal) throws InterruptedException {
		logger.info("*********Started TC003_Post_Employee_Record **********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();///to set all the variables mentioned which wehavee set in rest assurued to http request

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", ename);
		requestParams.put("salary", esal);
		requestParams.put("age", eage);

		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");

		String responseBody = response.getBody().asString();

		System.out.println("Response body is:" + responseBody);

		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(eage), true);
		Assert.assertEquals(responseBody.contains(esal), true);

		Thread.sleep(5000);

		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 200);

		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC003_Post_Employee_Record **********");
	}

}
