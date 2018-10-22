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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

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
    
    @FXML private Rectangle recBullet;
    
    @FXML private Rectangle recEnemy1; @FXML private Rectangle recEnemy2; @FXML private Rectangle recEnemy3;
    @FXML private Rectangle recEnemy4; @FXML private Rectangle recEnemy5; @FXML private Rectangle recEnemy6;
    @FXML private Rectangle recEnemy7; @FXML private Rectangle recEnemy8; @FXML private Rectangle recEnemy9;
    @FXML private Rectangle recEnemy10;@FXML private Rectangle recEnemy11;@FXML private Rectangle recEnemy12;
    @FXML private Rectangle recEnemy13;@FXML private Rectangle recEnemy14;@FXML private Rectangle recEnemy15;
    @FXML private Rectangle recEnemy16;@FXML private Rectangle recEnemy17;@FXML private Rectangle recEnemy18;
    @FXML private Rectangle recEnemy19;@FXML private Rectangle recEnemy20;@FXML private Rectangle recEnemy21;
    @FXML private Rectangle recEnemy22;@FXML private Rectangle recEnemy23;@FXML private Rectangle recEnemy24;
    @FXML private Rectangle recEnemy25;@FXML private Rectangle recEnemy26;@FXML private Rectangle recEnemy27;
    @FXML private Rectangle recEnemy28;@FXML private Rectangle recEnemy29;@FXML private Rectangle recEnemy30;
    @FXML private Rectangle recEnemy31;@FXML private Rectangle recEnemy32;@FXML private Rectangle recEnemy33;
    @FXML private Rectangle recEnemy34;@FXML private Rectangle recEnemy35;@FXML private Rectangle recEnemy36;
    

    Timeline movement = new Timeline(new KeyFrame(Duration.millis(50), ae -> move()));
    Timeline moveBullet = new Timeline(new KeyFrame(Duration.millis(50), ae -> bulletMove()));
    Timeline movementEnemies = new Timeline(new KeyFrame(Duration.millis(50), ae -> moveEnemies()));

    private Boolean upYes = false;
    private Boolean downYes = false;
    private Boolean leftYes = false;
    private Boolean rightYes = false;
    private Boolean gameStarted = false;
    private Boolean userMoving = false;

    Rectangle[] z;
    Rectangle[] enemies;
//  Rectangle[] bullets;
//  ArrayList<Rectangle> bullets = new ArrayList();

    public boolean collision(ImageView block1, Rectangle block2) {
        //returns true if the areas intersect, false if they dont
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }
    public boolean cEnemies(Rectangle block1, Rectangle block2){
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

    private boolean collisionBullets(){
        for (Rectangle i:enemies){
            if (cEnemies(recBullet, i)){
                return true;
            }
        }
        return false;    
    }
    
    private void bulletMove(){
       recBullet.setTranslateY(recBullet.getTranslateY() + 2);
    }
    
    private void move() {
        // If the user isn't colliding with a wall, and a direction has been inputted, move them in the direction
        if (upYes == true && !collisionLoop()) {
            imgUser.setTranslateY(imgUser.getTranslateY() - 5);
        } else if (downYes == true && !collisionLoop()) {
            imgUser.setTranslateY(imgUser.getTranslateY() + 5);
        } else if (leftYes == true && !collisionLoop()) {
            imgUser.setTranslateX(imgUser.getTranslateX() - 5);
        } else if (rightYes == true && !collisionLoop()) {
            imgUser.setTranslateX(imgUser.getTranslateX() + 5);
        }
        
        // If they are colliding with a wall, move them in the opposite direction and stop the movement
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
                    if (!bulletCreated){
                        bulletCreated=true;
                        recBullet.setTranslateX(imgUser.getTranslateX());
                        recBullet.setTranslateY(imgUser.getTranslateY());
                        moveBullet.play();
                        break;
                    }
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
        enemies = new Rectangle[]{recEnemy1,recEnemy2,recEnemy3,recEnemy4,recEnemy5,recEnemy6,recEnemy7,recEnemy8,recEnemy9,recEnemy10,recEnemy11,recEnemy12,recEnemy13,recEnemy14,recEnemy15,recEnemy16,recEnemy17,recEnemy18,recEnemy19,recEnemy20,recEnemy21,recEnemy22,recEnemy23,recEnemy24,recEnemy25,recEnemy26,recEnemy27,recEnemy28,recEnemy29,recEnemy30,recEnemy31,recEnemy32,recEnemy33,recEnemy34,recEnemy35,recEnemy36};
     // bullets = new Rectangle[]{recBullet1, recBullet2, recBullet3, recBullet4, recBullet5, recBullet6, recBullet7, recBullet8, recBullet9, recBullet10};
    }

}
