import tasks.Task;

import java.util.Scanner;

public class Heylo {
    public static void main(String[] args) {
        String logo =
                " _   _                  _\n" +
                        "| | | |   ___   _   _  | |   ___\n" +
                        "| |_| |  / _ \\ | | | | | |  / _ \\\n" +
                        "|  _  | |  __/ | |_| | | | | (_) |\n" +
                        "|_| |_|  \\___|  \\__, | |_|  \\___/\n" +
                        "                |___/\n";

        Task.getSavedTasks();

        System.out.println(logo);
        System.out.println("Heylo! What can I do for you today? :)\n");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            Command cmd = new Command(input);
            cmd.run(sc);
        }
    }
}
