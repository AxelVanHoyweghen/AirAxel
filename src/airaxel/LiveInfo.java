package airaxel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LiveInfo extends AirAxel{
    private final StringProperty InOut;
    private final StringProperty FlightNo;
    private final StringProperty Departure;
    private final StringProperty Arrival;
    private final StringProperty FromTo;
    private final StringProperty Delayed;
    
    public LiveInfo(String inout, String flightno, String departure, String arrival, String fromto, String delayed){
        this.InOut = new SimpleStringProperty(inout);
        this.FlightNo = new SimpleStringProperty(flightno);
        this.Departure = new SimpleStringProperty(departure);
        this.Arrival = new SimpleStringProperty(arrival);
        this.FromTo = new SimpleStringProperty(fromto);
        this.Delayed = new SimpleStringProperty(delayed);
    }
    
    public String GetInOut(){return InOut.get();}
    public String GetFlightNo(){return FlightNo.get();}
    public String GetDeparture(){return Departure.get();}
    public String GetArrival(){return Arrival.get();}
    public String GetFromTo(){return FromTo.get();}
    public String GetDelayed(){return Delayed.get();}
    
    public void SetInOut(String Value){InOut.set(Value);}
    public void SetFlightNo(String Value){FlightNo.set(Value);}
    public void SetDeparture(String Value){Departure.set(Value);}
    public void SetArrival(String Value){Arrival.set(Value);}
    public void SetFromTo(String Value){FromTo.set(Value);}
    public void SetDelayed(String Value){Delayed.set(Value);}
    
    public StringProperty InOutProperty(){return InOut;}
    public StringProperty FlightNoProperty(){return FlightNo;}
    public StringProperty DepartureProperty(){return Departure;}
    public StringProperty ArrivalProperty(){return Arrival;}
    public StringProperty FromToProperty(){return FromTo;}
    public StringProperty DelayedProperty(){return Delayed;}
}
