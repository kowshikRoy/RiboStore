/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreKeeper;


import Database.DBConnection;
import controller.Global;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author rifat
 */
public class StoreKeeperMain extends Application {
    

    @Override
    public void start(Stage primaryStage) throws IOException {
        conectDB();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Application.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome to RiboStore");
        primaryStage.getIcons().add(new Image("/image/icon.png"));
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(700.0);
        primaryStage.setMinWidth(1000.0);
        //primaryStage.setFullScreen(true);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();



    }

    private void conectDB() {
        try {
            Global.con = DBConnection.ConnectDB();
            Global.con.createStatement();
            Global.statement = Global.con.createStatement();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
