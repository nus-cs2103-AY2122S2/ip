public class Event extends Task {

    String time;

    Event(String time, String d) {
        super(d);
        this.time = time;
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[E][" + tempStr + "] " + this.description + "(" + this.time + ")";
    }
}

