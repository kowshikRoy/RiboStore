package controller.application.sell;

import controller.Global;
import controller.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Created by matrixcode on 12/9/16.
 */
public class FormController implements Initializable{



    @FXML
    private TextField tfc10;

    @FXML
    private DatePicker dpc10;

    @FXML
    private TextField labelCustomerMobile;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private ComboBox<String> comboGroup;

    @FXML
    private DatePicker dpc6;

    @FXML
    private TextField tfc7;

    @FXML
    private DatePicker dpc5;

    @FXML
    private TextField tfc8;

    @FXML
    private DatePicker dpc4;

    @FXML
    private TextField tfc5;

    @FXML
    private DatePicker dpc3;

    @FXML
    private TextField tfc6;

    @FXML
    private DatePicker dpc2;

    @FXML
    private TextField tfc3;

    @FXML
    private DatePicker dpc1;

    @FXML
    private TextField tfc4;

    @FXML
    private TextField tfc1;

    @FXML
    private TextField tfc2;

    @FXML
    private TextField labelRefAddress;

    @FXML
    private TextField labelCard;

    @FXML
    private TextField labelRefMobile;

    @FXML
    private TextField tfc9;

    @FXML
    private DatePicker dpr5;

    @FXML
    private TextField comment6;

    @FXML
    private DatePicker dpr4;

    @FXML
    private TextField comment5;

    @FXML
    private DatePicker dpr3;

    @FXML
    private TextField comment4;

    @FXML
    private DatePicker dpr2;

    @FXML
    private TextField comment3;

    @FXML
    private DatePicker dpr1;

    @FXML
    private TextField comment2;

    @FXML
    private TextField comment1;

    @FXML
    private TextField comment9;

    @FXML
    private TextField comment8;

    @FXML
    private ComboBox<String> comboDay;

    @FXML
    private TextField comment7;

    @FXML
    private DatePicker dpr10;

    @FXML
    private TextField labelProductName;

    @FXML
    private TextField Cost4month;

    @FXML
    private TextField labelRefName;

    @FXML
    private TextField labelCustomerName;

    @FXML
    private DatePicker dpr9;

    @FXML
    private DatePicker dpr8;

    @FXML
    private DatePicker dpr7;

    @FXML
    private DatePicker dpr6;

    @FXML
    private TextField tfr6;

    @FXML
    private TextField tfr7;

    @FXML
    private TextField tfr4;

    @FXML
    private TextField tfr5;

    @FXML
    private TextField tfr2;

    @FXML
    private TextField tfr3;

    @FXML
    private TextField tfr1;

    @FXML
    private TextField labelCustomerAddress;

    @FXML
    private TextField tfr10;

    @FXML
    private TextField tfr8;

    @FXML
    private TextField tfr9;

    @FXML
    private Label Due;

    @FXML
    private TextField labelRefFather;

    @FXML
    private TextField comment10;

    @FXML
    private Button SubmitButton;

    @FXML
    private DatePicker labelNextDate;

    @FXML
    private ImageView submitButtonImage;

    @FXML
    private DatePicker dpc9;

    @FXML
    private DatePicker dpc8;

    @FXML
    private Label totalRecieve;

    @FXML
    private DatePicker dpc7;

    @FXML
    private TextField labelCustomerFather;

    @FXML
    private Label totalCustomer;

    @FXML
    private TextField labelProductId;

    @FXML
    private TextField labelProductCost;

    @FXML
    private DatePicker labelDate;

    @FXML
    private TextField Cost10Months;

