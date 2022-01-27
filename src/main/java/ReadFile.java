import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads the content of a txt file from a predetermined path
 * Referenced from topic W3.3c
 */
public class ReadFile {

    public static final String ADDRESS = "data/duke.txt";
    public static final String PARENTADDRESS = "data/";

    public ReadFile(ArrayList<Task> store) throws FileNotFoundException {
        if (Files.notExists(Path.of(PARENTADDRESS))) {
            throw new FileNotFoundException("Please initialise parent folder at [project_root]/data/" +
                    " and place the input file duke.txt inside");
        } else if (Files.notExists(Path.of(ADDRESS))) {
            throw new FileNotFoundException("Duke requires an input file called duke.txt inside of data folder.\n" +
                    "Please create and try running Duke again.");
        }
        File f = new File(ADDRESS);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] inputLine = s.nextLine().split("\\| ", 4);
            Task curr;
            switch(inputLine[0]) {
                case "T " :
                    curr = new Todo(inputLine[2]);
                    break;
                case "D " :
                    String strD = inputLine[2];
                    curr = new Deadline(strD.substring(0, strD.length() - 1), inputLine[3]);
                    break;
                case "E " :
                    String strE = inputLine[2];
                    curr = new Event(strE.substring(0, strE.length() - 1), inputLine[3]);
                    break;
                default:
                    curr = null;
            }
            switch (inputLine[1]) {
                case "1 " :
                    curr.setMark();
                case "0 " :
                    break;
            }
            store.add(curr);
        }
    }
}