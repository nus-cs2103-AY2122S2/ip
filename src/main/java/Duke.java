import java.util.*;

public class Duke {

    public static class ToDo extends Task {
        public ToDo(String taskName) {
            super(taskName);
        }
        @Override
        public String toString() {
            return String.format("[T][%s] %s", this.done ? "X" : " ", this.taskName);
        }
    }

    public static class Deadline extends Task {
        public String deadline;
        public Deadline(String taskName, String deadline) {
            super(taskName);
            this.deadline = deadline;
        }
        @Override
        public String toString() {
            return String.format("[D][%s] %s (by: %s)",
                    this.done ? "X" : " ", this.taskName, this.deadline);
        }
    }

    public static class Event extends Task {
        public String eventTime;
        public Event(String taskName, String eventTime) {
            super(taskName);
            this.eventTime = eventTime;
        }
        @Override
        public String toString() {
            return String.format("[E][%s] %s (at: %s)",
                    this.done ? "X" : " ", this.taskName, this.eventTime);
        }
    }
    public static class Task {
        public boolean done = false;
        public String taskName;
        public Task(String taskName) {
            this.taskName = taskName;
        }
        public void setDone(boolean newDone) {
            this.done = newDone;
        }
        @Override
        public String toString() {
            return String.format("[%s] %s", this.done ? "X" : " ", this.taskName);
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

    public static void addTask(String taskString) {
        int firstSpace = taskString.indexOf(" ");
        String firstWord = taskString.substring(0, firstSpace);

        String taskName;
        Task t;

        switch (firstWord) {
            case "todo":
                taskName = taskString.substring(5);
                t = new ToDo(taskName);
                break;
            case "deadline":
                int byIdx = taskString.indexOf("/by");
                taskName = taskString.substring(9, byIdx-1);  // "deadline " has 9 characters
                String taskDeadline = taskString.substring(byIdx + 4);  // "/by " has 4 characters
                t = new Deadline(taskName, taskDeadline);
                break;
            case "event":
                int atIdx = taskString.indexOf("/at");
                taskName = taskString.substring(6, atIdx-1);  // "event " has 9 characters
                String taskTime = taskString.substring(atIdx + 4);  // "/at " has 4 characters
                t = new Event(taskName, taskTime);
                break;
            default:
                t = new Task(taskString);
        }
        allTasks.add(t);
        String[] messages = new String[] {
                "Got it. I've added this task:",
                t.toString(),
                "Now you have " + allTasks.size() + " tasks in the list."
        };

        prettyPrint(messages);
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

            if (userInput.equals("bye")) {  // end
                prettyPrint(closingMessage);
                finished = true;
            } else if (userInput.equals("list")) {  // display tasks
                displayTasks();
            } else if (userInput.startsWith("mark")) {  // mark task as done
                char lastChar = userInput.charAt(userInput.length() - 1);
                int taskToMark = Character.getNumericValue(lastChar);
                markTask(taskToMark - 1);
            } else if (userInput.startsWith("unmark")) {  // mark task as undone
                char lastChar = userInput.charAt(userInput.length() - 1);
                int taskToUnmark = Character.getNumericValue(lastChar);
                unmarkTask(taskToUnmark - 1);
            } else {  // add task
                addTask(userInput);
            }

        }
    }
}
