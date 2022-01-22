import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Mnsky {
    private ArrayList<Task> list;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for the Mnsky object.
     */
    public Mnsky() {
         this.ui = new Ui();
         this.storage = new Storage("data/MnskyData.txt");

         try {
             this.list = this.parseStorageData(this.storage.readFromDataFile());
         } catch (MnskyException e) {
             ui.printException(e);
             this.list = new ArrayList<>();
         }

         this.ui.greeting();
    }

    /**
     * Prints out the list of tasks.
     */
    private void printList() {
        if (this.list.size() == 0) {
            System.out.println("[MNSKY presents an empty task list.]");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, this.list.get(i));
            }
        }
    }

    /**
     * Retrieves and returns the value of the index parameter from the input.
     * @param command The name of the command that called this function.
     * @param inputSplit The input, split into an array using space.
     * @return The value of the index parameter.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private int retrieveIndex(String command, String[] inputSplit) throws MnskyException {
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException(command, "index");
        }

        if (!inputSplit[1].matches("\\d+")) {
            throw new MnskyInvalidParameterException(command, "index");
        }

        int index = Integer.parseInt(inputSplit[1]) - 1;

        if (index < 0 || index >= this.list.size()) {
            throw new MnskyInvalidParameterException(command, "index");
        }

        return index;
    }

    /**
     * Marks the task corresponding to the given index parameter as done, if it exists.
     * @param inputSplit The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void mark(String[] inputSplit) {
        int index = this.retrieveIndex("mark", inputSplit);
        this.list.get(index).mark();
        this.ui.printTask(this.list.get(index));
    }

    /**
     * Unmarks the task corresponding to the given index parameter so it is not done, if it exists.
     * @param inputSplit The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void unmark(String[] inputSplit) throws MnskyException {
        int index = this.retrieveIndex("mark", inputSplit);
        this.list.get(index).unmark();
        this.ui.printTask(this.list.get(index));
    }

    /**
     * Adds the given task to the list while printing a message.
     * @param task The task to be added to the list.
     */
    private void addTask(Task task) {
        this.list.add(task);
        this.ui.printAddedTask(task);
    }

    /**
     * Creates a new task by parsing the input.
     * @param input The input string.
     * @throws MnskyException Thrown if the name parameter is missing.
     * @return The new task.
     */
    private Task parseTask(String input) throws MnskyException {
        String[] inputSplit = input.split(" ", 2);
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException("todo", "name");
        }

        String taskName = inputSplit[1];
        return new Task(taskName);
    }

    /**
     * Creates a new deadline (a task with a "by" parameter included) by parsing the input.
     * @param inputSplit The input, split into an array using space.
     * @throws MnskyException Thrown if the name or the by parameter is missing.
     * @return The new deadline.
     */
    private Deadline parseDeadline(String[] inputSplit) throws MnskyException {
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException("deadline", "name");
        }

        int by_index = 1;
        for (; by_index < inputSplit.length; by_index++) {
            if (inputSplit[by_index].equals("/by")) {
                break;
            }
        }

        if (by_index >= inputSplit.length) {
            throw new MnskyMissingParameterException("deadline", "by");
        }

        String deadlineName = String.join(" ", Arrays.copyOfRange(inputSplit, 1, by_index));
        String by = String.join(" ", Arrays.copyOfRange(inputSplit, by_index + 1, inputSplit.length));
        LocalDate byDate = null;
        LocalTime byTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (by_index + 1 < inputSplit.length) {
            byDate = this.parseDate(inputSplit[by_index + 1]);
            if (byDate != null && by_index + 2 < inputSplit.length) {
                byTime = this.parseTime(inputSplit[by_index + 2]);
            } else {
                byTime = this.parseTime(inputSplit[by_index + 1]);
            }
        }

        return new Deadline(deadlineName, by, byDate, byTime);
    }

    /**
     * Creates a new event (a task with an "at" parameter included) by parsing the input.
     * @param inputSplit The input, split into an array using space.
     * @throws MnskyException Thrown if the name or the at parameter is missing.
     * @return The new event.
     */
    private Event parseEvent(String[] inputSplit) throws MnskyException {
        if (inputSplit.length < 2) {
            throw new MnskyMissingParameterException("event", "name");
        }

        int at_index = 1;
        for (; at_index < inputSplit.length; at_index++) {
            if (inputSplit[at_index].equals("/at")) {
                break;
            }
        }

        if (at_index >= inputSplit.length) {
            throw new MnskyMissingParameterException("event", "at");
        }

        String eventName = String.join(" ", Arrays.copyOfRange(inputSplit, 1, at_index));
        String at = String.join(" ", Arrays.copyOfRange(inputSplit, at_index + 1, inputSplit.length));
        LocalDate atDate = null;
        LocalTime atTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (at_index + 1 < inputSplit.length) {
            atDate = this.parseDate(inputSplit[at_index + 1]);
            if (atDate != null && at_index + 2 < inputSplit.length) {
                atTime = this.parseTime(inputSplit[at_index + 2]);
            } else {
                atTime = this.parseTime(inputSplit[at_index + 1]);
            }
        }

        return new Event(eventName, at, atDate, atTime);
    }

    /**
     * Deletes the task corresponding to the given index parameter, if it exists.
     * @param inputSplit The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void delete(String[] inputSplit) throws MnskyException {
        int index = this.retrieveIndex("delete", inputSplit);
        this.ui.printDeletedTask(this.list.get(index));
        this.list.remove(index);
    }

    /**
     * Creates a LocalDate object based on the input string.
     * @param input The input string to create a LocalDate object from
     * @return The created LocalDate object if the input string is in a valid date format, null otherwise.
     */
    private LocalDate parseDate(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Adds all the tasks from the data file into the list.
     */
    private ArrayList<Task> parseStorageData(ArrayList<String> rawTaskList) throws MnskyException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();

            for (String line : rawTaskList) {
                String[] lineSplit = line.split(" ");
                Task nextTask = null;

                if (line.charAt(1) == 'T') {
                    nextTask = this.parseTask(line);
                } else if (line.charAt(1) == 'D') {
                    nextTask = this.parseDeadline(lineSplit);
                } else if (line.charAt(1) == 'E') {
                    nextTask = this.parseEvent(lineSplit);
                }

                if (nextTask != null) {
                    taskList.add(nextTask);

                    if (line.charAt(4) == 'X') {
                        nextTask.mark();
                    }
                }
            }

            return taskList;
        } catch (MnskyException e) {
            throw new MnskyException("[MNSKY is having trouble remembering the previous task list...]\n");
        }
    }

    /**
     * Creates a LocalTime object based on the input string.
     * @param input The input string to create a LocalTime object from
     * @return The created LocalTime object if the input string is in a valid time format, null otherwise.
     */
    private LocalTime parseTime(String input) {
        try {
            return LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses the input and executes the logic depending on the type of input.
     * @return True if the user input "bye" and thus wants to stop talking to the chatbot.
     *          False otherwise.
     */
    public boolean parseInput() {
        String input = this.ui.getInput();
        String[] inputSplit = input.split(" ");

        try {
            switch (inputSplit[0]) {
                case "bye":
                    this.ui.bye();
                    return false;

                case "list":
                    this.printList();
                    break;

                case "mark":
                    this.mark(inputSplit);
                    this.storage.writeToDataFile(this.list);
                    break;

                case "unmark":
                    this.unmark(inputSplit);
                    this.storage.writeToDataFile(this.list);
                    break;

                case "todo":
                    this.addTask(this.parseTask(input));
                    this.storage.writeToDataFile(this.list);
                    break;

                case "event":
                    this.addTask(this.parseEvent(inputSplit));
                    this.storage.writeToDataFile(this.list);
                    break;

                case "deadline":
                    this.addTask(this.parseDeadline(inputSplit));
                    this.storage.writeToDataFile(this.list);
                    break;

                case "delete":
                    this.delete(inputSplit);
                    this.storage.writeToDataFile(this.list);
                    break;

                default:
                    this.ui.printMnsky("...");
            }
        } catch (MnskyException e) {
            this.ui.printException(e);
        }

        this.ui.printMnsky("I can help!");
        return true;
    }

    public static void main(String[] args) {
        Mnsky mnsky = new Mnsky();

        while (mnsky.parseInput()) {}
    }
}
