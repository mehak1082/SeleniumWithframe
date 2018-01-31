package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	Properties ps;

	public ConfigReader() {

		try {
			File fs = new File(System.getProperty("user.dir") + "\\Configuration\\Config.property");
			FileInputStream fopen = new FileInputStream(fs);
			ps = new Properties();
			ps.load(fopen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Config Reader for: " + e.getMessage());
		}

	}

	public String getUrl() {
		return ps.getProperty("Url");

	}

	public String getDataFilePath() {
		return ps.getProperty("ExcelPath");

	}

	public String getSheet() {
		return ps.getProperty("SheetName");

	}

	public String getChDriver() {

		String driverRef = ps.getProperty("ChDriver");
		return driverRef;

	}

	public String getBrowser() {

		String browser = ps.getProperty("Browser");
		return browser;

	}

	public String getChromePath() {

		String driverPath = ps.getProperty("ChPath");
		return driverPath;

	}

	public String getFirefoxPath() {
		// TODO Auto-generated method stub
		String driverPath = ps.getProperty("FfPath");

		return driverPath;
	}

	public String getFfDriver() {
		// TODO Auto-generated method stub
		String driverRef = ps.getProperty("FfDriver");

		return driverRef;
	}

	public String getExpPath() {
		// TODO Auto-generated method stub
		String driverPath = ps.getProperty("ExpPath");

		return driverPath;
	}

	public String getExpDriver() {
		// TODO Auto-generated method stub
		String driverRef = ps.getProperty("ExpDriver");

		return driverRef;
	}
}
