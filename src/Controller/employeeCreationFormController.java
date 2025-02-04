package Controller;

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
import java.util.Objects;
import java.util.ResourceBundle;

public class employeeCreationFormController implements Initializable {

    @FXML
    private ComboBox<?> countryPicker;

    @FXML
    private Button createButton;

    @FXML
    private Pane customerCreationFormPane;

    @FXML
    private ComboBox<?> divisionPicker;

    @FXML
    private TextField employeeAddress;

    @FXML
    private TextField employeeFirstName;

    @FXML
    private TextField employeeID;

    @FXML
    private TextField employeeLastName;

    @FXML
    private TextField employeePhoneNumber;

    @FXML
    private TextField employeePostalCode;

    @FXML
    private Button returnButton;

    @FXML
    void onActionCountryPicker(ActionEvent event) {

    }

    @FXML
    void onActionCreateEmployee(ActionEvent event) {

    }

    @FXML
    void onActionDivisionPicker(ActionEvent event) {

    }

    @FXML
    void onActionReturn(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(mainMenuController.class.getResource("/view/employeeMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("List of Employees");
        System.out.println("Returning to Employee View List");

    }

    @FXML
    void onActionStoreLocation(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
