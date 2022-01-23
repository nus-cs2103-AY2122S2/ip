import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(String filePath) {
        this.filePath = filePath;
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList) {
                fw.write(task.getTaskData());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Task list cannot be saved!");
        }
    }

    public void load() {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                boolean isDone = false;
                Task newTask;
                String[] substring;

                //split each line in text file into [task type, isDone, task description]
                String[] data = sc.nextLine().split(" ", 3);

                //assign isDone
                switch(data[1]) {
                case "":
                    isDone = false;
                case "1":
                    isDone = true;
                }

                //add task to array list
                switch(data[0]) {
                case "T":
                    taskList.add(new ToDo(data[2], isDone));
                    break;
                case "D":
                    substring = data[2].split("/by ", 2);
                    if (substring.length < 2) {
                        newTask = new Deadline(substring[0], isDone);
                    } else {
                        newTask = new Deadline(substring[0], substring[1], isDone);
                    }
                    taskList.add(newTask);
                    break;
                case "E":
                    substring = data[2].split("/at ", 2);
                    if (substring.length < 2) {
                        newTask = new Event(substring[0], isDone);
                    } else {
                        newTask = new Event(substring[0], substring[1], isDone);
                    }
                    taskList.add(newTask);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file path does not exist!");
        }
    }


    private void add(Task task) {
        taskList.add(task);
        System.out.println("OK...");
        System.out.println("The following task has been added to the list: ");
        System.out.println(task);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
    }

    public void add(String[] str, Command command) throws EmptyDescriptionException {
        if (str.length < 2) {
            throw new EmptyDescriptionException(str[0]);
        } else {
            Task newTask;
            String[] substring;

            switch (command) {
            case TODO:
                newTask = new ToDo(str[1]);
                this.add(newTask);
                break;

            case DEADLINE:
                substring = str[1].split("/by ", 2);
                if (substring.length < 2) {
                    newTask = new Deadline(substring[0]);
                } else {
                    newTask = new Deadline(substring[0], substring[1]);
                }
                this.add(newTask);
                break;

            case EVENT:
                substring = str[1].split("/at ", 2);
                if (substring.length < 2) {
                    newTask = new Event(substring[0]);
                } else {
                    newTask = new Event(substring[0], substring[1]);
                }
                this.add(newTask);
                break;
            }
        }
    }

    public void markTask(String[] str) throws InvalidTaskNumberException {
        if (str.length < 2 || !isNumeric(str[1]) || Integer.parseInt(str[1]) > taskList.size()) {
            throw new InvalidTaskNumberException(str[0]);
        }
        Task task = this.get(Integer.parseInt(str[1]));
        task.markAsDone();
    }

    public void unmarkTask(String[] str) throws InvalidTaskNumberException {
        if (str.length < 2 || !isNumeric(str[1]) || Integer.parseInt(str[1]) > taskList.size()) {
            throw new InvalidTaskNumberException(str[0]);
        }
        Task task = this.get(Integer.parseInt(str[1]));
        task.markAsUndone();
    }

    private void delete(int taskNumber) {
        System.out.println("OK...");
        System.out.println("The following task has been removed from the list: ");
        System.out.println(taskList.get(taskNumber - 1));
        taskList.remove(taskNumber - 1);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
    }

    public void delete(String[] str) throws InvalidTaskNumberException {
        if (str.length < 2 || !isNumeric(str[1]) || Integer.parseInt(str[1]) > taskList.size()) {
            throw new InvalidTaskNumberException(str[0]);
        }
        this.delete(Integer.parseInt(str[1]));
    }

    public Task get(int item) {
        return taskList.get(item - 1);
    }

    public int numOfTasks() {
        return taskList.size();
    }

    public void list() {
        if (numOfTasks() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task task : taskList) {
            output += counter + "." + task + "\n";
            counter += 1;
        }
        return output.substring(0,output.length() - 1);
    }

    private boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
