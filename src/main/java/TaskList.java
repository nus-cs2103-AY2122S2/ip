public class TaskList {
    private final Task[] taskArray = new Task[100];
    private int index = 0;

    String addTask(Task newTask) {
        taskArray[index] = newTask;
        index++;
        String output = "Got it. I've added this task: \n  " + newTask + "\nnow you have " + this.index + " tasks in the list";
        return output;
    }

    String getTasks() {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < index; i++) {
            output += String.format("\n%d.%s", i + 1, taskArray[i]);
        }
        return output;
    }

    String mark(int id) {
        if (id > index) {
            return "Task has not been added yet";
        } else if (id <= 0) {
            return "invalid task number";
        }
        String out = taskArray[id - 1].switchMark();
        return out;
    }
}
