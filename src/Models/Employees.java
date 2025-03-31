package Models;

import java.time.LocalDateTime;

public class Employees {
    private int employeeId;

    //Foreign Keys
    private int divisionId;
    private int countryId;
    private int storeId;


    protected String employeeFirstName;
    protected String employeeLastName;
    private String employeeAddress;
    private String employeePostalCode;
    private String employeePhoneNumber;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;


    public Employees(int employeeId, String employeeFirstName, String employeeLastName, String employeeAddress, String employeePostalCode, String employeePhoneNumber, int divisionId, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int storeId, int countryId) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAddress = employeeAddress;
        this.employeePostalCode = employeePostalCode;
        this.employeePhoneNumber = employeePhoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.storeId = storeId;
    }

    //used in modifying employee
    public Employees(int employeeId, String employeeFirstName, String employeeLastName, String employeeAddress, String employeePostalCode, String employeePhoneNumber, int divisionId, LocalDateTime lastUpdated, String lastUpdatedBy, int storeId, int countryId) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAddress = employeeAddress;
        this.employeePostalCode = employeePostalCode;
        this.employeePhoneNumber = employeePhoneNumber;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.storeId = storeId;
    }

    //Will be used for EmployeeDAO.getAllEmployeesById(int)
    public Employees(int collectedId, String collectedName) {
        this.employeeId = collectedId;
        this.employeeFirstName = collectedName;
    }

    public int getDivisionId() {
        return divisionId;
    }
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    public int getCountryId() {
        return countryId;
    }
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public int getStoreId() {
        return storeId;
    }
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


    public String getEmployeeFirstName() {
        return employeeFirstName;
    }
    public String getEmployeeLastName() {
        return employeeLastName;
    }
    public String getEmployeeAddress() {
        return employeeAddress;
    }
    public String getEmployeePostalCode() {
        return employeePostalCode;
    }
    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String toString() {
        return this.employeeFirstName;
    }

}
