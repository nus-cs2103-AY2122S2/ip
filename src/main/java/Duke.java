import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String BOT_ART =
            "     /\\   | |   | |          \n" +
            "    /  \\  | |__ | |__  _   _ \n" +
            "   / /\\ \\ | '_ \\| '_ \\| | | |\n" +
            "  / ____ \\| |_) | |_) | |_| |\n" +
            " /_/    \\_\\_.__/|_.__/ \\__, |\n" +
            "                        __/ |\n" +
            "                       |___/ ";
    private static final String BOT_NAME = "Abby";

    private ArrayList<Task> tasks;
    
    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void output(String output) {
        String result =
                "____________________________________________________________\n\n" +
                output +
                "\n____________________________________________________________\n\n";
        System.out.printf("%s", result);
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public void add(String task) {
        String[] taskArr = task.split(" ", 2);
        String type = taskArr[0];
        String result = "Got it. I've added this task:\n";

        if (type.equalsIgnoreCase("deadline")) {
            String[] taskData = taskArr[1].split(" /by ");

            this.tasks.add(new Deadline(taskData[0], taskData[1]));
        } else if (type.equalsIgnoreCase("event")) {
            String[] taskData = taskArr[1].split(" /at ");

            this.tasks.add(new Event(taskData[0], taskData[1]));
        } else {
            this.tasks.add(new Todo(taskArr[1]));
        }

        int noOfTasks = this.tasks.size();
        String pluralTask = noOfTasks > 1 ? "tasks" : "task";

        result += "  " + this.tasks.get(noOfTasks - 1).toString() + "\n";
        result += "Now you have " + noOfTasks + " " + pluralTask + " in the list.";

        output(result);
    }

    public void list() {
        int length = this.tasks.size();
        StringBuilder sb = new StringBuilder();

        if (length == 0) {
            output("No tasks found! Quit lazing around!");
            return;
        }

        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < length; ++i) {
            sb.append(i + 1 + ". " + this.tasks.get(i).toString());

            if (i + 1 != length) {
                sb.append("\n");
            }
        }

        output(sb.toString());
    }

    public void toggleCompleted(boolean isMark, int index) {
        this.tasks.get(--index).setCompleted(isMark);

        String output = isMark ?
                "Nice! I've marked this task as done:\n" :
                "OK, I've marked this task as not done yet:\n";
        String task = "  " + this.tasks.get(index).toString();

        output(output + task);
    }

    public void start() {
        Input input = null;
        Scanner sc = new Scanner(System.in);

        output(BOT_ART + "\nHello! I'm " + BOT_NAME + "\nWhat can I do for you?\n");
            
        while (true) {
            input = new Input(sc.nextLine());

            switch (input.getStates()) {
                case ECHO:
                    output(input.getInput());

                    break;
                case BYE:
                    output(bye());

                    break;
                case ADD:
                    add(input.getInput());

                    break;
                case LIST:
                    list();

                    break;
                case TOGGLE:
                    boolean isMark =
                            input.getInput().split(" ")[0].equalsIgnoreCase("mark");

                    toggleCompleted(isMark, Integer.parseInt(input.getArgs()));

                    break;
                default:
                    break;
            }

            if (input.getStates() == Input.States.BYE) {
                break;
            }
        }

        sc.close(); 
    }

    public static void main(String[] args) {
        Duke abby = new Duke();

        abby.start();
    }
}
