package airaxel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class E_AddFlightController implements Initializable, ControlledScreen {
   ScreensController myController;
    @FXML private JFXTextField txtOrigin;
    @FXML private JFXTextField txtDestination;
    @FXML private JFXTextField txtDistance;
    @FXML private JFXComboBox<String> cboAircraft;
    @FXML private JFXTextField txtPrice;
    @FXML private JFXDatePicker DateDepartureDate;
    @FXML private JFXTextField txtDepartureTime;
    @FXML private JFXDatePicker DateArrivalDate;
    @FXML private JFXTextField txtArrivalTime;
    @FXML private JFXTextField txtTotalTime;

    
    @FXML
    void AddFlight(ActionEvent event) {
         boolean FieldEmpty = false;
            if(txtOrigin.getText().isEmpty()){FieldEmpty = true;};
            if(txtDestination.getText().isEmpty()){FieldEmpty = true;};
            if(txtDistance.getText().isEmpty()){FieldEmpty = true;};
            if(cboAircraft.getValue().isEmpty()){FieldEmpty = true;};
            if(txtPrice.getText().isEmpty()){FieldEmpty = true;};
            if(txtDepartureTime.getText().isEmpty()){FieldEmpty = true;};
            if(txtArrivalTime.getText().isEmpty()){FieldEmpty = true;};
            if(txtTotalTime.getText().isEmpty()){FieldEmpty = true;};
            if(!FieldEmpty){
                DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
                String FormatDep = DateDepartureDate.getValue().format(format);
                String FormatArrival = DateArrivalDate.getValue().format(format);
                boolean success = AirAxel.AddFlight(txtOrigin.getText(),txtDestination.getText(),txtDistance.getText(),cboAircraft.getValue(), txtPrice.getText(), FormatDep, txtDepartureTime.getText(),FormatArrival,txtArrivalTime.getText(),txtTotalTime.getText());
           if(success){ 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The flight is added.");
                alert.showAndWait();
                EmptyFields();
                myController.setScreen(AirAxel.E_Welcome);
           }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The flight could not be added at this moment.");
                alert.showAndWait();
           }
        }else{    
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Fill in all fields!");
                alert.showAndWait();
    }
    }
    
    
   public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
     void setCboAircraft(){
        String[] CBO = AirAxel.GetAircrafts();
        cboAircraft.getItems().clear();
        for(int i=0;i<CBO.length;i++)
        {
            if(CBO[i] != null){
            cboAircraft.getItems().add(CBO[i]);
            }
        }
     }
        
    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
       EmptyFields();
    }
    
    @FXML
    void goToE_home(ActionEvent event) {
           myController.setScreen(AirAxel.E_Welcome);
           EmptyFields();
    }
  
    private void EmptyFields(){
        txtOrigin.setText("");
            txtDestination.setText("");
            txtDistance.setText("");
            cboAircraft.setValue("");
            txtPrice.setText("");
            txtDepartureTime.setText("");
            txtArrivalTime.setText("");
            txtTotalTime.setText("");
    }
    
}