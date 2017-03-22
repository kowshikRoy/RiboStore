package controller.application.sell;

/**
 * Created by matrixcode on 12/25/16.
 */
import controller.Global;
import controller.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdditionalController implements Initializable{

    @FXML
    private Button incomingPlusButton;

    @FXML
    private Button incomeDoneButton;

    @FXML
    private Button mainButton;

    @FXML
    private ComboBox<String> outgoingCombo;

    @FXML
    private ImageView incomingPlus;

    @FXML
    private TextField outgoingText;

    @FXML
    private TextField incomeText;

    @FXML
    private Button outgoingDoneButton;

    @FXML
    private ImageView mainImage;

    @FXML
    private ImageView outgoingImage;

    @FXML
    private ImageView outgoingPlus;

    @FXML
    private ImageView incomingImage;

    @FXML
    private Button outgoingButton;

    @FXML
    private ComboBox<String> incomeCombo;


    @FXML
    void mainButtonOnClick(ActionEvent event) {
        // Go to DashBoard
        Button button = (Button)event.getSource();
        loadScene("/view/application/sell/MainSellWindow.fxml", button.getParent().getParent().getParent().getParent().getParent());
    }

    @FXML
    void incomeDoneButtonOnClick(ActionEvent event) {
        try{
            ResultSet resultSet = Global.statement.executeQuery("SELECT AMOUNT FROM INCOMESOURCE WHERE NAME = \"" + incomeCombo.getValue()+"\"");
            long prv = resultSet.getLong(1);
            long value = Long.valueOf(incomeText.getText());
            long newValue = prv + value;
            String q = "UPDATE INCOMESOURCE SET "+
                    "AMOUNT = \"" + String.valueOf(newValue) + "\" WHERE NAME = \"" + incomeCombo.getValue() + "\"";
            System.out.println(q);
            Global.statement.executeUpdate(q);


            // Insert into login
            q = "INSERT INTO LOGIN (TIME,TYPE,DESCRIPTION,AMOUNT) VALUES (\"" + LocalDateTime.now() + "\",\"" + "2" + "\",\"" + incomeCombo.getValue().toString()+
                    "\",\"" + incomeText.getText() + "\")";
            System.out.println(q);
            Global.statement.executeUpdate(q);


            Button button = (Button)event.getSource();
            loadScene("/view/application/sell/MainSellWindow.fxml", button.getParent().getParent().getParent().getParent().getParent().getParent());

        }catch (Exception e){
            Toast.makeText((Stage)((Node)event.getSource()).getScene().getWindow() , "Error Occured !" , 2500, 250,250 );
            e.printStackTrace();
        }
    }

    @FXML
    void incomingPlusButtonOnClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Incoming Source");
        dialog.setContentText("Enter New Incoming Source Name");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());

            try {

                String q = "INSERT INTO INCOMESOURCE(NAME) VALUES (\"" + result.get() + "\") ";
                System.out.println(q);
                Global.statement.executeUpdate(q);
                incomeCombo.getItems().add(result.get());


            }catch (Exception e){

                Toast.makeText((Stage)((Node)event.getSource()).getScene().getWindow() , "Error Occured !" , 2500, 250,250 );
                e.printStackTrace();
            }


        }

    }

    @FXML
    void outgoingDoneButtonOnClick(ActionEvent event) {
        try{
            ResultSet resultSet = Global.statement.executeQuery("SELECT AMOUNT FROM OUTGOING WHERE NAME = \"" + outgoingCombo.getValue()+"\"");
            long prv = resultSet.getLong(1);
            long value = Long.valueOf(outgoingText.getText());
            long newValue = prv + value;
            String q = "UPDATE OUTGOING SET "+
                    "AMOUNT = \"" + String.valueOf(newValue) + "\" WHERE NAME = \"" + outgoingCombo.getValue() + "\"";
            System.out.println(q);
            Global.statement.executeUpdate(q);


            // Insert into logout
            q = "INSERT INTO LOGOUT (TIME,TYPE,DESCRIPTION,AMOUNT) VALUES (\"" + LocalDateTime.now() + "\",\"" + "2" + "\",\"" + outgoingCombo.getValue().toString()+
                    "\",\"" + outgoingText.getText() + "\")";
            System.out.println(q);
            Global.statement.executeUpdate(q);


            Button button = (Button)event.getSource();
            loadScene("/view/application/sell/MainSellWindow.fxml", button.getParent().getParent().getParent().getParent().getParent().getParent());

        }catch (Exception e){
            Toast.makeText((Stage)((Node)event.getSource()).getScene().getWindow() , "Error Occured !" , 2500, 250,250 );
            e.printStackTrace();
        }
    }

    @FXML
    void outgoingButtonOnClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Outgoing Source");
        dialog.setContentText("Enter New Outgoing Source Name");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
            // Push it Database & Update Combobox
            System.out.println("Your name: " + result.get());

            try {

                String q = "INSERT INTO OUTGOING(NAME) VALUES (\"" + result.get() + "\") ";
                System.out.println(q);
                Global.statement.executeUpdate(q);
                outgoingCombo.getItems().add(result.get());


            }catch (Exception e){
                Toast.makeText((Stage)((Node)event.getSource()).getScene().getWindow() , "Error Occured !" , 2500, 250,250 );
                e.printStackTrace();
            }

        }
    }


    void loadScene(String url, Parent root) {

        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }




        StackPane pane = (StackPane) root;
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        incomingImage.setImage(new Image("/icon/done.png"));
        outgoingImage.setImage(new Image("/icon/done.png"));
        mainImage.setImage(new Image("/icon/main.png"));
        incomingPlus.setImage(new Image("/icon/plus.png"));
        outgoingPlus.setImage(new Image("/icon/plus.png"));
        comboInit();

    }

    private void comboInit() {
        ObservableList<String> options = FXCollections.observableArrayList();
        try{

            String q = "SELECT * FROM INCOMESOURCE";
            ResultSet resultSet = Global.statement.executeQuery(q);
            while(resultSet.next()) {
                options.add(resultSet.getString(1));
            }
            incomeCombo.getItems().clear();
            incomeCombo.getItems().addAll(options);

            if(options.size()>0) incomeCombo.setValue(options.get(0));
             q = "SELECT * FROM OUTGOING";
            resultSet = Global.statement.executeQuery(q);

            options.clear();
            while(resultSet.next()) {
                options.add(resultSet.getString(1));
            }
            outgoingCombo.getItems().clear();
            outgoingCombo.getItems().addAll(options);
            resultSet.close();
            if(options.size() > 0 ) outgoingCombo.setValue(options.get(0));

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
