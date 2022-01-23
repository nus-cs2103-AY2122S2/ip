import java.io.IOException;

public enum Handlers {
    Bye("bye"),
    Deadline("deadline"),
    Delete("delete"),
    Event("event"),
    Mark("mark"),
    List("list"),
    Todo("todo"),
    Unmark("unmark");

    public final String label;

    private Handlers(String label) {
        this.label = label;
    }

    public static void byeHandler(Tasklist list, String path) throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        list.writeTaskList(path);
    }

    public static void deadlineHandler(Tasklist list, String path, String input, String cmd) {
        try {
            int index = input.indexOf("/by");
            DukeException.taskValidity(index, input, cmd);
            Deadline task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
            list.addTask(task);
            list.writeTaskList(String.valueOf(path));
            System.out.println("Deadline Added: " + task.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException | IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public static void deleteHandler(Tasklist list, String path, String input) {
        try {
            int index = DukeException.indexValidity(input, list);
            System.out.println("Noted. Deleting this task...");
            Task t = list.delete(index);
            list.writeTaskList(String.valueOf(path));
            System.out.println(t.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException | IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public static void eventHandler(Tasklist list, String path, String input, String cmd) {
        try {
            int index = input.indexOf("/at");
            DukeException.taskValidity(index, input, cmd);
            Event task = new Event(input.substring(6, index - 1), input.substring(index + 4));
            list.addTask(task);
            list.writeTaskList(String.valueOf(path));
            System.out.println("Event Added: " + task.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException | IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public static void markHandler(Tasklist list, String path, String input) {
        try {
            int index = DukeException.indexValidity(input, list);
            System.out.println("Nice! I've marked this task as done!");
            Task t = list.mark(index);
            list.writeTaskList(String.valueOf(path));
            System.out.println(t.toString() + "\n");
        } catch (DukeException | IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public static void listHandler(Tasklist list) {
        System.out.println(list.toString());
    }

    public static void todoHandler(Tasklist list, String path, String input) {
        try {
            DukeException.taskValidity(input);
            Todo task = new Todo(input.substring(5));
            list.addTask(task);
            list.writeTaskList(String.valueOf(path));
            System.out.println("Todo Added: " + task.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException | IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public static void unmarkHandler(Tasklist list, String path, String input) {
        try {
            int index = DukeException.indexValidity(input, list);
            System.out.println("Okay! I've marked this as undone!");
            Task t = list.unmark(index);
            list.writeTaskList(String.valueOf(path));
            System.out.println(t.toString() + "\n");
        } catch (DukeException | IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
