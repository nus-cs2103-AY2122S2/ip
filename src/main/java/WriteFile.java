import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

//class to write file to hard disk
public class WriteFile {
        public static void writeToFile(String s) {
            try {
                File f = new File("data");
                if (!f.exists()) {
                    f.mkdir();
                    File file = new File("data/duke.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                }        
                
                FileWriter fw = new FileWriter("data/duke.txt");
                fw.write(s);
                fw.close();
            } catch (IOException e) {
                System.out.println("Sorry, I was not able to save the changes made to the hard disk... Please try again.");
            }
    }
}