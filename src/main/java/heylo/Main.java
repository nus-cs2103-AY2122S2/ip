package heylo;

import heylo.commands.Command;
import heylo.tasks.Task;

import java.util.Scanner;


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
        greet();
        runCommandsTillExit();
    }

    /**
     * Greets the user with a stylized logo.
     */
    static void greet() {
        String logo =
                " _   _                  _\n"
                        + "| | | |   ___   _   _  | |   ___\n"
                        + "| |_| |  / _ \\ | | | | | |  / _ \\\n"
                        + "|  _  | |  __/ | |_| | | | | (_) |\n"
                        + "|_| |_|  \\___|  \\__, | |_|  \\___/\n"
                        + "                |___/\n";

        System.out.println(logo);
        System.out.println("Heylo! What can I do for you today? :)\n");
    }

    static void runCommandsTillExit() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            Command cmd = new Command(input);
            cmd.run(sc);
        }
    }
}
