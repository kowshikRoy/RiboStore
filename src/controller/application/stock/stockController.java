package controller.application.stock;

import controller.Global;
import controller.Toast;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * Created by matrixcode on 12/25/16.
 */

public class stockController implements Initializable {

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ImageView DeleteImage;

    @FXML
    private TextField search_item_category;

    @FXML
    private TableColumn<ObservableList, String> price_4;

    @FXML
    private TableColumn<ObservableList, String> itemCode;

    @FXML
    private TableColumn<ObservableList, String> description;

    @FXML
    private ImageView addItemImage;

    @FXML
    private ImageView updateImage;

    @FXML
    private TableColumn<ObservableList, String> price_10;

    @FXML
    private TextField QuantityTf;

    @FXML
    private TableColumn<ObservableList, String> itemName;

    @FXML
    private TextField categoryTf;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<ObservableList, String> brand;

    @FXML
    private Button addItemButton;

    @FXML
    private TextField BuyCostTf;

    @FXML
    private TableColumn<ObservableList, String> quantity;

    @FXML
    private TextField brandTf;

    @FXML
    private TextField search_item_code;

    @FXML
    private TextField itemCodeTf;

    @FXML
    private TextField DescriptionTf;

    @FXML
    private TextField price4CostTf;

    @FXML
    private TextField price_10CostTf;


    @FXML
    private TableView<ObservableList> inventoryTable;

    @FXML
    private ImageView searchImage;

    @FXML
    private TextField itemNameTf;

    @FXML
    private TableColumn<ObservableList, String> buy_cost;

    @FXML
    private TableColumn<ObservableList, String> category;

    @FXML
    private TextField addTf;

    @FXML
    private TextField search_item_brand;

    private final ObservableList<ObservableList> data= FXCollections.observableArrayList();


    @FXML
    void searchButtonOnClick(ActionEvent event) {
        // Do need to update the tableView
        if (search_item_code.getText().equals("") == false || search_item_category.getText().equals("") == false || search_item_brand.getText().equals("") == false) {
            ResultSet rs = null;
            String q = "SELECT PRODUCT_CODE,PRODUCT_NAME, QUANTITY, CATEGORY , BRAND, DESCRIPTION ,BUY_COST, PRICE_4, PRICE_10  from PRODUCT WHERE";
            boolean prv = false;
            if (search_item_code.getText().equals("") == false) {
                if (prv) q += " AND ";
                q += " PRODUCT_CODE = \"" + search_item_code.getText() + "\"";
                prv = true;
            }
            if (search_item_category.getText().equals("") == false) {
                if (prv) q += " AND ";
                q += " CATEGORY = \"" + search_item_category.getText() + "\"";
                prv = true;
            }
            if (search_item_brand.getText().equals("") == false) {
                if (prv) q += " AND ";
                q += " BRAND = \"" + search_item_brand.getText() + "\"";
                prv = true;
            }
            System.out.println(q);
            data.clear();
            try {
                rs = Global.statement.executeQuery(q);
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            inventoryTable.setItems(data);
        }
    }

    @FXML
    void addItemButtonOnClick(ActionEvent event) {
        loadScreen("/view/application/stock/newItem.fxml", (Button)event.getSource());
    }

    @FXML
    void updateButtonOnClick(ActionEvent event) {
        // Do some database

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");

        alert.setContentText("Do you want to update this product ? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            long add =0;
            String value = addTf.getText();
            long newQuantity = Long.parseLong(QuantityTf.getText());
            if(value.isEmpty() == false) {
                try {
                    add = Long.parseLong(value);
                    newQuantity += add;

                }catch (Exception e) {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setTitle("Error Dialog");
                    alert3.setContentText("Ooops, there was an error!");
                    alert3.showAndWait();
                    return;
                }

            }


            String q = "UPDATE PRODUCT SET " +
                    "PRODUCT_CODE = \""+ itemCodeTf.getText() + "\"," +
                    "PRODUCT_NAME = \""+ itemNameTf.getText() + "\"," +
                    "QUANTITY = \"" + String.valueOf(newQuantity) + "\"," +
                    "CATEGORY = \"" + categoryTf.getText() + "\"," +
                    "BRAND = \"" + brandTf.getText() + "\"," +
                    "DESCRIPTION = \"" + DescriptionTf.getText() + "\"," +
                    "BUY_COST = \"" + BuyCostTf.getText() + "\","  +
                    "PRICE_4 = \"" + price4CostTf.getText() + "\"," +
                    "PRICE_10 = \"" + price_10CostTf.getText() + "\""+
                    "WHERE " +
                    "PRODUCT_CODE = \""+ itemCodeTf.getText() + "\"" ;

            System.out.println(q);
            try {
                Global.statement.executeUpdate(q);
                Toast.makeText((Stage)updateButton.getScene().getWindow(),"Update Successfull",2000,200,200);
                reload();
            } catch (SQLException e) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error Dialog");
                alert2.setContentText("Ooops, databse Error");
                alert2.showAndWait();
                e.printStackTrace();
            }
            itemCodeTf.setText("");
            itemNameTf.setText("");
            QuantityTf.setText("");
            categoryTf.setText("");
            brandTf.setText("");
            DescriptionTf.setText("");
            BuyCostTf.setText("");
            price4CostTf.setText("");
            price_10CostTf.setText("");
            addTf.setText("");
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }


    }



