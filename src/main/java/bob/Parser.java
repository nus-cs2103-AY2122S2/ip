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
            boolean isTasksDiff = false;

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
                isTasksDiff = true;
                break;
            case "todo":
                response = newToDo(userInput, tasks);
                isTasksDiff = true;
                break;
            case "deadline":
                response = newDeadline(userInput, tasks);
                isTasksDiff = true;
                break;
            case "event":
                response = newEvent(userInput, tasks);
                isTasksDiff = true;
                break;
            case "find":
                response = find(userInput, tasks);
                break;
            case "archive":
                response = archive(tasks);
                isTasksDiff = true;
                break;
            case "commands":
                response = getCommands();
                break;
            default:
                response = Ui.invalidCommand(command);
            }

            if (isTasksDiff) {
                assert storage != null;
                storage.writeTaskListToFile(tasks);
            }
        } catch (InsufficientArgumentsException | ArrayIndexOutOfBoundsException e) {
            response = Ui.insufficientArgs();
        } catch (IndexOutOfBoundsException e) {
            response = Ui.noSuchItem();
        } catch (NumberFormatException e) {
            response = Ui.invalidInt();
        } catch (DateTimeParseException e) {
            response = Ui.invalidTimeFormat();
        } catch (IllegalArgumentException e) {
            response = Ui.insufficientArgs();
        }

        return response;
    }

    private static String update(String command, String userInput, TaskList tasks) {
        int idx = Integer.parseInt(userInput.split(" ")[1]);
        return tasks.update(command, idx);
    }

    private static String newToDo(String userInput, TaskList tasks) throws IllegalArgumentException {
        String todoDesc = userInput.split(" ", 2)[1].trim();
        Task newTodo = new ToDo(todoDesc);
        return tasks.add(newTodo);
    }

    private static String newEvent(String userInput, TaskList tasks) throws InsufficientArgumentsException {
        String time = userInput.split("/at ")[1];
        String eventDesc = userInput.split("/at ")[0].strip();
        if (!eventDesc.contains(" ")) {
            throw new InsufficientArgumentsException("Insufficient arguments provided!");
        }
        eventDesc = eventDesc.substring(eventDesc.indexOf(" ")).strip();

        Task newEvent = new Event(eventDesc, time);
        return tasks.add(newEvent);
    }

    private static String newDeadline(String userInput, TaskList tasks) throws InsufficientArgumentsException {
        String by = userInput.split("/by ")[1];
        String deadlineDesc = userInput.split("/by ")[0].strip();
        if (!deadlineDesc.contains(" ")) {
            throw new InsufficientArgumentsException("Insufficient arguments provided!");
        }
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
