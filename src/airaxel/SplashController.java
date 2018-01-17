/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airaxel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author axelv
 */
public class SplashController implements Initializable, ControlledScreen {
  ScreensController myController;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //NextScreen();
        //myController.setScreen(AirAxel.Welcome);
    }    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    public void NextScreen(){
         
            myController.setScreen(AirAxel.Welcome);
    }
}
