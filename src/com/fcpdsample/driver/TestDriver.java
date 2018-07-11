
package com.fcpdsample.driver;

import com.fcpdsample.model.business.manager.DAOManager;
import com.fcpdsample.model.domain.Car;
import com.fcpdsample.model.domain.Customer;
import com.fcpdsample.model.domain.Itinerary;
import com.fcpdsample.model.domain.RentalComposite;
import org.apache.log4j.Logger;

/**
 * Please note that the FleetRental application can be run either by running
 * the ViewDriver class or this class. This class tests the Controller and Model
 * components.  
 * 
 * Its common to have test drivers such as this class to test applications.
 *  
 * A real world implementation, would of course have a View (either Swing or Web based).
 * 
	* 
	 ///////////////////////////////////////////////////////////
  // Need to pass property file that is loaded by PropertyManager
		// 1. From IDE, set the VM Options under Project Properties
		//     -Dprop_location=<path>\config\application.properties
		// 2. Build.xml: reads off from the target to run the app with the sys property set as :
		//   <sysproperty key="prop_location" value="${config.dir}application.properties"/>
	* 
 * @author Mike.Prasad
 *
 */

public class TestDriver
{

				/*
					* Category set in config/log4j.properties as
					* log4j.category.com.classexercise=DEBUG, A1
					*/
					static Logger log = Logger.getLogger("com.fleetrentalconnpooldaosample");
  
				public static void main (String[] args)
				{
						Log4JInit.initializeLog4J();

						
						
						// In the real world implementation, customer would identify
						// an Itinerary.
						RentalComposite rentalComposite = new RentalComposite();

						// lets create a sample itinerary.
						// Itinerary constructor needs following fields : 
						// (fleetRentalPickUp, fleetRentalDropOff,pickUpMonth,pickUpDay,pickUpYear,pickUpTime,dropOffMonth,dropOffDay, dropOffYear,dropOffTime)		
						rentalComposite.setItinerary(new Itinerary("San Francisco Airport", "San Francisco Airport", "06", "18", "2006" ,"01:10", "06", "28", "2006", "12:00"));

						log.info ("\n----------------");
						log.info ("\n-->Checking Car Availablity for itinerary: \n\n" + rentalComposite.getItinerary());

						// now that we have an itinerary, lets call into the Model via the controller,
						// to see if have any cars available for this itinerary
						DAOManager daoManager =  DAOManager.getInstance();
						boolean status = daoManager.performAction("ProcessItinerary", rentalComposite);

						if (status) //if true then request processed successfully
						{
								// Lets check if cars are available if so we can reserve them.
								if (rentalComposite != null)
								{
										if (rentalComposite.getAvailableRentals() != null)
										{
												if (rentalComposite.getAvailableRentals().isAvailable())
												{	
															// Cool, we have a car to rent, lets get Customer info and the Car customer
															// wants to rent.
															log.info ("\n-->Cars available for above itinerary: \n\n" + rentalComposite.getAvailableRentals());

															// User enters personal info
															// Customer contructor takes in lastname, firstname, email address, day time phone and evening phone
															rentalComposite.setCustomer(new Customer("Simpson", "Homer","homer@duff.com","303-786-1111", "303-786-1111"));

															// User select the car he/she wants to rent
															// Car constructor takes in rate, manufacturer, model, miles included
															rentalComposite.setRentedCar(new Car(25.50f, "Ford", "Focus", "Unlimited"));

															log.info ("\n-->Calling reserve rental car service with this details: \n\n" + rentalComposite);

															// Ideally the type of the service that needs to be executed
															// is mapped in a properties file. Hardcoded here to
															// illustrate the example.
															daoManager.performAction("ReserveRental", rentalComposite);
												}	
												else
												{
														// Hopefully this doesn't happen in the real world! :)
														log.info ("No car available! Suggest hitchhiking!");
												} //end if
										}
										else
										{
														// AvailableRentals is NULL - this due to SQL Exception issue
														log.error ("We are facing an issue, please try back later!");									
										}//end if								
								} //end if							
						}
						else
						{
								log.error ("We are facing an issue, please try back later!");																
						}	
  	  	
    } //end main

} //end class TestDriver
