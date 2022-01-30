import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArray;
    private Storage storage;

    public TaskList(ArrayList<Task> taskArray, Storage storage) {
        this.taskArray = taskArray;
        this.storage = storage;
    }

    public void addToDo(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            Todo newTodo = new Todo(inputs[1]);
            System.out.println("Bobby heard: " + newTodo);
            taskArray.add(newTodo);
            storage.updateFile(taskArray);
            System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
        } else {
            throw new BobbyException("Description cannot be empty");
        }
    }

    public void addDeadline(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            String[] splitInputs = inputs[1].split(" /by ", 2);
            if (splitInputs.length > 1) {
                String description = splitInputs[0];
                LocalDate by = LocalDate.parse(splitInputs[1]);
                Deadline newDeadline = new Deadline(description, by);
                System.out.println("Bobby heard: " + newDeadline);
                taskArray.add(newDeadline);
                storage.updateFile(taskArray);
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else {
                throw new BobbyException("Date/Time format of Deadline is incorrect or empty");
            }
        } else {
            throw new BobbyException("Description cannot be empty");
        }

    }
    public void addEvent(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            String[] splitInputs = inputs[1].split(" /at ", 2);
            if (splitInputs.length > 1) {
                String description = splitInputs[0];
                String at = splitInputs[1];
                Event newEvent = new Event(description, at);
                System.out.println("Bobby heard: " + newEvent);
                taskArray.add(newEvent);
                storage.updateFile(taskArray);
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else {
                throw new BobbyException("Date/Time format of Event is incorrect or empty");
            }
        } else {
            throw new BobbyException("Description cannot be empty");
        }
    }
    public void delete(String task) throws BobbyException, IOException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length > 1) {
            int i = Integer.parseInt(inputs[1]) - 1;
            System.out.println("Bobby has forgotten this task:\n" + taskArray.get(i));
            taskArray.remove(i);
            storage.updateFile(taskArray);
            System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
        } else {
            throw new BobbyException("Indicate which task should be deleted.");
        }
    }

    public void list() {
        Ui.printList(taskArray);
    }

    public void mark(int i) throws IOException {
        Task t = taskArray.get(i);
        t.markAsDone();
        storage.updateFile(taskArray);
        Ui.taskDone(t);
    }

    public void unmark(int i) throws IOException {
        Task t = taskArray.get(i);
        t.unmarkAsDone();
        storage.updateFile(taskArray);
        Ui.taskNotDone(t);
    }
}
