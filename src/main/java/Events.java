import java.time.LocalDate;

public class Events extends Task {

    public LocalDate date;

    public Events (String s, String time){
        super(s);
        this.date = LocalDate.parse(time);
        super.date = this.date;
    }

    @Override
    public String show(){
        if(super.done){
            return "[E][X] " + s + " (at: " + date + ")";
        }
        else{
            return "[E][ ] " + s + " (at: " + date + ")";
        }
    }

    @Override
    public String storeFormat(){
        return "E|" + super.done + "|" + super.s + "|" + this.date + "\n";
    }

}
