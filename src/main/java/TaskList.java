/**
 * TaskList helps to store tasks given by the user. TaskList is contained in
 * the Bot class and handles the Task for the Bot.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the TaskList currently.
 */

public class TaskList {
    Task[] tasks;
    int nextIndex = 0;
    int done = 0;
    String lineBreak = "___________________________________________________________";
    /**
     * Constructs a TaskList containing an array to contain tasks
     */
    TaskList() {
        this.tasks = new Task[100];
    }

    /**
     * Adds a Task given an input by the user. The specific
     * class created is determined by what is contained in the input.
     * @param input String, given by user. Either contains todo, deadline or event.
     */
    void add(String input) throws IllegalArgumentException {
        Task newTask = null;
        if (isType("todo", input)) {
            String[] inputArr = getParams("todo", input);
            String description = inputArr[0];
            newTask = new ToDo(description, nextIndex + 1);
        } else if (isType("deadline", input)) {
            String[] inputArr = getParams("deadline", input);
            String description = inputArr[0];
            String by = inputArr[1];
            newTask = new Deadline(description, nextIndex + 1, by);
        } else if (isType("event", input)) {
            String[] inputArr = getParams("event", input);
            String description = inputArr[0];
            String at = inputArr[1];
            newTask = new Event(description, nextIndex + 1, at);
        } else {
            throw new IllegalArgumentException("Not a valid type of task!");
        }
        tasks[nextIndex] = newTask;
        nextIndex++;
        System.out.printf("Got ya. Added:\n%s\nYou got %d tasks waiting for ya!\n",
                            newTask, nextIndex - done);
        System.out.println(lineBreak);
    }

    /**
     * Verifies the user input is of which task
     * @param taskType String
     * @param input String
     * @return boolean to affirm if the input is of this task
     */
    boolean isType(String taskType, String input) {
        return input.indexOf(taskType) == 0;
    }

    /**
     * Splits a user input into an array accordingly depending on taskType.
     * Array contains the respective Task parameters
     * @param taskType String, what
     * @param input String, a user input
     * @return String[] inputArr. For "todo": an array of 1, containing description.
     * For "deadline": an array of 2: [description, by]
     * For "event": an array of 2: [description, at]
     */
    String[] getParams(String taskType, String input) {
        String description;
        String[] inputArr = null;
        switch (taskType) {
            case "todo":
                inputArr = new String[]{input.split("todo ")[1]};
                break;
            case "deadline":
                inputArr = input.split(" /by ");
                description = inputArr[0].split("deadline ")[1];
                String by = inputArr[1];
                inputArr = new String[]{description, by};
                break;
            case "event":
                inputArr = input.split(" /at ");
                description = inputArr[0].split("event ")[1];
                String at = inputArr[1];
                inputArr = new String[]{description, at};
                break;
            default:
                break;
        }
        return inputArr;
    }

    /**
     * Prints out every item contained in the tasks array
     */
    void list() {
        System.out.println("Here's what you need to do buddy:");
        for (int i = 0; i < nextIndex; i++) {
            Task currentTask = tasks[i];
            System.out.printf("%d. %s\n", currentTask.id, currentTask);
        }
        System.out.println(lineBreak);
    }

    /**
     * Mark or unmark a task number depending on the input. Catches errors if user
     * enters invalid inputs etc.
     * @param input String, containing mark/unmark and the task number to perform action
     */
    void mark(String input) {
        String[] inputArr = input.split(" ");
        try {
            if (isValidInput(inputArr)) {
                String action = inputArr[0];
                String num = inputArr[1];
                int taskIndex = Integer.parseInt(num) - 1;
                if (taskExists(taskIndex)) {
                    if (action.equals("mark")) {
                        tasks[taskIndex].markDone();
                        done++;
                        System.out.printf("This is now done:\n%s\n", tasks[taskIndex]);
                        System.out.println(lineBreak);
                    } else if (action.equals("unmark")) {
                        tasks[taskIndex].markNotDone();
                        done--;
                        System.out.printf("This is now undone:\n%s\n", tasks[taskIndex]);
                        System.out.println(lineBreak);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(lineBreak);
        }
    }

    boolean taskExists(int index) throws IllegalArgumentException {
        if (index >= 0 && index < this.nextIndex) {
            return true;
        } else {
            throw new IllegalArgumentException("Task number does not exist!");
        }
    }

    boolean isValidInput(String[] inputArr) throws IllegalArgumentException {
        if (inputArr.length == 2) {
            return true;
        } else {
            throw new IllegalArgumentException("Wrong input. Type this: mark taskNumber");
        }
    }
}
