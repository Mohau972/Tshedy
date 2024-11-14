module ass.strata
{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens ass.strata to javafx.fxml;
    exports ass.strata;
}