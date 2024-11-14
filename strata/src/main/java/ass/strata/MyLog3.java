package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MyLog3
{
    public Button sendID;
    Functions fn = new Functions();
    @FXML
    private Button CancelID;

    @FXML
    private Button SignUp;


    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUserName;
    private ActionEvent event;

    // Method to handle the login action when the "Login" button is clicked
    @FXML
    void Login2(ActionEvent event) throws IOException, SQLException
    {

        String username = txtUserName.getText();
        String password = txtPass.getText();

        fn.Login2(username, password);
        fn.logLogin(username);

    }

    // Method to clear the username and password text fields
    public void clearFields()
    {
        txtUserName.clear();
        txtPass.clear();

    }

    // Method to handle the sign-up action when the "Sign Up" button is clicked
    @FXML
    void signUp(ActionEvent event)
    {
        String username = txtUserName.getText();
        String password = txtPass.getText();

        try
        {
            fn.signUp(username, password);
            clearFields();
        }
        catch (SQLException e)
        {
            HelloApplication.showAlert(Alert.AlertType.INFORMATION,"SIGN UP","ERROR SIGNING UP"+e.toString());
        }
    }

    // Method to handle the cancel action when the "Cancel" button is clicked
    @FXML
    void CancelAction(ActionEvent event)
    {
        this.event = event;

        // Clear the text fields
        txtUserName.clear();
        txtPass.clear();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

}