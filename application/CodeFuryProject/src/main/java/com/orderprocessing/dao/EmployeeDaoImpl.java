//package com.orderprocessing.dao;
//
//import com.orderprocessing.entity.Employee;
//
//public class EmployeeDaoImpl implements EmployeeDao{
//
//	@Override
//	public Employee findEmployeeById(int id) {
//		// TODO Auto-generated method stub
//		
//		
//		
//		
//		return null;
//	}
//}
package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderprocessing.entity.Employee;
import com.orderprocessing.exception.EmployeeNotFoundException;
import com.orderprocessing.utils.DBUtil;

// Implementation for EmployeeDao Inerface
public class EmployeeDaoImpl implements EmployeeDao {

	private static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE EMPLOYEE_ID=? and EMPLOYEE_PASSWORD=?";

	ResultSet resultSet;
	PreparedStatement stmt;
	private Connection connection;

	public EmployeeDaoImpl() {
		connection = DBUtil.getMyConnection();
	}

	// Authenticating credentials
	@Override
	public Employee login(String id, String password) throws EmployeeNotFoundException {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Employee employee = new Employee();
		try {
			stmt = connection.prepareStatement(FIND_EMPLOYEE_BY_ID);
			stmt.setString(1, id);
			stmt.setString(2, password);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
//				
				employee.setEmployeeId(resultSet.getString(1));
				employee.setEmployeeName(resultSet.getString(2));
				employee.setEmployeePassword("");

				// Have to set it employee.setLastLoginDateTime(());
				employee.setLastLoginDateTime(resultSet.getTimestamp(4));
				return employee;

			} else {
				return null;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		throw new EmployeeNotFoundException();
	}

}
