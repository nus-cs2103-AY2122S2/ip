/**
 * TaskList helps to store tasks given by the user. TaskList is contained in
 * the Bot class.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the TaskList currently.
 */

public class TaskList {
    Task[] tasks;
    int index = 0;

    /**
     * Constructs a TaskList containing an array to contain tasks
     */
    TaskList() {
        this.tasks = new Task[100];
    }

    /**
     * Adds a given input to the tasks array by creating a Task object
     * @param input String, given by user
     */
    void add(String input) {
        System.out.printf("added: %s\n", input);
        Task newTask = new Task(input, index + 1);
        tasks[index] = newTask;
        index++;
    }

    /**
     * Prints out every item contained in the tasks array
     */
    void list() {
        System.out.println("Here's what you need to do buddy:");
        for (int i = 0; i < index; i++) {
            Task currentTask = tasks[i];
            System.out.println(currentTask);
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
                    tasks[taskIndex].markDone();
                    System.out.printf("This is now done: \n%s\n", tasks[taskIndex]);
                } else if (type.equals("unmark")) {
                    tasks[taskIndex].markNotDone();
                    System.out.printf("This is now undone:\n%s\n", tasks[taskIndex]);
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
        return (index <= this.index);
    }
}
