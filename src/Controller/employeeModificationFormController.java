package Controller;

import DAO.CountriesDAO;
import DAO.EmployeesDAO;
import DAO.First_Level_DivisionsDAO;
import DAO.StoresDAO;
import Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static utility.errorMessages.employeeCreationAndUpdateErrorCodes;

public class employeeModificationFormController implements Initializable {
    Parent scene;
    Stage stage;

    @FXML private ComboBox<Countries> countryPicker;
    @FXML private ComboBox<First_Level_Divisions> divisionPicker;
    @FXML private ComboBox<Stores> storesComboBox;

    @FXML private Button createButton;
    @FXML private Button returnButton;

    @FXML private Pane customerCreationFormPane;

    @FXML private TextField employeeAddress;
    @FXML private TextField employeeFirstName;
    @FXML private TextField employeeID;
    @FXML private TextField employeeLastName;
    @FXML private TextField employeePhoneNumber;
    @FXML private TextField employeePostalCode;

    public int selectedIndex;
    Employees selectedEmployee = new Employees(1, "name", "n", "n", "n", "n", 1, LocalDateTime.now(), "script", LocalDateTime.now(), "script", 1, 1);



    @FXML void onActionCountryPicker(ActionEvent  event) {
        divisionPicker.setValue(null);
        Countries list = countryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(list.getCountryId()));
    }

    @FXML void onActionDivisionPicker(ActionEvent event) {

    }

    @FXML void onActionUpdateEmployee(ActionEvent event) {
        try {
            if (createEmployeeValidation()) {
                int id = Integer.parseInt(employeeID.getText());
                String firstName = employeeFirstName.getText();
                String lastName = employeeLastName.getText();
                String address = employeeAddress.getText();
                String postalCode = employeePostalCode.getText();
                String phoneNumber = employeePhoneNumber.getText();
                First_Level_Divisions divisionPickerValue = divisionPicker.getValue();
                int divisionId = divisionPickerValue.getDivisionId();
                LocalDateTime lastUpdated = LocalDateTime.now();
                String lastUpdatedBy = "Replace with login ID";
                Stores storeValue = storesComboBox.getValue();
                int storeId = storeValue.getStoreId();
                EmployeesDAO.updateEmployee(id, firstName, lastName, address, postalCode, phoneNumber, divisionId, lastUpdated, lastUpdatedBy, storeId);
                employeeMenuController.returnToEmployeeViewList(event);
            }
        } catch (Exception e) {
            System.out.println("Must be Error with Employee Creation");
            throw new RuntimeException(e);
        }

    }


    @FXML void onActionReturn(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Employee Modification Page");
        alert.setHeaderText("Are you sure you want to leave without saving?");
        alert.setContentText("Click 'OK' to confirm deletion.\r" + "Click 'Cancel' to go back.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/employeeMenu.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
            stage.centerOnScreen();
            stage.setTitle("Employee View List");
            System.out.println("Returning to Employee View List.");
        }
    }

    @FXML void onActionStoreLocation(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryPicker.setItems(CountriesDAO.getAllCountriesList());
        storesComboBox.setItems(StoresDAO.getAllStores());
    }


    public void employeeSelection(int index, Employees selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
        this.selectedIndex = index;
        employeeID.setText(String.valueOf(selectedEmployee.getEmployeeId()));
        employeeFirstName.setText(selectedEmployee.getEmployeeFirstName());
        employeeLastName.setText(selectedEmployee.getEmployeeLastName());
        employeeAddress.setText(selectedEmployee.getEmployeeAddress());
        employeePostalCode.setText(selectedEmployee.getEmployeePostalCode());
        employeePhoneNumber.setText(selectedEmployee.getEmployeePhoneNumber());
        First_Level_Divisions selectedDivID = First_Level_DivisionsDAO.getSelectedCustomerDivisionLevel(selectedEmployee.getDivisionId());
        divisionPicker.setValue(selectedDivID);
        Countries selectedCountryId = CountriesDAO.getSelectedEmployeeCountry(selectedEmployee.getCountryId());
        countryPicker.setValue(selectedCountryId);
        Countries selectedCountryNDiv = countryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(selectedCountryNDiv.getCountryId()));
        Stores selectedStoreId = StoresDAO.getSelectedEmployeeStore(selectedEmployee.getStoreId());
        storesComboBox.setValue(selectedStoreId);
    }

    public boolean createEmployeeValidation() {
        if (employeeFirstName.getText().isEmpty() && employeeLastName.getText().isEmpty() && employeeAddress.getText().isEmpty() && employeePostalCode.getText().isEmpty() && employeePhoneNumber.getText().isEmpty() && countryPicker.getSelectionModel().isEmpty() && divisionPicker.getSelectionModel().isEmpty() && storesComboBox.getSelectionModel().isEmpty()) {
            employeeCreationAndUpdateErrorCodes(0);
            return false;
        } else if (employeeFirstName.getText().isBlank() || employeeFirstName.getText().isEmpty()) {
            employeeCreationAndUpdateErrorCodes(1);
            return false;
        } else if (employeeLastName.getText().isBlank() || employeeLastName.getText().isEmpty()) {
            employeeCreationAndUpdateErrorCodes(2);
            return false;
        } else if (employeeAddress.getText().isBlank() || employeeAddress.getText().isEmpty()) {
            employeeCreationAndUpdateErrorCodes(3);
            return false;
        } else if (employeePostalCode.getText().isBlank() || employeePostalCode.getText().isEmpty()) {
            employeeCreationAndUpdateErrorCodes(4);
            return false;
        } else if (employeePhoneNumber.getText().isBlank() || employeePhoneNumber.getText().isEmpty()) {
            employeeCreationAndUpdateErrorCodes(5);
            return false;
        } else if (countryPicker.getValue() == null) {
            employeeCreationAndUpdateErrorCodes(6);
            return false;
        } else if (divisionPicker.getValue() == null) {
            employeeCreationAndUpdateErrorCodes(7);
            return false;
        } else if (divisionPicker.getValue() == null) {
            Countries countries = countryPicker.getValue();
            if (countries == null) {
                employeeCreationAndUpdateErrorCodes(8);
                return false;
            }
        } else if (storesComboBox.getValue() == null) {
            employeeCreationAndUpdateErrorCodes(9);
            return false;
        }
        return true;
    }
}
