package airaxel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FlightObject extends AirAxel{
    private final StringProperty FromLoc;
    private final StringProperty TO;
    private final StringProperty Departure;
    private final StringProperty Arrival;
    private final StringProperty Time;
    private final StringProperty Distance;
    private final StringProperty RegPrice;
    private final StringProperty PlusPrice;
    private final StringProperty FirstPRice;
    
    public FlightObject(String from, String to, String dep, String arr, String time, String dis, String reg, String plus, String first){
        this.FromLoc = new SimpleStringProperty(from);
        this.TO = new SimpleStringProperty(to);
        this.Departure = new SimpleStringProperty(dep);
        this.Arrival = new SimpleStringProperty(arr);
        this.Time = new SimpleStringProperty(time);
        this.Distance = new SimpleStringProperty(dis);
        this.RegPrice = new SimpleStringProperty(reg);
        this.PlusPrice = new SimpleStringProperty(plus);
        this.FirstPRice = new SimpleStringProperty(first);
        
    }
    
    public String GetFromLoc(){return FromLoc.get();}
    public String GetTO(){return TO.get();}
    public String GetDeparture(){return Departure.get();}
    public String GetArrival(){return Arrival.get();}
    public String GetTime(){return Time.get();}
    public String GetDistance(){return Distance.get();}
    public String GetRegPrice(){return RegPrice.get();}
    public String GetPlusPrice(){return PlusPrice.get();}
    public String GetFirstPRice(){return FirstPRice.get();}
    
    public void SetFromLoc(String Value){FromLoc.set(Value);}
    public void SetTO(String Value){TO.set(Value);}
    public void SetDeparture(String Value){Departure.set(Value);}
    public void SetArrival(String Value){Arrival.set(Value);}
    public void SetTime(String Value){Time.set(Value);}
    public void SetDistance(String Value){Distance.set(Value);}
    public void SetRegPrice(String Value){RegPrice.set(Value);}
    public void SetPlusPrice(String Value){PlusPrice.set(Value);}
    public void SetFirstPRice(String Value){FirstPRice.set(Value);}
    
    public StringProperty FromLocProperty(){return FromLoc;}
    public StringProperty TOProperty(){return TO;}
    public StringProperty DepartureProperty(){return Departure;}
    public StringProperty ArrivalProperty(){return Arrival;}
    public StringProperty TimeProperty(){return Time;}
    public StringProperty DistanceProperty(){return Distance;}
    public StringProperty RegPriceProperty(){return RegPrice;}
    public StringProperty PlusPriceProperty(){return PlusPrice;}
    public StringProperty FirstPRiceProperty(){return FirstPRice;}
    
    
}
