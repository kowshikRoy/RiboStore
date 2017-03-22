package controller.application.repair;

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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by matrixcode on 12/31/16.
 */
public class RepairController implements Initializable {


    @FXML
    private TableView<ObservableList> RepairTable;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<ObservableList, String> status;

    @FXML
    private Button deleteButton;

    @FXML
    private ImageView DeleteImage;

    @FXML
    private TableColumn<ObservableList,String> entryNo;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private TableColumn<ObservableList, String> CardNo;

    @FXML
    private ImageView addItemImage;

    @FXML
    private TextField customerNameTf;

    @FXML
    private ImageView updateImage;

    @FXML
    private TextField labelCardTf;

    @FXML
    private TextField search_id;

    @FXML
    private ImageView searchImage;

    @FXML
    private DatePicker Arrive;

    @FXML
    private TableColumn<ObservableList, String > Arrival;

    @FXML
    private TextField entryCodeTf;

    @FXML
    private TextField itemNameTf;

    @FXML
    private DatePicker Departure;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<ObservableList, String > delivery;

    @FXML
    private Button addItemButton;

    @FXML
    private TableColumn<ObservableList, String> tableReceiver;

    @FXML
    private TableColumn<ObservableList, String> tableGiver;
    @FXML
    private TextField Giver;

    @FXML
    private TextField Taker;

    private final ObservableList<ObservableList> data= FXCollections.observableArrayList();


    @FXML
    void searchButtonOnClick(ActionEvent event) {
        clear();
        if(search_id.getText().equals("")==false) {
            ResultSet rs= null;
            data.clear();
            try {
                rs = Global.statement.executeQuery("SELECT INI,LABELCARD,INDATE,OUTDATE,STATUS,GIVER,TAKER FROM REPAIR WHERE " +
                        "LABELCARD = \"" + search_id.getText() + "\"");
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
            RepairTable.setItems(data);

        }
        else {
            Toast.makeText((Stage)updateButton.getScene().getWindow(),"PLEASE ENTER SEARCH ID",2000,200,200);
        }

    }

    @FXML
    void addItemButtonOnClick(ActionEvent event) {
        loadScreen("/view/application/repair/newRepair.fxml", (Button)event.getSource());
    }

    @FXML
    void updateButtonOnClick(ActionEvent event) {

        if(entryCodeTf.getText().equals("")) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");

        alert.setContentText("Do you want to update this product ? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            String q = "UPDATE REPAIR SET " +
                    "OUTDATE = \""+ Departure.getValue() + "\"," +
                    "STATUS = \""+ comboStatus.getValue() + "\"," +
                    "TAKER = \"" + Taker.getText() + "\"" +
                    "WHERE " +
                    "INI = \""+ entryCodeTf.getText() + "\"" ;

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
            clear();
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

            String q = "DELETE FROM REPAIR WHERE INI = \"" + entryCodeTf.getText() + "\"";
            System.out.println(q);
            try {
                Global.statement.executeUpdate(q);
                Toast.makeText((Stage)updateButton.getScene().getWindow(),"Delete Successful" ,2000,200,200);
                reload();
            } catch (SQLException e) {

                e.printStackTrace();
            }
            clear();
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
        comboStatus.getItems().addAll("FINISHED","PENDING");
        Arrival.setCellFactory(TextFieldTableCell.forTableColumn());
        delivery.setCellFactory(TextFieldTableCell.forTableColumn());
        entryNo.setCellFactory(TextFieldTableCell.forTableColumn());
        CardNo.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        tableReceiver.setCellFactory(TextFieldTableCell.forTableColumn());
        tableGiver.setCellFactory(TextFieldTableCell.forTableColumn());

        entryNo.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(0)));
        CardNo.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(1)));
        Arrival.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(2)));
        delivery.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(3)));

        status.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(4)));
        tableGiver.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(5)));
        tableReceiver.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(6)));

        reload();
        RepairTable.setRowFactory(tv -> {
            TableRow<ObservableList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    clear();
                    ObservableList clickedRow = row.getItem();
                    System.out.println(clickedRow);
                    entryCodeTf.setText(clickedRow.get(0).toString());
                    labelCardTf.setText(clickedRow.get(1).toString());

                    if(clickedRow.get(2) != null ) {
                        int [] d = get(clickedRow.get(2).toString());
                        if(d.length == 3) Arrive.setValue(LocalDate.of(d[0],d[1],d[2]));
                    }


                    if(clickedRow.get(3) != null) {
                        int [] d = get(clickedRow.get(3).toString());
                        Departure.setValue(LocalDate.of(d[0],d[1],d[2]));
                    }
                    comboStatus.setValue(clickedRow.get(4).toString());

                    if(clickedRow.get(5) != null)Giver.setText(clickedRow.get(5).toString());
                    if(clickedRow.get(6) != null)Taker.setText(clickedRow.get(6).toString());

                    String [] ret= who(labelCardTf.getText());
                    customerNameTf.setText(ret[0]);
                    itemNameTf.setText(ret[1]);

                }
            });
            return row ;
        });

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
    String [] who(String code)
    {
        String q = "SELECT LABELCUSTOMERNAME,LABELPRODUCTID FROM CUSTOMER WHERE LABELCARD = \"" + code + "\"";
        String[] ret = new String[3];
        try {
            ResultSet resultSet = Global.statement.executeQuery(q);
            while(resultSet.next()){
                ret[0] = resultSet.getString(1);
                ret[1] = resultSet.getString(2);
            }
            return  ret;

        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;

    }
    private void reload() {
        ResultSet rs= null;
        data.clear();
        try {
            rs = Global.statement.executeQuery("SELECT INI,LABELCARD,INDATE,OUTDATE,STATUS,GIVER,TAKER FROM REPAIR WHERE STATUS = \"PENDING\"");
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


        RepairTable.setItems(data);
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
    void clear()
    {
        entryCodeTf.setText("");
        labelCardTf.setText("");
        Arrive.setValue(null);
        Departure.setValue(null);
        comboStatus.setValue(null);

        Giver.setText("");
        Taker.setText("");

        String [] ret= who(labelCardTf.getText());
        customerNameTf.setText(ret[0]);
        itemNameTf.setText(ret[1]);
        search_id.setText("");
    }
}
