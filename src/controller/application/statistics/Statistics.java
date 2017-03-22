package controller.application.statistics;

import controller.Global;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by matrixcode on 12/31/16.
 */
public class Statistics implements Initializable{

    @FXML
    private TableColumn<ObservableList, String > DescriptionRight;

    @FXML
    private ImageView YearImage;

    @FXML
    private TableColumn<ObservableList, String> AmountRight;

    @FXML
    private Button monthButton;

    @FXML
    private TableView<ObservableList> TableRight;

    @FXML
    private Button YearButton;

    @FXML
    private Button todayButton;

    @FXML
    private ImageView todayImage;

    @FXML
    private ImageView weekImage;

    @FXML
    private ImageView monthImage;

    @FXML
    private TableView<ObservableList> TableLeft;

    @FXML
    private TableColumn<ObservableList, String> DescriptionLeft;

    @FXML
    private Label labelDue;

    @FXML
    private Button weekButton;

    @FXML
    private Label labelStockValue;

    @FXML
    private TableColumn<ObservableList, String> AmountLeft;

    private final ObservableList<ObservableList> data= FXCollections.observableArrayList();
    private final ObservableList<ObservableList> data2= FXCollections.observableArrayList();

    @FXML
    void todayButtonOnClick(ActionEvent event) {
        reload(LocalDateTime.now());
    }

    @FXML
    void weekButtonOnClick(ActionEvent event) {
        LocalDateTime p = LocalDateTime.now().minusDays(7);
        reload(p);
    }

    @FXML
    void monthButtonOnClick(ActionEvent event) {
        LocalDateTime p = LocalDateTime.now().minusMonths(1);
        reload(p);
    }

    @FXML
    void YearButtonOnClick(ActionEvent event) {
        LocalDateTime p = LocalDateTime.now().minusYears(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        todayImage.setImage(new Image("/icon/today.png"));
        weekImage.setImage(new Image("/icon/week.png"));
        monthImage.setImage(new Image("/icon/month.png"));
        YearImage.setImage(new Image("/icon/year.png"));

        DescriptionLeft.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionRight.setCellFactory(TextFieldTableCell.forTableColumn());
        AmountLeft.setCellFactory(TextFieldTableCell.forTableColumn());
        AmountRight.setCellFactory(TextFieldTableCell.forTableColumn());

        DescriptionLeft.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(0)));
        DescriptionRight.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(0)));
        AmountLeft.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(1)));
        AmountRight.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(1)));


        String q = "SELECT SUM(BUY_COST * QUANTITY) FROM PRODUCT";

        try {
            ResultSet resultSet = Global.statement.executeQuery(q);
            labelStockValue.setText(resultSet.getString(1));

            q = "SELECT labelProductCost, TFC1, TFC2, TFC3,TFC4, TFC5,TFC6,TFC7,TFC8,TFC9,TFC10 FROM CUSTOMER";
            resultSet = Global.statement.executeQuery(q);
            long sum = 0;
            while (resultSet.next()) {
                sum += val(resultSet.getString(1));
                for(int i=2; i< resultSet.getMetaData().getColumnCount(); i++) {
                    sum -= val(resultSet.getString(i));
                }
            }
            labelDue.setText(String.valueOf(sum));
        } catch (SQLException e) {

            e.printStackTrace();
        }
        reload(LocalDateTime.now());

    }

    private void reload(LocalDateTime ldt) {
        ResultSet rs= null;
        data.clear();
        data2.clear();
        try {
            String q = "SELECT NAME FROM INCOMESOURCE";

            rs = Global.statement.executeQuery(q);
            ArrayList<String> names=  new ArrayList<>();
            while(rs.next()) {
                names.add(rs.getString(1));
            }
            rs.close();
            for(int i = 0; i < names.size(); i ++ ) {
                ObservableList<String> row =  FXCollections.observableArrayList();
                String innq = "SELECT SUM(AMOUNT) FROM LOGIN WHERE DESCRIPTION = \""+ names.get(i) + "\" AND TIME >= \"" + ldt.toString().substring(0,10) + "T00:00:00.001" + "\" ORDER BY TIME";
                System.out.println(innq);
                ResultSet inner = Global.statement.executeQuery( innq);
                row.add(names.get(i));
                row.add(inner.getString(1));
                data.add(row);
            }

            q = "SELECT NAME FROM OUTGOING";

            rs = Global.statement.executeQuery(q);
            names.clear();
            while(rs.next()) {
                names.add(rs.getString(1));
            }
            rs.close();
            for(int i = 0; i < names.size(); i ++ ) {
                ObservableList<String> row =  FXCollections.observableArrayList();
                String innq = "SELECT SUM(AMOUNT) FROM LOGOUT WHERE DESCRIPTION = \""+ names.get(i) + "\" AND TIME >= \"" + ldt.toString().substring(0,10) + "T00:00:00.001" + "\" ORDER BY TIME";
                ResultSet inner = Global.statement.executeQuery( innq);
                row.add(names.get(i));
                row.add(inner.getString(1));
                data2.add(row);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


        TableLeft.setItems(data);
        TableRight.setItems(data2);
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
