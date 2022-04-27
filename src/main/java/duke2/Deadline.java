package duke2;// package duke1;

import java.util.Arrays;

import static duke2.Parser.dateFormat;
import static duke2.Parser.timeFormat;

public class Deadline extends Task {
    protected String date;

    /**
     * Constructor for Deadline
     * 
     * @param name
     * @param date
     * @returns new Deadline object
     */
    Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public void createDeadlineFromString(String[] input) {
        for (int i = 1; i < input.length; i++) {
            if (!input[i].equals("(by:")) {
                System.out.println(input[i]);
                if (input[i + 1].equals("(by:")) {
                    this.name += input[i];
                    continue;
                }
                this.name += input[i] + " ";
            } else {
                try {
                this.date += input[i + 1] + "-" + input[i + 2] + "-" + input[i + 3] + " " + input[i + 4];
                break;
            } catch (IndexOutOfBoundsException e) {
                    this.date = null;
                }
            }
        }
    }



    public void createDeadlineFromCommand(String[] input) {

        if (!Arrays.asList(input).contains("/by:")) {
            System.out.println("here");
            this.date = null;
            return;
        }
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("/by:")) {
                String date = input[i + 1];
                String time = input[i + 2];
                this.date = dateFormat(date + " " + time);
                break;
            }
            this.name += input[i] + " ";
        }
    }

    /**
     * Deadline toString()
     * 
     * @return Formatted string for deadline object
     */
    public String toString() {
        if (done) {
            return "[D][X] " + this.name + " (by: " + this.date + ")";
        }
        return "[D][] " + this.name + " (by: " + this.date + ")";
    }
}