    @FXML
    private ComboBox<String> skim;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(this.toString());
        submitButtonImage.setImage(new Image("/icon/submit_new.png"));
        comboDay.getItems().addAll("SAT","SUN","MON","TUE","WED","THR","FRI");
        comboGroup.getItems().addAll("A","B");
        skim.getItems().addAll("নগদ","চারমাসের কিস্তি","১০মাসের কিস্তি");
        comboStatus.getItems().addAll("DUE","CLEAR");
        comboStatus.setValue("DUE");
        comboDay.setValue("SAT");
        comboGroup.setValue("A");
        skim.setValue("নগদ");
        System.out.println(this.toString());
    }

    public void handleSubmitButton(ActionEvent e) {
        //INPUT VALIDATION

        long quantity= 0;
        try {
            String query = "SELECT QUANTITY FROM PRODUCT WHERE PRODUCT_CODE = \"" + labelProductId.getText() + "\"";
            ResultSet resultSet  = Global.statement.executeQuery(query);

            if(resultSet.next() && resultSet.getString(1).equals("0") == false){
                quantity = Long.parseLong(resultSet.getString(1));
            }
            else {
                Toast.makeText((Stage) ((Button)(e.getSource())).getScene().getWindow(),"Error !", 2500,200,200);
                return;
            }
        } catch (SQLException e1) {
            Toast.makeText((Stage) ((Button)(e.getSource())).getScene().getWindow(),"Error !", 2500,200,200);
            e1.printStackTrace();
            return;
        }
        String q = "INSERT INTO CUSTOMER (labelCard,labelDate,labelProductId,labelCustomerName,labelRefName,labelProductCost,labelCustomerFather,"
                + "labelRefFather,comboDay,labelCustomerAddress,labelRefAddress,comboGroup,labelCustomerMobile,labelRefMobile,labelNextDate,labelProductName,"
                + "Cost4month,Cost10Months,comboStatus,DPC1,DPC2,DPC3,DPC4,DPC5,DPC6,DPC7,DPC8,DPC9,DPC10,TFC1,TFC2,TFC3,TFC4,TFC5,TFC6,TFC7,TFC8,TFC9,TFC10,"
                + "DPR1,DPR2,DPR3,DPR4,DPR5,DPR6,DPR7,DPR8,DPR9,DPR10,TFR1,TFR2,TFR3,TFR4,TFR5,TFR6,TFR7,TFR8,TFR9,TFR10,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10, skim) VALUES(\""
                + labelCard.getText()+"\",\"" + labelDate.getValue() + "\",\"" + labelProductId.getText() + "\",\"" + labelCustomerName.getText() + "\",\""
                + labelRefName.getText() + "\",\"" + labelProductCost.getText() + "\",\"" + labelCustomerFather.getText() +"\",\""+ labelRefFather.getText()+"\",\""
                + comboDay.getValue()+ "\",\"" + labelCustomerAddress.getText() + "\",\"" + labelRefAddress.getText()+"\",\""+comboGroup.getValue() + "\",\""
                + labelCustomerMobile.getText()+"\",\""+labelRefMobile.getText() + "\",\""+  labelNextDate.getValue()+"\",\""+labelProductName.getText()+"\",\""+Cost4month.getText()+"\",\""+Cost10Months.getText()+"\",\""
                + comboStatus.getValue() + "\",\"" + dpc1.getValue()+"\",\""+dpc2.getValue()+"\",\""+dpc3.getValue() + "\",\""+ dpc4.getValue()+ "\",\""
                + dpc5.getValue() + "\",\"" + dpc6.getValue() + "\",\"" + dpc7.getValue() + "\",\"" + dpc8.getValue() + "\",\""
                + dpc9.getValue() +"\",\"" +  dpc10.getValue() + "\",\"" + tfc1.getText()+"\",\"" + tfc2.getText() + "\",\"" + tfc3.getText() + "\",\"" + tfc4.getText() + "\",\""
                + tfc5.getText()+ "\",\"" + tfc6.getText() + "\",\"" + tfc7.getText() + "\",\"" + tfc8.getText() + "\",\"" + tfc9.getText() + "\",\""
                + tfc10.getText() + "\",\"" + dpr1.getValue() + "\",\""+ dpr2.getValue() + "\",\"" + dpr3.getValue()+"\",\""+ dpr4.getValue() + "\",\""
                + dpr5.getValue() + "\",\"" + dpr6.getValue() + "\",\"" +dpr7.getValue() + "\",\"" + dpr8.getValue() + "\",\""+ dpr9.getValue() + "\",\""
                + dpr10.getValue() + "\",\"" + tfr1.getText() + "\",\"" + tfr2.getText() + "\",\"" +  tfr3.getText() + "\",\""
                + tfr4.getText() + "\",\"" + tfr5.getText()+ "\",\"" + tfr6.getText() + "\",\"" + tfr7.getText() + "\",\"" + tfr8.getText() + "\",\""
                + tfr9.getText() + "\",\"" + tfr10.getText() +  "\",\"" + comment1.getText() + "\",\"" + comment2.getText() + "\",\"" + comment3.getText()+ "\",\""
                + comment4.getText() + "\",\"" + comment5.getText() + "\",\"" + comment6.getText() + "\",\"" + comment7.getText()+ "\",\"" + comment8.getText() + "\",\""
                + comment9.getText() + "\",\"" + comment10.getText() + "\",\"" + skim.getValue() + "\")";
        System.out.println(q);

        try {
            Global.statement.executeUpdate(q);
            Global.setCustomerId(Long.parseLong(labelCard.getText()));

            q = "UPDATE PRODUCT SET "+
                    "QUANTITY = \"" + String .valueOf(quantity - 1) + "\"" +
                    " WHERE PRODUCT_CODE = \"" + labelProductId.getText() + "\"";
            Global.statement.executeUpdate(q);
            // Insert into login
            String description = labelCard.getText() +"  " + labelCustomerName.getText()  ;
            q = "INSERT INTO LOGIN (TIME,TYPE,DESCRIPTION,AMOUNT) VALUES (\"" + LocalDateTime.now() + "\",\"" + "1" + "\",\"" + description+
                    "\",\"" + tfr1.getText() + "\")";
            System.out.println(q);
            Global.statement.executeUpdate(q);

            q = "INSERT INTO LOGOUT (TIME,TYPE,DESCRIPTION,AMOUNT) VALUES (\"" + LocalDateTime.now() + "\",\"" + "1" + "\",\"" + "SELL "+ labelProductId.getText() + " , card : " + labelCard.getText() +
                    "\",\"" + labelProductCost.getText() + "\")";
            System.out.println(q);
            Global.statement.executeUpdate(q);

            loadScene("/view/application/sell/formView.fxml",this.SubmitButton);


        } catch (SQLException e1) {
            Toast.makeText((Stage) ((Button)(e.getSource())).getScene().getWindow(),"Error !", 2500,200,200);
            e1.printStackTrace();
        }


    }
    void loadScene(String url, Button button) {

        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }



        Parent root = button.getParent().getParent().getParent().getParent().getParent();
        StackPane pane = (StackPane) root;
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);

    }
}

