package ui;

import task.Task;
import ui.command.Command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jiaaa-yang
 *
 * ChatBot class that allows for running of user command
 * and keeping track of its termination status.
 */
public class ChatBot {
    private final static String WELCOME_STRING = "Hello I'm Duke!\n"
                                                + "What can I do for you?";

    /**
     * File instance storing tasks data for ChatBot instance
     */
    private final File dataFile;

    /**
     * ArrayList to keep track of text entered by user
     */
    private final ArrayList<Task> tasks;

    /**
     * Boolean to track if bot has received a termination command
     */
    private boolean hasTerminated;

    public ChatBot(File dataFile) {
        this.dataFile = dataFile;
        this.tasks = new ArrayList<>();
        this.hasTerminated = false;
    }

    /**
     * Performs initialisation of the ChatBot. Should be called
     * before receiving commands
     */
    public void initialise() {
        System.out.println(WELCOME_STRING);
        this.loadTasksList();
    }

    /**
     * Runs the command given by user input string
     *
     * @param input String command by user to run
     */
    public void runCommand(String input) {
        try {
            Command command = Command.parseCommand(input, this.tasks, this.dataFile);
            this.hasTerminated = command.execute();
        } catch (IllegalArgumentException e) {
            ArrayList<String> response = new ArrayList<>();
            response.add("Sorry, the following problem has occurred:");
            response.add(e.getMessage());
            Command.styledPrint(response);
        }
    }

    public boolean hasTerminated() {
        return this.hasTerminated;
    }

    private void loadTasksList() {
        try {
            Scanner scanner = new Scanner(this.dataFile);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task savedTask = Task.decodeTaskData(taskData);
                this.tasks.add(savedTask);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
