package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    protected static ArrayList<Task> manager;
    private static Parser parser;

    public TaskList() {
        manager = new ArrayList<>();
        parser = new Parser();
    }

    public static void reportList() throws CustomException {
        int len = manager.size();
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                System.out.println((i + 1) + ". " + manager.get(i).toString());
            }
        } else {
            throw new CustomException("your task list is currently empty. " + "Add a task first:)");
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
            throw new CustomException("your task list is currently empty. " + "Add a task first:)");
        }
    }

    public static void addTask(String taskType, String instruction) {
        try {
            Task toAdd = Parser.parseCommandFromUser(taskType, instruction);
            manager.add(toAdd);
            Ui.showSuccessfulMessage("Rodger that! Task item added:");
            Ui.displayTask(toAdd);
        } catch (CustomException e) {
            Ui.displayError(e.getMessage());
        }
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

    public static void findTasks(String keywords) {
        List<String> allKeywords = Arrays.stream(keywords.split(",")).sequential().map(String::trim).collect(Collectors.toList());
        ArrayList<Task> matchingTasks = manager.stream().filter(x -> allKeywords.stream().anyMatch(y -> x.description.contains(y))).collect(Collectors.toCollection(ArrayList<Task>::new));
        System.out.println("Here are the tasks found: ");
        matchingTasks.forEach(System.out::println);
    }
}
