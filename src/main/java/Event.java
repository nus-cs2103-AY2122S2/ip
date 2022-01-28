public class Event extends Task{
    protected String place;

    public Event(String d, String place) {
        super(d);
        this.place = place;
        this.type = "E";
    }

    public Event(String d, String done, String place){
        super(d, done);
        this.place = place;
        this.type = "E";
    }

    public String getPlace(){
        return this.place;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + place + ") ";
    }
}
