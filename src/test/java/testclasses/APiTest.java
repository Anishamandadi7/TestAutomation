package testclasses;

import org.testng.annotations.Test;

import endpoints.EndPoints;
import utils.RestAssuredBase;

public class APiTest extends BaseTest {
	RestAssuredBase restAssuredBase;
	String employee_id=null;
	public APiTest() {

		restAssuredBase = new RestAssuredBase();
	}

	@Test(priority=0)
	
	public void createEmployee() {
		extentTest=extent.createTest("CreateEmployee");
		restAssuredBase.post(EndPoints.create_employee, "post");
		restAssuredBase.validate200(extentTest);
		System.out.println(restAssuredBase.getReponse());
		employee_id=Integer.toString(restAssuredBase.response.jsonPath().getInt("data.id"));
		System.out.println(employee_id);
	}
	@Test(priority=1,dependsOnMethods="createEmployee")
	public void update_employee() {
		extentTest=extent.createTest("UpdateEmployee");
		restAssuredBase.put(EndPoints.update_employee+employee_id, "post");
		restAssuredBase.validate200(extentTest);
		System.out.println(restAssuredBase.getReponse());
	}
	
	@Test(priority=4)
	public void getEmployee() {
		extentTest=extent.createTest("GetEmployee");
		restAssuredBase.get(EndPoints.get_employees);
		restAssuredBase.validate200(extentTest);
		System.out.println(restAssuredBase.getReponse());
	}
	
	@Test(priority=2,dependsOnMethods="createEmployee")
	public void getEmployeeWithID() {
		extentTest=extent.createTest("GetEmployeeWithID");
		restAssuredBase.get(EndPoints.get_employee+employee_id);
		restAssuredBase.validate200(extentTest);
		System.out.println(restAssuredBase.getReponse());
	}
	@Test(priority=3,dependsOnMethods="createEmployee")
	public void deleteEmployee() {
		extentTest=extent.createTest("DeleteEmployee");
		restAssuredBase.delete(EndPoints.delete_employee+employee_id);
		restAssuredBase.validate200(extentTest);
		System.out.println(restAssuredBase.getReponse());
	}
}
