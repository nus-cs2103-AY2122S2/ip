import model.*;
import util.InputParser;
import util.OutputHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.stream.Stream;

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
    private static final String TASK_DELETION_MESSAGE = "Hiya! I've deleted:\n%s\nYou now have %d task(s)!";
    private static final String ERROR_MESSAGE = "I don't understand what '%s' means";
    private static final String INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "Task '%s' is out of bounds (Tasks are 1-indexed!)";

    private final OutputHandler outputHandler;
    private final TaskList taskList;
    private final Path path;

    public ShayBot(String filePath) {
        outputHandler = new OutputHandler();
        path = Paths.get(filePath);
        taskList = loadTaskList(path);
    }

    private TaskList loadTaskList(Path path) {
        try {
            TaskList importedTaskList = new TaskList();
            BufferedReader reader = Files.newBufferedReader(path);
            Stream<String> inputStream = reader.lines();
            inputStream.forEach(line -> tryAddTask(importedTaskList, line));
            reader.close();
            return importedTaskList;
        } catch (IOException e) {
            return new TaskList();
        }
    }

    private String formatTaskForSaveFile(Task task) {
        String type = task.getType();
        String completionStatus = task.getCompletionStatus() ? "1" : "0";
        String taskBody = task.getTaskBody();
        return String.format("%s | %s | %s\n", type, completionStatus, taskBody);
    }

    private void saveTaskList(TaskList taskList) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (int i = 1; i <= taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                writer.write(formatTaskForSaveFile(task));
            }
            writer.close();
        } catch (IOException e) {
            outputHandler.printError(String.format("Uh oh, I can't find '%s'", e.getMessage()));
        }
    }

    private void tryAddTask(TaskList importedTaskList, String line) {
        try {
            Task task = createTask(line);
            importedTaskList.addTask(task);
        } catch (IncorrectTaskFormatException e) {
            outputHandler.print(String.format("File error: %s", e.getMessage()), false);
        }
    }

    //todo: clean up this method to not be a mess.
    private Task createTask(String line) throws IncorrectTaskFormatException {
        String[] parameters = parseFileData(line);
        if (parameters.length < 3) {
            String errorMessage = String.format("'%s' row does not contain at least 3 parameters", line);
            throw(new IncorrectTaskFormatException(errorMessage));
        }
        try {
            int completeness = Integer.parseInt(parameters[1]);
            if (completeness < 0 || completeness > 1) {
                throw new IncorrectTaskFormatException(String.format("Completion isn't 0 or 1: '%s'", parameters[1]));
            }
            boolean isComplete = (completeness == 1);
            Task task;
            switch (parameters[0]) {
            case "T":
            case "Todo":
                task = new TodoTask(parameters[2], isComplete);
                break;
            case "D":
            case "Deadline":
                if (parameters.length < 4) {
                    String errorMessage = String.format("'%s' does not contain at least 4 parameters", line);
                    throw(new IncorrectTaskFormatException(errorMessage));
                }
                task = new DeadlineTask(parameters[2], parameters[3], isComplete);
                break;
            case "E":
            case "Event":
                if (parameters.length < 4) {
                    String errorMessage = String.format("'%s' does not contain at least 4 parameters", line);
                    throw(new IncorrectTaskFormatException(errorMessage));
                }
                task = new EventTask(parameters[2], parameters[3], isComplete);
                break;
            default:
                throw(new IncorrectTaskFormatException(String.format("Unknown type: '%s'", parameters[0])));
            }
            return task;
        } catch (NumberFormatException e) {
            throw (new IncorrectTaskFormatException(String.format("Completion isn't 0 or 1: '%s'", parameters[1])));
        } catch (DateTimeParseException e) {
            throw (new IncorrectTaskFormatException(String.format("%s", e.getMessage())));
        }
    }

    private String[] parseFileData(String line) {
        return line.split(" \\| ");
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
            String operator = InputParser.getOperator(userInput);
            String remainingInput = InputParser.getRemainingInput(userInput, operator);
            switch (operator) {
            case "list":
                listTasks();
                break;
            case "delete":
                deleteTask(remainingInput);
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

    private void addTask(Task task) {
        taskList.addTask(task);
        outputHandler.print(String.format(TASK_ADDITION_MESSAGE, task, taskList.getSize()));
    }

    private void addTodoTask(String taskBody) {
        Task newTask = TodoTask.of(taskBody);
        addTask(newTask);
    }

    private void addDeadlineTask(String taskBody) {
        try {
            Task newTask = DeadlineTask.of(taskBody);
            addTask(newTask);
        } catch (IncorrectTaskFormatException e) {
            outputHandler.printError(e.getMessage());
        }
    }

    private void addEventTask(String taskBody) {
        try {
            Task newTask = EventTask.of(taskBody);
            addTask(newTask);
        } catch (IncorrectTaskFormatException e) {
            outputHandler.printError(e.getMessage());
        }
    }

    private void listTasks() {
        outputHandler.print(taskList.toString());
    }

    private void markTask(String number, boolean isComplete) {
        try {
            Task markedTask = taskList.markTask(Integer.parseInt(number), isComplete);
            outputHandler.print(String.format(isComplete ? MARK_MESSAGE : UNMARK_MESSAGE, markedTask));
        } catch (IndexOutOfBoundsException e) {
            outputHandler.printError(String.format(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE, e.getMessage()));
        } catch (TaskNoChangeException e) {
            outputHandler.printError(String.format("%s", e.getMessage()));
        } catch (NumberFormatException e) {
            outputHandler.printError(String.format(ERROR_MESSAGE, "Task '" + number + "'"));
        }
    }

    private void deleteTask(String number) {
        try {
            Task deletedTask = taskList.deleteTask(Integer.parseInt(number));
            outputHandler.print(String.format(TASK_DELETION_MESSAGE, deletedTask, taskList.getSize()));
        } catch (IndexOutOfBoundsException e) {
            outputHandler.printError(String.format(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE, e.getMessage()));
        } catch (NumberFormatException e) {
            outputHandler.printError(String.format(ERROR_MESSAGE, "Task '" + number + "'"));
        }
    }

    private void endInteraction() {
        outputHandler.print(EXIT_MESSAGE);
        saveTaskList(taskList);
    }
}
