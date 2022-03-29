package duke;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * A Parser that make sense of user commands.
 */
public class Parser {

        private final Storage storage;
        private final TaskList taskListObj;
        private final Ui ui;
        private final DukeException dukeException;
        private final ArrayList<Task> taskListArr;
        private final CommandHandler commandHandler;
        private boolean isExit;


    /**
     * Constructor for {@code Parser} object.
     * Initiate Setup for Parser, which requires Storage, TaskList, and Ui.
     *
     * @param storage Current Storage
     * @param taskListObj Current TaskList
     * @param ui Ui Implementation
     */
        public Parser(Storage storage, TaskList taskListObj, Ui ui) {
            this.taskListObj = taskListObj;
            this.taskListArr = taskListObj.getTaskList();
            dukeException = new DukeException();
            this.storage = storage;
            this.ui = ui;
            this.isExit = false;
            this.commandHandler = new CommandHandler(taskListObj,dukeException, storage);
        }


    /**
     * Parse User Input, routing input to the correct action.
     *
     * @param command String Value of User Input
     * @return String Message once command is executed.
     */
        public String parse(String command) {
            if (command.equals("bye")) {
                return commandHandler.handleBye(command);
            } else if (command.startsWith("list")) {
                return commandHandler.handleList(command);
            } else if (command.startsWith("mark")) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                return taskListObj.mark(value);
            } else if (command.startsWith("unmark")) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                return taskListObj.unmark(value);
            } else if (command.startsWith("todo")) {
                return commandHandler.handleTodo(command.substring(5));
            } else if (command.startsWith("deadline")) {
                return commandHandler.handleDeadline(command);
            } else if (command.startsWith("event")) {
                return commandHandler.handleEvent(command);
            } else if (command.startsWith("delete")) {
                return commandHandler.handleDelete(command);
            } else if (command.startsWith("find")) {
                return commandHandler.handleFind(command);
            } else if (command.startsWith("addTag")){
                return commandHandler.handleTag(command);
            } else if (command.startsWith("removeTag")) {
                return commandHandler.handleUntag(command);
            } else {
                return dukeException.noSuchCommandException();
            }
        }

    /**
     * Method to check if exitTrigger is triggered.
     *
     * @return Exit Trigger as a boolean
     */
        public boolean isExitTrigger() {
            return isExit;
        }
}
