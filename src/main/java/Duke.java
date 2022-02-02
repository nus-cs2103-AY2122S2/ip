import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    public static ArrayList<Task> manager = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        addLineBreak();

        while (true) {
            String instruct = br.readLine();
            try {
                if (instruct.equals("bye")) {
                    endSession();
                    break;
                } else if (instruct.equals("list")) {
                    reportList();
                } else {
                    String[] details = instruct.split(" ");
                    if (details[0].equals("mark")) {
                        markAsDone(Integer.parseInt(details[1]));
                    } else if (details[0].equals("unmark")) {
                        markNotDone(Integer.parseInt(details[1]));
                    } else if (details[0].equals("delete")) {
                        deleteTask(Integer.parseInt(details[1]));
                    } else {
                        String taskType = details[0];
                        if (!(taskType.equals("todo") || taskType.equals("deadline")
                                || taskType.equals("event"))) {
                            throw new CustomException("sorry, this isn't a valid command yet!");
                        } else {
                            addTask(taskType, instruct);
                        }
                    }
                }
                addLineBreak();
            } catch (CustomException e) {
                System.out.println(e.getMessage());
                addLineBreak();
            }
        }

        br.close();
    }

    public static void greet() {
        String welcome = "Hi my name is Duke!";
        String assist = "How may I help you today?";
        System.out.println(welcome);
        System.out.println(assist);
    }

    public static void endSession() {
        String goodbye = "Adios! See you soon:)";
        System.out.println(goodbye);
    }

    public static void addLineBreak() {
        System.out.println("---------------------xx-------------------------");
    }

    public static void reportList() throws CustomException {
        int len = manager.size();
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                System.out.println((i + 1) + ". " + manager.get(i).toString());
            }
        } else {
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
                int indexOfDifferentiator = time.indexOf("-");
                String dateAndStartTime = time.substring(0, indexOfDifferentiator).trim();
                String endTime = time.substring(indexOfDifferentiator + 1).trim();
                toAdd = new Event(description, dateAndStartTime, endTime);
            } else {
                throw new CustomException("Incorrect time format: ensure to prefix time with '/at'");
            }

        }
        manager.add(toAdd);
        System.out.println("Rodger that! Task item added:");
        System.out.println(toAdd);
    }

    public static void deleteTask(int num) throws CustomException {
        if (num <= 0) {
            throw new CustomException("Invalid task ID: number must be a positive integer:)");
        } else if (num > manager.size()) {
            throw new CustomException("Invalid task ID: this task number does not exist as of now.");
        } else {
            manager.remove(num - 1);
            System.out.println("Sure! Task item " + num + " has now been deleted:)");
        }
    }

    public static Task findTask(int num) throws CustomException {
        Task desiredTask = new Task("empty task");
        int len = manager.size();

        if ((num <= len) && (len > 0) && (num > 0)) {
            for (int i = 1; i <= len; i++) {
                if (i == num) {
                    desiredTask = manager.get(i - 1);
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
