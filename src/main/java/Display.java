import java.util.List;

public class Display {
    private static final String line = "------------------------------------------";
    private static final String greeting = "-> Hi I'm Duke~\n-> What can I do for you?";
    private static final String listMsg = "-> Here are the tasks in your list:";
    private static final String markMsg = "Nice~ I've marked this task as *done*:";
    private static final String unmarkMsg = "Okie~ I've marked this task as *not done* yet:";
    private static final String farewellMsg = "~*~*Cya soon*~*~!";
    private static final String addTaskMsg = "Gotcha~ I've added this task:";
    private static final String deleteTaskMsg = "Noted~ I've removed this task:";

    protected List<Task> tasks;
    Display(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void greeting() {
        System.out.println(" n____n");
        System.out.println("( o v o ) n");
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    public void lists() {
        System.out.println("  n____n");
        System.out.println(" ( o v o )");
        System.out.println("--u-----u---------------------------------");
        System.out.println(listMsg);

        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i - 1));
        }

        System.out.println(line);
    }

    public void farewell() {
        System.out.println("   n____n");
        System.out.println("n ( o v o )");
        System.out.println(line);
        System.out.println(farewellMsg);
        System.out.println(line);
    }

    public void mark(String taskName) {
        System.out.println("   n____n");
        System.out.println("  ( > v < )n");
        System.out.println("---u--------------------------------------");
        System.out.println(markMsg);
        System.out.println("    " + taskName);
        System.out.println(line);
    }

    public void unmark(String taskName) {
        System.out.println("   n____n");
        System.out.println("  ( o _ o )");
        System.out.println(line);
        System.out.println(unmarkMsg);
        System.out.println("    " + taskName);
        System.out.println(line);
    }

    public void tasks(Task t, int total) {
        System.out.println("   n____n");
        System.out.println("  ( ^ v ^ )");
        System.out.println("---u-----u--------------------------------");
        System.out.println(addTaskMsg);
        System.out.println("    " + t);
        System.out.println("Now you have " + total + " task in the list.");
        System.out.println(line);
    }

    public void delete(Task t, int total) {
        System.out.println("   n____n");
        System.out.println("  ( o n o )");
        System.out.println(line);
        System.out.println(deleteTaskMsg);
        System.out.println("    " + t);
        System.out.println("Now you have " + total + " task in the list.");
        System.out.println(line);
    }
}