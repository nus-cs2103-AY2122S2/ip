public class EventTask extends Task {
    String preposition;
    String time;

    EventTask(String name,String preposition, String time) {
        super(name);
        this.preposition = preposition;
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + String.format(" (%s: %s)",this.preposition, this.time);
    }
}
