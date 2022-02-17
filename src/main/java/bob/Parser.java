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
            String cmd = userInput.split(" ")[0].strip();
            boolean tasksIsDiff = false;

            switch (cmd) {
            case "list":
                response = tasks.list();
                break;

            case "mark":
                //fallthrough
            case "unmark":
                //fallthrough
            case "delete":
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                response = tasks.update(cmd, idx);
                tasksIsDiff = true;
                break;

            case "todo":
                Task newTodo = new ToDo(userInput.split(" ", 2)[1]);
                response = tasks.add(newTodo);
                tasksIsDiff = true;
                break;

            case "deadline":
                String by = userInput.split("/by ")[1];
                String deadlineDesc = userInput.split("/by ")[0];
                deadlineDesc = deadlineDesc.substring(deadlineDesc.indexOf(" ")).strip();

                Task newDeadline = new Deadline(deadlineDesc, by);
                response = tasks.add(newDeadline);
                tasksIsDiff = true;
                break;

            case "event":
                String time = userInput.split("/at ")[1];
                String eventDesc = userInput.split("/at ")[0].strip();
                eventDesc = eventDesc.substring(eventDesc.indexOf(" ")).strip();

                Task newEvent = new Event(eventDesc, time);
                response = tasks.add(newEvent);
                tasksIsDiff = true;
                break;

            case "find":
                String keyword = userInput.split(" ", 2)[1];
                response = tasks.find(keyword);
                break;

            default:
                response = Ui.invalidCommand(cmd);
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
}
