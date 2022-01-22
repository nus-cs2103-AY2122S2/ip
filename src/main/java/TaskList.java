import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int size = 0;

    public TaskList(int limit) {
        taskList = new ArrayList<>(limit);
    }

    @Override
    public String toString() {
        String message = "\tDear Master, ";
        if (size == 0) {
            message = message + "you have not added anything";
        } else {
            message = message + "here is a list of things you have added:\n";
            for (int i = 0; i < size; i++) {
                message = message + String.format("\t%2d. %s\n", i + 1, getTask(i));
            }
        }

        return message;
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) throws OutOfRangeException {
        if (size == 0) {
            throw new OutOfRangeException("Dear Master, you have not added anything");
        } else if (index < 0 || index >= size) {
            throw new OutOfRangeException(
                    String.format("Sorry Master, please enter an integer between 1 and %d (inclusive)", size));
        }
        return taskList.get(index);
    }

    public void addTask(String item) {
        taskList.add(size, new Task(item));
        size++;

        System.out.println(String.format("\tUnderstood, I have added \"%s\" to the list", item));
    }

    public void addTask(Task task) {
        taskList.add(size, task);
        size++;

        System.out.println(String.format("\tUnderstood, I have added this task to the list:\n"
                + "\t    %s\n"
                + "\tYou have %d task(s) currently", task, size));
    }

    public void addTask(String inst, String task) {
        try {
            switch (inst) {
            case "todo":
                addTask(new ToDoTask(task));
                break;
            case "deadline":
                addTask(new DeadlineTask(task));
                break;
            case "event":
                addTask(new EventTask(task));
                break;
            }
        } catch (EmptyMessageException emptyEx) {
            System.out.println("\t" + emptyEx.getMessage());
        } catch (DateFormatException dateEx) {
            System.out.println("\t" + dateEx.getMessage());
        } catch (DateTimeParseException dateTimeEx) {
            System.out.println("\tDear Master, please enter the date using \"YYYY-MM-DD\" format");
        }
    }

    protected Task delete(int index) throws OutOfRangeException {
        if (size == 0) {
            throw new OutOfRangeException("Dear Master, you have not added anything");
        } else if(index < 0 || index >= size) {
            throw new OutOfRangeException(
                    String.format("Sorry Master, please enter an integer between 1 and %d (inclusive)", size));
        }

        Task delTask = taskList.remove(index);
        size--;

        return delTask;
    }

    public void deleteTask(String message) {
        try {
            int index = Integer.valueOf(message);
            Task delTask = delete(index - 1);

            System.out.println(String.format("\tYes Master, I have removed this task:\n"
                + "\t    %s\n"
                + "\tYou have %d task(s) currently", delTask, size));
        } catch (NumberFormatException numEx) {
            System.out.println("\tSorry Master, you have to enter an integer after the \"delete\" command");
        } catch (OutOfRangeException rangeEx) {
            System.out.println("\t" + rangeEx.getMessage());
        }
    }

    public void markTask(String message) {
        try {
            int index = Integer.valueOf(message);
            Task currentTask = getTask(index - 1);
            currentTask.markDone();

            System.out.println("\tYes Master, I have marked this task as done:");
            System.out.println(String.format("\t    %s", currentTask));
        } catch (NumberFormatException numEx) {
            System.out.println("\tSorry Master, you have to enter an integer after the \"mark\" command");
        } catch (OutOfRangeException rangeEx) {
            System.out.println("\t" + rangeEx.getMessage());
        }

    }

    public void unmarkTask(String message) {
        try {
            int index = Integer.valueOf(message);
            Task currentTask = getTask(index - 1);
            currentTask.markNotDone();

            System.out.println("\tYes Master, I have marked this task as not done yet:");
            System.out.println(String.format("\t    %s", currentTask));
        } catch (NumberFormatException numEx) {
            System.out.println("\tSorry Master, you have to enter an integer after the \"unmark\" command");
        } catch (OutOfRangeException rangeEx) {
            System.out.println("\t" + rangeEx.getMessage());
        }
    }
}
