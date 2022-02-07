package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.exception.DukeException;
import duke.ui.MessageUi;

/**
 * A class that manages the functions of the task list. This class contains
 * two constructor, one for when the file exist in the file path of which
 * will be loaded to this class' tasklist, else creates a new file in the
 * file path.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     * @throws DukeException
     */
    public TaskList() {
            tasks = new ArrayList<>();
    }

    /**
     * Constructor for when there is a text file in the specified directory.
     * @param data String list containing the information of task list.
     * @throws DukeException If task list is not found.
     */
    public TaskList(List<String> data) throws DukeException {
        if (data == null) {
            throw new DukeException("Oops Ekud could not find the file");
        } else {
            tasks = new ArrayList<>();
            for (String d : data) {
                String[] splitData = d.split(" , ");
                switch (splitData[0]) {
                case "T":
                    ToDo toDo = new ToDo(splitData[2]);
                    if (splitData[1].equals("1")) {
                        toDo.mark();
                    }
                    this.tasks.add(toDo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(splitData[2],
                            LocalDate.parse(splitData[3]));
                    if (splitData[1].equals("1")) {
                        deadline.mark();
                    }
                    this.tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(splitData[2],
                            LocalDate.parse(splitData[3]));
                    if (splitData[1].equals("1")) {
                        event.mark();
                    }
                    this.tasks.add(event);
                    break;
                default:
                }
            }
        }
    }

    /**
     * Clear the task list and creates a overwrites exising text file
     * with empty text.
     * @param tasks Existing task list.
     * @param storage Storage class.
     * @throws DukeException If text file is not found.
     */
    public void clearTaskList(TaskList tasks, Storage storage) throws DukeException {
        tasks.tasks = new ArrayList<>();
        storage.writeToFile(tasks.tasks);
    }

    public int getTaskSize() {
        return tasks.size();
    }

    public Task getTask(int position) {
        return tasks.get(position - 1);
    }

    public void removeTask(int position) {
        tasks.remove(position - 1);
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Add a task to the task list.
     * @param task Task.
     * @param ui MessageUi class.
     * @param storage Storage class.
     * @return Task added message.
     * @throws DukeException If text file cannot be found.
     */
    public String addToList(Task task, MessageUi ui, Storage storage) throws DukeException {
            tasks.add(task);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(task.taskDescriptionForFile()
                    + System.lineSeparator());
            storage.appendToFile(stringBuilder.toString());
            return ui.showAddTaskMessage(this, task);
    }
}

