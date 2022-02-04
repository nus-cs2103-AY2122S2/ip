import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Waits for user input and returns it
     *
     * @return Next line of input from user
     */
    public String getNextLine() {
        return sc.nextLine();
    }

    /**
     * Duke's standard output line
     *
     * @return String of a line
     */
    private static String line() {
        String line = "____________________________________________________________";
        return line;
    }

    /**
     * Formatted output for a confirmation of a new added task
     *
     * @param task Newly added task
     */
    public static void printAddTask(Task task) {
        System.out.println(Ui.line());
        System.out.println(task.toString());
        System.out.println(Ui.line());
    }

    public static void printExit() {
        String EXIT = "Duke terminated";
        System.out.println(Ui.line());
        System.out.println(EXIT);
        System.out.println(Ui.line());
    }

    public static void printTaskList(String formattedTaskList) {
        System.out.println(Ui.line());
        System.out.println(formattedTaskList);
        System.out.println(Ui.line());
    }

}
