package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class assRole
{
    @FXML
    private TextField lecturerNumberField;
    @FXML
    private TextField roleField;
    Functions fn = new Functions();

    @FXML
    private Button CancelID;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        // Clear the text fields
        lecturerNumberField.clear();
        roleField.clear();

    }
    //button method to close an already open interface(assign role as an fxml file)
    @FXML
    void CancelAction(ActionEvent event)
    {
        // Clear the text fields
        clearFields();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    //button method to assign a role to a lecturer
    @FXML
    private void assignLecturerRole(ActionEvent actionEvent) throws SQLException
    {
        String lec_number = lecturerNumberField.getText();
        String role = roleField.getText();

        //to call the assign role method from the functions class
        fn.assignRole(lec_number, role);
        clearFields();
    }
}
