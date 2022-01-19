import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey there! I'm Mickey, your Mouse assistant.\nWhat can I do for you today?\n");
        for (String in = sc.next(); !in.equals("bye"); in = sc.next()) {
            switch (in) {
                case "list":
                    System.out.println("\tOh boy! Here are your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("\t\t" + (i + 1) + ". " + tasks.get(i));
                    }
                    break;
                case "mark":
                    System.out.println("\tThat is swell! You have completed this task:");
                    System.out.println("\t\t" + markAsDone(sc.nextInt()));
                    break;
                case "unmark":
                    System.out.println("\tHot dog! Complete this soon:");
                    System.out.println("\t\t" + markAsDone(sc.nextInt()));
                    break;
                default:
                    Task newTask = new Task(in);
                    tasks.add(newTask);
                    System.out.println("\tAw, gee! New task: " + in);
                    break;
            }
        }
        System.out.println("\tToodles! See ya real soon!");
        tasks.clear();
    }

    public static Task markAsDone(int index) {
        tasks.get(index - 1).markAsDone();
        return tasks.get(index - 1);
    }
}