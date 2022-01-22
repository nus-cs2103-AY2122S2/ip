import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;
    private int size = 0;

    private String filePath;

    public TaskList(int limit) {
        taskList = new ArrayList<>(limit);
    }

    public TaskList(int limit, String filePath) {
        this(limit);
        this.filePath = filePath;
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

    public void save() {
        String[] paths = filePath.split("/");
        File folder = new File(paths[0]);
        folder.mkdir();

        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("File not created! An error occurred!");
            e.printStackTrace();
        }

        try {
            FileWriter writeFile = new FileWriter(filePath);
            for (Task t : taskList) {
                writeFile.write(t.textToFile());
                writeFile.write(System.lineSeparator());
            }
            writeFile.close();
        } catch (IOException e) {
            System.out.println("File does not exist! An error occurred!");
            e.printStackTrace();
        }

    }

    public void load() {
        try {
            File file = new File(filePath);
            file.createNewFile();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String d = reader.nextLine();
                String[] data = d.split(" ");

                if (data[0].equals("T")) {
                    loadAddTask("todo", d.substring(4));
                } else if (data[0].equals("D")) {
                    loadAddTask("deadline", d.substring(4));
                } else if (data[0].equals("E")) {
                    loadAddTask("event", d.substring(4));
                }

                if (data[1].equals("1")) {
                    loadMarkTask(size);
                }

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not created! An error occurred!");
            e.printStackTrace();
        }
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

    public void addTask(Task task) {
        taskList.add(task);
        size++;
    }

    public void loadAddTask(String inst, String task) {
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
        }
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

            save();

            System.out.println(String.format("\tUnderstood, I have added this task to the list:\n"
                + "\t    %s\n"
                + "\tYou have %d task(s) currently", task, size));
        } catch (EmptyMessageException emptyEx) {
            System.out.println("\t" + emptyEx.getMessage());
        } catch (DateFormatException dateEx) {
            System.out.println("\t" + dateEx.getMessage());
        }
    }

    protected Task delete(int index) throws OutOfRangeException {
        if (size == 0) {
            throw new OutOfRangeException("Dear Master, you have not added anything");
        } else if (index < 0 || index >= size) {
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
            
            save();
        } catch (NumberFormatException numEx) {
            System.out.println("\tSorry Master, you have to enter an integer after the \"delete\" command");
        } catch (OutOfRangeException rangeEx) {
            System.out.println("\t" + rangeEx.getMessage());
        }
    }

    public void loadMarkTask(int index) {
        Task currentTask = getTask(index - 1);
        currentTask.markDone();
    }

    public void markTask(String message) {
        try {
            int index = Integer.valueOf(message);
            Task currentTask = getTask(index - 1);
            currentTask.markDone();

            save();

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

            save();

            System.out.println("\tYes Master, I have marked this task as not done yet:");
            System.out.println(String.format("\t    %s", currentTask));
        } catch (NumberFormatException numEx) {
            System.out.println("\tSorry Master, you have to enter an integer after the \"unmark\" command");
        } catch (OutOfRangeException rangeEx) {
            System.out.println("\t" + rangeEx.getMessage());
        }
    }
}
