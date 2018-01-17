/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airaxel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author axelv
 */
public class E_FlightInfoController implements Initializable, ControlledScreen  {
ScreensController myController;

private ObservableList<CustomerObject> CList;
private ObservableList<EmployeeObject> EList;
    @FXML private AnchorPane SearchPane;
    @FXML private JFXTextField txtFlightNumber;
    @FXML private AnchorPane DisplayInfoPane;
    @FXML private Label lblTotalSales;
    //Table Customers
    @FXML private TableView<CustomerObject> TableCustomer;
    @FXML private TableColumn<CustomerObject, String> CMName;
    @FXML private TableColumn<CustomerObject, String> CMTC;
    @FXML private TableColumn<CustomerObject, String> CMPhone;
    @FXML private TableColumn<CustomerObject, String> CMMember;
    
    //Table emp
    @FXML private TableView<EmployeeObject> TableEmployees;
    @FXML private TableColumn<EmployeeObject, String> EMID;
    @FXML private TableColumn<EmployeeObject, String> EMName;
    @FXML private TableColumn<EmployeeObject, String> EMFunction;
  
    @FXML
    void FindFlight(ActionEvent event) {
        
        if(txtFlightNumber.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Flight number is empty!");
                alert.showAndWait();
        }else{
        CList = AirAxel.LookUpCustomersOnFlight(txtFlightNumber.getText());
        EList= AirAxel.LookUpEmployeesOnFlight(txtFlightNumber.getText());
        
        float TS = AirAxel.TotalSalesOfFLight(txtFlightNumber.getText());
        if(TS == 0 && CList.isEmpty() && EList.isEmpty()){
            lblTotalSales.setText("This flight has been cancelled or does not contain any customers and employees at this time");
            DisplayInfoPane.setVisible(true);
            SearchPane.setVisible(false);
        }else{
        //put in field
        lblTotalSales.setText("Total sales for flight: $"+ Float.toString(TS) + " dollars.");
            
        //Table Customers
            CMName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            CMTC.setCellValueFactory(new PropertyValueFactory<>("TravelClass"));
            CMPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            CMMember.setCellValueFactory(new PropertyValueFactory<>("Member"));
            
            TableCustomer.setItems(null);
            TableCustomer.setItems(CList);
            
        //Table Employees 
            EMID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            EMName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            EMFunction.setCellValueFactory(new PropertyValueFactory<>("Function"));
            
            TableEmployees.setItems(null);
            TableEmployees.setItems(EList);
            
        DisplayInfoPane.setVisible(true);
        SearchPane.setVisible(false);
        }
        }
    }


   public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DisplayInfoPane.setVisible(false);
        SearchPane.setVisible(true);
    }    
     @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
      emptyFields();
    }
    
    @FXML
    void goToE_home(ActionEvent event) {
           myController.setScreen(AirAxel.E_Welcome);
           emptyFields();
    }
    
    void emptyFields(){
        txtFlightNumber.setText("");
        lblTotalSales.setText("");
        TableCustomer.setItems(null);
        TableEmployees.setItems(null);
        DisplayInfoPane.setVisible(false);
        SearchPane.setVisible(true);
    }
}
