import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an instance of "Mike" - a simple task tracking bot.
 */
public class Mike {
    private final List<Task> listOfTasks;

    /**
     * Constructor for Mike.
     */
    public Mike() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Calls methods relevant to initialising "Mike".
     */
    void start() {
        printGreeting();
        printStartingInstruction();
        loadStoredList();
    }

    /**
     * Prints a greeting from "Mike" with an accompanying text banner.
     */
    void printGreeting() {
        String textBanner = "" +
                "  _     _   _\n" +
                " | |   (_) (_)\n" +
                " | |__  _   _    __ _ _ __ ___\n" +
                " | '_ \\| | | |  / _` | '_ ` _ \\\n" +
                " | | | | | | | | (_| | | | | | |\n" +
                " |_| |_|_| |_|_ \\__,_|_| |_| |_|\n" +
                "           (_) |\n" +
                "  _ __ ___  _| | _____\n" +
                " | '_ ` _ \\| | |/ / _ \\\n" +
                " | | | | | | |   <  __/\n" +
                " |_| |_| |_|_|_|\\_\\___|\n";
        System.out.println("Welcome!\n" + textBanner);
    }

    /**
     * Prints instructions to guide a user on how to use "Mike".
     */
    void printStartingInstruction() {
        String tip = "**Tip: type bye to end conversation**";
        System.out.println(tip);
    }

