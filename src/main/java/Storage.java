import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private String taskFilePath; 
    private Ui ui;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String taskFilePath, Ui ui) {
        this.taskFilePath = taskFilePath;
        this.ui = ui;
    }

    public ArrayList<Task> loadFileContents() {
        // load tasklist data from save 
        try {
            File f = new File(taskFilePath);
            
            // checks if the user already has existing save data 
            if (!f.createNewFile()) {
                ui.showText("loading previous save data...");
                Scanner s = new Scanner(f);
                while(s.hasNext()) {
                    
                    String task = s.nextLine();
                    String[] taskStrings = task.split(" \\| ");
                    String taskType = taskStrings[0];
                    boolean taskStatus = taskStrings[1] == "1";
                    String taskDescription = taskStrings[2];

                    switch (taskType) {
                        case "T":
                            taskList.add(new Todo(taskDescription, taskStatus));
                            break;
                        case "E":
                            taskList.add(new Event(taskDescription, taskStrings[3], taskStatus));
                            break;
                        case "D":
                            taskList.add(new Deadline(taskDescription, taskStrings[3], taskStatus));
                            break;
                        default:
                            throw new DukeException("Invalid save data");
                    }
                }
                s.close();
            // else creates the save data for further use
            } else {
                ui.showText("creating save data...");
            }
            
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        return taskList;
    }

    public void updateFileContents(ArrayList<Task> taskList) {
        // write & update save data with current tasklist 
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            for (Task t: taskList) {
                fw.write(t.toStringSaveData());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

}
