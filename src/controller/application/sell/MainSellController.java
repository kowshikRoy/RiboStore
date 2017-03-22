package controller.application.sell;

/**
 * Created by matrixcode on 12/25/16.
 */


import controller.Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainSellController implements Initializable{
    @FXML
    private Button newTransactionButton;

    @FXML
    private ImageView newTransactionImage;

    @FXML
    private ImageView costImage;

    @FXML
    private ImageView viewImage;

    @FXML
    private Button newBillingButton;

    @FXML
    private ImageView showListImage;

    @FXML
    private Button viewInventoryButton;

    @FXML
    private ImageView newBillingmage;

    @FXML
    private Button AdditionalCostButton;

    @FXML
    private Button AddtionalIncomeButton;

    @FXML
    private ImageView incomeImage;

    @FXML
    private Button showListButton;

    @FXML
    void newBillingButtonOnClick(ActionEvent event) {
        String url = "/view/application/sell/form.fxml";
        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }

        Button b = (Button)event.getSource();
        StackPane pane = (StackPane)b.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    @FXML
    void newTransactionButtonOnClick(ActionEvent event) {


        String url = "/view/application/sell/ChooseCustomer.fxml";
        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }

        Button b = (Button)event.getSource();
        StackPane pane = (StackPane)b.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    @FXML
    void AddtionalIncomeButtonOnClick(ActionEvent event) {
        String url = "/view/application/sell/Additional.fxml";
        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }

        Button b = (Button)event.getSource();
        StackPane pane = (StackPane)b.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    @FXML
    void AdditionalCostButtonOnClick(ActionEvent event) {
        // This is log button

        String url = "/view/application/sell/log.fxml";
        if(Global.getUser()==1) url = "/view/application/sell/logAdmin.fxml";
        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }

        Button b = (Button)event.getSource();
        StackPane pane = (StackPane)b.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    @FXML
    void showListButton(ActionEvent event) {
        String url = "/view/application/sell/customerList.fxml";
        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }

        Button b = (Button)event.getSource();
        StackPane pane = (StackPane)b.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    @FXML
    void viewInventoryButtonOnClick(ActionEvent event) {

        String url = "/view/application/sell/ViewInventory.fxml";
        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }

        Button b = (Button)event.getSource();
        StackPane pane = (StackPane)b.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newTransactionImage.setImage(new Image("/icon/new_transaction.png"));
        newBillingmage.setImage(new Image("/icon/new_biling.png"));
        viewImage.setImage(new Image("/icon/view.png"));
        showListImage.setImage(new Image("/icon/show_list.png"));
        incomeImage.setImage(new Image("/icon/income.png"));
        costImage.setImage(new Image("/icon/cost.png"));
        viewImage.setImage(new Image("/icon/view.png"));
    }
}
