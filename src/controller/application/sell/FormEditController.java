package controller.application.sell;

/**
 * Created by matrixcode on 12/12/16.
 */


import controller.Global;
import controller.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class FormEditController implements Initializable{

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
    private Label labelCard;

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
    private TextField labelRefFather;

    @FXML
    private Label labelDue;

    @FXML
    private TextField comment10;

    @FXML
    private Button SubmitButton;



    @FXML
    private DatePicker labelNextDate;

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
    private ImageView submitButtonImage;

    @FXML
    private ComboBox<String> skim;

    @FXML
    private  Label Due;

    long prv = 0;
    @FXML
    void handleSubmitButton(ActionEvent event) {
        // Do some Database work

        String delq= "DELETE FROM CUSTOMER WHERE LABELCARD = \"" + labelCard.getText()+ "\"";

        try {
            Global.statement.executeUpdate(delq);
        } catch (SQLException e) {
            e.printStackTrace();
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
            Button b = (Button)event.getSource();


            long cpaid = 0;
            cpaid += val(tfc1.getText());
            cpaid += val(tfc2.getText());
            cpaid += val(tfc3.getText());
            cpaid += val(tfc4.getText());
            cpaid += val(tfc5.getText());
            cpaid += val(tfc6.getText());
            cpaid += val(tfc7.getText());
            cpaid += val(tfc8.getText());
            cpaid += val(tfc9.getText());
            cpaid += val(tfc10.getText());

            String des = labelCard.getText() + "  " + labelCustomerName.getText();
            // Insert into login
            q = "INSERT INTO LOGIN (TIME,TYPE,DESCRIPTION,AMOUNT) VALUES (\"" + LocalDateTime.now() + "\",\"" + "1" + "\",\"" + des +
                    "\",\"" + String.valueOf(cpaid - prv)+ "\")";
            System.out.println(q);
            Global.statement.executeUpdate(q);
            Toast.makeText((Stage) b.getScene().getWindow(),"Edit Succefull",2000,200,200);
            loadScene("/view/application/sell/formView.fxml",this.SubmitButton);
        } catch (SQLException e1) {
            e1.printStackTrace();
            Button b = (Button)event.getSource();
            Toast.makeText((Stage) b.getScene().getWindow(),"Please Enter Valid Data",2000,200,200);
        }



        loadScene("/view/application/sell/formView.fxml",this.SubmitButton);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButtonImage.setImage(new Image("/icon/submit_new.png"));
        String q = "SELECT * FROM CUSTOMER WHERE labelCard = \"" + Global.getCustomerId() + "\"";
        ResultSet rs = null;
        try {
            rs = Global.statement.executeQuery(q);
            while(rs.next())
            {
                ObservableList<String> row= FXCollections.observableArrayList();
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
                {
                    row.add(rs.getString(i));
                }
                populate(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void populate(ObservableList<String> row){
        labelCard.setText(row.get(0));
        int [] d = get(row.get(1));
        if(d.length == 3) labelDate.setValue(LocalDate.of(d[0],d[1],d[2]));
        labelProductId.setText(row.get(2));
        labelCustomerName.setText(row.get(3));
        labelRefName.setText(row.get(4));
        labelProductCost.setText(row.get(5));
        labelCustomerFather.setText(row.get(6));
        labelRefFather.setText(row.get(7));
        comboDay.setValue(row.get(8));
        labelCustomerAddress.setText(row.get(9));
        labelRefAddress.setText(row.get(10));
        comboGroup.setValue(row.get(11));
        labelCustomerMobile.setText(row.get(12));
        labelRefMobile.setText(row.get(13));
        d = get(row.get(14));
        if(d.length==3) labelNextDate.setValue(LocalDate.of(d[0],d[1],d[2]));
        labelProductName.setText(row.get(15));
        Cost4month.setText(row.get(16));
        Cost10Months.setText(row.get(17));
        comboStatus.setValue(row.get(18));

        d= get(row.get(19));
        if(d.length==3) dpc1.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(20));
        if(d.length==3)dpc2.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(21));
        if(d.length==3)dpc3.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(22));
        if(d.length==3)dpc4.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(23));
        if(d.length==3)dpc5.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(24));
        if(d.length==3)dpc6.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(25));
        if(d.length==3)dpc7.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(26));
        if(d.length==3)dpc8.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(27));
        if(d.length==3)dpc9.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(28));
        if(d.length==3)dpc10.setValue(LocalDate.of(d[0],d[1],d[2]));


        long cpaid = 0;
        tfc1.setText(row.get(29)); cpaid += val(tfc1.getText());
        tfc2.setText(row.get(30)); cpaid += val(tfc2.getText());
        tfc3.setText(row.get(31)); cpaid += val(tfc3.getText());
        tfc4.setText(row.get(32)); cpaid += val(tfc4.getText());
        tfc5.setText(row.get(33)); cpaid += val(tfc5.getText());
        tfc6.setText(row.get(34)); cpaid += val(tfc6.getText());
        tfc7.setText(row.get(35)); cpaid += val(tfc7.getText());
        tfc8.setText(row.get(36)); cpaid += val(tfc8.getText());
        tfc9.setText(row.get(37)); cpaid += val(tfc9.getText());
        tfc10.setText(row.get(38));cpaid += val(tfc10.getText());


        d= get(row.get(39));
        if(d.length==3) dpr1.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(40));
        if(d.length==3)dpr2.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(41));
        if(d.length==3)dpr3.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(42));
        if(d.length==3)dpr4.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(43));
        if(d.length==3)dpr5.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(44));
        if(d.length==3)dpr6.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(45));
        if(d.length==3)dpr7.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(46));
        if(d.length==3)dpr8.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(47));
        if(d.length==3)dpr9.setValue(LocalDate.of(d[0],d[1],d[2]));
        d= get(row.get(48));
        if(d.length==3)dpr10.setValue(LocalDate.of(d[0],d[1],d[2]));

        long paid = 0;
        tfr1.setText(row.get(49)); paid += val(tfr1.getText());
        tfr2.setText(row.get(50)); paid += val(tfr2.getText());
        tfr3.setText(row.get(51)); paid += val(tfr3.getText());
        tfr4.setText(row.get(52)); paid += val(tfr4.getText());
        tfr5.setText(row.get(53)); paid += val(tfr5.getText());
        tfr6.setText(row.get(54)); paid += val(tfr6.getText());
        tfr7.setText(row.get(55)); paid += val(tfr7.getText());
        tfr8.setText(row.get(56)); paid += val(tfr8.getText());
        tfr9.setText(row.get(57)); paid += val(tfr9.getText());
        tfr10.setText(row.get(58));paid += val(tfr10.getText());

        comment1.setText(row.get(59));
        comment2.setText(row.get(60));
        comment3.setText(row.get(61));
        comment4.setText(row.get(62));
        comment5.setText(row.get(63));
        comment6.setText(row.get(64));
        comment7.setText(row.get(65));
        comment8.setText(row.get(66));
        comment9.setText(row.get(67));
        comment10.setText(row.get(68));

        skim.setValue(row.get(69));

        long e = Integer.parseInt(labelProductCost.getText()) - paid;
        Due.setText(String.valueOf(e));

        totalCustomer.setText(String.valueOf(cpaid));
        totalRecieve.setText(String.valueOf(paid));
        prv = cpaid;
    }
    int [] get(String d)
    {
        try{
            String [] p = d.split("-");
            int [] ret = new int[3];
            ret[0]= Integer.parseInt(p[0]);
            ret[1]= Integer.parseInt(p[1]);
            ret[2]= Integer.parseInt(p[2]);
            return ret;
        }
        catch (Exception e) {
            return new int[1];
        }

    }
    long val(String t)
    {
        try{
            if(t==null) return 0;
            if(t.isEmpty()) return 0;

            return Long.parseLong(t);
        }
        catch (Exception e){
            return 0L;
        }

    }
}

