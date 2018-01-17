package airaxel;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class E_WelcomeController implements Initializable, ControlledScreen {
   ScreensController myController;
   
   public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    void goToE_AddAirport(ActionEvent event) {
        myController.setScreen(AirAxel.E_AddAirport);
    }

    @FXML
    void goToE_AddFlight(ActionEvent event) {
        myController.setScreen(AirAxel.E_AddFlight);
    }

    @FXML
    void goToE_DeleteFlight(ActionEvent event) {
        myController.setScreen(AirAxel.E_DeleteFlight);
    }

    @FXML
    void goToE_FlightInfo(ActionEvent event) {
        myController.setScreen(AirAxel.E_FlightInfo);
    }

    @FXML
    void goToE_UpdateFlight(ActionEvent event) {
        myController.setScreen(AirAxel.E_UpdateFlight);
    }
    @FXML
    void goToE_LiveInfo(ActionEvent event) {
        myController.setScreen(AirAxel.E_LiveInfo);
    }
    
    
     
//******************************************************************************        
    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
       
    }
    
    @FXML
    void goToE_home(ActionEvent event) {
       myController.setScreen(AirAxel.E_Welcome);
    }
  
    private void EmptyFields(){
    }
    
}
