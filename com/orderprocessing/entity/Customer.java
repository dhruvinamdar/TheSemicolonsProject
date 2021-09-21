package com.orderprocessing.entity;

import java.util.Objects;

public class Customer {
	private String customerId;
	private String customerName;
	private String customerGST;
	private String customerAddress;
	private String customerCity;
	private String customerEmail;
	private int customerContact;
	private int customerPincode;

	// default constructor
	public Customer() {
		super();
	}

	// parameterized constructor
	public Customer(String customerId, String customerName, String customerGST, String customerAddress,
			String customerCity, String customerEmail, int customerContact, int customerPincode) {
		super();

		this.customerId = customerId;
		this.customerName = customerName;
		this.customerGST = customerGST;
		this.customerAddress = customerAddress;
		this.customerCity = customerCity;
		this.customerEmail = customerEmail;
		this.customerContact = customerContact;
		this.customerPincode = customerPincode;
	}

	// getter-setter methods
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

	public int getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(int customerContact) {
		this.customerContact = customerContact;
	}

	public int getCustomerPincode() {
		return customerPincode;
	}

	public void setCustomerPincode(int customerPincode) {
		this.customerPincode = customerPincode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerAddress, customerCity, customerContact, customerEmail, customerGST, customerId,
				customerName, customerPincode);
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
				&& Objects.equals(customerCity, other.customerCity) && customerContact == other.customerContact
				&& Objects.equals(customerEmail, other.customerEmail) && Objects.equals(customerGST, other.customerGST)
				&& Objects.equals(customerId, other.customerId) && Objects.equals(customerName, other.customerName)
				&& customerPincode == other.customerPincode;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerGST=" + customerGST
				+ ", customerAddress=" + customerAddress + ", customerCity=" + customerCity + ", customerEmail="
				+ customerEmail + ", customerContact=" + customerContact + ", customerPincode=" + customerPincode + "]";
	}

}
