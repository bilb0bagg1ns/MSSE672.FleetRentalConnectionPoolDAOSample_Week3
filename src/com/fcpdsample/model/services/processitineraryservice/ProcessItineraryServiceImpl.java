package com.fcpdsample.model.services.processitineraryservice;

import com.fcpdsample.model.dao.IFleetRentalDao;
import com.fcpdsample.model.domain.RentalComposite;
import com.fcpdsample.model.services.exception.DaoLoadException;
import com.fcpdsample.model.services.factory.DAOFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author Mike.Prasad
 *
 * Checks to see if rental car request is available.
 */
public class ProcessItineraryServiceImpl implements IProcessItineraryService {

  /*
     * Category set in config/log4j.properties as
     * log4j.category.com.classexercise=DEBUG, A1
   */
  static Logger log = Logger.getLogger("com.fleetrentalhibernatedaosample");

  /**
   * Delegates request to the DAO.
   *
   * @param rentalComposite contains available rentals
   */
  @Override
  public boolean processItinerary(RentalComposite rentalComposite) {
    boolean status = false;
    // Fetch the DAO Implementation
    IFleetRentalDao fleetRentalDao;
    try {
      fleetRentalDao = DAOFactory.getDao();
      // get available cars to rent
      status = fleetRentalDao.getAvailableRentals(rentalComposite);
    } catch (DaoLoadException ex) {
      // We are not propagating exception, with the intent that the 
      // RentalComposite will hold the state reflecting(null AvailableRentals
      // for example) an anomaly
      log.error("DAO Load Exception", ex);
    }
    return status;
  }
} // end class ProcessItineraryServiceImpl 

