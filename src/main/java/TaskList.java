import java.util.HashSet;
import java.util.Set;

public class TaskList {

  private final Task[] list;
  private final Set<String> set;
  private int numItems;

  public TaskList() {
    this.list = new Task[100];
    this.numItems = 0;
    this.set = new HashSet<>();
  }

  public Task getTask(int index) {
    return list[index];
  }

  public Boolean isValidIndex(int index) {
    return index >= 0 && index < numItems;
  }

  public String add(String[] input) {
    String title = "";
    for (int i = 0; i < input.length; i++) {
      if (i != input.length - 1) {
        title = title.concat(input[i]).concat(" ");
      } else {
        title = title.concat(input[i]);
      }
    }

    if (set.contains(title)) {
      return "This task is already in your task List!";
    } else {
      list[numItems++] = new Task(numItems, title);
      set.add(title);
      return title + " has been added to your task List!";
    }
  }

  public void summary() {
    for (int i = 0; i < numItems; i++) {
      System.out.println(list[i]);
    }
    System.out.println();
  }

  public String mark(int index) {
    Task task = list[index];
    if (task.isCompleted()) {
      return "This task was already completed! No need to mark it again.";
    } else {
      task.mark();
      return "This task has been marked as completed in your task list!";
    }
  }

  public String unmark(int index) {
    Task task = list[index];
    if (!task.isCompleted()) {
      return "This task has not been completed yet! No need to unmark it.";
    } else {
      task.unmark();
      return "This task has been unmarked in your task list!";
    }
  }

  public boolean isEmpty() {
    return numItems == 0;
  }
}
