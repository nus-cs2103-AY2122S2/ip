import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> ls = new ArrayList<>();

    public static void main(String[] args) {
        initDuke();
        Scanner sc = new Scanner(System.in);
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

            String action = wordSplit[0];
            String[] split = userInput.split("/");
            int start = userInput.indexOf(" ") + 1;

            if (userInput.equals("bye")) {
                sayBye();
                break;
            } else if (action.equals("list")) {
                //prints the list
                printList();
            } else if (action.equals("mark")) {
                //mark
                mark(Integer.parseInt(wordSplit[1]) - 1);
            } else if (action.equals("unmark")) {
                //unmark
                unmark(Integer.parseInt(wordSplit[1]) - 1);
            } else if (action.equals("todo")) {
                //add ToDos
                addTask(new ToDos(userInput.substring(start), false));
            } else if (action.equals("deadline") || action.equals("event")) {
                int end = userInput.lastIndexOf('/');
                String task = userInput.substring(start, end - 1);
                String details = split[1].substring(3);
                if (action.equals("deadline")) {
                    //add DeadLines
                    addTask(new DeadLines(task, false, details));
                } else {
                    //add Events
                    addTask(new Events(task, false, details));
                }
            } else if (action.equals("delete")) {
                //delete task
                deleteTask(Integer.parseInt(wordSplit[1]) - 1);
            }

        }
        sc.close();
    }

    private static void deleteTask(int indx) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(ls.remove(indx));
        String s = String.format("Now you have %d tasks in the list.", ls.size());
        System.out.println(s);
        printHorizontalLine();
    }

    private static void checker(String[] splitInput) throws TaskException {
        if (splitInput.length == 0 || notKeyWord(splitInput[0])) {
            throw new IncorrectInputException();

        } else if (splitInput.length == 1) {
            String command = splitInput[0];
            if (command.equals("todo")) {
                throw new ToDosException();
            } else if (command.equals("deadline")) {
                throw new DeadlineException();
            } else if (command.equals("event")) {
                throw new EventException();
            } else if (!command.equals("bye") && !command.equals("list")) {
                throw new IncorrectLengthException();
            }
        }
    }

    private static boolean notKeyWord(String command) {
        return (!command.equals("bye") && !command.equals("list")
                && !command.equals("delete")  && !command.equals("mark") && !command.equals("unmark")
                && !command.equals("todo") && !command.equals("deadline") && !command.equals("event"));
    }

    private static void mark(int indx) {
        System.out.println("Nice! I've marked this task as done: ");
        Task t = ls.get(indx);
        t.setMarked(true);
        System.out.println(t);
        printHorizontalLine();
    }

    private static void unmark(int indx) {
        System.out.println("OK, I've marked this task as not done yet: ");
        Task t = ls.get(indx);
        t.setMarked(false);
        System.out.println(t);
        printHorizontalLine();
    }

    private static void addTask(Task t) {
        ls.add(t);
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + t);
        String s = String.format("Now you have %d tasks in the list.", ls.size());
        System.out.println(s);
        printHorizontalLine();
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            String num = String.format("%d. ", i + 1);
            System.out.println(num + ls.get(i).toString());
        }
        printHorizontalLine();
    }

    private static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void initDuke() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

}
