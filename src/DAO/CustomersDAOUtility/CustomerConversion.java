package DAO.CustomersDAOUtility;

import Models.Customers;
import Models.SubClasses.CustomersInUSA;
import Models.SubClasses.CustomersOutsideOfUSA;

import java.time.LocalDateTime;

public class CustomerConversion {
    public static Customers createCustomer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId, int countryId) {
        if (countryId == 1) {
            return new CustomersInUSA(customerId, customerName, customerAddress, customerPostalCode, customerPhoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId);
        } else {
            return new CustomersOutsideOfUSA(customerId, customerName, customerAddress, customerPostalCode, customerPhoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, divisionId, countryId);
        }
    }
}
