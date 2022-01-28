import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    ArrayList<Task> Tasks = new ArrayList<>();
    WriteToFile writeToFile;

    public TaskList() {
        writeToFile = new WriteToFile();
    }

    public void listTasks() {
        Tasks = writeToFile.toRead();

        if (Tasks.size() == 0) {
            System.out.println("empty list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Tasks.size(); i++) {
                System.out.println((i + 1) + ". " + Tasks.get(i));
            }
        }
        System.out.println("");
    }

    public void mark(String str) throws IOException {
        String[] input = str.split(" ");
        int taskID = Integer.parseInt(input[1]) - 1;
        Tasks.get(taskID).setDone();
        writeToFile.toWrite(Tasks);
        System.out.println("Nice! I've marked this task as done:\n" +
                Tasks.get(taskID) + "\n");
    }

    public void unmark(String str) throws IOException{
        String[] input = str.split(" ");
        int taskID = Integer.parseInt(input[1]) - 1;
        Tasks.get(taskID).setUnDone();
        writeToFile.toWrite(Tasks);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                Tasks.get(taskID) + "\n");
    }

    public void setTodo(String str) throws DukeException, IOException {
        String[] input = str.split(" ");
        if (input.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(str.substring(5));
        Tasks.add(todo);
        writeToFile.toWrite(Tasks);
        System.out.println("Got it. I've added this task:\n" + todo);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
    }

    public void setDeadline(String str) throws DukeException, IOException {
        String[] input = str.split(" ");

        if (input.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] split = str.split("/by ");
        Deadline deadline = new Deadline(split[0].substring(9), split[1]);
        Tasks.add(deadline);
        writeToFile.toWrite(Tasks);
        System.out.println("Got it. I've added this task:\n" + deadline);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
    }

    public void setEvent(String str) throws DukeException, IOException {
        String[] input = str.split(" ");

        if (input.length == 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }

        String[] split = str.split("/at ");
        Event event = new Event(split[0].substring(6), split[1]);
        Tasks.add(event);
        writeToFile.toWrite(Tasks);
        System.out.println("Got it. I've added this task:\n" + event);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
    }

    public void delete(String str) {
        String[] input = str.split(" ");

        int toDelete = Integer.parseInt(input[1]) - 1;
        Task removed = Tasks.remove(toDelete);
        writeToFile.toWrite(Tasks);
        System.out.println("Noted. I've removed this task:\n" + removed);
        System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
    }

}
