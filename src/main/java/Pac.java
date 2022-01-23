import java.util.Scanner;
import java.util.ArrayList;

public class Pac {

    public static void main(String[] args) {
        String logo = " ____     ___    _____\n"
                + "|  _ \\   / _ \\  |  ___|\n"
                + "| |_| | | |_| | | |\n"
                + "|  __/  | | | | | |___\n"
                + "|_|     |_| |_| |_____|\n";
        String newline = "----------------------------------------------------";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("\n" + newline + "\n" + newline + "\n" + logo);
        System.out.println("Hello there! I'm Pac, your very own Personal Assistant ChatBot.\nHow may I help you?");
        System.out.println(newline + "\n");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            String keyword = inputArray[0].toLowerCase();

            if (keyword.equals("bye")) {
                System.out.println(newline + "Goodbye! See you soon. :)\n" + newline);
                System.exit(0);
                break;
            } else if (keyword.equals("list")) {
                int i = 1;
                System.out.println(newline);
                for (Task task : tasks) {
                    System.out.println(i++ + ". " + task.getDescription());
                }
                System.out.println(newline + "\n");
            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println(newline + "\n" + "added: "+ input + "\n" + newline + "\n");
            }
        }
    }
}