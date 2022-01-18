import java.security.InvalidParameterException;

/**
 * TaskList helps to store tasks given by the user. TaskList is contained in
 * the Bot class.
 * marker helps to keep track of the tasks done.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the storage currently.
 */

public class TaskList {
    String[] tasks;
    boolean[] taskMarker;
    int id = 0;

    /**
     * Constructs a TaskList containing an array to contain tasks and mark tasks as done/not
     */
    TaskList() {
        this.tasks = new String[100];
        this.taskMarker = new boolean[100];
    }

    /**
     * Adds a given input to the tasks array
     * @param input String, given by user
     */
    void add(String input) {
        System.out.printf("added: %s\n", input);
        tasks[id] = input;
        id++;
    }

    /**
     * Prints out every item contained in the tasks array with done / not done status
     */
    void list() {
        for (int i = 0; i < id; i++) {
            if (taskMarker[i]) {
                System.out.printf("%d.[X] %s\n", i + 1, tasks[i]);
            } else {
                System.out.printf("%d.[ ] %s\n", i + 1, tasks[i]);
            }
        }
    }

    /**
     * Mark or unmark a task number depending on the input
     * @param input String, containing mark/unmark and the task number to perform action
     */
    void mark(String input) {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 2) {
            String type = inputArr[0];
            String num = inputArr[1];
            int taskIndex = Integer.parseInt(num) - 1;
            if (checkTaskExistence(taskIndex)) {
                if (type.equals("mark")) {
                    taskMarker[taskIndex] = true;
                    System.out.printf("This is now done: \n[X] %s\n", tasks[taskIndex]);
                } else if (type.equals("unmark")) {
                    taskMarker[taskIndex] = false;
                    System.out.printf("This is now undone: \n[ ] %s\n", tasks[taskIndex]);
                } else {
                    System.out.println("Please check the action input!");
                }
            } else {
                System.out.println("Task number does not exist!");
            }
        } else {
            System.out.println("Have you entered the correct input?");
        }
    }

    /**
     * Checks if the task index is a valid one
     * @param index int, index of the task
     * @return boolean value indicating if task exists
     */
    boolean checkTaskExistence(int index) {
        return (index <= id);
    }
}
