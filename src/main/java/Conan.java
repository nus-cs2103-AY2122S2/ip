/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Conan class.
 */

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Conan {

    // GREETING variable contains the introductory greetings.
    private static final String GREETING = "Hello There, My name is Conan! \n"
            + "Hope you're doing fine today! (^_^) \n"
            + "I'm a task manager, and I can help you keep up with your tasks.\n"
            + "Now before we start, lets get acquainted! Lets start with our names!";

    // SEPARATOR helps us distinguish between user commands and the task managers comments.
    private static final String SEPARATOR = "------------------------------------------------";

    // HELLO variable is used to store Hello greeting.
    private static final String HELLO = "Hello, ";

    // NTMY variable is followed by after the username.
    private static final String NTMY = "!, Nice to meet you! (*^_^*)";

    // INITIAL_ASK stores the text to be displayed at the start of the interaction.
    private static final String INITIAL_ASK = "So, tell me what would you like to do ";

    // ASK variable asks the user for tasks.
    private static final String ASK = "Please let me know if there's anything else you would like to add, ";

    // ADDED variable stores the message displayed after the task is added.
    private static final String ADDED = ", I have added ";

    // BYE variable stores bye, which recognises user exit command.
    private static final String BYE = "BYE";

    //DELETE variable stores delete command.
    private static final String DELETE = "delete";

    // LIST variable store the list command
    private static final String LIST = "LIST";

    // GOODBYE variable stores a farewell message.
    private static final String GOODBYE = "Goodbye, ";

    // FAREWELL variable store the remaining goodbye message.
    private static final String FAREWELL = "\nHope I helped you complete your tasks!\n"
            + "Have a great day ahead, enjoy ! (^-^)/\n"
            + "Hope to see you next time! ";

    // space
    private static final String SPACE = " ";

    // MARK stores the command mark.
    private static final String MARK = "mark";

    // DONE stores the message to be printed when a task is marked done.
    private static final String DONE = "Great job, on completing this task! \\(^_^)/";

    // UNDONE stores the meesage to be printed when a task is unmarked done.
    private static final String UNDONE = "Sure, I have unmarked this task :";

    //
    private static final String DISPLAY = "These are your Tasks, ";

    // UNMARK stores the command unmark.
    private static final String UNMARK = "unmark";

    // starting index of a list or a char in string.
    private static final int START_INDEX = 0;

    // stores the length of date argument.
    private static final int DATE_LENGTH = 10;

    // Stores the command TODO.
    private static final String TODO = "TODO";

    // Stores the command DEADLINE.
    private static final String DEADLINE = "DEADLINE";

    // Stores the command EVENT.
    private static final String EVENT = "EVENT";

    // stores the command for due before.
    private static final String DUE_BEFORE = "due-before";

    // stores the command for due on.
    private static final String DUE_ON = "due-on";

    private static final String NUM_TASKS = "Number of tasks up to now: ";

    private static final String NUM_TASK = "Number of tasks: ";

    // username is an instance variable that stores the name of the user.
    private String username;

    // tasks store the list of all tasks given by the user.
    private final ArrayList<Task> tasks;

    /**
     * Constructor returns a new Conan object.
     * returns a new Conan instance.
     */
    Conan() {

        System.out.println(SEPARATOR);
        System.out.println(GREETING);

        System.out.println(SEPARATOR);
        this.username = "";
        this.tasks = new ArrayList<>();

    }

    /**
     * tellName updates the username.
     * @param username the name of the user.
     */
    void tellName(String username) {
        this.username = username;
        System.out.println(SEPARATOR);
        System.out.println(HELLO + this.username + NTMY);
        System.out.println(INITIAL_ASK + this.username);
        System.out.println(SEPARATOR);
    }

    /**
     * echo function echoes the message given by user.
     * @param message the message given by user.
     * @return CarryOn.NEXT to indicate the user wants to give more tasks.
     */
    private CarryOn echo(String message) {
        System.out.println(message);
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * bye function bids user farewell and returns a trigger to end the program.
     * @return CarryOn.STOP to end the program.
     */
    private CarryOn bye() {
        System.out.println(GOODBYE + this.username + FAREWELL);
        System.out.println(SEPARATOR);
        return CarryOn.STOP;
    }

    /**
     * tell function gets the message from user and passes on to other
     *  function for a suitable response.
     * @param message the message given by user.
     * @return CarryOn.NEXT if the user wants to continue; else CarryOn.STOP.
     */
    CarryOn tell(String message) {

        message = message.trim();
        System.out.println(SEPARATOR);

        try {
            // Checks if the user wants to exit.
            if (message.equalsIgnoreCase(BYE)) {
                return bye();
            }

            // Checks if the user wants to display the tasks
            if (message.equalsIgnoreCase(LIST)) {
                return displayTasks();
            }


            // Primary error handling for commands.
            if (!message.contains(SPACE)) {
                // check if the commands are empty
                // or else its invalid command
                if (message.equalsIgnoreCase(TODO)) {
                    throw new MissingTaskArgumentException(TODO);
                } else if (message.equalsIgnoreCase(DEADLINE)) {
                    throw new MissingTaskArgumentException(DEADLINE);
                } else if (message.equalsIgnoreCase(EVENT)) {
                    throw new MissingTaskArgumentException(EVENT);
                } else if (message.equalsIgnoreCase(MARK)) {
                    throw new MissingTaskArgumentException(MARK);
                } else if (message.equalsIgnoreCase(UNMARK)) {
                    throw new MissingTaskArgumentException(UNMARK);
                } else if (message.equalsIgnoreCase(DELETE)) {
                    throw new MissingTaskArgumentException(DELETE);
                } else if (message.equalsIgnoreCase(DUE_BEFORE)) {
                    throw new MissingTaskArgumentException(DUE_BEFORE);
                } else if (message.equalsIgnoreCase(DUE_ON)) {
                    throw new MissingTaskArgumentException(DUE_ON);
                } else {
                    throw new IllegalCommandException(message);
                }
            }

            String command = message.split(SPACE, 2)[START_INDEX].toLowerCase();

            // check if the command says mark or unmarked.
            switch (command) {
                case MARK:
                    return mark(message);
                case UNMARK:
                    return unmark(message);
                case DELETE:
                    return delete(message);
                case DUE_BEFORE:
                    return dueBefore(message);
                case DUE_ON:
                    return dueOn(message);
            }

            return add(message);
        } catch (IllegalCommandException e) {
            System.out.println(e.toString());
            System.out.println("Please try again");
            System.out.println(SEPARATOR);
            return CarryOn.NEXT;
        }
    }

    /**
     * add function adds a task to the list of tasks to be performed.
     * @param text the task to be added.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throws MissingTimeArgumentException if the user missed time argument out.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    private CarryOn add(String text) {

        String[] split_text = text.split(SPACE, 2);
        String type = split_text[START_INDEX].toUpperCase();
        String message = split_text[1];

        Task task;

        switch (type) {
            case TODO:
                task = new Todo(message);
                break;
            case DEADLINE:
                Deadline.correctArgument(message);
                task = new Deadline(message);
                break;
            case EVENT:
                Event.correctArgument(message);
                task = new Event(message);
                break;
            default:
                task = new Task(text);
                break;
        }

        tasks.add(task);
        System.out.println(this.username + ADDED + task.getTask());
        System.out.println(NUM_TASKS + tasks.size());
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;

    }

    /**
     * mark function marks a task as done.
     * @param message the user command.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    private CarryOn mark(String message) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if (num <= this.tasks.size() && num > 0) {
            this.tasks.get(num - 1).markDone();
            System.out.println(DONE);
            System.out.println(tasks.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * unmark function marks a task as not done.
     * @param message the user command.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    private CarryOn unmark(String message) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if(num > 0 && num <= this.tasks.size()) {
            this.tasks.get(num - 1).unMarkDone();
            System.out.println(UNDONE);
            System.out.println(tasks.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * delete function removes a task from the list.
     * @param message the user command.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    private CarryOn delete(String message) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if (num > 0 && num <= this.tasks.size()) {
            Task task = this.tasks.remove(num - 1);
            System.out.println("The following task has been removed from the list :");
            System.out.println(task);
        } else {
            throw new FaultyTaskNumberException(num);
        }
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * prints a list of tasks that are due before date
     * @param date the date given by the user
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    private CarryOn dueBefore(String date) {

        date = date.substring(DUE_BEFORE.length()).trim();

        DateHandler.checkValidDate(date);

        System.out.println(DISPLAY + this.username);
        int taskNum = 1;

        for (Task task: tasks) {
            if (task.isBefore(date)) {
                System.out.println(taskNum + ". " + task);
                taskNum += 1;
            }
        }

        taskNum -= 1;
        System.out.println(NUM_TASK + taskNum);
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);

        return CarryOn.NEXT;
    }

    /**
     * prints a list of tasks that are due on date
     * @param date the date given by the user
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    private CarryOn dueOn(String date) {

        date = date.substring(DUE_ON.length()).trim();

        DateHandler.checkValidDate(date);

        System.out.println(DISPLAY + this.username);
        int taskNum = 1;

        for (Task task: tasks) {
            if (task.isOnDate(date)) {
                System.out.println(taskNum + ". " + task);
                taskNum += 1;
            }
        }

        taskNum -= 1;

        System.out.println(NUM_TASK + taskNum);
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);

        return CarryOn.NEXT;
    }

    /**
     * displayTasks shows all the tasks that are currently in the list of tasks.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     */
    CarryOn displayTasks() {

        System.out.println(DISPLAY + this.username);
        int taskNum = 1;

        for (Task task: tasks) {
            System.out.println(taskNum + ". " + task);
            taskNum += 1;
        }
        System.out.println(NUM_TASKS + this.tasks.size());
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);

        return CarryOn.NEXT;
    }

}
