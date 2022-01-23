import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final static String DIRECTORY = "data";
    private final static String FILE_NAME = "Duke.txt";
    private final static String PATH = DIRECTORY + "/" + FILE_NAME;

    public enum TypicalString {
        LONG_LINE {
            public String toString() {
                return "____________________________________________________________";
            }
        },
        ADDED_TASK {
            public String toString() {
                return " Got it. I've added this task:";
            }
        },
        HELLO {
            public String toString() {
                return " Hello! I'm Duke";
            }
        },
        GOODBYE {
            public String toString() {
                return " Bye. Hope to see you again soon!";
            }
        }
    }

    private static void printTask(ArrayList<TaskStorage> taskStorages, int numTask) {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(TypicalString.ADDED_TASK);
        System.out.println("  " + taskStorages.get(taskStorages.size() - 1));
        System.out.println(" Now you have " + numTask +  " tasks in the list.");
        System.out.println(TypicalString.LONG_LINE);
    }

    private static void readFileContent(File database, ArrayList<TaskStorage> storingList) throws IOException {
        FileReader fr = new FileReader(database);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String type = String.valueOf(line.charAt(2));
            String status = String.valueOf(line.charAt(6));
            String description = line.substring(10, line.length() - 2);
            TaskStorage task;
            if (type.equals("D") || type.equals("E")) {
                task = new TaskStorage(description.replace("| ", "/"), type);
            } else {
                task = new TaskStorage(description, type);
            }
            if (! status.equals("1")) {
                task.taskDone();
            }
            storingList.add(task);
        }
    }

    private static boolean checkNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }


    private static void changeStatusTask(
            int lineNumber, File database, ArrayList<TaskStorage> storingList)
            throws IOException {
        List<String> lines = Files.readAllLines(database.toPath());
        lines.set(lineNumber, storingList.get(lineNumber).toFileText());
        Files.write(database.toPath(), lines);
    }

    private static void addTaskToText(ArrayList<TaskStorage> storingList) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH, true);
        TaskStorage taskStorage = storingList.get(storingList.size() - 1);
        fileWriter.write(taskStorage.toFileText() + System.lineSeparator());
        fileWriter.close();
    }

    private static void deleteTask(int lineNumber, File database) throws IOException {
        List<String> lines = Files.readAllLines(database.toPath());
        lines.remove(lineNumber);
        Files.write(database.toPath(), lines);
    }

    public static void main(String[] args) throws IOException {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(TypicalString.HELLO);
        System.out.println(" What can I do for you?");
        System.out.println(TypicalString.LONG_LINE);
        File database = new File(DIRECTORY + "/" + FILE_NAME);

        Scanner sc = new Scanner(System.in);
        String nextWord = sc.nextLine();
        int taskNumber = 0;
        ArrayList<TaskStorage> storingList = new ArrayList<>();
        BotException exception = new BotException();

        if (! database.exists()) {
            File directory = new File(DIRECTORY);
            directory.mkdir();
            database.createNewFile();
        }

        if (!(database.length() == 0)) {
            readFileContent(database, storingList);
            taskNumber = storingList.size();
        }

        int commandIndex = nextWord.indexOf(" ");
        String commandWord;
        String restWord;
        if (commandIndex == -1) {
            commandWord = nextWord;
            restWord = nextWord;
        } else {
            commandWord = nextWord.substring(0, commandIndex);
            restWord = nextWord.substring(commandIndex + 1);
        }

        while (! nextWord.equals("bye")) {
            if (commandWord.equals("list")) {
                System.out.println(TypicalString.LONG_LINE);
                System.out.println(" Here are the tasks in your list:");
                for (int i = 1; i <= storingList.size(); i++) {
                    System.out.println(" " + i + "." + storingList.get(i - 1));
                }
                System.out.println(TypicalString.LONG_LINE);
            } else if (commandWord.equals("mark")) {
                if (! checkNumeric(restWord)) {
                    exception.notNumeric("mark");
                } else {
                    int index = Integer.parseInt(restWord) - 1;
                    TaskStorage temp = storingList.get(index);
                    temp.taskDone();
                    System.out.println(TypicalString.LONG_LINE);
                    System.out.println(" Nice! I've marked this task as done: ");
                    System.out.println("  " + temp);
                    System.out.println(TypicalString.LONG_LINE);
                    changeStatusTask(index, database, storingList);
                }
            } else if (commandWord.equals("unmark")) {
                if (! checkNumeric(restWord)) {
                    exception.notNumeric("unmark");
                } else {
                    int index = Integer.parseInt(restWord) - 1;
                    TaskStorage temp = storingList.get(index);
                    temp.taskUndone();
                    System.out.println(TypicalString.LONG_LINE);
                    System.out.println(" OK, I've marked this task as not done yet: ");
                    System.out.println("  " + temp);
                    System.out.println(TypicalString.LONG_LINE);
                    changeStatusTask(index, database, storingList);
                }
            } else if (commandWord.equals("todo")) {
                if (nextWord.length() == 4) {
                    exception.emptyDescription("todo");
                } else {
                    taskNumber += 1;
                    storingList.add(new TaskStorage(restWord, "T"));
                    printTask(storingList, taskNumber);
                    addTaskToText(storingList);
                }
            } else if (commandWord.equals("deadline")) {
                if (nextWord.length() == 8) {
                    exception.emptyDescription("deadline");
                } else {
                    taskNumber += 1;
                    storingList.add(new TaskStorage(restWord, "D"));
                    printTask(storingList, taskNumber);
                    addTaskToText(storingList);
                }
            } else if (commandWord.equals("event")) {
                if (nextWord.length() == 5) {
                    exception.emptyDescription("event");
                } else {
                    taskNumber += 1;
                    storingList.add(new TaskStorage(restWord, "E"));
                    printTask(storingList, taskNumber);
                    addTaskToText(storingList);
                }
            } else if (commandWord.equals("delete")) {
                if (! checkNumeric(restWord)) {
                    exception.notNumeric("delete");
                } else {
                    int index = Integer.parseInt(restWord) - 1;
                    TaskStorage temp = storingList.remove( index);
                    System.out.println(TypicalString.LONG_LINE);
                    System.out.println(" Noted. I've removed this task: ");
                    System.out.println("  " + temp);
                    taskNumber -= 1;
                    System.out.println(" Now you have " + taskNumber + " tasks in the list.");
                    System.out.println(TypicalString.LONG_LINE);
                    deleteTask(index, database);
                }
            } else {
                exception.wrongSyntax();
            }
            nextWord = sc.nextLine();
            commandIndex = nextWord.indexOf(" ");
            if (commandIndex == -1) {
                commandWord = nextWord;
                restWord = nextWord;
            } else {
                commandWord = nextWord.substring(0, commandIndex);
                restWord = nextWord.substring(commandIndex + 1);
            }
        }
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(TypicalString.GOODBYE);
        System.out.println(TypicalString.LONG_LINE);
    }
}
