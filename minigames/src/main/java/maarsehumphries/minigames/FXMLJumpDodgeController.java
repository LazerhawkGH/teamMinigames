/*
Shayne Humphries

 */
package maarsehumphries.minigames;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Shayne
 */
public class FXMLJumpDodgeController implements Initializable {

    @FXML private ImageView imgB;
  @FXML private ImageView imgG;
  @FXML private ImageView imgO;
  @FXML private Label lblD;
  @FXML private ImageView imgT;
  @FXML private Button btnGame;
  int s = 0;
  int m = 20;
  int n = 4;
  int e = 0;
            
    Image G= new Image(getClass().getResource("/Stop sign.png").toString());
    Image B = new Image(getClass().getResource("/link attack.png").toString());
    Image O = new Image(getClass().getResource("/rock.png").toString());
    Timeline jump = new Timeline(new KeyFrame(Duration.millis(20), ae -> up()));
    Timeline obstacles = new Timeline(new KeyFrame(Duration.millis(10), ae -> move()));
    Timeline time = new Timeline(new KeyFrame(Duration.millis(1000), ae -> tim()));
    
    private boolean c(ImageView block1, ImageView block2) {
    return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
}
       
   public void keyPressed(KeyEvent event){
        if (event.getCode()== KeyCode.SPACE){              
           if(c(imgB,imgG)){
               imgB.setTranslateY(imgB.getTranslateY()-5);
               jump.setCycleCount(Timeline.INDEFINITE);
               m = 20;
               jump.play();
        }
        }
}
    
   @FXML public void Begin(ActionEvent event){
    time.setCycleCount(Timeline.INDEFINITE);
    time.play();
    obstacles.setCycleCount(Timeline.INDEFINITE);
    obstacles.play();       //starts various timers
    btnGame.setDisable(true);
    n = 4;
    e = 0;
    }
    
        public void up() {
        imgB.setTranslateY(imgB.getTranslateY()-m); // moves the user upwards
        m--; // decreases the amount the user wil go up by, until eventually going down again.
        if(c(imgB,imgG)){
        jump.stop(); //stops timer if user collides with ground
        }
    }
        
      public void move() {
        imgO.setTranslateX(imgO.getTranslateX()-n);
        if (c(imgO,imgB)){
        obstacles.stop();
        time.stop();
        imgO.setTranslateX(1);
        btnGame.setDisable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //shows alert to tell of game over
        alert.setTitle("Game Over!");
        alert.setHeaderText(null); 
        alert.setContentText("Your Score was "+s+"!");
        alert.show();
        s = 0;  
        lblD.setText("Time: "+ s);}
        if(c(imgT,imgO)){
        imgO.setTranslateX(300);
        e++;
        if (e == 8 && n < 9 ){
        n++;
        e = 0;
        }
        }}
                
      public void tim() {
      lblD.setText("Time: "+ s++); //updates time of survival
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgO.setImage(O);
        imgB.setImage(B);
        imgG.setImage(G);
        imgT.setTranslateX(-100);
    }    
}
