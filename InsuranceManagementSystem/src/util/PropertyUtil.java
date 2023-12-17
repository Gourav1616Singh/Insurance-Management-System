package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return null;
            }

            properties.load(input);
            return properties.getProperty("connectionString");
        }
    }
}
