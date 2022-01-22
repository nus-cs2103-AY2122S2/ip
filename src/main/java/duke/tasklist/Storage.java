package duke.tasklist;
import java.io.*;
import java.util.*;
import duke.tasks.*;
import duke.Pikachu;

public class Storage {
    private String filePath;
    public Pikachu pikachu;

    //Constructor
    public Storage(String filePath, Pikachu pikachu) {
        this.filePath = filePath;
        this.pikachu = pikachu;
    }

    public void readTaskList() throws IOException {
        //Create file
        File tasklist = new File(filePath);
        tasklist.createNewFile(); //if file already exists, will do nothing

        FileReader fw = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fw);

        //Reading of lines
        String currLine = null;
        while ((currLine = br.readLine()) != null) {
            //System.out.println("For debugging. Am I running too many times?");
            String[] split = currLine.split(",");
            if (split[0].equals("T")) {
                ToDo t = new ToDo(split[2]);
                this.pikachu.inputList.add(t);
                if (split[1].equals("1")) t.mark(); 
            } else if (split[0].equals("D")) {
                Deadline d = new Deadline(split[2], split[3]);
                this.pikachu.inputList.add(d);
                if (split[1].equals("1")) d.mark(); 
            } else if (split[0].equals("E")) {
                Event e = new Event(split[2], split[3]);
                this.pikachu.inputList.add(e);
                if (split[1].equals("1")) e.mark(); 
            }
        }

        fw.close();
        br.close();
    }

    public void writeTaskList() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);

        //Writing of tasks into tasklist
        for (Task t : this.pikachu.inputList) {
            fw.write(t.info + "\n");
        }

        fw.close();
    }
}
