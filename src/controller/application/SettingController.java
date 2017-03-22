package controller.application;
import controller.Global;
import controller.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by matrixcode on 12/26/16.
 */
public class SettingController implements Initializable {



    @FXML
    private TextField newPass;

    @FXML
    private ImageView submitImage2;

    @FXML
    private ImageView submitImage1;

    @FXML
    private TextField companypass;

    @FXML
    private ImageView submitImage3;

    @FXML
    private TextField companiEmail;


    @FXML
    private TextField recoveryEmail;

    @FXML
    private TextField newPass2;

    @FXML
    private TextField line2;

    @FXML
    private TextField oldPass;

    @FXML
    private TextField usrName;

    @FXML
    private TextField line1;

    @FXML
    private TextField line0;


    @FXML
    private void submitButtonOnClick(ActionEvent event) {
        Button button = (Button)event.getSource();
        try {
            String q = "SELECT * FROM ADMININFO WHERE USERID = \"" + usrName.getText() + "\" AND PASSWORD = \"" + oldPass.getText() + "\"";
            System.out.println(q);
            ResultSet resultSet = Global.statement.executeQuery(q);

            if (resultSet.next() && newPass.getText().equals(newPass2.getText())== true) {
                 q = "UPDATE ADMININFO SET PASSWORD = \"" + newPass.getText() + "\"" + " WHERE USERID = \"" + usrName.getText() + "\"";
                System.out.println(q);
                Global.statement.executeUpdate(q);
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "Password Change Successful", 2000, 200, 200);

                loadScene("/view/application/home/Home.fxml", button.getParent().getParent().getParent().getParent().getParent());
            } else {
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            }

        } catch (SQLException e) {
            Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            e.printStackTrace();
        }

        clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitImage1.setImage(new Image("/icon/submit2.png"));
        submitImage2.setImage(new Image("/icon/submit2.png"));
        submitImage3.setImage(new Image("/icon/submit2.png"));
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

    public void submitButton2OnClick(ActionEvent event) {
        if(companiEmail.getText().equals("")==false){
            String q = "UPDATE COMPANY SET EMAIL = \""+ companiEmail.getText() + "\"";
            try {
                Global.statement.executeUpdate(q);
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            }
        }
        if(companypass.getText().equals("")==false){
            String q = "UPDATE COMPANY SET PASSWORD = \""+ companypass.getText() + "\"";
            try {
                Global.statement.executeUpdate(q);
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            }
        }
        if(recoveryEmail.getText().equals("")==false ) {
            String q = "UPDATE COMPANY SET RECOVER = \""+ recoveryEmail.getText() + "\"";
            try {
                Global.statement.executeUpdate(q);
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            }
        }
        clear();
    }

    public void submitButton3OnClick(ActionEvent event) {
        if(line0.getText().equals("")==false) {
            String q = "UPDATE COMPANY SET NAME = \""+ line0.getText() + "\"";
            try {
                Global.statement.executeUpdate(q);
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            }
        }
        if(line1.getText().equals("")==false){
            String q = "UPDATE COMPANY SET LINE1 = \""+ line1.getText() + "\"";
            try {
                Global.statement.executeUpdate(q);
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            }
        }
        if(line2.getText().equals("")==false) {
            String q = "UPDATE COMPANY SET LINE2 = \""+ line2.getText() + "\"";
            try {
                Global.statement.executeUpdate(q);
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText((Stage) ((Button) (event.getSource())).getScene().getWindow(), "An Error Occured !", 3500, 250, 250);
            }
        }
        clear();
    }
    private void clear()
    {
        usrName.setText("");
        newPass.setText("");
        companypass.setText("");
        companiEmail.setText("");
        recoveryEmail.setText("");
        newPass2.setText("");
        line2.setText("");
        oldPass.setText("");
        line1.setText("");
        line0.setText("");


    }
}

