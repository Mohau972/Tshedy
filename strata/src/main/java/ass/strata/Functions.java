package ass.strata;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Functions
{
    DBConnection  db;

    // Registers a new user with the provided username and password
    public void signUp(String user, String pass) throws SQLException
    {
        if (user.isEmpty() || pass.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR ", "PLEASE FILL IN ALL FIELDS BEFORE SIGNING UP.");
        }

        else
        {
        db = new DBConnection();
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        db.setStm(sql);
        db.getStm().setString(1,user);
        db.getStm().setString(2,pass);
        db.getStm().executeUpdate();

        HelloApplication.showAlert(Alert.AlertType.INFORMATION,"SIGN UP","USER SUCCESSFULLY SIGNED UP");
        db.CloseConnection();
        }

    }

    // Logs in a user and opens the Faculty Admin page if credentials are valid
    public void Login(String user, String pass) throws SQLException, IOException
    {
        if (user.isEmpty() || pass.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR ", "PLEASE FILL IN ALL FIELDS BEFORE SIGNING UP.");
        }
        else {
            String sql = "SELECT * FROM users where username = ? AND password = ?";
            db = new DBConnection();
            db.setStm(sql);
            db.getStm().setString(1, user);
            db.getStm().setString(2, pass);
            db.setRes();

            if (db.getRes().next())
            {
                String title = "Welcome to Faculty Admin Page";
                Stage stage = new Stage();
                HelloApplication.page(stage, "OpenerHomeFA.fxml", title.toUpperCase(), 675, 526);
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "LOGGING IN", "INVALID CREDENTIALS");
            }
        }
    }

    // Logs in a user and opens the Principal Lecturer page if credentials are valid
    public void Login1(String user, String pass) throws SQLException, IOException
    {
        if (user.isEmpty() || pass.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR ", "PLEASE FILL IN ALL FIELDS BEFORE SIGNING UP.");
            return;
        }
        else
        {
            String sql = "SELECT * FROM users where username = ? AND password = ?";
            db = new DBConnection();
            db.setStm(sql);
            db.getStm().setString(1, user);
            db.getStm().setString(2, pass);
            db.setRes();

            if (db.getRes().next())
            {
                String title = "Welcome to Principal Lecturer Page";
                Stage stage = new Stage();
                HelloApplication.page(stage, "OpenerHomePRL.fxml", title.toUpperCase(), 600, 400);
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "LOGGING IN", "INVALID CREDENTIALS");
            }
        }
    }

    // Logs in a user and opens the Lecturer page if credentials are valid
    public void Login2(String user, String pass) throws SQLException, IOException
    {
        if (user.isEmpty() || pass.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR ", "PLEASE FILL IN ALL FIELDS BEFORE SIGNING UP.");
            return;
        }
        else {
        String insertQuery = "SELECT * FROM users where username=? AND password=?";
        db = new DBConnection();
        db.setStm(insertQuery);
        db.getStm().setString(1, user);
        db.getStm().setString(2, pass);
        db.setRes();

        if (db.getRes().next())
        {
            String title = "Welcome to Lecturer Page";
            Stage stage = new Stage();
            HelloApplication.page(stage, "OpenerHomeLec.fxml", title.toUpperCase(), 600, 400);

        }
        else
        {
            HelloApplication.showAlert(Alert.AlertType.WARNING, "LOGGING IN", "INVALID CREDENTIALS");
        }
        }
    }

    // Method to log login details to a file
    public void logLogin(String username)
    {
        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             PrintWriter printWriter = new PrintWriter(fileWriter))
        {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            printWriter.println("USER: " + username + " LOGGED IN AT " + timeStamp);
        }

        catch (IOException e)
        {
            System.err.println("ERROR IN LOCATING THE FILE " + e.getMessage());
        }
    }

    // Adds a weekly report to the database
    public void addWeeklyReport(String className, String moduleName, String challenges, String recommendations) throws SQLException
    {
        if (className.isEmpty() || moduleName.isEmpty() || challenges.isEmpty() || recommendations.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill in all fields before adding.");

        }
        else {
            db = new DBConnection(); // Open database connection

            String insertQuery = "INSERT INTO weekly_reports (class, module, challenges, recommendations) VALUES (?, ?, ?, ?)";
            db.setStm(insertQuery);
            db.getStm().setString(1, className);
            db.getStm().setString(2, moduleName);
            db.getStm().setString(3, challenges);
            db.getStm().setString(4, recommendations);

            // Execute the insert query
            int rowsInserted = db.getStm().executeUpdate();

            if (rowsInserted > 0)
            {
                HelloApplication.showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "DATA ADDED SUCCESSFULLY!");
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING DATA", "ADDING DATA UNSUCCESSFUL");
            }
            db.CloseConnection();
        }
    }

    //Retrieves user details for the specified username
    public void getUserDetails(String username) throws SQLException
    {
        DBConnection db = new DBConnection();
        String query = "SELECT username FROM users WHERE username = ?";
        db.setStm(query);
        db.getStm().setString(1, username);
        ResultSet rs = db.getStm().executeQuery();

        if (rs.next())
        {
            HelloApplication.showAlert(Alert.AlertType.INFORMATION, "DATA SELECTION", "SUCCESSFULLY!");
        }
        else
        {
            HelloApplication.showAlert(Alert.AlertType.WARNING, "ADATA SELECTION", "UNSUCCESSFUL");
        }
        db.CloseConnection();
    }

    //Adds a new lecturer to the database
    public void addLecturer(String name, String lecNum) throws SQLException
    {
        try
        {

            if (name.isEmpty() || lecNum.isEmpty())
            {
                HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ADDING");

            }
            else
            {
                db = new DBConnection();

                String insertQuery = "INSERT INTO lecturers (name, lec_number) VALUES (?, ?)";
                db.setStm(insertQuery);
                db.getStm().setString(1, name);
                db.getStm().setString(2, lecNum);

                // Execute the insert query
                int rowsInserted = db.getStm().executeUpdate();

                if (rowsInserted > 0)
                {
                    //exception
                    HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING A LECTURER ","ADDITION SUCCECSSFULL!!");
                }
                else
                {
                    HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING DATA", "ADDING DATA UNSUCCESSFUL");
                }
                db.CloseConnection();
            }
        }
        catch (SQLException e)
        {
            HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING LECTURER","ERROR ADDING DATA"+e.toString());
        }

    }
    //Adds a new academic year to the database
    public void addAcademicYear(String year) throws SQLException
    {
        try {

            if (year.isEmpty())
            {
                HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ADDING");

            } else {
                db = new DBConnection();

                String insertQuery = "INSERT INTO Academic_year (year) VALUES (?)";
                db.setStm(insertQuery);
                db.getStm().setString(1, year);

                int rowsInserted = db.getStm().executeUpdate();

                if (rowsInserted > 0) {
                    //exception
                    HelloApplication.showAlert(Alert.AlertType.INFORMATION, "ADDING A YEAR ", "ADDITION OF A YEAR SUCCESSFUL!!");
                }
                else
                {
                    HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING A YEAR ", "ADDITION OF A YEAR UNSUCCESSFUL");
                }
                db.CloseConnection();
            }
        }
        catch (SQLException e)
        {
            HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING ACADEMIC YEAR","ERROR ADDING DATA"+e.toString());
        }

    }

    //Adds a new semester to the database
    public void addSemester(String semester) throws SQLException
    {
        if (semester.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ADDING");

        }
        else
        {
            db = new DBConnection();

            String insertQuery = "INSERT INTO semesters (semester) VALUES (?)";
            db.setStm(insertQuery);
            db.getStm().setString(1, semester);

            int rowsInserted = db.getStm().executeUpdate();

            if (rowsInserted > 0)
            {
                ///exception
                HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING A SEMESTER ","ADDITION OF SEMESTER SUCCESSFUL!!");
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING A SEMESTER ","ADDITION OF A SEMESTER UNSUCCESSFUL");
            }
            db.CloseConnection();
        }
    }

    //Adds a new Module
    public void addModule(String module) throws SQLException
    {
        if (module.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ADDING");

        }
        else
        {
            db = new DBConnection();

            String insertQuery = "INSERT INTO modules (module) VALUES (?)";
            db.setStm(insertQuery);
            db.getStm().setString(1, module);

            int rowsInserted = db.getStm().executeUpdate();

            if (rowsInserted > 0)
            {
                //exception
                HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING A MODULE ","ADDITION OF MODULE SUCCESSFUL!!");
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING A MODULE ","ADDITION OF A MODULE UNSUCCESSFUL");
            }
            db.CloseConnection();
        }
    }

    // Adds a new class
    public void addClass(String className) throws SQLException
    {
        if (className.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ADDING");

        }
        else
        {
            db = new DBConnection();
            String insertQuery = "INSERT INTO classes (class_name) VALUES (?)";
            db.setStm(insertQuery);
            db.getStm().setString(1, className);

            int rowsInserted = db.getStm().executeUpdate();

            if (rowsInserted > 0)
            {
                ///exception
                HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING A CLASS ","ADDITION OF CLASS SUCCESSFUL!!");
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING A CLASS ","ADDITION OF A CLASS UNSUCCESSFUL");
            }
            db.CloseConnection();
        }
    }

    // Assigns a role to a lecturer
    public void assignRole(String lec_number, String role) throws SQLException
    {
        if (lec_number.isEmpty() || role.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ASSIGNING");

        }
        else
        {
            db = new DBConnection();
            String updateQuery = "UPDATE lecturers SET role = ? WHERE lec_number = ?";
            db.setStm(updateQuery);
            db.getStm().setString(1, role);
            db.getStm().setString(2, lec_number);

            int rowsInserted = db.getStm().executeUpdate();

            if (rowsInserted > 0)
            {
                //exception
                HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ASSIGNING A ROLE ","ASSIGNING A ROLE SUCCESSFUL!!");
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "ASSIGNING A ROLE ","ASSIGNING A ROLE UNSUCCESSFUL");
            }
            db.CloseConnection();
        }
    }

    // Adds a new student in a database
    public void addAssignStudent(String name, String studentID, String className) throws SQLException
    {
        if (name.isEmpty() || studentID.isEmpty() || className.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ADDING AND ASSIGNING");

        }
        else
        {
            db = new DBConnection();

            String insertQuery = "INSERT INTO students(name, studentID, class_name) VALUES (?, ?, ?)";
            db.setStm(insertQuery);
            db.getStm().setString(1, name);
            db.getStm().setString(2, studentID);
            db.getStm().setString(3, className);

            int rowsInserted = db.getStm().executeUpdate();

            if (rowsInserted > 0)
            {
                //exception
                HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING STUDENTS AND ASSIGNING THEM A CLASS","ADDING STUDENTS AND ASSIGNING THEM A CLASS SUCCESSFUL!!");
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING STUDENTS AND ASSIGNING THEM A CLASS","ADDING STUDENTS AND ASSIGNING THEM A CLASS UNSUCCESSFUL");
            }
            db.CloseConnection();
        }
    }

    //assign new module to a lecturer
    public void assignModule(String lecNumber, String module, String className, String semester, String academic_year) throws SQLException
    {
        if (lecNumber.isEmpty() || module.isEmpty() || className.isEmpty() || semester.isEmpty() || academic_year.isEmpty())
        {
            HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ASSIGNING");

        }
        else
        {
            db = new DBConnection();
            String query = "UPDATE lecturers SET Modules = ?, class_name = ?, semester = ?, year = ? WHERE lec_number = ? ";
            db.setStm(query);
            db.getStm().setString(1, module);
            db.getStm().setString(2, className);
            db.getStm().setString(3, semester);
            db.getStm().setString(4, academic_year);
            db.getStm().setString(5, lecNumber);

            int rowsInserted = db.getStm().executeUpdate();

            if (rowsInserted > 0)
            {
                //exception
                HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ASSIGNING A MODULE ","ASSIGNING A MODULE SUCCESSFUL!!");
            }
            else
            {
                HelloApplication.showAlert(Alert.AlertType.WARNING, "ASSIGNING A MODULE ","ASSIGNING A MODULE UNSUCCESSFUL");
            }
            db.CloseConnection();
        }

    }

    // Retrieves all lecturers for viewing purposes
    public String viewAllLecturers() throws SQLException
    {
        db = new DBConnection();
        String selectQuery = "SELECT * FROM lecturers";
        db.setStm(selectQuery);
        ResultSet rs = db.getStm().executeQuery();

        StringBuilder lecturers = new StringBuilder();
        while (rs.next())
        {
            lecturers.append("").append(rs.getString("name"))
                    .append("         ").append(rs.getString("lec_number"))
                    .append("\n");
        }
        db.CloseConnection();
        return lecturers.toString();
    }

    // Retrieves all modules for viewing purposes
    public String viewAllModules() throws SQLException
    {
        db = new DBConnection();
        String selectQuery = "SELECT * FROM modules";
        db.setStm(selectQuery);
        ResultSet rs = db.getStm().executeQuery();

        StringBuilder modules = new StringBuilder();
        while (rs.next())
        {
            modules.append("").append(rs.getString("module")).append("\n");
        }
        db.CloseConnection();
        return modules.toString();
    }

    // Retrieves all semesters for viewing purposes
    public String viewAllSemesters() throws SQLException
    {
        db = new DBConnection();
        String selectQuery = "SELECT * FROM semesters";
        db.setStm(selectQuery);
        ResultSet rs = db.getStm().executeQuery();

        StringBuilder semesters = new StringBuilder();
        while (rs.next())
        {
            semesters.append("").append(rs.getString("semester")).append("\n");
        }
        db.CloseConnection();
        return semesters.toString();
    }

    // Retrieves all semesters for viewing purposes
    public String tickStudents() throws SQLException
    {
        db = new DBConnection();
        String selectQuery = "SELECT * FROM attendance";
        db.setStm(selectQuery);
        ResultSet rs = db.getStm().executeQuery();

        StringBuilder students = new StringBuilder();
        while (rs.next())
        {
            students.append("").append(rs.getString("name"))
                    .append("         ").append(rs.getString("studentID"))
                    .append("\n");
        }
        db.CloseConnection();
        return students.toString();
    }

    //attendance
    public void attendancy(String presency, String studentID) throws SQLException
    {
        try {
                db = new DBConnection();
                String query = "UPDATE attendance SET Present = ? WHERE studentID = ?";
                db.setStm(query);
                db.getStm().setString(1, presency);
                db.getStm().setString(2, studentID);

                int rowsInserted = db.getStm().executeUpdate();
                if (rowsInserted > 0)
                {
                    HelloApplication.showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "DATA ADDED SUCCESSFULLY!");
                }
                else
                {
                    HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING DATA", "ADDING DATA UNSUCCESSFUL");
                }

                db.CloseConnection();
            }

        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    //provide learning Outcomes to chapter
    public void outcomes(String chapter, String learningOut) throws SQLException
    {
        try
        {
            if (chapter.isEmpty() || learningOut.isEmpty())
            {
                HelloApplication.showAlert(Alert.AlertType.ERROR, "INPUT ERROR", "PLEASE FILL IN ALL THE FIELDS BEFORE ADDING.");

            }
            else
            {
                db = new DBConnection(); // Open database connection

                String insertQuery = "INSERT INTO learning_outcomes (chapter, learningOutcome) VALUES (?, ?)";
                db.setStm(insertQuery);
                db.getStm().setString(1, chapter);
                db.getStm().setString(2, learningOut);


                // Execute the insert query
                int rowsInserted = db.getStm().executeUpdate();

                if (rowsInserted > 0)
                {
                    HelloApplication.showAlert(Alert.AlertType.INFORMATION, "SUCCESS", "DATA ADDED SUCCESSFULLY!");
                }
                else
                {
                    HelloApplication.showAlert(Alert.AlertType.WARNING, "ADDING DATA", "ADDING DATA UNSUCCESSFUL");
                }
                db.CloseConnection();
            }
        }
        catch (SQLException e)
        {
            HelloApplication.showAlert(Alert.AlertType.INFORMATION,"ADDING LEARNING OUTCOMES AND CHAPTERS","ERROR ADDING DATA"+e.toString());
        }
    }

}