import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> itemList = new ArrayList<>(0);

    public TaskList() {}

    public void addItem(Task task) {
        this.itemList.add(task);
    }

    public void addTodo(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String taskType = tokens[0];
        String taskTitle = "";
        for (int i = 1; i < tokens.length; i++) {
            if (i == tokens.length - 1) {
                taskTitle = taskTitle + tokens[i];
                continue;
            }
            taskTitle = taskTitle + tokens[i] + " ";
        }
        Task newTask = new UnmarkedTask(taskTitle, taskType);
        this.itemList.add(newTask);
        System.out.println(
                "Got it. I've added this task:\n"
                        + "  " + newTask
                        + "Now you have " + this.itemList.size() + " tasks in the list."
        );
    }

    public void addEvent(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String taskType = tokens[0];
        String taskTitle = "";
        for (int i = 1; i < tokens.length; i++) {
            if (i == tokens.length - 1) {
                taskTitle = taskTitle + tokens[i];
                continue;
            }
            taskTitle = taskTitle + tokens[i] + " ";
        }
        String[] secondSplit = taskTitle.split(" /at ");
        taskTitle = secondSplit[0];
        String deadline = secondSplit[1];
        Task newTask = new UnmarkedTask(taskTitle, deadline, taskType);
        this.itemList.add(newTask);
        System.out.println(
                "Got it. I've added this task:\n"
                        + "  " + newTask
                        + "Now you have " + this.itemList.size() + " tasks in the list."
        );
    }

    public void addDeadline(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String taskType = tokens[0];
        String taskTitle = "";
        for (int i = 1; i < tokens.length; i++) {
            if (i == tokens.length - 1) {
                taskTitle = taskTitle + tokens[i];
                continue;
            }
            taskTitle = taskTitle + tokens[i] + " ";
        }
        String[] secondSplit = taskTitle.split(" /by ");
        taskTitle = secondSplit[0];
        String deadline = secondSplit[1];
        Task newTask = new UnmarkedTask(taskTitle, deadline, taskType);
        this.itemList.add(newTask);
        System.out.println(
                "Got it. I've added this task:\n"
                + "  " + newTask
                + "Now you have " + this.itemList.size() + " tasks in the list."
        );
    }

    public void markTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        Task newTask = targetTask.markTask();
        this.itemList.set(index, newTask);
        System.out.println(
                "Nice! I've marked this task as done:"
                        + "\n" + newTask
        );
    }

    public void unmarkTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        Task newTask = targetTask.unmarkTask();
        this.itemList.set(index, newTask);
        System.out.println(
                "OK, I've marked this task as not done yet:"
                        + "\n" + newTask
        );
    }

    public String printList() {
        StringBuilder initList = new StringBuilder();

        for (int i = 1; i < itemList.size() + 1; i++) {
            initList.append(i + ".");
            initList.append(itemList.get(i - 1));
            initList.append("\n");
        }

        return initList.toString();
    }
}
