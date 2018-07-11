package com.fcpdsample.model.dao;

import com.fcpdsample.model.domain.RentalComposite;

/**
 * Defines the interface that the concrete DAO implementation classes implement
 *
 * @author Mike.Prasad
 *
 */
public interface IFleetRentalDao {

  public boolean getAvailableRentals(RentalComposite rentalComposite);

  public boolean reserveRentalCar(RentalComposite _rentalComposite);

}
