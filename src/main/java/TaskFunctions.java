public class TaskFunctions {

    private static final int SIZE = 100;
    private static Task[] taskList = new Task[SIZE];
    private static int pointer = 0;

    static public void addToList(Task task) {

        if (pointer == 99) {
            System.out.println("Task list is full!");
        } else {
            taskList[pointer++] = task;
            System.out.println("added: " + task.toString());
        }
    }

    static public void markTask(int position) {
        if (position < 1 || position - 1 > pointer) {
            System.out.println("Task do not exist!");
        } else if (taskList[position - 1].isDone == true) {
            System.out.println("Task is already marked as done!");
        } else {
            taskList[position - 1].mark();
            System.out.println("Nice! I've marked this task as done: \n" +
                    taskList[position - 1].toString());
        }
    }

    static public void unmarkTask(int position) {
        if (position < 1 || position - 1 > pointer) {
            System.out.println("Task do not exist!");
        } else if (taskList[position - 1].isDone == false) {
            System.out.println("Task is already marked as not done!");
        } else {
            taskList[position - 1].unmark();
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    taskList[position - 1].toString());
        }
    }

    static public void showTaskList() {
        if (pointer == 0) {
            System.out.println("You have not added any tasks!");
        } else {
            for (int i = 0; i < pointer; i++) {
                System.out.println(i + 1 + "." + taskList[i].toString());
            }
        }
    }

}
