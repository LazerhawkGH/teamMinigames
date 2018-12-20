/*
 * Author: Shayne Humphries
 * Date: Oct 15,2018
 * Purpose: Play a game like the offline Google Chrome game
 */
package maarsehumphries.minigames;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

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

    int s = 0; // this variable is the score of the user in one game
    int m = 25; // this is the inital jump speed, the number here doesn't matter, since it is restated in the up method, but it needs to be initialized. 
    int n = 5; // the initial speed of the obstacle, just like with m, the number here doesn't matter, just that it now exits.
    int e = 0; //a variable used to limit the increase of obstacle speed  
    int f = 0; // a variable used to limit the increase of score.

    MotionBlur mb = new MotionBlur();

    Timeline jump = new Timeline(new KeyFrame(Duration.millis(20), ae -> up())); // timer for jumping
    Timeline obstacles = new Timeline(new KeyFrame(Duration.millis(10), ae -> move())); // timer for obstacle moving

    MediaPlayer player;

    private boolean c(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    public void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) { // will execute code if the spacebar is pressed
            if (c(imgB, imgG)) {
                imgB.setTranslateY(imgB.getTranslateY() - 5);// since jump will stop once it makes contact with the ground,it needs to be in contact with the ground to start jumping,this will push it out of the ground before starting
                m = 25; //   sets the initial speed, needs to be set each jump so that it doesn't just go down.                                                                                                                              
                jump.play();
            }
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            Alert alert = new Alert(AlertType.CONFIRMATION); // opens up dialog box asking user if they want to exit to the main menu
            alert.setTitle("Exiting to Main Menu");
            alert.setHeaderText("Do you wish to return to the main menu?");
            ButtonType btnYes = new ButtonType("YES"); // pressing yes will put this button into a variable called "result"
            ButtonType btnNo = new ButtonType("No", ButtonData.CANCEL_CLOSE); // if escape is pressed again, or they press this, it will close the alert
            alert.getButtonTypes().setAll(btnYes, btnNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btnYes) { // a check to see if the yes button is pressed, if so, it will stop the game if it is ongoing and exit to main menu
                gameover();
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(home_page_scene);
                stage.setTitle("Main Menu");
                stage.show();
                stage.setOnCloseRequest(e -> System.exit(0)); // makes it so that if the x button in the top right is used to exit instead of the designated exit button, everything will close anyway.
                player.stop();
            }

        }

    }

    @FXML
    public void Begin(ActionEvent event) {
        obstacles.setCycleCount(Timeline.INDEFINITE); // sets the cycle count to infinite so that the timer doesn't run once then stop.
        obstacles.play();       //starts various timers
        btnGame.setDisable(true); //disabling this button makes it so that users can't manually slow down the speed of the obstacle
        mb.setRadius(6);   // the motion blur is just to make the obstacle look slightly better, otherwise it looks kind of choppy
        imgO.setEffect(mb);
        n = 5; // the initial speed of the obstacle, this needs to be set at each beginning, otherwise the rock would never slow down
        e = 0; // this variable is used
    }

    public void up() {
        imgB.setTranslateY(imgB.getTranslateY() - m); // moves the user upwards
        m--; // decreases the amount the user wil go up by, until eventually going down again.
        if (c(imgB, imgG)) {
            jump.stop(); //stops timer if user collides with ground
            imgB.setEffect(null);
        }
    }

    private int upgrade = 0;

    public void move() {

        f += n - 3; // makes it so that each time the speed of the obstacle increses, the rate of points gained also increase
        if (f > 60) { // this is the hard limit that makes it so that
            s++;
            f = 0;
        }

        lblD.setText("Score: " + s);
        imgO.setTranslateX(imgO.getTranslateX() - (n - upgrade)); // the upgrade is the item from the store, it will slow down the obstacle by 2, 
        if (c(imgO, imgB)) { // checks if there is collision between the user and the obstacle

            gameover();

        }
        if (c(imgT, imgO)) { // checks if there is collision between the obstacle and an imageview that is to the left of the user, but slightly off screen
            imgO.setTranslateX(300);
            e++;
            if (e == 8 && n < 10) {
                n++;
                e = 0;
            }
        }
    }

    private void gameover() {
        obstacles.stop();
        imgO.setEffect(null);
        imgO.setTranslateX(1);
        btnGame.setDisable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //shows alert to tell of game over
        alert.setTitle("Game Over!");
        alert.setHeaderText(null);
        alert.setContentText("Your Score was " + s + "!");
        alert.show();
        lblPoints.setText("Points " + (getPoints() + s));
        setPoints(getPoints() + s);
        s = 0;
        lblD.setText("Score: 0");
        imgO.setTranslateX(300);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = new MediaPlayer((new Media(getClass().getResource("/HellMarch.mp3").toString()))); // sets the media
        player.play(); // plays the media
        lblPoints.setText("Points: " + getPoints()); //puts how many points the user has overall in a label
        imgT.setTranslateX(-100); // puts slightly off screen
        jump.setCycleCount(Timeline.INDEFINITE);
        imgO.setTranslateX(300); //puts obstacle off screen

        setObstacleUpgrade(getObstacleUpgrade());

        if (boughtObstacle) { // checks if upgrade from store is bought.
            upgrade = 2;
        }
    }
}
