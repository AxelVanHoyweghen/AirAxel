package airaxel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.sql.*;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author axelv
 */
public class WelcomeController implements Initializable, ControlledScreen  {

    ScreensController myController;
     
    @FXML private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnConnect;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnEmployees;

    @FXML private JFXTextField txtDBLocation;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCustomer.setVisible(false);
        btnEmployees.setVisible(false);
       
    }    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
     @FXML
    void ConnectServer(ActionEvent event) {
         if(txtUserName.getText() == null || txtUserName.getText().trim().isEmpty() || txtPassword.getText() == null || txtPassword.getText().trim().isEmpty()|| txtDBLocation.getText() == null || txtDBLocation.getText().trim().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Provide both Username and password!");
            alert.showAndWait();
           
            //execute query
        }else{
           ServerConnection con = new ServerConnection();
           try{
               Class.forName("com.mysql.jdbc.Driver");
           Connection Connect = con.getConnection(txtUserName.getText(), txtPassword.getText(), txtDBLocation.getText());
           Connect.close();
           if(Connect != null){
               btnConnect.setDisable(true);
               txtUserName.setDisable(true);
               txtPassword.setDisable(true);
               txtDBLocation.setDisable(true);
               btnCustomer.setVisible(true);
               btnEmployees.setVisible(true);
             
           }
           }catch(Exception e){System.out.println(e);}
         }
    }
   
      @FXML
    private void goToC_Welcome(ActionEvent event){
       myController.setScreen(AirAxel.C_Welcome);
    }

    @FXML
    void goToE_Welcome(ActionEvent event) {
       myController.setScreen(AirAxel.E_Welcome);
    }
    
}
