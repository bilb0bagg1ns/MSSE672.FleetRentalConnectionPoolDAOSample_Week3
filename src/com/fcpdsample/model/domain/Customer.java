package com.fcpdsample.model.domain;

import java.io.Serializable;


public class Customer implements Serializable
{
  private String lastName;
  private String firstName;
  private String emailAddress;
  private String dayTimePhone;
  private String eveningTimePhone;
  

	/**
	 *
	 */
	public Customer() {

		// TODO Auto-generated constructor stub
	}
	/**
	 * @param lastName
	 * @param firstName
	 * @param emailAddress
	 */
	public Customer(String lastName, String firstName, String emailAddress, String dayTimePhone, String eveningTimePhone)
	{
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.dayTimePhone = dayTimePhone;
		this.eveningTimePhone = eveningTimePhone;
	}
/**
 * @param emailAddress The emailAddress to set.
 */
public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}
/**
 * @return Returns the dayTimePhone.
 */
public String getDayTimePhone() {
	return dayTimePhone;
}
/**
 * @return Returns the eveningTimePhone.
 */
public String getEveningTimePhone() {
	return eveningTimePhone;
}
/**
 * @param firstName The firstName to set.
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
/**
 * @param lastName The lastName to set.
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}

/**
 * @return Returns the emailAddress.
 */
public String getEmailAddress() {
	return emailAddress;
}
/**
 * @return Returns the firstName.
 */
public String getFirstName() {
	return firstName;
}
/**
 * @return Returns the lastName.
 */
public String getLastName() {
	return lastName;
}

public String toString()
{
  StringBuffer strBfr = new StringBuffer();
  strBfr.append("\nlastname :");
  strBfr.append(lastName);
  strBfr.append("\nfirstname :");
  strBfr.append(firstName);
  strBfr.append("\nemail address :");
  strBfr.append(emailAddress);
  strBfr.append("\nday time phone :");
  strBfr.append(dayTimePhone);
  strBfr.append("\nevening Phone :");
  strBfr.append(eveningTimePhone);

  return strBfr.toString();
}

} // end class Customer