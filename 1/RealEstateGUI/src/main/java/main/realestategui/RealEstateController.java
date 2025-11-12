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
    public static ArrayList<String[]> names;

    @FXML Label sellerNameLabel;
    @FXML Label sellerAdsCountLabel;
    @FXML Label sellerPhoneLabel;

    @FXML ListView<String> listView;

    @FXML
    public void showData(){
        String selectedName = listView.getSelectionModel().getSelectedItem();

        for(int i = 0; i < names.size(); i++){
            if(names.get(i)[0].equals(selectedName)){
                sellerNameLabel.setText(names.get(i)[0]);
                sellerPhoneLabel.setText(names.get(i)[1]);
                break;
            }
        }
    }

    @FXML
    public void countAds(){
        String selectedName = listView.getSelectionModel().getSelectedItem();

        int count = 0;
        for(Ad ad : ads){
            if(ad.getSeller().getName().equals(selectedName)){
                count++;
            }
        }

        sellerAdsCountLabel.setText(String.valueOf(count));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MySQLHandler.conn = MySQLHandler.createConnection();
        ads = MySQLHandler.getData();
        names = MySQLHandler.getNames();
        MySQLHandler.closeConnection();


        for(String[] name : names){
            listView.getItems().add(name[0]);
        }
    }
}
