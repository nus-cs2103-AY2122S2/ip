package duke.storage;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public void writeToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int i = 0;
        while (i < list.getSize()) {
            String textToAdd = list.getTask(i).getTaskData();
            fw.write(textToAdd + System.lineSeparator());
            i++;
        }
        fw.close();
    }

    public ArrayList<Task> loadTasksFromFile() throws FileNotFoundException{
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String currTask = s.nextLine();
                String typeOfTask = Character.toString(currTask.charAt(1));
                boolean isMarked = false;
                if (Character.toString(currTask.charAt(4)).equals("X")) {
                    isMarked = true;
                }
                if (typeOfTask.equals("T")) {
                    Todo curr = new Todo(currTask.substring(7));
                    if (isMarked) {
                        curr.mark();
                    }
                    list.add(curr);
                } else if (typeOfTask.equals("D")) {
                    String[] info = currTask.split(" \\(by: ");
                    String description = info[0].substring(7);
                    String date = info[1].substring(0, info[1].length() - 1); //Oct 12 2020 1 pm
                    Deadline curr = new Deadline(description, date);
                    if (isMarked) {
                        curr.mark();
                    }
                    list.add(curr);
                } else {
                    String[] info = currTask.split(" \\(at: ");
                    String description = info[0].substring(7);
                    String date = info[1].substring(0, info[1].length() - 1);
                    Event curr = new Event(description, date);
                    if (isMarked) {
                        curr.mark();
                    }
                    list.add(curr);
                }
            }
        } catch (Exception err) {
            File myObj = new File(filePath);
            try {
                myObj.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return list;
    }
}
