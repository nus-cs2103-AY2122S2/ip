package heylo;

import heylo.commands.Command;
import heylo.tasks.Task;

/**
 * Drives the Heylo program.
 */
public class Main {
    /**
     * Loads previously saved tasks, greets the user and accepts commands to run.
     *
     * @param args Input arguments.
     */
    public static void main(String[] args) {
        Task.getSavedTasks();
    }

    /**
     * Runs command given by user.
     *
     * @param input Input.
     * @return Command result.
     */
    static String runCommand(String input) {
        Command cmd = new Command(input);
        return cmd.run();
    }

    /**
     * Returns response from Heylo to the user input.
     *
     * @param input User input.
     * @return Heylo response.
     */
    public String getResponse(String input) {
        return runCommand(input);
    }
}
