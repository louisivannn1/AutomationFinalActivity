package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class CheckoutPageUtil {
    public static String[] getCheckoutTestData(String scenario) {
    	   String filePath = "C:\\Users\\Ivan\\Downloads\\testdata.xlsx";  // Update with the correct path
           String sheetName = "CheckoutPageData"; 

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new IllegalArgumentException("Sheet not found");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(scenario)) {
                    String firstName = row.getCell(1) != null ? row.getCell(1).toString() : "";
                    String lastName = row.getCell(2) != null ? row.getCell(2).toString() : "";
                    String zipCode = row.getCell(3) != null ? row.getCell(3).toString() : "";
                    return new String[]{firstName, lastName, zipCode};
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return new String[]{"", "", ""}; // Default empty
    }
}
