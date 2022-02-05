package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main duke application class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadListFromDisk());
        } catch (FileNotFoundException err) {
            ui.displayLoadError();
            tasks = new TaskList();
        }
    }

    public String[] getEventTimings(String text) {
        // /at 2/12/2022 1800 to 1900
        String fullDateString = text.split("/at")[1].trim();
        String[] timingSplit = fullDateString.split("to");

        if (timingSplit.length < 2) {
            System.out.println("Error, duke.Event must contain both start & end timings!");
            return new String[]{};
        } else {
            String dateText = timingSplit[0].trim().split(" ")[0];
            String startTimeText = timingSplit[0].trim().split(" ")[1];
            String endTimeText = timingSplit[1].trim();
            String startDateText = dateText + " " + startTimeText;
            String endDateText = dateText + " " + endTimeText;
            String[] eventTimings = new String[] {startDateText, endDateText};
            return eventTimings;
        }
    }

    public String getDeadlineTiming(String text) {
        String dateText = text.split("/by")[1].trim();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(dateText, format);
        return dateText;
    }

    public String runListCommand() {
        return this.ui.displayTaskList(tasks.getTasks());
    }

    /**
     * Runs Level 5, 6 & 7 version of the app, Exception handling,
     * Hard disk storage
     *
     * @throws DukeException for checked errors handled by duke.Duke app
     */
    public String run(String input) throws DukeException {
        Command command = Parser.parse(input);

        if (command.equals(Command.BYE)) {
            try {
                storage.saveListOnDisk(tasks);
            } catch (IOException err) {
                System.out.println("Error occurred while trying to save list data to disk");
                err.printStackTrace();
            }

            return this.ui.displayExitMsg();


        } else if (command.equals(Command.LIST)) {
            System.out.println("LIST COMMAND CALLED");
            return runListCommand();

        } else if (command.equals(Command.MARK)) {
            int itemIndex = Integer.parseInt(input.split(" ")[1]);
            Task selectedTask = tasks.getTask(itemIndex - 1);
            selectedTask.markAsComplete();
            return this.ui.displayMarkMsg(selectedTask.toString());

        } else if (command.equals(Command.UNMARK)) {
            int itemIndex = Integer.parseInt(input.split(" ")[1]);
            Task selectedTask = tasks.getTask(itemIndex - 1);
            selectedTask.markAsIncomplete();
            return this.ui.displayUnmarkMsg(selectedTask.toString());

        } else if (command.equals(Command.DEADLINE)) {
            String endTime = getDeadlineTiming(input);
            String taskName = input.replaceAll("deadline", "").split("/by")[0];
            Deadline newDeadline = new Deadline(taskName, endTime);
            tasks.addTask(newDeadline);
            return this.ui.displayListedText(newDeadline, tasks.getSize());

        } else if (command.equals(Command.EVENT)) {
            String[] eventTimings = getEventTimings(input);
            String taskName = input.replaceAll("event", "").split("/at")[0];
            Event newEvent = new Event(taskName, eventTimings[0], eventTimings[1]);
            tasks.addTask(newEvent);
            return this.ui.displayListedText(newEvent, tasks.getSize());

        } else if (command.equals(Command.TODO)) {
            String taskName = input.replaceAll("todo", "");

            if (input.split(" ").length <= 1) {
                throw new TodoEmptyException();
            }

            Todo newTodo = new Todo(taskName);
            tasks.addTask(newTodo);
            return this.ui.displayListedText(newTodo, tasks.getSize());

        } else if (command.equals(Command.DELETE)) {
            if (input.split("").length <= 1) {
                throw new DeleteEmptyException();
            }

            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            try {
                Task deletedTask = tasks.removeTaskIndex(taskIndex - 1);
                if (deletedTask.getEventType() == Type.EVENT) {
                    Event deletedEvent = (Event) deletedTask;
                    return this.ui.displayDeletedMessage(deletedEvent, tasks.getSize());
                } else if (deletedTask.getEventType() == Type.DEADLINE) {
                    Deadline deletedDeadline = (Deadline) deletedTask;
                    return this.ui.displayDeletedMessage(deletedDeadline, tasks.getSize());
                } else if (deletedTask.getEventType() == Type.TODO) {
                    Todo deletedTodo = (Todo) deletedTask;
                    return this.ui.displayDeletedMessage(deletedTodo, tasks.getSize());
                }
            } catch (IndexOutOfBoundsException err) {
                throw new DukeException("task index provided is invalid :(");
            }

        } else if (command.equals(Command.FIND)) {
            String searchTerm = input.split(" ")[1];
            TaskList foundTasks = tasks.findTask(searchTerm);
            return this.ui.displayFoundTaskList(foundTasks);

        } else {
            throw new UnknownCommandException();
        }

        return "";
    }
}
