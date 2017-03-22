package controller.application.sell;

/**
 * Created by matrixcode on 12/25/16.
 */

import controller.Global;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class viewInventoryController implements Initializable{


    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<ObservableList, String> quantity;

    @FXML
    private TableColumn<ObservableList, String> price_4;

    @FXML
    private TableColumn<ObservableList, String> itemCode;

    @FXML
    private TableColumn<ObservableList, String> description;

    @FXML
    private TableColumn<ObservableList, String> price_10;

    @FXML
    private TextField search_item_code;

    @FXML
    private TableView<ObservableList> inventoryTable;

    @FXML
    private ImageView searchImage;

    @FXML
    private TableColumn<ObservableList, String> itemName;

    @FXML
    private TextField search_category;

    @FXML
    private TextField search_brand;

    @FXML
    private TableColumn<ObservableList, String> category;

    @FXML
    private TableColumn<ObservableList, String> brand;

    private final ObservableList<ObservableList> data= FXCollections.observableArrayList();
    @FXML
    void searchButtonOnClick(ActionEvent event) {
        if(search_item_code.getText().equals("")==false || search_category.getText().equals("")==false || search_brand.getText().equals("")==false) {
            ResultSet rs= null;
            String q = "SELECT PRODUCT_CODE,PRODUCT_NAME, QUANTITY, CATEGORY , BRAND, DESCRIPTION , PRICE_4, PRICE_10  from PRODUCT WHERE";
            boolean prv= false;
            if(search_item_code.getText().equals("")==false){
                if(prv) q += " AND ";
                q += " PRODUCT_CODE = \""+ search_item_code.getText()+"\"";
                prv = true;
            }
            if(search_category.getText().equals("")==false){
                if(prv) q += " AND ";
                q += " CATEGORY = \""+ search_category.getText()+"\"";
                prv = true;
            }
            if(search_brand.getText().equals("")==false){
                if(prv) q += " AND ";
                q += " BRAND = \""+ search_brand.getText()+"\"";
                prv = true;
            }
            System.out.println(q);
            data.clear();
            try {
                rs = Global.statement.executeQuery(q);
                while(rs.next()) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        price_4.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(6)));
        price_10.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(7)));

        ResultSet rs= null;
        try {
            rs = Global.statement.executeQuery("SELECT PRODUCT_CODE,PRODUCT_NAME, QUANTITY, CATEGORY , BRAND, DESCRIPTION , PRICE_4, PRICE_10  FROM PRODUCT");
            while(rs.next())
            {
                ObservableList<String> row=FXCollections.observableArrayList();
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
