package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pages.Login;
import pages.UserRegistration;

public class ExcelReader {
	XSSFWorkbook WkBk;
	XSSFSheet WkbkSheet;

	UserRegistration objRegister;
	Login objLogin;

	public ExcelReader(String ExcelPath) {

		FileInputStream ExcelFile;
		try {

			ExcelFile = new FileInputStream(ExcelPath);
			WkBk = new XSSFWorkbook(ExcelFile);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}
	
	public List<String> getSheets() {

		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < WkBk.getNumberOfSheets(); i++) {
			sheetNames.add(WkBk.getSheetName(i));
			System.out.println("Return from ExcelRader with sheets");
		}
		return sheetNames;

	}

	public ArrayList<SheetDataBean> getData() throws InterruptedException {
		// WkbkSheet = WkBk.getSheet(sheetName);

		StackTraceElement[] element = new Throwable().getStackTrace();

		String calleeMethod = element[0].getMethodName();
		String callerMethodName = element[1].getMethodName();
		String callerClassName = element[1].getClassName();

		System.out.println("CallerClassName=" + callerClassName + " , Caller method name: " + callerMethodName);
		System.out.println("Callee method name: " + calleeMethod);

		Sheet datatypeSheet = null;
		if ("UserRegistration".equals(callerMethodName)){
			
			datatypeSheet = WkBk.getSheetAt(0);
			System.out.println("Taking sheet 1" + datatypeSheet);
		}
		else{
		
				
				//	objLogin = new Login(driver);
					System.out.println("Taking sheet 2");
					datatypeSheet = WkBk.getSheetAt(1);
		}

		ArrayList<SheetDataBean> list = new ArrayList<SheetDataBean>();
		for (Row r : datatypeSheet) {
			
			SheetDataBean shb = new SheetDataBean();
			if (r.getRowNum() == 0) {

				continue; // just skip the rows if row number is 0 or 1

			}

			if ("UserRegistration".equals(callerMethodName)) {
				//	objRegister = new UserRegistration(driver);
				System.out.println("Fetching data from sheet 1");
				

				Cell fname = r.getCell(0);
				Cell lname = r.getCell(1);
				Cell uname = r.getCell(2);
				Cell pwd = r.getCell(3);
				shb.setFname(cellToString((XSSFCell) fname));
				shb.setLname(cellToString((XSSFCell) lname));
				shb.setUname(cellToString((XSSFCell) uname));
				shb.setPwd(cellToString((XSSFCell) pwd));
			
				
				/*
				if (uname.getCellType() == Cell.CELL_TYPE_STRING) {
					shb.setUname(uname.getStringCellValue());

				}
				if (pwd.getCellType() == Cell.CELL_TYPE_STRING) {
					shb.setPwd(pwd.getStringCellValue());
				} */
			
			
			} else {
				
			//	objLogin = new Login(driver);
				System.out.println("Fetching data from sheet 2");
				
				Cell uname = r.getCell(0);
				Cell pwd = r.getCell(1);
				shb.setUname(cellToString((XSSFCell) uname));
				shb.setPwd(cellToString((XSSFCell) pwd));
			}

			list.add(shb);
		}

		return list;

	}

	public int getRowCnt(String sheetName) {
		WkbkSheet = WkBk.getSheet(sheetName);
		int row = WkbkSheet.getLastRowNum();
		row = row + 1;

		return row;

	}
	
	public ArrayList getFlightData() throws InterruptedException {
		// WkbkSheet = WkBk.getSheet(sheetName);

	//	flightBook = new FlightReservation(driver);
		Sheet datatypeSheet = WkBk.getSheetAt(2);

		ArrayList list = new ArrayList();
		for (Row r : datatypeSheet) {
			SheetDataBean shb = new SheetDataBean();
			if (r.getRowNum() == 0) {
				continue; // just skip the rows if row number is 0 or 1

			}
			Cell tripType = r.getCell(0);
			Cell passengers = r.getCell(1);
			Cell departurePort = r.getCell(2);
			Cell travelMonth = r.getCell(3);
			Cell travelDate = r.getCell(4);
			Cell arrivalAt = r.getCell(5);
			Cell returnMonth = r.getCell(6);
			Cell returnDate = r.getCell(7);
			Cell classPreference = r.getCell(8);
			Cell airlinePreference = r.getCell(9);

			if (tripType.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setTrip(tripType.getStringCellValue());

			}

			/*if (passengers.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setPassengerCount(passengers.getStringCellValue());

			}*/
			shb.setPassengerCount(cellToString((XSSFCell) passengers));


			if (departurePort.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setDepartFrom(departurePort.getStringCellValue());

			}

			if (travelMonth.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setDepartureMonth(travelMonth.getStringCellValue());

			}
			if (travelDate.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setDepartureDate(travelDate.getStringCellValue());

			}
			if (arrivalAt.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setArrivalPort(arrivalAt.getStringCellValue());

			}
			/*if (returnMonth.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setReturnMonth(returnMonth.getStringCellValue());

			}			
			
			if (returnDate.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setReturnDate(returnDate.getStringCellValue());

			}*/
			shb.setReturnMonth(cellToString((XSSFCell) returnMonth));
			shb.setReturnDate(cellToString((XSSFCell) returnDate));

			
			if (classPreference.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setTravelClass(classPreference.getStringCellValue());

			}
			if (airlinePreference.getCellType() == Cell.CELL_TYPE_STRING) {
				shb.setAirline(airlinePreference.getStringCellValue());

			}			

			list.add(shb);
		}

		return list;

	}
	
	public static String[][] FetchDataFromExcel(String path) throws IOException {

		// Change File name as per file location on machine
		// File excel= new File("F:\\Nilesh Selenium 14th May\\Data.xls");
		File excel = new File(path);
		FileInputStream fis = new FileInputStream(excel);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Frames");

		int rowNum = ws.getLastRowNum() + 1;
		int colNum = ws.getRow(0).getLastCellNum();

		String[][] data = new String[rowNum][colNum];

		for (int i = 0; i < rowNum; i++) {
			XSSFRow row = ws.getRow(i);
			for (int j = 0; j < colNum; j++) {
				XSSFCell cell = row.getCell(j);
				String value = cellToString(cell);

				data[i][j] = value;
			}

		}
		wb.close();
		fis.close();
		return data;

	}

	@SuppressWarnings("deprecation")
	public static String cellToString(XSSFCell cell) {
		// int type;
		Object result;
		String strReturn = null;
		if (cell == null) {
			strReturn = "";
		} else {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: // Numeric
				result = cell.getNumericCellValue();
				strReturn = result.toString();
				break;
			case Cell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				strReturn = result.toString();
				break;
			default:
				strReturn = null;
			}
		}
		return strReturn;

	}

}
