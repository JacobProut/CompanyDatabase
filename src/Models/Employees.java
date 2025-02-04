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
    private String createdBy;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdated;
    private LocalDateTime createDate;
}
