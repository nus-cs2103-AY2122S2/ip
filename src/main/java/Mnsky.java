import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Mnsky {
    private ArrayList<Task> list;
    private Scanner con;

    /**
     * Constructor for the Mnsky object.
     */
    public Mnsky() {
         this.list = new ArrayList<>();
         this.con = new Scanner(System.in);
    }

    /**
     * Prints the passed message with "MNSKY: " before it.
     * @param msg The message that should be printed after "MNSKY: "
     */
    private void printMnsky(String msg) {
        System.out.print("MNSKY: ");
        System.out.println(msg);
    }

    /**
     * Prints the greeting message for the chatbot.
     */
    public void greeting() {
        this.printMnsky("Hi, I'm");
        this.printMnsky("MM      MM  NN      NN   SSSSSSS   KK     KK  YY      YY");
        this.printMnsky("MMMM  MMMM  NNNN    NN  SSSS       KK   KK      YY  YY");
        this.printMnsky("MM  MM  MM  NN  NN  NN    SSSSS    KKKKK          YY");
        this.printMnsky("MM      MM  NN    NNNN       SSSS  KK   KK        YY");
        this.printMnsky("MM      MM  NN      NN  SSSSSSS    KK     KK      YY");
        this.printMnsky("I can help!");
    }

    /**
     * Retrieves user input from stdin.
     * @return The user's input
     */
    private String getInput() {
        System.out.println();
        System.out.print("> ");
        return this.con.nextLine();
    }

    /**
     * Prints out the bye messages.
     */
    private void bye() {
        this.printMnsky("I can help!");
        System.out.println("[MNSKY has shut itself down]");
    }

    /**
     * Prints out the list of tasks.
     */
    private void printList() {
        if (this.list.size() == 0) {
            System.out.println("[MNSKY presents an empty task list.]");
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, this.list.get(i)));
            }
        }
    }

    /**
     * Retrieves and returns the value of the index parameter from the input.
     * @param command The name of the command that called this function.
     * @param input_split The input, split into an array using space.
     * @return The value of the index parameter.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private int retrieveIndex(String command, String[] input_split) throws MnskyException {
        if (input_split.length < 2) {
            throw new MnskyMissingParameterException(command, "index");
        }

        if (!input_split[1].matches("\\d+")) {
            throw new MnskyInvalidParameterException(command, "index");
        }

        int index = Integer.parseInt(input_split[1]) - 1;

        if (index < 0 || index >= this.list.size()) {
            throw new MnskyInvalidParameterException(command, "index");
        }

        return index;
    }

    /**
     * Marks the task corresponding to the given index parameter as done, if it exists.
     * @param input_split The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void mark(String[] input_split) throws MnskyException {
        int index = this.retrieveIndex("mark", input_split);
        this.list.get(index).mark();
        System.out.println(this.list.get(index));
    }

    /**
     * Unmarks the task corresponding to the given index parameter so it is not done, if it exists.
     * @param input_split The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void unmark(String[] input_split) throws MnskyException {
        int index = this.retrieveIndex("unmark", input_split);
        this.list.get(index).unmark();
        System.out.println(this.list.get(index));
    }

    /**
     * Adds a task to the list.
     * @param input The input string.
     * @throws MnskyException Thrown if the name parameter is missing.
     */
    private void addTask(String input) throws MnskyException {
        String[] input_split = input.split(" ", 2);
        if (input_split.length < 2) {
            throw new MnskyMissingParameterException("todo", "name");
        }

        String taskName = input_split[1];
        this.list.add(new Task(taskName));
        System.out.println(String.format("[MNSKY added task %s to their list]", taskName));
    }

    /**
     * Adds a deadline (a task with a "by" parameter included) to the list.
     * @param input_split The input, split into an array using space.
     * @throws MnskyException Thrown if the name or the by parameter is missing.
     */
    private void addDeadline(String[] input_split) throws MnskyException {
        if (input_split.length < 2) {
            throw new MnskyMissingParameterException("deadline", "name");
        }

        int by_index = 1;
        for (; by_index < input_split.length; by_index++) {
            if (input_split[by_index].equals("/by")) {
                break;
            }
        }

        if (by_index >= input_split.length) {
            throw new MnskyMissingParameterException("deadline", "by");
        }

        String deadlineName = String.join(" ", Arrays.copyOfRange(input_split, 1, by_index));
        String by = String.join(" ", Arrays.copyOfRange(input_split, by_index + 1, input_split.length));
        LocalDate byDate = null;
        LocalTime byTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (by_index + 1 < input_split.length) {
            byDate = parseDate(input_split[by_index + 1]);
            if (byDate != null && by_index + 2 < input_split.length) {
                byTime = parseTime(input_split[by_index + 2]);
            } else {
                byTime = parseTime(input_split[by_index + 1]);
            }
        }

        this.list.add(new Deadline(deadlineName, by, byDate, byTime));
        System.out.println(String.format("[MNSKY added deadline %s to their list]", deadlineName));
    }

    /**
     * Adds a deadline (a task with an "at" parameter included) to the list.
     * @param input_split The input, split into an array using space.
     * @throws MnskyException Thrown if the name or the at parameter is missing.
     */
    private void addEvent(String[] input_split) throws MnskyException {
        if (input_split.length < 2) {
            throw new MnskyMissingParameterException("event", "name");
        }

        int at_index = 1;
        for (; at_index < input_split.length; at_index++) {
            if (input_split[at_index].equals("/at")) {
                break;
            }
        }

        if (at_index >= input_split.length) {
            throw new MnskyMissingParameterException("event", "at");
        }

        String eventName = String.join(" ", Arrays.copyOfRange(input_split, 1, at_index));
        String at = String.join(" ", Arrays.copyOfRange(input_split, at_index + 1, input_split.length));
        LocalDate atDate = null;
        LocalTime atTime = null;

        // Check to see if there are any dates or times in the first two words after the /by command
        if (at_index + 1 < input_split.length) {
            atDate = parseDate(input_split[at_index + 1]);
            if (atDate != null && at_index + 2 < input_split.length) {
                atTime = parseTime(input_split[at_index + 2]);
            } else {
                atTime = parseTime(input_split[at_index + 1]);
            }
        }

        this.list.add(new Event(eventName, at, atDate, atTime));
        System.out.println(String.format("[MNSKY added event %s to their list]", eventName));
    }

    /**
     * Deletes the task corresponding to the given index parameter, if it exists.
     * @param input_split The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void delete(String[] input_split) throws MnskyException {
        int index = this.retrieveIndex("delete", input_split);
        System.out.println(String.format("[MNSKY has deleted the task %s from the list.]", this.list.get(index)));
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

    /**
     * Parses the input and executes the logic depending on the type of input.
     * @return True if the user input "bye" and thus wants to stop talking to the chatbot.
     *          False otherwise.
     */
    public boolean parseInput() {
        String input = this.getInput();
        String[] input_split = input.split(" ");

        try {
            switch (input_split[0]) {
                case "bye":
                    this.bye();
                    return false;

                case "list":
                    this.printList();
                    break;

                case "mark":
                    this.mark(input_split);
                    break;

                case "unmark":
                    this.unmark(input_split);
                    break;

                case "todo":
                    this.addTask(input);
                    break;

                case "event":
                    this.addEvent(input_split);
                    break;

                case "deadline":
                    this.addDeadline(input_split);
                    break;

                case "delete":
                    this.delete(input_split);
                    break;

                default:
                    this.printMnsky("...");
            }
        } catch (MnskyException m) {
            this.printMnsky("..?");
            System.out.println(m);
        }

        this.printMnsky("I can help!");
        return true;
    }

    public static void main(String[] args) {
        Mnsky mnsky = new Mnsky();
        mnsky.greeting();

        while (mnsky.parseInput()) {}
    }
}
