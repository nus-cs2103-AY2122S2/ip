import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class TaskList {
    private Scanner sc;
    private final ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
        try {
            this.sc = new Scanner(new File("src/main/java/data/duke.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        this.getTaskListFromDukeTxt();
    }

    Task getTask(int index) {
        try {
            return taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }


    void markTask(int index) throws IndexOutOfBoundsException {
        try {
            taskList.get(index - 1).setDone();
            updateDukeTxt();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    void unmarkTask(int index) throws IndexOutOfBoundsException {
        try {
            taskList.get(index - 1).setUndone();
            updateDukeTxt();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    void addTask(Task task) {
        taskList.add(task);
        updateDukeTxt();
    }

    void printTasks() {
        for (int index = 0; index < this.taskList.size(); index++) {
            System.out.println(Integer.toString(index + 1) + ". " + taskList.get(index).toString());
        }
    }

    void printNoTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    void deleteFromIndex(int index) {
        this.taskList.remove(index - 1);
        updateDukeTxt();
    }

    private void getTaskListFromDukeTxt() {
        while (sc.hasNextLine()) {
            String type = sc.nextLine();
            Boolean done = Boolean.parseBoolean(sc.nextLine());
            String name = sc.nextLine();
            String date = sc.nextLine();
            if (date.equals("*** Next Task ***")) {
                date = null;
            } else {
                sc.nextLine();
            }
            try {
                this.taskList.add(Task.createTask(type, done, name, date));
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            }
            
        }
    }

    private void updateDukeTxt() {
        try {
            FileWriter fw = new FileWriter("src/main/java/data/duke.txt");
            for (Task task : this.taskList) {
                fw.write(task.updateIntoDatabase());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}