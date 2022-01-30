package batman.tasks;

import batman.exception.DukeException;
import batman.exception.Error;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static String addTask(String[] command) {
        try {
            String task;
            Task t;
            String description;
            String details;
            if (command.length <= 1) {
                throw new DukeException(Error.EMPTYDESC);
            }
            task = command[1];
            switch (command[0]) {
            case "deadline":
                description = task.split(" /")[0];
                details = task.split("/by ")[1];
                t = new Deadline(description, details);
                break;
            case "event":
                description = task.split(" /")[0];
                details = task.split("/at ")[1];
                t = new Event(description, details);
                break;
            default:
                description = task;
                t = new Todo(description);
            }
            tasks.add(t);
            return printTask(t);
        } catch (DukeException e) {
            return e.emptyDesc();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Missing argument(s) for tasks\n" +
                    "e.g. <task> <desc> /(at or by) <datetime>\n";
        }
        catch (DateTimeParseException e) {
            return "Get the date format right!\ndd/MM/yyyy HH:mm OR yyyy-MM-dd HH:mm\n";
        }
    }

    public static String deleteTask(int index) {
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LISTERROR);
            }
            Task t = tasks.get(index);
            tasks.remove(index);
            return "Got it. Task removed:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
        } catch (DukeException e) {
            return printList() + e.listError();
        }
    }

    public static StringBuilder toggleStatus(String action, int index) {
        StringBuilder sb = new StringBuilder();
        try {
            if (index >= tasks.size() || index < 0) {
                throw new DukeException(Error.LISTERROR);
            }
            switch (action) {
            case "mark":
                tasks.get(index).markItem();
                sb.append("Alfred, mark it as done!\n  ").
                        append(tasks.get(index).toString()).append("\n");
                break;
            case "unmark":
                tasks.get(index).unmarkItem();
                sb.append("Make up your mind. Alfred, unmark it!\n  ").
                        append(tasks.get(index).toString()).append("\n");
                break;
            }
            return sb;
        } catch (DukeException e) {
            return sb.append(printList()).append(e.listError());
        }
    }

    public static StringBuilder findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(keyword)) {
                temp.add(t);
            }
        }
        if (temp.size() == 0) {
            sb.append("No matching tasks in your list.\n");
        }
        else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < temp.size(); i++) {
                sb.append(i + 1).append(". ").append(temp.get(i)).append("\n");
            }
        }
        return sb;
    }

    public void getTasksFromFile(List<String> lines) {
        tasks = new ArrayList<>();
        if (lines != null) {
            for (String line : lines) {
                String[] item = line.split("\\|");
                switch (item[0]) {
                case "D":
                    tasks.add(new Deadline(item[1].equals("1"), item[2], item[3]));
                    break;
                case "E":
                    tasks.add(new Event(item[1].equals("1"), item[2], item[3]));
                    break;
                default:
                    tasks.add(new Todo(item[1].equals("1"), item[2]));
                }
            }
        }
    }

    public static String printList() {
        StringBuilder s = new StringBuilder();
        if (tasks.size() == 0) {
            return "List is empty.\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }

    private static String printTask(Task t) {
        return "Got it. Task added:\n  " + t + "\n" + t.printNoOfTasks(tasks.size()) + "\n";
    }
}
