package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addSemester
{

    @FXML
    private TextField semesterField;
    Functions fn = new Functions();

    @FXML
    private Button CancelID;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        semesterField.clear();

    }

    //button method to close an already open interface(addSemester as an fxml file)
    @FXML
    void CancelAction(ActionEvent event)
    {

        // Clear the text fields
        clearFields();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    //button method to add a semester
    @FXML
    private void addSemester(ActionEvent event) throws SQLException
    {
        String semester = semesterField.getText();

        //to call the add module method from the functions class
        fn.addSemester(semester);
        clearFields();
    }


}
