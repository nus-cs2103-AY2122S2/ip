package duke.functionality;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void readFile(TaskList taskList) throws DukeException {
        File information = new File(this.filePath);
        if (information.exists()) {
            try {
                File file = new File(this.filePath);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    String[] data = text.split(" \\| ");
                    String initial = data[0];
                    Task history;
                    switch (initial) {
                    case "T":
                        history = new Todo(data[2]);
                        break;
                    case "D":
                        history = new Deadline(data[2], Parser.convertDate(data[3]));
                        break;
                    case "E":
                        history = new Event(data[2], Parser.convertDate(data[3]));
                        break;
                    default:
                        throw new DukeException("Cannot understand the command");
                    }
                    if (data[1].equals("mark")) {
                        history.markAsDone();
                    }
                    taskList.addGeneralTask(history);
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("Cannot find file...");
            }
        }
    }

    public void writeFile(TaskList taskList) throws DukeException {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            String path = directory.getAbsolutePath() + "/Duke.txt";
            File newFile = new File(path);
            FileWriter writer = new FileWriter(newFile);
            StringBuilder data = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                data.append(taskList.get(i).formatString()).append("\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error... try again");
        }
    }
}
