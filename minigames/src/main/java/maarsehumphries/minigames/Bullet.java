/*
 * Author: Zachary Maarse
 * Date: Oct 18, 2018
 * Purpose: 
 */
package maarsehumphries.minigames;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author zacharym44
 */
public class Bullet {
    private int xCoord;
    private int yCoord;
    private int height;
    private int width;
    private AnchorPane root;
    private Rectangle rec;
    
    Bullet(int x, int y, AnchorPane r){
        xCoord = x;
        yCoord = y;
        root = r;
        rec = new Rectangle(x,y,7,7); // Creates the custom object on the coordinates specified in FXMLChronosAeonController.java
    }
    
    public void move(int y) {
        rec.setTranslateY(rec.getTranslateY() + y);
    }
}
