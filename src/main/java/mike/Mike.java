package mike;

import java.time.format.DateTimeParseException;

/**
 * Represents an instance of "Mike" - a simple task manager app.
 * This class handles the logic behind managing user input.
 */
public class Mike {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for Mike.
     */
    public Mike() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage("storedList.txt");
        loadStoredList();
    }

    /**
     * Returns a string with a message for the user when no characters are entered.
     *
     * @return String with a message for the user when no characters are entered.
     */
    private String noCharactersEnteredResponse() {
        return ui.printNoCharactersMessage();
    }

    private String printList() {
        return taskList.getListForPrint();
    }

    private String addTaskToList(Task task) {
        return taskList.addToListWithMessage(task);
    }

    private String removeTaskFromList(int removeIndex) {
        return taskList.removeFromListWithMessage(removeIndex);
    }

    /**
     * Adds a task of type "To-do" to the list
     *
     * @param str The name of the task to be added to the list.
     * @return String to be output to the user by Mike.
     */
    private String addTodo(String str) {
        Todo todo = new Todo(str);
        return addTaskToList(todo);
    }

    /**
     * Adds a task of type "Deadline" to the list.
     *
     * @param name The name of the task and its deadline in the format "taskName /by deadline".
     * @param date The date of the deadline represented as a String.
     * @return String to be output to the user by Mike.
     */
    private String addDeadline(String name, String date) {
        try {
            Deadline deadline = new Deadline(name, date);
            return addTaskToList(deadline);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return ("You probably keyed in a deadline with the wrong format\n"
                + "Enter dates in the format yyyy-mm-dd");
    }

    /**
     * Adds a task of type "Event" to the list.
     *
     * @param name The name of the task and the time of the event in the format "taskName /at eventTime".
     * @param scheduledDate The date of the event represented as a String.
     * @return String to be output to the user by Mike.
     */
    private String addEvent(String name, String scheduledDate) {
        try {
            Event event = new Event(name, scheduledDate);
            return addTaskToList(event);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return ("You probably keyed in an event with the wrong format\n"
                + "Enter dates in the format yyyy-mm-dd");
    }

    /**
     * Marks the task (specified by its index in the list) as "done".
     *
     * @param indexFromUser The index of the task (as seen by the user) to be marked in the list.
     * @return String to be output to the user by Mike.
     */
    private String mark(int indexFromUser) {
        return (taskList.markInListWithMessage(indexFromUser));
    }

    /**
     * Unmarks the task (specified by its index in the list) as "done".
     *
     * @param indexFromUser The index of the task (as seen by the user) to be unmarked in the list.
     * @return String to be output to the user by Mike.
     */
    private String unmark(int indexFromUser) {
        return (taskList.unmarkInListWithMessage(indexFromUser));
    }

    private String findInList(String searchWords) {
        String listForPrint = taskList.findTasksInListForPrint(searchWords);
        return (listForPrint);
    }

    /**
     * Processes the user's input then and responds accordingly.
     *
     * @param userInput String input from user.
     * @return String response from Mike.
     */
    protected String processInput(String userInput) {
        Parser parser = new Parser(userInput);

        if (parser.isBlank()) {
            return noCharactersEnteredResponse();
        }
        String message = "-No message-";

        String command = parser.getCommand();
        try {
            switch (command) {
            case "ls":
            case "list":
                message = printList();
                break;
            case "mark":
                int markIndex = parser.getIndex();
                message = mark(markIndex);
                break;
            case "unmark":
                int unmarkIndex = parser.getIndex();
                message = unmark(unmarkIndex);
                break;
            case "remove":
                int removeIndex = parser.getIndex();
                message = removeTaskFromList(removeIndex);
                break;
            case "t":
            case "todo":
                String todoName = parser.getTodoName();
                message = addTodo(todoName);
                break;
            case "d":
            case "deadline":
                String deadlineName = parser.getDeadlineName();
                String deadlineDate = parser.getDeadlineDate();
                message = addDeadline(deadlineName, deadlineDate);
                break;
            case "e":
            case "event":
                String eventName = parser.getEventName();
                String eventDate = parser.getEventDate();
                message = addEvent(eventName, eventDate);
                break;
            case "find":
                String searchWords = parser.getSearchWords();
                message = findInList(searchWords);
                break;
            case "bye":
                System.exit(0);
                break;
            default:
                throw new UnsupportedOperationException("Invalid command, UnsupportedOperationException");
            }
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            String invalidCommandMessage =
                    String.format("I don't understand the command \"%s\"",
                            parser.getUserInput());
            message = invalidCommandMessage;
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            String stringIndexOutOfBoundsMessage =
                    "Hmm, you may have entered the command arguments incorrectly.\n"
                    + "Tip: Enter dates in the format yyyy-mm-dd";
            message = stringIndexOutOfBoundsMessage;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            String indexOutOfBoundsMessage = "That item number doesn't exist on your list :/";
            message = indexOutOfBoundsMessage;
        }
        saveToStoredList();
        return ui.printReply(message);
    }

    /**
     * Stores the current list in the hard drive to be accessed on reboot of Mike.
     */
    private void saveToStoredList() {
        String listInStorageFormat = taskList.convertToStoredListFormat();
        storage.storeList(listInStorageFormat);

    }

    /**
     * Loads the list from a previous session that is stored in the hard drive.
     */
    private void loadStoredList() {
        this.taskList = this.taskList.convertFromStoredList(storage.loadFile());
    }
}
