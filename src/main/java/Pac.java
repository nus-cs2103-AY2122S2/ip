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
            String[] inputArray = input.split(" ", 2);
            String firstword = inputArray[0].toLowerCase();
            Keyword keyword;

            switch (firstword) {
                case "bye":
                    keyword = Keyword.BYE;
                    break;
                case "list":
                    keyword = Keyword.LIST;
                    break;
                case "mark":
                    keyword = Keyword.MARK;
                    break;
                case "unmark":
                    keyword = Keyword.UNMARK;
                    break;
                case "todo":
                    keyword = Keyword.TODO;
                    break;
                case "deadline":
                    keyword = Keyword.DEADLINE;
                    break;
                case "event":
                    keyword = Keyword.EVENT;
                    break;
                default:
                    keyword = Keyword.ERROR;
                    break;
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
                case TODO:
                    addToDo(inputArray[1]);
                    break;
                case DEADLINE:
                    addDeadline(inputArray[1]);
                    break;
                case EVENT:
                    addEvent(inputArray[1]);
                    break;
                case ERROR:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void addToDo(String description) {
        Task task = new ToDo(description);
        tasks.add(task);
        System.out.println(newline + "\nadded: " + task.toString());
        System.out.println("You have " + tasks.size() + " tasks in your list" + "\n" + newline + "\n");
    }

    public static void addDeadline(String input) {
        String[] inputArray = input.split("/");
        inputArray[1] = inputArray[1].replaceFirst("BY ", "by ");
        inputArray[1] = inputArray[1].replaceFirst("By ", "by ");
        inputArray[1] = inputArray[1].replaceFirst("bY ", "by ");
        inputArray[1] = inputArray[1].split("by ", 2)[1];
        Task task = new Deadline(inputArray[0], inputArray[1]);
        tasks.add(task);
        System.out.println(newline + "\nadded: " + task.toString());
        System.out.println("You have " + tasks.size() + " tasks in your list" + "\n" + newline + "\n");
    }

    public static void addEvent(String input) {
        String[] inputArray = input.split("/");
        inputArray[1] = inputArray[1].replaceFirst("AT ", "by ");
        inputArray[1] = inputArray[1].replaceFirst("At ", "by ");
        inputArray[1] = inputArray[1].replaceFirst("aT ", "by ");
        inputArray[1] = inputArray[1].split("at ", 2)[1];
        Task task = new Event(inputArray[0], inputArray[1]);
        tasks.add(task);
        System.out.println(newline + "\nadded: " + task.toString());
        System.out.println("You have " + tasks.size() + " tasks in your list" + "\n" + newline + "\n");
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