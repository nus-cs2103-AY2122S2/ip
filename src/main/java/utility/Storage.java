package utility;

import exception.DukeException;
import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(this.filePath);

        if(file.exists()){
            try{
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()){
                    String[] taskLine = sc.nextLine().split(",");
                    Task t;
                    if(taskLine[0].equals("T")){
                        t = new Todo(taskLine[2]);
                    } else if(taskLine[0].equals("D")){
                        t = new Deadline(taskLine[2], taskLine[3]);
                    } else  if(taskLine[0].equals("E")){
                        t = new Event(taskLine[2],taskLine[3]);
                    } else {
                        throw new DukeException("cannot read the line");
                    }
                    if(taskLine[1].equals("X")){
                        t.setMarked(true);
                    }
                    taskList.add(t);
                }

            } catch (FileNotFoundException e) {
                throw new DukeException("file cannot be found!");
            }
        }

        return taskList;
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            File file;
            FileWriter writer = new FileWriter(this.filePath);
            for(Task t : tasks.getTasks()) {
                String result = "";
                if(t instanceof Todo) {
                    result += "T,";
                } else if(t instanceof Event) {
                    result += "E,";
                } else if(t instanceof Deadline) {
                    result += "D,";
                }
                if (t.isMarked()) {
                    result += "X,";
                } else {
                    result += "O,";
                }
                result += t.getName();
                result += ",";
                if(t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    result += d.getTime();
                    result += ",";
                } else if(t instanceof Event) {
                    Event e = (Event) t;
                    result += e.getTime();
                    result += ",";
                }
                writer.write(result);
                writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            throw new DukeException("cannot save tasks");
        }
    }
}
