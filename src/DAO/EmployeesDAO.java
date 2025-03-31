package DAO;

import Models.Employees;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static DAO.JDBC.createConnection;

public class EmployeesDAO {

    public static ObservableList<Employees> getAllEmployees() {
        ObservableList<Employees> employeesObservableList = FXCollections.observableArrayList();
        try {
            String getAllCustomerData = "SELECT *, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM employees JOIN first_level_divisions ON employees.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY employees.Employee_ID";
            PreparedStatement getEmployee = createConnection().prepareStatement(getAllCustomerData);
            ResultSet result = getEmployee.executeQuery();

            while (result.next()) {
                int employeeId = result.getInt("Employee_ID");
                String firstName = result.getString("First_Name");
                String lastName = result.getString("Last_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String phoneNumber = result.getString("Phone");
                int divisionId = result.getInt("Division_ID");
                LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = result.getString("Created_By");
                LocalDateTime lastUpdated = result.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = result.getString("Last_Updated_By");
                int storeId = result.getInt("Store_ID");
                int countryId = result.getInt("Country_ID");

                Employees employees = new Employees(employeeId, firstName, lastName, address, postalCode, phoneNumber, divisionId, createDate, createdBy, lastUpdated, lastUpdatedBy, storeId, countryId);
                employeesObservableList.add(employees);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting ALL Employees");
            throw new RuntimeException(e);
        }
        return employeesObservableList;
    }


    public static void createEmployee(String firstName, String lastName, String address, String postalCode, String phoneNumber, int divisionId, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int storeId) throws SQLException {
        try {
            String employeeCreation = "INSERT INTO employees (First_Name, Last_Name, Address, Postal_Code, Phone, Division_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, Store_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement createEmployee = createConnection().prepareStatement(employeeCreation);

            createEmployee.setString(1, firstName);
            createEmployee.setString(2, lastName);
            createEmployee.setString(3, address);
            createEmployee.setString(4, postalCode);
            createEmployee.setString(5, phoneNumber);
            createEmployee.setInt(6, divisionId);
            createEmployee.setTimestamp(7, Timestamp.valueOf(createDate));
            createEmployee.setString(8, createdBy);
            createEmployee.setTimestamp(9, Timestamp.valueOf(lastUpdated));
            createEmployee.setString(10, lastUpdatedBy);
            createEmployee.setInt(11, storeId);

            createEmployee.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateEmployee(int employeeId, String firstName, String lastName, String address, String postalCode, String phoneNumber, int divisionId, LocalDateTime lastUpdated, String lastUpdatedBy, int storeId) throws SQLException {
        try {
            String updateEmployee = "UPDATE employees SET First_Name = ?, Last_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?, Last_Update = ?, Last_Updated_By = ?, Store_ID = ? WHERE Employee_ID = ?";
            PreparedStatement updateEmployeeToDB = createConnection().prepareStatement(updateEmployee);

            updateEmployeeToDB.setString(1, firstName);
            updateEmployeeToDB.setString(2, lastName);
            updateEmployeeToDB.setString(3, address);
            updateEmployeeToDB.setString(4, postalCode);
            updateEmployeeToDB.setString(5, phoneNumber);
            updateEmployeeToDB.setInt(6, divisionId);
            updateEmployeeToDB.setTimestamp(7, Timestamp.valueOf(lastUpdated));
            updateEmployeeToDB.setString(8, lastUpdatedBy);
            updateEmployeeToDB.setInt(9, storeId);
            updateEmployeeToDB.setInt(10, employeeId);

            updateEmployeeToDB.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Error with UPDATING Employee");
            throw new RuntimeException(e);
        }
    }
}
