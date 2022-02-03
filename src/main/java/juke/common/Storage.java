package juke.common;

import juke.Juke;
import juke.exception.JukeException;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.Task;
import juke.task.TaskStatus;
import juke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    /**
     * Reference to the Juke instance.
     */
    private final Juke juke;
    
    private static final String PATH_NAME = "data/juke.txt";
    
    private File file = new File(PATH_NAME);;
    
    public Storage(Juke instance) {
        this.juke = instance;
        this.initialiseFile();
    }
    
    private void initialiseFile() {
        try {
            if (this.file.getParentFile().mkdirs()) {
                this.juke.getUi().formattedPrint("Directories not found. Creating new directories.");
            }
            if (this.file.createNewFile()) {
                this.juke.getUi().formattedPrint("File not found. Creating new file.");
            }
        } catch (IOException | SecurityException e) {
            this.juke.getUi().formattedPrint(e.getMessage());
        }
    }
    
    public void loadTasks() {
        ArrayList<String[]> parseArr = this.parse();
        for (String[] args : parseArr) {
            try {
                Task task = this.decode(args);
                if (task != null) {
                    this.juke
                        .getTaskList()
                        .add(task);
                }
            } catch (JukeException e) {
                this.juke.getUi().formattedPrint(e.getMessage());
                System.exit(-1);
            }
        }
    }
    
    public void saveTasks() {
        ArrayList<String[]> writeArr = new ArrayList<>();
        for (Task task : this.juke.getTaskList()) {
            String[] args = this.encode(task);
            if (args != null) {
                writeArr.add(args);
            }
        }
        this.write(writeArr);
    }
    
    public ArrayList<String[]> parse() {
        ArrayList<String[]> array = new ArrayList<>();
        try {
            Scanner in = new Scanner(this.file);
            while (in.hasNextLine()) {
                array.add(in.nextLine().strip().split(";"));
            }
            in.close();
        } catch (FileNotFoundException e) {
            this.juke.getUi().formattedPrint(e.getMessage());
        }
        return array;
    }
    
    public boolean write(ArrayList<String[]> array) {
        try {
            FileWriter out = new FileWriter(file);
            for (String[] args : array) {
                String str = String.join(";", args);
                out.write(str + "\n");
            }
            out.close();
        } catch (IOException e) {
            this.juke.getUi().formattedPrint(e.getMessage());
            return false;
        }
        return true;
    }
    
    public Task decode(String[] args) throws JukeException {
        Task task = null;
        if (args.length > 2) {
            boolean mark = Boolean.parseBoolean(args[1]);
            switch (args[0]) {
            case "E":
                if (args.length < 4) {
                    throw new JukeException("File has incorrect arguments");
                } else {
                    task = new Event(args[2], args[3]);
                }
                break;
            case "T":
                task = new Todo(args[2]);
                break;
            case "D":
                if (args.length < 4) {
                    throw new JukeException("File has incorrect arguments");
                } else {
                    task = new Deadline(args[2], args[3]);
                }
                break;
            default:
                throw new JukeException("File has incorrect arguments");
            }
            if (mark) {
                task.markAsDone();
            }
        } else {
            throw new JukeException("File has incorrect arguments");
        }
        return task;
    }
    
    public String[] encode(Task task) {
        String[] args = null;
        if (task instanceof Todo) {
            args = new String[3];
            args[0] = "T";
            if (task.getStatus() == TaskStatus.DONE) {
                args[1] = "true";
            } else {
                args[1] = "false";
            }
            args[2] = task.getDescription();
        } else if (task instanceof Event) {
            args = new String[4];
            args[0] = "E";
            if (task.getStatus() == TaskStatus.DONE) {
                args[1] = "true";
            } else {
                args[1] = "false";
            }
            args[2] = task.getDescription();
            args[3] = ((Event) task).getTime();
        } else if (task instanceof Deadline) {
            args = new String[4];
            args[0] = "D";
            if (task.getStatus() == TaskStatus.DONE) {
                args[1] = "true";
            } else {
                args[1] = "false";
            }
            args[2] = task.getDescription();
            args[3] = ((Deadline) task).getTime();
        }
        return args;
    }
}
