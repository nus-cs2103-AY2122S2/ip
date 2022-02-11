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
     * @param taskArray ArrayList storing Tasks.
     * @param storage used to update the list stored in the .txt file.
     */
    public TaskList(ArrayList<Task> taskArray, Storage storage) {
        this.stateStack = new Stack<State>();
        stateStack.push(new State(taskArray));
        this.taskArray = taskArray;
        this.storage = storage;
    }

    /**
     * Adds a Task of type Todo.
     * @param task description of the task.
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
        System.out.println(taskArray.get(taskArray.size() - 1).toString());

        return Ui.printAddedTask(newTodo, taskArray);
    }

    /**
     * Adds a Task of type Deadline.
     * @param task description of Deadline and when the task has to be completed in YYYY-MM-DD format.
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
     * @param task description of Event and date/time of the Event.
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
     * @param index index of task to be deleted.
     * @throws BobbyException when no given index or when index is bigger than list.
     */
    public String delete(int index) throws BobbyException {
        if (index > taskArray.size() - 1|| index < 0) {
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
     */
    public String list() {
        System.out.println("stack size check: " + stateStack.size());
        return Ui.printList(taskArray);
    }


    /**
     * Marks a task as done.
     * @param index index of task to be marked as done.
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
        assert t.isDone: "error in mark";

        return Ui.taskDone(t);
    }


    /**
     * Unmarks a task as done.
     * @param index index of task to be unmarked.
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
        assert !t.isDone: "error in unmark";

        return Ui.taskNotDone(t);
    }

    /**
     * Find a task by searching for a key word.
     * @param query keyword used to search.
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
            return "Bobby could not find any matching tasks.";
        }
        return Ui.printFoundTasks(resultTasks);
    }

    private void updateStateArray() {
        currentState = new State(taskArray);
        stateStack.push(currentState);
        System.out.println("pushed");
        System.out.println(currentState.loadState().get(currentState.loadState().size()-1).toString());
    }

    public void undo() {
//        System.out.println("size before pop: " + stateStack.size());
//        State currentState = stateStack.pop();   // removes from the top
//        System.out.println("size after pop: " + stateStack.size());  // shows that size is decreasing
//        State prevState = stateStack.peek();    // prevState stores top element using peek()
//        taskArray = prevState.loadState();
//        storage.updateFile(taskArray);
//
//        // this prints the last task in my peeked() array, which is always the same even though i popped, this doesnt change
//        System.out.println(currentState.loadState().get(currentState.loadState().size()-1).toString());
//        System.out.println(currentState.loadState().get(0).toString());
//        //System.out.println(stateStack.peek().loadState().get(stateStack.peek().loadState().size()-1).toString());
//        //System.out.println(taskArray.get(taskArray.size() - 1).toString());
    }
}
