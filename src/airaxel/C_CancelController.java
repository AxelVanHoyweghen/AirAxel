package airaxel;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class C_CancelController implements Initializable, ControlledScreen  {
    ScreensController myController; 
    @FXML private AnchorPane CancelPane;
    @FXML private JFXTextField txtBookingNo;
    @FXML private JFXTextField txtFlightNo;
    
    
    
     @FXML
    void CancelBooking(ActionEvent event) {
        float price =0;
        if(txtBookingNo.getText() == null || txtBookingNo.getText().trim().isEmpty() ||txtFlightNo.getText() == null || txtFlightNo.getText().trim().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Give a booking and flight number!");
            alert.showAndWait();
        }else{
            price = AirAxel.CancelCustomerFlight(txtFlightNo.getText(), txtBookingNo.getText());
            if(price !=0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Your booking has been cancelled, you still have to pay: $" + price + " dollar.");
                    alert.showAndWait();
                    txtBookingNo.setText("");
                    txtFlightNo.setText("");
                    myController.setScreen(AirAxel.C_Welcome);
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Something went wrong and we could not find your profile.");
                    alert.showAndWait();
                }
        }
    }
    
//***************************************************************************************************
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         txtBookingNo.setText("");
         txtFlightNo.setText("");
    }    
    
      public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
      
    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
        txtBookingNo.setText("");
    }
    
    @FXML
    private void goToC_Booking(ActionEvent event){ 
       myController.setScreen(AirAxel.C_Booking);
        txtBookingNo.setText("");
    }
    
    @FXML
    private void goToC_Cancel(ActionEvent event){ 
       myController.setScreen(AirAxel.C_Cancel);
        txtBookingNo.setText("");
    }
    
    @FXML
    private void goToC_Itinerary(ActionEvent event){
       myController.setScreen(AirAxel.C_Itinerary);
       txtBookingNo.setText("");
    }
    
    @FXML
    private void goToC_Payment(ActionEvent event){ 
       myController.setScreen(AirAxel.C_Payment);
        txtBookingNo.setText("");
    }
}
