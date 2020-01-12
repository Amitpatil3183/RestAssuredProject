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
import com.employeeapi.utilities.EmpDataProvider;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_Post_Employees_Records extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	
	//EmpDataProvider dataprovider = new EmpDataProvider();

	@Test(dataProvider = "empdataprovider")
	public void createEmployees(String ename, String eage, String esal) throws InterruptedException {
		logger.info("*********Started TC003_Post_Employee_Record **********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

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

	
	  @DataProvider(name = "empdataprovider")
	  String[][] getEmpTestData() throws IOException {
	  
	  String path = System.getProperty("user.dir") +
	  "/src/test/java/com/employeeapi/utilities/EmpTestData.xlsx";
	  
	  int rownum = XLUtils.getRowCount(path, "Sheet1");
	  
	  int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
	  
	  String EmpTestData[][] = new String[rownum][colcount];
	  
	  for (int i = 1; i <= rownum; i++) { for (int j = 0; j < colcount; j++) {
	  
	  EmpTestData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j); }
	  
	  }
	  
	  return (EmpTestData); }
	 
	 

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC003_Post_Employee_Record **********");
	}

}
