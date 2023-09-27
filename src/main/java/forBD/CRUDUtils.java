package forBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private static final String URL="jdbc:mysql://localhost:3306/mysql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    private static final String UPDATE_DEPARTMENT_NAME = "update company1.department set department_name=? where department_id = ?";
    private static Connection connection;//конектишион с помощью jdbc к нашей базе данных
    private static  String INSERT_DEPARTMENT ="INSERT INTO company1.department(department_id,department_name,location) values (?,?,?);";
    private static String DELETE_DEPARTMENT="DELETE from company1.department where department_id = ?";

    public static Connection getConnection() {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
       }

        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static List<Department> selectMethodDepartment(String query){
        List<Department> departments= new ArrayList<>(); //переменной departments с типом днаных List присваивается значение объекта ArrayList - это создали коллекцию
        try {
            getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
               String department_id = rs.getString("department_id");
                String department_name =rs.getString("department_name");
                String location =rs.getString("location");
                departments.add(new Department(department_id,department_name,location));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departments;
    }

    public static void insertMethodDepartment (Department department) {
       //List<Department> departments = new ArrayList<>();
        try {
            getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT);
            preparedStatement.setString(1,department.getDepartmentId());
            preparedStatement.setString(2,department.getDepartment_name());
           preparedStatement.setString(3,department.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMethodDepartment (String department_id, String department_name) {
        try {
            getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT_NAME);
            preparedStatement.setString(1,department_id);
            preparedStatement.setString(2,department_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteMethodDepartment (String department_id) {
        try {
            getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT);
            preparedStatement.setString(1,department_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
