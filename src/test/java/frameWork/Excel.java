package frameWork;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	Workbook wb;

	public Excel(String filepath) {
		try {
			if (filepath.endsWith(".xls"))
				wb = new HSSFWorkbook(new FileInputStream(filepath));
			else if (filepath.endsWith(".xlsx"))
				wb = new XSSFWorkbook(new FileInputStream(filepath));
		} catch (Exception e) {
			System.out.println("Error while reading file " + e.getMessage());
		}
	}

	public String readData(String sheetName, int row, int col) {
		String data = wb.getSheet(sheetName).getRow(row).getCell(col).toString();
		return data;
	}

	public int getLastRowNum(String sheetName) {
		return wb.getSheet(sheetName).getLastRowNum();
	}
}
