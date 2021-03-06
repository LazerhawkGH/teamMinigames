/*
 * Name: Zachary Maarse & Shayne Humphries
 * Date: Oct 15, 2018
 * Purpose: Displays the main menu, allows the user to select the game of their choice
 */
package maarsehumphries.minigames;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class FXMLMainMenu implements Initializable {

    @FXML
    private Button btnG1;
    @FXML
    private Button btnG2;
    @FXML
    private Button btnG3;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnStore;
    
    MediaPlayer player;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        player.stop();
        if (btnExit.isArmed()) {
            System.exit(0);
        }
        if (btnG1.isArmed()) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLJumpDodge.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Jump Dodge");
            stage.show();
            home_page_scene.getRoot().requestFocus();
            stage.setOnCloseRequest(e -> System.exit(0));
        }
        if (btnG2.isArmed()) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLChronosAeon.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Chronos Aeon");
            stage.show();
            home_page_scene.getRoot().requestFocus();
            stage.setOnCloseRequest(e -> System.exit(0));
        }
        if (btnG3.isArmed()) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLRhythm.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Rhythm Game");
            stage.show();
            home_page_scene.getRoot().requestFocus();
            stage.setOnCloseRequest(e -> System.exit(0));
        }
        if (btnStore.isArmed()) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLShop.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(home_page_scene);
            stage.setTitle("Welcome to the Store!");
            stage.show();
            home_page_scene.getRoot().requestFocus();
            stage.setOnCloseRequest(e -> System.exit(0));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = new MediaPlayer((new Media(getClass().getResource("/RockyTheme.mp3").toString())));
        player.play();
    }
}
