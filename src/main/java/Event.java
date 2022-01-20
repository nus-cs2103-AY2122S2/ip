public class Event extends Task{

    protected String date;

    public Event(String desc, boolean isComp, String date){
        super(desc, isComp);
        this.date = date;
    }

    @Override
    public String toString(){
        String temp = "[E] " + super.toString() + " (at: " + date + ")";
        return temp;
    }
}
