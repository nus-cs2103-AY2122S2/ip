/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Conan class.
 */

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Conan {

    private static final String PATH_TASK_FILE = "taskList.txt";

    // GREETING variable contains the introductory greetings.
    private static final String GREETING = "Hello There, My name is Conan! \n"
            + "Hope you're doing fine today! (^_^) \n"
            + "I'm a task manager, and I can help you keep up with your tasks.\n"
            + "Now before we start, lets get acquainted! Lets start with our names!";

    // SEPARATOR helps us distinguish between user commands and the task managers comments.
    private static final String SEPARATOR = "------------------------------------------------";

    // ASK variable asks the user for tasks.
    private static final String ASK = "Please let me know if there's anything else you would like to add, ";

    // BYE variable stores bye, which recognises user exit command.
    private static final String BYE = "BYE";

    //DELETE variable stores delete command.
    private static final String DELETE = "delete";

    // LIST variable store the list command
    private static final String LIST = "LIST";

    // FAREWELL variable store the remaining goodbye message.
    private static final String FAREWELL = "\nHope I helped you complete your tasks!\n"
            + "Have a great day ahead, enjoy ! (^-^)/\n"
            + "Hope to see you next time! ";

    // space
    private static final String SPACE = " ";

    // MARK stores the command mark.
    private static final String MARK = "mark";

    // UNMARK stores the command unmark.
    private static final String UNMARK = "unmark";

    // starting index of a list or a char in string.
    private static final int START_INDEX = 0;

    // Stores the command TODO.
    private static final String TODO = "TODO";

    // Stores the command DEADLINE.
    private static final String DEADLINE = "DEADLINE";

    // Stores the command EVENT.
    private static final String EVENT = "EVENT";

    // the user input when they want to continue with the previous task list.
    private static final String YES = "yes";

    // the user input when they want don't want to continue with the previous task list.
    private static final String NO = "no";

    // Stores the display output that keeps track of number of tasks.
    private static final String NUM_TASKS= "Number of tasks up to now: ";

    // used as a temporary value placeholder.
    private static final String EMPTY_STR_FILLER = "";

    // used to differentiate between constructors
    private static final int DUMMY_VARIABLE = 1;

    // username is an instance variable that stores the name of the user.
    private String username;

    // tasks store the list of all tasks given by the user.
    private final ArrayList<Task> tasks;

    // stores a file object.
    private final File file;

    // taskFileExists checks if the file already exists in the harddrive.
    private final boolean taskFileExists;

    // stores the previous tasks, if exists
    private final ArrayList<String> previousTasks;

    /**
     * Constructor returns a new Conan object.
     * returns a new Conan instance.
     */
    Conan() {

        System.out.println(SEPARATOR);
        System.out.println(GREETING);

        System.out.println(SEPARATOR);
        this.username = EMPTY_STR_FILLER;
        this.tasks = new ArrayList<>();
        this.file = new File(PATH_TASK_FILE);
        this.taskFileExists = this.file.exists();
        this.previousTasks = new ArrayList<>();
    }

    /**
     * updates the username, returns true if there was a simiar user previously.
     * @param username the name of the user.
     */
    boolean tellName(String username) {

        try {
            boolean isSimilarUser = false;

            String previousUser = EMPTY_STR_FILLER;

            if (this.taskFileExists) {
                Scanner sc = new Scanner(this.file);

                while (sc.hasNextLine()) {
                    String str = sc.nextLine();
                    this.previousTasks.add(str);
                }

                // the firstline of the file contains the username.
                previousUser = this.previousTasks.remove(START_INDEX);
            }

            this.username = username.trim();

            System.out.println(SEPARATOR);

            if (!previousUser.equalsIgnoreCase(username)) {
                System.out.println("Hello, " + this.username + "!, Nice to meet you! (*^_^*)");
                System.out.println("So, tell me what would you like to do " + this.username);
            } else {
                System.out.println("I have found out that there was a similar user in the past under the name :");
                System.out.println(this.username);
                System.out.println("If this is you, would you like to continue from the previous tasks ?");
                System.out.println("If this isn't you or you don't want to use the previous tasks, please type no");
                isSimilarUser = true;
            }

            System.out.println(SEPARATOR);
            return isSimilarUser;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println(SEPARATOR);
            return false;
        }
    }

    /**
     * checks is the user wants to add the previous tasks to list
     * @param userInput the userInput
     * @return true is successful, false if the user typed invalid input.
     */
    boolean continueFromLastTime(String userInput) {

        boolean isSuccessful = true;

        try {
            userInput = userInput.trim();

            switch (userInput.toLowerCase()) {
            case YES:
                break;
            case NO:
                break;
            default:
                throw new InvalidYesOrNoException(userInput);
            }

            if (userInput.equalsIgnoreCase(YES)) {
                addPreviousTask();
            }
            System.out.println(ASK + this.username);
            System.out.println(SEPARATOR);

        } catch (InvalidYesOrNoException e) {
            System.out.println(SEPARATOR);
            System.out.println(e.toString());
            isSuccessful = false;

        } finally {

            return isSuccessful;
        }

    }

    /**
     * adds the users previous tasks.
     */
    private void addPreviousTask() {
        for (String task: this.previousTasks) {
            if (Todo.isTodo(task)) {
                this.tasks.add(new Todo(task, DUMMY_VARIABLE));
            } else if (Deadline.isDeadline(task)) {
                this.tasks.add(new Deadline(task, DUMMY_VARIABLE));
            } else if (Event.isEvent(task)) {
                this.tasks.add(new Event(task, DUMMY_VARIABLE));
            } else {
                this.tasks.add(new Task(task, DUMMY_VARIABLE));
            }
        }
    }

    /**
     * echoes the message given by user.
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
     * bids user farewell and returns a trigger to end the program.
     * @return CarryOn.STOP to end the program.
     */
    private CarryOn bye() {
        saveTask();
        System.out.println("Goodbye, " + this.username + FAREWELL);
        System.out.println(SEPARATOR);
        return CarryOn.STOP;
    }

    /**
     * gets the message from user and passes on to other
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
     * adds a task to the list of tasks to be performed.
     * @param text the task to be added.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throws MissingTimeArgumentException if the user missed time argument out.
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
        System.out.println(this.username + ", I have added " + task.getTask());
        System.out.println(NUM_TASKS + tasks.size());
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;

    }

    /**
     * marks a task as done.
     * @param message the user command.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    private CarryOn mark(String message) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if (num <= this.tasks.size() && num > 0) {
            this.tasks.get(num - 1).markDone();
            System.out.println("Great job, on completing this task! \\(^_^)/");
            System.out.println(tasks.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * marks a task as not done.
     * @param message the user command.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    private CarryOn unmark(String message) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if(num > 0 && num <= this.tasks.size()) {
            this.tasks.get(num - 1).unMarkDone();
            System.out.println("Sure, I have unmarked this task :");
            System.out.println(tasks.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * removes a task from the list.
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
     * stores the tasks to harddrive, when the user exits the program.
     */
    private void saveTask() {
        try {

            //File file = new File(PATH_TASK_FILE);

            boolean noPreviousFile = this.file.createNewFile();

            String content = this.username + "\n";

            for (Task task : this.tasks) {
                content += task.toString() + "\n";
            }

            FileWriter writer = new FileWriter(PATH_TASK_FILE);
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            System.out.println("Error...");
            System.out.println(e);
        }

    }

    /**
     * shows all the tasks that are currently in the list of tasks.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     */
    CarryOn displayTasks() {

        System.out.println("These are your Tasks, " + this.username);
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
