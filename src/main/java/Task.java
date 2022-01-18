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
        arrayOfTask[counter] = this;
        counter++;
    }

    public static String printArray() {
        int arrayLength = arrayOfTask.length;
        String output = "       This is the list of all tasks :D \n";

        for(int i = 0; i < arrayLength; i++) {
            if(arrayOfTask[i] != null) {
                output += "       " + arrayOfTask[i];
                if(i != arrayLength - 1) {
                    output += "\n";
                }
            }

        }

        return output;
    }

    public String toString() {
        return Integer.toString(this.id) + ". " + arrayOfTask[this.id - 1].name;
    }

}
