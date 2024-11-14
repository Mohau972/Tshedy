package ass.strata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenerHomeFA
{
    @FXML
    private Button btnAddAssign;

    @FXML
    private Button btnAddYear;

    @FXML
    private Button btnClass;

    @FXML
    private Button btnLecturer;

    @FXML
    private Button btnModule;

    @FXML
    private Button btnModule1;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnRole;

    @FXML
    private Button btnSemester;

    @FXML
    private Button btnView;

    @FXML
    private Button btnLogout;

    @FXML
    private Button CancelID;

    private ActionEvent event;

    @FXML
    void addAcademicYear(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"addYear.fxml","ADD AND ASSIGN STUDENTS TO CLASSES", 600, 400);
    }

    //to add and to assign students
    @FXML
    void addAssign(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"addStudents.fxml","ADD AND ASSIGN STUDENTS TO CLASSES", 600, 400);
    }

    //to add class
    @FXML
    void addClass(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"addClass.fxml","ADD CLASS", 600, 400);
    }

    //to add lecturer
    @FXML
    void addLecturer(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"addLecturer.fxml","ADD LECTURER", 600, 400);
    }

    //to add Module
    @FXML
    void addModule(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"addModule.fxml","ADD MODULE", 600, 400);
    }

    //to add Semester
    @FXML
    void addSemester(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"addSemester.fxml","ADD SEMESTER", 600, 400);
    }

    //to assign Module
    @FXML
    void assignModule(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"assModule.fxml","ASSIGN MODULE", 600, 400);
    }

    //to assign lecturer Role
    @FXML
    void assignRole(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"assRole.fxml","ASSIGN ROLE", 600, 400);
    }

    //to view data(lecturers, semesters, and Modules)
    @FXML
    void view(ActionEvent event)throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage,"view.fxml","VIEW DATA", 675, 526);
    }

    @FXML
    void Logout(ActionEvent event) throws IOException
    {
        // Get the current stage from the logout button
        Stage currentStage = (Stage) btnLogout.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Open the login screen
        Stage loginStage = new Stage();
        HelloApplication.page(loginStage, "login.fxml", "Login", 600, 400);

    }

    @FXML
    void viewProfile(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        HelloApplication.page(stage, "viewProfile.fxml", "PROFILE", 400, 300);
    }

    @FXML
    void CancelAction(ActionEvent event)
    {
        Stage stage = (Stage) CancelID.getScene().getWindow();
        stage.close();
    }

}
