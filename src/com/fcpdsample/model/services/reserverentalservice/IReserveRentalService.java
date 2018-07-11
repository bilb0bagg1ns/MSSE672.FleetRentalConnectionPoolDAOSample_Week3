/**
 * 
 */
package com.fcpdsample.model.services.reserverentalservice;

import com.fcpdsample.model.domain.RentalComposite;
import com.fcpdsample.model.services.IService;

/**
 * @author mike.prasad
 *
 */
public interface IReserveRentalService extends IService
{

	public final String NAME = "IReserveRentalService";

	/** Register customer into our application 
	 * @throws RegistrationException */
	public boolean reserveRentalCar(RentalComposite rentalComposite);

}
