import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String string = sc.nextLine();
        return string;
    }

    public void showBye() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showHi() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello! I' Duke \nWhat can I do for you?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showMark(Task task) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showUnmark(Task task) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(task);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showOutOfBounds() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Out of bounds!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showAddTask(Task task) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it, I have added this task:" );
        System.out.println(task);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showEmptyMessageError() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("OOPS!!! The description cannot be empty.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showDateTimeParseException() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Date must be in yyyy-mm-dd format!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showSave() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("File saved!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showGeneralException() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Please give a proper command!");
        System.out.println("List of commands: ");
        System.out.println("1. todo\n2. deadline\n3. event\n4. list\n5. mark\n6. unmark\n7. delete \n8. bye");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showDelete(Task task, int taskListSize) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println(String.format("Now you have %d task(s) in the list.", taskListSize));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showSaveError(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    public void list(TaskList taskList) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        taskList.list();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public String getCommandWord(String fullCommand) {
        int itemIndex = -1;
        String firstWord = "";
        int index = fullCommand.indexOf(' ');
        if (index > -1) {
            firstWord = fullCommand.substring(0, index);
        } else {
            firstWord = fullCommand;
        }
        return firstWord;
    }

    public boolean isValidTask(String fullCommand) {
        int index = fullCommand.indexOf(' ');
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidDeadline(String fullCommand) {
        return isValidTask(fullCommand) && fullCommand.contains("/by");
    }

    public boolean isValidEvent(String fullCommand) {
        return isValidTask(fullCommand) && fullCommand.contains("/at");
    }

    public boolean isValidMark(String fullCommand) {
        return true;
    }

    public String getTaskName(String fullCommand) {
        if (getCommandWord(fullCommand).equals("todo")) {
            return fullCommand.substring(fullCommand.indexOf(' ') + 1);
        } else if (getCommandWord(fullCommand).equals("deadline")) {
            return fullCommand.substring(fullCommand.indexOf(' ') + 1, fullCommand.indexOf("/by") - 1);
        } else {
            return fullCommand.substring(fullCommand.indexOf(' ') + 1, fullCommand.indexOf("/at") - 1);
        }
    }

    public LocalDate getTaskDate

}
