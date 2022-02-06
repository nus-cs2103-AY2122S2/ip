package Duke.Processing;
import java.util.ArrayList;
import Duke.Exception.DukeException;
import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Task;
import Duke.tasks.todo;

public class TaskList {
    static final String LIST = "Here are the things the covenent are after\n";
    static final String MARK = "We've completed these objectives\n";
    static final String UNMARK = "The covenant have reclaimed these locations\n";
    static final String ADDED = "Just one more thing to do\n";
    static final String DELETED = "It's wiped off the map\n";

    private final ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public int size() {
        return this.tasklist.size();
    }

    public void list() {
        System.out.println(LIST);
        for (Task task : tasklist) {
            System.out.println(task);
        }
    }

    public void mark(String num) {
        int act = Integer.parseInt(num) - 1;
        Task marking = this.tasklist.get(act);
        marking.isDone = true;
        this.tasklist.set(act, marking);
        System.out.println(MARK);
    }

    public void unmark(String num) {
        int act = Integer.parseInt(num) - 1;
        Task unmarking = this.tasklist.get(act);
        unmarking.isDone = false;
        this.tasklist.set(act, unmarking);
        System.out.println(UNMARK);
    }

    public void addTodo(String item) {
        todo newtodo = new todo(item);
        this.tasklist.add(newtodo);
        System.out.println(ADDED + newtodo);
    }
    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    public Task get(int i) {
        return this.tasklist.get(i);
    }

    public void addDeadline(String[] item) throws DukeException{
        Deadline newDeadline = new Deadline(item[0], Parser.convert1(item[1]));
        //System.out.println(newDeadline);
        this.tasklist.add(newDeadline);
        System.out.println(ADDED + newDeadline);
    }

    public void addEvent(String[] item) throws DukeException{
        //System.out.println(item[1]);
        Event newEvent = new Event(item[0], Parser.convert1(item[1]));
        //System.out.println(newEvent);
        this.tasklist.add(newEvent);
        System.out.println(ADDED + newEvent);
    }

    public void delete(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num) - 1;
            this.tasklist.remove(index);
            System.out.println(DELETED + "1 item");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such item exists");
        }
    }
}
