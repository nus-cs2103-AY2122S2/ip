import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class writes a txt file to a predetermined path
 * Referenced from topic W3.3c
 */
public class WriteFile {

    public static final String ADDRESS = "data/duke.txt";

    public WriteFile(ArrayList<Task> store) throws IOException {
        FileWriter fw = new FileWriter(ADDRESS);
        for (Task curr : store) {
            String str = null;
            String suffix = null;
            switch(curr.whatType()) {
                case "T":
                    str = "T | ";
                    suffix = curr.description;
                    break;
                case "D":
                    str = "D | ";
                    Deadline tempD = (Deadline) curr;
                    suffix = tempD.description + " | " + tempD.dateInfo;
                    break;
                case "E":
                    str = "E | ";
                    Event tempE = (Event) curr;
                    suffix = tempE.description + " | " + tempE.dateInfo;
                    break;

            }
            switch(curr.getStatusIcon()) {
                case "X":
                    str = str + "1 | ";
                    break;
                case " ":
                    str = str + "0 | ";
                    break;
            }
            fw.write(str + suffix + System.lineSeparator());
        }
        fw.close();
    }
//
//    public static void main(String[] args) {
//        String file2 = "../../../data/duke.txt";
//        try {
//            writeToFile(file2, "first line" + System.lineSeparator() + "second line");
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }

}

