import com.orderprocessing.service.EmployeeService;
import com.orderprocessing.service.EmployeeServiceImpl;

public class Test {

	public static void main(String args[])
	{
		
		EmployeeService emp = new EmployeeServiceImpl();
		
		emp.login("1001","amol");
		
		
	}
	
	
	
}
