package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class CommandHandler {
    private final TaskList taskList;
    private final DukeException dukeException;
    private final Ui ui;
    private final Storage storage;


    public CommandHandler(TaskList taskList, DukeException dukeException, Storage storage) {
        this.taskList = taskList;
        this.dukeException = new DukeException();
        this.ui = new Ui();
        this.storage = storage;
    }

    public String handleTodo(String command) {
        try {
            Todo todoTask = new Todo(command.substring(5));
            return taskList.addTask(todoTask);
        } catch (StringIndexOutOfBoundsException noDescription) {
            assert command.substring(5).isEmpty() : "Description empty!";
            return dukeException.noDescriptionException();
        }
    }

    public String handleDeadline(String command) {
        try {
            command = command.replace("deadline", "");
            String[] taskText = command.split(" /by");
            DateTimeFormatter format1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter format3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"); // Not working

            // Create a formatter with the formats
            DateTimeFormatter parser = new DateTimeFormatterBuilder()
                    .appendOptional(format1)
                    .appendOptional(format2)
                    .appendOptional(format3)
                    .toFormatter();
            LocalDate parsedDate = LocalDate.parse(taskText[1].strip(), parser);
            Deadline deadlineTask = new Deadline(taskText[0].strip(), parsedDate);
            return taskList.addTask(deadlineTask);
        } catch (ArrayIndexOutOfBoundsException invalidDeadlineSyntax) {
            return dukeException.invalidDeadlineSyntax();
        } catch (DateTimeParseException wrongDate) {
            String[] taskText = command.split(" /by");
            // This means that there is no date to be parsed, or incorrect format,
            // so treat it as normal string
            Deadline deadlineTask = new Deadline(taskText[0].strip(), taskText[1].strip());
            return taskList.addTask(deadlineTask);
        }
    }

    public String handleEvent(String command) {
        try {
            command = command.replace("event", "");
            String[] taskText = command.split(" /at");
            Event eventTask = new Event(taskText[0].strip(), taskText[1].strip());
            return taskList.addTask(eventTask);
        } catch (ArrayIndexOutOfBoundsException invalidEventSyntax) {
            return dukeException.invalidEventSyntax();
        }
    }

    public String handleList(String command) {
        if (command.contains("allTags")) {
            return ui.displayTags(taskList.getTaskList());
        } else {
            return ui.displayTasks(taskList.getTaskList());
        }
    }

    public String handleDelete(String command) {
        try {
            int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            return taskList.delete(index);
        } catch (NumberFormatException invalidSyntax) {
            return dukeException.invalidDeleteSyntax();
        }
    }

    public String handleFind(String command) {
        String filteredCommand = command.replace("find", "").strip();
        return taskList.find(filteredCommand);
    }

    public String handleTag(String command) {
        try {
            String filteredCommand = command.replace("addTag", "");
            String[] splitCommand = filteredCommand.split(" ");
            String tagDescription = "";
            if (splitCommand[1].isEmpty()) {
                return dukeException.invalidTagSyntax();
            } else {
                tagDescription = splitCommand[2].trim().strip();
            }
            int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            return taskList.tag(index, tagDescription);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException invalidSyntax) {
            return dukeException.invalidTagSyntax();
        }
    }

    public String handleUntag(String command) {
        try {
            String filteredCommand = command.replace("removeTag", "");
            String[] splitCommand = filteredCommand.split(" ");
            int taskIndex = Integer.parseInt(splitCommand[1]);
            int tagIndex = Integer.parseInt(splitCommand[1]);
            return taskList.removeTag(taskIndex, tagIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException invalidSyntax) {
            return dukeException.invalidDeleteTagSyntax();
        }
    }

    public String handleBye(String command) {
        command = command.trim().strip();
        if (command.equals("bye")) {
            storage.saveToTaskList(taskList.getTaskList());
        }
        return ui.displayBye();
    }
}
