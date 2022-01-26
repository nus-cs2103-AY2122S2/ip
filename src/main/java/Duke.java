import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        startGreeting();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storeList = new ArrayList<>();
        restoreList(storeList);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                startGoodbye();
                saveList(storeList);
                break;
            } else if (command.equals("list")) {
                TaskList.printTheList(storeList);
            } else if (command.startsWith("mark ")) {
                markCommand(command, storeList);
            } else if (command.startsWith("unmark ")) {
                unmarkCommand(command, storeList);
            } else if (command.startsWith("deadline")) {
                addDeadline(command, storeList);
            } else if (command.startsWith("todo")) {
                addTodo(command, storeList);
            } else if (command.startsWith("event")) {
                addEvent(command, storeList);
            } else if (command.startsWith("delete")) {
                deleteTask(command, storeList);
            } else {
                unknownCommand();
                continue;
            }
        }
        sc.close();
    }

    public static void unknownCommand() {
        System.out.println("Master, I have all the knowledge in the world but I do not recognise that command," +
                " Please wish again");
    }

    public static void deleteTask(String command, ArrayList<Task> storeList) {
        if (command.length() <= 7) {
            System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                    "delete tasknumber. Please wish again");
        } else {
            try {
                Task t = storeList.get(Integer.parseInt(command.substring(7)));
                System.out.println("Yes master. The task " + t.toString() + " has been removed");
                storeList.remove(Integer.parseInt(command.substring(7)));
                System.out.println("Now you have " + storeList.size() + " tasks left master");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Master, you only have " + storeList.size() + " tasks now. Please choose another task number");
            }
        }
    }

    public static void addEvent(String command, ArrayList<Task> storeList) {
        int slash = command.indexOf("/");
        if (slash == -1 || slash <= 6) {
            System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                    "deadline task /by dateofevent. Please wish again");
        } else {
            String newtask = command.substring(6, slash - 1);
            String attime = command.substring(slash + 1);
            Event e = new Event(newtask, attime);
            storeList.add(e);
            System.out.println("Added to my brain master:");
            System.out.println(e.toString());
            System.out.println("Currently I have " + storeList.size() + " things in my brain");
        }
    }

    public static void addTodo(String command, ArrayList<Task> storeList) {
        if (command.length() < 5) {
            System.out.println("Master, I have all the knowledge in the world but I do not know what you want to do," +
                    " Please wish again in the format todo task");
        } else {
            String newtask = command.substring(5);
            Todo t = new Todo(newtask);
            storeList.add(t);
            System.out.println("Added to my brain master:");
            System.out.println(t.toString());
            System.out.println("Currently I have " + storeList.size() + " things in my brain");
        }
    }

    public static void addDeadline(String command, ArrayList<Task> storeList) {
        int slash = command.indexOf("/");
        if (slash == -1 || slash <= 9) {
            System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                    "deadline task /by dateofdeadline. Please wish again");
        } else {
            String newtask = command.substring(9, slash - 1);
            if (newtask.equals("")) {
                System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                        "deadline task /by dateofdeadline. Please wish again");
            } else {
                String endtime = command.substring(slash + 1);
                Deadline d = new Deadline(newtask, endtime);
                storeList.add(d);
                System.out.println("Added to my brain master:");
                System.out.println(d.toString());
                System.out.println("Currently I have " + storeList.size() + " things in my brain");
            }
        }
    }

    public static void unmarkCommand(String command, ArrayList<Task> storeList) {
        int taskNumber1 = Integer.parseInt(command.substring(7));
        if (taskNumber1 > storeList.size()) {
            System.out.println("Master, you do not have that many tasks, you currently only have "
                    + storeList.size() + " tasks. Please wish again");
        } else if (taskNumber1 <= 0) {
            System.out.println("Master, I am only a genie. Please choose a number between 1 and " + storeList.size());
        } else {
            Task tobeUnmark = storeList.get(taskNumber1 - 1);
            tobeUnmark.setUnmark();
            System.out.println("You probably need more genies to help you. This task has been marked as not done");
            System.out.println(tobeUnmark.toString());
        }
    }

    public static void markCommand(String command, ArrayList<Task> storeList) {
        int taskNumber = Integer.parseInt(command.substring(5));
        if (taskNumber > storeList.size()) {
            System.out.println("Master, you do not have that many tasks, you currently only have "
                    + storeList.size() + " tasks. Please wish again");
        } else if (taskNumber <= 0) {
            System.out.println("Master, I am only a genie. Please choose a number between 1 and " + storeList.size());
        } else {
            Task tobeMark = storeList.get(taskNumber - 1);
            tobeMark.setMark();
            System.out.println("You could have gotten me to help you. This task has been marked done");
            System.out.println(tobeMark.toString());
        }
    }

    public static void startGoodbye() {
        System.out.println("Rub my lamp to summon me again");
        System.out.println("Good bye for now master");
        System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
    }

    public static void restoreList(ArrayList<Task> storeList) throws IOException {
        String currentDirectory = Paths.get("Duke.java").toAbsolutePath().getParent().toString();
        String newFilePath = currentDirectory + "/duke.txt";
        // create the file if do not exist yet
        try {
            Files.createFile(Paths.get(newFilePath));
        } catch (FileAlreadyExistsException e) {

        }
    }

    public static void saveList(ArrayList<Task> arrlist) throws IOException {
        int sizeOfList = arrlist.size();

    }

    public static void startGreeting() {
        System.out.println("A very good day to you master, I'm Blue the Genie");
        System.out.println("What do you wish for today? Your wish is my command");
        System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
    }
}
