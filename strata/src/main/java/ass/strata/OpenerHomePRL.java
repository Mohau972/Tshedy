package ass.strata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class OpenerHomePRL
{

    @FXML
    private Button btnAdd; // Button to add a report

    @FXML
    private Button btnLogout; // Button to log out

    @FXML
    private Button CancelID; // Button to cancel the action and close the window

    @FXML
    private TextArea lblChallenges; // Text area for inputting challenges

    @FXML
    private ChoiceBox<String> lblClass; // ChoiceBox for selecting the class

    @FXML
    private ChoiceBox<String> lblModule; // ChoiceBox for selecting the module

    @FXML
    private TextArea lblRecommendations; // Text area for inputting recommendations

    Functions fn = new Functions(); // Instance of the Functions class to handle database operations

    // Method to clear all fields in the form
    public void clearFields()
    {
        lblClass.setValue(null); // Clear class selection
        lblModule.setValue(null); // Clear module selection
        lblChallenges.clear(); // Clear challenges text area
        lblRecommendations.clear(); // Clear recommendations text area
    }

    @FXML
    void Add(ActionEvent event)
    {
        // Retrieve data from UI elements
        String className = lblClass.getValue(); // Get selected class from ChoiceBox
        String moduleName = lblModule.getValue(); // Get selected module from ChoiceBox
        String challenges = lblChallenges.getText(); // Get text from challenges area
        String recommendations = lblRecommendations.getText(); // Get text from recommendations area

        try
        {
            // Insert the data into the database by calling the addWeeklyReport method
            fn.addWeeklyReport(className, moduleName, challenges, recommendations);
            clearFields(); // Clear fields after successful addition

        }
        catch (SQLException e)
        {
            // Show alert if adding the report was unsuccessful
            HelloApplication.showAlert(Alert.AlertType.INFORMATION, "ADDING REPORT", "REPORT ADDED UNSUCCESSFULLY");
        }
    }

    @FXML
    void CancelAction(ActionEvent event)
    {
        clearFields(); // Clear fields when action is canceled
        Stage stage = (Stage) CancelID.getScene().getWindow(); // Get the current stage (window)
        stage.close(); // Close the current stage
    }

    @FXML
    public void initialize()
    {
        // Sample class options and module options for ChoiceBoxes
        ObservableList<String> classOptions = FXCollections.observableArrayList("BSCIT", "BSCBIT", "BSCSM");
        ObservableList<String> moduleOptions = FXCollections.observableArrayList("Java", "Computer Support", "Mathematics");

        // Set the options in ChoiceBox components
        lblClass.setItems(classOptions);
        lblModule.setItems(moduleOptions);
    }

    @FXML
    void Logout(ActionEvent event) throws IOException
    {
        // Get the current stage from the logout button
        Stage currentStage = (Stage) btnLogout.getScene().getWindow();

        // Close the current stage to log out
        currentStage.close();

        // Open the login screen on a new stage
        Stage loginStage = new Stage();
        HelloApplication.page(loginStage, "login.fxml", "Login", 600, 400); // Display the login page
    }
}