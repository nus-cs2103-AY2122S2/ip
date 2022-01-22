import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    /**
     * Enum of different types of tasks.
     */
    private enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    private List<Task> lst = new ArrayList<Task>(); // a list to collect tasks

    /**
     * Print the msg between line breaks.
     *
     * @param msg A string of message to be printed.
     */
    private void printMsg(String msg) {
        String br = "\t____________________________________________________________\n";
        System.out.println(br + msg + br);
        return;
    }

    /**
     * Print out the greeting words.
     */
    private void greeting() {
        this.printMsg("\t Hello! I'm Duke\n\t What can I do for you?\n");
    }

    /**
     * Print out the goodbye words.
     */
    private void bye() {
        this.printMsg("\t Bye. Hope to see you again soon!\n");
    }

    /**
     * List out the current tasks as well as their status.
     */
    private void list() {
        String msg = "";
        for (int i = 1; i <= lst.size(); i++) {
            msg += "\t " + i + "." + lst.get(i-1).toString() + "\n";
        }
        this.printMsg("\t Here are the tasks in your list:\n" + msg);
    }

    /**
     * Add a task to the list.
     * A task can be one of 'todo', 'event' or 'deadline', whilst 'event'
     * and 'deadline' have a time specified after the keyword '/at' or '/by'.
     *
     * @param input The user input of adding a task. 
     */
    private void addTask(TaskType type, String input) {
        String description;
        String time;
        int timeIndex;
        Task t;

        if (type == TaskType.TODO) {
            t = new Todo(input.substring(5));
        } else if (type == TaskType.EVENT) {
            timeIndex = input.indexOf(" /at ");
            description = input.substring(6, timeIndex);
            time = input.substring(timeIndex + 5);
            t = new Event(description, time);
        } else { // deadline
            timeIndex = input.indexOf(" /by ");
            description = input.substring(9, timeIndex);
            time = input.substring(timeIndex + 5);
            t = new Deadline(description, time);
        }
        this.lst.add(t);
        this.printMsg("\t Got it. I've added this task:\n\t   " + t.toString()
                + "\n\t Now you have " + this.lst.size() + " tasks in the list.\n");
    }

    /**
     * Mark a particular task as done.
     *
     * @param num The number of the task to be marked.
     */
    private void mark(int num) {
        this.lst.get(num).markAsDone();
        this.printMsg("\t Nice! I've marked this task as done:\n\t   " + lst.get(num).toString() + "\n"); 
    }

    /**
     * Unmark a particular task as done.
     *
     * @param num The number of the task to be unmarked.
     */
    private void unmark(int num) {
        this.lst.get(num).unmarkAsDone();
        this.printMsg("\t OK, I've marked this task as not done yet:\n\t   " + lst.get(num).toString() + "\n");
    }

    /**
     * Give the bot intructions and let it do the corresponding job.
     */
    private void run() {
        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        String[] splitted = input.split("\\s+");
        if (input.equals("bye")) { // bye
            this.bye();
            return;
        } else if (input.equals("list")) { // list
            this.list();
        } else if (splitted.length == 2 && splitted[0].equals("mark")) { // mark
            try {
                int num = Integer.parseInt(splitted[1]);
                this.mark(num-1);
            } catch (NumberFormatException e) {
                this.printMsg("\t Opps! Pls check your numbers.\n");
            } catch (IndexOutOfBoundsException e) {
                this.printMsg("\t Opps! The item you wanna mark is out of bounds.\n");
            }
        } else if (splitted.length == 2 && splitted[0].equals("unmark")) { // unmark
            try {
                int num = Integer.parseInt(splitted[1]);
                this.unmark(num-1);
            } catch (NumberFormatException e) {
                this.printMsg("\t Opps! Pls check your numbers.\n");
            } catch (IndexOutOfBoundsException e) {
                this.printMsg("\t Opps! The item you wanna unmark is out of bounds.\n");
            }
        } else if (splitted[0].equals("todo")) {
            this.addTask(TaskType.TODO, input);
        } else if (splitted[0].equals("event")) {
            this.addTask(TaskType.EVENT, input);
        } else if (splitted[0].equals("deadline")) {
            this.addTask(TaskType.DEADLINE, input);
        } else {
            // TODO: handle exceptions
        }
        this.run();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();

        bot.greeting();
        bot.run();
    }
}
