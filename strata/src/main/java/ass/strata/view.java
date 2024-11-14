package ass.strata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class view
{

    @FXML
    private TableView<Info> infoTable1;
    @FXML
    private TableView<Info> infoTable2;
    @FXML
    private TableView<Info> infoTable3;
    @FXML
    private TableColumn<Info, String> lecturerColumn;
    @FXML
    private TableColumn<Info, String> moduleColumn;
    @FXML
    private TableColumn<Info, String> semesterColumn;

    @FXML
    private Button CancelID;

    private Functions fn = new Functions();

    // Observable list to hold table data
    private ObservableList<Info> infoList = FXCollections.observableArrayList();


    // Method to handle cancel action
    @FXML
    void CancelAction(ActionEvent event)
    {
        // Clear the info list if desired
        infoList.clear();

        // Close the current stage (window)
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize()
    {
        // Set up table columns with the Info class properties
        lecturerColumn.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));

        // Attach the observable list to the table
        infoTable1.setItems(infoList);
        infoTable2.setItems(infoList);
        infoTable3.setItems(infoList);
    }

    @FXML
    private void viewAllInfo(ActionEvent actionEvent)
    {
        infoList.clear(); // Clear the list before reloading

        try
        {
            // Retrieve data from database
            String allLecturers = fn.viewAllLecturers();
            String allModules = fn.viewAllModules();
            String allSemesters = fn.viewAllSemesters();

            // Split data into arrays by new lines to process each row
            String[] lecturers = allLecturers.split("\n");
            String[] modules = allModules.split("\n");
            String[] semesters = allSemesters.split("\n");

            // Populate infoList with the rows for each category
            int maxRows = Math.max(lecturers.length, Math.max(modules.length, semesters.length));
            for (int i = 0; i < maxRows; i++)
            {
                String lecturer = i < lecturers.length ? lecturers[i] : "";
                String module = i < modules.length ? modules[i] : "";
                String semester = i < semesters.length ? semesters[i] : "";
                infoList.add(new Info(lecturer, module, semester));
            }
        }

        catch (SQLException e)
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "ERROR", "COULD NOT RETRIEVE INFORMATION. " + e.getMessage());
        }
    }

    // Inner class to represent each row in the table
    public static class Info
    {
        private final String lecturer;
        private final String module;
        private final String semester;

        public Info(String lecturer, String module, String semester)
        {
            this.lecturer = lecturer;
            this.module = module;
            this.semester = semester;
        }

        public String getLecturer()
        {
            return lecturer;
        }

        public String getModule()
        {
            return module;
        }

        public String getSemester()
        {
            return semester;
        }

    }
}
