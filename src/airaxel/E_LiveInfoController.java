package airaxel;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class E_LiveInfoController implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML private JFXComboBox<String> cboAirport;
    @FXML private TableView<LiveInfo> tblFlights;
    @FXML private TableColumn<LiveInfo, String> clmInOut;
    @FXML private TableColumn<LiveInfo, String> clmFlightNo;
    @FXML private TableColumn<LiveInfo, String> clmDeparture;
    @FXML private TableColumn<LiveInfo, String> clmArrival;
    @FXML private TableColumn<LiveInfo, String> clmFromTo;
    @FXML private TableColumn<LiveInfo, String> clmDelayed;
    
    private ObservableList<LiveInfo> IList;
    
    
    
    @FXML
    void RefreshTable(ActionEvent event) {   
        
        tblFlights.setItems(null);
        IList = null;
        IList = AirAxel.LiveInfoFlights(cboAirport.getValue());
   
            clmInOut.setCellValueFactory(new PropertyValueFactory<>("InOut"));
            clmFlightNo.setCellValueFactory(new PropertyValueFactory<>("FlightNo"));
            clmDeparture.setCellValueFactory(new PropertyValueFactory<>("Departure"));
            clmArrival.setCellValueFactory(new PropertyValueFactory<>("Arrival"));
            clmFromTo.setCellValueFactory(new PropertyValueFactory<>("FromTo"));
            clmDelayed.setCellValueFactory(new PropertyValueFactory<>("Delayed"));
            
            tblFlights.setItems(null);
            tblFlights.setItems(IList);
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
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
    
    @FXML
    private void SetCBo(){
        String[] From = AirAxel.FromFlightCbo();
        String[] TO = AirAxel.ToFlightCbo();
        cboAirport.getItems().clear();
        
        for(int i=0;i<From.length;i++)
        {
            if(From[i] != null){    
            cboAirport.getItems().add(From[i]);
            }
        }
        for(int i=0;i<TO.length;i++)
        {
            if(TO[i] != null){
                if(!cboAirport.getItems().contains(TO[i])){
                    cboAirport.getItems().add(TO[i]);
                }
            }
        }
    }
}
