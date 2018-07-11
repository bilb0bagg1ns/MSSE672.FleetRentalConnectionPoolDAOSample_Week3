package com.fcpdsample.model.business.manager;


import com.fcpdsample.model.services.exception.PropertyFileNotFoundException;
import com.fcpdsample.model.domain.RentalComposite;
import com.fcpdsample.model.services.exception.ServiceLoadException;
import com.fcpdsample.model.services.factory.ServiceFactory;
import com.fcpdsample.model.services.processitineraryservice.IProcessItineraryService;
import com.fcpdsample.model.services.reserverentalservice.IReserveRentalService;
import org.apache.log4j.Logger;

/**
 *  This Manager class provides two key functionalities:
 *  1. Has the properties files loaded by the PropertyManager
 *  2. Calls the ServiceFactory class and executes the services being
 *     requested by the controller.
 *     
 *  Manager classes in reality provide a define set of functionality and govern
 *  the overall processing of the application.
 *     
 * @author Mike.Prasad
 *
 */

public class DAOManager extends ManagerSuperType
{
	
	/*
	 * Category set in config/log4j.properties as
	 * log4j.category.com.classexercise=DEBUG, A1
	 */
  static Logger log = Logger.getLogger("com.fleetrentalconnpooldaosample");

	private static DAOManager _instance;

	 /**
	  * keep the constructor private to prevent instantiation by outside callers.
	  */
	 private DAOManager() {
	  // construct object . . .
	 }

	 /**
	  * Assures that there is only one FleetRentalManager created. 
	  * @return FleetRentalManager instance
	  */
	 public static synchronized DAOManager getInstance() {
	  if (_instance==null) {
	   _instance = new DAOManager();
	  }
	  return _instance;
	 }

	/**
	 * Method delegates to the ServiceFactory to execute a service. Good part of this 
	 * approach is that the Manager knows the service by a string name - thus achieving 
	 * the decoupling effect that we so desire in the MVC approach.
	 * 
	 * @param commandString - contains the service that needs to be performed
	 * @param rentalComposite - contains the info needed by the above service.
		*                          null if fatal issues encountered.
	 */ 
		@Override
		public boolean performAction(String commandString, RentalComposite rentalComposite)
		{
				boolean status = false;
				switch (commandString) {
					case "ProcessItinerary":
								status = processItinerary(IProcessItineraryService.NAME, rentalComposite);
						break;
					case "ReserveRental":
								status = reserveRentalCar(IReserveRentalService.NAME, rentalComposite);
						break;
				}
				return status;
		}//end performaAction


		/**
			* Process incoming itinerary
			* 
			* @param commandString
			* @param rentalComposite 
			*/
		private boolean processItinerary(String commandString, RentalComposite rentalComposite)
		{
				boolean status = false;
			 ServiceFactory serviceFactory = ServiceFactory.getInstance();
				IProcessItineraryService iProcessItineraryService;

				try 
				{
						iProcessItineraryService = (IProcessItineraryService)serviceFactory.getService(commandString);
						status = iProcessItineraryService.processItinerary(rentalComposite);
				} catch (ServiceLoadException e) {
											log.error ("DAOManager::processItinerary failed", e);
				}										
				return status;
		} //end processItinerary

		/**
			* Reserve car
			* 
			* @param commandString
			* @param rentalComposite 
			*/
		private boolean reserveRentalCar(String commandString, RentalComposite rentalComposite)
		{
				boolean status = false;
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				IReserveRentalService iReserveRentalService;

				try 
				{
						iReserveRentalService = (IReserveRentalService)serviceFactory.getService(commandString);
						status = iReserveRentalService.reserveRentalCar(rentalComposite);
				} catch (ServiceLoadException e) {
											log.error ("DAOManager::reserveRentalCar failed", e);
				}											
    return status; 
		}//end reserveRentalCar
				
		/**
			* Not really used in this application. But is here in case we want to test
			* this class standalone.
			* @param args
			*/
		public static void main (String[] args)
		{
			try
			{
							DAOManager.loadProperties();  
			}
  	catch (PropertyFileNotFoundException pfnfe)
			{
			  log.error ("Application Properties failed to be loaded - Server will exit", pfnfe);
			}
		} //end main
} // end class FleetRentalServerManager