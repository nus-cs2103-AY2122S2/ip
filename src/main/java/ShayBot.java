import model.*;
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
    private static final String MARK_MESSAGE = "Well done! I've marked the following task as done:\n%s";
    private static final String UNMARK_MESSAGE = "Aight, I've marked the following task as not done yet:\n%s";
    private static final String EXIT_MESSAGE = "Bye~! See you next time!";
    private static final String TASK_ADDITION_MESSAGE = "Hiya! I've added:\n%s\nYou now have %d task(s)!";
    private static final String ERROR_MESSAGE = "I don't understand what '%s' means";

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
                outputHandler.printError(String.format(ERROR_MESSAGE, operator));
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
        outputHandler.print(String.format(TASK_ADDITION_MESSAGE, task, taskList.getSize()));
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
        try {
            String[] taskParameters = parseTaskBody(taskBody);
            if (taskParameters.length != 3) {
                throw(new ShayBotException(String.format(ERROR_MESSAGE, taskBody)));
            } else if (!taskParameters[1].equals("by")) {
                throw(new ShayBotException(String.format(ERROR_MESSAGE, taskParameters[1])));
            }
            Task newTask = new DeadlineTask(taskParameters[0], taskParameters[2]);
            addTask(newTask);
        } catch (ShayBotException e) {
            outputHandler.printError(e.getMessage());
        }
    }

    private void addEventTask(String taskBody) {
        try {
            String[] taskParameters = parseTaskBody(taskBody);
            if (taskParameters.length != 3) {
                throw(new ShayBotException(String.format(ERROR_MESSAGE, taskBody)));
            } else if (!taskParameters[1].equals("at")) {
                throw(new ShayBotException(String.format(ERROR_MESSAGE, taskParameters[1])));
            }
            Task newTask = new EventTask(taskParameters[0], taskParameters[2]);
            addTask(newTask);
        } catch (ShayBotException e) {
            outputHandler.printError(e.getMessage());
        }
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

    private void markTask(int number, boolean isComplete) {
        try {
            Task markedTask = taskList.markTask(number, isComplete);
            outputHandler.print(String.format(isComplete ? MARK_MESSAGE : UNMARK_MESSAGE, markedTask));
        } catch (IndexOutOfBoundsException e) {
            outputHandler.printError(String.format("Task '%s' is out of bounds (Tasks are 1-indexed!)", e.getMessage()));
        } catch (TaskNoChangeException e) {
            outputHandler.printError(String.format("%s", e.getMessage()));
        }
    }

    private void markTask(String number, boolean isComplete) {
        try {
            markTask(Integer.parseInt(number), isComplete);
        } catch (NumberFormatException e) {
            outputHandler.printError(String.format("I'm not sure what you mean by Task '%s'", number));
        }
    }

    private void endInteraction() {
        outputHandler.print(EXIT_MESSAGE);
    }
}
