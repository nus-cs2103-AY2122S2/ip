package duke.util;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String addTask(Task task) {
        this.taskList.add(task);
        return "Got it. I've added this task\n" + task.printTask() + "\n" + "Now you've got " + taskList.size() +
                " tasks in the list.";
    }

    public void deleteTask(int index) throws InvalidIndexException {
        Task deletedTask;
        try {
            deletedTask = this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.printTask());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public Task mark(int index) throws InvalidIndexException {
        Task markTask;
        try {
            markTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        markTask.updateStatus(1);
        System.out.println("Nice! I've marked this task as done");
        System.out.println(markTask.printTask());
        return markTask;
    }

    public Task unmark(int index) throws InvalidIndexException {
        Task unmarkTask;
        try {
            unmarkTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("1 to " + taskList.size() + " inclusive");
        }
        unmarkTask.updateStatus(0);
        System.out.println("Oof! I've marked this task as undone");
        System.out.println(unmarkTask.printTask());
        return unmarkTask;
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            System.out.print(num + ". ");
            System.out.println(taskList.get(i).printTask());
        }
    }

}
