package controller;

import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by matrixcode on 10/28/16.
 */

public class LoginController implements Initializable{

    @FXML
    public Label loginLabel;


    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField pfUserPassword;



    @FXML
    public void btnLogin(ActionEvent actionEvent) throws Exception {

        // sql
        Connection con = DBConnection.ConnectDB();
        con.createStatement();    // newConnection can createstament.
        Statement statement = con.createStatement();
        String q = "SELECT * from AdminInfo WHERE UserId = \"" + tfUserName.getText() + "\" AND Password = \"" + pfUserPassword.getText()+"\"";
        System.out.println(q);
        ResultSet resultSet = statement.executeQuery(q);

        if(resultSet.next()){
            if(resultSet.getInt(3)==3) {



                Global.setUser(3);


            }
            else {
                Global.setUser(1);
                Stage v = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                v.close();

                Parent root = FXMLLoader.load(getClass().getResource("/view/Application.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Welcome to RiboStore");
                primaryStage.getIcons().add(new Image("/image/icon.png"));
                primaryStage.setMaximized(false);
                primaryStage.setMinHeight(700.0);
                primaryStage.setMinWidth(1000.0);
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                primaryStage.setWidth(bounds.getWidth());
                primaryStage.setHeight(bounds.getHeight());
                primaryStage.show();
            }


        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong UserName or Password");


            alert.showAndWait();
        }
        con.close();
        statement.close();
        resultSet.close();
    }

    @FXML
    public void handleCancelButton(ActionEvent event) throws Exception{
        Stage v = (Stage)((Button)event.getSource()).getScene().getWindow();
        v.close();


        Parent root = FXMLLoader.load(getClass().getResource("/view/Application.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome to RiboStore");
        primaryStage.getIcons().add(new Image("/image/icon.png"));
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(700.0);
        primaryStage.setMinWidth(1000.0);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