    @FXML
    void deleteButtonOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");

        alert.setContentText("Do you want to delete this product ? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            String q = "DELETE FROM PRODUCT WHERE PRODUCT_CODE = \"" + itemCodeTf.getText() + "\"";
            System.out.println(q);
            try {
                Global.statement.executeUpdate(q);
                Toast.makeText((Stage)updateButton.getScene().getWindow(),"Delete Successful" ,2000,200,200);
                reload();
            } catch (SQLException e) {

                e.printStackTrace();
            }
            itemCodeTf.setText("");
            itemNameTf.setText("");
            QuantityTf.setText("");
            categoryTf.setText("");
            brandTf.setText("");
            DescriptionTf.setText("");
            BuyCostTf.setText("");
            price4CostTf.setText("");
            price_10CostTf.setText("");
            addTf.setText("");
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }




    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addItemImage.setImage(new Image("icon/add_item.png"));
        DeleteImage.setImage(new Image("/icon/delete.png"));
        updateImage.setImage(new Image("/icon/update.png"));
        searchImage.setImage(new Image("/icon/search.png"));

        itemCode.setCellFactory(TextFieldTableCell.forTableColumn());
        itemName.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn());
        category.setCellFactory(TextFieldTableCell.forTableColumn());
        brand.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        price_4.setCellFactory(TextFieldTableCell.forTableColumn());
        price_10.setCellFactory(TextFieldTableCell.forTableColumn());

        itemCode.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(0)));
        itemName.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(1)));
        quantity.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(2)));
        category.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(3)));

        brand.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(4)));
        description.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(5)));
        buy_cost.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(6)));
        price_4.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(7)));
        price_10.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(8)));

        reload();




        inventoryTable.setRowFactory(tv -> {
            TableRow<ObservableList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    ObservableList clickedRow = row.getItem();
                    System.out.println(clickedRow);
                    itemCodeTf.setText(clickedRow.get(0).toString());
                    itemNameTf.setText(clickedRow.get(1).toString());
                    QuantityTf.setText(clickedRow.get(2).toString());
                    categoryTf.setText(clickedRow.get(3).toString());
                    brandTf.setText(clickedRow.get(4).toString());
                    DescriptionTf.setText(clickedRow.get(5).toString());
                    BuyCostTf.setText(clickedRow.get(6).toString());
                    price4CostTf.setText(clickedRow.get(7).toString());
                    price_10CostTf.setText(clickedRow.get(8).toString());
                }
            });
            return row ;
        });



    }

    void loadScreen(String url, Button button){
        AnchorPane anchorPane = new AnchorPane();
        try{
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource(url).openStream());
            anchorPane = fXMLLoader.getRoot();
        }catch (Exception e){
            e.printStackTrace();
        }



        Parent root = button.getParent().getParent().getParent().getParent().getParent().getParent();
        StackPane pane = (StackPane) root;
        pane.getChildren().clear();
        pane.getChildren().add(anchorPane);
    }
    private void reload() {
        ResultSet rs= null;
        data.clear();
        try {
            rs = Global.statement.executeQuery("SELECT PRODUCT_CODE,PRODUCT_NAME, QUANTITY, CATEGORY , BRAND, DESCRIPTION ,BUY_COST, PRICE_4, PRICE_10  FROM PRODUCT");
            while(rs.next())
            {
                ObservableList<String> row= FXCollections.observableArrayList();
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
                {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        inventoryTable.setItems(data);
    }


}
