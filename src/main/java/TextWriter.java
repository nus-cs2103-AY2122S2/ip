import java.io.FileWriter; // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class TextWriter {
    String contents;
    String filename;

    public TextWriter(String args) {
        this.contents = args;
        this.filename = "duke.txt";

        try {
            FileWriter myWriter = new FileWriter(this.filename, false);
            myWriter.write(this.contents);
            myWriter.close();
            System.out.println("Your tasks have been saved in " + this.filename + ".");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
