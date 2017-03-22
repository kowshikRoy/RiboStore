/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.application.about;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class AboutMeController implements Initializable {
    @FXML
    private ImageView nafiImg;
    @FXML
    private  ImageView reponImg;

    Image image = new Image("/image/nafi.jpg");
    Image image2 = new Image("/image/repon.jpg");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nafiImg.setImage(image);
        reponImg.setImage(image2);

    }


}
