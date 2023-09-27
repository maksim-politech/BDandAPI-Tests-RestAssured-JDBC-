package forBD;

public class Department {
    private String departmentId;
    private String Department_name;
    private String Location;

    public Department(String departmentId, String department_name, String location) { ////ПОЧЕМУ не добавилсь this?
        this.departmentId = departmentId;
        Department_name = department_name;
        Location = location;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment_name() {
        return Department_name;
    }

    public void setDepartment_name(String department_name) {
        Department_name = department_name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "Department_id=" + departmentId +
                ", Department_name='" + Department_name + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }


}
