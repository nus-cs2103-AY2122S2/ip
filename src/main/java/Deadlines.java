import java.time.LocalDate;

public class Deadlines extends Task{

    public LocalDate date;

    public Deadlines(String s, String time) {
        super(s);
        this.date = LocalDate.parse(time);
        super.date = this.date;
    }

    @Override
    public String show(){
        if(super.done){
            return "[D][X] " + s + " (by: " + date + ")";
        }
        else{
            return "[D][ ] " + s + " (by: " + date + ")";
        }
    }

    @Override
    public String storeFormat(){
        return "D|" + super.done + "|" + super.s + "|" + this.date + "\n";
    }
}
