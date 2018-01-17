package airaxel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;


public class E_DeleteFlightController implements Initializable, ControlledScreen {
   ScreensController myController;
   @FXML private JFXTextField txtFlightNumber;
  
    @Override
   public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFlightNumber.setText("");
    }    
    

    @FXML
    void CancelFlight(ActionEvent event) {
        boolean Success =false;
        if(txtFlightNumber.getText() == null || txtFlightNumber.getText().trim().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Give a flight number!");
            alert.showAndWait();
        }else{
            Success = AirAxel.CancelFlight(txtFlightNumber.getText());
            if(Success){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("The flight has been cancelled");
                    alert.showAndWait();
                    txtFlightNumber.setText("");
                     myController.setScreen(AirAxel.E_Welcome);
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Something went wrong and we could not find the flight.");
                    alert.showAndWait();
                }
        }
    }
     
        
    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
    }
    
    @FXML
    void goToE_home(ActionEvent event) {
           myController.setScreen(AirAxel.E_Welcome);
    }
    
}
