package com.fcpdsample.model.domain;

import java.io.Serializable;

public class Car implements Serializable {

  /**
   * Car ID
   */
  private Integer carId;

  /**
   * Daily rate
   */
  private float rate;

  /**
   * Car manufacturer
   */
  private String manufacturer;

  /**
   * Car model
   */
  private String model;

  /**
   * Free miles included in this rental
   */
  private String milesIncluded;

  /**
   * Y/N : Rented/Not Rented
   */
  private String rented;

  /**
   * Location where this car is available Part of Hibernate mapping, that Car to
   * Location is a one-to-many association.
   */
  private Location location;

  /**
   *
   */
  public Car() {

    // TODO Auto-generated constructor stub
  }

  /**
   * @param rate
   * @param manufacturer
   * @param model
   * @param milesIncluded
   */
  public Car(float rate, String manufacturer, String model, String milesIncluded) {
    super();
    this.rate = rate;
    this.manufacturer = manufacturer;
    this.model = model;
    this.milesIncluded = milesIncluded;
  }

  public Car(float rate, String manufacturer, String model, String milesIncluded, String rented) {
    super();
    // TODO Auto-generated constructor stub
    this.rate = rate;
    this.manufacturer = manufacturer;
    this.model = model;
    this.milesIncluded = milesIncluded;
    this.rented = rented;
  }

  public Integer getCarId() {
    return carId;
  }

  public void setCarId(Integer carId) {
    this.carId = carId;
  }

  /**
   * @return Returns the rate.
   */
  public float getRate() {
    return rate;
  }

  public void setRate(float rate) {
    this.rate = rate;
  }

  /**
   * @return Returns the manufacturer.
   */
  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * @return Returns the model.
   */
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  /**
   * @return Returns the milesIncluded.
   */
  public String getMilesIncluded() {
    return milesIncluded;
  }

  public void setMilesIncluded(String milesIncluded) {
    this.milesIncluded = milesIncluded;
  }

  public String getRented() {
    return rented;
  }

  public void setRented(String rented) {
    this.rented = rented;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   *
   * @return @author
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("\t\n\nCar[");
    buffer.append("\t\t\ncarId = ").append(carId);
    buffer.append("\t\t\nmanufacturer = ").append(manufacturer);
    buffer.append("\t\t\nmilesIncluded = ").append(milesIncluded);
    buffer.append("\t\t\nmodel = ").append(model);
    buffer.append("\t\t\nrate = ").append(rate);
    buffer.append("\t\t\nrented = ").append(rented);
    buffer.append("]\n");
    return buffer.toString();
  }

} //end Car
