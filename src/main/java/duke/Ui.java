package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Contains all ui functions.
 */
public class Ui {
    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String string = sc.nextLine();
        return string;
    }

    public void showMessage(String message) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(message);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    public String welcomeString() {
        String welcome = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " +
                "\nHello! I' Duke " +
                "\nWhat can I do for you?" +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        return welcome;
    }

    public String markString(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    public String unmarkString(Task task) {
        return "OK, I've marked this task as incomplete: \n" + task;
    }

    public String outOfBoundsString() {
        return "Out of bounds!";
    }

    public String addTaskString(Task task) {
        return "Got it, I have added this task: \n" + task;
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

    public String saveString() {
        return "File saved!";
    }

    public String deleteString(Task task, int taskListSize) {
        return "Noted. I've removed this task: \n" + task + "\n" +
                String.format("Now you have %d task(s) in the list.", taskListSize);
    }

    public String saveErrorString(IOException e) {

        return "Something went wrong: " + e.getMessage();
    }

    public String list(TaskList taskList) {
        return taskList.list();
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
        if (getCommandWord(fullCommand).equals("todo") || getCommandWord(fullCommand).equals("find")) {
            int index = fullCommand.indexOf(' ');
            if (index == -1) {
                return false;
            } else {
                return true;
            }
        } else if (getCommandWord(fullCommand).equals("deadline")) {
            if (fullCommand.indexOf("deadline") + 9 >= fullCommand.indexOf("/by")) {
                return false;
            } else {
                return true;
            }
        } else if (getCommandWord(fullCommand).equals("event")) {
            if (fullCommand.indexOf("event") + 6 >= fullCommand.indexOf("/at")) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isValidDeadline(String fullCommand) {
        return isValidTask(fullCommand) && fullCommand.contains("/by");
    }

    public boolean isValidEvent(String fullCommand) {
        return isValidTask(fullCommand) && fullCommand.contains("/at");
    }

    public boolean isValidMarkFormat(String fullCommand) {
        try {
            Integer.parseInt(fullCommand.substring(fullCommand.indexOf(' ') + 1));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidUnmarkFormat(String fullCommand) {
        try {
            Integer.parseInt(fullCommand.substring(fullCommand.indexOf(' ') + 1));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int markIndex(String fullCommand) {
        return Integer.parseInt(fullCommand.substring(fullCommand.indexOf(' ') + 1));
    }

    public String getTaskName(String fullCommand) {
        if (getCommandWord(fullCommand).equals("todo") || getCommandWord(fullCommand).equals("find")) {
            return fullCommand.substring(fullCommand.indexOf(' ') + 1);
        } else if (getCommandWord(fullCommand).equals("deadline")) {
                return fullCommand.substring(fullCommand.indexOf(' ') + 1, fullCommand.indexOf("/by") - 1);
        } else {
            return fullCommand.substring(fullCommand.indexOf(' ') + 1, fullCommand.indexOf("/at") - 1);
        }
    }

    public LocalDate getTaskDate(String fullCommand) throws InvalidDateException {
        if (getCommandWord(fullCommand).equals("deadline")) {
            return LocalDate.parse(fullCommand.substring(fullCommand.indexOf("/by") + 4));
        } else {
            return LocalDate.parse(fullCommand.substring(fullCommand.indexOf("/at") + 4));
        }
    }

    public String findTask(String fullCommand, TaskList taskList) {
        String taskName = getTaskName(fullCommand);
        return taskList.find(taskName);
    }

    public String errorString(Exception exception) {
        return exception.getMessage();
    }
}
