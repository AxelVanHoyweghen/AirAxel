package airaxel;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.swing.ImageIcon;

public class AirAxel extends Application implements ControlledScreen {
    ScreensController myController;
    
    //main references to frames
    public static String Splash = "Splash";
    public static String SplashFile = "FXML/Splash.fxml";
    public static String Welcome = "Welcome";
    public static String WelcomeFile = "FXML/Welcome.fxml";
    public static String C_Welcome = "C_Welcome";
    public static String C_WelcomeFile ="FXML/C_Welcome.fxml";
    public static String E_Welcome = "E_Welcome";
    public static String E_WelcomeFile ="FXML/E_Welcome.fxml";
    
    //references for Customer frames
    public static String C_Booking = "C_Booking";
    public static String C_BookingFile ="FXML/C_Booking.fxml";
    public static String C_Cancel = "C_Cancel";
    public static String C_CancelFile ="FXML/C_Cancel.fxml";
    public static String C_Itinerary = "C_Itinerary";
    public static String C_ItineraryFile ="FXML/C_Itinerary.fxml";
    public static String C_Payment = "C_Payment";
    public static String C_PaymentFile ="FXML/C_Payment.fxml";
    
    //references for Employee frames
public static String E_FlightInfo = "E_FlightInfo";
    public static String E_FlightInfoFile ="FXML/E_FlightInfo.fxml";
    public static String E_UpdateFlight = "E_UpdateFlight";
    public static String E_UpdateFlightFile ="FXML/E_UpdateFlight.fxml";
    public static String E_AddFlight = "E_AddFlight";
    public static String E_AddFlightFile ="FXML/E_AddFlight.fxml";
    public static String E_DeleteFlight = "E_DeleteFlight";
    public static String E_DeleteFlightFile ="FXML/E_DeleteFlight.fxml";
    public static String E_AddAirport = "E_AddAirport";
    public static String E_AddAirportFile ="FXML/E_AddAirport.fxml";
    public static String E_LiveInfo = "E_LiveInfo";
    public static String E_LiveInfoFile ="FXML/E_LiveInfo.fxml";
    

    public static ServerConnection servercon = new ServerConnection();
    public static int MemberID; 
    public static int CustomerID;
    public static int[] FlightsBooking = new int[30];
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        
        mainContainer.loadScreen(AirAxel.Splash, AirAxel.SplashFile); 
        mainContainer.loadScreen(AirAxel.Welcome, AirAxel.WelcomeFile);
        mainContainer.loadScreen(AirAxel.C_Welcome, AirAxel.C_WelcomeFile);
        mainContainer.loadScreen(AirAxel.E_Welcome, AirAxel.E_WelcomeFile);
        
        //load customer screens
            mainContainer.loadScreen(AirAxel.C_Booking, AirAxel.C_BookingFile);
            mainContainer.loadScreen(AirAxel.C_Cancel, AirAxel.C_CancelFile);
            mainContainer.loadScreen(AirAxel.C_Itinerary, AirAxel.C_ItineraryFile);
            mainContainer.loadScreen(AirAxel.C_Payment, AirAxel.C_PaymentFile);
        
