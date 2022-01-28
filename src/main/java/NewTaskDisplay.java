public class NewTaskDisplay{
    void run(Task task, TaskList taskList) {
        System.out.println("====================================");
        System.out.println("Alright, I've added this to the list");
        System.out.println(task.toString());
        taskList.printNoTasks();
        System.out.println("====================================");
    }
}
