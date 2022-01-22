import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Class task that Duke creates
 */
public class Task {

    String time;
    String name;
    String type;
    int number;
    static int totalTask = 0;
    boolean isdone = false;

    /**
     * Constructor of task
     *
     * @param name Descriptor of task
     * @param number Number associated with task
     * @param time time associated with task
     * @param type type of task. 'E' for Event, 'T' for Todo and 'D' for Deadline
     */
    public Task(String name, int number, String time, String type, boolean isReading){
        try {
            if (name.equals("")) {
                throw new EmptyDescriptorExceptions();
            }
            this.name = name;
            this.number = number;
            this.type = type;
            setDate(time, isReading);
            this.type = type;
            if (!isReading) {
                System.out.println("Got it. I've added this task: ");
                if (type.equals("D")) {
                    System.out.printf(" [D][%s] %s (by: %s) \n", this.getStatus(), this.name, this.time);
                } else if (type.equals("T")) {
                    System.out.printf(" [T][%s] %s\n", this.getStatus(), this.name);
                } else {
                    System.out.printf(" [E][%s] %s (on: %s) \n", this.getStatus(), this.name, this.time);
                }
                Storage.addLineToFile(this.getDataRepresentation());
                totalTask++;
                System.out.printf("Now you have %d task on the list.\n", Task.totalTask);
            }
            else {
                totalTask++;
            }
        }
        catch (EmptyDescriptorExceptions e){
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getDataRepresentation(){
        return String.format("%s---%s---%s---%s \n", this.type, this.isdone, this.name, this.time);
    }

    private void setDate(String input, boolean isReading){
        try {
            if ((this.type.equals("D") || this.type.equals("E")) && input != null) {
                input = input.replaceAll("/", "-");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
                LocalDateTime lt = LocalDateTime.parse(input, formatter);
                DateTimeFormatter out = DateTimeFormatter.ofPattern("MMM dd uuuu hh:mm a");
                this.time = lt.format(out);
            }
        }
        catch(DateTimeParseException e){
            if (!isReading)
                System.out.println("Note that dates should be in <<YYYY-MM-DD HHMM>> format");
            this.time = input;
        }
    }

    /**
     * Marks tasks as done.
     */
    public void mark(){
        this.isdone = true;
    }

    /**
     * Unmarks tasks as not done.
     */
    public void unmark(){
        this.isdone = false;
    }

    /**
     * Gets status of tasks based on if task is done/not done.
     *
     * @return X if task is done and empty string if task is not done
     */
    public String getStatus(){
        if (this.isdone){
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
    public String toString(){
        String s = String.format("%d. [%s] %s\n", number+1, getStatus(), name);
        return s;
    }
}
