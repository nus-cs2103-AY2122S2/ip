import java.io.FileWriter;
import java.io.IOException;

class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public String load() {
        System.out.println("loaded!");
        return "";
    }
}
