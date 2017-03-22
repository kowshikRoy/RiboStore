package controller.application.repair;

/**
 * Created by matrixcode on 12/31/16.
 */
import controller.Global;
import controller.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class newRepairController implements Initializable {
    @FXML
    private Button cancelButton;

    @FXML
    private Button submitButton;

    @FXML
    private ImageView categoryImage;

    @FXML
    private TextField giverName;

    @FXML
    private ImageView submitImage;

    @FXML
    private ImageView brandImage;

    @FXML
    private ImageView cancelImage;

    @FXML
    private Button BrandButton;

    @FXML
    private Button categoryButton;

    @FXML
    private TextField cardNo;

    @FXML
    private DatePicker arrivalDate;


    @FXML
    void cancelButtonOnClick(ActionEvent event) {
        loadScene("/view/application/repair/Repair.fxml", ((Button)event.getSource()).getParent().getParent().getParent().getParent().getParent().getParent().getParent());

    }

    @FXML
    void submitButtonOnClick(ActionEvent event) {

        try {
            String q = "INSERT INTO REPAIR (LABELCARD,INDATE,GIVER,STATUS) VALUES (\""+
                    cardNo.getText() + "\",\""+
                    arrivalDate.getValue() + "\",\""+
                    giverName.getText() + "\",\"" +
                    "PENDING" + "\")";
            System.out.println(q);
            Global.statement.executeUpdate(q);
            Toast.makeText((Stage) ((Button)event.getSource()).getScene().getWindow(),"Opearion Successful !",2000,200,200);
            loadScene("/view/application/repair/repair.fxml", ((Button)event.getSource()).getParent().getParent().getParent().getParent().getParent().getParent().getParent());

            return;
        } catch (Exception e) {
            Toast.makeText((Stage) ((Button)event.getSource()).getScene().getWindow(),"Please Enter Valid Data!",2000,200,200);
            e.printStackTrace();
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelImage.setImage(new Image("/icon/cancel.png"));
        submitImage.setImage(new Image("/icon/submit.png"));
    }
}
