package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addStudents
{
    private Functions fn = new Functions();
    @FXML
    private TextField studentNameField;
    @FXML
    private TextField studentIDField;
    @FXML
    private TextField classField;

    @FXML
    private Button CancelID;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        studentNameField.clear();
        studentIDField.clear();
        classField.clear();

    }
    //button method to close an already open interface(addStudents as an fxml file)
    @FXML
    void CancelAction(ActionEvent event)
    {
        // Clear the text fields
        clearFields();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    //button method to add and assign students to a class
    @FXML
    private void addAssign(ActionEvent actionEvent) throws SQLException
    {
        String name = studentNameField.getText();
        String studentID = studentIDField.getText();
        String className = classField.getText();

        //to call the addAssign students method from the functions class
        fn.addAssignStudent(name, studentID, className);
        clearFields();
    }

}