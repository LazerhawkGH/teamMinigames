/*
Shayne Humphries

 */
package maarsehumphries.minigames;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import static maarsehumphries.minigames.MainApp.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author Shayne
 */
public class FXMLJumpDodgeController implements Initializable {

    @FXML
    private ImageView imgB;
    @FXML
    private ImageView imgG;
    @FXML
    private ImageView imgO;
    @FXML
    private Label lblD;
    @FXML
    private ImageView imgT;
    @FXML
    private Button btnGame;
    @FXML
    private Label lblPoints;

    int s = 0;
    int m = 20;
    int n = 4;
    int e = 0;

    int f = 0;

    MotionBlur mb = new MotionBlur();

    Timeline jump = new Timeline(new KeyFrame(Duration.millis(20), ae -> up()));
    Timeline obstacles = new Timeline(new KeyFrame(Duration.millis(10), ae -> move()));
    
    MediaPlayer player;

    private boolean c(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    public void keyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ESCAPE) {
           Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exiting to Main Menu");
            alert.setHeaderText("Do you wish to return to the main menu?");
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnYes, btnNo);
            Optional<ButtonType> result = alert.showAndWait(); 
            
        }
        if (event.getCode() == KeyCode.SPACE) {
            if (c(imgB, imgG)) {
                imgB.setTranslateY(imgB.getTranslateY() - 5);
                m = 50;
                mb.setRadius(4);
                mb.setAngle(90);
                imgB.setEffect(mb);
                jump.play();
            }
        }
    }

    @FXML
    public void Begin(ActionEvent event) {
        obstacles.setCycleCount(Timeline.INDEFINITE);
        obstacles.play();       //starts various timers
        btnGame.setDisable(true);
        mb.setRadius(6);
        imgO.setEffect(mb);
        n = 4;
        e = 0;
    }

    public void up() {
        imgB.setTranslateY(imgB.getTranslateY() - m); // moves the user upwards
        m--; // decreases the amount the user wil go up by, until eventually going down again.
        if (c(imgB, imgG)) {
            jump.stop(); //stops timer if user collides with ground
        }
    }

    public void move() {

        f += n - 3;
        if (f > 70) {
            s++;
        }

        lblD.setText("Score: " + s);
        imgO.setTranslateX(imgO.getTranslateX() - n);
        if (c(imgO, imgB)) {
            obstacles.stop();
            imgO.setEffect(null);
            imgO.setTranslateX(1);
            btnGame.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //shows alert to tell of game over
            alert.setTitle("Game Over!");
            alert.setHeaderText(null);
            alert.setContentText("Your Score was " + s + "!");
            alert.show();

            lblPoints.setText("Points " + getPoints() + s);
            s = 0;
            lblD.setText("Score: 0");

        }
        if (c(imgT, imgO)) {
            imgO.setTranslateX(300);
            e++;
            if (e == 8 && n < 9) {
                n++;
                e = 0;
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = new MediaPlayer((new Media(getClass().getResource("//RockyTheme.mp3").toString())));
        player.play();
        setPoints(getPoints());
        lblPoints.setText("Points: " + getPoints());
        imgT.setTranslateX(-100);
        jump.setCycleCount(Timeline.INDEFINITE);
        imgO.setTranslateX(300);
    }
}
