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
             this.getStorageData();
         } catch (MnskyException e) {
             ui.printException(e);
             this.list = new ArrayList<>();
         }

         this.ui.greeting();
    }

    private void getStorageData() throws MnskyException {
        ArrayList<ArrayList<String>> taskList = Parser.parseStorageData(this.storage.readFromDataFile());
        for (ArrayList<String> task : taskList) {
            Task actualTask = null;
            switch (task.get(0)) {
                case "task":
                    actualTask = this.addTask(task.get(1));
                    break;

                case "event":
                    actualTask = this.addEvent(task.get(1), task.get(2));

                case "deadline":
                    actualTask = this.addDeadline(task.get(1), task.get(2));
                    break;

                default:
                    throw new MnskyException("????");
            }

            if (actualTask != null) {
                if (task.get(3).equals("X")) {
                    actualTask.mark();
                }
            }
        }
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
     * Marks the task corresponding to the given index parameter as done, if it exists.
     * @param index The input, split into an array using space.
     */
    private void mark(int index) {
        this.list.get(index).mark();
        this.ui.printTask(this.list.get(index));
    }

    /**
     * Unmarks the task corresponding to the given index parameter so it is not done, if it exists.
     * @param index The input, split into an array using space.
     */
    private void unmark(int index) {
        this.list.get(index).unmark();
        this.ui.printTask(this.list.get(index));
    }

    private Task addTask(String name) {
        Task task = new Task(name);
        this.list.add(task);
        return task;
    }

    /**
     * Creates a new deadline (a task with a "by" parameter included) by parsing the input.
     * @throws MnskyException Thrown if the name or the by parameter is missing.
     * @return The new deadline.
     */
    private Deadline addDeadline(String name, String by) {
        String[] bySplit = by.split(" ");
        LocalDate byDate = null;
        LocalTime byTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (bySplit.length >= 1) {
            byDate = this.parseDate(bySplit[0]);
            if (byDate != null && bySplit.length >= 2) {
                byTime = this.parseTime(bySplit[1]);
            } else {
                byTime = this.parseTime(bySplit[0]);
            }
        }

        Deadline deadline = new Deadline(name, by, byDate, byTime);
        this.list.add(deadline);
        return deadline;
    }

    /**
     * Creates a new event (a task with an "at" parameter included) by parsing the input.
     * @throws MnskyException Thrown if the name or the at parameter is missing.
     * @return The new event.
     */
    private Event addEvent(String name, String at) throws MnskyException {
        String[] atSplit = at.split(" ");
        LocalDate atDate = null;
        LocalTime atTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (atSplit.length >= 1) {
            atDate = this.parseDate(atSplit[0]);
            if (atDate != null && atSplit.length >= 2) {
                atTime = this.parseTime(atSplit[1]);
            } else {
                atTime = this.parseTime(atSplit[0]);
            }
        }

        Event event = new Event(name, at, atDate, atTime);
        this.list.add(event);
        return event;
    }

    /**
     * Deletes the task corresponding to the given index parameter, if it exists.
     * @param index The input, split into an array using space.
     */
    private void delete(int index) throws MnskyException {
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


    private boolean isWriteCommand(String searchedCommand) {
        String[] writeCommands = {"mark", "unmark", "todo", "event", "deadline", "delete"};

        for (String command : writeCommands) {
            if (command.equals(searchedCommand)) {
                return true;
            }
        }

        return false;
    }

    private int stringToIndex(String command, String stringIndex) throws MnskyInvalidParameterException {
        int index = Integer.parseInt(stringIndex) - 1;
        if (index < 0 || index >= this.list.size()) {
            throw new MnskyInvalidParameterException(command, "index");
        }

        return index;
    }

    public boolean run() {
        String input = this.ui.getInput();
        ArrayList<String> parsedInput = Parser.parseInput(input);

        try {
            switch (parsedInput.get(0)) {
            case "bye":
                this.ui.bye();
                return false;

            case "list":
                this.printList();
                break;

            case "mark":
                this.mark(this.stringToIndex("mark", parsedInput.get(1)));
                break;

            case "unmark":
                this.unmark(this.stringToIndex("unmark", parsedInput.get(1)));
                break;

            case "task":
                Task task = this.addTask(parsedInput.get(1));
                this.ui.printAddedTask(task);
                break;

            case "event":
                Event event = this.addEvent(parsedInput.get(1), parsedInput.get(2));
                this.ui.printAddedTask(event);
                break;

            case "deadline":
                Deadline deadline = this.addDeadline(parsedInput.get(1), parsedInput.get(2));
                this.ui.printAddedTask(deadline);
                break;

            case "delete":
                this.delete(this.stringToIndex("delete", parsedInput.get(1)));
                break;

            default:
                this.ui.printMnsky("...");
            }
        } catch (MnskyException e) {
            this.ui.printException(e);
        }

        if (this.isWriteCommand(parsedInput.get(0))) {
            this.storage.writeToDataFile(this.list);
        }

        this.ui.printMnsky("I can help!");
        return true;
    }

    public static void main(String[] args) {
        Mnsky mnsky = new Mnsky();

        while (mnsky.run()) {}
    }
}
