package main.java;

/**
 * Events is a type of Task, containing extra information.
 */
public class Events extends Task {

    String type = "E";
    String info;

    /**
     * Constructor of Events
     * @param name name of the event.
     * @param info extra info of the event.
     */
    Events(String name, String info) {
        super(name);
        this.info = info;
    }

    @Override
    String display() {
        return "[" +this.type + "] " + "[" +this.done + "] " + this.name + " (at " + info + ")";
    }

}
