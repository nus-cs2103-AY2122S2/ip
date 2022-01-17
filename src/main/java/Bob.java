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

        while(!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                showList();
            } else if (input.split(" ")[0].equalsIgnoreCase("mark")){
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
        System.out.println("\t" + current.printStatus());
        System.out.print(lineSplit + "You: ");
    }

    public static void showList() {
        System.out.println("Bob: Okay, these are your tasks");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t" + i + ". " + tasks.get(i - 1).printStatus());
        }
        System.out.println("      (づ｡◕‿‿◕｡)づ");
        System.out.print(lineSplit + "You: ");
    }

    public static void addTask(String input) {
        StringTokenizer line = new StringTokenizer(input);
        String taskType = line.nextToken();
        Task newTask = null;
        if (taskType.equalsIgnoreCase("todo")) {
            newTask = new Todo(input.substring(4).trim());
        } else if (taskType.equalsIgnoreCase("deadline")) {
            String[] strArr = input.substring(8).split("/by");
            if (strArr.length <= 1) {
                invalidInput();
                return;
            }
            newTask = new Deadline(strArr[0].trim(), strArr[1].trim());
        } else if (taskType.equalsIgnoreCase("event")) {
            String[] strArr = input.substring(5).split("/at");
            if (strArr.length <= 1) {
                invalidInput();
                return;
            }
            newTask = new Event(strArr[0].trim(), strArr[1].trim());
        } else {
            invalidInput();
            return;
        }
        tasks.add(newTask);
        System.out.print("Bob: I have added " + newTask + " to your tasks! You have " + tasks.size() + " tasks now " +
                "._.)/\\(._.\n" + lineSplit + "You: ");
    }

    public static void invalidInput() {
        System.out.print("Bob: Your input was invalid (ㆆ _ ㆆ)\n" + lineSplit + "You: ");
    }
    public static void sayBye() {
        System.out.println("Bob: "+ "bye bye c u next time (ʘ‿ʘ)╯\n" + lineSplit2);
    }
}
