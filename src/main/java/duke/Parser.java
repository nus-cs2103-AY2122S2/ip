package duke;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Parser {

    private final String[] cmdTypes = {"list", "delete", "unmark", "mark", "todo", "event", "deadline", "bye"};

    public String[] splitCmd(String input) throws DukeException {

        String[] cmd = input.trim().split(" ", 2);
        boolean doesExist = Arrays.asList(cmdTypes).contains(cmd[0].trim().toLowerCase());

        if (doesExist) {
            return cmd;
        } else {
            throw new DukeException("Wrong Command Entered");
        }
    }

    public boolean checkQuit(String cmd) {
        return cmd.equalsIgnoreCase(cmdTypes[7]);
    }

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
