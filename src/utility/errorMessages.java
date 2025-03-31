package utility;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.util.ResourceBundle;

/**
 * errorMessages was created to keep almost all Error Alerts into one spot.
 */
public abstract class errorMessages implements Initializable {
    static ResourceBundle languageBundle = ResourceBundle.getBundle("LanguageBundle/language");

    public static void loginErrorCodes(int codes) {
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
                //FINISH ERROR CODES
            }
    }

    public static void customerViewListErrorCodes(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (codes) {
            case 1:
                alert.setTitle((languageBundle.getString("NullSelectionWhileTryingToUpdateCustomer")));
                alert.setContentText(languageBundle.getString("NullSelectionWhileTryingToUpdateCustomerText"));
                alert.showAndWait();
                break;
        }
    }
    //ADD THIS TO LANGUAGEBUNDLES
    public static void customerCreationAndUpdateErrorCodes(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (codes) {
            case 0:
                alert.setTitle((languageBundle.getString("MutualOneOrMoreInvalidFieldsTitle")));
                alert.setContentText((languageBundle.getString("MutualOneOrMoreInvalidFieldsText")));
                alert.showAndWait();
                break;

            case 1: // empty name
                alert.setTitle((languageBundle.getString("CustomerEmptyNameFieldTitle")));
                alert.setContentText((languageBundle.getString("CustomerEmptyNameFieldText")));
                alert.showAndWait();
                break;

            case 2: // empty address
                alert.setTitle((languageBundle.getString("MutualEmptyAddressFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyAddressFieldText")));
                alert.showAndWait();
                break;

            case 3: // empty postal
                alert.setTitle((languageBundle.getString("MutualEmptyPostalFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyPostalFieldText")));
                alert.showAndWait();
                break;

            case 4: // empty phone
                alert.setTitle((languageBundle.getString("MutualEmptyPhoneNumberFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyPhoneNumberFieldText")));
                alert.showAndWait();
                break;

            case 5: // empty country
                alert.setTitle((languageBundle.getString("MutualEmptyCountryFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyCountryFieldText")));
                alert.showAndWait();
                break;

            case 6: // empty division
                alert.setTitle((languageBundle.getString("MutualEmptyDivisionFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyDivisionFieldText")));
                alert.showAndWait();
                break;

            case 7: // empty division
                alert.setTitle((languageBundle.getString("MutualNullDivisionFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualNullDivisionFieldText")));
                alert.showAndWait();
                break;
        }
    }



    public static void employeeViewListErrorCodes(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (codes) {
            case 1:
                alert.setTitle((languageBundle.getString("NullSelectionWhileTryingToUpdateEmployee")));
                alert.setContentText(languageBundle.getString("NullSelectionWhileTryingToUpdateEmployeeText"));
                alert.showAndWait();
                break;
        }
    }
    //ADD THIS TO LANGUAGEBUNDLES
    public static void employeeCreationAndUpdateErrorCodes(int codes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (codes) {
            case 0:
                alert.setTitle((languageBundle.getString("MutualOneOrMoreInvalidFieldsTitle")));
                alert.setContentText((languageBundle.getString("MutualOneOrMoreInvalidFieldsText")));
                alert.showAndWait();
                break;

            case 1: // empty first name
                alert.setTitle((languageBundle.getString("EmployeeEmptyFirstNameFieldTitle")));
                alert.setContentText((languageBundle.getString("EmployeeEmptyFirstNameFieldText")));
                alert.showAndWait();
                break;

            case 2: // empty last name
                alert.setTitle((languageBundle.getString("EmployeeEmptyLastNameFieldTitle")));
                alert.setContentText((languageBundle.getString("EmployeeEmptyLastNameFieldText")));
                alert.showAndWait();
                break;

            case 3: // empty address
                alert.setTitle((languageBundle.getString("MutualEmptyAddressFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyAddressFieldText")));
                alert.showAndWait();
                break;

            case 4: // empty postal
                alert.setTitle((languageBundle.getString("MutualEmptyPostalFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyPostalFieldText")));
                alert.showAndWait();
                break;

            case 5: // empty phone
                alert.setTitle((languageBundle.getString("MutualEmptyPhoneNumberFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyPhoneNumberFieldText")));
                alert.showAndWait();
                break;

            case 6: // empty country
                alert.setTitle((languageBundle.getString("MutualEmptyCountryFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyCountryFieldText")));
                alert.showAndWait();
                break;

            case 7: // empty division
                alert.setTitle((languageBundle.getString("MutualEmptyDivisionFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualEmptyDivisionFieldText")));
                alert.showAndWait();
                break;

            case 8: // empty division
                alert.setTitle((languageBundle.getString("MutualNullDivisionFieldTitle")));
                alert.setContentText((languageBundle.getString("MutualNullDivisionFieldText")));
                alert.showAndWait();
                break;

            case 9: // empty store
                alert.setTitle((languageBundle.getString("EmployeeEmptyStoreFieldTitle")));
                alert.setContentText((languageBundle.getString("EmployeeEmptyStoreFieldText")));
                alert.showAndWait();
                break;
        }
    }

}
