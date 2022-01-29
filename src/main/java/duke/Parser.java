package duke;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Parser {

    private final String[] cmdTypes = {"list", "delete", "unmark", "mark", "todo", "event", "deadline", "bye"};

    /**
     * Gets string and checks if command is in cmdTypes
     * @param input User input
     * @return String array split into command and the rest of the input
     * @throws DukeException Command not in cmdTypes
     */
    public String[] splitCmd(String input) throws DukeException {

        String[] cmd = input.trim().split(" ", 2);
        boolean doesExist = Arrays.asList(cmdTypes).contains(cmd[0].trim().toLowerCase());

        if (doesExist) {
            return cmd;
        } else {
            throw new DukeException("Wrong Command Entered");
        }
    }

    /**
     * Check if cmd is the bye command
     * @param cmd the command that the user input
     * @return true if is "bye" else false
     */
    public boolean checkQuit(String cmd) {
        return cmd.equalsIgnoreCase(cmdTypes[7]);
    }

    /**
     * Parses and runs the command given
     * @param cmd command type
     * @param task task to be parsed and run
     * @param tasks task list of the current program
     * @param ui ui for displaying messages to the user
     * @param storage contains the file object for I/O operations to the save file
     * @throws DukeException A foreseeable error has occurred
     * @throws IOException Save file error occurred
     */
    public void runCmd(String cmd, String task, TaskList tasks, Ui ui, Storage storage)
            throws DukeException, IOException {

        int idx;
        Task newTask;
        String[] parsedTask;
        switch (cmd.toLowerCase().trim()) {

        case "list":
            System.out.println(tasks.toString());
            break;

        case "mark":
            idx = getIdx(task, tasks.getTaskList().size());
            tasks.getTask(idx).setCompleted(true);
            storage.write(tasks.getTaskList());
            ui.showMarked(tasks.getTask(idx));
            break;

        case "unmark":
            idx = getIdx(task, tasks.getTaskList().size());
            tasks.getTask(idx).setCompleted(false);
            storage.write(tasks.getTaskList());
            ui.showUnmarked(tasks.getTask(idx));
            break;

        case "delete":
            idx = getIdx(task, tasks.getTaskList().size());
            ui.showDeleted(tasks.getTask(idx));
            tasks.removeTask(idx);
            storage.write(tasks.getTaskList());
            break;

        case "todo":
            if (task.equals("")) {
                throw new DukeException("There is no task");
            } else {
                newTask = new ToDo(task);
                tasks.addTask(newTask);
                storage.write(tasks.getTaskList());
                ui.showAdded(newTask);
            }
            break;

        case "deadline":
            parsedTask = checkTask(task, "/by");
            newTask = new Deadline(parsedTask[0], getDateTime(parsedTask[1]));
            tasks.addTask(newTask);
            storage.write(tasks.getTaskList());
            ui.showAdded(newTask);
            break;

        case "event":
            parsedTask = checkTask(task, "/at");
            newTask = new Event(parsedTask[0], getDateTime(parsedTask[1]));
            tasks.addTask(newTask);
            storage.write(tasks.getTaskList());
            ui.showAdded(newTask);
            break;
        default:
        }
    }

    /**
     * Checks the validity of the index of the array list
     * @param strIdx index of list in string type
     * @param listSize size of list
     * @return the index of the list
     * @throws DukeException A foreseeable error has occured
     */
    private int getIdx(String strIdx, int listSize) throws DukeException {
        try {
            int idx = Integer.parseInt(strIdx) - 1;

            if (!(idx >= 0 && idx < listSize)) {
                throw new DukeException("duke.Task does not exist");
            }

            return idx;
        } catch (NumberFormatException e) {
            throw new DukeException("There is no index");
        }

    }

    /**
     * Checks the validity of the task in question for event and deadline
     * @param task Task needed to be checked
     * @param keyWord Word ued to separate datetime and description of task
     * @return String array of the split task
     * @throws DukeException Task cannot be parsed
     */
    private String[] checkTask(String task, String keyWord) throws DukeException {

        String[] splitTask = task.split(keyWord, 2);

        if (splitTask.length < 2) {
            throw new DukeException("\"" + keyWord + "\" Not Found");
        } else {
            if (splitTask[0].equals("") && splitTask[1].equals("")) {
                throw new DukeException("No task in instruction");
            } else if (splitTask[0].equals("")) {
                throw new DukeException("Description of task not found");
            } else if (splitTask[1].equals("")) {
                throw new DukeException("Datetime of task not found");
            }
        }

        splitTask[0] = splitTask[0].trim();
        splitTask[1] = splitTask[1].trim();
        return splitTask;
    }

    /**
     * Converts string datetime into Date datetime
     * @param datetime Datetime in string type
     * @return Datetime in Date type
     * @throws DukeException Date cannot be parsed
     */
    private Date getDateTime(String datetime) throws DukeException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy h.mma");

        try {
            return dateFormat.parse(datetime);
        } catch (ParseException e) {
            throw new DukeException("""
                    That is not a date.
                    \t\tRequires this format: dd-MMM-yyyy h.mma
                    \t\tFor Example: 3-jan-2011 1.00pm""");
        }
    }
}
