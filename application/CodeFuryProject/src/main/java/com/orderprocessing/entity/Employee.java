package com.orderprocessing.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Employee {

	private String employeeId;
	private String employeeName;
	private String employeePassword;
	private Timestamp lastLoginDateTime;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
			}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public Timestamp getLastLoginDateTime() {
		return lastLoginDateTime;
	}
	public void setLastLoginDateTime(java.sql.Timestamp timestamp) {
		this.lastLoginDateTime = timestamp;
	}
	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(employeeId, other.employeeId);
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", lastLoginDateTime="
				+ lastLoginDateTime + "]";
	}
	
	
	
}
