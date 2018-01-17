package airaxel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class C_PaymentController implements Initializable, ControlledScreen  {
ScreensController myController; 
    @FXML private AnchorPane PaymentPane;
    @FXML private JFXTextField txtBookingNo;
    @FXML private  JFXComboBox<String> cboPaymentMethod;
    @FXML private  JFXTextField txtCardNumber;
    @FXML private  JFXTextField txtNameOnCard;

    @FXML private  JFXTextField txtCVC;
    @FXML private JFXButton btnPay;
    @FXML private Label lblDue;
    @FXML private JFXComboBox<Integer> cboMonth;
    @FXML private JFXComboBox<Integer> cboYear;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmptyFields();
        PaymentPane.setVisible(true);
        cboPaymentMethod.getItems().addAll("Cash", "Debit", "Visa", "American Express", "Discover");
        cboMonth.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11, 12);
        cboYear.getItems().addAll(2017, 2018, 2019 ,2020, 2021,2022,2023,2024,2025,2026,2027,2028,2029,2030);

    }    
    
      public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
      
     @FXML
    void FindPayment(ActionEvent event) {
        if(!txtBookingNo.getText().isEmpty()){
            float Amount = AirAxel.LookUpPayment(txtBookingNo.getText());
                if(Amount == 99999999){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Booking number is not found in the system");
                    alert.showAndWait();
                }else{
                    if(Amount == 999999999){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("You already paid this booking.");
                        alert.showAndWait();
                        EmptyFields();
                        myController.setScreen(AirAxel.C_Welcome);
                    }else{
                    cboPaymentMethod.setVisible(true);
                    lblDue.setText("Amount due: \n" + Float.toString(Amount));
                    lblDue.setVisible(true);
                    txtBookingNo.setDisable(true);
                    }
                }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Booking number is not given");
                alert.showAndWait();
        }
    }  
    
    @FXML
    void DisplayPayment(ActionEvent event) {
        if(cboPaymentMethod.getValue().equals("Cash")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Paying cash will not guarantee your seat, paying with debit or credit card will.");
                alert.showAndWait();
               
                    AirAxel.MakePayment(txtBookingNo.getText(), cboPaymentMethod.getValue(), null, null, null, null);
                    myController.setScreen(AirAxel.C_Welcome);
                 
        }else{
            txtCardNumber.setVisible(true);
            txtNameOnCard.setVisible(true);
            cboYear.setVisible(true);
            cboMonth.setVisible(true);
            txtCVC.setVisible(true);
            btnPay.setVisible(true);
        }
    }
    
   

    @FXML
    void PayDebitCredit(ActionEvent event) {
        boolean FieldEmpty = false;
        DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
        if(txtCardNumber.getText().isEmpty()){FieldEmpty = true;};
        if(txtNameOnCard.getText().isEmpty()){FieldEmpty = true;};
        if(txtCVC.getText().length() > 3){FieldEmpty = true;}; 
        if(txtCVC.getText().isEmpty()){FieldEmpty = true;};

        String Date = cboYear.getValue() + "-" + cboMonth.getValue() +"-01";

        if(!FieldEmpty){
           boolean success = AirAxel.MakePayment(txtBookingNo.getText(), cboPaymentMethod.getValue(), txtCardNumber.getText(), txtNameOnCard.getText(), Date, txtCVC.getText());
           if(success){ 
                EmptyFields();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Your payment is registered.");
                alert.showAndWait();
                
                myController.setScreen(AirAxel.C_Welcome);
           }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The payment could not be registered at this moment");
                alert.showAndWait();
           }
        }else{    
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Fill in all fields!");
                alert.showAndWait();
    }
         
        
    }
      
      
     
//********************************************************************************************************************  
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
    lblDue.setText("");
    txtBookingNo.setText("");
    txtCardNumber.setText("");
    txtNameOnCard.setText("");
    txtCVC.setText("");
    cboPaymentMethod.setVisible(false);
    txtCardNumber.setVisible(false);
    txtNameOnCard.setVisible(false);
    cboYear.setVisible(false);
    cboMonth.setVisible(false);
    txtCVC.setVisible(false);
    btnPay.setVisible(false);
    txtBookingNo.setDisable(false);
    
    }
}
