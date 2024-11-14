package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addClass
{
    @FXML
    private TextField classNameField;

    Functions fn = new Functions();

    @FXML
    private Button CancelID;
    private ActionEvent event;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        classNameField.clear();

    }
    //button method to close an already open interface(addClass)
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

    //button method to add a class
    @FXML
    private void addClass(ActionEvent actionEvent) throws SQLException
    {
        String className = classNameField.getText();

        //to call the add class method from the functions class
        fn.addClass(className);
        clearFields();

    }


}

