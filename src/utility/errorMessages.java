package utility;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.util.ResourceBundle;

/**
 * errorMessages was created to keep almost all Error Alerts into one spot.
 */
public abstract class errorMessages implements Initializable {
    static ResourceBundle languageBundle = ResourceBundle.getBundle("LanguageBundle/language");

    public static void errorCode(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            switch (codes) {
                case 1: //Empty username field
                    alert.setTitle(languageBundle.getString("EmptyUsernameField"));
                    alert.setContentText(languageBundle.getString("EmptyUserName"));
                    alert.showAndWait();
                    break;

                case 2: //Empty password field
                    alert.setTitle(languageBundle.getString("EmptyPasswordField"));
                    alert.setContentText((languageBundle.getString("EmptyPassword")));
                    alert.showAndWait();
                    break;

                case 3: //Incorrect username
                    alert.setTitle((languageBundle.getString("IncorrectUsernameAndPassword")));
                    alert.setContentText(languageBundle.getString("wrongUserNameAndPassword"));
                    alert.showAndWait();
                    break;

                case 4:
                    alert.setTitle((languageBundle.getString("EmptyUserAndPassword")));
                    alert.setContentText(languageBundle.getString("EmptyUsernPass"));
                    alert.showAndWait();
                    break;

                case 5:
                    alert.setTitle((languageBundle.getString("NullSelectionWhileTryingToUpdateCustomer")));
                    alert.setContentText(languageBundle.getString("NullSelectionWhileTryingToUpdateCustomerText"));
                    alert.showAndWait();
                    break;
            }
    }

    //ADD THIS TO LANGUAGEBUNDLES
    public static void employeeCreationErrorCodes(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (codes) {
            case 0:
                alert.setTitle("One or more invalid fields");
                alert.setContentText("One or more invalid fields, please check them over and try again.");
                alert.showAndWait();
                break;

            case 1: // empty first name
                alert.setTitle("Empty First Name Field");
                alert.setContentText("Please add a First Name.");
                alert.showAndWait();
                break;

            case 2: // empty last name
                alert.setTitle("Empty Last Name Field");
                alert.setContentText("Please add a Last Name.");
                alert.showAndWait();
                break;

            case 3: // empty address
                alert.setTitle("Empty Address Field");
                alert.setContentText("Please add an Address.");
                alert.showAndWait();
                break;

            case 4: // empty postal
                alert.setTitle("Empty Postal Code Field");
                alert.setContentText("Please add a Postal Code.");
                alert.showAndWait();
                break;

            case 5: // empty phone
                alert.setTitle("Empty Phone Number Field");
                alert.setContentText("Please add a Phone number.");
                alert.showAndWait();
                break;

            case 6: // empty country
                alert.setTitle("Empty Country Box");
                alert.setContentText("Please add a Country.");
                alert.showAndWait();
                break;

            case 7: // empty division
                alert.setTitle("Empty Division Box");
                alert.setContentText("Please add a Division.");
                alert.showAndWait();
                break;

            case 8: // empty division
                alert.setTitle("Null Division Box");
                alert.setContentText("Either ther is no Divisions in the database or a null error has occured.");
                alert.showAndWait();
                break;

            case 9: // empty store
                alert.setTitle("Empty Store Field");
                alert.setContentText("Please add an associated store to this employee.");
                alert.showAndWait();
                break;
        }
    }

}
