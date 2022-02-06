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
    public static void parse(String userInput, TaskList tasks, Storage storage) {
        try {
            String cmd = userInput.split(" ")[0].strip();
            boolean tasksIsDiff = false;

            switch (cmd) {
            case "list":
                tasks.list();
                break;

            case "mark":
                //fallthrough
            case "unmark":
                //fallthrough
            case "delete":
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                tasks.update(cmd, idx);
                tasksIsDiff = true;
                break;

            case "todo":
                Task newTodo = new ToDo(userInput.split(" ", 2)[1]);
                tasks.add(newTodo);
                tasksIsDiff = true;
                break;

            case "deadline":
                String by = userInput.split("/by ")[1];
                String deadlineDesc = userInput.split("/by ")[0];
                deadlineDesc = deadlineDesc.substring(deadlineDesc.indexOf(" ")).strip();

                Task newDeadline = new Deadline(deadlineDesc, by);
                tasks.add(newDeadline);
                tasksIsDiff = true;
                break;

            case "event":
                String time = userInput.split("/at ")[1];
                String eventDesc = userInput.split("/at ")[0].strip();
                eventDesc = eventDesc.substring(eventDesc.indexOf(" ")).strip();

                Task newEvent = new Event(eventDesc, time);
                tasks.add(newEvent);
                tasksIsDiff = true;
                break;

            case "find":
                String keyword = userInput.split(" ", 2)[1];
                tasks.find(keyword);
                break;

            default:
                Ui.invalidCommand(cmd);
            }

            if (tasksIsDiff) {
                storage.writeTaskListToFile(tasks);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.insufficientArgs();
        } catch (IndexOutOfBoundsException e) {
            Ui.noSuchItem();
        } catch (NumberFormatException e) {
            Ui.invalidInt();
        } catch (DateTimeParseException e) {
            Ui.invalidTimeFormat();
        }
    }
}
