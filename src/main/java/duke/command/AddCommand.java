package duke.command;

import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Tasks;
import duke.task.Todos;
import duke.task.Events;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddCommand extends Commands {
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String SUCCESS_MESSAGE = "    Command Executed Successfully";
    public static final String FAILURE_MESSAGE = "    'Add' Command Executed Unsuccessfully";

    private static boolean IS_EXIT = false;
    private String commandWord;
    private String arguments; // In the form of user duke.command

    public AddCommand(String commandWord, String arguments) {
        this.commandWord = commandWord;
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return IS_EXIT;
    }

    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Tasks> createdTaskList = new ArrayList<Tasks>();
        try {
            switch (commandWord) {
                case COMMAND_TODO:
                    createdTaskList.add(new Todos(arguments));
                    break;

                case COMMAND_EVENT:
                    createdTaskList.add(new Events(arguments.split(" /at ", 2)[0],
                            arguments.split(" /at ", 2)[1]));
                    break;

                case COMMAND_DEADLINE:
                    createdTaskList.add(new Deadlines(arguments.split(" /by ", 2)[0],
                            arguments.split(" /by ", 2)[1]));
                    break;
            }

            if (createdTaskList.size() > 0) {
                if (tasks.addsTask(createdTaskList.get(0), storage)) {
                    return new CommandResult(SUCCESS_MESSAGE);
                }
            }

        } catch (IndexOutOfBoundsException err) {
            System.out
                    .println("    Addition of tasks unsuccessful, ensure that you are writing in the correct format.");
        } catch (DateTimeParseException err) {
            System.out
                    .println(
                            "    Addition of tasks unsuccessful, ensure that the date is valid, and goes by the foramt of (YYYY-MM-DD).");
        }
        return new CommandResult(FAILURE_MESSAGE);
    }
}
