package heylo;

import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);

        Task.getSavedTasks();
        greet();

        while (true) {
            String input = sc.nextLine();
            Command cmd = new Command(input);
            cmd.run(sc);
        }
    }

    /**
     * Greets the user with a stylized logo.
     */
    public static void greet() {
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
}
