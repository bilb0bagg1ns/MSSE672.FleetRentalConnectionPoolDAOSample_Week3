package com.fcpdsample.model.domain;

import java.io.Serializable;
import java.util.Calendar;

public class Itinerary implements Serializable {

  /**
   * Pickup City Id
   */
  private String fleetRentalPickUpCityId;

  /**
   * Pickup City
   */
  private String fleetRentalPickUpCity;

  /**
   * Return City Id
   */
  private String fleetRentalDropOffCityId;

  /**
   * Return City
   */
  private String fleetRentalDropOffCity;

  /**
   * Pick-up Date
   */
  private String pickUpMonth;
  private String pickUpDay;
  private String pickUpYear;

  /**
   * Pick-up Time
   */
  private String pickUpTime;

  /**
   * Drop-off Date
   */
  private String dropOffMonth;
  private String dropOffDay;
  private String dropOffYear;

  /**
   * Drop-off Time
   */
  private String dropOffTime;

  /**
   *    */
  private int qtyRentalDays = 0;

  /**
   *
   */
  public Itinerary() {

    // TODO Auto-generated constructor stub
  }

  public Itinerary(String fleetRentalPickUp,
          String fleetRentalDropOff,
          String pickUpMonth,
          String pickUpDay,
          String pickUpYear,
          String pickUpTime,
          String dropOffMonth,
          String dropOffDay,
          String dropOffYear,
          String dropOffTime) {
    this.fleetRentalPickUpCity = fleetRentalPickUp;
    this.fleetRentalDropOffCity = fleetRentalDropOff;
    this.pickUpMonth = pickUpMonth;
    this.pickUpDay = pickUpDay;
    this.pickUpYear = pickUpYear;
    this.pickUpTime = pickUpTime;
    this.dropOffMonth = dropOffMonth;
    this.dropOffDay = dropOffDay;
    this.dropOffYear = dropOffYear;
    this.dropOffTime = dropOffTime;

    // determine qty of rental days by using Calendar class.		   
    Calendar pickUpCalendar = Calendar.getInstance();

    // -1 from pickUpMonth since Calendar is 0 based (Jan is 0)
    pickUpCalendar.set(Integer.parseInt(pickUpYear),
            (Integer.parseInt(pickUpMonth) - 1),
            Integer.parseInt(pickUpDay));

    Calendar dropOffCalendar = Calendar.getInstance();

    // -1 from pickUpMonth since Calendar is 0 based (Jan is 0)
    dropOffCalendar.set(Integer.parseInt(dropOffYear),
            (Integer.parseInt(dropOffMonth) - 1),
            Integer.parseInt(dropOffDay));

    // convert pickUp and dropOff dates into milli seconds,
    // so we can determine the qty of rental days.
    long diffDayMillis = dropOffCalendar.getTimeInMillis()
            - pickUpCalendar.getTimeInMillis();

    // divide by milli seconds in a day (24*60*60*1000)
    qtyRentalDays = (int) (diffDayMillis / 86400000);

  }

  /**
   * @return Returns the dropOffDay.
   */
  public String getDropOffDay() {
    return dropOffDay;
  }

  /**
   * @return Returns the dropOffMonth.
   */
  public String getDropOffMonth() {
    return dropOffMonth;
  }

  /**
   * @return Returns the dropOffTime.
   */
  public String getDropOffTime() {
    return dropOffTime;
  }

  /**
   * @return Returns the dropOffYear.
   */
  public String getDropOffYear() {
    return dropOffYear;
  }

  /**
   * @return Returns the fleetRentalDropOff.
   */
  public String getFleetRentalDropOffCityId() {
    return fleetRentalDropOffCityId;
  }

  /**
   * @return Returns the fleetRentalDropOffCity.
   */
  public String getFleetRentalDropOffCity() {
    return fleetRentalDropOffCity;
  }

  /**
   * @param fleetRentalDropOffCity The fleetRentalDropOffCity to set.
   */
  public void setFleetRentalDropOffCity(String fleetRentalDropOffCity) {
    this.fleetRentalDropOffCity = fleetRentalDropOffCity;
  }

  /**
   * @param fleetRentalPickUpCity The fleetRentalPickUpCity to set.
   */
  public void setFleetRentalPickUpCity(String fleetRentalPickUpCity) {
    this.fleetRentalPickUpCity = fleetRentalPickUpCity;
  }

  /**
   * @return Returns the fleetRentalPickUp.
   */
  public String getFleetRentalPickUpCityId() {
    return fleetRentalPickUpCityId;
  }

  /**
   * @return Returns the fleetRentalPickUpCity.
   */
  public String getFleetRentalPickUpCity() {
    return fleetRentalPickUpCity;
  }

  /**
   * @return Returns the pickUpDay.
   */
  public String getPickUpDay() {
    return pickUpDay;
  }

  /**
   * @return Returns the pickUpMonth.
   */
  public String getPickUpMonth() {
    return pickUpMonth;
  }

  /**
   * @return Returns the pickUpTime.
   */
  public String getPickUpTime() {
    return pickUpTime;
  }

  /**
   * @return Returns the pickUpYear.
   */
  public String getPickUpYear() {
    return pickUpYear;
  }

  /**
   * @return Returns the qtyRentalDays.
   */
  public int getQtyRentalDays() {
    return qtyRentalDays;
  }

  public String toString() {
    StringBuffer strBfr = new StringBuffer();
    strBfr.append("fleetRentalPickUp Id:");
    strBfr.append(fleetRentalPickUpCityId);
    strBfr.append("\nfleetRentalPickUp City:");
    strBfr.append(fleetRentalPickUpCity);
    strBfr.append("\nfleetRentalDropOff Id:");
    strBfr.append(fleetRentalDropOffCityId);
    strBfr.append("\nfleetRentalDropOff City:");
    strBfr.append(fleetRentalDropOffCity);
    strBfr.append("\npickUpMonth :");
    strBfr.append(pickUpMonth);
    strBfr.append("\npickUpDay :");
    strBfr.append(pickUpDay);
    strBfr.append("\npickUpYear :");
    strBfr.append(pickUpYear);
    strBfr.append("\npickUpTime :");
    strBfr.append(pickUpTime);
    strBfr.append("\ndropOffMonth :");
    strBfr.append(dropOffMonth);
    strBfr.append("\ndropOffDay :");
    strBfr.append(dropOffDay);
    strBfr.append("\ndropOffYear :");
    strBfr.append(dropOffYear);
    strBfr.append("\ndropOffTime :");
    strBfr.append(dropOffTime);
    strBfr.append("\nqtyRentalDays :");
    strBfr.append(qtyRentalDays);

    return strBfr.toString();
  }

} //end Itinerary
