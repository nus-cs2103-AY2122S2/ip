package duke.tasklist;
import java.io.*;
import java.util.*;
import java.time.*;

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

    /**
     * Reads the tasklist stored in Tasklist.txt, or creates one if it does not exist.
     * Stores the read data into Pikachu's tasklist.
     * 
     * @throws IOException If the content in Tasklist.txt is in the wrong format.
     */
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
                LocalDateTime deadline = LocalDateTime.of(Integer.parseInt(split[3]), Integer.parseInt(split[4]), 
                        Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]));
                Deadline d = new Deadline(split[2], deadline);
                this.pikachu.inputList.add(d);
                if (split[1].equals("1")) d.mark(); 
            } else if (split[0].equals("E")) {
                LocalDateTime start = LocalDateTime.of(Integer.parseInt(split[3]), Integer.parseInt(split[4]), 
                        Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]));
                LocalDateTime end = LocalDateTime.of(Integer.parseInt(split[8]), Integer.parseInt(split[9]), 
                        Integer.parseInt(split[10]), Integer.parseInt(split[11]), Integer.parseInt(split[12]));
                Event e = new Event(split[2], start, end);
                this.pikachu.inputList.add(e);
                if (split[1].equals("1")) e.mark(); 
            }
        }

        fw.close();
        br.close();
    }

    /**
     * Writes the current tasklist into Tasklist.txt.
     * 
     * @throws IOException If there is a problem with accessing and/or writing to Tasklist.txt.
     */
    public void writeTaskList() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);

        //Writing of tasks into tasklist
        for (Task t : this.pikachu.inputList) {
            fw.write(t.info + "\n");
        }

        fw.close();
    }
}
