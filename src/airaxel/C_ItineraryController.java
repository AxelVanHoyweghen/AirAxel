package airaxel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class C_ItineraryController implements Initializable, ControlledScreen  {
ScreensController myController; 

    @FXML private AnchorPane Itinerary;
    @FXML private JFXTextArea txtItinerary;
    @FXML private AnchorPane searchPanel;
    @FXML private JFXTextField txtBookingNo;
    @FXML private JFXButton btnView;

   @FXML
    void View(ActionEvent event) {
        String IT = null;
       if(txtBookingNo.getText() == null || txtBookingNo.getText().trim().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Give a booking number!");
            alert.showAndWait();
        }else{
            IT = AirAxel.FlightItineraryBookingNo(txtBookingNo.getText());
            if(!IT.isEmpty()){
                txtItinerary.setText(IT);
                Itinerary.setVisible(true);
                searchPanel.setVisible(false);
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Something went wrong and we could not find your booking.");
                    alert.showAndWait();
                }
        }
    }
    
    @FXML
    void Done(ActionEvent event) {
        EmptyFields();
        myController.setScreen(AirAxel.C_Welcome);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmptyFields();
    }    
    
      public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
        
        EmptyFields();
    }
    
    @FXML
    private void goToC_Booking(ActionEvent event){ 
       myController.setScreen(AirAxel.C_Booking);
       EmptyFields();
    }
    
    @FXML
    private void goToC_Cancel(ActionEvent event){ 
       myController.setScreen(AirAxel.C_Cancel);
       EmptyFields();
    }
    
    @FXML
    private void goToC_Itinerary(ActionEvent event){
       myController.setScreen(AirAxel.C_Itinerary);
       EmptyFields();
    }
    
    @FXML
    private void goToC_Payment(ActionEvent event){ 
       myController.setScreen(AirAxel.C_Payment);
       EmptyFields();
    }
    
    private void EmptyFields(){
        Itinerary.setVisible(false);
        searchPanel.setVisible(true);
        txtBookingNo.setText("");
        txtItinerary.setText("");
    }
}
