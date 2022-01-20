import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void addTask(ArrayList<TaskStorage> taskStorages, int numTask) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + taskStorages.get(taskStorages.size() - 1));
        System.out.println(" Now you have " + numTask +  " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static boolean checkNumeric(String string) {
        boolean numeric = true;
        numeric = string.matches("-?\\d+(\\.\\d+)?");
        if (numeric) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String nextWord = sc.nextLine();
        int taskNumber = 0;
        ArrayList<TaskStorage> storingList = new ArrayList<>();
        BotException exception = new BotException();
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
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 1; i <= storingList.size(); i++) {
                    System.out.println(" " + i + "." + storingList.get(i - 1));
                }
                System.out.println("____________________________________________________________");
            } else if (commandWord.equals("mark")) {
                if (! checkNumeric(restWord)) {
                    exception.notNumeric("mark");
                } else {
                    TaskStorage temp = storingList.get(Integer.parseInt(restWord) - 1);
                    temp.taskDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done: ");
                    System.out.println("  " + temp);
                    System.out.println("____________________________________________________________");
                }
            } else if (commandWord.equals("unmark")) {
                if (! checkNumeric(restWord)) {
                    exception.notNumeric("unmark");
                } else {
                    TaskStorage temp = storingList.get(Integer.parseInt(restWord) - 1);
                    temp.taskUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet: ");
                    System.out.println("  " + temp);
                    System.out.println("____________________________________________________________");
                }
            } else if (commandWord.equals("todo")) {
                if (nextWord.length() == 4) {
                    exception.emptyDescription("todo");
                } else {
                    taskNumber += 1;
                    storingList.add(new TaskStorage(restWord, "T"));
                    addTask(storingList, taskNumber);
                }
            } else if (commandWord.equals("deadline")) {
                if (nextWord.length() == 8) {
                    exception.emptyDescription("deadline");
                } else {
                    taskNumber += 1;
                    storingList.add(new TaskStorage(restWord, "D"));
                    addTask(storingList, taskNumber);
                }
            } else if (commandWord.equals("event")) {
                if (nextWord.length() == 5) {
                    exception.emptyDescription("event");
                } else {
                    taskNumber += 1;
                    storingList.add(new TaskStorage(restWord, "E"));
                    addTask(storingList, taskNumber);
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
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
