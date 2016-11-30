package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	public static String URI;
	public static String CONSUMER_KEY;
	public static String CONSUMER_SECRET;
	public static String TOKEN;
	public static String TOKEN_SECRET;

	public Configuration(){
		loadProperties();
	}

	public void loadProperties(){
		Properties prop=new Properties();
		try {
			FileReader read=new FileReader(new File(String.format("%s/src/resources/config.properties", System.getProperty("user.dir"))));
			prop.load(read);
			URI=prop.getProperty("Uri");
			
				CONSUMER_KEY=prop.getProperty("key");
				CONSUMER_SECRET=prop.getProperty("secret");
				TOKEN=prop.getProperty("token");
				TOKEN_SECRET=prop.getProperty("tokenSecret");
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
