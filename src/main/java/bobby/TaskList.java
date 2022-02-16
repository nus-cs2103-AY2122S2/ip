package bobby;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * The TaskList class executes commands and updates the list accordingly.
 */
public class TaskList {
    private ArrayList<Task> taskArray;
    private Stack<State> stateStack;
    private State currentState;
    private Storage storage;

    /**
     * Constructor to create an instance of TaskList.
     *
     * @param taskArray ArrayList storing Tasks.
     * @param storage used to update the list stored in the .txt file.
     */
    public TaskList(ArrayList<Task> taskArray, Storage storage) {
        this.stateStack = new Stack<State>();
        stateStack.push(new State(new ArrayList<Task>(taskArray)));
        this.taskArray = taskArray;
        this.storage = storage;
    }

    /**
     * Adds a Task of type Todo.
     *
     * @param task description of the task.
     * @return Program output to confirm execution of command to user.
     * @throws BobbyException if the description of the task is empty.
     */
    public String addToDo(String task) throws BobbyException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length <= 1) {
            throw new BobbyException("Description cannot be empty.");
        }

        Todo newTodo = new Todo(inputs[1]);
        taskArray.add(newTodo);
        storage.updateFile(taskArray);
        updateStateArray();

        return Ui.printAddedTask(newTodo, taskArray);
    }

    /**
     * Adds a Task of type Deadline.
     *
     * @param task description of Deadline and when the task has to be completed in YYYY-MM-DD format.
     * @return Program output to confirm execution of command to user.
     * @throws BobbyException when description is empty or date format is invalid.
     */
    public String addDeadline(String task) throws BobbyException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length <= 1) {
            throw new BobbyException("Description cannot be empty.");
        }

        String[] descAndDate = inputs[1].split(" /by ", 2);
        if (descAndDate.length <= 1) {
            throw new BobbyException("Invalid date format. Please use YYYY-MM-DD.");
        }

        String description = descAndDate[0];
        LocalDate by = LocalDate.parse(descAndDate[1]);
        Deadline newDeadline = new Deadline(description, by);
        taskArray.add(newDeadline);
        storage.updateFile(taskArray);
        updateStateArray();

        return Ui.printAddedTask(newDeadline, taskArray);
    }

    /**
     * Adds a Task of type Event.
     *
     * @param task description of Event and date/time of the Event.
     * @return Program output to confirm execution of command to user.
     * @throws BobbyException when description or date/time is empty.
     */
    public String addEvent(String task) throws BobbyException {
        String[] inputs = task.split(" ", 2);
        if (inputs.length <= 1) {
            throw new BobbyException("Description cannot be empty");
        }

        String[] descAndTime = inputs[1].split(" /at ", 2);
        if (descAndTime.length <= 1) {
            throw new BobbyException("Date/Time format of Event is incorrect or empty");
        }

        String description = descAndTime[0];
        String at = descAndTime[1];
        Event newEvent = new Event(description, at);
        taskArray.add(newEvent);
        storage.updateFile(taskArray);
        updateStateArray();

        return Ui.printAddedTask(newEvent, taskArray);
    }

    /**
     * deletes Removes Task of index given in user input from the list.
     *
     * @param index index of task to be deleted.
     * @return Program output to confirm execution of command to user.
     * @throws BobbyException when no given index or when index is bigger than list.
     */
    public String delete(int index) throws BobbyException {
        if (index > taskArray.size() - 1 || index < 0) {
            throw new BobbyException("Invalid index given to Bobby.");
        }

        Task t = taskArray.get(index);
        taskArray.remove(index);
        storage.updateFile(taskArray);
        updateStateArray();

        return Ui.printDeletedTask(t, taskArray);
    }

    /**
     * Generates a list of current tasks.
     *
     * @return List of all the tasks stored in Bobby.
     */
    public String list() {
        return Ui.printList(taskArray);
    }


    /**
     * Marks a task as done.
     *
     * @param index index of task to be marked as done.
     * @return Program output to confirm execution of command to user.
     * @throws BobbyException when index given exceeds size of list.
     */
    public String mark(int index) throws BobbyException {
        if (index > taskArray.size() - 1 || index < 0) {
            throw new BobbyException("Invalid index given to Bobby.");
        }

        Task t = taskArray.get(index);
        t.markAsDone();
        storage.updateFile(taskArray);
        updateStateArray();
        assert t.isDone : "error in mark";

        return Ui.taskDone(t);
    }


    /**
     * Unmarks a task as done.
     *
     * @param index index of task to be unmarked.
     * @return Program output to confirm execution of command to user.
     * @throws BobbyException when index given exceeds size of list.
     */
    public String unmark(int index) throws BobbyException {
        if (index > taskArray.size() - 1 || index < 0) {
            throw new BobbyException("Invalid index given to Bobby.");
        }

        Task t = taskArray.get(index);
        t.unmarkAsDone();
        storage.updateFile(taskArray);
        updateStateArray();
        assert !t.isDone : "error in unmark";

        return Ui.taskNotDone(t);
    }

    /**
     * Find a task by searching for a key word.
     *
     * @param query keyword used to search.
     * @return List of tasks that matches with given query keyword.
     */
    public String find(String query) {
        boolean isSuccessful = false;
        ArrayList<Task> resultTasks = new ArrayList<>();
        for (Task t : taskArray) {
            String[] contents = t.description.split(" ");
            for (String content : contents) {
                if (Objects.equals(content, query)) {
                    resultTasks.add(t);
                    isSuccessful = true;
                }
            }
        }
        if (!isSuccessful) {
            return Ui.printNoTasksFound();
        }
        return Ui.printFoundTasks(resultTasks);
    }

    private void updateStateArray() {
        ArrayList<Task> taskArrayCopy = new ArrayList<Task>(taskArray);
        currentState = new State(taskArrayCopy);
        stateStack.push(currentState);
    }

    /**
     * Undo previous change to the list of tasks stored in Bobby.
     *
     * @throws BobbyException when there is no more changes to be reversed.
     */
    public void undo() throws BobbyException {
        if (stateStack.size() <= 1) {
            throw new BobbyException("Bobby cannot Undo any further.");
        }
        stateStack.pop();
        currentState = stateStack.peek();
        taskArray = currentState.loadState();
        storage.updateFile(taskArray);
    }
}
