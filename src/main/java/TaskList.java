import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> taskList;

    // Constructor for making new file
    public TaskList() throws DukeException {
        try {
            File directory = new File("data");
            directory.mkdir();
            File file = new File(directory, "ekud.txt");
            file.createNewFile();
            taskList = new ArrayList<>();
        } catch (IOException err) {
            throw new DukeException("Could not create file for you!");
        }
    }

    // Constructor for loading existing file
    public TaskList(List<String> data) throws DukeException {
        if (data == null) {
            throw new DukeException("OH NO!");
        } else {
            taskList = new ArrayList<>();
            for (String d : data) {
                String[] splicedS = d.split(" , ");
                switch (splicedS[0]) {
                case "T":
                    ToDo toDo = new ToDo(splicedS[2]);
                    if (splicedS[1].equals("1")) {
                        toDo.mark();
                    }
                    this.taskList.add(toDo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(splicedS[2], LocalDate.parse(splicedS[3]));
                    if (splicedS[1].equals("1")) {
                        deadline.mark();
                    }
                    this.taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(splicedS[2], LocalDate.parse(splicedS[3]));
                    if (splicedS[1].equals("1")) {
                        event.mark();
                    }
                    this.taskList.add(event);
                    break;
                default:
                }
            }
        }
    }

    public void clearTaskList() {
        this.taskList = new ArrayList<>();
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
        stringBuilder.append(task.taskDescriptionForFile() + System.lineSeparator());
        storage.appendToFile(stringBuilder.toString());
        ui.showAddTaskMessage(this, task);
    }

    public void markTask(Storage storage, Ui ui, int position) throws DukeException, IOException {
        if (position < 1 || position > taskList.size()) {
            throw new DukeException("Task do not exist!");
        } else if (taskList.get(position - 1).isDone == true) {
            throw new DukeException("Task is already marked as done!");
        } else {
            taskList.get(position - 1).mark();
            storage.setInFile(position, taskList.get(position - 1).taskDescriptionForFile());
            ui.showMarkMessage(taskList.get(position - 1));
        }
    }

    public void unmarkTask(Storage storage, Ui ui, int position) throws DukeException, IOException {
        if (position < 1 || position > taskList.size()) {
            throw new DukeException("Task do not exist!");
        } else if (taskList.get(position - 1).isDone == false) {
            throw new DukeException("Task is already marked as not done!");
        } else {
            taskList.get(position - 1).unmark();
            storage.setInFile(position, taskList.get(position - 1).taskDescriptionForFile());
            //ui.showUnmarkMessage(taskList.get(position - 1));
        }
    }

    public void deleteTask(Storage storage, Ui ui, int position) throws DukeException, IOException {
        if (position < 0 || position > taskList.size()) {
            throw new DukeException("Task do not exist!");
        } else {
            Task task = taskList.get(position - 1);
            taskList.remove(position - 1);
            storage.writeToFile(taskList);
            ui.showDeleteMessage(task, taskList.size());
        }
    }

    public void showTaskList() {
        if (taskList.size() == 0) {
            System.out.println("You have not added any tasks!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i).toString());
            }
        }
    }
}

