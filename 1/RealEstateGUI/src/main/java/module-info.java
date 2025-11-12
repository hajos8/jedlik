module main.realestategui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.realestategui to javafx.fxml;
    exports main.realestategui;
}