        //load Employees screens
            mainContainer.loadScreen(AirAxel.E_FlightInfo, AirAxel.E_FlightInfoFile);
            mainContainer.loadScreen(AirAxel.E_UpdateFlight, AirAxel.E_UpdateFlightFile);
            mainContainer.loadScreen(AirAxel.E_AddFlight, AirAxel.E_AddFlightFile);
            mainContainer.loadScreen(AirAxel.E_DeleteFlight, AirAxel.E_DeleteFlightFile);
            mainContainer.loadScreen(AirAxel.E_AddAirport, AirAxel.E_AddAirportFile);
            mainContainer.loadScreen(AirAxel.E_LiveInfo, AirAxel.E_LiveInfoFile);
        
        
        mainContainer.setScreen(AirAxel.Splash);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(" Air Axel");
        primaryStage.getIcons().add(new Image("https://ae01.alicdn.com/kf/HTB1Dbv8HFXXXXXNaFXXq6xXFXXXd/22-5CM-PLANE-SILHOUETTE-Car-Sticker-Decal-Cartoon-Plane-Motorcycle-Car-Styling-Black-Silver-C2-0193.jpg"));
        primaryStage.show();
    }
    
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
     
    public static void main(String[] args) {
        launch(args);
    }
    
    //***************************************************** Customer *********************************
    public static boolean MemberLogin(String UserName, String Pw)throws SQLException{
        Boolean Suceeded = false;
        try{
        Connection con = servercon.getConnection(null, null, null);
         Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT Members.Member_id, Guest_Customers.Customer_id FROM members inner join Guest_Customers on Guest_Customers.Member_id = Members.Member_id Where Member_Email = '" + UserName+"' AND Member_Password = '" + Pw + "';");
        rs.next();
        MemberID = rs.getInt(1);
        CustomerID = rs.getInt(2);
        System.out.println(MemberID);
        System.out.println(CustomerID);
        Suceeded = true;
        con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }//end MemberLogin
    
    public static boolean newCustomerMember(String Name, String Email, String Phone, String Street, String City, String Zip, String Country, String DoB, String IdentificationType, String IdentificationNo, String Password)throws SQLException{
        Boolean Suceeded = false;
        try{
        Connection con = servercon.getConnection(null, null, null);
        CallableStatement cStmt = con.prepareCall("{call NewMemberCustomer(?,?,?,?,?,?,?,?,?,?,?,?)}");
        cStmt.setString(1,Name);
        cStmt.setString(2,Email);
        cStmt.setString(3,Phone);
        cStmt.setString(4,Street);
        cStmt.setString(5,City);
        cStmt.setString(6,Country);
        cStmt.setString(7,DoB);
        cStmt.setString(8,IdentificationType);
        cStmt.setString(9,IdentificationNo);
        cStmt.setString(10,Password);
        
        cStmt.execute();
        CustomerID = cStmt.getInt(11);
        MemberID =cStmt.getInt(12);
        
        Suceeded = true;
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }

    public static float LookUpPayment(String BookingNo){
        float Amount = 99999999;
        String Status = "";
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT Total_price, Payment_status from Bookings inner join Payments on Bookings.Payment_id = Payments.Payment_id WHERE Booking_no = '" +BookingNo+ "';");
            rs.next();
            Amount = rs.getFloat(1);
            Status = rs.getString(2);
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        if(!Status.equals("Pending")){
            Amount = 999999999;
        }
        return Amount;
    }
    
    public static boolean MakePayment(String BookingNo, String PaymentMethod, String Name, String number, String expdate, String CVC){
        Boolean Suceeded = false;
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
            CallableStatement cStmt = con.prepareCall("{call MakePayment(?,?,?,?,?,?)}");
            cStmt.setString(1,BookingNo);
            cStmt.setString(2,PaymentMethod);
            cStmt.setString(3,Name);
            cStmt.setString(4,number);
            cStmt.setString(5,expdate);
            cStmt.setString(6,CVC);
        
            cStmt.execute();
            Suceeded = true;
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }
    
    public static ObservableList<FlightObject> LookUpFlights(String Origin, String Destination, String DepartureDate){
        ObservableList<FlightObject> MyList = null;
        MyList = FXCollections.observableArrayList();

        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
             CallableStatement cStmt = con.prepareCall("{call SearchFlight(?,?,?,?,?,?,?,?,?)}");
            cStmt.setString(1,Origin);
            cStmt.setString(2,Destination);
            cStmt.setString(3,DepartureDate);
            cStmt.execute();

            int CountFlights = cStmt.getInt(9);
            
            for(int i =1; i<=CountFlights; i++){
                int ActiveFlight = cStmt.getInt(i+3);
               
                for(int ib = 0; ib<30; ib++){
                    if(FlightsBooking[ib] == 0){
                    
                    FlightsBooking[ib] = ActiveFlight;

                    break;
                    }
                }
                
                ResultSet rs=stmt.executeQuery("SELECT Origin, Destination, Concat(DepartureDate, \" \",DepartureTime) as DD, concat(ArrivalDate, \" \", ArrivalTime) as AA, SegmentTime, SegmentDistanceKM, Price, Price_ComfortPlus, Price_FirstClass, Seat_comfort_left, Seat_comfortPlus_left, Seat_First_left FROM Flights Where ActiveFlight_id = " +  ActiveFlight+ ";");
                rs.next();
                MyList.add(new FlightObject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            con.close();
        }catch(Exception ee){System.out.println(ee);};
      
       return MyList; 
    }
   
    public static float CancelCustomerFlight(String FlightNo, String BookingNo){
        float Suceeded = 0;
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
            CallableStatement cStmt = con.prepareCall("{call CancelFlight(?,?,?)}");
            cStmt.setInt(1,Integer.parseInt(FlightNo));
            cStmt.setInt(2,Integer.parseInt(BookingNo));
            cStmt.execute();
        
            Suceeded = cStmt.getFloat(3);
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }
    
    public static String FlightItineraryBookingNo(String BookingNo){
        String FI = "";
        int BN = Integer.parseInt(BookingNo);
        int i = 0;
        try{
        Connection con = servercon.getConnection(null, null, null);
         Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select Origin, Destination, Concat(DepartureDate, \" \",DepartureTime) as DD, concat(ArrivalDate, \" \", ArrivalTime) as AA, Flights.ActiveFlight_id from Flights inner join Booked_flight on Booked_flight.ActiveFlight_id = Flights.ActiveFlight_id where Booked_flight.Booking_no ="+ BN +";");
        
        while(rs.next()){
            i++;
            FI = FI + "Flight " + i + ": " + rs.getString(1) + " --> " + rs.getString(2) + " \n";
             FI = FI + "Flight number : " + rs.getString(5) + " \n";
            FI = FI + "Departure : " + rs.getString(3) + " \n";
            FI = FI + "Arrival   : " + rs.getString(4) + " \n \n";
        }
        con.close();
        }catch(Exception ee){System.out.println(ee);};
        
        return FI;
    }
    
    public static String FlightItineraryFlightNo(){
        int i = 0;
        String FI = "";
        try{
        Connection con = servercon.getConnection(null, null, null);
         Statement stmt=con.createStatement();
        
        while(FlightsBooking[i] != 0){
            ResultSet rs=stmt.executeQuery("Select Origin, Destination, Concat(DepartureDate, \" \",DepartureTime) as DD, concat(ArrivalDate, \" \", ArrivalTime) as AA from Flights where ActiveFlight_id ="+ FlightsBooking[i] +";");
            rs.next();
            FI = FI + "Flight " + (i+1) + ": " + rs.getString(1) + " --> " + rs.getString(2) + " \n";
            FI = FI + "Departure : " + rs.getString(3) + " \n";
            FI = FI + "Arrival   : " + rs.getString(4) + " \n \n";
            i++;
        }
        con.close();
        }catch(Exception ee){System.out.println(ee);};
        
        return FI;
    }
    
    public static String GetPriceFlights(String Class){
        int i = 0;
        Float Pr = 0f;
        String Price = "";
        String Query = "";
        if(Class.equalsIgnoreCase("Comfort")){
            Query = "Select Price from Flights where ActiveFlight_id =";
        }
        if(Class.equalsIgnoreCase("Comfort+")){
            Query = "Select Price_ComfortPlus from Flights where ActiveFlight_id =";
        }
        if(Class.equalsIgnoreCase("First class")){
            Query = "Select Price_FirstClass from Flights where ActiveFlight_id =";
        }
        try{
        Connection con = servercon.getConnection(null, null, null);
         Statement stmt=con.createStatement();

        while(FlightsBooking[i] != 0){
            ResultSet rs=stmt.executeQuery(Query + FlightsBooking[i] +";");
            rs.next();
            Pr = Pr + rs.getFloat(1);
            i++;
        }
          Price = String.valueOf(Pr);
        con.close();
        }catch(Exception ee){System.out.println(ee);};
        
        return Price;
    }
    
    public static int BookFlights(String TC){
        int BookingNo = 0;
        int i = 0;
        String Query = "";
        String Seats = "";
        float TotalPrice = 0f;
        
        if(TC.equalsIgnoreCase("Comfort")){
            Query = "Select Price from Flights where ActiveFlight_id =";
            Seats = "Seat_Comfort_Left";
        }
        if(TC.equalsIgnoreCase("Comfort+")){
            Query = "Select Price_ComfortPlus from Flights where ActiveFlight_id =";
            Seats = "Seat_ComfortPlus_Left";
        }
        if(TC.equalsIgnoreCase("First class")){
            Query = "Select Price_FirstClass from Flights where ActiveFlight_id =";
            Seats = "Seat_First_Left";
        }
         try{
            Connection con = servercon.getConnection(null, null, null);
            Statement stmt=con.createStatement();
            CallableStatement cStmt = con.prepareCall("{call MakeBookingID(?,?)}");
            cStmt.setInt(1,CustomerID);
            cStmt.execute();
            BookingNo = cStmt.getInt(2);
            
            while(FlightsBooking[i] != 0){  
                ResultSet rs=stmt.executeQuery(Query + FlightsBooking[i] +";");  
                rs.next();
                float Price = rs.getFloat(1);
                TotalPrice = TotalPrice + Price;
                stmt.executeUpdate("UPDATE Flights SET " + Seats +" =(" + Seats + "+1) WHERE ActiveFlight_id = " + FlightsBooking[i] + ";");
                stmt.executeUpdate("insert into Booked_flight(ActiveFlight_id, Booking_no, Cancelled, Price_flight, Travel_class) value (" + FlightsBooking[i] + "," + BookingNo + ",0," + Price + ",'" + TC +"');");
                i++;
            }        
        stmt.executeUpdate("UPDATE bookings SET Total_price = " + TotalPrice + "Where Booking_no = " + BookingNo + " AND Customer_id =" + CustomerID + ";");       
        con.close();
        }catch(Exception ee){System.out.println(ee);};
        return BookingNo;
    }
    
    //********************************************************** Employees ********************************************************************
    
    public static boolean CancelFlight(String FlightNo){
         Boolean Suceeded = false;
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
            CallableStatement cStmt = con.prepareCall("{call DeleteFlight(?)}");
            cStmt.setInt(1,Integer.parseInt(FlightNo));
            cStmt.execute();
            Suceeded = true;
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }//end CancelBooking
    
    public static boolean AddAirport(String AirportID, String AirportName,String City, String Country, String Lat, String Long, String ALT, String UTC){
        Boolean Suceeded = false;
        try{
            Connection con = servercon.getConnection(null, null, null);
            Statement stmt=con.createStatement();
            stmt.executeUpdate("INSERT INTO Airports VALUES('" + AirportID + "','"+ AirportName +"','"+City+"','"+Country +"','"+Lat+"','"+Long+"','"+ALT+"','"+UTC+"');");
            Suceeded = true;
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }
    
    public static boolean AddFlight(String Origin, String Destination, String Distance, String Aircraft, String Price, String DepartureDate, String DepartureTime, String ArrivalDate, String ArrivalTime , String TotalTime){
        Boolean Suceeded = false;
        try{
            float priceFloat = Float.parseFloat(Price);
            Connection con = servercon.getConnection(null, null, null);
            Statement stmt=con.createStatement();
            CallableStatement cStmt = con.prepareCall("{call AddFlight(?,?,?,?,?,?,?,?,?,?)}");
            cStmt.setString(1,Origin);
            cStmt.setString(2,Destination);
            cStmt.setString(3,Distance);
            cStmt.setString(4,Aircraft);
            cStmt.setFloat(5, priceFloat);
            cStmt.setString(6,DepartureDate);
            cStmt.setString(7,DepartureTime);
            cStmt.setString(8,ArrivalDate);
            cStmt.setString(9,ArrivalTime);
            cStmt.setString(10,TotalTime);
            cStmt.execute();

            Suceeded = true;
        con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }

    public static ObservableList<CustomerObject> LookUpCustomersOnFlight(String FlightNo){
        ObservableList<CustomerObject> MyList = null;
        MyList = FXCollections.observableArrayList();
       
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select guest_customers.Customer_name, booked_flight.Travel_class, guest_customers.Customer_Phone,guest_customers.Member_id from booked_flight \n" +
                     "inner join bookings on booked_flight.Booking_no = bookings.Booking_no inner join guest_customers ON bookings.Customer_id = guest_customers.Customer_id\n" +
                     "where  booked_flight.Cancelled = false AND booked_flight.ActiveFlight_id ="+ FlightNo +";");
            //work on resultset
             while(rs.next()){
                MyList.add(new CustomerObject(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)));
            }
             
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return MyList;
    } 
    
    public static ObservableList<EmployeeObject> LookUpEmployeesOnFlight(String FlightNo){
        ObservableList<EmployeeObject> MyList = null;
        MyList = FXCollections.observableArrayList();
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select employees.Employee_id, employees.Employee_name, employees.Employee_function from Employees \n" +
                    "inner join work_on_flight ON employees.Employee_id = work_on_flight.Employee_id\n" +
                    "where work_on_flight.ActiveFlight_id =" + FlightNo +";");
            while(rs.next()){
                MyList.add(new EmployeeObject(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return MyList;
    } 
    
    public static float TotalSalesOfFLight(String FlightNo){
        float TotalSales = 0;
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select sum(Price_flight) from booked_flight where ActiveFlight_id =" + FlightNo +";");
            //work on resultset
            rs.next();
            TotalSales = rs.getFloat(1);
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return TotalSales;
    }
    
    public static ObservableList<LiveInfo> LiveInfoFlights(String Airport){
        ObservableList<LiveInfo> MyList = null;
        MyList = FXCollections.observableArrayList();
        try{
             Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select ActiveFlight_id, Origin, Destination, Concat(DepartureDate, ' ' ,DepartureTime) AS Departure, Concat(ArrivalDate,' ', ArrivalTime) AS Arrival, DelayStart, DelayEnd "
                     + "from flights where (Origin = '"+ Airport +"' OR Destination = '"+ Airport +"') AND cancelled = false AND Concat(DepartureDate, ' ' ,DepartureTime) >= CURDATE() ORDER BY Departure ASC;");
            while(rs.next()){
                String ActiveFlight_id= Integer.toString(rs.getInt(1));
                String Origin = rs.getString(2);
                String Destination = rs.getString(3);
                String departure = rs.getString(4);
                String arrival = rs.getString(5);
                int DelayStart = rs.getInt(6);
                int DelayEnd = rs.getInt(7);
                
                String inout;
                String fromto;
                String delayed;
                
                if(Origin.equals(Airport)){
                    inout = "Outbound";
                    fromto = Destination;
                }else{
                    inout = "Inbound";
                    fromto = Origin;
                }
                if(DelayStart == 0 && DelayEnd == 0){
                    delayed = "No delay";
                }else{
                    delayed = "Start: " + DelayStart + " ,End: " +DelayEnd;
                }
                MyList.add(new LiveInfo(inout, ActiveFlight_id, departure,arrival, fromto,delayed));
            }
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return MyList;
    } 
    
    public static String[] InfoFlightsUpdate(String Flgihtno){
        String[] Results = new String[4];
        try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT DelayStart, DelayEnd, price from Flights WHERE ActiveFlight_id = '" +Flgihtno+ "';");
            rs.next();
            Results[1] = rs.getString(1);
            Results[2] = rs.getString(2);
            Results[3] = String.valueOf(rs.getFloat(3));
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Results;
    }
    
    public static boolean UpdateFlight(String FlightNo, int Delaystart, int delayend, String Price){
        Boolean Suceeded = false;
        try{
        Connection con = servercon.getConnection(null, null, null);
        CallableStatement cStmt = con.prepareCall("{call UpdateFlight(?,?,?,?)}");
        cStmt.setInt(1,Integer.parseInt(FlightNo));
        cStmt.setInt(2,Delaystart);
        cStmt.setInt(3,delayend);
        cStmt.setFloat(4,Float.parseFloat(Price));
     
        cStmt.execute();
        Suceeded = true;
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }
    
    public static boolean DeleteEmployeeFromFlight(String FlightNo, String EmpID){
         Boolean Suceeded = false;
        try{
            Connection con = servercon.getConnection(null, null, null);
            Statement stmt=con.createStatement();
            stmt.executeUpdate("Delete from Work_on_flight WHERE ActiveFlight_id ='"+ FlightNo +"' AND Employee_id ='"+ EmpID +"';");
            Suceeded = true;
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }
    
    public static boolean ADDEmployeeFromFlight(String FlightNo, String EmpID){
         Boolean Suceeded = false;
        try{
            Connection con = servercon.getConnection(null, null, null);
            Statement stmt=con.createStatement();
            stmt.executeUpdate("insert into Work_on_flight Value("+ Integer.parseInt(FlightNo) +",'"+ EmpID+"');");
            Suceeded = true;
            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Suceeded;
    }

//*********************************************************************** helper classes *********************************************

    public static String[] FromFlightCbo(){
        String[] Results = new String[100];
        int i = 0;
        try{
            Connection con = servercon.getConnection(null, null, null);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select distinct Origin from Flights;");
            while(rs.next()){
                Results[i] = rs.getString(1);
                i++;
            }

            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Results;
    }
    
    public static String[] ToFlightCbo(){
        String[] Results = new String[100];
        
        int i = 1;
         try{
            Connection con = servercon.getConnection(null, null, null);
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("Select distinct Destination from Flights;");
             while(rs.next()){
                 Results[i] = rs.getString(1);
                 i++;
             }
             con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Results;
    }
    
    public static String[] GetAircrafts(){
        String[] Results = new String[100];
        int i = 0;
        try{
            Connection con = servercon.getConnection(null, null, null);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select distinct Aircraft_id from Aircraft;");
            while(rs.next()){
                Results[i] = rs.getString(1);
                i++;
            }

            con.close();
        }catch(Exception ee){System.out.println(ee);};
        return Results;
    }
    
    public static void EmptyFlightsBooking(){
        FlightsBooking = null;
        FlightsBooking = new int[6];
    }
    public static int getMemberID(){
        return MemberID;
    }
    public static void SetMemeberID(int i){
        MemberID = i;
    }
    public static int getCustomerID(){
        return CustomerID;
    }
    public static void EmptyFlightBooking(){
        for(int i =0; i<30; i++){
            FlightsBooking[i] = 0;
        }
    }
}