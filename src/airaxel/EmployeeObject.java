package airaxel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class EmployeeObject {
    private final StringProperty ID;
    private final StringProperty Name;
    private final StringProperty Function;
    
    public EmployeeObject(String id, String name, String funtion){
        this.ID = new SimpleStringProperty(id);
        this.Name = new SimpleStringProperty(name);
        this.Function = new SimpleStringProperty(funtion);
    }
    
    public String GetID(){return ID.get();}
    public String GetName(){return Name.get();}
    public String GetFunction(){return Function.get();}

    public void SetID(String Value){ID.set(Value);}
    public void SetName(String Value){Name.set(Value);}
    public void SetFunction(String Value){Function.set(Value);}
    
    public StringProperty IDProperty(){return ID;}
    public StringProperty NameProperty(){return Name;}
    public StringProperty FunctionProperty(){return Function;}

    
}
