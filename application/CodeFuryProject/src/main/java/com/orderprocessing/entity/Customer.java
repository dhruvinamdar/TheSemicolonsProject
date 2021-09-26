package com.orderprocessing.entity;

import java.util.Objects;

public class Customer {
	private String customerId;
	private String customerName;
	private String customerGST;
	private String customerAddress;
	private String customerCity;
	private String customerState;
	private String customerEmail;
	private String customerContact;
	private String customerPincode;

	public Customer() {
		super();
	}

	public Customer(String customerId, String customerName, String customerGST, String customerAddress,
			String customerCity, String customerEmail, String customerContact, String customerPincode) {
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

	public Customer(String customerAddress, String customerCity, String customerState) {
		super();
		this.customerAddress = customerAddress;
		this.customerCity = customerCity;
		this.customerState = customerState;
	}

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

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
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

	@Override
	public int hashCode() {
		return Objects.hash(customerAddress, customerCity, customerContact, customerEmail, customerGST, customerId,
				customerName, customerPincode, customerState);
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
				&& Objects.equals(customerPincode, other.customerPincode)
				&& Objects.equals(customerState, other.customerState);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerGST=" + customerGST
				+ ", customerAddress=" + customerAddress + ", customerCity=" + customerCity + ", customerState="
				+ customerState + ", customerEmail=" + customerEmail + ", customerContact=" + customerContact
				+ ", customerPincode=" + customerPincode + "]";
	}

}
