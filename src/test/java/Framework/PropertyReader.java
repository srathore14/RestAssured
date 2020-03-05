package Framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private Properties prop = new Properties();

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void readProperties() throws IOException {

		InputStream stream = new FileInputStream(
				new File("C:\\EclipseWs\\MyRestProject\\src\\test\\resources\\project.properties"));

		prop.load(stream);
	}
}
