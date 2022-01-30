import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> taskArray = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you");
        System.out.println("__________________________________");
        try {
            File myObj = new File("data/duke.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        listen();
    }

    public static void listen(){
        Scanner dukeScan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            try  {
                String userInput = dukeScan.nextLine();
                String[] command = parseInput(userInput);
                switch (command[0]) {
                    case "bye":
                        running = false;
                        break;
                    case "list":
                        listItem();
                        break;
                    case "mark":
                        markItem(command);
                        break;
                    case "unmark":
                        unmarkItem(command);
                        break;
                    case "delete":
                        deleteItem(command);
                        break;
                    default:
                        addItem(command);
                }
                FileWriter myWriter =  new FileWriter("data/duke.txt");
                myWriter.write(writeItem());
                myWriter.close();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("__________________________________");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }

        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    public static String[] parseInput(String input) {
        String[] inputArray = input.split(" ");
        String[] tempArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
        return new String[] {inputArray[0], String.join(" ", tempArray)};
    }


    public static void addItem(String[] command) throws DukeException{
        String input = command[0];
        Task task;
        String title;
        String time;
        switch (input) {
        case "todo":
            if (command[1].isEmpty()) {
                throw new DukeException("Please tell me what you need to do");
            }
            task = new Todo(command[1]);
            break;
        case "deadline":
            if (command[1].isEmpty()) {
                throw new DukeException("Please tell me what you need to do");
            }
            try {
                title = command[1].split(" /by ")[0];
                time = command[1].split(" /by ")[1];
                task = new Deadline(title, time);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please tell me the deadline in this format: <Activity> /by <Time>");
            }
            break;
        case "event":
            if (command[1].isEmpty()) {
                throw new DukeException("Please tell me what you need to do");
            }
            try {
                title = command[1].split(" /at ")[0];
                time = command[1].split(" /at ")[1];
                task = new Event(title, time);
            } catch (IndexOutOfBoundsException e){
                throw new DukeException("Please tell me when your event is in this format: <Activity> /at <Time>");
            }
            break;
        default:
            throw new DukeException("Sorry I don't understand what that is :(");
        }

        taskArray.add(task);
        System.out.println("__________________________________");
        System.out.println("added: " + task);
        System.out.printf("You have %d tasks in your list\n", taskArray.size());
        System.out.println("__________________________________");
    }

    public static void listItem() {
        System.out.println("__________________________________");
        if (taskArray.size() == 0) {
            System.out.println("No items in the list");
        } else {
            for (int i = 0; i < taskArray.size(); i++) {
                System.out.printf("%d. " + taskArray.get(i)+ "\n", i+1);
            }
        }
        System.out.println("__________________________________");
    }

    public static void markItem(String[] command) {
        int index = Integer.parseInt(command[1]);
        taskArray.get(index-1).setChecked();
        System.out.println("__________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskArray.get(index-1));
        System.out.println("__________________________________");
    }

    public static void unmarkItem(String[] command) {
        int index = Integer.parseInt(command[1]);
        taskArray.get(index-1).setUnChecked();
        System.out.println("__________________________________");
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskArray.get(index-1));
        System.out.println("__________________________________");
    }

    public static void deleteItem(String[] command) {
        int index = Integer.parseInt(command[1]);
        System.out.println("__________________________________");
        System.out.println("Noted, I've removed this task from the list: ");
        System.out.println(taskArray.remove(index-1));
        System.out.printf("You have %d tasks left\n", taskArray.size());
        System.out.println("__________________________________");
    }

    public static String writeItem() {
        StringBuilder list = new StringBuilder();
        if (taskArray.size() == 0) {
            list = new StringBuilder("No items in the list");
        } else {
            for (int i = 0; i < taskArray.size(); i++) {
                String line = String.format("%d. " + taskArray.get(i)+ "\n", i+1);
                list.append(line);
            }
        }
        return list.toString();
    }
}
