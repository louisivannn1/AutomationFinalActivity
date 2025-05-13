package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    public static String[] getTestData(String scenario) {

        String filePath = "C:\\Users\\Ivan\\Downloads\\testdata.xlsx"; 
        String sheetName = "LoginData"; // Sheet name (ensure this is correct)

       
        String[] testData = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found in the Excel file.");
            }

            
            int rows = sheet.getPhysicalNumberOfRows();

            // Loop through the rows and look for the matching scenario in the first column
            for (int i = 1; i < rows; i++) { 
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null &&
                        row.getCell(0).getStringCellValue().equalsIgnoreCase(scenario)) {
                    // Extract the test data (username, password, expected error)
                    String username = (row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : "";
                    String password = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "";
                    String expectedError = (row.getCell(3) != null) ? row.getCell(3).getStringCellValue() : "";

                    testData = new String[]{username, password, expectedError};
                    break;
                }
            }

           
            if (testData == null) {
                System.out.println("No data found for scenario: " + scenario);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return testData; 
    }
}
