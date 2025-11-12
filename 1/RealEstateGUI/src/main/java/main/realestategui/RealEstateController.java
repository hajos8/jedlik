package main.realestategui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.realestategui.data.Ad;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RealEstateController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Ad> ads = MySQLHandler.getData();
    }
}
