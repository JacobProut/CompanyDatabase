package Controller;

import DAO.CountriesDAO;
import DAO.EmployeesDAO;
import DAO.First_Level_DivisionsDAO;
import DAO.StoresDAO;
import Models.Countries;
import Models.First_Level_Divisions;
import Models.Stores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import static utility.errorMessages.employeeCreationErrorCodes;

public class employeeCreationFormController implements Initializable {

    @FXML private ComboBox<Countries> countryPicker;
    @FXML private Button createButton;
    @FXML private Pane customerCreationFormPane;
    @FXML private ComboBox<First_Level_Divisions> divisionPicker;
    @FXML private TextField employeeAddress;
    @FXML private TextField employeeFirstName;
    @FXML private TextField employeeID;
    @FXML private TextField employeeLastName;
    @FXML private TextField employeePhoneNumber;
    @FXML private TextField employeePostalCode;
    @FXML private ComboBox<Stores> storeLocation;
    @FXML private Button returnButton;

    @FXML void onActionCountryPicker(ActionEvent event) {
        Countries list = countryPicker.getValue();
        divisionPicker.setItems(First_Level_DivisionsDAO.countryToDivision(list.getCountryId()));

    }

    @FXML void onActionCreateEmployee(ActionEvent event) {
        try {
            if (createEmployeeValidation()) {
                String firstName = employeeFirstName.getText();
                String lastName = employeeLastName.getText();
                String address = employeeAddress.getText();
                String postalCode = employeePostalCode.getText();
                String phoneNumber = employeePhoneNumber.getText();
                First_Level_Divisions divisionPickerValue = divisionPicker.getValue();
                int divisionId = divisionPickerValue.getDivisionId();
                LocalDateTime createDate = LocalDateTime.now();
                String createdBy = "Replace with login IDa";
                LocalDateTime lastUpdated = LocalDateTime.now();
                String lastUpdatedBy = "Replace with login ID";
                Stores storeValue = storeLocation.getValue();
                int storeId = storeValue.getStoreId();
                EmployeesDAO.createEmployee(firstName, lastName, address, postalCode, phoneNumber, divisionId, createDate, createdBy, lastUpdated, lastUpdatedBy, storeId);
                employeeMenuController.returnToEmployeeViewList(event);
            }
        } catch (Exception e) {
            System.out.println("Must be Error with Employee Creation");
            throw new RuntimeException(e);
        }
    }

    @FXML void onActionDivisionPicker(ActionEvent event) {

    }

    @FXML void onActionReturn(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(mainMenuController.class.getResource("/view/employeeMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("List of Employees");
        System.out.println("Returning to Employee View List");

    }

    @FXML void onActionStoreLocation(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryPicker.setItems(CountriesDAO.getAllCountriesList());
        countryPicker.setPromptText("Select a Country");
        divisionPicker.setPromptText("Select a Division");
        storeLocation.setPromptText("Select a Store");
        storeLocation.setItems(StoresDAO.getAllStores());
    }

    public boolean createEmployeeValidation() {
        if (employeeFirstName.getText().isEmpty() && employeeLastName.getText().isEmpty() && employeeAddress.getText().isEmpty() && employeePostalCode.getText().isEmpty() && employeePhoneNumber.getText().isEmpty() && countryPicker.getSelectionModel().isEmpty() && divisionPicker.getSelectionModel().isEmpty() && storeLocation.getSelectionModel().isEmpty()) {
            employeeCreationErrorCodes(0);
            return false;
        } else if (employeeFirstName.getText().isBlank() || employeeFirstName.getText().isEmpty()) {
            employeeCreationErrorCodes(1);
            return false;
        }
        else if (employeeLastName.getText().isBlank() || employeeLastName.getText().isEmpty()) {
            employeeCreationErrorCodes(2);
            return false;
        } else if (employeeAddress.getText().isBlank() || employeeAddress.getText().isEmpty()) {
            employeeCreationErrorCodes(3);
            return false;
        } else if (employeePostalCode.getText().isBlank() || employeePostalCode.getText().isEmpty()) {
            employeeCreationErrorCodes(4);
            return false;
        } else if (employeePhoneNumber.getText().isBlank() || employeePhoneNumber.getText().isEmpty()) {
            employeeCreationErrorCodes(5);
            return false;
        } else if (countryPicker.getSelectionModel().isEmpty()) {
            employeeCreationErrorCodes(6);
            return false;
        } else if (divisionPicker.getSelectionModel().isEmpty()) {
            employeeCreationErrorCodes(7);
            return false;
        } else if (divisionPicker.getValue() == null) {
            Countries countries = countryPicker.getValue();
            if (countries == null) {
                employeeCreationErrorCodes(8);
                return false;
            }
        } else if (storeLocation.getSelectionModel().isEmpty()) {
            employeeCreationErrorCodes(9);
            return false;
        }

        return true;
    }
}
