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
            try {
                command = sc.nextLine();
                if (command.equals("bye")) {
                    System.out.println(line + "\n" + "\tBye. Hope to see you again soon!\n" + line);
                    break;
                } else if (command.equals("list")) {
                    this.displayTasks();
                } else if (command.split(" ")[0].equals("mark")) {
                    this.markAsDone(command);
                } else if (command.split(" ")[0].equals("unmark")) {
                    this.unmarkDone(command);
                } else if (!command.equals("")) {
                    this.addTask(command);
                }
            } catch (DukeException e) {
                System.out.println(line + "\n\t" + e.getMessage() + "\n" + line);
            }
        }
    }

    private void addTask(String task) throws DukeException {
        String[] taskString = task.split(" ",2);
        String type = taskString[0];
        try {
            Task t = null;
            if (type.equals("todo")) t = new ToDo(taskString[1]);
            else if (type.equals("deadline")) {
                String[] descriptionAndDate = taskString[1].split(" /by ", 2);
                t = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
            } else if (type.equals("event")) {
                String[] descriptionAndTime = taskString[1].split(" /at ", 2);
                t = new Event(descriptionAndTime[0], descriptionAndTime[1]);
            } else if(type.equals("list")) {
                throw new DukeWrongInputFormatException("list command should not have any additional arguments");
            }
            if (t == null) {
                throw new DukeNotACommandException(type + " is not a valid Task command");
            } else {
                this.tasks.add(t);
                System.out.println(line + "\n\tGot it. I've added this task:\n\t\t" + t + "\n\t" +
                        "Now you have " + tasks.size() + " in the list.\n" + line);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task command:" +
                    "\n\ttodo <description>\n\tdeadline <description> /by <date>\n\tevent <description> /at <dateAndTime>");
        }
    }

    private void displayTasks() {
        System.out.println(line + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + tasks.get(i).toString());
        }
        System.out.println(line);
    }

    private void markAsDone(String in) throws DukeException{
        try {
            int index = Integer.parseInt(in.split(" ")[1]);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                tasks.get(index - 1).setDone(true);
                System.out.println(line + "\n\tNice! I've marked this task as done:\n\t\t" + tasks.get(index - 1).toString() + "\n" + line);
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number after typing mark: mark <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid mark command: mark <taskNum>");
        }
    }

    private void unmarkDone(String in) throws DukeException {
        try {
            int index = Integer.parseInt(in.split(" ")[1]);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                tasks.get(index - 1).setDone(false);
                System.out.println(line + "\n\tOK, I've marked this task as not done yet:\n\t\t" + tasks.get(index - 1).toString() + "\n" + line);
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number after typing unmark: unmark <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid mark command: unmark <taskNum>");
        }
    }
}
