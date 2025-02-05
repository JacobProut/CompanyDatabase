package DAO;

import DAO.CustomersDAOUtility.CustomerConversion;
import Models.Customers;
import Models.Stores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static DAO.JDBC.createConnection;

public class StoresDAO {

    public static ObservableList<Stores> getAllStores() {
        ObservableList<Stores> storesObservableList = FXCollections.observableArrayList();
        try {
            String getAllStores = "SELECT *, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM stores JOIN first_level_divisions ON stores.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY stores.Store_ID";
            PreparedStatement getStore = createConnection().prepareStatement(getAllStores);
            ResultSet result = getStore.executeQuery();

            while (result.next()) {
                int storeId = result.getInt("Store_ID");
                String phone = result.getString("Phone");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                int divisionId = result.getInt("Division_ID");
                int countryId = result.getInt("Country_ID");

                Stores stores = new Stores(storeId, phone, address, postalCode, countryId, divisionId);
                storesObservableList.add(stores);
            }
        }
        catch (SQLException e) {
            System.out.println("Error with getting ALL Stores");
            throw new RuntimeException(e);
        }
        return storesObservableList;
    }
}
