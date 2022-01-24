import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bob {
    private static final String lineSplit = "====================================================================\n";
    private static final String lineSplit2 = "====================================================================";
    public static final String fileName = "Bob.txt";
    public static File file;
    public static FileWriter fw;
    public static Scanner sc;
    private static List<Task> tasks;

    public static void main(String[] args) {
        initializeBob();
    }

    public static void initializeBob() {
        tasks = new ArrayList<>();
        file = getFile();
        sc = initiateFileScanner();
        initiateFileWriter();
        greet();
        activeListener();
    }

    public static Scanner initiateFileScanner() {
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("dasfowfwf");
        }
        return sc;
    }
    public static void initiateFileWriter() {
        try {
            fw = new FileWriter(fileName, true);
        } catch (IOException e) {
            System.out.println("Bob: I cant write to the file... Imma go..." + lineSplit);
        }
    }

    public static void closeFileWriter() {
        try {
            fw.close();
        } catch (IOException e) {
            System.out.println("Bob: I dont think ur tasks are saved... Imma go..." + lineSplit);
        }
    }

    public static File getFile() {
        file = new File(fileName);
        try {
            if (!file.exists()) {
                System.out.println("doesnt exist");
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Bob: I cant create new file... Imma go..." + lineSplit);
        }
        return file;
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
        listTasks();
        System.out.print(lineSplit + "You: ");
    }

    public static void listTasks() {
        int lineCounter = 1;
        while (sc.hasNextLine()) {
            String[] current = sc.nextLine().split("\\|");
            System.out.println("\t" + lineCounter + ". [" + current[0] + "] [" + current[1] + "] " + current[2]);
            lineCounter++;
        }
    }

    public static int listSize() {
        int counter = 0;
        while (sc.hasNextLine()) {
            sc.nextLine();
            counter++;
        }
        return counter;
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
    }

    public static void showList() {
        if (tasks.isEmpty()) {
            System.out.println("Bob: You are very free rn \t※\\(^o^)/※");
            System.out.print(lineSplit + "You: ");
        } else {
            System.out.println("Bob: Okay, these are your tasks");
            listTasks();
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
        try {
            fw.write(newTask.getType() + "|" + newTask.getStatus() + "|" + newTask + "\n");
            fw.flush();
        } catch (IOException e) {
            // make this exception
            System.out.println("Bob: Something went wrong....");
        }
        System.out.print("Bob: I have added " + newTask + " to your tasks! You have " + tasks.size() + " tasks now " +
            "._.)/\\(._.\n" + lineSplit + "You: ");
    }

    public static void sayBye() {
        closeFileWriter();
        System.out.println("Bob: "+ "bye bye c u next time (ʘ‿ʘ)╯\n" + lineSplit2);
    }
}
