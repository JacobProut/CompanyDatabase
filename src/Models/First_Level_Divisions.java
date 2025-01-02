package Models;

import java.time.LocalDateTime;

public class First_Level_Divisions {
    private int divisionId;

    //Foreign key
    private int countryId;

    private String divisionName;
    private String lastUpdatedBy;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;
    public First_Level_Divisions(int divisionId, String divisionName, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    /**
     * Constructor for First_Level_DivisionDAO.countryToDivision(int)
     * @param divisionId
     * @param divisionName
     * @param countryId
     */
    public First_Level_Divisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Constructor for First_Level_Divisions.getSelectedCustomerDivisionsLevel(int)
     * @param selectedDivId
     * @param selectedDivName
     */
    public First_Level_Divisions(int selectedDivId, String selectedDivName) {
        this.divisionId = selectedDivId;
        this.divisionName = selectedDivName;
    }
    public int getCountryId() {
        return countryId;
    }
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public int getDivisionId() {
        return divisionId;
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
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String toString() {
        return this.divisionName;
    }
}
