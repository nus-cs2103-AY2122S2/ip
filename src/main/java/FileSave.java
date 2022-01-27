import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileSave {

    //initialize Path object
    static String dataPath = System.getProperty("user.dir") + "/data/duke.txt";
    static Path path = Paths.get(dataPath);

    public static void createFile() {
        //create file
        File dataDir = new File("./data");
        dataDir.mkdirs();
        try {
            Files.createFile(path);
            System.out.println("File has been created...");
        } 
        catch (IOException e) {
           System.out.println("Loading content...");
        }
     }

     public static void writetoFile(ArrayList<Task> tasks) {
        try {
            Files.write(path,"".getBytes());
            for (Task t : tasks) {
                Files.write(path, t.appendtoFile().getBytes(), StandardOpenOption.APPEND);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
     }

     public static List<String> readFile() {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}