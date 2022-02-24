import model.Task;
import util.OutputHandler;

import java.util.Scanner;

public class ShayBot {
    private static final String INTRO_LOGO = "╭━━━┳╮        ╭━━╮   ╭╮\n"
            + "┃╭━╮┃┃        ┃╭╮┃  ╭╯╰╮\n"
            + "┃╰━━┫╰━┳━━┳╮ ╭┫╰╯╰┳━┻╮╭╯\n"
            + "╰━━╮┃╭╮┃╭╮┃┃ ┃┃╭━╮┃╭╮┃┃\n"
            + "┃╰━╯┃┃┃┃╭╮┃╰━╯┃╰━╯┃╰╯┃╰╮\n"
            + "╰━━━┻╯╰┻╯╰┻━╮╭┻━━━┻━━┻━╯\n"
            + "          ╭━╯┃\n"
            + "          ╰━━╯";
    private static final String INTRO_MESSAGE = "Hiya! I'm ShayBot\n"
            + "How may I help you?";
    private static final String ERROR_MESSAGE = "I'm not sure what you mean by \"%s\"...";
    private static final String MARK_MESSAGE = "Well done! I've marked the following task as done:\n%s";
    private static final String UNMARK_MESSAGE = "Aight, I've marked the following task as not done yet:\n%s";
    private static final String EXIT_MESSAGE = "Bye~! See you next time!";

    private final OutputHandler outputHandler;
    private final TaskList taskList;


    public ShayBot() {
        outputHandler = new OutputHandler();
        taskList = new TaskList();
    }

    public void startInteraction() {
        outputHandler.print(INTRO_LOGO, false);
        outputHandler.print(INTRO_MESSAGE);
        runInteraction();
    }

    private void runInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean isEnded = false;
        while (!isEnded) {
            String userInput = sc.nextLine();
            String operator = getOperator(userInput);
            String remainingInput = getRemainingInput(userInput, operator);
            switch (operator) {
            case "list":
                listTasks();
                break;
            case "add":
                addTask(remainingInput);
                break;
            case "bye":
                endInteraction();
                isEnded = true;
                break;
            case "mark":
                markTask(remainingInput, true);
                break;
            case "unmark":
                markTask(remainingInput, false);
                break;
            default:
                printError(operator);
                break;
            }
        }
        sc.close();
    }

    private String getOperator(String userInput) {
        return userInput.split(" ")[0];
    }

    private String getRemainingInput(String userInput, String operator) {
        if (userInput.length() > operator.length()) {
            return userInput.substring(operator.length() + 1);
        } else {
            return "";
        }
    }

    private void addTask(String task) {
        Task newTask = new Task(task);
        taskList.addTask(newTask);
        outputHandler.print("Hiya! I've added: " + task);
    }

    private void listTasks() {
        outputHandler.print(taskList.toString());
    }

    private void printError(String errorMessage) {
        outputHandler.print(String.format(ERROR_MESSAGE, errorMessage));
    }

    private void markTask(int number, boolean isComplete) {
        try {
            Task markedTask = taskList.markTask(number, isComplete);
            outputHandler.print(String.format(isComplete ? MARK_MESSAGE : UNMARK_MESSAGE, markedTask));
        } catch (IndexOutOfBoundsException e) {
            printError(String.format("Task '%s'", e.getMessage()));
        }
    }

    private void markTask(String number, boolean isComplete) {
        try {
            markTask(Integer.parseInt(number), isComplete);
        } catch (NumberFormatException e) {
            printError(number);
        }
    }

    private void endInteraction() {
        outputHandler.print(EXIT_MESSAGE);
    }
}
