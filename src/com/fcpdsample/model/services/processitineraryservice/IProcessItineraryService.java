/**
 * 
 */
package com.fcpdsample.model.services.processitineraryservice;

import com.fcpdsample.model.domain.RentalComposite;
import com.fcpdsample.model.services.IService;

/**
 * @author mike.prasad
 *
 */
public interface IProcessItineraryService extends IService
{

		public final String NAME = "IProcessItineraryService";

		public boolean processItinerary(RentalComposite rentalComposite);
}
