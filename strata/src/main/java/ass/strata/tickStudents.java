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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class tickStudents
{
    @FXML
    private TableView<info> students; // TableView for displaying student information and attendance

    @FXML
    private Button btnAdd; // Button for adding attendance

    @FXML
    private Button btnCancelID; // Button for canceling the operation
    Functions fn = new Functions();

    @FXML
    private TableColumn<info, String> presColumn; // TableColumn for attendance status ("tick")
    @FXML
    private TableColumn<info, String> studentColumn; // TableColumn for student names

    @FXML
    private Button CancelID; // Button to close the window

    // Observable list to hold table data
    private ObservableList<info> infoList = FXCollections.observableArrayList();

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

    // Method to initialize the TableView and its columns
    @FXML
    private void initialize()
    {
        // Set up table columns with the Info class properties
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        presColumn.setCellValueFactory(new PropertyValueFactory<>("tick"));

        // Make presColumn editable
        presColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        presColumn.setOnEditCommit(event -> {
            info selectedInfo = event.getRowValue();
            selectedInfo.setTick(event.getNewValue());
        });

        // Set the table to be editable
        students.setEditable(true);

        // Attach the observable list to the table
        students.setItems(infoList);
    }

    // Constructor to populate the table with data from the database
    public tickStudents() throws SQLException
    {
        // Retrieve data from the database for students and ticks
        String allStudents = fn.tickStudents();
        String allTicks = fn.tickStudents();

        // Split data into arrays by new lines to process each row
        String[] students = allStudents.split("\n");
        String[] ticks = allTicks.split("\n");

        // Populate infoList with rows for each category
        int maxRows = Math.max(students.length, ticks.length);
        for (int i = 0; i < maxRows; i++)
        {
            String student = i < students.length ? students[i] : "";
            String tick = i < ticks.length ? ticks[i] : "";
            infoList.add(new info(student, tick));
        }
    }

    // Method to add attendance data to the database
    public void ADD(ActionEvent actionEvent) throws SQLException
    {
        // Get the selected student info from the TableView
        info selectedInfo = students.getSelectionModel().getSelectedItem();

        try
        {
            String presency = "";

            // Check if presency field is empty before proceeding
            if (presency.isEmpty())
            {
                HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ASSIGNING");
            }

            else
            {
                // If a student is selected, update the database with their attendance
                if (selectedInfo != null)
                {
                    String studentID = selectedInfo.getStudent();
                    presency = selectedInfo.getTicks();
                    fn.attendancy(presency, studentID);

                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    // Inner class to hold student and attendance information for each row in the table
    public static class info
    {
        private final String student; // Student name
        private String tick; // Attendance status ("tick")

        // Constructor for info class
        public info(String student, String tick)
        {
            this.student = student;
            this.tick = tick;
        }

        // Getter for student name
        public String getStudent()
        {
            return student;
        }

        // Getter for attendance status
        public String getTicks()
        {
            return tick;
        }

        // Setter for attendance status
        public void setTick(String tick)
        {
            this.tick = tick;
        }
    }
}
