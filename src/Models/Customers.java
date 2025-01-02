package Models;
import java.time.LocalDateTime;

public class Customers {

    private int customerId;

    //Foreign Keys
    private int divisionId;
    private int countryId;


    protected String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private String createdBy;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdated;
    private LocalDateTime createDate;

    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId, int countryId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.countryId = countryId;
    }

    /**
     * Constructor used in CustomerDAO.getAllCustomersById(int)
     * @param collectedId
     * @param collectedName
     */
    public Customers(int collectedId, String collectedName) {
        this.customerId = collectedId;
        this.customerName = collectedName;
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
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
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
        return this.customerName;
    }

    public boolean isInUSA() {
        return this.countryId == 1;
    }

    public boolean outOfUSA() {
        return !isInUSA();
    }

}