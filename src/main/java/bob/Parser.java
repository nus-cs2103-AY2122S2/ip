package bob;

import java.time.format.DateTimeParseException;

/**
 * The Parser class consists of static methods to handle and execute user input commands.
 */
public class Parser {
    /**
     * Parses user input and executes the desired command.
     *
     * @param userInput String input from user.
     * @param tasks     Current task list.
     * @param storage   Storage instance associated with current Bob program.
     */
    public static String parse(String userInput, TaskList tasks, Storage storage) {
        String response;
        assert !userInput.isEmpty();
        assert tasks != null;
        try {
            String command = userInput.split(" ")[0].strip().toLowerCase();
            boolean tasksIsDiff = false;

            switch (command) {
            case "list":
                response = tasks.list();
                break;
            case "mark":
                //fallthrough
            case "unmark":
                //fallthrough
            case "delete":
                response = update(command, userInput, tasks);
                tasksIsDiff = true;
                break;
            case "todo":
                response = newToDo(userInput, tasks);
                tasksIsDiff = true;
                break;
            case "deadline":
                response = newDeadline(userInput, tasks);
                tasksIsDiff = true;
                break;
            case "event":
                response = newEvent(userInput, tasks);
                tasksIsDiff = true;
                break;
            case "find":
                response = find(userInput, tasks);
                break;
            case "archive":
                response = archive(tasks);
                tasksIsDiff = true;
                break;
            case "command":
                response = getCommands();
                break;
            default:
                response = Ui.invalidCommand(command);
            }

            if (tasksIsDiff) {
                assert storage != null;
                storage.writeTaskListToFile(tasks);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            response = Ui.insufficientArgs();
        } catch (IndexOutOfBoundsException e) {
            response = Ui.noSuchItem();
        } catch (NumberFormatException e) {
            response = Ui.invalidInt();
        } catch (DateTimeParseException e) {
            response = Ui.invalidTimeFormat();
        }

        return response;
    }

    private static String update(String command, String userInput, TaskList tasks) {
        int idx = Integer.parseInt(userInput.split(" ")[1]);
        return tasks.update(command, idx);
    }

    private static String newToDo(String userInput, TaskList tasks) {
        Task newTodo = new ToDo(userInput.split(" ", 2)[1]);
        return tasks.add(newTodo);
    }

    private static String newEvent(String userInput, TaskList tasks) {
        String time = userInput.split("/at ")[1];
        String eventDesc = userInput.split("/at ")[0].strip();
        eventDesc = eventDesc.substring(eventDesc.indexOf(" ")).strip();

        Task newEvent = new Event(eventDesc, time);
        return tasks.add(newEvent);
    }

    private static String newDeadline(String userInput, TaskList tasks) {
        String by = userInput.split("/by ")[1];
        String deadlineDesc = userInput.split("/by ")[0];
        deadlineDesc = deadlineDesc.substring(deadlineDesc.indexOf(" ")).strip();

        Task newDeadline = new Deadline(deadlineDesc, by);
        return tasks.add(newDeadline);
    }

    private static String find(String userInput, TaskList tasks) {
        String keyword = userInput.split(" ", 2)[1];
        return tasks.find(keyword);
    }

    private static String getCommands() {
        return Ui.command();
    }

    private static String archive(TaskList tasks) {
        tasks.archive();
        return Ui.archive();
    }
}
