package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Login
{

    @FXML
    private Button btnFA;

    @FXML
    private Button btnLecturer;

    @FXML
    private Button btnPRL;

    // Method to handle login for Faculty Admin (FA) button
    @FXML
    void loginFA(ActionEvent event) throws IOException
    {
        // Create a new stage and open the Faculty Admin login page
        Stage stage = new Stage();
        HelloApplication.page(stage,"MyLog1.fxml","LOGIN", 600, 400);

    }

    // Method to handle login for Principal Lecturer (PRL) button
    @FXML
    void loginPRL(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"MyLog2.fxml","LOGIN", 600, 400);
    }

    // Method to handle login for Lecturer button
    @FXML
    void loginLecturer(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"MyLog3.fxml","LOGIN", 600, 400);
    }

}