    /**
     * Prints a string with line separators to make it
     * visually appealing
     *
     * @param str The string that is to be printed
     */
    void printReply(String str){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: " + str);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints instructions to ask a user for the next command.
     */
    void printNextCommandInstruction() {
        System.out.println("Enter next command:");
    }

    /**
     * Prints a goodbye message from "Mike".
     */
    void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye and see you again :)";
        printReply(goodbyeMessage);
    }

    /**
     * Prints a message informing the user that their input does not contain any characters.
     */
    void printNoCharactersMessage() {
        printReply("Hey! You didn't enter any characters!");
    }

    /**
     * Prints the current lists of tasks created by the user.
     */
    void printList() {
        int counter = 1;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: Behold, your list!");
        for (Task task : listOfTasks) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Processes the user's input and responds accordingly.
     *
     * @param trimmedInputString String input from user passed here from Main class.
     */
    void processInput(String trimmedInputString) {
        String[] splitString = trimmedInputString.split(" ");
            String command = splitString[0];
            try {
                //Todo: ask in forum how to use enum for switch statement when strings are used
                switch (command) {
                case "list":
                    printList();
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(splitString[1]);
                    mark(markIndex);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(splitString[1]);
                    unmark(unmarkIndex);
                    break;
                case "remove":
                    int removeIndex = Integer.parseInt(splitString[1]);
                    removeFromList(removeIndex);
                    break;
                case "todo":
                    String todoParameters =
                            removeCommandFromString(trimmedInputString);
                    addTodo(todoParameters);
                    break;
                case "deadline":
                    String deadlineParameters =
                            removeCommandFromString(trimmedInputString);
                    addDeadline(deadlineParameters);
                    break;
                case "event":
                    String eventParameters =
                            removeCommandFromString(trimmedInputString);
                    addEvent(eventParameters);
                    break;
                default:
                    String invalidCommandMessage =
                            String.format("\n**Mike: I don't understand the command \"%s\"**",
                                    trimmedInputString);
                    throw new UnsupportedOperationException(invalidCommandMessage);
                }
            } catch(UnsupportedOperationException e) {
                e.printStackTrace();
            } catch(StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("**Mike: Hmm, you may have entered the command arguments incorrectly.**");
            } catch(IndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("**Mike: That item number doesn't exist on your list :/**");
            }
        }

    /**
     * Trims the "command" portion out from the input string and returns the remainder of the string.
     * This is a helper function for the processInput method.
     *
     * @param str The input string from the user.
     * @return The input string from the user with the "command" portion removed.
     */
    String removeCommandFromString(String str) {
        int indexOfFirstSpace = str.indexOf(" ");
        int indexOfTaskParameters = indexOfFirstSpace + 1;
        return str.substring(indexOfTaskParameters);
    }

    //TODO: consider making Mike immutable (return Mike with new list each time)
    /**
     * Adds the specified task to the list.
     *
     * @param task The task to be added to the list.
     */
    void addToListWithoutReply(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Adds the specified task to the list, then prints a message to inform the user of this.
     *
     * @param task The task to be added to the list.
     */
    void addToListWithReply(Task task) {
        addToListWithoutReply(task);
        String taskName = task.getTaskName();
        String addTaskOutput = "Added \"" + taskName + "\" to the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        printReply(String.format("%s\n%s", addTaskOutput, noOfTasksOutput));
    }

    /**
     * Removes the task (specified by its index in the list) from the list.
     *
     * @param removeIndex The index of the task to be removed from the list.
     */
    void removeFromList(int removeIndex) {
        Task removedTask = this.listOfTasks.remove(removeIndex - 1);
        String taskName = removedTask.getTaskName();
        String removeTaskOutput = "Removed \"" + taskName + "\" from the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        printReply(String.format("%s\n%s", removeTaskOutput, noOfTasksOutput));
    }

    /**
     * Adds a task of type "To-do" to the list
     *
     * @param str The name of the task to be added to the list.
     */
    void addTodo(String str) {
        Todo todo = new Todo(str);
        addToListWithReply(todo);
    }

    /**
     * Adds a task of type "Deadline" to the list.
     *
     * @param str The name of the task and its deadline in the format "taskName /by deadline".
     */
    void addDeadline(String str) {
        int indexOfBy = str.indexOf("/by");
        int indexOfEndDate = indexOfBy + 4;
        String name = str.substring(0, indexOfBy - 1);
        String endDate = str.substring(indexOfEndDate);
        Deadline deadline = new Deadline(name, endDate);
        addToListWithReply(deadline);
    }

    /**
     * Adds a task of type "Event" to the list.
     *
     * @param str The name of the task and the time of the event in the format "taskName /at eventTime".
     */
    void addEvent(String str) {
        int indexOfAt = str.indexOf("/at");
        int indexOfEventTime = indexOfAt + 4;
        String name = str.substring(0, indexOfAt - 1);
        String scheduledDate = str.substring(indexOfEventTime);
        Event event = new Event(name, scheduledDate);
        addToListWithReply(event);
    }

    /**
     * Marks the task (specified by its index in the list) as "done".
     *
     * @param indexFromUser The index of the task (as seen by the user) to be marked in the list.
     */
    void mark(int indexFromUser) {
        int indexInList = indexFromUser - 1;
        Task oldTask = this.listOfTasks.get(indexInList);
        Task newTask = oldTask.markAsDone();
        this.listOfTasks.set(indexInList, newTask);

        String outputMessage =
                String.format("Great job, \"%s\" marked as done!", newTask.getTaskName());
        printReply(outputMessage);
    }

    /**
     * Marks the task (specified by its index in the list) as "not done".
     *
     * @param indexFromUser The index of the task (as seen by the user) to be unmarked in the list.
     */
    void unmark(int indexFromUser) {
        int indexInList = indexFromUser - 1;
        Task oldTask = this.listOfTasks.get(indexInList);
        Task newTask = oldTask.markAsUndone();
        this.listOfTasks.set(indexInList, newTask);

        String outputMessage =
                String.format("Sure thing, \"%s\" marked as undone!", newTask.getTaskName());
        printReply(outputMessage);
    }

    /**
     * Stores the current list in the hard drive to be accessed on reboot.
     */
    void saveToStoredList() {
        try {
            File file = new File("storedList.txt");
            String storedList = "";
            //Todo: consider using string builder instead
            for (Task task : listOfTasks) {
                storedList += task.convertToStoredListFormat();
                storedList += "\n";
            }
            FileWriter fw = new FileWriter(file);
            fw.write(storedList);
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list from a previous session that is stored in the hard drive.
     */
    void loadStoredList() {
        try {
            File file = new File("storedList.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    break; //Todo: this might cause problems later
                } else {
                    addToListWithoutReply(convertStringToTask(input));
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            saveToStoredList(); //create a new file for storedList
            System.out.println("**storedList.txt not found. " +
                    "New file \"storedList.txt\" has been created for you.**");
        }
    }

    /**
     * Returns a task object after processing a string from storedList.txt.
     *
     * @param input String containing details of the task.
     * @return A task object instantiated with parameters provided by the input string.
     */
    Task convertStringToTask(String input) {
        String[] splitString = input.split("\\|");
        String taskType = splitString[0];
        boolean isDone;
        switch(taskType) {
        case "T":
            isDone = Boolean.parseBoolean(splitString[1]);
            String todoName = splitString[2];
            Todo todo = new Todo(todoName, isDone);
            return todo;
            //break;
        case "D":
            isDone = Boolean.parseBoolean(splitString[1]);
            String deadlineName = splitString[2];
            String endDate = splitString[3];
            Deadline deadline = new Deadline(deadlineName, endDate, isDone);
            return deadline;
            //break;
        case "E":
            isDone = Boolean.parseBoolean(splitString[1]);
            String eventName = splitString[2];
            String eventTime = splitString[3];
            Event event = new Event(eventName, eventTime, isDone);
            return event;
            //break;
        default:
            return new Todo("Fallthrough occurred!!");
            //break;
        }
    }
}
