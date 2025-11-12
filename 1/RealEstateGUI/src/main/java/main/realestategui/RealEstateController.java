package main.realestategui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.realestategui.data.Ad;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RealEstateController implements Initializable {
    public static ArrayList<Ad> ads;
    public static ArrayList<String> names;

    @FXML Label sellerNameLabel;
    @FXML Label sellerAdsCountLabel;
    @FXML Label sellerPhoneLabel;

    @FXML ListView<String> listView;

    @FXML
    public void showData(){
        String selectedName = listView.getSelectionModel().getSelectedItem();

        String sellerName = names.get(listView.getSelectionModel().getSelectedIndex());
        String sellerPhone = "";
        int count = 0;
        for(Ad ad : ads){
            if(ad.getSeller().getName().equals(selectedName)){
                sellerPhone = ad.getSeller().getPhone();
                count++;
            }
        }

        sellerNameLabel.setText(sellerName);
        sellerPhoneLabel.setText(sellerPhone);
        sellerAdsCountLabel.setText(String.valueOf(count));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MySQLHandler.conn = MySQLHandler.createConnection();
        ads = MySQLHandler.getData();
        names = MySQLHandler.getNames();
        MySQLHandler.closeConnection();


        listView.getItems().addAll(names);
    }
}
