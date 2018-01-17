
package airaxel;

import static airaxel.AirAxel.LookUpFlights;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class C_BookingController implements Initializable, ControlledScreen {
ScreensController myController;
 private ObservableList<FlightObject> OneList;
 private ObservableList<FlightObject> TwoList;
 private ObservableList<FlightObject> ThreeList;
 private ObservableList<FlightObject> FourList;
 private int NOF;
 private int type;
 private int posNExt = 0;
 private String TravelClass = "";
 
    @FXML private AnchorPane MainAnchor;
    @FXML private JFXButton btn1Way;
    @FXML private JFXButton Btn2Way;
    @FXML private JFXButton btn3Way;
    
    //************************************************************* ONE WAY *********************************************************
        @FXML private AnchorPane OneWayPane;
            @FXML private JFXComboBox<String> txtFrom1;
            @FXML private JFXComboBox<String> txtTo1;
            @FXML private JFXDatePicker DateDepartureDate1;
        @FXML private JFXButton btnFindFlight1;

    //************************************************************* ROUND TRIP *********************************************************
    @FXML private AnchorPane RoundTripPane;
        @FXML private JFXComboBox<String> txtFrom2;
        @FXML private JFXComboBox<String> txtTo2;
        @FXML private JFXDatePicker DateDepartureDate2;
        @FXML private JFXDatePicker DateReturnDate2;
    @FXML private JFXButton btnFindFlight2;
    
    //************************************************************* MULTI-CITY *********************************************************
    @FXML private AnchorPane MultiCityPane;
        @FXML private JFXComboBox<Integer> cboNOFlights;
        //Flight1
           @FXML private JFXComboBox<String> txtFrom3F1;
           @FXML private JFXComboBox<String> txtTo3F1;
           @FXML private JFXDatePicker DateDepartureDate3F1;
           @FXML private Label lblFlight1;
        //Flight2
            @FXML private JFXComboBox<String> txtFrom3F2;
            @FXML private JFXComboBox<String> txtTo3F2;
            @FXML private JFXDatePicker DateDepartureDate3F2;
            @FXML private Label lblFlight2;
        //Flight3
            @FXML private JFXComboBox<String> txtFrom3F3;
            @FXML private JFXComboBox<String> txtTo3F3;
            @FXML private JFXDatePicker DateDepartureDate3F3;
            @FXML private Label lblFlight3;
        //Flight4
            @FXML private JFXComboBox<String> txtFrom3F4;
            @FXML private JFXComboBox<String> txtTo3F4;
            @FXML private JFXDatePicker DateDepartureDate3F4;
            @FXML private Label lblFlight4;
    @FXML private JFXButton btnFindFlight3;

      //************************************************************* Display flights *********************************************************
    @FXML private AnchorPane FlightsFound;
        @FXML private TableView<FlightObject> tblFlights;
                @FXML private TableColumn<FlightObject, String> clmFrom;
                @FXML private TableColumn<FlightObject, String> clmDeparture;
                @FXML private TableColumn<FlightObject, String> clmTo;
                @FXML private TableColumn<FlightObject, String> clmArrival;
                @FXML private TableColumn<FlightObject, String> clmPReg;
                @FXML private TableColumn<FlightObject, String> clmPCom;
                @FXML private TableColumn<FlightObject, String> clmPFirst;
                @FXML private TableColumn<FlightObject, String> clmTime;
                @FXML private TableColumn<FlightObject, String> clmDistance;
                
                @FXML private JFXButton btnNextFlightView;
//********************************************from Signup ***********************************************
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
    
//Guest Customer pane
    @FXML private AnchorPane GuestCustomer;
     @FXML private JFXTextField txtName1;
    @FXML private JFXTextField txtEmail1;
    @FXML private JFXTextField txtPhone1;
    @FXML private JFXTextField txtStreet1;
    @FXML private JFXTextField txtCity1;
    @FXML private JFXTextField txtZip1;
    @FXML private JFXComboBox<String> cboCountry1;
    @FXML private JFXDatePicker DateDoB1;
    @FXML private JFXComboBox<String> cboIdentification1;
    @FXML private JFXTextField txtIdentificationNo1;

    @FXML private JFXTextArea txtItinerary;
    @FXML private AnchorPane FinalPane;
    @FXML private JFXButton btnBook;
    @FXML private JFXComboBox<String> cboClass;
    @FXML private Label lblPrice;

    
//*****************************************************************************************************************************************    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EmptyFields();
        MainAnchor.setVisible(true);
        
        cboNOFlights.getItems().addAll(1,2,3,4);
        clmFrom.setCellValueFactory(new PropertyValueFactory<>("FromLoc"));
            clmDeparture.setCellValueFactory(new PropertyValueFactory<>("Departure"));
            clmTo.setCellValueFactory(new PropertyValueFactory<>("TO"));
            clmArrival.setCellValueFactory(new PropertyValueFactory<>("Arrival"));
            clmPReg.setCellValueFactory(new PropertyValueFactory<>("RegPrice"));
            clmPCom.setCellValueFactory(new PropertyValueFactory<>("PlusPrice"));
            clmPFirst.setCellValueFactory(new PropertyValueFactory<>("FirstPRice"));
            clmTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
            clmDistance.setCellValueFactory(new PropertyValueFactory<>("Distance"));
    }    
    
    @FXML 
    private void DisplayNOFlights(ActionEvent event){
        NOF = 0;
        NOF = cboNOFlights.getValue();
        btnFindFlight3.setVisible(true);
        if(NOF == 1){
             txtFrom3F1.setVisible(true);
                        txtTo3F1.setVisible(true);
                        DateDepartureDate3F1.setVisible(true);
                        lblFlight1.setVisible(true);
                     //Flight2
                         txtFrom3F2.setVisible(false);
                         txtTo3F2.setVisible(false);
                         DateDepartureDate3F2.setVisible(false);
                         lblFlight2.setVisible(false);
                     //Flight3
                         txtFrom3F3.setVisible(false);
                         txtTo3F3.setVisible(false);
                         DateDepartureDate3F3.setVisible(false);
                         lblFlight3.setVisible(false);
                     //Flight4
                         txtFrom3F4.setVisible(false);
                         txtTo3F4.setVisible(false);
                         DateDepartureDate3F4.setVisible(false);
                         lblFlight4.setVisible(false); 
        }
        if(NOF ==2){
            txtFrom3F1.setVisible(true);
                        txtTo3F1.setVisible(true);
                        DateDepartureDate3F1.setVisible(true);
                        lblFlight1.setVisible(true);
                     //Flight2
                         txtFrom3F2.setVisible(true);
                         txtTo3F2.setVisible(true);
                         DateDepartureDate3F2.setVisible(true);
                         lblFlight2.setVisible(true);
                     //Flight3
                         txtFrom3F3.setVisible(false);
                         txtTo3F3.setVisible(false);
                         DateDepartureDate3F3.setVisible(false);
                         lblFlight3.setVisible(false);
                     //Flight4
                         txtFrom3F4.setVisible(false);
                         txtTo3F4.setVisible(false);
                         DateDepartureDate3F4.setVisible(false);
                         lblFlight4.setVisible(false); 
        }
        if(NOF ==3){
           txtFrom3F1.setVisible(true);
                        txtTo3F1.setVisible(true);
                        DateDepartureDate3F1.setVisible(true);
                        lblFlight1.setVisible(true);
                     //Flight2
                         txtFrom3F2.setVisible(true);
                         txtTo3F2.setVisible(true);
                         DateDepartureDate3F2.setVisible(true);
                         lblFlight2.setVisible(true);
                     //Flight3
                         txtFrom3F3.setVisible(true);
                         txtTo3F3.setVisible(true);
                         DateDepartureDate3F3.setVisible(true);
                         lblFlight3.setVisible(true);
                     //Flight4
                         txtFrom3F4.setVisible(false);
                         txtTo3F4.setVisible(false);
                         DateDepartureDate3F4.setVisible(false);
                         lblFlight4.setVisible(false); 
        }
        if(NOF == 4){ 
                        txtFrom3F1.setVisible(true);
                        txtTo3F1.setVisible(true);
                        DateDepartureDate3F1.setVisible(true);
                        lblFlight1.setVisible(true);
                     //Flight2
                         txtFrom3F2.setVisible(true);
                         txtTo3F2.setVisible(true);
                         DateDepartureDate3F2.setVisible(true);
                         lblFlight2.setVisible(true);
                     //Flight3
                         txtFrom3F3.setVisible(true);
                         txtTo3F3.setVisible(true);
                         DateDepartureDate3F3.setVisible(true);
                         lblFlight3.setVisible(true);
                     //Flight4
                         txtFrom3F4.setVisible(true);
                         txtTo3F4.setVisible(true);
                         DateDepartureDate3F4.setVisible(true);
                         lblFlight4.setVisible(true);   
        }
    }
    
    @FXML 
    private void FindFlightOneWay(ActionEvent event){
        type = 0;
        type = 100;
         posNExt = 0;
        DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
        String FormattedDoB = DateDepartureDate1.getValue().format(format);
        OneList = AirAxel.LookUpFlights(txtFrom1.getValue(), txtTo1.getValue(), FormattedDoB);
        if(OneList.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("The flights you requested could not be found." );
            alerta.showAndWait();
            AirAxel.EmptyFlightBooking();
        }else{
            GoToNext(null);
        }
    }
    @FXML 
    private void FindFlightRoundTrip(ActionEvent event){
        type = 0;
        type = 200;
         posNExt = 0;
        DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
        String Formatted1 = DateDepartureDate2.getValue().format(format);
        String Formatted2 = DateReturnDate2.getValue().format(format);
        OneList = AirAxel.LookUpFlights(txtFrom2.getValue(), txtTo2.getValue(), Formatted1);
        TwoList = AirAxel.LookUpFlights(txtTo2.getValue(), txtFrom2.getValue(),Formatted2);
        if(OneList.isEmpty() || TwoList.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("The flights you requested could not be found." );
            alerta.showAndWait();
            AirAxel.EmptyFlightBooking();
        }else{
            GoToNext(null);
        }
    }
    @FXML 
    private void FindFlightMultiCity(ActionEvent event){
        type = 0;
        type = 300;
        posNExt = 0;
        Boolean Empty = false;
        DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
        if(NOF == 1){
             String Formatted1 = DateDepartureDate3F1.getValue().format(format);
             OneList = AirAxel.LookUpFlights(txtFrom3F1.getValue(), txtTo3F1.getValue(), Formatted1);
              if(OneList.isEmpty()){
                  Empty = true;
              }
        }
        if(NOF ==2){
                String Formatted1 = DateDepartureDate3F1.getValue().format(format);
                String Formatted2 = DateDepartureDate3F2.getValue().format(format);
                OneList = AirAxel.LookUpFlights(txtFrom3F1.getValue(), txtTo3F1.getValue(), Formatted1);
                TwoList = AirAxel.LookUpFlights(txtFrom3F2.getValue(), txtTo3F2.getValue(), Formatted2);
                if(OneList.isEmpty() || TwoList.isEmpty()){
                  Empty = true;
              }
        }
        if(NOF ==3){
                String Formatted1 = DateDepartureDate3F1.getValue().format(format);
                String Formatted2 = DateDepartureDate3F2.getValue().format(format);
                String Formatted3 = DateDepartureDate3F3.getValue().format(format);
                OneList = AirAxel.LookUpFlights(txtFrom3F1.getValue(), txtTo3F1.getValue(), Formatted1);
                TwoList = AirAxel.LookUpFlights(txtFrom3F2.getValue(), txtTo3F2.getValue(), Formatted2);
                ThreeList = AirAxel.LookUpFlights(txtFrom3F3.getValue(), txtTo3F3.getValue(), Formatted3);
                if(OneList.isEmpty() || TwoList.isEmpty() || ThreeList.isEmpty()){
                  Empty = true;
                }
        }
        if(NOF == 4){        
            String Formatted1 = DateDepartureDate3F1.getValue().format(format);
            String Formatted2 = DateDepartureDate3F2.getValue().format(format);
            String Formatted3 = DateDepartureDate3F3.getValue().format(format);
            String Formatted4 = DateDepartureDate3F4.getValue().format(format);

            OneList = AirAxel.LookUpFlights(txtFrom3F1.getValue(), txtTo3F1.getValue(), Formatted1);
            TwoList = AirAxel.LookUpFlights(txtFrom3F2.getValue(), txtTo3F2.getValue(), Formatted2);
            ThreeList = AirAxel.LookUpFlights(txtFrom3F3.getValue(), txtTo3F3.getValue(), Formatted3);
            FourList = AirAxel.LookUpFlights(txtFrom3F4.getValue(), txtTo3F4.getValue(), Formatted4);
            if(OneList.isEmpty() || TwoList.isEmpty() || ThreeList.isEmpty() || FourList.isEmpty()){
                  Empty = true;
                }
        }
        if(Empty){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("The flights you requested could not be found." );
            alerta.showAndWait();
            AirAxel.EmptyFlightBooking();
        }else{
            GoToNext(null);
        }
    }
    
    @FXML 
    private void GoToNext(ActionEvent event){
        posNExt++;
        
        if(type == 100){//for 1 way
            if(posNExt == 1){
            tblFlights.setItems(null);
            tblFlights.setItems(OneList);
            OneWayPane.setVisible(false);
            FlightsFound.setVisible(true);
            btnNextFlightView.setText("Book flight");
            }
            if(posNExt == 2){
                 ViewItinerary();
            }
        }
        if(type ==200){//for 2 way
            if(posNExt == 1){
            tblFlights.setItems(null);
            tblFlights.setItems(OneList);
            RoundTripPane.setVisible(false);
            FlightsFound.setVisible(true);
            btnNextFlightView.setText("Ok, next flight");
            }
            if(posNExt == 2){
            tblFlights.setItems(null);
            tblFlights.setItems(TwoList);
            FlightsFound.setVisible(true);
            btnNextFlightView.setText("Book flight");
            }
            if(posNExt == 3){
                ViewItinerary();
            }
        }
        if(type ==300){
            
            if(NOF == 1){        
               if(posNExt == 1){
                    tblFlights.setItems(null);
                    tblFlights.setItems(OneList);
                    MultiCityPane.setVisible(false);
                    FlightsFound.setVisible(true);
                    btnNextFlightView.setText("Book flight");
                }
                if(posNExt == 2){
                     ViewItinerary();
                }
            }
            
            if(NOF == 2){        
                    if(posNExt == 1){
                        tblFlights.setItems(null);
                        tblFlights.setItems(OneList);
                        MultiCityPane.setVisible(false);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Ok, next flight");
                    }
                    if(posNExt == 2){
                        tblFlights.setItems(null);
                        tblFlights.setItems(TwoList);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Ok, next flight");
                    }
                    if(posNExt == 3){
                         ViewItinerary();
                    }
            }
            if(NOF == 3){        
                    if(posNExt == 1){
                        tblFlights.setItems(null);
                        tblFlights.setItems(OneList);
                        MultiCityPane.setVisible(false);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Ok, next flight");
                    }
                    if(posNExt == 2){
                        tblFlights.setItems(null);
                        tblFlights.setItems(TwoList);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Ok, next flight");
                    }
                    if(posNExt == 3){
                        tblFlights.setItems(null);
                        tblFlights.setItems(ThreeList);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Book flight");
                    }
                    if(posNExt == 4){
                         ViewItinerary();
                    }
            }
            if(NOF == 4){        
                    if(posNExt == 1){
                        tblFlights.setItems(null);
                        tblFlights.setItems(OneList);
                        MultiCityPane.setVisible(false);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Ok, next flight");
                    }
                    if(posNExt == 2){
                        tblFlights.setItems(null);
                        tblFlights.setItems(TwoList);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Ok, next flight");
                    }
                    if(posNExt == 3){
                        tblFlights.setItems(null);
                        tblFlights.setItems(ThreeList);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Ok, next flight");
                    }
                    if(posNExt == 4){
                       tblFlights.setItems(null);
                        tblFlights.setItems(ThreeList);
                        FlightsFound.setVisible(true);
                        btnNextFlightView.setText("Book flight");
                    }
                    if(posNExt == 5){
                         ViewItinerary();
                    }
            }
        }
        
    }
    
    void ViewItinerary(){
        txtItinerary.setText(AirAxel.FlightItineraryFlightNo());
        MainAnchor.setVisible(false);
        FlightsFound.setVisible(false);
        OneWayPane.setVisible(false);
        RoundTripPane.setVisible(false);
        MultiCityPane.setVisible(false);
        FinalPane.setVisible(true);
        cboClass.setVisible(true);
        cboClass.getItems().clear();
        cboClass.getItems().addAll("Comfort", "Comfort+","First class"); 
        btnBook.setDisable(true);
        lblPrice.setVisible(false);
    }
    
     @FXML
    void ChangePrice(ActionEvent event) {
        TravelClass = cboClass.getValue();
        lblPrice.setText("Price: $" + AirAxel.GetPriceFlights(TravelClass));
        lblPrice.setVisible(true);
        btnBook.setDisable(false);
    }
    
   
    void BookFlight() {
               int Bookingno = AirAxel.BookFlights(TravelClass);
               if(Bookingno !=0){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText("Your flights have been booked, for further reference and for making your payment \n your booking number is: " + Bookingno + " \n Thank you for choosing Air Axel." );
                alerta.showAndWait();
                EmptyFields();
                myController.setScreen(AirAxel.C_Welcome);
               }else{
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText("Something went wrong and we can not book your flight at this time." );
                    alerta.showAndWait();
                    EmptyFields();
                    myController.setScreen(AirAxel.C_Welcome);
               }

    }
    @FXML
    private void GoToCustomersMemberSignIn(ActionEvent event){
        if(AirAxel.getMemberID() == 0){
            FinalPane.setVisible(false);
            LogInPane.setVisible(true);
        }else{
            FinalPane.setVisible(false);
            BookFlight();
        }
    }
    
    @FXML 
    private void GuestSignUp(ActionEvent event){
       LogInPane.setVisible(false);
       SignUp.setVisible(false);
       GuestCustomer.setVisible(true);
       cboCountry1.getItems().clear();
       cboIdentification1.getItems().clear();
       cboCountry1.getItems().addAll("Belgium", "France","United Kingdom","United States of America");
       cboIdentification1.getItems().addAll("Driver license", "Green card", "Passport", "other");
    }

    @FXML 
    private void Submit2(ActionEvent event)throws SQLException{
        Boolean FieldEmpty = false;
        Boolean Success = false;
        
        if(txtName1.getText().isEmpty()){FieldEmpty = true;};
        if(txtEmail1.getText().isEmpty()){FieldEmpty = true;};
        if(txtPhone1.getText().isEmpty()){FieldEmpty = true;};
        if(txtStreet1.getText().isEmpty()){FieldEmpty = true;};
        if(txtCity1.getText().isEmpty()){FieldEmpty = true;};
        if(txtZip1.getText().isEmpty()){FieldEmpty = true;};
        if(cboCountry1.getValue()==null){FieldEmpty = true;};
        if(DateDoB1.getValue()==null){FieldEmpty = true;};
        if(cboIdentification1.getValue()==null){FieldEmpty = true;};
        if(txtIdentificationNo1.getText().isEmpty()){FieldEmpty = true;}; 

        if(!FieldEmpty){
             DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
             String FormattedDoB = DateDoB1.getValue().format(format);
             Success = AirAxel.newCustomerMember(txtName1.getText(), txtEmail1.getText(), txtPhone1.getText(), txtStreet1.getText(), txtCity1.getText(), txtZip1.getText(), cboCountry1.getValue(),FormattedDoB, cboIdentification1.getValue(), txtIdentificationNo1.getText(), null);
                if(Success){
                   BookFlight();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Something went wrong and we could not add you.");
                    alert.showAndWait();
                }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("All fields must be filled out");
            alert.showAndWait();
        }
    }
    
    
   
