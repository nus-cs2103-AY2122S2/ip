import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

class FileClass {

    public void createFile(String filePath) {
        File f = new File(filePath); //initialise the file
        if (!f.exists()) { //meaning f doesnt exist
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Path Directory is invalid!");
            }
        }
    } 
    //taken from W3.3c File Access
    public void writeFile(String filePath, String textToAdd) throws IOException { 
        FileWriter fw = new FileWriter(filePath, true); // initialise the filewriter
        fw.write(textToAdd + "\r\n");
        fw.close();
    }
}