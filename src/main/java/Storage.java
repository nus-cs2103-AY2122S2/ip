import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    protected Path absoluteFilePath;

    public Storage(String filePath) {
        String currentDir = System.getProperty("user.dir");
        this.absoluteFilePath = Paths.get(currentDir, filePath);
    }

    public ArrayList<Task> load() throws DukeException  {

        try {
            FileInputStream fis = new FileInputStream(absoluteFilePath.toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> tasks = (ArrayList<Task>) ois.readObject();
            fis.close();
            ois.close();
            return tasks;
        } catch (Exception e) {
            throw new DukeException("Hello! I see it's your first time here!");
        }
    }

    public void save(ArrayList<Task> tasks) {
        File file = new File(absoluteFilePath.toString());
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {      }

        try {
            FileOutputStream fos = new FileOutputStream(absoluteFilePath.toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
