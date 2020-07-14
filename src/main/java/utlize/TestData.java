package utlize;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="inputData")
	public Object[][] getData()
	{
		Object[][] obj =new Object[][]
				{
			
			{"shuv"},{"@#hello"}
				};
				
				return obj;
	}
	
}