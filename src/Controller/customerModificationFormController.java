package Controller;

import DAO.CountriesDAO;
import DAO.CustomersDAO;
import DAO.First_Level_DivisionsDAO;
import Models.Countries;
import Models.Customers;
import Models.First_Level_Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static utility.errorMessages.customerCreationAndUpdateErrorCodes;

/**
 * customerModificationFormController is used to modify selected Customers
 */
public class customerModificationFormController implements Initializable {
    Parent scene;
    Stage stage;

    /**
     * ComboBox Declarations
     */
    @FXML private ComboBox<Countries> modificationCountryPicker;
    @FXML private ComboBox<First_Level_Divisions> divisionPicker;

    /**
     * TextField Declarations
     */
    @FXML private TextField modificationCustomerAddress;
    @FXML private TextField modificationCustomerID;
    @FXML private TextField modificationCustomerName;
    @FXML private TextField modificationCustomerPhoneNumber;
    @FXML private TextField modificationCustomerPostalCode;

    /**
     * selectedIndex & selectedCustomer are used for customerSelection()
     */
    public int selectedIndex;
    Customers selectedCustomer = new Customers(1, "name", "n", "n", "n", LocalDateTime.now(), "script", LocalDateTime.now(), "script", 1, 1);

    /**
     * onActionModificationCancel(ActionEvent) is used to return to customerMenu.fxml[Customer View List]
     *      - Prompt pulls up asking if the user wants to return to the Customer View List
     *          - Pressing 'OK' sends the user back to customerMenu.fxml
     * @param event
     * @throws IOException
     */
    @FXML void onActionModificationCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Modification Page");
        alert.setHeaderText("Are you sure you want to leave without saving?");
        alert.setContentText("Click 'OK' to confirm deletion.\r" + "Click 'Cancel' to go back.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.centerOnScreen();
            stage.setTitle("Customer View List");
            System.out.println("Returning to Customer View List.");
        }

    }

    /**
     * onActionModificationCountryPicker(ActionEvent) if modificationsCountryPicker value changes, then divisionsPicker is set to null
     *  Then divisionPicker should have a new selection of items to choose from.
     * @param event
     */
    @FXML void onActionModificationCountryPicker(ActionEvent event) {
        divisionPicker.setValue(null);
        Countries list = modificationCountryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(list.getCountryId()));

    }

    /**
     * onActionUpdateCustomerButton(ActionEvent) method to Modify selected Customer[customerSelection()] + add it to database
     * @param event
     */
    @FXML void onActionUpdateCustomerButton(ActionEvent event) {

        try {
            if (updateCustomerValidation()) {
                int updateCustomerID = Integer.parseInt(modificationCustomerID.getText());
                String updateCustomerName = modificationCustomerName.getText();
                String updateCustomerAddress = modificationCustomerAddress.getText();
                String updateCustomerPostalCode = modificationCustomerPostalCode.getText();
                String updateCustomerPhoneNumber = modificationCustomerPhoneNumber.getText();
                int updateCustomerDivisionId = divisionPicker.getValue().getDivisionId();
                LocalDateTime updateLastUpdated = LocalDateTime.now();
                String updateLastUpdatedBy = "User";

                CustomersDAO.updateCustomer(updateCustomerID, updateCustomerName, updateCustomerAddress, updateCustomerPostalCode, updateCustomerPhoneNumber, updateLastUpdated, updateLastUpdatedBy, updateCustomerDivisionId);
                customerMenuController.returnToCustomerViewList(event);
            }
        }
        catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * customerSelection used in customerMenuController.onActionUpdateCustomer(ActionEvent) to get selected customer from tableview and put information into customerModificationFormController fields.
     * @param index
     * @param selectedCustomer
     * @throws SQLException
     */
    public void customerSelection(int index, Customers selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        this.selectedIndex = index;
        modificationCustomerID.setText(String.valueOf(selectedCustomer.getCustomerId()));
        modificationCustomerName.setText(selectedCustomer.getCustomerName());
        modificationCustomerAddress.setText(selectedCustomer.getCustomerAddress());
        modificationCustomerPostalCode.setText(selectedCustomer.getCustomerPostalCode());
        modificationCustomerPhoneNumber.setText(selectedCustomer.getCustomerPhoneNumber());
        First_Level_Divisions selectedDivID = First_Level_DivisionsDAO.getSelectedCustomerDivisionLevel(selectedCustomer.getDivisionId());
        divisionPicker.setValue(selectedDivID);
        Countries selectedCountryId = CountriesDAO.getSelectedCustomerCountry(selectedCustomer.getCountryId());
        modificationCountryPicker.setValue(selectedCountryId);
        Countries selectedCountryNDiv = modificationCountryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(selectedCountryNDiv.getCountryId()));
    }

    /**
     * updateCustomerValidation() checks to make sure textFields, and ComboBoxes are not empty or Null
     * @return false or true
     */
    public boolean updateCustomerValidation() {
        // FINISH CUSTOMER ERROR CODES IN ERRORMESSAGES.JAVA
        if (modificationCustomerID.getText().isEmpty() && modificationCustomerAddress.getText().isEmpty() && modificationCustomerPostalCode.getText().isEmpty() && modificationCustomerPhoneNumber.getText().isEmpty() && modificationCountryPicker.getSelectionModel().isEmpty() && divisionPicker.getSelectionModel().isEmpty()) {
            customerCreationAndUpdateErrorCodes(0);
            return false;
        } else if (modificationCustomerName.getText().isBlank() || modificationCustomerName.getText().isEmpty()) {
            customerCreationAndUpdateErrorCodes(1);
            return false;
        } else if (modificationCustomerAddress.getText().isBlank() || modificationCustomerAddress.getText().isEmpty()) {
            customerCreationAndUpdateErrorCodes(2);
            return false;
        } else if (modificationCustomerPostalCode.getText().isBlank() || modificationCustomerPostalCode.getText().isEmpty()) {
            customerCreationAndUpdateErrorCodes(3);
            return false;
        } else if (modificationCustomerPhoneNumber.getText().isBlank() || modificationCustomerPhoneNumber.getText().isEmpty()) {
            customerCreationAndUpdateErrorCodes(4);
            return false;
        } else if (modificationCountryPicker.getValue() == null) {
            customerCreationAndUpdateErrorCodes(5);
            return false;
        } else if (divisionPicker.getValue() == null) {
            customerCreationAndUpdateErrorCodes(6);
            return false;
        } else if (divisionPicker.getValue() == null) {
            Countries countries = modificationCountryPicker.getValue();
            if (countries == null) {
                customerCreationAndUpdateErrorCodes(7);
                return false;
            }
        }
        return true;
    }

    /**
     * initialize sets modificationCountryPicker comboBox to getAllCountriesList()
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modificationCountryPicker.setItems(CountriesDAO.getAllCountriesList());
    }
}
