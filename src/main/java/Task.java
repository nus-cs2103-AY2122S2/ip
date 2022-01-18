import java.util.*;

public class Task {
    protected String name;
    protected boolean isDone;
    protected int id;

    private static Task[] arrayOfTask = new Task[100];
    private static int counter = 0;

    Task(String name) {
        this.name = name;
        this.id = counter + 1;
        this.isDone = false;
        arrayOfTask[counter] = this;
        counter++;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }
    public static Task[] getTaskList() {
        return Task.arrayOfTask;
    }

    public static String printArray() {
        int arrayLength = arrayOfTask.length;
        String output = "       This is the list of all tasks :D \n";

        for(int i = 0; i < arrayLength; i++) {
            if(arrayOfTask[i] != null) {
                output += "       " + Integer.toString(i + 1) + "." + arrayOfTask[i];
                if(i != arrayLength - 1) {
                    output += "\n";
                }
            }

        }

        return output;
    }

    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + arrayOfTask[this.id - 1].name;
    }

}
