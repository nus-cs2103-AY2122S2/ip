public class Event extends Task {

    String time;

    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    Event(String description, boolean isDone, String time) {
        super(description, isDone);
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

