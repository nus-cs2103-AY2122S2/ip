package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskArray = new ArrayList<>();

    public void listItem() {
        System.out.println("__________________________________");
        if (taskArray.size() == 0) {
            System.out.println("No items in the list");
        } else {
            for (int i = 0; i < taskArray.size(); i++) {
                System.out.printf("%d. " + taskArray.get(i)+ "\n", i+1);
            }
        }
        System.out.println("__________________________________");
    }

    public void markItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        taskArray.get(index-1).setChecked();
        System.out.println("__________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskArray.get(index-1));
        System.out.println("__________________________________");
    }

    public void unmarkItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        taskArray.get(index-1).setUnChecked();
        System.out.println("__________________________________");
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskArray.get(index-1));
        System.out.println("__________________________________");
    }

    public void deleteItem(String[] command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command[1]);
        Task task = taskArray.remove(index - 1);
        System.out.println("__________________________________");
        System.out.println("Noted, I've removed this task from the list: ");
        System.out.println(task);
        System.out.printf("You have %d tasks left\n", taskArray.size());
        System.out.println("__________________________________");
    }

    public void addItem(String[] command) throws DukeException {
        String input = command[0];
        System.out.println("__________________________________");
        switch (input) {
        case "todo":
            taskArray.add(new Todo(command[1]));
            break;
        case "deadline":
            taskArray.add(new Deadline(command[1]));
            break;
        case "event":
            taskArray.add(new Event(command[1]));
            break;
        }
        System.out.printf("You have %d tasks in your list\n", taskArray.size());
        System.out.println("__________________________________");
    }

    public String writeItem() {
        StringBuilder list = new StringBuilder();
        if (taskArray.size() == 0) {
            list = new StringBuilder("No items in the list");
        } else {
            for (int i = 0; i < taskArray.size(); i++) {
                String line = String.format("%d. " + taskArray.get(i)+ "\n", i+1);
                list.append(line);
            }
        }
        return list.toString();
    }
}
