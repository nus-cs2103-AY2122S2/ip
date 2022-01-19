import java.util.ArrayList;

public class TaskModule {
  private ArrayList<Task> taskList;

  public TaskModule() {
    this.taskList = new ArrayList<>();
  }

  public void taskAdder(String str) throws DukeException {
    Task task = null;
    Task.TaskType taskType = taskIdentifier(str);
    switch (taskType) {
      case TODO :
        task = new Todo(str.substring(5));
        break;
      case DEADLINE :
        task = new Deadline(str.substring(9, str.lastIndexOf(" /by ")), str.substring(str.lastIndexOf(" /by ") + 5));
        break;
      case EVENT :
        task = new Event(str.substring(6, str.lastIndexOf(" /at ")), str.substring(str.lastIndexOf(" /at ") + 5));
        break;
    }

    if (task != null) {
      taskList.add(task);
      System.out.println(String.format("Got it. I've added this task:\n\t%s\nNow you have %d task(s) in the list", task.toString(), taskList.size()));
    }
  }

  public Task.TaskType taskIdentifier(String str) throws DukeException {
    Task.TaskType type = null;
    if (str.matches("(todo|deadline|event).*")) {
      if (str.startsWith("todo")) {
        if (!str.matches("todo\\s\\S+.*")) {
          throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        type = Task.TaskType.TODO;
      } else if (str.startsWith("deadline")) {
        if (!str.matches("deadline\\s\\S+.*\\s/by\\s\\S+.*")) {
          throw new DukeException("☹ OOPS!!! The description/date of a deadline cannot be empty.");
        }
        type = Task.TaskType.DEADLINE;
      } else if (str.startsWith("event ")) {
        if (!str.matches("event\\s\\S+.*\\s/at\\s\\S+.*")) {
          throw new DukeException("☹ OOPS!!! The description/location of a event cannot be empty.");
        }
        type = Task.TaskType.EVENT;
      }
    } else {
      throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    return type;
  }

  public void markHandler(String[] strArr) throws DukeException{
    if (strArr.length < 2) {
      throw new DukeException("Please choose which task you would like to mark.");
    } else {
      String listNumber = strArr[1];
      if ((listNumber.chars().allMatch(Character::isDigit))
          && (Integer.parseInt(listNumber) <= taskList.size()) && strArr.length == 2) {
        int num = Integer.parseInt(listNumber);
        Task currTask = taskList.get(num - 1);
        currTask.setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask.toString());
      } else {
        throw new DukeException("Invalid task chosen to be marked, please try again");            }
    }
  }

  public void unmarkHandler(String[] strArr) throws DukeException{
    if (strArr.length < 2) {
      throw new DukeException("Please choose which task you would like to unmark.");
    } else {
      String listNumber = strArr[1];
      if ((listNumber.chars().allMatch(Character::isDigit))
          && (Integer.parseInt(listNumber) <= taskList.size()) && strArr.length == 2) {
        int num = Integer.parseInt(listNumber);
        Task currTask = taskList.get(num - 1);
        currTask.setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask.toString());
      } else {
        throw new DukeException("Invalid task chosen to be unmarked, please try again");
      }
    }
  }

  public void deleteHandler(String[] strArr) throws DukeException {
    if (strArr.length < 2) {
      throw new DukeException("Please choose which task you would like to delete.");
    } else {
      String listNumber = strArr[1];
      if ((listNumber.chars().allMatch(Character::isDigit))
          && (Integer.parseInt(listNumber) <= taskList.size()) && strArr.length == 2) {
        int num = Integer.parseInt(listNumber);
        Task currTask = taskList.get(num - 1);
        taskList.remove(num - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currTask.toString());
      } else {
        throw new DukeException("Invalid task chosen to be deleted, please try again");
      }
    }
  }

  public void displayList() {
    if (taskList.size() == 0) {
      System.out.println("LUMU: Your list is empty!");
    } else {
      for (int i = 0; i < taskList.size(); i++) {
        Task currTask = taskList.get(i);
        System.out.println(String.valueOf(i + 1) + ". " + currTask.toString());
      }
    }
  }
}
