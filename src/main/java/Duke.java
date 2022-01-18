import java.util.*;

public class Duke {

    public static class Task {
        public boolean done = false;
        public String name;

        public Task(String taskName) {
            this.name = taskName;
        }

        public void setDone(boolean newDone) {
            this.done = newDone;
        }

        public boolean isDone() {
            return this.done;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", this.done ? "X" : " ", this.name);
        }
    }

    public static String indent = "    ";
    public static String separator = "--------------------------------------------";
    public static String[] openingMessage = new String[] {"Hello! I'm Duke", "What can I do for you?"};
    public static String closingMessage = "Bye. Hope to see you again soon!";

    public static ArrayList<Task> allTasks = new ArrayList<>();

    public static void printIndent(String s) {
        System.out.println(indent + s);
    }

    public static void prettyPrint(String s) {
        printIndent(separator);
        printIndent(s);
        printIndent(separator + "\n");
    }

    public static void prettyPrint(String[] messages) {
        printIndent(separator);
        for (String message : messages) printIndent(message);
        printIndent(separator + "\n");
    }

    public static void displayTasks() {
        printIndent(separator);
        printIndent("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            printIndent(String.format("%d. %s", i+1, allTasks.get(i)));
        }
        printIndent(separator + "\n");
    }

    public static void addTask(String taskName) {
        Task t = new Task(taskName);
        allTasks.add(t);
        prettyPrint(String.format("added: %s", taskName));
    }

    public static void markTask(int taskNum) {
        Task thisTask = allTasks.get(taskNum);
        thisTask.setDone(true);
        prettyPrint(new String[] {"Nice! I've marked this task as done:", thisTask.toString()});
    }

    public static void unmarkTask(int taskNum) {
        Task thisTask = allTasks.get(taskNum);
        thisTask.setDone(false);
        prettyPrint(new String[] {"Ok, I've marked this task as not done yet:", thisTask.toString()});
    }

    public static void main(String[] args) {
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";

        // introduction messages
        System.out.println(logo);
        prettyPrint(openingMessage);

        // read input
        Scanner sc = new Scanner(System.in);
        String userInput;
        boolean finished = false;
        while (!finished) {
            userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                prettyPrint(closingMessage);
                finished = true;
            } else if (userInput.equals("list")) {
                displayTasks();
            } else if (userInput.startsWith("mark")) {
                char lastChar = userInput.charAt(userInput.length() - 1);
                int taskToMark = Character.getNumericValue(lastChar);
                markTask(taskToMark - 1);
            } else if (userInput.startsWith("unmark")) {
                char lastChar = userInput.charAt(userInput.length() - 1);
                int taskToUnmark = Character.getNumericValue(lastChar);
                unmarkTask(taskToUnmark - 1);
            } else {
                addTask(userInput);
            }

        }
    }
}
