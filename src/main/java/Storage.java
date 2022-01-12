import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected File dir;

    public Storage(String filePath) {
        this.dir = new File(filePath);
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException, DukeException {
        List<Task> loaded = new ArrayList<>();
        dir.getParentFile().mkdirs();
        if (dir.createNewFile()) {
            System.out.println("Old save file found on: " + dir.getAbsolutePath()+"\n");
            System.out.println("There is no old save file found.\n");
        } else {
            System.out.println("Old save file found on: " + dir.getAbsolutePath()+"\n");
            Scanner sc = new Scanner(dir);
            while (sc.hasNext()) {
                String encoded = sc.nextLine();
                loaded.add(Task.deserialize(encoded));
            }
            sc.close();
        }
        return loaded;
    }

    public void appendFile(Task todo) throws IOException{
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(todo.serialize());
        fw.close();
    }

    public void overWriteFile(TaskList todo) throws IOException{
        FileWriter fw = new FileWriter(this.filePath);
        List<String> encodedList = todo.serializedList();
        for (String task : encodedList) {
            fw.write(task);
        }
        fw.close();
    }
}
