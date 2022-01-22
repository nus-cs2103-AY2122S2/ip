import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Mnsky {
    private ArrayList<Task> list;
    private Scanner con;
    private static final String dataFileName = "data/MnskyData.txt";

    /**
     * Constructor for the Mnsky object.
     */
    public Mnsky() {
         this.list = new ArrayList<>();
         this.con = new Scanner(System.in);
         this.readFromDataFile();
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
        System.out.println(this.list.get(index));
    }

    /**
     * Unmarks the task corresponding to the given index parameter so it is not done, if it exists.
     * @param inputSplit The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void unmark(String[] inputSplit) throws MnskyException {
        int index = this.retrieveIndex("mark", inputSplit);
        this.list.get(index).unmark();
        System.out.println(this.list.get(index));
    }

    /**
     * Adds the given task to the list while printing a message.
     * @param task The task to be added to the list.
     */
    private void addTask(Task task) {
        this.list.add(task);
        System.out.printf("[MNSKY added task %s to their list]\n", task.getName());
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
        return new Deadline(deadlineName, by);
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
        return new Event(eventName, at);
    }

    /**
     * Deletes the task corresponding to the given index parameter, if it exists.
     * @param inputSplit The input, split into an array using space.
     * @throws MnskyException Thrown if the index parameter is missing, not an integer, or out of bounds of the list.
     */
    private void delete(String[] inputSplit) throws MnskyException {
        int index = this.retrieveIndex("delete", inputSplit);
        System.out.printf("[MNSKY has deleted the task %s from the list.]\n", this.list.get(index));
        this.list.remove(index);
    }

    /**
     * Creates the data folder.
     */
    private void createDataFolder() {
        File newFolder = new File("data/");
        newFolder.mkdir();
    }

    /**
     * Saves the current state of the list to the data file.
     */
    private void writeToDataFile() {
        FileWriter fileWriter;

        // If directory for data file doesn't exist, create it first
        try {
            fileWriter = new FileWriter(Mnsky.dataFileName);
        } catch (IOException e) {
            this.createDataFolder();
        }

        try {
            fileWriter = new FileWriter(Mnsky.dataFileName);
            BufferedWriter bufferedWriter  = new BufferedWriter(fileWriter);

            for (int i = 0; i < this.list.size(); i++) {
                bufferedWriter.write(this.list.get(i).toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("[MNSKY had trouble committing the task list to its memory!]");
        }
    }

    /**
     * Adds all the tasks from the data file into the list.
     */
    private void readFromDataFile() {
        try {
            Scanner fileScanner = new Scanner(new File(Mnsky.dataFileName));

            while (fileScanner.hasNext()) {
                String nextLine = fileScanner.nextLine();
                if (nextLine.charAt(4) == ' ') {
                    nextLine = nextLine.replaceFirst(" ", "Y");
                }

                String[] nextLineSplit = nextLine.split(" ");
                Task nextTask = null;
                
                if (nextLine.charAt(1) == 'T') {
                    nextTask = this.parseTask(nextLine);
                } else if (nextLine.charAt(1) == 'D') {
                    nextTask = this.parseDeadline(nextLineSplit);
                } else if (nextLine.charAt(1) == 'E') {
                    nextTask = this.parseEvent(nextLineSplit);
                }

                if (nextTask != null) {
                    this.list.add(nextTask);

                    if (nextLine.charAt(4) == 'X') {
                        nextTask.mark();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            this.writeToDataFile();
        } catch (MnskyException e) {
            System.out.println("[MNSKY is having trouble remembering the previous task list...]");
        }
    }

    /**
     * Parses the input and executes the logic depending on the type of input.
     * @return True if the user input "bye" and thus wants to stop talking to the chatbot.
     *          False otherwise.
     */
    public boolean parseInput() {
        String input = this.getInput();
        String[] inputSplit = input.split(" ");

        try {
            switch (inputSplit[0]) {
                case "bye":
                    this.bye();
                    return false;

                case "list":
                    this.printList();
                    break;

                case "mark":
                    this.mark(inputSplit);
                    this.writeToDataFile();
                    break;

                case "unmark":
                    this.unmark(inputSplit);
                    this.writeToDataFile();
                    break;

                case "todo":
                    this.addTask(this.parseTask(input));
                    this.writeToDataFile();
                    break;

                case "event":
                    this.addTask(this.parseEvent(inputSplit));
                    this.writeToDataFile();
                    break;

                case "deadline":
                    this.addTask(this.parseDeadline(inputSplit));
                    this.writeToDataFile();
                    break;

                case "delete":
                    this.delete(inputSplit);
                    this.writeToDataFile();
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
