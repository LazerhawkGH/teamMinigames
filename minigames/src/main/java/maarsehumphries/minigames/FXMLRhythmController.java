/*
 * Name: Zachary Maarse & Shayne Humphries
 * Date: October 15th, 2018
 * Purpose: Allows the user to play a game where you must press specific keys when the objects move to the correct location
 */
package maarsehumphries.minigames;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import static maarsehumphries.minigames.MainApp.*;

public class FXMLRhythmController implements Initializable {

    @FXML
    private Label lblTest;
    @FXML
    private ImageView imgUser;
    @FXML
    private ImageView imgU;
    @FXML
    private Button btnStart;
    @FXML
    private ImageView imgB;
    @FXML
    private Label lblPoints;
    @FXML
    private Label lblScore;

    Timeline approach = new Timeline(new KeyFrame(Duration.millis(15), ae -> move()));

    int rand = 1;
    int h = 4;
    int s = 0; // score in a play session

    int success = 0;
    int remain = 0;

    private boolean left = false;
    private boolean right = false;
    private boolean down = false;
    private boolean up = false;

    ArrayList<Integer> list = new ArrayList();

    MediaPlayer player;

    Image Up = new Image(getClass().getResource("/arrowU.png").toString());
    Image Down = new Image(getClass().getResource("/arrowD.png").toString());
    Image Right = new Image(getClass().getResource("/arrowR.png").toString());  // gathers and puts images of arrows in variables
    Image Left = new Image(getClass().getResource("/arrowL.png").toString());

    public void Begin(ActionEvent event) {
        if ( btnStart.isDisabled()== false) {
            btnStart.setDisable(true);
            choose();
            approach.play();
            h = 4;
            remain = 10;

        }
    }

    public void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.LEFT) { // makes boolean true for when key is pressed
            left = true;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            right = true;
        }
        if (event.getCode() == KeyCode.DOWN) {
            down = true;
        }
        if (event.getCode() == KeyCode.UP) {
            up = true;
        }

        if (event.getCode() == KeyCode.ESCAPE) { // opens alert to see if user wants to return to the main menu
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exiting to Main Menu");
            alert.setHeaderText("Do you wish to return to the main menu?");
            ButtonType btnYes = new ButtonType("Yes");
            ButtonType btnNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnYes, btnNo);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btnYes) {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml")); // loads up the main menu
                Scene home_page_scene = new Scene(home_page_parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(home_page_scene);
                stage.setTitle("Main Menu");
                stage.show();
                stage.setOnCloseRequest(e -> System.exit(0));
                player.stop(); // stops the music so that there is no audio overlap
                gameover();
            }
        }

    }

    public void keyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) { // if the key is released it will turn the variable from true to false
            left = false;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            right = false;
        }
        if (event.getCode() == KeyCode.DOWN) {
            down = false;
        }
        if (event.getCode() == KeyCode.UP) {
            up = false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        approach.setCycleCount(Timeline.INDEFINITE);
        imgU.setLayoutY(-100);
        imgB.setTranslateY(40);

        player = new MediaPlayer((new Media(getClass().getResource("/StayingAlive.mp3").toString()))); 
        player.play();
        lblPoints.setText("Points: "+ getPoints());
    


        
        setObjectiveUpgrade(getObjectiveUpgrade());
        
        if (boughtObjective){
            upgrade = 2;
        }
    }


    private int upgrade = 0;
    

    private void move() {
        imgU.setTranslateY(imgU.getTranslateY() + (h - upgrade));
        if (c(imgU, imgUser)) {
            if (list.get(0) == 1 && left == true) {
                list.removeAll(list);
                if (h < 14) {
                    h++;
                }
                s += h;
                lblScore.setText("Score: " + s);
                choose();
                imgU.setTranslateY(-100);

            }
            if (list.get(0) == 2 && up == true) {
                list.removeAll(list);
                if (h < 14) {
                    h++;
                }
                s += h;
                lblScore.setText("Score: " + s);
                choose();
                imgU.setTranslateY(-100);

            }
            if (list.get(0) == 3 && down == true) {
                list.removeAll(list);
                if (h < 14) {
                    h++;
                }
                s += h;
                lblScore.setText("Score: " + s);
                choose();
                imgU.setTranslateY(-100);

            }
            if (list.get(0) == 4 && right == true) {
                list.removeAll(list);
                if (h < 14) {
                    h++;
                }
                s += h;
                lblScore.setText("Score: " + s);
                choose();
                imgU.setTranslateY(-100);

            }
        }
        if (c(imgU, imgB)) {
            remain--;
            if (remain == 0) {
                approach.stop();
                gameover();
                choose();
            }

            list.removeAll(list);
            choose();
            imgU.setTranslateY(-100);
            if (h >= 5) {
                h-= 2;
            }

        }
    }

    public void choose() {
        rand = ThreadLocalRandom.current().nextInt(1, 4 + 1);
        switch (rand) {
            case 1:
                imgU.setLayoutX(70);
                list.add(1);
                imgU.setImage(Left);
                break;
            case 2:
                imgU.setLayoutX(232);
                list.add(2);
                imgU.setImage(Up);
                break;
            case 3:
                imgU.setLayoutX(380);
                list.add(3);
                imgU.setImage(Down);
                break;
            case 4:
                imgU.setLayoutX(534);
                list.add(4);
                imgU.setImage(Right);
                break;
        }
    }

    public boolean c(ImageView block1, ImageView block2) {
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    public void gameover() {
        btnStart.setDisable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //shows alert to tell of game over
        alert.setTitle("Game Over!");
        alert.setHeaderText(null);
        alert.setContentText("Your Score was " + s + "!");
        alert.show();
        lblPoints.setText("Points " + (getPoints() + s));
        setPoints(getPoints() + s);
        s = 0;
        lblScore.setText("Score: 0");
    }

}

