import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bob {
    private static final String lineSplit = "====================================================================\n";
    private static final String lineSplit2 = "====================================================================";
    private static List<Task> tasks;

    public static void main(String[] args) {
        initializeBob();
    }

    public static void initializeBob() {
        tasks = new ArrayList<>();
        greet();
        activeListener();
        sayBye();
    }

    public static void greet() {
        String logo = "██╗░░██╗██╗  ██╗██╗███╗░░░███╗  ██████╗░░█████╗░██████╗░\n"
                    + "██║░░██║██║  ██║╚█║████╗░████║  ██╔══██╗██╔══██╗██╔══██╗\n"
                    + "███████║██║  ██║░╚╝██╔████╔██║  ██████╦╝██║░░██║██████╦╝\n"
                    + "██╔══██║██║  ██║░░░██║╚██╔╝██║  ██╔══██╗██║░░██║██╔══██╗\n"
                    + "██║░░██║██║  ██║░░░██║░╚═╝░██║  ██████╦╝╚█████╔╝██████╦╝\n"
                    + "╚═╝░░╚═╝╚═╝  ╚═╝░░░╚═╝░░░░░╚═╝  ╚═════╝░░╚════╝░╚═════╝░\n"
                    + lineSplit;
        System.out.print(logo);
        System.out.print("Bob: How can I help? \t¯\\(°_o)/¯\n" + lineSplit + "You: ");
    }

    public static void activeListener() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.print(lineSplit);

        while(!input.toLowerCase().equals("bye")) {
            if (input.toLowerCase().equals("list")) {
                showList();
            } else if (input.split(" ")[0].toLowerCase().equals("mark")){
                StringTokenizer command = new StringTokenizer(input);
                command.nextToken();
                markTaskDone(Integer.parseInt(command.nextToken()));
            } else {
                addTask(input);
            }
            input = sc.nextLine();
            System.out.print(lineSplit);
        }
    }

    public static void markTaskDone(int taskNumber) {
        Task current = tasks.get(taskNumber - 1);
        current.setStatus(1);
        System.out.println("Bob: Great job in completing your task! I've marked it as done. ᕕ(⌐■_■)ᕗ ♪♬");
        System.out.println("\t" + current.getStatus());
        System.out.print(lineSplit + "You: ");
    }

    public static void showList() {
        System.out.println("Bob: Okay, these are your tasks (づ｡◕‿‿◕｡)づ");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t" + i + ". " + tasks.get(i - 1).getStatus());
        }
        System.out.print(lineSplit + "You: ");
    }

    public static void addTask(String input) {
        Task newTask = new Task(input);
        tasks.add(newTask);
        System.out.print("Bob: " + "I have added " + newTask + " to your tasks! ._.)/\\(._.\n" + lineSplit + "You: ");
    }
    public static void sayBye() {
        System.out.println("Bob: "+ "bye bye c u next time (ʘ‿ʘ)╯\n" + lineSplit2);
    }
}
