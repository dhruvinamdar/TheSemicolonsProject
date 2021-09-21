package com.orderprocessing.utility;

import java.util.Objects;

public class Customer {

	private String customerId;
	private String customerName;
	private String customerGST;
	private String customerAddress;
	private String customerCity;
	private String customerEmail;
	private String customerContact;
	private String customerPincode;
	private String customerPassword;
	
	// default constructor
	public Customer() {
		super();
	}

	// parameterized constructor
	public Customer(String customerId, String customerName, String customerGST, String customerAddress,
			String customerCity, String customerEmail, String customerContact, String customerPincode,
			String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerGST = customerGST;
		this.customerAddress = customerAddress;
		this.customerCity = customerCity;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerPincode = customerPincode;
		this.customerPassword = customerPassword;
	}

	// setter-getter methods
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGST() {
		return customerGST;
	}

	public void setCustomerGST(String customerGST) {
		this.customerGST = customerGST;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerPincode() {
		return customerPincode;
	}

	public void setCustomerPincode(String customerPincode) {
		this.customerPincode = customerPincode;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerAddress, customerCity, customerContact, customerEmail, customerGST, customerId,
				customerName, customerPassword, customerPincode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerAddress, other.customerAddress)
				&& Objects.equals(customerCity, other.customerCity)
				&& Objects.equals(customerContact, other.customerContact)
				&& Objects.equals(customerEmail, other.customerEmail) && Objects.equals(customerGST, other.customerGST)
				&& Objects.equals(customerId, other.customerId) && Objects.equals(customerName, other.customerName)
				&& Objects.equals(customerPassword, other.customerPassword)
				&& Objects.equals(customerPincode, other.customerPincode);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerGST=" + customerGST
				+ ", customerAddress=" + customerAddress + ", customerCity=" + customerCity + ", customerEmail="
				+ customerEmail + ", customerContact=" + customerContact + ", customerPincode=" + customerPincode
				+ ", customerPassword=" + customerPassword + "]";
	}

	
	
	
}

