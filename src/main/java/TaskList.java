import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasks;


    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static void list() {
        Ui.list();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            int index = i + 1;
            System.out.println(index + "." + currentTask.toString());
        }
    }

    public static void mark(int indexMarked) {
        try {
            Task currentTask = tasks.get(indexMarked);
            currentTask.isDone = true;
            Ui.mark();
            System.out.println(currentTask.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
    }

    public static void unmark(int indexUnmarked) {
        try {
            Task currentTask = tasks.get(indexUnmarked);
            currentTask.isDone = false;
            Ui.unmark();
            System.out.println(currentTask.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
    }

    public static void delete(int indexDelete) {
        try {
            Task deletedTask = tasks.remove(indexDelete);
            Ui.delete();
            System.out.println(deletedTask.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
    }

    public static void todo(String description) {
        try {
            if (description.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Todo currentTodo = new Todo(description);
            tasks.add(currentTodo);
            Ui.todo();
            System.out.println(currentTodo.toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a todo cannot be empty"));
        }
    }

    public static void deadline(String description, String time) {
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Deadline currentDeadline = new Deadline(description, time);
            tasks.add(currentDeadline);
            Ui.deadline();
            System.out.println("added: " + currentDeadline.toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a deadline cannot be empty"));
        }
    }

    public static void event(String description, String time) {
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Event currentEvent = new Event(description, time);
            tasks.add(currentEvent);
            Ui.event();
            System.out.println("added: " + currentEvent.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a event cannot be empty"));
        }
    }
}
