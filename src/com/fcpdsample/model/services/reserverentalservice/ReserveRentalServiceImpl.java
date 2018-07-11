package com.fcpdsample.model.services.reserverentalservice;

import com.fcpdsample.model.dao.IFleetRentalDao;
import com.fcpdsample.model.domain.RentalComposite;
import com.fcpdsample.model.services.exception.DaoLoadException;
import com.fcpdsample.model.services.factory.DAOFactory;
import org.apache.log4j.Logger;

/**
 * Reserves the car by delegating to ReserveRentalPojo
 *
 * @author Mike.Prasad
 *
 *
 */
public class ReserveRentalServiceImpl implements IReserveRentalService {

  /*
	 * Category set in config/log4j.properties as
	 * log4j.category.com.classexercise=DEBUG, A1
   */
  static Logger log = Logger.getLogger("com.fcpdsample");

  /**
   * Delegates request to the DAO.
   *
   * @param rentalComposite contains reservation information
   */
  @Override
  public boolean reserveRentalCar(RentalComposite rentalComposite) {
    boolean status = false;
    try {
      // Fetch the DAO Implementation
      IFleetRentalDao fleetRentalDao = DAOFactory.getDao();
      // Reserve the car
      status = fleetRentalDao.reserveRentalCar(rentalComposite);
    } //end reserveRentalCar
    catch (DaoLoadException ex) {
      // We are not propagating exception, with the intent that the 
      // RentalComposite will hold the state reflecting(null AvailableRentals
      // for example) an anomaly
      log.error("DAO Load Exception", ex);
    }
    return status;
  } //end reserveRentalCar

} //end ReserveRentalServiceImpl
