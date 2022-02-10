package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;


    /**
     * Constructor for Duke class
     *
     * @param filePath path to where the file is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showFileError();
        }
    }

    /**
     * Method to run entire programme
     */
    public String getResponse(String input) throws DukeException {

        try {
            String[] commands = Parser.parse(input.toLowerCase(Locale.ROOT));
            if (commands[0].equals("bye")) {
                return exitSystem();
            } else if (commands[0].equals("list")) {
                return listTask();
            } else if (commands[0].equals("mark")) {
                int indexOfTask = Integer.parseInt(commands[1]);
                return markTask(indexOfTask);
            } else if (commands[0].equals("unmark")) {
                int indexOfTask = Integer.parseInt(commands[1]);
                return unmarkTask(indexOfTask);
            } else if (commands[0].equals("todo")) {
                return addTodoTask(commands[1]);
            } else if (commands[0].equals("event")) {
                return addEventTask(commands[1], commands[2]);
            } else if (commands[0].equals("deadline")) {
                return addDeadlineTask(commands[1], commands[2]);
            } else if (commands[0].equals("delete")) {
                int index = Integer.parseInt(commands[1]);
                return deleteTask(index);
            } else if (commands[0].equals("find")) {
                return findTasks(commands[1]);
            } else if (commands[0].equals("reminder")) {
                return reminder(commands[1]);
            } else {
                return "You have entered an invalid command! :(";
            }
        } catch (DukeException | IOException | IllegalArgumentException e) {
            return ui.showError(e.getMessage());
        }
    }

    private String exitSystem() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 2000);
        return ui.showBye();
    }

    private String listTask() {
        return ui.showTaskList(tasks);
    }

    private String markTask(int indexOfTask) throws IOException {
        // index is not in array
        if (indexOfTask > tasks.size()) {
            return ui.showOutOfArray();
        }
        Task taskToMark = tasks.markTask(indexOfTask - 1);
        storage.updateTaskStatus(indexOfTask, true);
        return ui.showMarkTask(taskToMark);
    }

    private String unmarkTask(int indexOfTask) throws IOException {
        // index is not in array
        if (indexOfTask > tasks.size()) {
            return ui.showOutOfArray();
        }
        Task taskToUnmark = tasks.unmarkTask(indexOfTask - 1);
        storage.updateTaskStatus(indexOfTask, false);
        return ui.showUnmarkTask(taskToUnmark);
    }

    private String addTodoTask(String description) throws IOException {
        Task todoTask = new Todo(description, false);
        tasks.addTask(todoTask);
        String data = "T,0," + description;
        storage.addToFile(data);
        return ui.showAddTask(todoTask, tasks.size());
    }

    private String addDeadlineTask(String description, String date) throws IOException {
        //format date time
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse(date, format);
        Task deadlineTask = new Deadline(description, formattedDate, false);
        tasks.addTask(deadlineTask);
        String data = "D,0," + description + "," + date;
        storage.addToFile(data);
        return ui.showAddTask(deadlineTask, tasks.size());
    }

    private String addEventTask(String description, String date) throws IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse(date, format);
        Task eventTask = new Event(description, formattedDate, false);
        tasks.addTask(eventTask);
        String data = "E,0," + description + "," + date;
        storage.addToFile(data);
        return ui.showAddTask(eventTask, tasks.size());
    }

    private String deleteTask(int indexOfTask) throws IOException {
        if (indexOfTask > tasks.size()) {
            return ui.showOutOfArray();
        }
        Task taskToDelete = tasks.deleteTask(indexOfTask - 1);
        storage.deleteFileData(indexOfTask);
        return ui.showDeleteTask(taskToDelete, tasks.size());
    }

    private String findTasks( String description) {
        TaskList taskList = new TaskList();
        Task[] tasksFound = tasks.find(description);
        int count = 0;
        while (tasksFound[count] != null) {
            taskList.addTask(tasksFound[count]);
            count++;
        }
        return ui.showFindTask(taskList);
    }

    private String reminder(String date) {
        TaskList taskList = new TaskList();
        Task[] remiderTask = tasks.reminder(date);
        int count = 0;
        while (remiderTask[count] != null) {
            taskList.addTask(remiderTask[count]);
            count++;
        }
        return ui.showReminder(taskList, date);
    }
}
