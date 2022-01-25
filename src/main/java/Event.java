public class Event extends Task{
    public String duration;

    public Event(String title, String duration) {
        super(title);
        this.duration = duration;
    }

    public String toString(){
        if (this.checked) {
            return "[E][X] " + this.title + "(at: " + this.duration + ")";
        } else {
            return "[E][ ] " + this.title + "(at: " + this.duration + ")";
        }
    }
}
