import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int IS_UNMARKED = 0;
    private static final int IS_MARKED = 1;
    private static final String DUKE_SAVE_DIRECTORY = "C:\\DukeSaveDirectory";
    private static final String DUKE_SAVE_TXT = "C:\\DukeSaveDirectory\\DukeSave.txt";

    // Creates an empty ArrayList to store Tasks
    private static ArrayList<Task> taskArr = new ArrayList<>();

    // Create an empty ArrayList for String to ArrayList conversion
    private static ArrayList<String> taskArrStr = new ArrayList<>();

    /**
     * Prints a line
     */
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a logo
     */
    public static void logo() {
        System.out.println(" ____                         _____       _\n"
                + "| |_) |_   _ ___ ___ _   _  | (___   __ _| | ____ _\n"
                + "|  _ <| | | / __/ __| | | |  \\___ \\ / _` | |/ / _` |\\\n"
                + "| |_) | |_| \\__ \\__ \\ |_| |  ____) | (_| |   < (_| |\n"
                + "|____/ \\__,_|___/___/\\__, | |_____/ \\__,_|_|\\_\\__,_|\n"
                + "                      __/ |\n"
                + "                      |___/ \n");
    }

    /**
     * Prints the total number of tasks
     *
     * @param num Total number of tasks
     */
    public static void totalTasks(int num) {
        if (num == 1) {
            System.out.println("     You currently have " + num + " task in your device.");
        } else {
            System.out.println("     You currently have " + num + " tasks in your device.");
        }
    }

    /**
     * Marks the respective task with a tick
     *
     * @param num The index of the task to be marked
     */
    public static void mark(int num) {
        line();
        Task currTask = taskArr.get(num - 1);
        currTask.mark();
        System.out.println("     The bar on the top left of your screen just increased! Keep going!");
        System.out.println("     " + currTask);
        updateTextFile();
        line();
    }

    /**
     * Unmarks the respective task with a tick
     *
     * @param num The index of the task to be unmarked
     */
    public static void unmark(int num) {
        line();
        Task currTask = taskArr.get(num - 1);
        currTask.unmark();
        System.out.println("     Surely you aren't the imposter... right??");
        System.out.println("     " + currTask);
        updateTextFile();
        line();
    }

    /**
     * Adds a Task to the Task ArrayList
     *
     * @param task Task to be added into the ArrayList
     */
    public static void addToList(Task task) {
        taskArr.add(task);
        System.out.println("       " + task.toString());
        totalTasks(taskArr.size());
        updateTextFile();
    }

    /**
     * Adds a Task to the Task ArrayList without print statements
     *
     * @param task Task to be added into the ArrayList
     */
    public static void addToListNoPrint(Task task) {
        taskArr.add(task);
        updateTextFile();
    }

    /**
     * Deletes a Task from the Task ArrayList
     *
     * @param task Task to be deleted from the ArrayList
     */
    public static void deleteFromList(Task task) {
        taskArr.remove(task);
        line();
        System.out.println("     Hmm... kinda sus you deleted this task...");
        System.out.println("       " + task);
        totalTasks(taskArr.size());
        updateTextFile();
        line();
    }

    /**
     * Prints all the Tasks in the ArrayList in sequential order
     */
    public static void printList() {
        line();
        System.out.println("     Here are the tasks in your device:");
        for (int i = 0; i < taskArr.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskArr.get(i).toString());
        }
        line();
    }

    private static void updateTextFile() {
        try {
            writeToFile(DUKE_SAVE_TXT);
        } catch (IOException e) {
            System.out.println("Cannot write to txt file!");
        }
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task str : taskArr) {
            fw.write(formatTextFileLine(str) + System.lineSeparator());
        }
        fw.close();
    }

    private static String formatTextFileLine(Task task) {
        String finalOutput= null;
        String description = task.toString().substring(7);
        int mark = (task.getStatusIcon().equals("X") ? IS_MARKED : IS_UNMARKED);
        if (task instanceof ToDo) {
            finalOutput = "T|" + mark + "|" + description;
        } else if (task instanceof Deadline) {
            String[] str = description.split(" \\(by: ");
            String deadLineTask = str[0];
            String dateTime = str[1].substring(0, str[1].length() - 1);
            finalOutput = "D|" + mark + "|" + deadLineTask + "|" + dateTime;
        } else if (task instanceof Event) {
            String[] str = description.split(" \\(at: ");
            String eventTask = str[0];
            String dateTime = str[1].substring(0, str[1].length() - 1);
            finalOutput = "E|" + mark + "|" + eventTask + "|" + dateTime;
        }
        return finalOutput;
    }

    public static void readTxtFile() {
        try {
            readFileContents(DUKE_SAVE_TXT);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read txt file!");
        }
    }

    private static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            String[] lineArr = currentLine.split("\\|");
            int checkMarked = Integer.parseInt(lineArr[1]);
            switch (lineArr[0]) {
            case "T":
                Task toDo = new ToDo(lineArr[2]);
                if (isMarked(checkMarked)) {
                    toDo.mark();
                } else {
                    toDo.unmark();
                }
                addToListNoPrint(toDo);
                break;
            case "D":
                Task deadline = new Deadline(lineArr[2], lineArr[3]);
                if (isMarked(checkMarked)) {
                    deadline.mark();
                } else {
                    deadline.unmark();
                }
                addToListNoPrint(deadline);
                break;
            case "E":
                Task event = new Event(lineArr[2], lineArr[3]);
                if (isMarked(checkMarked)) {
                    event.mark();
                } else {
                    event.unmark();
                }
                addToListNoPrint(event);
                break;
            default:
                break;
            }
        }
    }

    public static boolean isMarked(int markedNum) {
        if(markedNum == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            // Get directory of where Duke.txt is
            File saveDirectory = new File(DUKE_SAVE_DIRECTORY);
            // Get text file for saved tasks in list
            File dukeSaveFile = new File(DUKE_SAVE_TXT);

            if (!saveDirectory.exists()) {
                throw new FileNotFoundException("DukeSaveDirectory not found! Please create one!");
            }
            if (!dukeSaveFile.exists()) {
                throw new FileNotFoundException("DukeSaveFile not found! Please create one!");
            }

            readTxtFile();

            line();
            System.out.println("Tell me... have you seen a RED imposter among us?");
            System.out.println("If you have seen this SUSSY imposter, please let me know immediately..."
                    + " otherwise how may I be of assistance?");
            line();

            Scanner sc = new Scanner(System.in);

            while (sc.hasNextLine()) {
                try {
                    String input = sc.nextLine();

                    // Handle empty command
                    if (input.equals("")) {
                        throw new EmptyInputException();
                    }

                    // Create a String array to read various functions
                    String[] strs = input.split(" ");

                    // Store first word as variable
                    String firstWord = strs[0];

                    if (input.equalsIgnoreCase("bye")) {
                        line();
                        System.out.println("Better watch out for the imposter AMONG US!");
                        logo();
                        line();
                        break;
                    } else if (input.equalsIgnoreCase("list")) {
                        printList();
                    } else if (firstWord.equalsIgnoreCase("mark")) {
                        int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
                        mark(listIndex);
                    } else if (firstWord.equalsIgnoreCase("unmark")) {
                        int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
                        unmark(listIndex);
                    } else if (firstWord.equalsIgnoreCase("todo")) {
                        String subString = input.substring(4).trim(); // take the remaining of the input String
                        if (subString.length() == 0) {
                            throw new IncompleteInputException(firstWord);
                        } else {
                            Task toDo = new ToDo(subString);
                            line();
                            System.out.println("     Remember to do your task!");
                            addToList(toDo);
                            line();
                        }
                    } else if(firstWord.equalsIgnoreCase("deadline")) {
                        String subString = input.substring(8).trim(); // take the remaining of the input String
                        if (subString.length() == 0) {
                            throw new IncompleteInputException(firstWord);
                        } else {
                            String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                            if (!temp[1].substring(0,3).equals("by ")) {
                                throw new UnknownInputException();
                            } else {
                                String deadlineDate = temp[1].substring(3); // retrieves the String after '/by'
                                Task deadline = new Deadline(temp[0], deadlineDate);
                                line();
                                System.out.println("     This task is on a timer!");
                                addToList(deadline);
                                line();
                            }
                        }
                    } else if (firstWord.equalsIgnoreCase("event")) {
                        String subString = input.substring(5).trim(); // take the remaining of the input String
                        if (subString.length() == 0) {
                            throw new IncompleteInputException(firstWord);
                        } else {
                            String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                            if (!temp[1].substring(0,3).equals("at ")) {
                                throw new UnknownInputException();
                            } else {
                                String eventDate = temp[1].substring(3); // retrieves the String after '/at'
                                Task event = new Event(temp[0], eventDate);
                                line();
                                System.out.println("     Emergency event on this date!");
                                addToList(event);
                                line();
                            }
                        }
                    } else if (firstWord.equalsIgnoreCase("delete")) {
                        int listIndex = Integer.parseInt(strs[1]); // retrieve the index after delete
                        Task taskToBeDeleted = taskArr.get(listIndex - 1);
                        deleteFromList(taskToBeDeleted);
                    } else {
                        throw new UnknownInputException();
                    }
                } catch(EmptyInputException | UnknownInputException | IncompleteInputException ie) {
                    line();
                    System.out.println(ie.getMessage());
                    line();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
