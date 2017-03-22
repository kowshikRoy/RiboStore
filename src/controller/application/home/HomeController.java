/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.home;

import controller.Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class HomeController implements Initializable {
    @FXML
    private Button homeSubmitbtn;

    @FXML
    private Label lblStockValue;


    @FXML
    private Label lblTotalSell;

    @FXML
    private Label lblTotalEmployee;

    @FXML
    private Label lblOrgName;
    @FXML
    private Label lblOrgName1;
    @FXML
    private Label lblOrgName2;



    @FXML
    private Label lblTotalItem;

    @FXML
    private Label lblSellValue;

    @FXML
    private ImageView buttonImg;

    @FXML
    void homeSubmitbtnHandler(ActionEvent event) throws Exception{
        if(Global.getUser()==0){ // Employee
            ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login Window");
            stage.setMaximized(false);
            stage.setMinHeight(450);
            stage.setMinWidth(450);
            stage.setResizable(false);
            stage.show();
        }
        else if(Global.getUser()==1) {
            Global.setUser(0);
            Stage v = (Stage)((Button)event.getSource()).getScene().getWindow();
            v.close();


            Parent root = FXMLLoader.load(getClass().getResource("/view/Application.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome to RiboStore");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(700.0);
            primaryStage.setMinWidth(1000.0);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());
            primaryStage.show();
        }


    }


    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {


        if(Global.getUser()==0){
            Image image = new Image("/icon/login.png");
            homeSubmitbtn.setText("Login");
            buttonImg.setImage(image);
        }
        else {
            Image image = new Image("/icon/logout.png");
            buttonImg.setImage(image);
            homeSubmitbtn.setText("Logout");


        }
        reload();
    }

    private void reload()
    {
        String q = "SELECT COUNT(*) FROM PRODUCT WHERE QUANTITY > 0";
        try {
            ResultSet resultSet = Global.statement.executeQuery(q);
            if(resultSet.next()) lblTotalItem.setText(resultSet.getString(1));
            else lblTotalItem.setText("0");

            q = "SELECT SUM(BUY_COST * QUANTITY) FROM PRODUCT";
            resultSet = Global.statement.executeQuery(q);

            if(resultSet.next()) lblStockValue.setText(resultSet.getString(1));
            else lblStockValue.setText("0");


            q = "SELECT COUNT(*) FROM CUSTOMER";
            resultSet = Global.statement.executeQuery(q);

            if(resultSet.next()) lblTotalSell.setText(resultSet.getString(1));
            else lblTotalSell.setText("0");

            q = "SELECT SUM(LABELPRODUCTCOST) FROM CUSTOMER";

            resultSet = Global.statement.executeQuery(q);

            if(resultSet.next()) lblSellValue.setText(resultSet.getString(1));
            else lblSellValue.setText("0");


            q = "SELECT NAME FROM COMPANY  ";
            resultSet = Global.statement.executeQuery(q);

            if(resultSet.next()) lblOrgName.setText(resultSet.getString(1));
            else lblOrgName.setText("হাজী ইলেক্ট্রনিক্স");

            q = "SELECT LINE1 FROM COMPANY";
            resultSet = Global.statement.executeQuery(q);

            if(resultSet.next()) lblOrgName1.setText(resultSet.getString(1));
            else lblOrgName1.setText("শহীদ মিনার রোড , পার্বতীপুর , দিনাজপুর");

            q = "SELECT LINE2 FROM COMPANY";
            resultSet = Global.statement.executeQuery(q);

            if(resultSet.next()) lblOrgName2.setText(resultSet.getString(1));
            else lblOrgName2.setText("মালিক - ০১৭১১০৭৫৫২৭ , ম্যানেজার - ০১৭৩৯১৮৯৯৩৩");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
