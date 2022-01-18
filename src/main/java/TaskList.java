public class TaskList {
    private final String[] taskArray = new String[100];
    private int index = 0;

    String addTask(String newTask) {
        taskArray[index] = newTask;
        index++;
        return "added: " + newTask;
    }

    String getTasks() {
        String output = "";
        for (int i = 0; i < index; i++) {
            output += String.format("%d. %s", i + 1, taskArray[i]);
            if (i != index - 1) {
                output += "\n";
            }
        }
        return output;
    }
}
