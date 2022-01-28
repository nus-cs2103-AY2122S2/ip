import java.util.ArrayList;

public class TaskList {
    private static final String LIST = "Here are the tasks in your list:";
    private static final String MARK = "Nice! I've marked this task as done:\n  ";
    private static final String UNMARK = "OK, I've marked this task as not done yet:\n  ";
    private static final String ADDED = "Got it. I've added this task:\n  ";
    private static final String REMOVED = "Noted. I've removed this task:\n";
    private static final String ALREADY_MARKED = "Tasked has already been marked...";
    private static final String ALREADY_UNMARKED = "Task has already been unmarked...";
    private static final String ALREADY_DELETED = "Task has already been deleted...";


    private final ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void listItems() {
        System.out.println(LIST);
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + "." + this.taskList.get(i));
        }
    }

    public void markTask(String taskNumber) throws DukeException {
        int index = Integer.parseInt(taskNumber) - 1;
        Task taskToMark = this.taskList.get(index);
        if (!taskToMark.getStatusIcon().equals("X")) {
            taskToMark.markAsDone();
            this.taskList.set(index, taskToMark);
        } else {
            throw new DukeException(ALREADY_MARKED);
        }
        System.out.println(MARK + taskToMark);
    }

    public void unmarkTask(String taskNumber) throws DukeException {
        int num = Integer.parseInt(taskNumber) - 1;
        Task taskToUnmark = this.taskList.get(num);
        if (taskToUnmark.getStatusIcon().equals("X")) {
            taskToUnmark.markAsUndone();
            this.taskList.set(num, taskToUnmark);
        } else {
            throw new DukeException(ALREADY_UNMARKED);
        }
        System.out.println(UNMARK + taskToUnmark);
    }

    public void addToDoTask(String description) {
        Todo todoItem = new Todo(description);
        this.taskList.add(todoItem);
        int numOfItems = this.taskList.size();
        System.out.println(ADDED + todoItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public void addDeadlineTask(String[] arr) throws DukeException {
        Deadline deadlineItem = new Deadline(arr[0], Parser.convertDate(arr[1]));
        this.taskList.add(deadlineItem);
        int numOfItems = this.taskList.size();
        System.out.println(ADDED + deadlineItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public void addEventTask(String[] arr) throws DukeException {
        Event eventItem = new Event(arr[0], Parser.convertDate(arr[1]));
        this.taskList.add(eventItem);
        int numOfItems = this.taskList.size();
        System.out.println(ADDED + eventItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    public void deleteTask(String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task itemToDelete = this.taskList.get(index);
            this.taskList.remove(index);
            int numOfItems = this.taskList.size();
            System.out.println(REMOVED + itemToDelete);
            System.out.println("Now you have " + numOfItems + " tasks in the list.");
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ALREADY_DELETED);
        }
    }

    public void addGeneralTask(Task task) {
        this.taskList.add(task);
    }

}
