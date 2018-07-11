package com.fcpdsample.model.services.manager;

import com.fcpdsample.model.services.exception.PropertyFileNotFoundException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/*-
 * This manager class primary responsiblity is to the load the property file
 * into memory and make it available for all other classes to use.
 * 
 * This class's loadProperties method is called by the FleetRentalManager.
 * 
 * Once loaded, any other class can call it and request for a property value.
 * 
 * For example:
 *   1. The application.properties file that was loaded for this application 
 *      has these values:
 *  		 jdbc.url = jdbc:odbc:TimeZone
 *      	 jdbc.user = Homer
 *   		 jdbc.password = Duff
 *   2. Once this file is loaded, if a class wants to get the value for the 
 *      key jdbc.url, you would make the following call:
 *           PropertyManager.getPropertyValue("jdbc.url");
 *      This would return the string value, "jdbc:odbc:TimeZone"      
 *   
 * @author Mike.Prasad
 *
 */
public class PropertyManager {

  /*
	 * Category set in config/log4j.properties as
	 * log4j.category.com.classexercise=DEBUG, A1
   */
  static Logger log = Logger.getLogger("com.fleetrentalconnpooldaosample");

  private static Properties properties;

  /**
   * Load the properties file so its contents are available for classes in the
   * Model tier.
   *
   * @param propertyFileLocation
   * @throws PropertyFileNotFoundException
   */
  public static void loadProperties(String propertyFileLocation) throws PropertyFileNotFoundException {
    properties = new Properties();
    FileInputStream sf = null;

    try {
      sf = new FileInputStream(propertyFileLocation);
      properties.load(sf);
    } catch (FileNotFoundException e) {
      log.error("Property file not found at location" + propertyFileLocation, e);
      throw new PropertyFileNotFoundException("Property File cannot be found in PropertyManager::loadProperties", e);
    } catch (IOException e) {
      log.error("IOException while loading property file from location: " + propertyFileLocation, e);
      throw new PropertyFileNotFoundException("IOException while loading property file in PropertyManager::loadProperties", e);

    } catch (Exception e) {
      log.error("Exception while loading property file from location: " + propertyFileLocation, e);
      throw new PropertyFileNotFoundException("Exception while loading property file in PropertyManager::loadProperties", e);
    } finally {
      if (sf != null) {
        try {
          sf.close();
        } catch (IOException ioe) {
          // Can't do much here if exceptions occur, other then logging
          log.error(ioe.getClass() + ": " + ioe.getMessage(), ioe);
        }
      }
    }//end try/catch/finally	    					
  } //end loadProperties()

  /**
   * This methods returns the Value for the passed key.
   *
   * @param key - key whose value needs to be returned
   * @return String - value for the passed key
   */
  static public String getPropertyValue(String key) {
    return properties.getProperty(key);
  }
} // end class PropertyManager
