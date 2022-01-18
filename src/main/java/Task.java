import java.util.*;

public class Task {
    private String name;
    private static String[] arrayOfTask = new String[100];
    private static int counter = 0;

    Task(String name) {
        this.name = name;
        arrayOfTask[counter] = name;
        counter++;
    }

    public static String printArray() {
        int arrayLength = arrayOfTask.length;
        String output = "";

        for(int i = 0; i < arrayLength; i++) {
            if(arrayOfTask[i] != null) {
                output += "       " + Integer.toString(i+1) + ". " + arrayOfTask[i];
                if(i != arrayLength - 1) {
                    output += "\n";
                }
            }

        }

        return output;
    }

}
