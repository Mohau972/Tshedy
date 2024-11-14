package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class assModule
{
    @FXML
    private TextField classField;

    @FXML
    private TextField lecturerNumberField11;

    @FXML
    private TextField lecturerModule;

    @FXML
    private TextField semesterField1;

    @FXML
    private TextField yearField;

    Functions fn = new Functions();
    @FXML
    private Button CancelID;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        // Clear the text fields
        lecturerNumberField11.clear();
        lecturerModule.clear();
        semesterField1.clear();
        yearField.clear();
        classField.clear();

    }
    //button method to close an already open interface(assModule as an fxml file)
    @FXML
    void CancelAction(ActionEvent event)
    {
        // Clear the text fields
        clearFields();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    //button method to assign a module to a lecturer in a given class, semester and academic year
    @FXML
    void assignLecModule(ActionEvent event) throws SQLException
    {
        String lec_number = lecturerNumberField11.getText();
        String Module = lecturerModule.getText();
        String className = classField.getText();
        String semester = semesterField1.getText();
        String academic_year = yearField.getText();

        //to call the assign module method from the functions class
        fn.assignModule(lec_number, Module, className, semester, academic_year);
        clearFields();

    }
}