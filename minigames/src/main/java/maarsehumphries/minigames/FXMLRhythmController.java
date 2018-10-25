package maarsehumphries.minigames;

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

public class FXMLRhythmController implements Initializable {
    
    @FXML private Label lblTest;
    @FXML private ImageView imgUser;
    @FXML private ImageView imgU;
    @FXML private Button btnStart;
    @FXML private ImageView imgB;
    
    Timeline approach = new Timeline(new KeyFrame(Duration.millis(15), ae -> move()));
    Timeline stop = new Timeline(new KeyFrame(Duration.millis(500), ae -> top()));
    
    
    int rand = 1;
    int h = 4;
    int s = 0;
    
    private boolean left = false;
    private boolean right = false;
    private boolean down = false;
    private boolean up = false;
    private boolean game = false;
    
     ArrayList<Integer> list = new ArrayList();
    
    public void Begin(KeyEvent event) {
        if(game == false){
        choose(); 
        approach.play();   
        h = 4;
    }}
    
    public void keyPressed(KeyEvent event) {
      if (event.getCode()== KeyCode.LEFT){
      left = true;}
      if (event.getCode()== KeyCode.RIGHT){
      right = true;}
      if (event.getCode()== KeyCode.DOWN){
      down = true;}
      if(event.getCode()==KeyCode.UP){
      up = true;}
    }
    
    public void keyRelease(KeyEvent event) {
      if (event.getCode()== KeyCode.LEFT){
      left = false;}
      if (event.getCode()== KeyCode.RIGHT){
      right = false;}
      if (event.getCode()== KeyCode.DOWN){
      down = false;}
      if(event.getCode()==KeyCode.UP){
      up = false;}   
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        approach.setCycleCount(Timeline.INDEFINITE);
       // imgU.setLayoutY(-100);
        imgB.setTranslateY(40);
    }    

    private void move() { 
           imgU.setTranslateY(imgU.getTranslateY()+h);
           if(c(imgU, imgUser)){
               if(list.get(0)== 1 && left == true){
                   list.removeAll(list);
                choose();
                imgU.setTranslateY(-100);
               }if(list.get(0)== 2 && up == true){
                   list.removeAll(list);
                choose();
                imgU.setTranslateY(-100);
               }if(list.get(0)== 3 && down == true){
                   list.removeAll(list);
                choose();
                imgU.setTranslateY(-100);
               }if(list.get(0)== 4 && right == true){
                   list.removeAll(list);
                choose();
                imgU.setTranslateY(-100);
               }}
               if(c(imgU, imgB)){
                   list.removeAll(list);
                    choose();
                    imgU.setTranslateY(-100);}            
    }

    public void choose() {   
    rand = ThreadLocalRandom.current().nextInt(1,4+1);
    switch (rand){
        case 1:
    imgU.setLayoutX(70);
    list.add(1);
        break;
        case 2:
    imgU.setLayoutX(190);
    list.add(2);
    
        break;
        case 3:
    imgU.setLayoutX(340);
    list.add(3);
    
        break;
        case 4:
    imgU.setLayoutX(510);
    list.add(4);
    
        break;
    }
     //***Get fonts from 1001fonts.com or whatever***
    }
    
    public boolean c(ImageView block1, ImageView block2) {
    return (block1.getBoundsInParent().intersects(block2.getBoundsInParent()));
    }

    private void top() {
       list.removeAll(list);
       choose();
       imgU.setTranslateY(-100);
    }
}
