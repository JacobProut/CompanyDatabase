package Controller;

import DAO.EmployeesDAO;
import DAO.JDBC;
import Models.Employees;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.OK;

public class employeeMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML private MenuBar menuBar;
    @FXML private Menu profileMenu;
    @FXML private MenuItem profileMenuLogout;
    @FXML private TextField searchTextField;

    @FXML private TableView<Employees> employeeTableView;
    @FXML private TableColumn<Employees, Integer> tableEmployeeID;
    @FXML private TableColumn<Employees, String> tableFirstName;
    @FXML private TableColumn<Employees, String> tableLastName;
    @FXML private TableColumn<Employees, String> tableEmployeeAddress;
    @FXML private TableColumn<Employees, Integer> tableEmployeePostalCode;
    @FXML private TableColumn<Employees, String> tableEmployeePhoneNumber;
    @FXML private TableColumn<Employees, Integer> tableEmployeeDivisionID;
    @FXML private TableColumn<Employees, Timestamp> tableEmployeeCreatedDate;
    @FXML private TableColumn<Employees, String> tableEmployeeCreatedBy;
    @FXML private TableColumn<Employees, Timestamp> tableEmployeeLastUpdated;
    @FXML private TableColumn<Employees, String> tableEmployeeLastUpdatedBy;
    @FXML private TableColumn<Employees, Integer> tableEmployeeStoreId;

    @FXML private ComboBox<Employees> viewEmployeesCombo;

    @FXML private Label timeLabel;
    @FXML private Label zoneID;

    private final boolean timeStopped = false;

    @FXML
    void createEmployeeButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(mainMenuController.class.getResource("/view/employeeCreationForm.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Employee Creation");
        System.out.println("Opening Employee Creation Menu");
    }

    @FXML void deleteEmployeeButton(ActionEvent event) {

    }

    @FXML void editEmployeeButton(ActionEvent event) throws IOException {
        if (employeeTableView.getSelectionModel().isEmpty()) {
           // errorCode(5);
            System.out.println("Null Selection while trying to Update a Employee.");
        }
        else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader loadupper = new FXMLLoader(getClass().getResource("/view/employeeModificationForm.fxml"));
            scene = loadupper.load();
            Employees employeeSelected = employeeTableView.getSelectionModel().getSelectedItem();
            employeeModificationFormController controller = loadupper.getController();
            controller.employeeSelection(employeeTableView.getSelectionModel().getSelectedIndex(), employeeSelected);
            stage.setScene(new Scene(scene));
            stage.show();
            stage.centerOnScreen();
            stage.setTitle("Customer Modification Page");
            System.out.println("Switching to Customer Modification Form.");
        }
    }

    @FXML
    void logoutSystem(ActionEvent event) {
        System.out.println("Logout Button Selected.");
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("Attempting to log out\r" + "Any unsaved data will be LOST!");
        alert.setContentText("Are you sure you want to continue?\r" + "Click 'OK' to confirm exit.\r" + "Click 'Cancel' to go back.");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == OK) {
            JDBC.closeConnection();
            System.out.println("Shutting down Application.");
            System.exit(0);
        }
        if (confirmation.isPresent() && confirmation.get() == CANCEL) {
            System.out.println("Logout canceled.");
        }
    }


    @FXML
    void returnToMainMenuButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainMenu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Database Main Menu");
        System.out.println("Returning to Main Menu");
    }

    @FXML
    void viewEmployeesComboAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneID.setText(String.valueOf(ZoneId.systemDefault()));
        timeLabel.setText(displayCurrentTime());
        viewEmployeesCombo.setPromptText("All Employees");

        //Populate TableView
        employeeTableView.setItems(EmployeesDAO.getAllEmployees());
        tableEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tableFirstName.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));
        tableLastName.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
        tableEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        tableEmployeePostalCode.setCellValueFactory(new PropertyValueFactory<>("employeePostalCode"));
        tableEmployeePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("employeePhoneNumber"));
        tableEmployeeDivisionID.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        tableEmployeeCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        tableEmployeeCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        tableEmployeeLastUpdated.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
        tableEmployeeLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        tableEmployeeStoreId.setCellValueFactory(new PropertyValueFactory<>("storeId"));

    }


    public static void returnToEmployeeViewList(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(customerMenuController.class.getResource("/view/employeeMenu.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        stage.setTitle("Employee View List");
    }

        private String displayCurrentTime() {
        Thread currentTime = new Thread(() -> {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("hh:mm:ss a");

            while(!timeStopped) {
                try {
                    sleep(1);
                    //Needed this for time to render properly.
                }
                catch (Exception e) {
                    System.out.println("Cannot Display time!");
                    throw new RuntimeException(e);
                }
                final String showCurrentTime = simpleFormat.format(new Date());
                Platform.runLater(()-> timeLabel.setText(showCurrentTime));
            }
        });
        currentTime.start();
        return null;
    }
}