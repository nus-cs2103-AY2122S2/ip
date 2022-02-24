import model.DeadlineTask;
import model.EventTask;
import model.Task;
import model.TodoTask;
import util.OutputHandler;

import java.util.ArrayList;
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
                addBasicTask(remainingInput);
                break;
            case "todo":
                addTodoTask(remainingInput);
                break;
            case "deadline":
                addDeadlineTask(remainingInput);
                break;
            case "event":
                addEventTask(remainingInput);
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

    private void addTask(Task task) {
        taskList.addTask(task);
        outputHandler.print("Hiya! I've added: " + task);
    }

    private void addBasicTask(String task) {
        Task newTask = new Task(task);
        addTask(newTask);
    }

    private void addTodoTask(String taskBody) {
        Task newTask = new TodoTask(taskBody);
        addTask(newTask);
    }

    private void addDeadlineTask(String taskBody) {
        String[] taskParameters = parseTaskBody(taskBody);
        if (taskParameters.length != 3) {
            printError(taskBody);
            return;
        } else if (!taskParameters[1].equals("by")) {
            printError(taskParameters[1]);
            return;
        }
        Task newTask = new DeadlineTask(taskParameters[0], taskParameters[2]);
        addTask(newTask);
    }

    private void addEventTask(String taskBody) {
        String[] taskParameters = parseTaskBody(taskBody);
        if (taskParameters.length != 3) {
            printError(taskBody);
            return;
        } else if (!taskParameters[1].equals("at")) {
            printError(taskParameters[1]);
            return;
        }
        Task newTask = new EventTask(taskParameters[0], taskParameters[2]);
        addTask(newTask);
    }

    private String[] parseTaskBody(String task) {
        ArrayList<String> parameters = new ArrayList<>(0);
        String[] parameterList = task.split(" ", -1);
        StringBuilder curParameter = new StringBuilder();
        for (String str : parameterList) {
            if (str.length() == 0) {
                curParameter.append(" ");
            } else if (str.charAt(0) == '/') {
                if (curParameter.length() > 0) {
                    parameters.add(curParameter.toString());
                    curParameter = new StringBuilder();
                }
                parameters.add(str.substring(1));
            } else {
                if (curParameter.length() > 0) {
                    curParameter.append(" ");
                }
                curParameter.append(str);
            }
        }
        if (curParameter.length() > 0) {
            parameters.add(curParameter.toString());
        }
        return parameters.toArray(new String[0]);
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
