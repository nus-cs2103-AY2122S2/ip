import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String FILE_PATH;
    private final  File FILE;

    public Storage(String filePath){
        this.FILE_PATH = filePath;
        this.FILE = new File(FILE_PATH);

        try {
            if (!FILE.exists()) {
                FILE.getParentFile().mkdirs();
                FILE.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when creating initialising the file: "
                + e.getMessage());
        }
    }

    public List<Task> load(){
        List<Task> result = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (List<Task>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Something went wrong while loading file: " + e.getMessage());
        }
        return result;
    }

    public boolean save(List<Task> taskList){
        boolean isSuccessful = false;
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
            isSuccessful = true;
        } catch (IOException e){
            System.out.println("Something went wrong while saving file: " + e.getMessage());
        }
        return isSuccessful;
    }
}
