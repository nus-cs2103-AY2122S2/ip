package duke;

import gui.Output;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class task that duke.Duke creates
 */
public class Task {

    public String time = "";
    public String name;
    public String type;
    int number;
    public static int totalTask = 0;
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
                throw new EmptyDescriptorExceptions();
            }
            this.name = name;
            this.number = number;
            this.type = type;
            isCorrectDateFormat = setDate(time, isReading);

            if (!isCorrectDateFormat) {
                printFirstAddition += Output.WRONG_DATE_FORMAT;
            }

            this.type = type;
            if (!isReading) {
                printFirstAddition += Output.printAddThisTask(this);
                Storage.addLineToFile(this.getDataRepresentation());
                totalTask++;
                printFirstAddition += Output.printTotalTasks();
            }
            else {
                totalTask++;
            }
        }
        catch (EmptyDescriptorExceptions e){
            Output.printEmptyDescriptionException();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets string representation of how task will be formatted into disk
     * @return string representation of how task will be formatted into disk
     */
    public String getDataRepresentation() {
        return String.format("%s---%s---%s---%s\n", this.type, this.isDone, this.name, this.time);
    }

    private boolean setDate(String input, boolean isReading) {
        try {
            if ((this.type.equals("D") || this.type.equals("E")) && input != null) {
                input = input.replaceAll("/", "-");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
                LocalDateTime lt = LocalDateTime.parse(input, formatter);
                DateTimeFormatter out = DateTimeFormatter.ofPattern("MMM dd uuuu hh:mm a");
                this.time = lt.format(out);
            }
            return true;
        }
        catch (DateTimeParseException e){
            if (!isReading)
                System.out.println("Note that dates should be in <<YYYY-MM-DD HHMM>> format");
            this.time = input;
            return false;
        }
    }

    /**
     * Marks tasks as done.
     */
    public void mark(){
        this.isDone = true;
    }

    /**
     * Unmarks tasks as not done.
     */
    public void unmark(){
        this.isDone = false;
    }

    /**
     * Gets status of tasks based on if task is done/not done.
     *
     * @return X if task is done and empty string if task is not done
     */
    public String getStatus() {
        if (this.isDone){
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Decrements number associated with the task.
     */
    public void decrementNum(){
        this.number--;
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
