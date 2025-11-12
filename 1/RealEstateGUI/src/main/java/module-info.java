module main.realestategui {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.realestategui to javafx.fxml;
    exports main.realestategui;
}