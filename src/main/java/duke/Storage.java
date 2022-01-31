package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

public class Storage {
    protected String filePath;
    ArrayList<Task> tasks;

    Storage(String filePath) {
        this.filePath = filePath;
        File data = new File(filePath);
        data.getParentFile().mkdirs(); //make preceding directories, if any are not found

        tasks = new ArrayList<>();
        try {
            if (!data.createNewFile()) { //if file exists
                Scanner fileReader = new Scanner(data);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] tmp = line.split("\\|");

                    boolean isDone = tmp[1].trim().equals("D");
                    //assumes valid input
                    switch (tmp[0].trim()) {
                    case "T":
                        Todo t = new Todo(tmp[2].trim());
                        if (isDone) {
                            t.markComplete();
                        }
                        tasks.add(t);
                        break;
                    case "D":
                        Deadline d = new Deadline(tmp[2].trim(), tmp[3].trim());
                        if (isDone) {
                            d.markComplete();
                        }
                        tasks.add(d);
                        break;
                    case "E":
                        Event e = new Event(tmp[2].trim(), tmp[3].trim());
                        if (isDone) {
                            e.markComplete();
                        }
                        tasks.add(e);
                        break;
                    //needs default exception handling
                    }
                }
                fileReader.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not read file at " + filePath, e);
        }
    }

    public ArrayList<Task> load() {
        return tasks;
    }

    //data.txt file + all its directories will be present at this point
    public void save(TaskList tasks) {
        File data = new File(filePath);
        //File data = new File("duke.txt");
        FileWriter f;

        try {
            f = new FileWriter(data, false);
            boolean isFirst = true;
            for (int i = 0; i < tasks.size(); i++) {
                String s = isFirst ? "" : "\n";
                f.write(s + tasks.get(i).writeToFile());
                isFirst = false;
            }
            f.close();
        } catch (IOException e) {
            System.out.println("An error occurred with writing to the data file.");
            e.printStackTrace();
        }
    }
}
