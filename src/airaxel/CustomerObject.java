package airaxel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerObject extends AirAxel{
    private final StringProperty Name;
    private final StringProperty TravelClass;
    private final StringProperty Phone;
    private final StringProperty Member;
    
    public CustomerObject(String name, String TravelClass, String Phone, String Member){
        this.Name = new SimpleStringProperty(name);
        this.TravelClass = new SimpleStringProperty(TravelClass);
        this.Phone = new SimpleStringProperty(Phone);
        this.Member = new SimpleStringProperty(Member);
    }
    
    public String GetName(){return Name.get();}
    public String GetTravelClass(){return TravelClass.get();}
    public String GetPhone(){return Phone.get();}
    public String GetMember(){return Member.get();}
    
    public void SetName(String Value){Name.set(Value);}
    public void SetTravelClass(String Value){TravelClass.set(Value);}
    public void SetPhone(String Value){Phone.set(Value);}
    public void SetMember(String Value){Member.set(Value);}
    
    public StringProperty NameProperty(){return Name;}
    public StringProperty TravelClassProperty(){return TravelClass;}
    public StringProperty PhoneProperty(){return Phone;}
    public StringProperty MemberProperty(){return Member;}
    
    
}
