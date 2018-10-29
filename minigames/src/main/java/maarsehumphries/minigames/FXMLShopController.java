/*
 * Name: Zachary Maarse
 * Date Started: Oct 15, 2018
 * Date Finished: Oct 31, 2018
 * Purpose: Allows the user to user to use their earned points to buy various things
 */
package maarsehumphries.minigames;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import static maarsehumphries.minigames.MainApp.*;

/**
 *
 * @author zacha
 */
public class FXMLShopController implements Initializable{

    @FXML private Label lblPoints;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPoints(getPoints());   // Obtains the amount of points the user has, sets a label box to that amount
        lblPoints.setText("" + getPoints());
    }
}
