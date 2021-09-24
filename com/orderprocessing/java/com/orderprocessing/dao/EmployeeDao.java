package com.orderprocessing.dao;
import com.orderprocessing.entity.Employee;
import com.orderprocessing.exception.EmployeeNotFoundException;

public interface EmployeeDao {
	
	Employee login(String id,String password) throws EmployeeNotFoundException;
	

}
