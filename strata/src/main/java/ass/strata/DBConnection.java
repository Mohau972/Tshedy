package ass.strata;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection
{
    // Database connection credentials
    private String url = "jdbc:mysql://127.0.0.1:3306/Login"; // Database URL
    private String user = "root"; // Database username
    private String pass = "123456"; // Database password

    private Connection conn; // Connection object to manage the database connection
    private PreparedStatement Stm; // PreparedStatement object for executing SQL queries
    private ResultSet res; // ResultSet object for storing results from SQL queries

    // Constructor to initialize and establish a database connection
    public DBConnection()
    {
        try
        {
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
        }
        catch(SQLException e)
        {
            // Print SQL exception details if connection fails
            System.out.println(e.toString());
        }
    }

    // Method to open a database connection
    public void OpenConnection()
    {
        try
        {
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
        }
        catch(SQLException e)
        {
            // Print SQL exception details if connection fails
            System.out.println(e.toString());
        }
    }

    // Getter method to retrieve the current PreparedStatement
    public PreparedStatement getStm()
    {
        return Stm;
    }

    // Setter method to create a PreparedStatement with the provided SQL query
    public void setStm(String sql) throws SQLException
    {
        this.Stm = this.conn.prepareStatement(sql);
    }

    // Method to close the database connection
    public void CloseConnection()
    {
        try
        {
            this.conn.close(); // Attempt to close the connection
        }
        catch (SQLException e)
        {
            // Show an error alert if connection closing fails
            HelloApplication.showAlert(Alert.AlertType.ERROR, "CLOSING CONNECTION", e.toString());
        }
    }

    // Getter method to retrieve the current ResultSet
    public ResultSet getRes()
    {
        return res;
    }

    // Setter method to execute a query using the current PreparedStatement
    // and store the result in the ResultSet
    public void setRes() throws SQLException
    {
        this.res = this.getStm().executeQuery();
    }
}