import java.util.Scanner;
import java.util.ArrayList;

public class Pac {
    static String logo = " ____     ___    _____\n"
            + "|  _ \\   / _ \\  |  ___|\n"
            + "| |_| | | |_| | | |\n"
            + "|  __/  | | | | | |___\n"
            + "|_|     |_| |_| |_____|\n";
    static String newline = "----------------------------------------------------";
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("\n" + newline + "\n" + newline + "\n" + logo);
        System.out.println("Hello there! I'm Pac, your very own Personal Assistant ChatBot.\nHow may I help you?");
        System.out.println(newline + "\n");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            String firstword = inputArray[0].toLowerCase();
            Keyword keyword = Keyword.ADD;

            if (firstword.equals("bye")) {
                keyword = Keyword.BYE;
            } else if (firstword.equals("list")) {
                keyword = Keyword.LIST;
            } else if (firstword.equals("mark")) {
                keyword = Keyword.MARK;
            } else if (firstword.equals("unmark")) {
                keyword = Keyword.UNMARK;
            }

            switch (keyword) {
                case BYE:
                    exitPac();
                    break;
                case LIST:
                    displayList();
                    break;
                case MARK:
                    markTask(Integer.parseInt(inputArray[1]) - 1);
                    break;
                case UNMARK:
                    unmarkTask(Integer.parseInt(inputArray[1]) - 1);
                    break;
                default:
                    addTask(input);
                    break;
            }
        }
    }

    public static void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println(newline + "\n" + "added: "+ task.getDescription() + "\n" + newline + "\n");
    }

    public static void markTask(int index) {
        tasks.get(index).markAsDone();
        System.out.println(newline);
        System.out.println("Task is marked as done. \n" + tasks.get(index));
        System.out.println(newline + "\n");
    }

    public static void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
        System.out.println(newline);
        System.out.println("Task is marked as  not done. \n" + tasks.get(index));
        System.out.println(newline + "\n");
    }

    public static void displayList() {
        int i = 1;
        System.out.println(newline);
        for (Task task : tasks) {
            System.out.println(i++ +". " + task);;
        }
        System.out.println(newline + "\n");
    }

    public static void exitPac() {
        System.out.println(newline + "\n" + "Goodbye! See you soon. :)\n" + newline);
        System.exit(0);
    }
}