public class Event extends Task{
    protected String place;

    public Event(String d, String place) {
        super(d);
        this.place = place;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + place + ") ";
    }
}
