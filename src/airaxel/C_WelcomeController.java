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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class C_WelcomeController implements Initializable, ControlledScreen  {
    ScreensController myController;

    @FXML private AnchorPane LogInPane;
    @FXML private JFXTextField txtUserName;
    @FXML private JFXPasswordField txtPasswordLogin;
    @FXML private AnchorPane SignUp;
    @FXML private JFXTextField txtName;
    @FXML private JFXTextField txtEmail;
    @FXML private JFXTextField txtPhone;
    @FXML private JFXTextField txtStreet;
    @FXML private JFXTextField txtCity;
    @FXML private JFXTextField txtZip;
    @FXML private JFXComboBox<String> cboCountry;
    @FXML private JFXDatePicker DateDoB;
    @FXML private JFXComboBox<String> cboIdentification;
    @FXML private JFXTextField txtIdentificationNo;
    @FXML private JFXPasswordField txtPassword;
    @FXML private JFXPasswordField txtPassword2;
    @FXML private JFXButton btnLogIn;

    @FXML private JFXButton btnSignUp;

    @FXML private JFXButton btnLogOut;

    @FXML
    void LogOut(ActionEvent event) {
        AirAxel.SetMemeberID(0);
        btnLogIn.setDisable(false);
        btnSignUp.setDisable(false);
        txtUserName.setDisable(false);
        txtPasswordLogin.setDisable(false);
        txtUserName.setText("");
        txtPasswordLogin.setText("");
    }
    
    @FXML
    private void SignIn(ActionEvent event )throws SQLException{
        boolean Success =false;
        if(txtUserName.getText() == null || txtUserName.getText().trim().isEmpty() || txtPasswordLogin.getText() == null || txtPasswordLogin.getText().trim().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Provide both Username and password!");
            alert.showAndWait();
        }else{
            Success = AirAxel.MemberLogin(txtUserName.getText(),txtPasswordLogin.getText());
            if(Success){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Welcome loyal member");
                    btnLogIn.setDisable(true);
                    btnSignUp.setDisable(true);
                    txtUserName.setDisable(true);
                    txtPasswordLogin.setDisable(true);
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Something went wrong and we could not find your profile.");
                    alert.showAndWait();
                }
        }
    }  
    
    @FXML
    private void SignUp(ActionEvent event){
       LogInPane.setVisible(false);
       SignUp.setVisible(true);
       cboCountry.getItems().clear();
       cboIdentification.getItems().clear();
       cboCountry.getItems().addAll("Belgium", "France","United Kingdom","United States of America");
       cboIdentification.getItems().addAll("Driver license", "Green card", "Passport", "other");
    }
     
    @FXML
    private void Submit(ActionEvent event) throws SQLException{
        Boolean FieldEmpty = false;
        Boolean Success = false;
        
        System.out.println("+" + cboCountry.getValue() + "+");
        if(txtName.getText().isEmpty()){FieldEmpty = true;};
        if(txtEmail.getText().isEmpty()){FieldEmpty = true;};
        if(txtPhone.getText().isEmpty()){FieldEmpty = true;};
        if(txtStreet.getText().isEmpty()){FieldEmpty = true;};
        if(txtCity.getText().isEmpty()){FieldEmpty = true;};
        if(txtZip.getText().isEmpty()){FieldEmpty = true;};
        if(cboCountry.getValue()==null){FieldEmpty = true;};
        if(DateDoB.getValue()==null){FieldEmpty = true;};
        if(cboIdentification.getValue()==null){FieldEmpty = true;};
        if(txtIdentificationNo.getText().isEmpty()){FieldEmpty = true;};
        if(txtPassword.getText().isEmpty()){FieldEmpty = true;};
        if(txtPassword2.getText().isEmpty()){FieldEmpty = true;};    
        
        
        if(!FieldEmpty){
            if(txtPassword.getText().equals(txtPassword2.getText())){
             DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
             String FormattedDoB = DateDoB.getValue().format(format);
                Success = AirAxel.newCustomerMember(txtName.getText(), txtEmail.getText(), txtPhone.getText(), txtStreet.getText(), txtCity.getText(), txtZip.getText(), cboCountry.getValue(),FormattedDoB, cboIdentification.getValue(), txtIdentificationNo.getText(), txtPassword.getText());
                if(Success){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Welcome to our membership program, login to your new account to use it.");
                    alert.showAndWait();
                    LogInPane.setVisible(true);
                    SignUp.setVisible(false);
                    btnLogIn.setDisable(true);
                    btnSignUp.setDisable(true);
                    txtUserName.setText(txtEmail.getText());
                    txtUserName.setDisable(true);
                    txtPasswordLogin.setText(txtPassword.getText());
                    txtPasswordLogin.setDisable(true);
                    EmptyFields();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Something went wrong and we could not add you to our membership list. Check if you have already a profile.");
                    alert.showAndWait();
                    LogInPane.setVisible(true);
                    SignUp.setVisible(false);
                    EmptyFields();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Password doesn't match!");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("All fields must be filled out");
            alert.showAndWait();
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LogInPane.setVisible(true);
        //SignUp.setVisible(false);
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
     
        
    @FXML
    private void goTo_Welcome(ActionEvent event){
        myController.setScreen(AirAxel.Welcome);
        LogInPane.setVisible(true);
        SignUp.setVisible(false);
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
        txtUserName.setText("");
        txtPasswordLogin.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtZip.setText("");
        cboCountry.setValue(null);
        cboIdentification.setValue(null);
        txtIdentificationNo.setText("");
        txtPassword.setText("");
        txtPassword2.setText("");
    }
}
