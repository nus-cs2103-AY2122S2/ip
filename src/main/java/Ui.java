import java.util.Scanner;

public class Ui {
    // String helpers
    private final String SPACE = "     ";

    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(SPACE + line);
    }

    public void showGreeting() {
        String greeting = SPACE + "Hello! I'm Duke\n"
                + SPACE + "Nice to meet you!";
        System.out.println(greeting);
    }

    public void showTutorial() {
        String tutorial = SPACE + "Here are my features!\n"
                + SPACE + "To add a ToDo task, input: ToDo/<name>\n"
                + SPACE + "To add a Event task, input: Event/<name>/<date>/<time>\n"
                + SPACE + "To add a Deadline task, input: Deadline/<name>/<date>/<time>\n"
                + SPACE + "To list all tasks, input: list\n"
                + SPACE + "To mark a task as completed, "
                + "input: mark/<task number based on the most recent list call>\n"
                + SPACE + "To unmark a task as completed, "
                + "input: mark/<task number based on the most recent list call>\n"
                + SPACE + "To delete a task, "
                + "input: delete/<task number based on the most recent list call>\n"
                + SPACE + "To exit duke, input: exit\n"
                + SPACE + "To view this manual again, input: help\n"
                + SPACE + "The first word of each command is not case-sensitive!\n"
                + SPACE + "The format of the date should be in the format DDMMYYYY!\n"
                + SPACE + "The format of the time should be in the format HHMM!";
        System.out.println(tutorial);
    }

    public String promptCommand()  {
        System.out.println(SPACE + "Please input your command:");
        this.showLine();
        Scanner sc = new Scanner(System.in);
        String command = "";
        if (sc.hasNext()) {
            command = sc.nextLine();
        }
        return command;
    }

    public void showTaskAdded() {
        System.out.println(SPACE + "Task has been added!");
    }

    public void showTaskMarked() {
        System.out.println(SPACE + "Task has been marked!");
    }

    public void showTaskUnmarked() {
        System.out.println(SPACE + "Task has been unmarked!");
    }

    public void showTaskDeleted() {
        System.out.println(SPACE + "Task has been deleted!");
    }

    public void showList(String tasks) {
        System.out.println(tasks);
    }

    public void showException(Exception e) {
        System.out.println(e.toString());
    }

    public void showLoadingError() {
        System.out.println(SPACE + "Error loading old data!");
    }

    public void showExit() {
        System.out.println(SPACE + "Bye! Hope to see you again soon!");
    }
}
