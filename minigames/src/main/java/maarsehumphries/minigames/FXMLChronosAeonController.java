/*
 * Author: Zachary Maarse
 * Date: Oct 15, 2018
 * Purpose: Allows the user to play a Space Invaders themed game
 */
package maarsehumphries.minigames;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author zacharym44
 */
public class FXMLChronosAeonController implements Initializable {

    @FXML private ImageView imgUser;
    @FXML private Rectangle rectBoundsTop;
    @FXML private Rectangle rectBoundsBottom;
    @FXML private Rectangle rectBoundsLeft;
    @FXML private Rectangle rectBoundsRight;

    Timeline movement = new Timeline(new KeyFrame(Duration.millis(50), ae -> move()));
    Timeline movementEnemies = new Timeline(new KeyFrame(Duration.millis(50), ae -> moveEnemies()));

    private Boolean upYes = false;
    private Boolean downYes = false;
    private Boolean leftYes = false;
    private Boolean rightYes = false;
    private Boolean gameStarted = false;
    private Boolean userMoving = false;

    Rectangle[] z;
    

    public boolean collision(ImageView block1, Rectangle block2) {
        //returns true if the areas intersect, false if they dont
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    private boolean collisionLoop() {
        for (Rectangle i : z) {
            if (collision(imgUser, i)) {
                return true;
                
            }
        }
        return false;
    }

    private void move() {
        if (upYes == true && !collisionLoop()) {
            imgUser.setTranslateY(imgUser.getTranslateY() - 5);
        } else if (downYes == true && !collisionLoop()) {
            imgUser.setTranslateY(imgUser.getTranslateY() + 5);
        } else if (leftYes == true && !collisionLoop()) {
            imgUser.setTranslateX(imgUser.getTranslateX() - 5);
        } else if (rightYes == true && !collisionLoop()) {
            imgUser.setTranslateX(imgUser.getTranslateX() + 5);
        }
        if (collisionLoop()){
            if (upYes){
                imgUser.setTranslateY(imgUser.getTranslateY() + 5);
                movement.stop();
                upYes=false;
                downYes=false;
                leftYes=false;
                rightYes=false;
                userMoving=false;
            }else if (downYes){
                imgUser.setTranslateY(imgUser.getTranslateY() - 5);
                movement.stop();
                upYes=false;
                downYes=false;
                leftYes=false;
                rightYes=false;
                userMoving=false;
            }else if (leftYes){
                imgUser.setTranslateX(imgUser.getTranslateX() + 5);
                movement.stop();
                upYes=false;
                downYes=false;
                leftYes=false;
                rightYes=false;
                userMoving=false;
            }else if (rightYes){
                imgUser.setTranslateX(imgUser.getTranslateX() - 5);
                movement.stop();
                upYes=false;
                downYes=false;
                leftYes=false;
                rightYes=false;
                userMoving=false;
            }
        }
        
    }

    private void moveEnemies() {

    }

    private Boolean bulletCreated = false;
    @FXML
    private void moveUser(KeyEvent e) {

        if (null != e.getCode()) {
            switch (e.getCode()) {
                case W:
                    upYes = true;
                    downYes = false;
                    leftYes = false;
                    rightYes = false;
                    break;
                case S:
                    upYes = false;
                    downYes = true;
                    leftYes = false;
                    rightYes = false;
                    break;
                case D:
                    upYes = false;
                    downYes = false;
                    leftYes = false;
                    rightYes = true;
                    break;
                case A:
                    upYes = false;
                    downYes = false;
                    leftYes = true;
                    rightYes = false;
                    break;
                case ENTER:
                bulletCreated = true;
                    break;

                default:
                    break;
            }
        }
        if (!userMoving) {
            userMoving = true;
            movement.setCycleCount(Timeline.INDEFINITE);
            movement.play();
        }
    }

//    private int xCoord = 0;
//    @FXML private AnchorPane anchor;
    
//    private void rootyTootShoot(){
//       
//        Bullet b = new Bullet((int)imgUser.getLayoutX(), (int)imgUser.getLayoutY(),anchor); // Creates a custom object
//                                                                                            // Refer to Bullet.java to see how it works                                                                           
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        z = new Rectangle[]{rectBoundsTop, rectBoundsBottom, rectBoundsLeft, rectBoundsRight};
    }

}
