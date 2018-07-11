package com.fcpdsample.model.services.factory;

import com.fcpdsample.model.dao.IFleetRentalDao;
import com.fcpdsample.model.services.exception.DaoLoadException;
import com.fcpdsample.model.services.manager.PropertyManager;

/**
 * This factory class retrieves from the properties file the concrete
 * type of DAO implementation - JDBC or Hibernate
 * 
 *     
 * @author Mike.Prasad
 *
 */
public class DAOFactory
{
   /**
    *  Calls PropertyManager to fetch the DAO Implementation 
    *  and returns it.
    *     
    * @return IFleetRentalDao
    */
   public static IFleetRentalDao getDao() throws DaoLoadException
   {

     Class  c;
     Object o = null;
					try
					{
								// lets get the concrete service from the property file
								// and assign (reuse) to serviceString
								//
								// This property value is set in config/application.properties
								String daoImplString = PropertyManager.getPropertyValue("IFleetRentalDao");

								// using the fully qualified service name,
								// lets create and instance of the class
								c = Class.forName(daoImplString);
								o = c.newInstance();

					} catch (ClassNotFoundException e) {
							 throw new DaoLoadException("Class not found", e);
					} catch (InstantiationException e) {
							 throw new DaoLoadException("Instantiation Issue", e);
					} catch (IllegalAccessException e) {
							 throw new DaoLoadException("Illegal Access Issue", e);
					}
					return (IFleetRentalDao)o;
   } //end getService

}//end ServiceFactory