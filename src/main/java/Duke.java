import java.util.*;

import exception.DukeException;

public class Duke {

    /**
     * This Chat version is just supposed to take in the commands,
     * and return them, thats all. Mitran.Di.CHatri. Ton. Udd. Gayi. Laundi.
     */

    public static String LINE_BREAK = "---------------";
    public static String GREETING = "FUNNY FELLA WEIIIIII! \nHow can I help Mr Singhhhhh?";
    public static String BYE = LINE_BREAK + "\n Byeeeee, come back again ah!\n" +
            LINE_BREAK;
    public static String LINE_INTRO = "Nah, here's your list";
    public static String MARK_MESSAGE = "Power la Mr Bosssssss, mark alr bro!";
    public static String UNMARK_MESSAGE = "No probs bro, unmarked already!";

    public static void main(String[] args) {

        // an array of tasks
        // Task[] list = new Task[100];
        int counter = 0;
        System.out.println(GREETING);
        // new Data Structure to hold the tasks
        ArrayList<Task> tasks = new ArrayList<Task>();

        // takes in the incoming prompt
        Scanner sc = new Scanner(System.in);

        // Outputs
        while (true) {

            String input = sc.nextLine();

            // we will check from 0 to the first space
            // this will let us know of the command
            String[] command = input.split(" ");

            if (input.equals("bye")) {
                System.out.println(BYE);
                break;
            } else if (input.equals("list")) {

                System.out.println(LINE_BREAK);
                System.out.println("  " + LINE_INTRO);
                int internalCounter = 1;

                // iterate through the list
                for (Task task : tasks) {
                    if (task != null) {
                        System.out.println(" " + internalCounter + ". " + task);
                        ++internalCounter;
                    } else {
                        break;
                    }
                }
            } else if (command[0].equals("mark")) {

                int number = Integer.parseInt(command[1]) - 1;

                // Task currTask = list[number];
                Task currTask = tasks.get(number);
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + MARK_MESSAGE);
                System.out.println("        [X] " + currTask.getDescription());
            } else if (command[0].equals("unmark")) {
                int number = Integer.parseInt(command[1]) - 1;

                // Task currTask = list[number];
                Task currTask = tasks.get(number);
                boolean currState = currTask.getIsDone();
                currTask.setDone(!currState);

                System.out.println(LINE_BREAK);
                System.out.println("   " + UNMARK_MESSAGE);
                System.out.println("        [ ] " + currTask.getDescription());
            } else if (command[0].equals("deadline")) {
                // deadline make some cups /by the day after
                if (command.length == 1) {
                    DukeException e = new DukeException("bro why la");
                    System.err.println(e.getMessage());
                } else {
                    String[] deadlineInput = input.split("/by");
                    String deadline = deadlineInput[1];
                    String left = deadlineInput[0];
                    String description = left.substring(9, left.length() - 1);
                    // create a new deadline
                    Task newTask = new Deadlines(description, deadline);
                    // adding to the array
                    // list[counter] = newTask;
                    tasks.add(newTask);
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out
                            .println("Got it. I added this deadline already bro: \n" + " " + newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                }
            } else if (command[0].equals("event")) {

                // event project meeting /at Mon 2-4pm
                if (command.length == 1) {
                    DukeException e = new DukeException("OOPS!!! The description of an event cannot be empty.");
                    System.out.println(LINE_BREAK);
                    System.out.println(e.getMessage());
                } else {
                    String[] deadlineInput = input.split("/at");
                    String deadline = deadlineInput[1];
                    String left = deadlineInput[0];
                    String description = left.substring(6, left.length() - 1);
                    // create a new deadline
                    Task newTask = new Events(description, deadline);
                    // adding to the array
                    // list[counter] = newTask;
                    tasks.add(newTask);
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out.println("Got it. I added this event already bro: \n" + " " + newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                }
            } else if (command[0].equals("todo")) {
                // here we declare the new task to be added (TODO)

                if (command.length == 1) {
                    DukeException e = new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(LINE_BREAK);
                    System.out.println(e.getMessage());
                } else {

                    Task t = new ToDos(input);

                    // list[counter] = t;
                    tasks.add(t);
                    System.out.println(LINE_BREAK);
                    System.out.println(" ok added alr bro: " + input);
                    ++counter;
                }
            } else if (command[0].equals("delete")) {
                // deleting a task
                // find the index to be deleted
                int number = Integer.parseInt(command[1]) - 1;
                // task being deleted
                Task beingDeleted = tasks.get(number);
                // deleting operation
                tasks.remove(number);
                System.out.println("Noted. I've removed this task: ");
                System.out.println("  " + beingDeleted);
                System.out.println("Now you have " + tasks.size() + " in the list.");
            } else {
                DukeException e = new DukeException("Tak faham banggg, speak in my language la bayi....");
                System.out.println(e.getMessage());
            }
        }
    }

}
