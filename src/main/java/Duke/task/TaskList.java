package Duke.task;

import Duke.main.Ui;

import java.util.ArrayList;

/**
 * a data structure for tasks
 */
public class TaskList extends ArrayList<Task> {
    private static ArrayList<Task> taskList;

    /**
     * Constructor
     * @param taskList input the tasks
     */
    public TaskList (ArrayList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    /**
     * Constructor
     */
    public TaskList () {
        taskList = new ArrayList<Task>();
    }

    /**
     * get tasks
     * @return get the ArrayList<Task> data
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * add task to task list
     * @param t a todo task
     */
    public void addTodoTask(Task t) {
        taskList.add(t);
        Ui.addTaskMessage();
        System.out.println(t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + Ui.LINE);
    }

    /**
     * add task to task list
     * @param d a DeadLine task
     */
    public void addDeadlineTask(Task d) {
        taskList.add(d);
        Ui.addTaskMessage();
        System.out.println(d);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + Ui.LINE);
    }

    /**
     * add task to task list
     * @param e a Event task
     */
    public void addEventTask(Task e) {
        taskList.add(e);
        Ui.addTaskMessage();
        System.out.println(e);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + Ui.LINE);
    }

    /**
     * delete a task in task list
     * @param taskNum the numbering of the task
     */
    public void deleteTask(int taskNum) {
        Ui.deleteTaskMessage();
        System.out.println(taskList.get(taskNum));
        taskList.remove(taskNum);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + Ui.LINE);
    }

}
