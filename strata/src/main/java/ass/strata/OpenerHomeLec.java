package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenerHomeLec
{

    @FXML
    private Button btnLogOut; // Button component for logging out

    // Method to open the attendance tracking page when "Add Attendance" is selected
    public void addAttendance(ActionEvent actionEvent) throws IOException
    {
        Stage stage = new Stage(); // Create a new stage for the attendance page
        HelloApplication.page(stage, "tickStudents.fxml", "LOGIN", 600, 400); // Open the attendance page with specified title and size
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException
    {
        // Get the current stage (window) from the logout button
        Stage currentStage = (Stage) btnLogOut.getScene().getWindow();

        // Close the current stage to log the user out
        currentStage.close();

        // Open the login screen on a new stage
        Stage loginStage = new Stage();
        HelloApplication.page(loginStage, "login.fxml", "Login", 600, 400); // Display the login page with specified title and size
    }

    // Method to open the Learning Outcomes page when "Learning Outcomes" is selected
    public void learningOutcomes(ActionEvent actionEvent) throws IOException
    {
        Stage stage = new Stage(); // Create a new stage for the learning outcomes page
        HelloApplication.page(stage, "outcomes.fxml", "LOGIN", 600, 400); // Open the learning outcomes page with specified title and size
    }
}