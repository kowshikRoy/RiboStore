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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class customerListController implements Initializable {


    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button nextWeek;

    @FXML
    private TableColumn<ObservableList, String > labelNextDate;

    @FXML
    private TableColumn<ObservableList, String > labelCustomerAddress;

    @FXML
    private ImageView AllImage;

    @FXML
    private TableColumn<ObservableList, String > lableCustomerMobile;

    @FXML
    private Button todayButton;

    @FXML
    private TableColumn<ObservableList, String > comboGroup;

    @FXML
    private ImageView nextWeekImage;

    @FXML
    private TableColumn<ObservableList, String > comboDay;

    @FXML
    private Button tomorrowButton;

    @FXML
    private ImageView tomorrowImage;

    @FXML
    private ImageView todatImage;

    @FXML
    private Button totalDueCustomer;

    @FXML
    private TableColumn<ObservableList, String > labelProductId;

    @FXML
    private TableColumn<ObservableList, String > labelProductCost;

    @FXML
    private TableColumn<ObservableList, String> labelCard;

    @FXML
    private TableColumn<ObservableList, String> labelCustomerName;

    @FXML
    private TableColumn<ObservableList, String> labelRefName;

    @FXML
    private TableView<ObservableList> customers;
    ResultSet resultSet;
    private final ObservableList<ObservableList> data= FXCollections.observableArrayList();
    @FXML
    void todayButtonOnClick(ActionEvent event) {
        String Today = new Date().toString().split(" ")[0].toUpperCase();
        LocalDate now = LocalDate.now();
        String q = "SELECT COMBODAY,COMBOGROUP,LABELCARD,LABELCUSTOMERNAME,LABELCUSTOMERADDRESS,LABELREFNAME,LABELCUSTOMERMOBILE,LABELPRODUCTID,LABELPRODUCTCOST,LABELNEXTDATE FROM CUSTOMER WHERE "
                + "COMBODAY = \"" + Today + "\" AND LABELNEXTDATE <= \""+now.toString()+ "\" AND COMBOSTATUS = \"DUE\"  ORDER BY COMBOGROUP";
        System.out.println(q);
        try {
            resultSet = Global.statement.executeQuery(q);
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tomorrowButtonOnClick(ActionEvent event) {
        LocalDate now = LocalDate.now();
        LocalDate nextDay = now.plusDays(1);
        String q = "SELECT COMBODAY,COMBOGROUP,LABELCARD,LABELCUSTOMERNAME,LABELCUSTOMERADDRESS,LABELREFNAME,LABELCUSTOMERMOBILE,LABELPRODUCTID,LABELPRODUCTCOST,LABELNEXTDATE FROM CUSTOMER WHERE "
                + "COMBODAY = \"" + day(nextDay) + "\" AND LABELNEXTDATE <= \""+nextDay.toString()+ "\" AND COMBOSTATUS = \"DUE\"  ORDER BY COMBOGROUP";
        System.out.println(q);
        try {
            resultSet = Global.statement.executeQuery(q);
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String day(LocalDate ld){
        String o = ld.getDayOfWeek().toString().substring(0,3);
        if(o.compareTo("THU")==0) return "THR";
        return o;
    }

    @FXML
    void nextWeekButton(ActionEvent event) {
        LocalDate week = LocalDate.now().plusDays(7);
        String q = "SELECT COMBODAY,COMBOGROUP,LABELCARD,LABELCUSTOMERNAME,LABELCUSTOMERADDRESS,LABELREFNAME,LABELCUSTOMERMOBILE,LABELPRODUCTID,LABELPRODUCTCOST,LABELNEXTDATE FROM CUSTOMER WHERE "
                +" LABELNEXTDATE <= \""+week.toString()+ "\" AND COMBOSTATUS = \"DUE\"  ORDER BY COMBOGROUP";
        System.out.println(q);
        try {
            resultSet = Global.statement.executeQuery(q);
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void totalDueCustomerOnClick(ActionEvent event) {
        String q = "SELECT COMBODAY,COMBOGROUP,LABELCARD,LABELCUSTOMERNAME,LABELCUSTOMERADDRESS,LABELREFNAME,LABELCUSTOMERMOBILE,LABELPRODUCTID,LABELPRODUCTCOST,LABELNEXTDATE FROM CUSTOMER WHERE "
                +"COMBOSTATUS = \"DUE\"  ORDER BY COMBOGROUP";
        System.out.println(q);
        try {
            resultSet = Global.statement.executeQuery(q);
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        todatImage.setImage(new Image("/icon/today.png"));
        tomorrowImage.setImage(new Image("/icon/tomorrow.png"));
        nextWeekImage.setImage(new Image("/icon/week.png"));
        AllImage.setImage(new Image("/icon/all.png"));

        customers.setEditable(false);
        String Today = new Date().toString().split(" ")[0].toUpperCase();
        LocalDate now = LocalDate.now();
        String q = "SELECT COMBODAY,COMBOGROUP,LABELCARD,LABELCUSTOMERNAME,LABELCUSTOMERADDRESS,LABELREFNAME,LABELCUSTOMERMOBILE,LABELPRODUCTID,LABELPRODUCTCOST,LABELNEXTDATE FROM CUSTOMER WHERE "
                + "COMBODAY = \"" + Today + "\" AND LABELNEXTDATE <= \""+now.toString()+ "\" AND COMBOSTATUS = \"DUE\"  ORDER BY COMBOGROUP";

        comboDay.setCellFactory(TextFieldTableCell.forTableColumn());
        comboGroup.setCellFactory(TextFieldTableCell.forTableColumn());
        labelCard.setCellFactory(TextFieldTableCell.forTableColumn());
        labelCustomerName.setCellFactory(TextFieldTableCell.forTableColumn());
        labelCustomerAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        labelRefName.setCellFactory(TextFieldTableCell.forTableColumn());
        lableCustomerMobile.setCellFactory(TextFieldTableCell.forTableColumn());
        labelProductId.setCellFactory(TextFieldTableCell.forTableColumn());
        labelProductCost.setCellFactory(TextFieldTableCell.forTableColumn());
        labelNextDate.setCellFactory(TextFieldTableCell.forTableColumn());

        comboDay.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(0)));
        comboGroup.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(1)));
        labelCard.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(2)));
        labelCustomerName.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(3)));

        labelCustomerAddress.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(4)));
        labelRefName.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(5)));
        lableCustomerMobile.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(6)));
        labelProductId.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(7)));
        labelProductCost.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(8)));
        labelNextDate.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(9)));

        resultSet = null;
        data.clear();
        try {
            resultSet = Global.statement.executeQuery(q);
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customers.setRowFactory(tv -> {
            TableRow<ObservableList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    ObservableList clickedRow = row.getItem();
                    Global.setCustomerId(Long.parseLong(clickedRow.get(2).toString()));

                    String url = "/view/application/sell/formView.fxml";
                    AnchorPane anchorPane = new AnchorPane();
                    try{
                        FXMLLoader fXMLLoader = new FXMLLoader();
                        fXMLLoader.load(getClass().getResource(url).openStream());
                        anchorPane = fXMLLoader.getRoot();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Parent root = todayButton.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                    StackPane pane = (StackPane) root;
                    pane.getChildren().clear();
                    pane.getChildren().setAll(anchorPane);
                }
            });
            return row ;
        });

    }

    private void load()
    {
        data.clear();
        try {
            while(resultSet.next())
            {
                ObservableList<String> row=FXCollections.observableArrayList();
                for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++)
                {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customers.setItems(data);

    }

}
