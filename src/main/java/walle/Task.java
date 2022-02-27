package walle;

import gui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class task that duke.Walle creates
 */
public class Task {

    public String time = "";
    public String name;
    public String type;
    int number;
    boolean isDone = false;
    boolean isCorrectDateFormat;
    String printFirstAddition = "";

    /**
     * Constructor of task
     *
     * @param name Descriptor of task
     * @param number Number associated with task
     * @param time time associated with task
     * @param type type of task. 'E' for duke.Event, 'T' for Todo and 'D' for duke.Deadline
     * @param isReading flag to check if input is being read from file data
     */
    public Task(String name, int number, String time, String type, boolean isReading) {
        try {
            if (name.equals("")) {
                throw new EmptyDescriptorException();
            }
            this.name = name;
            this.number = number;
            this.type = type;
            this.isCorrectDateFormat = setDate(time, isReading);

            if (!isCorrectDateFormat) {
                printFirstAddition += Ui.WRONG_DATE_FORMAT;
            }

            if (!isReading) {
                printFirstAddition += Ui.printAddThisTask(this);
                Storage.addLineToFile(this.getDataRepresentation());
                printFirstAddition += Ui.printTotalTasks(number);
            }
        } catch (EmptyDescriptorException e) {
            Ui.printEmptyDescriptionException();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets string representation of how task will be formatted into disk
     *
     * @return string representation of how task will be formatted into disk
     */
    public String getDataRepresentation() {
        return String.format("%s---%s---%s---%s\n", this.type, this.isDone, this.name, this.time);
    }

    /**
     * Set date of task to DD MMM YYYY HH:MM format
     *
     * @param input input date of task
     * @param isReading flag to check if data is being read from file or user input
     * @return true if successfully set to specified format. False otherwise
     */
    private boolean setDate(String input, boolean isReading) {
        try {
            if ((this.type.equals("D") || this.type.equals("E")) && input != null) {
                input = input.replaceAll("/", "-");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm");
                LocalDateTime lt = LocalDateTime.parse(input, formatter);
                DateTimeFormatter out = DateTimeFormatter.ofPattern("dd MMM uuuu hh:mm a");
                this.time = lt.format(out);
            }
            return true;
        } catch (DateTimeParseException e) {
            if (!isReading) {
                System.out.println("Note that dates should be in <<DD-MM-YYYY HHMM>> format");
            }
            this.time = input;
            return false;
        }
    }

    /**
     * Marks tasks as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks tasks as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets status of tasks based on if task is done/not done.
     *
     * @return X if task is done and empty string if task is not done
     */
    public String getStatus() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Decrements number associated with the task.
     */
    public void decrementNum() {
        this.number--;
        assert this.number >= 0 : "Invalid: Task has Negative number! (function called - decrementNum())";
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        String s = String.format("%d. [%s] %s\n", number + 1, getStatus(), name);
        return s;
    }
}
