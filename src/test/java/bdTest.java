import forBD.CRUDUtils;
import forBD.Department;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class bdTest {
    @Test
    public void select() {
        List<Department> departments = CRUDUtils.selectMethodDepartment("Select * from company1.department");
        System.out.println(departments);
    }

    @Test
    public void selectHR() {
        List<Department> departments = CRUDUtils.selectMethodDepartment("Select * from company1.department where department_name='hr'");
        assertEquals(departments.size(),1);
        System.out.println(departments);
    }

    @Test
    public void create() {
       Department department = new Department("ex2","Example_Name2","Example_loc2");;
       // department.setDepartment_id("d6"); ///через сеттеры не получилось, поэтому сделал выше через объект
        //department.setDepartment_name("Example");
        //department.setLocation("Example");
        CRUDUtils.insertMethodDepartment(department);
        //AssertThat появилось в интерфейсе, или в кафке передалось
    }

    @Test
    public void update() {
        CRUDUtils.updateMethodDepartment ("d3", "School");
        //AssertThat появилось в интерфейсе, или в кафке передалось
    }

    @Test
    public void delete() {
        CRUDUtils.deleteMethodDepartment ("ex2");
        //AssertThat появилось в интерфейсе, или в кафке передалось
    }
}
