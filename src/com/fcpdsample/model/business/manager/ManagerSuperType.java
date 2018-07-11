package com.fcpdsample.model.business.manager;

import com.fcpdsample.model.services.exception.PropertyFileNotFoundException;
import com.fcpdsample.model.dao.jdbc.manager.JDBCConnectionPoolManager;
import com.fcpdsample.model.domain.RentalComposite;
import com.fcpdsample.model.services.exception.ConnectionPoolCreationException;
import com.fcpdsample.model.services.manager.PropertyManager;
import org.apache.log4j.Logger;

/**
 * 
 * NOTE: Certain features in this abstract class are not discussed in class slides, 
 *       however recommended to understand and highly encouraged to apply in your homework.
 *       
 */       
public abstract class ManagerSuperType {

		/*
	 * Category set in config/log4j.properties as
	 * log4j.category.com.classexercise=DEBUG, A1
	 */
    static Logger log = Logger.getLogger("com.fleetrentalconnpooldaosample");
				
	/**
	 * What you seeing below, is called a static initializer block,
	 * which gets executed at the time when the class that contains it or extends it is referenced.
	 * 
	 * What we hope to achieve in this application is that when the FleetRentalManager(which extends this class)
	 * is referenced, we want the application properties to be loaded up so the properties contents are available
	 * for use by all tiers under the business tier.
	 * 
	 * Reference site on static initializer block:
	 * http://www.developer.com/java/other/article.php/2238491/The-Essence-of-OOP-using-Java-Static-Initializer-Blocks.htm
	 * 
	 */
	static
	{
  try
		{
    		ManagerSuperType.loadProperties();  
      JDBCConnectionPoolManager.createPool(); 						
		}
  catch (PropertyFileNotFoundException pfnfe)
		{
    	 log.error ("Application Properties failed to be loaded - Server will exit. Reason: " + pfnfe.getMessage(), pfnfe);
						System.exit(1);
		}			
		catch (ConnectionPoolCreationException cpce)
		{
    	 log.error ("Connection Pool Issues - Server will exit. Reason:", cpce);
						System.exit(1);			
		}
	}
	
	/** 
	 * Generic method that all clients of this class can call to perform certain actions.
	 * 
	 * @param commandString 
	 *                      Holds the service name to be invoked	                       
	 * @param rentalComposite
	 *                      Holds application specific domain state
	 * @return
	 *         false
	 *              if action failed
	 *         true
	 *              if action is successful
	 */
	public abstract boolean performAction(String commandString, RentalComposite rentalComposite); 
	
	
	/**
	 * Loads the property file into memory so its available for use by all tiers (business and below)
	 * @throws PropertyFileNotFoundException
	 *                                Properties file could bot be loaded.
	 */
 public static void loadProperties () throws PropertyFileNotFoundException
	{
		/*
    	* FAQ: How to pass in Properties file to app while I'm testing?
    	* 
    	* Answer:
    	*   		 
        * If using ANT:
    	* ============
    	* Property file is located by making a call to
		* System.getProperty(prop_location), which actually
		* reads off from the target runTestDriver in build.xml
		*   <sysproperty key="prop_location" value="${prop.dir}application.properties"/>
    	*
    	* If running in Eclipse 
    	* ===================== 
    	* 
    	* Right click on the unit test and navigate to
    	*       1. Run As -> Run Configuration
    	*       2. Select Arguments Tab
    	*       3. In VM Arguments section, add the -D property 
    	* 	           -Dprop_location=<drive><location>\application.properties
    	*            Example:
    	* 	           -Dprop_location=E:\FleetRental\config\application.properties
    	* 
    	* If running on command line 
    	* ==========================
    	* you'd pass in the above -D option with the java command.
    	*/

	
		  String propertyFileLocation = System.getProperty("prop_location");
       		
				//String propertyFileLocation = "config/application.properties";

				// Now that we have the property file location, lets have the 
				// PropertyManager class load it up
   	PropertyManager.loadProperties(propertyFileLocation);
		
	} //end loadProperties
    
				/**
	 * Delegates to the right property manager.
		* 
		* By making the application Manager be aware of the property file used
	 * in the application, this allows the Factory to just deal
	 * with the application manager and not be aware of the property manager
	 * 
	 * @param key - key whose value needs to be returned
	 * @return String - value for the passed key
	 */
    static public String getPropertyValue (String key)
    {
    	return PropertyManager.getPropertyValue(key);
    }
}
