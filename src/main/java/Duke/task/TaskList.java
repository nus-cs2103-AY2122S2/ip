package Duke.task;

import Duke.main.Ui;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public static ArrayList<Task> taskList;

    public TaskList (ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    public TaskList () {
        taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTodoTask(Task t) {
        taskList.add(t);
        Ui.addTaskMessage();
        System.out.println(t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + Ui.line);
    }

    public void addDeadlineTask(Task d) {
        taskList.add(d);
        Ui.addTaskMessage();
        System.out.println(d);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + Ui.line);
    }

    public void addEventTask(Task e) {
        taskList.add(e);
        Ui.addTaskMessage();
        System.out.println(e);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + Ui.line);
    }

    public void deleteTask(int taskNum) {
        Ui.deleteTaskMessage();
        System.out.println(taskList.get(taskNum));
        taskList.remove(taskNum);
        System.out.println("Now you have " + taskList.size() + " tasks in the list." + Ui.line);
    }

}
