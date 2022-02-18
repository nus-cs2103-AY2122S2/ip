package duke.parser;

import duke.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import duke.commands.Find;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

/**
 * Deals with making sense of the user command.
 * What are the commands you will face? This will just abstract the whole
 * if-else block from Duke.java.
 */
public class Parser {

    public static String LINE_BREAK = "---------------";
    public static String BYE = LINE_BREAK + "\n Byeeeee, come back again ah!\n" + LINE_BREAK;
    public static String LINE_INTRO = "Nah, here's your list";
    public static String MARK_MESSAGE = "Power la Mr Bosssssss, mark alr bro!";
    public static String UNMARK_MESSAGE = "No probs bro, unmarked already!";

    // is this the most updated task arrayList?
    private TaskList tasks;

    /**
     * Constructor
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Determines if input string is date or not.
     * 
     * @param date
     * @return boolean
     */
    public boolean isDate(String date) {
        try {
            LocalDate.parse(date);
            System.out.println("we have a real date");
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Handles all the possible commands from the CLI.
     * 
     * @param input from CLI
     * @return a string value of the command
     * @throws DukeException
     * @throws IOException
     */
    public String scanInput(String input) throws DukeException, IOException {
        String[] command = input.split(" ");

        int counter = 0;

        if (command[0].equals("bye")) {
            String filePath = "data/duke.txt";
            // this will update the duke.txt file
            Storage.updateDukeTxt(filePath, tasks.getTasks());
            System.out.println(BYE);
            return "bye";
        } else if (command[0].equals("list")) {

            System.out.println(LINE_BREAK);
            System.out.println(" " + LINE_INTRO);
            int internalCounter = 1;
            // iterate through the list
            for (Task task : tasks.getTasks()) {
                if (task != null) {
                    System.out.println(" " + internalCounter + ". " + task);
                    ++internalCounter;
                } else {
                    break;
                }
            }
            return "list";
        } else if (command[0].equals("mark")) {

            int number = Integer.parseInt(command[1]) - 1;
            // Task currTask = list[number];
            Task currTask = tasks.getTasks().get(number);
            boolean currState = currTask.getIsDone();
            currTask.setDone(!currState);

            System.out.println(LINE_BREAK);
            System.out.println(" " + MARK_MESSAGE);
            System.out.println(" [X] " + currTask.getDescription());

            return "mark";
        } else if (command[0].equals("unmark")) {
            int number = Integer.parseInt(command[1]) - 1;
            // Task currTask = list[number];
            Task currTask = tasks.getTasks().get(number);
            boolean currState = currTask.getIsDone();
            currTask.setDone(!currState);

            System.out.println(LINE_BREAK);
            System.out.println(" " + UNMARK_MESSAGE);
            System.out.println(" [ ] " + currTask.getDescription());
            return "unmark";
        } else if (command[0].equals("deadline")) {
            // deadline make some cups /by the day after
            if (command.length == 1) {
                DukeException e = new DukeException("bro why la");
                System.err.println(e.getMessage());
            } else {
                String[] deadlineInput = input.split("/by");
                String deadline = deadlineInput[1];
                System.out.println("deadline we got : " + deadline);

                String left = deadlineInput[0];
                String description = left.substring(9, left.length() - 1);
                // create a new deadline

                // checking for date validity
                if (isDate(deadline)) {

                    System.out.println("its a date ");

                    Task newTask = new Deadlines(description, LocalDate.parse(deadline));
                    tasks.addToTasks(newTask);
                    // adding to the array
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out
                            .println("Got it. I added this deadline already bro: \n" + " " + newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                } else {

                    System.out.println("its not a date");

                    // pass normally
                    Task newTask = new Deadlines(description, deadline);
                    tasks.addToTasks(newTask);
                    // adding to the array
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out
                            .println("Got it. I added this deadline already bro: \n" + " " + newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                }
            }

            return "deadline";
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
                // checking for date validity
                if (isDate(deadline)) {

                    Task newTask = new Events(description, LocalDate.parse(deadline));
                    tasks.addToTasks(newTask);
                    // adding to the array
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out.println("Got it. I added this event already bro: \n" + " " +
                            newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                } else {
                    // pass normally
                    Task newTask = new Events(description, deadline);
                    tasks.addToTasks(newTask);
                    // adding to the array
                    ++counter;
                    System.out.println(LINE_BREAK);
                    System.out.println("Got it. I added this event already bro: \n" + " " +
                            newTask.toString() + "\n");
                    System.out.println("Now you have " + counter + " tasks in the list. \n");
                }
            }

            return "event";
        } else if (command[0].equals("todo")) {
            // here we declare the new task to be added (TODO)
            if (command.length == 1) {
                DukeException e = new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println(LINE_BREAK);
                System.out.println(e.getMessage());
            } else {

                Task t = new ToDos(input);
                tasks.addToTasks(t);
                System.out.println(LINE_BREAK);
                System.out.println(" ok added alr bro: " + input);
                ++counter;
            }
            return "todo";
        } else if (command[0].equals("delete")) {
            // deleting a task
            // find the index to be deleted
            int number = Integer.parseInt(command[1]) - 1;
            // task being deleted
            Task beingDeleted = tasks.getTasks().get(number);
            // deleting operation
            tasks.deleteFromTasks(number);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(" " + beingDeleted);
            System.out.println("Now you have " + tasks.getTasks().size() + " in the list.");
            return "delete";
        } else if (command[0].equals("find")) {
            // keyword
            String keyword = command[1];
            Find find = new Find();
            find.findRelevantTasks(keyword, tasks.getTasks());

            return "find";
        } else {
            DukeException e = new DukeException("Tak faham banggg, speak in my language la bayi....");
            System.out.println(e.getMessage());
            return "error";
        }
    }

}
