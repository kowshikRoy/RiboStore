/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class ApplicationController implements Initializable {

    @FXML
    public StackPane acContent;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private Button btnLogOut;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private AnchorPane acHead;
    @FXML
    private AnchorPane acMain;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgHomeBtn;
    @FXML
    private Button btnStore;
    @FXML
    private ImageView imgStoreBtn;
    @FXML
    private Button btnEmplopye;

    @FXML
    private Button btnSell;
    @FXML
    private ImageView imgSellBtn;
    @FXML
    private Button btnSettings;
    @FXML
    private ImageView imgSettingsBtn;
    @FXML
    private Button btnAbout;
    @FXML
    private ImageView imgAboutBtn;
    @FXML
    private   Label lblUsrName;

    @FXML
    private Circle imgUsrTop;
    @FXML public Label date;

    @FXML private Button btnStatistics;

    @FXML private ImageView imgBackup;
    @FXML private ImageView imgRepair;

    @FXML private Button btnRepair;
    @FXML private Button btnbackup;




    Image menuImage = new Image("/icon/menu.png");
    Image menuImageRed = new Image("/icon/menuRed.png");
    Image image;

    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    Image home = new Image("/icon/home.png");
    Image homeRed = new Image("/icon/homeRed.png");
    Image stock = new Image("/icon/stock.png");

    Image sell = new Image("/icon/sell2.png");
    Image sellRed = new Image("/icon/sell2Red.png");
    Image employee = new Image("/icon/employe.png");
    Image employeeRed = new Image("/icon/employeRed.png");
    Image setting = new Image("/icon/settings.png");
    Image settingRed = new Image("/icon/settingsRed.png");
    Image about = new Image("/icon/about.png");
    Image aboutRed = new Image("/icon/aboutRed.png");

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(Global.getUser()==-1) Global.user=0;
        Global.setUser(1);
        System.out.println(Global.user);



        setAccount();
        loadSceen("/view/application/home/Home.fxml",btnHome);



    }


    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        if (sideMenuToogleBtn.isSelected()) {
            imgMenuBtn.setImage(menuImageRed);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(-130);
            sideMenu.play();
            acDashBord.getChildren().clear();
        } else {
            imgMenuBtn.setImage(menuImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(130);
            sideMenu.play();
            acDashBord.getChildren().add(leftSideBarScroolPan);
        }
    }




    @FXML
    public void btnHomeOnClick(ActionEvent event){
        loadSceen("/view/application/home/Home.fxml",(Button)event.getSource());
    }
    @FXML
    public void btnSettingsOnClick(ActionEvent event) {
        if(Global.getUser() == 1) {
            loadSceen("/view/Setting.fxml",(Button)event.getSource());
        }
        else {
            Toast.makeText( (Stage)((Button)(event.getSource())).getScene().getWindow(), "Please Log in", 2000,200,200);
            loadSceen("/view/application/home/Home.fxml",(Button)event.getSource());
        }

    }


    @FXML
    private void btnAboutOnClick(ActionEvent event) { loadSceen("/view/application/about/AboutMe.fxml",(Button)event.getSource());}
    @FXML
    private void btnStockOnClick(ActionEvent event) { loadSceen("/view/application/stock/stock.fxml",(Button)event.getSource());}

    @FXML
    private void btnSellOnClick(ActionEvent event) {loadSceen("/view/application/sell/MainSellWindow.fxml",(Button)event.getSource());}

    @FXML
    private void btnStatisticsOnClick(ActionEvent event) {
        if(Global.getUser()==1) loadSceen("/view/application/statistics/Statistics.fxml",(Button)event.getSource());
        else {
            Toast.makeText( (Stage)((Button)(event.getSource())).getScene().getWindow(), "Please Log in", 2000,200,200);
            loadSceen("/view/application/home/Home.fxml",(Button)event.getSource());
        }

    }
    @FXML
    public void btnRepairOnClick(ActionEvent event){
        loadSceen("/view/application/repair/Repair.fxml",(Button)event.getSource());
    }
    @FXML
    public void btnbackupOnClick(ActionEvent event) {
        if(Global.getUser()==1){
            //loadSceen("/view/application/");

        }
    }
    public void setAccount(){

        if(Global.user==0){
            imgMenuBtn.setImage(menuImage);
            Image usrImg = new Image("/image/employee.png");
            imgUsrTop.setFill(new ImagePattern(usrImg));
            lblUsrName.setText("Employee");

        }
        else if(Global.user==1){
            imgMenuBtn.setImage(menuImage);
            Image usrImg = new Image("/image/admin.png");
            imgUsrTop.setFill(new ImagePattern(usrImg));
            lblUsrName.setText("Admin");

        }
    }

    private void loadSceen(String url, Button button){
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(url);
        }
    }

}
