package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="testdata")
	public String[][] testData() throws IOException{
		String path= "./src/test/resources/testdata/LoginData.xlsx";
		ReadExcel excel = new ReadExcel(path);
		
		int rows=excel.getRowCount("Sheet1");
		int cells=excel.getCellCount("Sheet1", 1);
		String loginData[][]= new String[rows][cells];
		
		for(int i=1;i<=rows;i++) {
			for(int j=0;j<cells;j++) {
				loginData[i-1][j]=excel.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
	
	//below also works but used List
	/*
	@DataProvider(name="testdata")
	public String[][] testData() throws IOException {
	    String path = "./src/test/resources/testdata/LoginData.xlsx";
	    ReadExcel excel = new ReadExcel(path);

	    int rows = excel.getRowCount("Sheet1");
	    int cells = excel.getCellCount("Sheet1", 1);

	List<String[]> dataList = new ArrayList<>();

    for (int i = 1; i <= rows; i++) {
        // Read first cell (email) dynamically to check if row has data
        String firstCell = excel.getCellData("Sheet1", i, 0);

        // Filter out ghost/blank rows dynamically
        if (firstCell != null && !firstCell.trim().isEmpty()) {
            String[] rowData = new String[cells];
            
            // Nested loop to dynamically extract all column values (0, 1, 2, ...)
            for (int j = 0; j < cells; j++) {
                rowData[j] = excel.getCellData("Sheet1", i, j);
            }
            
            dataList.add(rowData);
        }
    }

	    // Convert dynamic list to 2D array expected by TestNG
	    return dataList.toArray(new String[0][0]);
	}*/
	
	
	
	


}
