package Controller;

import DAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static utility.errorMessages.loginErrorCodes;

/**
 * loginScreenForm is the first form that loads up before the Appointment Scheduler Application Form.
 */
public class loginScreenForm implements Initializable {
    @FXML private Button LoginButton;
    @FXML private Label ZoneID;
    @FXML private Label labelPassword;
    @FXML private Label labelTimezone;
    @FXML private Label labelUsername;
    @FXML private PasswordField passwordField;
    @FXML private TextField usernameField;

    public boolean isLoginTrue = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneID.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rbfr = ResourceBundle.getBundle("LanguageBundle/language_fr", Locale.getDefault());

                labelUsername.setText(rbfr.getString("Username"));
                labelPassword.setText(rbfr.getString("Password"));
                labelTimezone.setText(rbfr.getString("Timezone"));
                LoginButton.setText(rbfr.getString("Login"));
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED: "+ e.getMessage());
        }
    }

    public void onActionLogin(ActionEvent actionEvent) throws SQLException, IOException {
        if (!loginInfoValidation()) return;
        boolean isLoginValid = UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText());

        if (isLoginValid) {
            UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText());
            loadMainMenu(actionEvent);
            isLoginTrue = true;
            timesAttemptedToLogin();

        }
    }

    public Boolean loginInfoValidation() throws SQLException, IOException {
        if (usernameField.getText().isBlank() && passwordField.getText().isEmpty()) {
            loginErrorCodes(1);
            System.out.println("Failed Login Attempt: Blank User/Password");
            isLoginTrue = false;
            timesAttemptedToLogin();
            return false;
        } else if (usernameField.getText().isBlank() || usernameField.getText().isEmpty()) {
            loginErrorCodes(2);
            System.out.println("Failed Login Attempt: Blank or incorrect Username");
            isLoginTrue = false;
            timesAttemptedToLogin();
            return false;
        } else if (passwordField.getText().isBlank() || passwordField.getText().isEmpty()) {
            loginErrorCodes(3);
            System.out.println("Failed Login Attempt: Blank or incorrect Password");
            isLoginTrue = false;
            timesAttemptedToLogin();
            return false;
        } else if (!UsersDAO.verifyLoginInformation(usernameField.getText(), passwordField.getText())) {
            loginErrorCodes(4);
            System.out.println("Failed Login Attempt: Incorrect username or password");
            isLoginTrue = false;
            timesAttemptedToLogin();
        }
        return true;
    }

    public void timesAttemptedToLogin() throws IOException {
        //Setting dates/times for cleaner login_Activity.txt messages.
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter selectedTimeFormat = DateTimeFormatter.ofPattern("hh:mm:ss a");

        //Creating a file for FileWriter to type to.
        File file = new File("login_activity.txt");
        FileWriter loginWriter = new FileWriter(file.getAbsoluteFile(), true);

        if (isLoginTrue) {
            loginWriter.write("[" + currentDate + "]" + " User: '" + usernameField.getText() + "' has been Accepted in the database at a time of: [" + selectedTimeFormat.format(currentTime) + "]" + "\n");
        }
        else if (!isLoginTrue) {
            loginWriter.write("[" + currentDate + "]" + " User: '" + usernameField.getText() + "' has been Denied access to the database at a time of: [" + selectedTimeFormat.format(currentTime) + "]" + "\n");
        }
        loginWriter.close();
    }

    public static void loadMainMenu(ActionEvent event) throws IOException {
        Parent login = FXMLLoader.load(Objects.requireNonNull(loginScreenForm.class.getResource("/View/mainmenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene mainMenu = new Scene(login);
        stage.setTitle("Database Main Menu");
        stage.setScene(mainMenu);
        stage.show();
        stage.centerOnScreen();
        System.out.println("Successfully Logged in: Loading Database Manager");
    }

}