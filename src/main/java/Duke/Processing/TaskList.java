package Duke.Processing;
import java.util.ArrayList;
import java.util.Comparator;

import Duke.Exception.DukeException;
import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Task;
import Duke.tasks.todo;

public class TaskList {
    static final String LIST = "Here are the things the covenant are after\n";
    static final String MARK = "We've completed these objectives\n";
    static final String UNMARK = "The covenant have reclaimed these locations\n";
    static final String ADDED = "Just one more thing to do\n";
    static final String DELETED = "It's wiped off the map\n";
    static final String FOUND = "Here are the tasks that match your request\n";
    static final String NOTHING = "I couldn't find anything that matched\n";
    static final String FINISHED = "We've done all we set out to do\n";

    private final ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public int size() {
        return this.tasklist.size();
    }

    public String list() {
        String output = "";
        if(tasklist.size() == 0) {
            output = FINISHED;
        } else {
            output += LIST;
            for (int i = 0; i < tasklist.size(); i++) {
                output += ((i + 1) + ") " + this.tasklist.get(i) + "\n");
            }
        }
        return output;
    }
    /**
     * Marks a task as done
     *
     * @param num the array location of the item to be marked as done
     * @return a String output
     */
    public String mark(String num) {
        int act = Integer.parseInt(num) - 1;
        Task marking = this.tasklist.get(act);
        marking.isDone = true;
        this.tasklist.set(act, marking);
        String output = MARK + "\n";
        output += marking.getDescription();
        return output;
    }

    /**
     * Marks a task as undone
     *
     * @param num the array location of the item to be marked as undone
     * @return a String output
     */
    public String unmark(String num) {
        int act = Integer.parseInt(num) - 1;
        Task unmarking = this.tasklist.get(act);
        unmarking.isDone = false;
        this.tasklist.set(act, unmarking);
        System.out.println(UNMARK);
        String output = UNMARK + "\n";
        output += unmarking.getDescription();
        return output;
    }
    /**
     * Adds a Todo task to the Tasklist
     *
     * @param item The todo task to be added
     * @return a String output
     */
    public String addTodo(String item) {
        todo newtodo = new todo(item);
        int size = tasklist.size();
        this.tasklist.add(newtodo);
        String output = ADDED + "\n";
        assert tasklist.size() == size + 1: "Item not added";
        output += newtodo.toString();
        return output;
    }
    /**
     * Adds a general Task to the Tasklist
     *
     * @param task the task to be added to the Tasklist
     */
    public void addTask(Task task) {
        int size = tasklist.size();
        this.tasklist.add(task);
        assert tasklist.size() == size + 1: "Item not added";
    }

    public Task get(int i) {
        return this.tasklist.get(i);
    }

    /**
     * Adds a deadline Task to the Tasklist
     *
     * @param item the deadline task to be added to the Tasklist
     * @return a String output
     */
    public String addDeadline(String[] item) throws DukeException{
        Deadline newDeadline = new Deadline(item[0], Parser.convert1(item[1]));
        int size = tasklist.size();
        this.tasklist.add(newDeadline);
        String output = ADDED + "\n";
        assert tasklist.size() == size + 1: "Item not added";
        output += newDeadline.toString();
        tasklist.sort(Comparator.comparing(task -> task.getDate()));
        return output;
    }

    /**
     * Adds a Event Task to the Tasklist
     *
     * @param item the Event task to be added to the Tasklist
     * @return a String output
     */
    public String addEvent(String[] item) throws DukeException{
        Event newEvent = new Event(item[0], Parser.convert1(item[1]));
        int size = tasklist.size();
        this.tasklist.add(newEvent);
        String output = ADDED + "\n";
        assert tasklist.size() == size + 1: "Item not added";
        output += newEvent.toString();
        tasklist.sort(Comparator.comparing(task -> task.getDate()));
        return output;
    }

    /**
     * Deletes a general task from the Tasklist
     *
     * @param num the location in the array of the Tasklist to be deleted
     * @return a String output
     */
    public String delete(String num) throws DukeException {
        String output = "";
        try {
            int index = Integer.parseInt(num) - 1;
            Task deleted = tasklist.get(index);
            this.tasklist.remove(index);
            String description = deleted.getDescription();
            output += DELETED + "\n" + description;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such item exists");
        }
        return output;
    }
    /**
     * Allows the user to find a list of tasks that contain a fixed String
     *
     * @param name is the name of the task I am finding
     * @return a String output
     */

    public String findS(String name) {
        String output = "";
        int size = tasklist.size();
        assert size != 0 : "Tasklist is empty";
        String item = "";
        int counter = 0;
        for (int i = 0; i < size; i++) {
            Task checking = tasklist.get(i);
            String description = checking.getDescription();
            if(description.contains(name)) {
                output += checking.toString() + "\n";
                counter++;
            }
        }
        if (counter == 0) {
            output += NOTHING;
        } else {
            output += FOUND;
        }
        output += item;
        return output;
    }
    public String clear() {
        tasklist.clear();
        return "All items are off the table";
    }




}
