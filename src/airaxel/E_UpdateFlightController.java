package airaxel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class E_UpdateFlightController implements Initializable, ControlledScreen {
   ScreensController myController;
   private ObservableList<EmployeeObject> EList;
    @FXML private AnchorPane FirstPane;
    @FXML private AnchorPane UpdatePane;
    @FXML private JFXTextField txtFlightNumber;
    @FXML private JFXTextField txtDelayStart;
    @FXML private JFXTextField txtDelayEnd;
    @FXML private JFXTextField txtPrice;
    @FXML private JFXTextField txtEmployeeID;
    @FXML private TableView<EmployeeObject> TableEmployees;
    @FXML private TableColumn<EmployeeObject, String> EMID;
    @FXML private TableColumn<EmployeeObject, String> EMName;
    @FXML private TableColumn<EmployeeObject, String> EMFunction;
    private static String flightnumber;
    private String[] FlightInfo;
    
    @FXML
    void FindFlight(ActionEvent event) {
            FirstPane.setVisible(false);
            UpdatePane.setVisible(true);
            flightnumber = txtFlightNumber.getText();
            FlightInfo =AirAxel.InfoFlightsUpdate(flightnumber);
            txtDelayStart.setText(FlightInfo[1]);
            txtDelayEnd.setText(FlightInfo[2]);
            txtPrice.setText(FlightInfo[3]);
            EList= AirAxel.LookUpEmployeesOnFlight(flightnumber);
            EMID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            EMName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            EMFunction.setCellValueFactory(new PropertyValueFactory<>("Function"));
            
            TableEmployees.setItems(null);
            TableEmployees.setItems(EList);
            
    }
    @FXML
    void UpdateFlight(ActionEvent event) {
         boolean FieldEmpty = false;
           if(txtDelayStart.getText().isEmpty()){FieldEmpty = true;};
           if(txtDelayEnd.getText().isEmpty()){FieldEmpty = true;};
           if(txtPrice.getText().isEmpty()){FieldEmpty = true;}; 
            if(!FieldEmpty){
               boolean success = AirAxel.UpdateFlight(flightnumber,Integer.parseInt(txtDelayStart.getText()), Integer.parseInt(txtDelayEnd.getText()) ,txtPrice.getText());
                FlightInfo =AirAxel.InfoFlightsUpdate(flightnumber);
                txtDelayStart.setText(FlightInfo[1]);
                txtDelayEnd.setText(FlightInfo[2]);
                txtPrice.setText(FlightInfo[3]);
           if(!success){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The airport could not be added at this moment, make sure the airport doesnt exist already!");
                alert.showAndWait();
           }
        }else{    
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Fill in all fields!");
                alert.showAndWait();
        }
    }
    @FXML
    void AddEmployee(ActionEvent event) {
           AirAxel.ADDEmployeeFromFlight(flightnumber, txtEmployeeID.getText());
           EList= AirAxel.LookUpEmployeesOnFlight(flightnumber);
            EMID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            EMName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            EMFunction.setCellValueFactory(new PropertyValueFactory<>("Function"));
            
            TableEmployees.setItems(null);
            TableEmployees.setItems(EList);
    }
    @FXML
    void DeleteEmployee(ActionEvent event) {
           AirAxel.DeleteEmployeeFromFlight(flightnumber, txtEmployeeID.getText());
           EList= AirAxel.LookUpEmployeesOnFlight(flightnumber);
            EMID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            EMName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            EMFunction.setCellValueFactory(new PropertyValueFactory<>("Function"));
            
            TableEmployees.setItems(null);
            TableEmployees.setItems(EList);
    }

    
    
   public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmptyFields();
    }    
    
    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
         TableEmployees.setItems(null);
       EmptyFields();
    }
    
    @FXML
    void goToE_home(ActionEvent event) {
           myController.setScreen(AirAxel.E_Welcome);
           TableEmployees.setItems(null);
           EmptyFields();
    }
  
    private void EmptyFields(){
        FirstPane.setVisible(true);
        UpdatePane.setVisible(false);
        txtFlightNumber.setText("");
    txtDelayStart.setText("");
    txtDelayEnd.setText("");
    txtPrice.setText("");
    txtEmployeeID.setText("");
        
    }
    
}