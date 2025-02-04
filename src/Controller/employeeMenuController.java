package Controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    @FXML private TableView<?> employeeTableView;
    @FXML private MenuBar menuBar;
    @FXML private Menu profileMenu;
    @FXML private MenuItem profileMenuLogout;
    @FXML private TextField searchTextField;

    @FXML private TableColumn<?, ?> tableEmployeeID;
    @FXML private TableColumn<?, ?> tableFirstName;
    @FXML private TableColumn<?, ?> tableLastName;
    @FXML private TableColumn<?, ?> tableEmployeeAddress;
    @FXML private TableColumn<?, ?> tableEmployeePostalCode;
    @FXML private TableColumn<?, ?> tableEmployeePhoneNumber;
    @FXML private TableColumn<?, ?> tableEmployeeDivisionID;
    @FXML private TableColumn<?, ?> tableEmployeeCreatedDate;
    @FXML private TableColumn<?, ?> tableEmployeeCreatedBy;
    @FXML private TableColumn<?, ?> tableEmployeeLastUpdated;
    @FXML private TableColumn<?, ?> tableEmployeeLastUpdatedBy;
    @FXML private TableColumn<?, ?> tableEmployeeStoreId;

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

    @FXML
    void deleteEmployeeButton(ActionEvent event) {

    }

    @FXML
    void editEmployeeButton(ActionEvent event) {

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