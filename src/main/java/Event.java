public class Event extends Task{
    public String duration;

    public Event(String title, String duration) {
        super(title);
        this.duration = duration;
    }
}
