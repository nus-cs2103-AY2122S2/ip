import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileCreator {

    public static boolean checkDirectory(Path path) {
        return java.nio.file.Files.exists(path);
    }

    public static void writeToFile(String path, String content) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
        } catch(IOException err) {
            System.out.println("Path specified incorrectly.");
        }
    }

    public static void createFolder() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "desktop", "ip", "data");
        File file = new File(String.valueOf(path));
        file.mkdirs();
    }
}