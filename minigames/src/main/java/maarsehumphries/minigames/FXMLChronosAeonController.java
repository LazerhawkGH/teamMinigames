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
    Timeline moveBullet = new Timeline(new KeyFrame(Duration.millis(25), ae -> bulletMove()));
    Timeline movementEnemies = new Timeline(new KeyFrame(Duration.millis(50), ae -> moveEnemies()));

    private Boolean upYes = false;    /////////////////////////////////////////////////////////////////
    private Boolean downYes = false;  // Booleans to determine what direction the user is moving in  //
    private Boolean leftYes = false;  //                                                             //
    private Boolean rightYes = false; /////////////////////////////////////////////////////////////////
    
    private Boolean gameStarted = false; 
    private Boolean userMoving = false;
    private Boolean bulletCreated = false;
    
    Rectangle[] z;       // Creates two empty arrays, will be filled during initialization
    Rectangle[] enemies; // 
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
        for (Rectangle i : z) {   // Loops through the bounds of the play area, sets each rectangle to 'i' as it goes through
            if (collision(imgUser, i)) { // Checks for collision between the user and any of the walls
                return true;               
            }
        }
        return false;
    }

    private boolean collisionBullets(){
        for (Rectangle i:enemies){ // Goes through all of the enemies, sets 'i' as each one as it goes through
            if (cEnemies(recBullet, i)){ // Checks for a collision between the bullet and any of the enemies
                i.setLayoutX(-25);
                i.setLayoutY(-25);
                return true;
            }else if (cEnemies(recBullet,rectBoundsTop)){ // If the user misses all of the enemies, prevents the bullet from
                bulletCreated=false;                      // going off screen
                moveBullet.stop();
                recBullet.setTranslateX(576);
                recBullet.setTranslateY(89);
            }
        }
        return false;    
    }
    
    private void bulletMove(){  
        if (collisionBullets()){
           bulletCreated=false;
           moveBullet.stop();
           recBullet.setTranslateX(576);
           recBullet.setTranslateY(89);          
        }
        recBullet.setTranslateY(recBullet.getTranslateY() - 2);
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

    private int dir = 2; // Initial direction -> right
     
    private void moveEnemies() {
       
        for (Rectangle e:enemies){ // Loops through each enemy to move each one, 
            if (cEnemies(e,rectBoundsRight)){ // If one of the enemies hits the wall on the right
                dir = 1; // The direction of the enemies is changed to the opposite direction
                
                e.setTranslateY(e.getTranslateY() + 5);
            }else if (cEnemies(e,rectBoundsLeft)){ // If one of the enemies hits the wall on the left
                dir = 2;                           // The direction of the enemies is changed to the opposite direction
                e.setTranslateY(e.getTranslateY() + 5);
            }
            
            if (dir==1){       // Moves the enemies left
                e.setTranslateX(e.getTranslateX() - 5);
            }else if (dir==2){ // Moves the enemies right
                e.setTranslateX(e.getTranslateX() + 5);
            }
        }
    }

    
    @FXML
    private void moveUser(KeyEvent e) {

        // Handles the movement of the user
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
                    if (!bulletCreated){ // Moves the bullet to the user, then starts the bullet timer
                        bulletCreated=true;
                        recBullet.setTranslateX(imgUser.getTranslateX() + 13);
                        recBullet.setTranslateY(imgUser.getTranslateY() - 10);
                        moveBullet.setCycleCount(Timeline.INDEFINITE);
                        moveBullet.play();
                        break;
                    }
                default:
                    break;
            }
        }
        if (!userMoving) { // Prevents the timers from stacking, only 1 instance of each timer is allowed 
            userMoving = true;
            movement.setCycleCount(Timeline.INDEFINITE);
            movement.play();
            movementEnemies.setCycleCount(Timeline.INDEFINITE);
            movementEnemies.play();
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
        // Initializes both arrays, will be used later for movement/collision
        z = new Rectangle[]{rectBoundsTop, rectBoundsBottom, rectBoundsLeft, rectBoundsRight};
        enemies = new Rectangle[]{recEnemy1,recEnemy2,recEnemy3,recEnemy4,recEnemy5,recEnemy6,recEnemy7,recEnemy8,recEnemy9,recEnemy10,recEnemy11,recEnemy12,recEnemy13,recEnemy14,recEnemy15,recEnemy16,recEnemy17,recEnemy18,recEnemy19,recEnemy20,recEnemy21,recEnemy22,recEnemy23,recEnemy24,recEnemy25,recEnemy26,recEnemy27,recEnemy28,recEnemy29,recEnemy30,recEnemy31,recEnemy32,recEnemy33,recEnemy34,recEnemy35,recEnemy36};
    }

}
