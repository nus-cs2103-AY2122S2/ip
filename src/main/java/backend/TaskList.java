package backend;
import tasks.DoWithin;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasks;

    /**
     * Returns a list of all tasks beginning with index 1
     */
    public static String list() {
        // print the ui user message
        Ui.list();
        String output = "";
        int index = 1;

        // print out all objects in the list
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            index = i + 1;
//            System.out.println(index + "." + currentTask.toString());
            output = output + index + "." + currentTask.toString() + "\n";
        }

        assert index - 1 == tasks.size() : "index should be the same as the number of tasks";
        return output;
    }

    /**
     * Marks the task at the supplied integer as completed, based on their ordering in this.list().
     *
     * @param indexMarked index of the completed task
     */
    public static String mark(int indexMarked) {
        String output;
        try {
            Task currentTask = tasks.get(indexMarked);
            currentTask.setDone(true);
            output = Ui.mark(currentTask.toString()) ;
            assert currentTask.getDone() == true : "task isDone should be set to true";
        } catch (IndexOutOfBoundsException e) {
            output = Ui.invalidTask();
        }
        return output;
    }

    /**
     * Unmarks the task at the supplied integer as completed, based on their ordering in this.list().
     *
     * @param indexUnmarked index of the task to be unmarked
     */
    public static String unmark(int indexUnmarked) {
        String output;
        try {
            Task currentTask = tasks.get(indexUnmarked);
            currentTask.setDone(false);
            output = Ui.unmark(currentTask.toString());
            assert currentTask.getDone() == false : "task isDone should be set to false";
        } catch (IndexOutOfBoundsException e) {
            output = Ui.invalidTask();
        }
        return output;
    }

    /**
     * removes the task at the supplied integer from tasklist, based on their ordering in this.list().
     *
     * @param indexDelete index of the task to be deleted.
     */
    public static String delete(int indexDelete) {
        String output;
        try {
            Task deletedTask = tasks.remove(indexDelete);
            output =  Ui.delete(deletedTask.toString());
        } catch (IndexOutOfBoundsException e) {
            output = Ui.invalidTask();
        }
        return output;
    }

    /**
     * creates a Todo task and adds it to the end of the tasklist
     *
     * @param description description of the todo task.
     */
    public static String addTodo(String description) {
        String output;
        try {
            if (description.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Todo currentTodo = new Todo(description);
            tasks.add(currentTodo);
            output =  Ui.todo(currentTodo.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            output = Ui.emptyDescription();
        }
        return output;
    }


    /**
     * creates a deadline task and adds it to the end of the tasklist.
     *
     * @param description description of the deadline task.
     * @param time time of the deadline task.
     */
    public static String addDeadline(String description, String time) {
        String output = "";
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Deadline currentDeadline = new Deadline(description, time);
            tasks.add(currentDeadline);
            output =  Ui.deadline(currentDeadline.toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            output = Ui.emptyDescription();
        }
        return output;
    }

    /**
     * creates a event task and adds it to the end of the tasklist.
     *
     * @param description description of the event task.
     * @param time time of the event task.
     */
    public static String addEvent(String description, String time) {
        String output;
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Event currentEvent = new Event(description, time);
            tasks.add(currentEvent);
            output =  Ui.event(currentEvent.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            output = Ui.emptyDescription();
        }
        return output;
    }
    /**
     * returns all tasks that contain the designated keyword.
     *
     * @param keyword designated keyword
     */
    public static String find(String keyword) {
        int counter = 1;
        String output = Ui.find() + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                output = output + counter + ". " + tasks.get(i).toString() + "\n";
                counter += 1;
            }
        }
        return output;
    }

    public static String addWithin(String description, String start, String end) {
        String output;
        try {
            if (description.isBlank() || start.isBlank() || end.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            DoWithin currentWithin = new DoWithin(description, start, end);
            tasks.add(currentWithin);
            output =  Ui.within(currentWithin.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            output = Ui.emptyDescription();
        }
        return output;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * initialises the tasklist with an arraylist of tasks.
     *
     * @param tasks pre saved tasks from data/duke.txt
     */
    public static void initialise(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }
}
