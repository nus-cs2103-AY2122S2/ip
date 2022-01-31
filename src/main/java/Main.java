import commands.Command;
import tasks.Task;

import java.util.Scanner;

public class Main {
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

    public static void greet() {
        String logo =
                " _   _                  _\n" +
                        "| | | |   ___   _   _  | |   ___\n" +
                        "| |_| |  / _ \\ | | | | | |  / _ \\\n" +
                        "|  _  | |  __/ | |_| | | | | (_) |\n" +
                        "|_| |_|  \\___|  \\__, | |_|  \\___/\n" +
                        "                |___/\n";

        System.out.println(logo);
        System.out.println("Heylo! What can I do for you today? :)\n");
    }
}
