package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.data.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Priority;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads from file
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        try {
            loadFileHandler(taskList, file);
        } catch (FileNotFoundException e) {
            Ui ui = new Ui();
            if (file.getParentFile().mkdirs()) {
                createOrReadFile(file, ui);
            }
        }
        return taskList;
    }

    /**
     * Saves to file
     *
     * @param taskList tasks to be saved.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList.getTasks()) {
                writer.write(task.saveData());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            Ui ui = new Ui();
            ui.showMessage(e.getMessage());
        }
    }

    private void loadFileHandler(ArrayList<Task> taskList, File file) throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            List<String> input = Arrays.asList(sc.nextLine().split(" \\| "));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            switch (input.get(0)) {
            case "T":
                ToDo newTodo = new ToDo(input.get(3).trim());
                taskList.add(newTodo);
                int todoIndex = taskList.indexOf(newTodo);
                if (input.get(1).equals("1")) {
                    newTodo = newTodo.mark();
                    taskList.set(todoIndex, newTodo);
                }
                handlePriority(taskList, input, todoIndex, newTodo);
                break;
            case "D":
                Deadline newDeadline = new Deadline(input.get(3).trim(),
                        LocalDate.parse(input.get(4).trim(), formatter));
                taskList.add(newDeadline);
                int deadLineIndex = taskList.indexOf(newDeadline);
                if (input.get(1).equals("1")) {
                    newDeadline = newDeadline.mark();
                    taskList.set(deadLineIndex, newDeadline.mark());
                }
                handlePriority(taskList, input, deadLineIndex, newDeadline);
                break;
            case "E":
                Event newEvent = new Event(input.get(3).trim(),
                        LocalDate.parse(input.get(4).trim(), formatter));
                taskList.add(newEvent);
                int eventIndex = taskList.indexOf(newEvent);
                if (input.get(1).equals("1")) {
                    taskList.set(eventIndex, newEvent.mark());
                }
                handlePriority(taskList, input, eventIndex, newEvent);
                break;
            default:
                assert false : input.get(0);
                throw new DukeException("Unknown type");
            }
        }
        sc.close();
    }

    private void createOrReadFile(File file, Ui ui) {
        try {
            if (file.createNewFile()) {
                ui.showFileCreated();
            }
        } catch (IOException err) {
            ui.showIoException();
        }
        assert false;
    }

    private void handlePriority(ArrayList<Task> taskList, List<String> input, int taskIndex, Task newTask) {
        switch (input.get(2)) {
        case "HIGH":
            newTask = newTask.setPriority(Priority.HIGH);
            taskList.set(taskIndex, newTask.setPriority(Priority.HIGH));
            break;
        case "NORM":
            taskList.set(taskIndex, newTask.setPriority(Priority.NORMAL));
            break;
        case "LOW ":
            taskList.set(taskIndex, newTask.setPriority(Priority.LOW));
            break;
        default:
            assert false : "Unknown priority";
        }
    }
}
