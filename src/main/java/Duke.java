import Tasks.DeadLines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;
import exceptions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;


public class Duke {
    private static final ArrayList<Task> LIST = new ArrayList<>();
    private static final String PATH_OF_CACHE = "./data/duke.txt";

    public static void main(String[] args) {
        initDuke();
        Scanner sc = new Scanner(System.in);
        loadCache();
        loadDukeMainLogic(sc);
        sc.close();
    }

    private static void loadCache() {
        File data = new File(PATH_OF_CACHE);
        try {
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] arr = str.split("|");
                String type = arr[0];
                boolean marked = arr[2].equals("1");
                String remainingStr = str.substring(str.lastIndexOf("|") + 1);
                switch (type) {
                case "D":
                    LIST.add(new DeadLines(
                            str.substring(4, str.lastIndexOf("|")),
                            marked, remainingStr));
                    break;
                case "E":
                    LIST.add(new Events(str.substring(4, str.lastIndexOf("|")),
                            marked, remainingStr));
                    break;
                case "T":
                    LIST.add(new ToDos(remainingStr, marked));
                    break;
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no cache, " +
                    "duke will be initialised as per normal.");
        }
    }


    private static void loadDukeMainLogic(Scanner sc) {
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String[] wordSplit = userInput.split(" ");

            try {
                checker(wordSplit);
            } catch(TaskException e) {
                System.out.println(e.getMessage());
                printHorizontalLine();
                continue;
            }

            writeCache();
            String action = wordSplit[0];
            String[] split = userInput.split("/");
            int start = userInput.indexOf(" ") + 1;
            int end = userInput.lastIndexOf('/');
            String task = end == -1 ? "" : userInput.substring(start, end - 1);
            String details = split.length > 1 ? split[1].substring(3) : "";

            if (userInput.equals("bye")) {
                sayBye();
                break;
            }
            switch (action) {
            case "list":
                printList();
                break;
            case "mark":
                mark(Integer.parseInt(wordSplit[1]) - 1);
                break;
            case "unmark":
                unmark(Integer.parseInt(wordSplit[1]) - 1);
                break;
            case "todo":
                addTask(new ToDos(userInput.substring(start), false));
                break;
            case "deadline":
                addTask(new DeadLines(task, false, details));
                break;
            case "event":
                addTask(new Events(task, false, details));
                break;
            case "delete":
                deleteTask(Integer.parseInt(wordSplit[1]) - 1);
                break;
            }
        }
    }

    private static void deleteTask(int indx) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(LIST.remove(indx));
        String s = String.format("Now you have %d tasks in the list.", LIST.size());
        System.out.println(s);
        printHorizontalLine();
    }

    private static void checker(String[] splitInput) throws TaskException {
        if (splitInput.length == 0 || notKeyWord(splitInput[0])) {
            throw new IncorrectInputException();

        } else if (splitInput.length == 1) {
            String command = splitInput[0];
            switch (command) {
            case "todo":
                throw new ToDosException();
            case "deadline":
                throw new DeadlineException();
            case "event":
                throw new EventException();
            }
        } else {
            String command = splitInput[0];
            if (command.equals("mark") || command.equals("unmark")) {
                try {
                    Integer.parseInt(splitInput[1]);
                } catch (NumberFormatException e) {
                    throw new WrongInputException();
                }
            }
        }
    }

    private static boolean notKeyWord(String command) {
        return (!command.equals("bye") && !command.equals("list")
                && !command.equals("delete")  && !command.equals("mark") && !command.equals("unmark")
                && !command.equals("todo") && !command.equals("deadline") && !command.equals("event"));
    }

    private static void mark(int indx) {
        System.out.println("Nice! I've marked this task as done:");
        Task t = LIST.get(indx);
        t.setMarked(true);
        System.out.println(t);
        printHorizontalLine();
    }

    private static void unmark(int indx) {
        System.out.println("OK, I've marked this task as not done yet:");
        Task t = LIST.get(indx);
        t.setMarked(false);
        System.out.println(t);
        printHorizontalLine();
    }

    private static void writeCache() {
        try {
            File f = new File(PATH_OF_CACHE);
            FileWriter fw = new FileWriter(f);
            for (Task t : LIST) {
                fw.write(t.cacheString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }

    private static void addTask(Task t) {
        LIST.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + t);
        String s = String.format("Now you have %d tasks in the list.", LIST.size());
        System.out.println(s);
        printHorizontalLine();
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < LIST.size(); i++) {
            String num = String.format("%d. ", i + 1);
            System.out.println(num + LIST.get(i).toString());
        }
        printHorizontalLine();
    }

    private static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("_____________" +
                "_______________________________________________");
    }

    private static void initDuke() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

}
