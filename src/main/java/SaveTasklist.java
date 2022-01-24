import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveTasklist {
    public static int saveToFile(String path, List<Task> taskList) throws IOException{
        FileWriter out = null;
        //Check if folder/file is present, otherwise create file
        File file = new File(path);
        try{
            String split[] = path.split("/");
            String directoryPath = path.substring(0, path.length() - split[split.length-1].length());
            File directory = new File(directoryPath);
            if (!directory.exists())
                directory.mkdirs();
            if (!file.exists())
                file.createNewFile();
            out = new FileWriter(file);
            for (int i = 0; i < taskList.size(); i++){
                Task currentTask = taskList.get(i);
                String outputLine = currentTask.toOutputLine() + "\n";
                out.write(outputLine);
            }
        } catch(IOException exception){
            return -1;
        } finally {
            if (out != null)
                out.close();
        }
        return 1;
    }
}
