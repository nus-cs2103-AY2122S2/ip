import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> MANAGER;

    public TaskList() {
        this.MANAGER = new ArrayList<>();
    }

//    public TaskList(ArrayList<Task> manager) {
//        this.MANAGER = manager;
//
//    }

    public static void reportList() throws CustomException {
        int len = MANAGER.size();
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                System.out.println((i + 1) + ". " + MANAGER.get(i).toString());
            }
        } else {
            throw new CustomException("your task list is currently empty. " +
                    "Add a task first:)");
        }
    }

    public static Task findTask(int num) throws CustomException {
        Task desiredTask = new Task("empty task");
        int len = MANAGER.size();

        if ((num <= len) && (len > 0) && (num > 0)) {
            for (int i = 1; i <= len; i++) {
                if (i == num) {
                    desiredTask = MANAGER.get(i - 1);
                }
            }
            return desiredTask;
        } else if (num < 0) {
            throw new CustomException("Invalid task ID: number must be a positive integer:)");
        } else if (num > len) {
            throw new CustomException("Invalid task ID: this task number does not exist as of now.");
        } else { // length of task list == 0
            throw new CustomException("your task list is currently empty. " +
                    "Add a task first:)");
        }
    }

    public static void addTask(String taskType, String instruction) throws CustomException {
        Task toAdd = new Task("empty task");
        if (taskType.equals("todo")) {
            String description = instruction.substring(4);
            if (description.isBlank()) {
                throw new CustomException("todo description cannot be blank! Please add task details");
            } else {
                toAdd = new Todo(description.substring(1));
            }
        } else if (taskType.equals("deadline")) {
            int index = instruction.indexOf("/by");
            if (index != -1) {
                String description = instruction.substring(9, index);
                String time = instruction.substring(index + 4);
                toAdd = new Deadline(description, time);
            } else {
                throw new CustomException("Incorrect time format: ensure to prefix time with '/by'");
            }
        } else {
            int index = instruction.indexOf("/at");
            if (index != -1) {
                String description = instruction.substring(6, index);
                String time = instruction.substring(index + 4);
                toAdd = new Event(description, time);
            } else {
                throw new CustomException("Incorrect time format: ensure to prefix time with '/at'");
            }

        }
        MANAGER.add(toAdd);
        System.out.println("Rodger that! Task item added:");
        System.out.println(toAdd);
    }

    public static void deleteTask(int num) throws CustomException {
        if (num <= 0) {
            throw new CustomException("Invalid task ID: number must be a positive integer:)");
        } else if ( num > MANAGER.size()) {
            throw new CustomException("Invalid task ID: this task number does not exist as of now.");
        } else {
            MANAGER.remove(num - 1);
            System.out.println("Sure! Task item " + num + " has now been deleted:)");
        }
    }

    public static void markAsDone(int num) throws CustomException {
        Task t = findTask(num);
        t.markDone();
        System.out.println("Congrats! Keep going:)");
        System.out.println(t);
    }

    public static void markNotDone(int num) throws CustomException {
        Task t = findTask(num);
        t.undo();
        System.out.println("No worries:) Stay motivated!");
        System.out.println(t);
    }
}
