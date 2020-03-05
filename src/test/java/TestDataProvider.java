import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider
	public Object[][] empData() {

		Object[][] data = new Object[2][3];
		data[0][0] = "abc";
		data[0][1] = "50000";
		data[0][2] = "21";

		data[1][0] = "xyz";
		data[1][1] = "30000";
		data[1][2] = "31";

		return data;
	}

	@DataProvider
	public static Object[][] empDataFromExcel() throws IOException {

		int rowCount = 0;
		int colCount = 0;
		List<String> records = new ArrayList<String>();

		Workbook workbook = new XSSFWorkbook(
				"C:\\EclipseWs\\MyRestProject\\src\\test\\resources\\Data.xlsx");
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {

			XSSFRow row = (XSSFRow) rowIterator.next();
			rowCount++;
			colCount = 0;
			Iterator<Cell> cellIterator = row.iterator();

			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();
				colCount++;
				switch (cell.getCellType()) {

				case NUMERIC:
					System.out.println("Numeric");

					int val = (int) cell.getNumericCellValue();
					records.add(String.valueOf(val));
					break;
				case STRING:
					System.out.println(cell.getStringCellValue());

					records.add(cell.getStringCellValue());
					break;

				}
			}
		}
		System.out.println("row:" + rowCount);
		System.out.println("col:" + colCount);
		Object[][] data = generateDataArray(records, rowCount, colCount);

		return data;
	}

	private static Object[][] generateDataArray(List<String> records, int rowCount,
			int columnCount) {
		int k = 0;
		String[][] xlsData = new String[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				xlsData[i][j] = records.get(k++);
			}
		}
		return xlsData;
	}

}
