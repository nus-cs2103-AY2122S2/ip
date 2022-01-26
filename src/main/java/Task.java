import java.io.File;
import java.util.ArrayList;

public class Task {
    protected String name;
    protected boolean isDone;
    protected int id;

    private static ArrayList<Task> listOfTask = new ArrayList<>(100);
    private static int counter = 0;

    Task(String name) {
        this.name = name;
        this.id = counter + 1;
        this.isDone = false;
        listOfTask.add(this);
        counter++;
    }

    public void markDone() {
        this.isDone = true;
        updateFile();
    }

    public void markNotDone() {
        this.isDone = false;
        updateFile();
    }
    
    public void deleteTask(Task t) {
        listOfTask.remove(t);
        counter--;
        updateFile();
    }
    
    
    public static Task[] getTaskList() {
        Task[] newArray = listOfTask.toArray(new Task[0]);
        return newArray;
    }
    public static String getCounter() {
        return Integer.toString(counter);
    }

    public static String printArray() {
        int arrayLength = listOfTask.size();
        Task[] tempArray = Task.getTaskList();
        String output = "       This is the list of all tasks :D \n";

        for(int i = 0; i < arrayLength; i++) {
            if(tempArray[i] != null) {
                output += "       " + Integer.toString(i + 1) + "." + tempArray[i];
                if(i != arrayLength - 1) {
                    output += "\n";
                }
            }

        }

        return output;
    }

    private void updateFile() {
        FileReading.writeToPath("data/duke.txt", Task.printArray());
    }

    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.name;
    }

}
