import java.io.FileWriter; // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Storage {
    //    String contents;
    String filename;
    TaskList tasks;

    public Storage(String filename) {
        this.filename = filename;
        this.tasks = new TaskList();
    }

    public void saveFile(String args) {
        try {
            FileWriter myWriter = new FileWriter(this.filename, false);
            myWriter.write(args);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public TaskList readFile(String filepath) throws DukeException {
        try {
            File myObj = new File(System.getProperty("user.dir")+ "/" + filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // Process the data such that it can see if this is event or deadline or todo,
                // then create accordingly
                processString(data);
            }
            myReader.close();
            return this.tasks;
        } catch (FileNotFoundException e) {
            return this.tasks;
        }
    }

    private void processString(String line) throws DukeException {
        String[] parts = line.split("\\|");
        String state = parts[0].trim();
        String type = parts[1].trim();
        String description = parts[2].trim();
        // 1. Check Task type
        if (type.equals("D")) {
            String time = parts[3];
            DateHelper datetime = new DateHelper(time);
            Deadline out = new Deadline(description + " ", datetime);
            this.tasks.addTasks(out);
        } else if (type.equals("E")) {
            String time = parts[3];
            DateHelper datetime = new DateHelper(time);
            Event out = new Event(description + " ", datetime);
            this.tasks.addTasks(out);
        } else if (type.equals("T")) {
            Todo out = new Todo(description + " ");
            this.tasks.addTasks(out);
        }
        // 2. Check if marked
        if (state.equals("1")) {
            this.tasks.getTask(this.tasks.getSize()-1).setDone();
        }
    }
}
