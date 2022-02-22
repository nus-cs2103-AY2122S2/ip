package duke;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * A Parser that make sense of user commands.
 */
public class Parser {

        private final Storage storage;
        private final TaskList taskListObj;
        private final Ui ui;
        private final DukeException dukeException;
        private final ArrayList<Task> taskListArr;
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
        }


    /**
     * Parse User Input, routing input to the correct action.
     *
     * @param command String Value of User Input
     * @return String Message once command is executed.
     */
        public String parse(String command) {
            command = command.trim().strip();
            if (command.equals("bye")) {
                storage.saveToTaskList(taskListArr);
                isExit = true;
                return ui.displayBye();
            } else if (command.startsWith("list")) {
                if(command.contains("allTags")) {
                    return ui.displayTags(taskListArr);
                } else {
                    return ui.displayTasks(taskListArr);
                }
            } else if (command.startsWith("mark")) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                return taskListObj.mark(value);
            } else if (command.startsWith("unmark")) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                return taskListObj.unmark(value);
            } else if (command.startsWith("todo")) {
                try {
                    Todo todoTask = new Todo(command.substring(5));
                    return taskListObj.addTask(todoTask);
                } catch (StringIndexOutOfBoundsException noDescription) {
                   return dukeException.noDescriptionException();
                }
            } else if (command.startsWith("deadline")) {
                command = command.replace("deadline", "");
                String[] taskText = command.split(" /by");
                try {
                    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    DateTimeFormatter format3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"); // Not working

                    // Create a formatter with the formats
                    DateTimeFormatter parser = new DateTimeFormatterBuilder()
                            .appendOptional(format1)
                            .appendOptional(format2)
                            .appendOptional(format3)
                            .toFormatter();
                    LocalDate parsedDate = LocalDate.parse(taskText[1].strip(),parser);
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), parsedDate);
                    return taskListObj.addTask(deadlineTask);
                } catch(ArrayIndexOutOfBoundsException invalidDeadlineSyntax) {
                    return dukeException.invalidDeadlineSyntax();
                } catch(DateTimeParseException wrongDate) {
                    // This means that there is no date to be parsed, or incorrect format,
                    // so treat it as normal string
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), taskText[1].strip());
                    return taskListObj.addTask(deadlineTask);
                }
            } else if (command.startsWith("event")) {
                try {
                    command = command.replace("event", "");
                    String[] taskText = command.split(" /at");
                    Event eventTask = new Event(taskText[0].strip(), taskText[1].strip());
                    return taskListObj.addTask(eventTask);
                } catch(ArrayIndexOutOfBoundsException invalidEventSyntax) {
                    return dukeException.invalidEventSyntax();
                }
            } else if (command.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    return taskListObj.delete(index);
                } catch(NumberFormatException invalidSyntax) {
                    return dukeException.invalidDeleteSyntax();
                }
            } else if (command.startsWith("find")) {
                String filteredCommand = command.replace("find", "").strip();
                return taskListObj.find(filteredCommand);
            } else if (command.startsWith("addTag")){
                try {
                    String filteredCommand = command.replace("addTag", "");
                    String[] splitCommand = filteredCommand.split(" ");
                    String tagDescription = "";
                    if(splitCommand[1].isEmpty()) {
                        return dukeException.invalidTagSyntax();
                    } else {
                        tagDescription = splitCommand[2].trim().strip();
                    }
                    int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    return taskListObj.tag(index, tagDescription);
                } catch(NumberFormatException | ArrayIndexOutOfBoundsException invalidSyntax) {
                    return dukeException.invalidTagSyntax();
                }
            } else if (command.startsWith("removeTag")) {
                try {
                    String filteredCommand = command.replace("removeTag", "");
                    String[] splitCommand = filteredCommand.split(" ");
                    int taskIndex = Integer.parseInt(splitCommand[1]);
                    int tagIndex = Integer.parseInt(splitCommand[1]);
                    return taskListObj.removeTag(taskIndex,tagIndex);
                } catch(NumberFormatException | ArrayIndexOutOfBoundsException invalidSyntax) {
                    return dukeException.invalidDeleteTagSyntax();
                }
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
