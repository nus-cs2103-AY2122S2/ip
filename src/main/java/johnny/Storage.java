package johnny;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads tasks from the text file to
     * an ArrayList
     *
     * @return ArrayList of tasks.
     * @throws IOException
     */
    public ArrayList<Task> readTasks() throws IOException{
        ArrayList<Task> taskList = new ArrayList<>();

            File file = new File(
                    filepath);
            file.createNewFile();

            BufferedReader br
                    = new BufferedReader(new FileReader(file));

            String st;

            while ((st = br.readLine()) != null) {
                String[] words = st.split(",");
                if(words[0].equals("TODO")) {
                    taskList.add(new Todo(words[2], words[1].equals("true")));
                } else if (words[0].equals("DEADLINE")) {
                    taskList.add(new Deadline(words[2], LocalDate.parse(words[3]), words[1].equals("true")));
                } else if (words[0].equals("EVENT")) {
                    taskList.add(new Event(words[2], LocalDate.parse(words[3]), words[1].equals("true")));
                }
            }

        return taskList;
    }

    /**
     * Writes task from ArrayList to text file
     *
     * @param t  ArrayList of tasks.
     */
    public void writeTasks(ArrayList<Task> t) {
        try {
            File file = new File("tasklist.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);

            for(int i = 0; i < t.size(); i++) {
                bw.write(t.get(i).getTaskString() + "\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
