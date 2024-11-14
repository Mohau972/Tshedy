package ass.strata;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.SQLException;

public class ViewProfile
{@FXML
private ImageView profileImageView;

    @FXML
    private Label lblUserName;

    private Functions fn = new Functions();

    public void initializeProfile(String username) throws SQLException
    {
        viewProfile(username);
    }

    private void viewProfile(String username) throws SQLException
    {
        // Display username
        lblUserName.setText(username);
        fn.getUserDetails(username);

        // Load the user's profile picture
        Image profileImage = new Image("/path/to/default-profile.png"); // Default image path
        profileImageView.setImage(profileImage);
    }

}