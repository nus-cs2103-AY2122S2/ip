import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bob {
    private static final String lineSplit = "====================================================================\n";
    private static final String lineSplit2 = "====================================================================";
    public static final String fileName = "./Bob.txt";
    public static Storage store;
    private static List<Task> tasks;

    public static void main(String[] args) {
        initializeBob();
    }

    public static void initializeBob() {
        store = new Storage(fileName);
        tasks = store.getSavedStore();
        greet();
        activeListener();
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
        System.out.print("Bob: What tasks are you adding to the list today? \t¯\\(°_o)/¯\n");
        store.readStore();
        System.out.print(lineSplit + "You: ");
    }

    public static void activeListener() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.print(lineSplit);
        while(!Command.isBye(input)) {
            if (input.isBlank()) {
                System.out.print("Bob: ╭∩╮༼ಠ益ಠ╭∩╮༽\n" + lineSplit + "You: ");
            } else if (Command.isList(input)) {
                showList();
            } else if (Command.isMark(input.split(" ")[0])) {
                String index = input.substring(4).trim();
                try {
                    markTaskDone(index);
                } catch (BobException e) {
                    System.out.print(e.getBobReply() + lineSplit + "You: ");
                }
            } else if (Command.isDelete(input.split(" ")[0])) {
                String index = input.substring(6).trim();
                try {
                    deleteTask(index);
                } catch (BobException e) {
                    System.out.print(e.getBobReply() + lineSplit + "You: ");
                }
            } else {
                try {
                    addTask(input);
                } catch (BobException e) {
                    System.out.print(e.getBobReply() + lineSplit + "You: ");
                }
            }
            input = sc.nextLine();
            System.out.print(lineSplit);
        }
        sayBye();
    }

    public static void deleteTask(String taskNumber) throws BobException {
        int index;
        Task currTask;
        try {
            index = Integer.parseInt(taskNumber);
            currTask = tasks.get(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BobException("delete");
        }
        tasks.remove(currTask);
        store.updateStore(tasks);
        System.out.println("Bob: I have removed the following task from your list. ᕙ(⇀‸↼‶)ᕗ");
        System.out.println("\t" + currTask.printStatus());
        System.out.print(lineSplit + "You: ");
    }

    public static void markTaskDone(String taskNumber) throws BobException {
        int index;
        Task current;
        try {
            index = Integer.parseInt(taskNumber);
            current = tasks.get(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BobException("mark");
        }
        if (current.getStatus() == 1) {
            System.out.println("Bob: You already did this task... or did you? (ಠ_⊙)");
            System.out.print(lineSplit + "You: ");
        } else {
            current.setStatus(1);
            System.out.println("Bob: Great job in completing your task! I've marked it as done. ᕕ(⌐■_■)ᕗ ♪♬");
            System.out.println("\t" + current.printStatus());
            System.out.print(lineSplit + "You: ");
        }
        store.updateStore(tasks);
    }

    public static void showList() {
        if (tasks.isEmpty()) {
            System.out.println("Bob: You are very free rn \t※\\(^o^)/※");
            System.out.print(lineSplit + "You: ");
        } else {
            System.out.println("Bob: Okay, these are your tasks");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("\t" + i + ". " + tasks.get(i - 1).printStatus());
            }
            System.out.println("      (づ｡◕‿‿◕｡)づ");
            System.out.print(lineSplit + "You: ");
        }
    }

    public static void addTask(String input) throws BobException {
        StringTokenizer line = new StringTokenizer(input);
        String taskType = line.nextToken();
        Task newTask;
        if (Command.isTodo(taskType)) {
            String[] strArr = input.substring(4).split(" ");
            if (strArr.length == 1) {
                if (strArr[0].isBlank()) {
                    throw new BobException("todo");
                }
            }
            if (strArr.length <= 0) {
                throw new BobException("todo");
            }
            newTask = new Todo(input.substring(4).trim());
        } else if (Command.isDeadline(taskType)) {
            String[] strArr = input.substring(8).split("/by");
            if (strArr.length <= 1) {
                throw new BobException("deadline");
            }
            newTask = new Deadline(strArr[0].trim(), strArr[1].trim());
        } else if (Command.isEvent(taskType)) {
            String[] strArr = input.substring(5).split("/at");
            if (strArr.length <= 1) {
                throw new BobException("event");
            }
            newTask = new Event(strArr[0].trim(), strArr[1].trim());
        } else {
            throw new BobException("invalid input");
        }
        tasks.add(newTask);
        store.updateStore(tasks);
        System.out.print("Bob: I have added " + newTask + " to your tasks! You have " + tasks.size() + " tasks now " +
            "._.)/\\(._.\n" + lineSplit + "You: ");
    }

    public static void sayBye() {
        System.out.println("Bob: "+ "bye bye c u next time (ʘ‿ʘ)╯\n" + lineSplit2);
    }
}
