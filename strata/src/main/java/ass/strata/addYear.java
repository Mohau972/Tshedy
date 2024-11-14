package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addYear
{
    @FXML
    private TextField academicYearField;

    Functions fn = new Functions();

    @FXML
    private Button CancelID;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        academicYearField.clear();

    }

    //button method to close an already open interface(addYear as an fxml file)
    @FXML
    void CancelAction(ActionEvent event)
    {
        // Clear the text fields
        clearFields();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    //button method to add a year
    @FXML
    private void addAcademicYear(ActionEvent event) throws SQLException
    {
        String year = academicYearField.getText();

        //to call the add year method from the functions class
        fn.addAcademicYear(year);
        clearFields();
    }

}