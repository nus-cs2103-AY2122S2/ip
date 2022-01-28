package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        try {
            f.createNewFile();
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            while(sc.hasNext()) {
                String fullCommand = sc.nextLine();
                String[] commandArr = fullCommand.split(" \\| ");
                String type = commandArr[0];
                boolean status = (Integer.parseInt(commandArr[1]) == 1);
                String description = commandArr[2];
                switch(type) {
                    case "E":
                        String at = commandArr[3];
                        tasks.add(new Event(description, at, status));
                        break;
                    case "D":
                        String by = commandArr[3];
                        tasks.add(new Deadline(description, by, status));
                        break;
                    case "T":
                        tasks.add(new ToDo(description, status));
                        break;
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void save(TaskList tasks) throws DukeException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).save());
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
