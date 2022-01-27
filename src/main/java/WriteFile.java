import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private String pathName;

    public WriteFile() {
        this.pathName = "duke.txt";
    }

    public void clearFile() {
        try {
            FileWriter fileWriter = new FileWriter(this.pathName, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String text){
        try {
            FileWriter fileWriter = new FileWriter(this.pathName, true);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
