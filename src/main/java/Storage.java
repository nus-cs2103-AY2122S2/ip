import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static int saveFile(String path, String fileName, ArrayList<Task> arr) {
        try {
            Files.createDirectories(Paths.get(path));
            String filePath = path + "/" + fileName;
            File myObj = new File(filePath);
            myObj.createNewFile();
            FileWriter writer = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();
            for(Task t : arr) {
                sb.append(t.toString() + ";");
            }
            sb.setLength((sb.length() - 1));
            writer.write(sb.toString());
            writer.close();
            return 0;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}