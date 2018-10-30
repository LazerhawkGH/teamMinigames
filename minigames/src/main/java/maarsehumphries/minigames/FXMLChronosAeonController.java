/*
 * Author: Zachary Maarse
 * Date: Oct 15, 2018
 * Purpose: Allows the user to play a Space Invaders themed game
 */
package maarsehumphries.minigames;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static maarsehumphries.minigames.MainApp.*;
import javafx.stage.Stage;


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
    
    @FXML private Label lblPoints;
    @FXML private Label lblLives;
    
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
    Timeline moveBullet = new Timeline(new KeyFrame(Duration.millis(15), ae -> bulletMove()));
    Timeline movementEnemies = new Timeline(new KeyFrame(Duration.millis(90), ae -> moveEnemies()));

    private Boolean upYes = false;    /////////////////////////////////////////////////////////////////
    private Boolean downYes = false;  // Booleans to determine what direction the user is moving in  //
    private Boolean leftYes = false;  //                                                             //
    private Boolean rightYes = false; /////////////////////////////////////////////////////////////////
    
    private Boolean userMoving = false; // Used later to prevent timers from being stacked on top of each other
    private Boolean bulletCreated = false; // Makes sure only one instance of the bullet moving is created
    
    private int dir = 2; // Initial direction -> right
    private int enemySpeed = 0; // Current speed of the enemies, altered by 'wallsHit'
    private int wallsHit = 0; // Used in an if statement to check when to speed up the enemies
    private int lives = 3;    // Number of lives the user has
    
    private int shipsHit = 0; // Only for the current game, reset after every 'round'
                              // Used to check if the player has won
    
    private int scoreMult = 1; // Two variables used for the store bought upgrades
    private int upgrades = 0;  // By default will be a value that will not affect the game
    
    MediaPlayer player; // Created here so garbage collector doesn't get rid of it
    
    Rectangle[] z;       // Creates two empty arrays, will be filled during initialization
    Rectangle[] enemies; // 
    
    //Handles collision between an ImageView and a rectangle
    public boolean collision(ImageView block1, Rectangle block2) {
        //returns true if the areas intersect, false if they dont
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }
    
    //Handles the collision between two rectangles
    public boolean cEnemies(Rectangle block1, Rectangle block2){
        return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    private boolean collisionLoop() {
        for (Rectangle i : z) {          // Loops through the bounds of the play area, sets each rectangle to 'i' as it goes through
            if (collision(imgUser, i)) { // Checks for collision between the user and any of the walls
                return true;               
            }
        }
        return false;
    }

       
    private boolean collisionBullets(){
        for (Rectangle i:enemies){       // Goes through all of the enemies, sets 'i' as each one as it goes through
            if (cEnemies(recBullet, i)){ // Checks for a collision between the bullet and any of the enemies
                i.setLayoutX(-1000);     // Enemies that are hit are sent off the scene, where they can't interfere
                i.setLayoutY(-1000);     // 
                shipsHit+=1; // Used later to check if the user has hit all 36 ships
                setPoints(getPoints() + (10 * scoreMult)); // Uses the global variable so that points may be used in other scenes 
                lblPoints.setText("" + getPoints()); // Updates after every hit
                return true;
            }else if (cEnemies(recBullet,rectBoundsTop)){ // If the user misses all of the enemies, prevents the bullet from
                bulletCreated=false;                      // going off screen
                moveBullet.stop();
                recBullet.setTranslateX(576); // Moves the bullet to a different location after hitting a wall
                recBullet.setTranslateY(89);
            }
        }
        return false;    
    }
    
       
    private void bulletMove(){  
        if (collisionBullets()){  // If the bullet hits an enemy, the bullet is moved, and can then be fired again
           bulletCreated=false;
           moveBullet.stop();
           recBullet.setTranslateX(10);
           recBullet.setTranslateY(680);          
        }
        recBullet.setTranslateY(recBullet.getTranslateY() - 6 - upgrades); // Otherwise, the bullet continues moving upwards
    }
    
    //Handles all code for the resetting of the game, occurs after winning or losing
    private void reset(){
        for (Rectangle e:enemies){
            e.setTranslateX(0);
            e.setTranslateY(0);
            e.setVisible(true);
        }
        //Manually moving each enemy to the correct spot
        recEnemy1.setLayoutX(110);recEnemy1.setLayoutY(123);recEnemy2.setLayoutX(160);recEnemy2.setLayoutY(123);
        recEnemy3.setLayoutX(210);recEnemy3.setLayoutY(123);recEnemy4.setLayoutX(260);recEnemy4.setLayoutY(123);
        recEnemy5.setLayoutX(310);recEnemy5.setLayoutY(123);recEnemy6.setLayoutX(360);recEnemy6.setLayoutY(123);
        recEnemy7.setLayoutX(410);recEnemy7.setLayoutY(123);recEnemy8.setLayoutX(460);recEnemy8.setLayoutY(123);
        recEnemy9.setLayoutX(60);recEnemy9.setLayoutY(166);recEnemy10.setLayoutX(110);recEnemy10.setLayoutY(166);
        recEnemy11.setLayoutX(160);recEnemy11.setLayoutY(166);recEnemy12.setLayoutX(210);recEnemy12.setLayoutY(166);
        recEnemy13.setLayoutX(260);recEnemy13.setLayoutY(166);recEnemy14.setLayoutX(310);recEnemy14.setLayoutY(166);
        recEnemy15.setLayoutX(360);recEnemy15.setLayoutY(166);recEnemy16.setLayoutX(410);recEnemy16.setLayoutY(166);
        recEnemy17.setLayoutX(460);recEnemy17.setLayoutY(166);recEnemy18.setLayoutX(510);recEnemy18.setLayoutY(166);
        recEnemy19.setLayoutX(60);recEnemy19.setLayoutY(209);recEnemy20.setLayoutX(110);recEnemy20.setLayoutY(209);
        recEnemy21.setLayoutX(160);recEnemy21.setLayoutY(209);recEnemy22.setLayoutX(210);recEnemy22.setLayoutY(209);
        recEnemy23.setLayoutX(260);recEnemy23.setLayoutY(209);recEnemy24.setLayoutX(310);recEnemy24.setLayoutY(209);
        recEnemy25.setLayoutX(360);recEnemy25.setLayoutY(209);recEnemy26.setLayoutX(410);recEnemy26.setLayoutY(209);
        recEnemy27.setLayoutX(460);recEnemy27.setLayoutY(209);recEnemy28.setLayoutX(510);recEnemy28.setLayoutY(209);
        recEnemy29.setLayoutX(110);recEnemy29.setLayoutY(252);recEnemy30.setLayoutX(160);recEnemy30.setLayoutY(252);
        recEnemy31.setLayoutX(210);recEnemy31.setLayoutY(252);recEnemy32.setLayoutX(260);recEnemy32.setLayoutY(252);
        recEnemy33.setLayoutX(310);recEnemy33.setLayoutY(252);recEnemy34.setLayoutX(360);recEnemy34.setLayoutY(252);
        recEnemy35.setLayoutX(410);recEnemy35.setLayoutY(252);recEnemy36.setLayoutX(460);recEnemy36.setLayoutY(252);
         
        recBullet.setTranslateX(10);
        recBullet.setTranslateY(680);
        imgUser.setTranslateX(278);
        imgUser.setTranslateY(535);
        shipsHit=0;
        enemySpeed=0;
    }
    
    private void move() {
        if (shipsHit==36){  // If the user has hit all of the enemies (10x36=360) or (20x36=720), then a pop-up notifies the user
            reset();                      // of their victory
            movement.stop();        // Stops all of the timers to prevent any unnecessary movement
            movementEnemies.stop(); //
            moveBullet.stop();      //
            userMoving = false;
            Alert alert = new Alert(AlertType.INFORMATION); // Displays the alert box
            alert.setTitle("Congratulations!");
            alert.setHeaderText(null);
            alert.setContentText("You have defeated all of the ships!");
            Platform.runLater(alert::showAndWait);
        }
        
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
    
    private void moveEnemies(){
       
        for (Rectangle e:enemies){            // Loops through each enemy to move each one, 
            if (cEnemies(e,rectBoundsRight)){ // If one of the enemies hits the wall on the right
                dir = 1;                      // The direction of the enemies is changed to the opposite direction
                wallsHit+=1;                
                for (Rectangle h:enemies){    // Loops through again to move the enemies
                    h.setTranslateY(h.getTranslateY() + 8);
                    h.setTranslateX(h.getTranslateX() - 5);
                }
                break;
            }else if (cEnemies(e,rectBoundsLeft)){ // If one of the enemies hits the wall on the left
                dir = 2;                           // The direction of the enemies is changed to the opposite direction
                wallsHit+=1;
                for (Rectangle z:enemies){         // Loops through again to move the enemies
                    z.setTranslateY(z.getTranslateY() + 8);
                    z.setTranslateX(z.getTranslateX() + 5);
                }
                break;
            } else if ((cEnemies(e,rectBoundsBottom)) || (collision(imgUser, e))){  // If any enemy hits the user, or makes it past the user,
                reset();                                                            // The user is notified, and the game is reset
                movement.stop();        //Stops all of the timers to prevent any unnecessary movement
                movementEnemies.stop(); //
                moveBullet.stop();      //
                userMoving = false;
                bulletCreated = false;
                lives-=1;
                lblLives.setText(""+ lives);
                Alert alert = new Alert(AlertType.INFORMATION); 
                alert.setTitle("You've failed...");
                alert.setHeaderText(null);
                alert.setContentText("You have failed your duty,");
                Platform.runLater(alert::showAndWait); // Displays the alert box, must be in this format if used in a timer, as this one is
            }
            if (wallsHit==4){ // After hitting the wall four times, the enemies speed up
                wallsHit=0;
                enemySpeed+=1;
            }
            if (lives==0){
                Alert alert = new Alert(AlertType.INFORMATION); 
                alert.setTitle("We're disappointed");
                alert.setHeaderText(null);
                alert.setContentText("That was your last draw, pack your bags");
                Platform.runLater(alert::showAndWait); // Displays the alert box, must be in this format if used in a timer, as this one is
                try {
                    sceneChange();
                } catch (IOException ex) {  // Has to throw IOException because of the sceneChange method, and how it operates
                    ex.printStackTrace();
                }
            }
            if (dir==1){ // Moves the enemies left
                e.setTranslateX(e.getTranslateX() - 5 - enemySpeed);
            }
            if (dir==2){ // Moves the enemies right
                e.setTranslateX(e.getTranslateX() + 5 + enemySpeed);
            }
        }
    }
    
    @FXML 
    private void back(ActionEvent e){
        try {
            sceneChange();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void sceneChange() throws IOException{
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLMainMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) imgUser.getScene().getWindow();
        stage.hide();
        stage.setScene(home_page_scene);
        stage.setTitle("Main Menu");
        stage.show();
        home_page_scene.getRoot().requestFocus();
        stage.setOnCloseRequest(e -> System.exit(0));
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
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializes both arrays, will be used later for movement/collision
        z = new Rectangle[]{rectBoundsTop, rectBoundsBottom, rectBoundsLeft, rectBoundsRight};
        enemies = new Rectangle[]{recEnemy1,recEnemy2,recEnemy3,recEnemy4,recEnemy5,recEnemy6,recEnemy7,recEnemy8,recEnemy9,recEnemy10,recEnemy11,recEnemy12,recEnemy13,recEnemy14,recEnemy15,recEnemy16,recEnemy17,recEnemy18,recEnemy19,recEnemy20,recEnemy21,recEnemy22,recEnemy23,recEnemy24,recEnemy25,recEnemy26,recEnemy27,recEnemy28,recEnemy29,recEnemy30,recEnemy31,recEnemy32,recEnemy33,recEnemy34,recEnemy35,recEnemy36};
       
        setPoints(getPoints());   // Obtains the amount of points the user has, sets a label box to that amount
        lblPoints.setText("" + getPoints());
        
        // Variables initialized with default values
        shipsHit=0;
        bulletCreated = false;
        userMoving = false;
        
        player = new MediaPlayer((new Media(getClass().getResource("/Rasputin 8Bit.mp3").toString())));
        player.play();
        
        
        // Handles the global variables, checks for either one being bought
        setBulletUpgrade(getBulletUpgrade());
        setScoreUpgrade(getScoreUpgrade());
        
        if (boughtBullet){ // If the bullet upgrade has been bought, make the necessary changes
            upgrades = 2;
        }
        
        if (boughtScore){
            scoreMult = 2;
        }
    }

}
