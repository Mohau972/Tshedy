package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Outcomes
{
    @FXML
    private Button btnAdd;

    @FXML
    private Button CancelID;

    @FXML
    private TextArea txtChapter;

    @FXML
    private TextArea txtOutcomes;

    Functions fn = new Functions();

    public void clearFields()
    {
        txtChapter.clear();
        txtOutcomes.clear();

    }

    @FXML
    void Add(ActionEvent event) throws SQLException
    {
        String chapter = txtChapter.getText();
        String learningOut = txtOutcomes.getText();

            // Call the method to insert the data into the database
            fn.outcomes(chapter,learningOut);
            clearFields();

    }

    @FXML
    void CancelAction(ActionEvent event)
    {
        clearFields();
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

}