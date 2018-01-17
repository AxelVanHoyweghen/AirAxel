
package airaxel;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class E_AddAirportController implements Initializable, ControlledScreen {
   ScreensController myController;
   @FXML private JFXTextField txtAirportID;
    @FXML private JFXTextField txtAirportName;
    @FXML private JFXTextField txtCity;
    @FXML private JFXTextField txtCountry;
    @FXML private JFXTextField txtLatDec;
    @FXML private JFXTextField txtLongDec;
    @FXML private JFXTextField txtAltitude;
    @FXML private JFXTextField txtUTC;


   public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
        void AddAirport(ActionEvent event) {
             boolean FieldEmpty = false;
            if(txtAirportID.getText().isEmpty()){FieldEmpty = true;};
            if(txtAirportName.getText().isEmpty()){FieldEmpty = true;};
            if(txtCity.getText().isEmpty()){FieldEmpty = true;};
            if(txtCountry.getText().isEmpty()){FieldEmpty = true;};
            if(txtAltitude.getText().isEmpty()){FieldEmpty = true;};
            if(txtUTC.getText().isEmpty()){FieldEmpty = true;};
            
            
            if(!FieldEmpty){
                boolean success = AirAxel.AddAirport(txtAirportID.getText(), txtAirportName.getText(),txtCity.getText() ,txtCountry.getText(), txtLatDec.getText(), txtLongDec.getText(), txtAltitude.getText(),txtUTC.getText());
           if(success){ 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The airport is added.");
                alert.showAndWait();
                emptyfields();
                myController.setScreen(AirAxel.E_Welcome);
           }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The airport could not be added at this moment, make sure the airport doesnt exist already!");
                alert.showAndWait();
           }
        }else{    
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Fill in all fields! Longtitude and lattitude are not mandatory fields");
                alert.showAndWait();
    }
    }
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       emptyfields();
    }    

     
        
    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
       
    }
    
    @FXML
    void goToE_home(ActionEvent event) {
           myController.setScreen(AirAxel.E_Welcome);
    }
 
    void emptyfields(){
         txtAirportID.setText("");
        txtAirportName.setText("");
        txtCountry.setText("");
        txtCity.setText("");
        txtLatDec.setText("");
        txtLongDec.setText("");
        txtAltitude.setText("");
        txtUTC.setText("");
    }
}
