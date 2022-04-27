package duke2;// package duke1;

import java.util.Arrays;

import static duke2.Parser.dateFormat;
import static duke2.Parser.timeFormat;

public class Event extends Task {

    protected String event;

    /**
     * Constructor for Event object
     * 
     * @param name
     * @param event
     */
    Event(String name, String event) {
        super(name);
        this.event = event;
    }

    public void createEventFromString(String[] input) {
        for (int i = 1; i < input.length; i++) {
            if (!input[i].equals("(at:")) {
                if (input[i + 1].equals("(at:")) {
                    this.name += input[i];
                    continue;
                }
                this.name += input[i] + " ";
            } else {
                this.event += input[i + 1] + "-" + input[i + 2] + "-" + input[i + 3] + " " + input[i + 4];
                break;
            }
        }
    }

    public void createEventFromCommand(String[] input) {
        if (!Arrays.asList(input).contains("/at:")) {
            this.event = null;
            return;
        }
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("/at:")) {
                String date = input[i + 1];
                String time = input[i + 2];
                System.out.println(date);
                System.out.println(time);
                this.event += dateFormat(date + " " + time);
                break;
            }
            this.name += input[i] + " ";
        }
    }

    /**
     * Returns formatted string of Event object
     * 
     * @return Formatted string for event object
     */
    public String toString() {
        if (done) {
            return "[E][X] " + this.name + " (at: " + this.event + ")";
        }
        return "[E][] " + this.name + " (at: " + this.event + ")";
    }
}