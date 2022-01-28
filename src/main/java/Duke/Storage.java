package Duke;

import Task.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents the storage space, together with
 * functions to read and write data from the file path.
 */
public class Storage {
    /**
     * The filePath where data is stored.
     */
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath location where the file is to be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the list of tasks stored if the file already
     * exits or else returns an empty list.
     *
     * @return list containing saved tasks or empty list.
     * @throws Exception If an unexpected error occurs.
     */
    public ArrayList<Task> read() throws Exception {
        File file = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();

        if (file.exists()) {
            BufferedReader r = new BufferedReader(new FileReader(file));
            String t = r.readLine();
            while (!(t == null)) {
                char i = t.charAt(0);
                if (i == 'T') {
                    String[] in = t.split(":");
                    list.add(new Todo(in[2]));
                } else if (i == 'D') {
                    String[] in = t.split(":");
                    list.add(new Deadline(in[2], in[3]));
                } else if (i == 'E') {
                    String[] in = t.split(":");
                    list.add(new Event(in[2], in[3]));
                } else {
                    // do nothing
                }
                t = r.readLine();
            }
        }
        return list;
    }

    /**
     * Writes data entered by the user as a file
     * as per the file path.
     *
     * @param list Updated list of Tasks.
     * @throws Exception If unexpected error occurs.
     */
    public void write(ArrayList<Task> list) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        for(int n = 0; n < list.size(); n++) {
            fw.write(list.get(n).toSave() + "\n");
        }
        fw.close();
    }
}
