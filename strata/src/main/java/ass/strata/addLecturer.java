package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addLecturer
{
    @FXML
    private TextField lecturerNumberField;

    @FXML
    private TextField lecturerNameField;

    @FXML
    private Button CancelID;

    Functions fn = new Functions();
    private ActionEvent event;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        lecturerNameField.clear();
        lecturerNumberField.clear();

    }
    //button method to close an already open interface(addlecturer as an fxml file)
    @FXML
    void CancelAction(ActionEvent event)
    {
        this.event = event;

        // Clear the text fields
        clearFields();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    //button method to add a lecturer
    @FXML
    private void addLecturer(ActionEvent event) throws SQLException
    {
        String name = lecturerNameField.getText();
        String lecNum = lecturerNumberField.getText();

        //to call the add lecturer method from the functions class
        fn.addLecturer(name, lecNum);
        clearFields();
    }

}