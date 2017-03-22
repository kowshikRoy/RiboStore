package controller.application.stock;

import controller.Global;
import controller.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by matrixcode on 12/25/16.
 */
public class newItemController implements Initializable {

    @FXML
    private Button submitButton;

    @FXML
    private TextField Description;

    @FXML
    private ImageView categoryImage;

    @FXML
    private ImageView brandImage;

    @FXML
    private TextField itemCode;

    @FXML
    private Button BrandButton;

    @FXML
    private Button categoryButton;

    @FXML
    private TextField price10months;



    @FXML
    private TextField price4months;

    @FXML
    private TextField itemName;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView submitImage;

    @FXML
    private TextField BuyCost;

    @FXML
    private ImageView cancelImage;

    @FXML
    private ComboBox<String> category;

    @FXML
    private ComboBox<String> brand;


    @FXML
    private TextField Quantity;



    @FXML
    void categoryButtonOnClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Category");
        dialog.setContentText("Enter New Category Name");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());

            try {

                String q = "INSERT INTO CATEGORY (NAME) VALUES (\"" + result.get() + "\") ";
                System.out.println(q);
                Global.statement.executeUpdate(q);
                category.getItems().add(result.get());


            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    @FXML
    void BrandButtonOnClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Brand");
        dialog.setContentText("Enter New Brand Name");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());

            try {

                String q = "INSERT INTO BRAND (NAME) VALUES (\"" + result.get() + "\") ";
                System.out.println(q);
                Global.statement.executeUpdate(q);
                category.getItems().add(result.get());


            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    @FXML
    void cancelButtonOnClick(ActionEvent event) {
        loadScene("/view/application/stock/stock.fxml", ((Button)event.getSource()).getParent().getParent().getParent().getParent().getParent().getParent().getParent());
    }

    @FXML
    void submitButtonOnClick(ActionEvent event) {


        try {
            String q = "INSERT INTO PRODUCT (PRODUCT_CODE,PRODUCT_NAME,CATEGORY,DESCRIPTION,BRAND,BUY_COST,PRICE_4,PRICE_10,QUANTITY) VALUES (\""+
                    itemCode.getText() + "\",\""+ itemName.getText() + "\",\"" + category.getSelectionModel().getSelectedItem().toString() + "\",\"" +
                    Description.getText() + "\",\"" + brand.getSelectionModel().getSelectedItem().toString() + "\",\"" +
                    BuyCost.getText() + "\",\"" + price4months.getText() + "\",\"" + price10months.getText() + "\",\"" + Quantity.getText() + "\")";
            System.out.println(q);
            Global.statement.executeUpdate(q);
            Toast.makeText((Stage) ((Button)event.getSource()).getScene().getWindow(),"Opearion Successful !",2000,200,200);
            loadScene("/view/application/stock/stock.fxml", ((Button)event.getSource()).getParent().getParent().getParent().getParent().getParent().getParent().getParent());

            return;
        } catch (Exception e) {
            Toast.makeText((Stage) ((Button)event.getSource()).getScene().getWindow(),"Please Enter Valid Data!",2000,200,200);
            e.printStackTrace();
        }

        //Toast.makeText((Stage)((Button)event.getSource()).getScene().getWindow(),"Please Enter Valid Data!",2000,200,200);


    }

    private void loadScene(String url, Parent root) {
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

    Image button = new Image("/icon/add_item.png");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelImage.setImage(new Image("/icon/cancel.png"));
        submitImage.setImage(new Image("/icon/submit.png"));
        categoryImage.setImage(new Image("/icon/plus.png"));
        brandImage.setImage(new Image("/icon/plus.png"));
        comboInit();

    }

    private void comboInit() {
        ObservableList<String> options = FXCollections.observableArrayList();
        try{

            String q = "SELECT * FROM CATEGORY";
            ResultSet resultSet = Global.statement.executeQuery(q);
            while(resultSet.next()) {
                options.add(resultSet.getString(1));
            }
            category.getItems().clear();
            category.getItems().addAll(options);

            q = "SELECT * FROM BRAND";
            resultSet = Global.statement.executeQuery(q);

            options.clear();
            while(resultSet.next()) {
                options.add(resultSet.getString(1));
            }
            brand.getItems().clear();
            brand.getItems().addAll(options);
            resultSet.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
