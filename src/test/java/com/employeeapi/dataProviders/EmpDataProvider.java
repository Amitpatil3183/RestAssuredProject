package com.employeeapi.dataProviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.employeeapi.utilities.*;

public class EmpDataProvider {
	
	
	@DataProvider(name="empdataprovider")
	
	String [][] getEmpTestData() throws IOException {
		
	String path = System.getProperty("user.dir")+"/src/test/java/com/employeeapi/utilities/EmpTestData.xlsx";
	
	int rownum= XLUtils.getRowCount(path, "Sheet1");
	
	int colcount= XLUtils.getCellCount(path, "Sheet1", 1);
	
	String EmpTestData[][]= new String[rownum][colcount];
	
	for (int i=1; i<=rownum; i++) 
	{
	for (int j=0; j <colcount; j++)
	{
		
		EmpTestData[i-1][j] = XLUtils.getCellData(path,"Sheet1",i,j);
	}
		
	}
		
	return(EmpTestData);
	}


}
