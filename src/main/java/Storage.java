import java.io.*;
import java.util.ArrayList;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws Exception {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(filePath);
            in = new ObjectInputStream(fileIn);
            ArrayList<Task> tasks = (ArrayList<Task>) in.readObject();
            return tasks;
        } catch (FileNotFoundException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            throw new DukeException("Could not load save, creating new save file");
        } catch (EOFException e) {
            throw new DukeException("Empty save");
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    public void save(TaskList taskList) throws Exception {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList.tasks);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);
            fileOut = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList.tasks);
            out.close();
            fileOut.close();
            throw new DukeException("Error trying to save your tasks, did  you delete the file while the program was still running?");
        }
    }

}
