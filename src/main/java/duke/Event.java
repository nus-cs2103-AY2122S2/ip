package duke;

public class Event extends Task {

    Event(String task, String time) {
        super(task, "E", time);
    }

    @Override
    String saveFormat() {
        return super.saveFormat() + " ### " + this.time;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + this.date.format(formatter) + ")";
    }

}
