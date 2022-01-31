package duke.task;

import duke.exception.DukeException;
import duke.Storage;
import duke.Ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> taskList;

    // Constructor for making new file
    public TaskList() throws DukeException {
        try {
            Storage.createNewFolderAndTextFile();
            taskList = new ArrayList<>();
        } catch (IOException err) {
            throw new DukeException("Could not create file for you!");
        }
    }

    // Constructor for loading existing file
    public TaskList(List<String> data) throws DukeException {
        if (data == null) {
            throw new DukeException("Oops Ekud could not find the file");
        } else {
            taskList = new ArrayList<>();
            for (String d : data) {
                String[] splitData = d.split(" , ");
                switch (splitData[0]) {
                case "T":
                    ToDo toDo = new ToDo(splitData[2]);
                    if (splitData[1].equals("1")) {
                        toDo.mark();
                    }
                    this.taskList.add(toDo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(splitData[2],
                            LocalDate.parse(splitData[3]));
                    if (splitData[1].equals("1")) {
                        deadline.mark();
                    }
                    this.taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(splitData[2],
                            LocalDate.parse(splitData[3]));
                    if (splitData[1].equals("1")) {
                        event.mark();
                    }
                    this.taskList.add(event);
                    break;
                default:
                }
            }
        }
    }

    public void clearTaskList(TaskList tasks, Storage storage) throws IOException {
        tasks.taskList = new ArrayList<>();
        storage.writeToFile(tasks.taskList);
    }

    public int getTaskSize() {
        return this.taskList.size();
    }

    public Task getTask(int position) {
        return this.taskList.get(position - 1);
    }

    public void removeTask(int position) {
        this.taskList.remove(position - 1);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void addToList(Task task, Ui ui, Storage storage) throws IOException {
        taskList.add(task);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(task.taskDescriptionForFile()
                + System.lineSeparator());
        storage.appendToFile(stringBuilder.toString());
        ui.showAddTaskMessage(this, task);
    }
}

