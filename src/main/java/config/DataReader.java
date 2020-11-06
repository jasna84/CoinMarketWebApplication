package main.java.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataReader {

    public Properties prop = new Properties();
    InputStream input = null;

    public String getProperties(String key) {

        try {
            input =  getClass().getClassLoader().getResourceAsStream("data.properties");
            prop.load(input);
            if(input == null) {
                System.out.println("Properties file not found");
            }

        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(key);
    }

}
