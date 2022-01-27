import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "_______________________________________________";
    private static final String INTRO = "\nHello! I'm YQ\n" + "What can I do for you?\n";
    private static final String LIST = "Here are the tasks in your list:";
    private static final String MARK = "Nice! I've marked this task as done:\n  ";
    private static final String UNMARK = "OK, I've marked this task as not done yet:\n  ";
    private static final String ADDED = "Got it. I've added this task:\n  ";
    private static final String REMOVED = "Noted. I've removed this task:\n";
    private static final String ERROR_UNKNOWN = "OOPS, I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_DESCRIPTION = "OOPS, The description of a command cannot be empty.";

    public static void main(String[] args) throws DukeException {
        ArrayList<Task> database = new ArrayList<>();
        Duke.readFile("data/duke.txt", database);
        System.out.println(database);
        Duke.introMessage();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                System.out.println(LINE);
                if (input.equals("list")) {
                    Duke.listAction(database);
                } else if (input.startsWith("mark")) {
                    String itemNumber = input.substring(5);
                    Duke.markAction(itemNumber, database);
                } else if (input.startsWith("unmark")) {
                    String itemNumber = input.substring(7);
                    Duke.unmarkAction(itemNumber, database);
                } else if (input.startsWith("todo")) {
                    String description = input.substring(5);
                    Duke.toDoAction(description, database);
                } else if (input.startsWith("deadline")) {
                    String[] itemArr = input.substring(9).split(" /by ");
                    Duke.deadlineAction(itemArr, database);
                } else if (input.startsWith("event")) {
                    String[] itemArr = input.substring(6).split(" /at ");
                    Duke.eventAction(itemArr, database);
                } else if (input.startsWith("delete")) {
                    String itemNumber = input.substring(7);
                    deleteAction(itemNumber, database);
                } else {
                    throw new DukeException(ERROR_UNKNOWN);
                }
                System.out.println(LINE);
                //input = new Task(sc.nextLine());
            } catch (IndexOutOfBoundsException e){
                System.out.println(new DukeException(ERROR_DESCRIPTION).getMessage() + "\n" + LINE);
            } catch (DukeException errorMessage) {
                System.out.println(errorMessage.getMessage() + "\n" + LINE);
            }
            input = sc.nextLine();
        }
        writeFile(database);
        System.out.println(LINE + "\n" + "Bye. Hope to see you again soon!" + "\n" + LINE);
    }

    public static void introMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = LINE + INTRO + logo + LINE;
        System.out.println(intro);
    }

    public static void listAction(ArrayList<Task> listOfItems) {
        System.out.println(LIST);
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println(i + 1 + "." + listOfItems.get(i));
        }
    }

    public static void markAction(String taskNumber, ArrayList<Task> listOfItems) {
        int num = Integer.parseInt(taskNumber) - 1;
        Task taskToMark = listOfItems.get(num);
        if (!taskToMark.getStatusIcon().equals("X")) {
            taskToMark.markAsDone();
            listOfItems.set(num, taskToMark);
        }
        System.out.println(MARK + taskToMark);
    }

    public static void unmarkAction(String taskNumber, ArrayList<Task> listOfItems) {
        int num = Integer.parseInt(taskNumber) - 1;
        Task taskToUnmark = listOfItems.get(num);
        if (taskToUnmark.getStatusIcon().equals("X")) {
            taskToUnmark.markAsUndone();
            listOfItems.set(num, taskToUnmark);
        }
        System.out.println(UNMARK + taskToUnmark);
    }

    public static void toDoAction(String description, ArrayList<Task> lisOfItems) throws DukeException {
            Todo todoItem = new Todo(description);
            lisOfItems.add(todoItem);
            int numOfItems = lisOfItems.size();
            System.out.println(ADDED + todoItem);
            System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public static void deadlineAction(String[] arr, ArrayList<Task> listOfItems) throws DukeException {
        Deadline deadlineItem = new Deadline(arr[0], convertDate(arr[1]));
        listOfItems.add(deadlineItem);
        int numOfItems = listOfItems.size();
        System.out.println(ADDED + deadlineItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public static void eventAction(String[] arr, ArrayList<Task> listOfItems) throws DukeException {
        Event eventItem = new Event(arr[0], convertDate(arr[1]));
        listOfItems.add(eventItem);
        int numOfItems = listOfItems.size();
        System.out.println(ADDED + eventItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public static void deleteAction(String taskNumber, ArrayList<Task> listOfItems) {
        int num = Integer.parseInt(taskNumber) - 1;
        Task itemToDelete = listOfItems.get(num);
        listOfItems.remove(num);
        int numOfItems = listOfItems.size();
        System.out.println(REMOVED + itemToDelete);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public static void readFile(String path, ArrayList<Task> itemList) throws DukeException {
        File information = new File(path);
        if (information.exists()) {
            try {
                File file = new File(path);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    String[] data = text.split(" \\| ");
                    String initial = data[0];
                    Task history;
                    switch (initial) {
                        case "T":
                            history = new Todo(data[2]);
                            break;
                        case "D":
                            history = new Deadline(data[2], convertDate(data[3]));
                            break;
                        case "E":
                            history = new Event(data[2], convertDate(data[3]));
                            break;
                        default:
                            throw new DukeException("Cannot understand the command");
                    }
                    if (data[1].equals("mark")) {
                        history.markAsDone();
                    }
                    itemList.add(history);
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("Cannot find file...");
            }
        }
    }

    public static void writeFile(ArrayList<Task> itemList) throws DukeException {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            String path = directory.getAbsolutePath() + "/Duke.txt";
            File newFile = new File(path);
            FileWriter writer = new FileWriter(newFile);
            StringBuilder data = new StringBuilder();
            for (Task item : itemList) {
                data.append(item.formatString()).append("\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error... try again");
        }
    }

    public static LocalDate convertDate(String dateString) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDate.parse(dateString, format);
        } catch (DateTimeParseException error) {
            throw new DukeException("Wrong Format...");
        }
    }
}
