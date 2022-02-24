import java.io.*;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Arrays;


/**
 * Project Duke is a educational software project designed to take you
 * through the steps of building a small software incrementally,
 * while applying as many Java and SE techniques as possible along the way.
 *
 * @author AdrianOngJJ
 * @version 0.1
 * @since 22/1/2022
 */
public class Duke {
    private static final String LINE_BREAK = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static ArrayList<Task> masterList = new ArrayList<>();
    ;
    /**
     * Prints line break.
     * @return void
     */
    private static final void printLineBreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints Master List
     * @param bw BufferedWriter from main to print out the Master List.
     * @throws java.io.IOException If an I/O error occurs. Only takes in string.
     */
    private static final void printList(BufferedWriter bw) throws IOException {
        for(int i = 0; i < masterList.size(); i++) {
            Task curr = masterList.get(i);
            bw.write((i + 1) + "." + curr.toString());
            bw.newLine();
        }
        bw.flush();
    }

    private static final DateTime getDateTime(String[] inputArr) {
        String[] dateTimeArr = inputArr[1].split("/")[1].split("[- ]"); // [String, yyyy, mm, dd, HHMM]
        return new DateTime(Arrays.copyOfRange(dateTimeArr, 1, dateTimeArr.length));
        // will reduce dateTimeArr to [yyyy, mm, dd, HHMM]


    }

    private static final String getDescription(String[] inputArr) {
        return inputArr[1].split("/")[0]; // split input from slash
    }

    private static final String taskToString(Task task) {
        String toReturn = "";
        if (task instanceof ToDos) {
            toReturn = toReturn + "T|";
        } else if (task instanceof Deadlines) {
            toReturn = toReturn + "D|";
        } else if (task instanceof Events) {
            toReturn = toReturn + "E|";
        }
        if (task.isDone) {
            toReturn = toReturn + "1|";
        } else {
            toReturn = toReturn + "0|";
        }
        toReturn = toReturn + task.description;
        if (task instanceof Deadlines || task instanceof Events) {
            String[] durationArr = task.toString().split("[:)]");
            String duration = durationArr[1].split(" ")[1];
            toReturn = toReturn + "|" + duration;
        }
        return toReturn;
    }

    private static final void saveAllTask(ArrayList<Task> tasks, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(taskToString(task));
            fw.write("\n");
        }
        fw.close();
    }

    public static final Task loadTask(String description) {
        String[] splitDescription = description.split("\\|");
        String taskType = splitDescription[0];
        Task tempTask = new Task("Temp task");
        switch (taskType) {
        case "T":
            Task newToDo = new ToDos(splitDescription[2]);
            if (splitDescription[1].equals("1")) {
                newToDo.markAsDone();
            }
            tempTask = newToDo;
            break;

        case "D":
            Task newDeadline = new Deadlines(splitDescription[2], new DateTime(splitDescription[3].split("[- ]")));
            if (splitDescription[1].equals("1")) {
                newDeadline.markAsDone();
            }
            tempTask = newDeadline;
            break;

        case "E":
            Task newEvent = new Events(splitDescription[2], new DateTime(splitDescription[3].split("[- ]")));
            if (splitDescription[1].equals("1")) {
                newEvent.markAsDone();
            }
            tempTask = newEvent;
            break;

        default:
            System.out.println("All tasks loaded!");

        }
        return tempTask;
    }

    public static void main(String[] args) throws Exception, IOException {
        String home =  System.getProperty("user.home"); // base directory
        // following code should give me [HOME_DIRECTORY]/Desktop/iP/data
        java.nio.file.Path path = java.nio.file.Paths.get(home,"Desktop", "iP", "data");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            File dukeStore = new File(path + "/duke.txt");
            Scanner fileReader = new Scanner(dukeStore);
            while (fileReader.hasNextLine()) {
                masterList.add(loadTask(fileReader.nextLine()));
            }
        } catch (IOException e) {
            Path filePath = Paths.get("data");
            boolean isDirectoryExists = Files.exists(filePath);
            if (!isDirectoryExists) {
                new File("data").mkdir();
            }
            new File(path + "/duke.txt").createNewFile();
        }



        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLineBreak();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input; // to store raw input command
        String[] inputArr; // to store split input command
        boolean ifBye = false;
        do {
            printLineBreak();
            System.out.println();
            input = br.readLine();
            inputArr = input.split(" ", 2); // split first word from body
            printLineBreak();
            switch (inputArr[0]) {
            case "bye":
                ifBye = true;
                File dukeStore = new File(path + "/duke.txt");
                dukeStore.delete();
                saveAllTask(masterList, path + "/duke.txt");
                System.out.println("Bye. Hope to see you again soon!");
                break;

            case "list":
                bw.write("Here are the tasks in your list:\n");
                printList(bw);
                break;

            case "mark":
                bw.write("Nice! I've marked this task as done:\n");
                int index = Integer.parseInt(inputArr[1]) - 1;
                Task curr = masterList.get(index); // task to be marked
                masterList.set(index, curr.markAsDone());
                bw.write(masterList.get(index).toString());
                bw.flush();
                break;

            case "unmark":
                bw.write("OK, I've marked this task as not done yet:\n");
                int indexUnmark = Integer.parseInt(inputArr[1]) - 1;
                Task currUnmark = masterList.get(indexUnmark); // task to be unmarked
                masterList.set(indexUnmark, currUnmark.unmarkItem());
                bw.write(masterList.get(indexUnmark).toString());
                bw.flush();
                break;

            case "deadline":
                masterList.add(new Deadlines(getDescription(inputArr), getDateTime(inputArr)));
                System.out.println("Got it. I've added this task:\n\t "
                        + (masterList.get(masterList.size() - 1)).toString()
                        + "\nNow you have " + masterList.size() + " tasks in the list.");
                break;

            case "todo":
                masterList.add(new ToDos(getDescription(inputArr)));
                System.out.println("Got it. I've added this task:\n\t "
                        + (masterList.get(masterList.size() - 1)).toString()
                        + "\nNow you have " + masterList.size() + " tasks in the list.");
                break;

            case "event":
                masterList.add(new Events(getDescription(inputArr), getDateTime(inputArr)));
                System.out.println("Got it. I've added this task:\n\t "
                        + (masterList.get(masterList.size() - 1)).toString()
                        + "\nNow you have " + masterList.size() + " tasks in the list.");
                break;

            case "delete":
                bw.write("Noted. I've removed this task:\n\t");
                int indexDel = Integer.parseInt(inputArr[1]) - 1;
                bw.write(masterList.remove(indexDel).toString());
                bw.write("\nNow you have " + masterList.size() + " tasks in list.");
                bw.flush();
                break;


            default:
                System.out.println("Invalid input: " + input);
            }
        } while (!ifBye);
        printLineBreak();

    }
}
