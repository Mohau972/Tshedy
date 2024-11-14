package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addModule
{

    @FXML
    private TextField moduleNameField;
    Functions fn = new Functions();

    @FXML
    private Button CancelID;

    // Method to clear the text fields that had been entered
    public void clearFields()
    {
        moduleNameField.clear();

    }

    //button method to close an already open interface(addModule as an fxml file)
    @FXML
    void CancelAction(ActionEvent event)
    {
        // Clear the text fields
        clearFields();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    //button method to add a module
    @FXML
    private void addModule(ActionEvent actionEvent) throws SQLException
    {
        String module = moduleNameField.getText();

        //to call the add module method from the functions class
        fn.addModule(module);
        clearFields();
    }

}
