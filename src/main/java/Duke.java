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
            String action = wordSplit[0];
            if (userInput.equals("bye")) {
                sayBye();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else if (action.equals("mark")) {
                mark(Integer.parseInt(wordSplit[1]) - 1);
            } else if (action.equals("unmark")) {
                unmark(Integer.parseInt(wordSplit[1]) - 1);
            } else if (action.equals("todo")) {
                //add ToDos
                int start = userInput.indexOf(" ") + 1;
                addInput(new ToDos(userInput.substring(start), false));
            } else if (action.equals("deadline")) {
                //add DeadLines
                String[] split = userInput.split("/");
                int start = userInput.indexOf(" ") + 1;
                int end = userInput.lastIndexOf('/');
                String task = userInput.substring(start, end - 1);
                String details = split[1].substring(3);
                addInput(new DeadLines(task, false, details));
            } else if (action.equals("event")) {
                //add Events
                String[] split = userInput.split("/");
                int start = userInput.indexOf(" ") + 1;
                int end = userInput.lastIndexOf('/');
                String task = userInput.substring(start, end - 1);
                String details = split[1].substring(3);
                addInput(new Events(task, false, details));
            } else {
                //add normal task
                addInput(new Task(userInput, false));
            }
        }
        sc.close();
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

    private static void addInput(Task t) {
        printHorizontalLine();
        ls.add(t);
        System.out.println(" Got it. I've added this task: ");
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
        printHorizontalLine();
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
