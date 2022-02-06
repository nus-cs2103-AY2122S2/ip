package duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load()  throws IOException {
            ArrayList<Task> list = new ArrayList<>();
            File data = new File("prince.txt");
            if (!data.createNewFile()) {
                Scanner s = new Scanner(data);
                while (s.hasNext()) {
                    //read tasks and add to arraylist
                    String line = s.nextLine();
                    Task t = new Parser(line).parse();
                    if (line.substring(4,5).equals("X")) {
                        t.makeDone();
                    }
                    list.add(t);
                }
            }
            return list;
    }


}
