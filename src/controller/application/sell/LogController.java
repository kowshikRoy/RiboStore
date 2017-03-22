package controller.application.sell;

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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by matrixcode on 12/26/16.
 */
public class LogController implements Initializable{


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
    private TableColumn<ObservableList, String > TimeRight;

    @FXML
    private Button todayButton;

    @FXML
    private ImageView todayImage;

    @FXML
    private ImageView weekImage;

    @FXML
    private ImageView monthImage;

    @FXML
    private TableColumn<ObservableList, String> TimeLeft;

    @FXML
    private TableView<ObservableList> TableLeft;

    @FXML
    private TableColumn<ObservableList, String> DescriptionLeft;

    @FXML
    private Label labelLeft;

    @FXML
    private Label lableRight;

    @FXML
    private Button weekButton;

    @FXML
    private TableColumn<ObservableList, String> AmountLeft;

    @FXML
    void todayButtonOnClick(ActionEvent event) {
        reload(LocalDateTime.now());
    }

    @FXML
    void weekButtonOnClick(ActionEvent event) {
        LocalDateTime ldt = LocalDateTime.now().minusDays(7);
        reload(ldt);
    }

    @FXML
    void monthButtonOnClick(ActionEvent event) {
        LocalDateTime ldt = LocalDateTime.now().minusMonths(1);
        reload(ldt);
    }

    @FXML
    void YearButtonOnClick(ActionEvent event) {
        LocalDateTime ldt = LocalDateTime.now().minusYears(1);
        reload(ldt);
    }

    private final ObservableList<ObservableList> data= FXCollections.observableArrayList();
    private final ObservableList<ObservableList> data2= FXCollections.observableArrayList();
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
        TimeLeft.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeRight.setCellFactory(TextFieldTableCell.forTableColumn());


        DescriptionLeft.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(0)));
        DescriptionRight.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(0)));
        AmountLeft.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(1)));
        AmountRight.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(1)));

        TimeLeft.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(2)));
        TimeRight.setCellValueFactory((TableColumn.CellDataFeatures<ObservableList, String> p)
                -> new SimpleStringProperty((String) p.getValue().get(2)));


        reload(LocalDateTime.now());

    }

    private void reload(LocalDateTime ldt ) {
        ResultSet rs= null;
        data.clear();
        data2.clear();
        try {
            String q = "SELECT DESCRIPTION, AMOUNT , TIME FROM LOGIN WHERE TIME >=\"" + ldt.toString().substring(0,10) + "T00:00:00.001" + "\" ORDER BY TIME";
            System.out.println(q);
            rs = Global.statement.executeQuery(q);
            while(rs.next())
            {
                ObservableList<String> row= FXCollections.observableArrayList();
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
                {
                    if(i == 3) {
                        String oo = rs.getString(i);
                        LocalDateTime oh = LocalDateTime.parse(oo);
                        String gen = oh.getDayOfMonth() + " " + oh.getMonth().toString().substring(0,3) + " "+ oh.getYear() + " , " + oh.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                        row.add(gen);
                    }
                    else row.add(rs.getString(i));
                }
                data.add(row);
            }
            String q2 = "SELECT DESCRIPTION, AMOUNT , TIME FROM LOGOUT WHERE TIME >=\"" + ldt.toString().substring(0,10) + "T00:00:00.001" + "\" ORDER BY TIME";
            rs = Global.statement.executeQuery(q2);
            while(rs.next())
            {
                ObservableList<String> row= FXCollections.observableArrayList();
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
                {
                    if(i == 3) {
                        String oo = rs.getString(i);
                        LocalDateTime oh = LocalDateTime.parse(oo);
                        String gen = oh.getDayOfMonth() + " " + oh.getMonth().toString().substring(0,3) + " "+ oh.getYear() + " , " + oh.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                        row.add(gen);
                    }
                    else row.add(rs.getString(i));
                }
                data2.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        TableLeft.setItems(data);
        TableRight.setItems(data2);
    }
}
