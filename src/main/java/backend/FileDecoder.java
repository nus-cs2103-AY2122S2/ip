package backend;

import tasks.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileDecoder {
    private final SimpleDateFormat readFormat;
    private final SimpleDateFormat printFormat;

    public FileDecoder() {
        this.readFormat = new SimpleDateFormat("MMM dd yyyy");
        this.printFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * Returns the task equivalent from a task generating string input in pre-saved file
     * @param tasks A task generating string that follows standard format
     * @return Task object containing information from input string
     */
    public Task decode(String tasks) {

        String[] args = tasks.split(" \\| ");
        String taskType = args[0];
        Boolean isDone = args[1].contains("X");
        String taskDescription = args[2];
        String taskDate = null;
        String endTaskDate = null;
        // if String supplied is one with a date
        if (args.length == 4) {
            try {
                System.out.println(args[3]);
                Date dateObject = readFormat.parse(args[3]);
                taskDate = printFormat.format(dateObject);
            } catch (ParseException e) {
                System.out.println(e);
            }
        }

        // if String supplied has 5 args -- type, description, isDone, start, end
        if (args.length == 5) {
            System.out.println("check2");
            try {
                System.out.println(args[3]);
                System.out.println(args[4]);
                Date start = readFormat.parse(args[3]);
                Date end = readFormat.parse(args[4]);
                taskDate = printFormat.format(start);
                endTaskDate = printFormat.format(end);

            } catch (ParseException e) {
                System.out.println(e);
            }
        }

        //generate the right type of task
        Task task = null;
        switch (taskType) {
        case "E":
            task = new Event(taskDescription, taskDate);
            break;
        case "D":
            task = new Deadline(taskDescription, taskDate);
            break;
        case "T":
            task = new Todo(taskDescription);
            break;
        case "W":
            System.out.println("check1");
            task = new DoWithin(taskDescription, taskDate, endTaskDate);
            break;
        }

        // check if the task has been completed
        if (isDone) {
            task.setDone(true);
        }
        return task;
    }
}
