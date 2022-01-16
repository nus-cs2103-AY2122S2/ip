import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that executes the main functionality
 */
public class Driver {
    private ArrayList<Task> tasks;
    private final String line = "\t____________________________________________________________";

    /*
     * Constructor that initializes the tasks arraylist
     */
    public Driver() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Executes the instructions given by user
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String command;
        while(true) {
            command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println(line + "\n" + "\tBye. Hope to see you again soon!\n" + line);
                break;
            } else if(command.equals("list")) {
                this.displayTasks();
            } else if(command.split(" ")[0].equals("mark")) {
                this.markAsDone(command.split(" ")[1]);
            } else if(command.split(" ")[0].equals("unmark")) {
                this.unmarkDone(command.split(" ")[1]);
            } else if(!command.equals("")) {
                this.addTask(command);
            }
        }
    }

    private void addTask(String task) {
        String[] taskString = task.split(" ",2);
        String type = taskString[0];
        try {
            Task t = null;g
            if (type.equals("todo")) t = new ToDo(taskString[1]);
            else if (type.equals("deadline")) {
                String[] descriptionAndDate = taskString[1].split(" /by ", 2);
                t = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
            } else if (type.equals("event")) {
                String[] descriptionAndTime = taskString[1].split(" /at ", 2);
                t = new Event(descriptionAndTime[0], descriptionAndTime[1]);
            }
            if (t == null) {
                System.out.println(line + "\n\tPlease add a valid Task type\n" + line);
            } else {
                this.tasks.add(t);
                System.out.println(line + "\n\tGot it. I've added this task:\n\t\t" + t.toString() + "\n\t" +
                        "Now you have " + tasks.size() + " in the list.\n" + line);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(line + "\n\tPlease input a valid Task command\n" + line);
        }
    }

    private void displayTasks() {
        System.out.println(line + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + tasks.get(i).toString());
        }
        System.out.println(line);
    }

    private void markAsDone(String in) {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size()) {
                System.out.println(line + "\n\tTask number: " + index + " does not exist" + "\n" + line);
            } else {
                tasks.get(index - 1).setDone(true);
                System.out.println(line + "\n\tNice! I've marked this task as done:\n\t\t" + tasks.get(index - 1).toString() + "\n" + line);
            }
        } catch (NumberFormatException e) {
            System.out.println(line + "\n\t Please input a valid Task number" + "\n" + line);
        }
    }

    private void unmarkDone(String in) {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size()) {
                System.out.println(line + "\n\t Task number: " + index + " does not exist" + "\n" + line);
            } else {
                tasks.get(index - 1).setDone(false);
                System.out.println(line + "\n\tOK, I've marked this task as not done yet:\n\t\t" + tasks.get(index - 1).toString() + "\n" + line);
            }
        } catch (NumberFormatException e) {
            System.out.println(line + "\n\t Please input a valid Task number" + "\n" + line);
        }
    }
}