//********************************************************************* From form C-Welcome *************************************   
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
                    alert.showAndWait();
                    
                   BookFlight();
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
       GuestCustomer.setVisible(false);
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
                    alert.setHeaderText("Welcome to our membership program.");
                    alert.showAndWait();
                    BookFlight();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Something went wrong and we could not add you to our membership list. Check if you have already a profile.");
                    alert.showAndWait();
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
        
       
        txtName1.setText("");
        txtEmail1.setText("");
        txtPhone1.setText("");
        txtStreet1.setText("");
        txtCity1.setText("");
        txtZip1.setText("");
        cboCountry1.setValue(null);
        cboIdentification1.setValue(null);
        txtIdentificationNo1.setText("");
        txtItinerary.setText("");
        AirAxel.EmptyFlightBooking();
        
        MainAnchor.setVisible(true);
        FlightsFound.setVisible(false);
        OneWayPane.setVisible(false);
        RoundTripPane.setVisible(false);
        MultiCityPane.setVisible(false);
        LogInPane.setVisible(false);
        SignUp.setVisible(false);
        GuestCustomer.setVisible(false);
        FinalPane.setVisible(false);
    }
    
//**********************************************************************************************************************************************
    @FXML
    private void goTo_OneWay(ActionEvent event){
        EmptyFields();
        SetCBo();
        MainAnchor.setVisible(true);
        OneWayPane.setVisible(true);
        RoundTripPane.setVisible(false);
        MultiCityPane.setVisible(false);
        FlightsFound.setVisible(false);
        
    }
    @FXML
    private void goTo_RoundTrip(ActionEvent event){
        EmptyFields();
        SetCBo();
        MainAnchor.setVisible(true);
        OneWayPane.setVisible(false);
        RoundTripPane.setVisible(true);
        MultiCityPane.setVisible(false);
        FlightsFound.setVisible(false);
    }
    @FXML
    private void goTo_MultiCity(ActionEvent event){
        EmptyFields();
        SetCBo();
        MainAnchor.setVisible(true);
        OneWayPane.setVisible(false);
        RoundTripPane.setVisible(false);
        MultiCityPane.setVisible(true);
        FlightsFound.setVisible(false);
        
           txtFrom3F1.setVisible(false);
           txtTo3F1.setVisible(false);
           DateDepartureDate3F1.setVisible(false);
           lblFlight1.setVisible(false);
        //Flight2
            txtFrom3F2.setVisible(false);
            txtTo3F2.setVisible(false);
            DateDepartureDate3F2.setVisible(false);
            lblFlight2.setVisible(false);
        //Flight3
            txtFrom3F3.setVisible(false);
            txtTo3F3.setVisible(false);
            DateDepartureDate3F3.setVisible(false);
            lblFlight3.setVisible(false);
        //Flight4
            txtFrom3F4.setVisible(false);
            txtTo3F4.setVisible(false);
            DateDepartureDate3F4.setVisible(false);
            lblFlight4.setVisible(false);
            
        btnFindFlight3.setVisible(false);
    }
    
    private void SetCBo(){
        String[] From = AirAxel.FromFlightCbo();
        String[] TO = AirAxel.ToFlightCbo();
        txtFrom1.getItems().clear();
        txtFrom2.getItems().clear();
        txtFrom3F1.getItems().clear();
        txtFrom3F2.getItems().clear();
        txtFrom3F3.getItems().clear();
        txtFrom3F4.getItems().clear();
        txtTo1.getItems().clear();
        txtTo2.getItems().clear();
        txtTo3F1.getItems().clear();
        txtTo3F2.getItems().clear();
        txtTo3F3.getItems().clear();
        txtTo3F4.getItems().clear();   
        for(int i=0;i<From.length;i++)
        {
            if(From[i] != null){
            txtFrom1.getItems().add(From[i]);
            txtFrom2.getItems().add(From[i]);
            txtFrom3F1.getItems().add(From[i]);
            txtFrom3F2.getItems().add(From[i]);
            txtFrom3F3.getItems().add(From[i]);
            txtFrom3F4.getItems().add(From[i]);
            }
        }
        for(int j=0;j<TO.length;j++){
           if(TO[j] != null){
            txtTo1.getItems().add(TO[j]);
           txtTo2.getItems().add(TO[j]);
           txtTo3F1.getItems().add(TO[j]);
           txtTo3F2.getItems().add(TO[j]);
           txtTo3F3.getItems().add(TO[j]);
           txtTo3F4.getItems().add(TO[j]);
           }
        }
    }
//**************************************************************************************************************************************
    @Override 
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
    private void goTo_Welcome(ActionEvent event){
        EmptyFields();
       myController.setScreen(AirAxel.Welcome);
    }
    @FXML
    private void goToC_Booking(ActionEvent event){
        EmptyFields();
        
       myController.setScreen(AirAxel.C_Booking);
    }
    @FXML
    private void goToC_Cancel(ActionEvent event){
        EmptyFields();
       myController.setScreen(AirAxel.C_Cancel);
    }
    @FXML
    private void goToC_Itinerary(ActionEvent event){
        EmptyFields();
       myController.setScreen(AirAxel.C_Itinerary);
    }
    @FXML
    private void goToC_Payment(ActionEvent event){
        EmptyFields();
       myController.setScreen(AirAxel.C_Payment);
    }
}
