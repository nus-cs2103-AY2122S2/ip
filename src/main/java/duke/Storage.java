package duke;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Storage {

    private final File file;
    public int items;
    FileWriter fw;
    PrintWriter wf;


    /**
     * Represents a storage for duke.
     */
    public Storage(String filePath) throws IOException {
        this.fw = new  FileWriter(filePath,true);
        this.wf = new  PrintWriter(fw);
        filePath = getPath();
        items = 0;
        file = new File(filePath);
        if(!file.exists())
            file.createNewFile(); // if file already exists will do nothing
    }

    /**
     * Gets path.
     *
     * @return the path.
     */
    public String getPath() {
        String home = System.getProperty("user.dir");
        String path = home + "/data.txt";
        boolean directoryExists = new java.io.File(path).exists();
        return path;
    }
    /**
     * Writes the string to file and flushes the buffer.
     *
     * @param s  String to be written to the file
     */
    public void writeFile(String s) {
         this.wf.println(s);
         wf.flush();
    }
    public void flush() {
        wf.flush();
    }
    /**
     * Load tasks that is stored in the file
     * and returns it in ArrayList
     *
     * @return ArrayList of Task
     */
    public ArrayList<Task> load() throws IOException {
        BufferedReader readFile
                = new BufferedReader(new FileReader(this.file));
        ArrayList<Task> tasks = new ArrayList<>();
        StringBuilder sb;
        String line = "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        while((line = readFile.readLine()) != null) {
            items++;
            String[] words = line.split(" ");
            String type = words[0];

            // check whether the task is marked
            boolean isDone = !words[1].equals("0");

            if(words[0].equals("T")) {
                sb = new StringBuilder();
                for(int i = 2; i < words.length; i++) {
                    sb.append(words[i]).append(" "); // get the description
                }
                tasks.add(new ToDo(sb.toString(), isDone));
            }
            else if(words[0].equals("D")) {
                sb = new StringBuilder();
                int count = 2;
                for(int i = 2; i < words.length; i++) {
                    count++;
                    if(words[i].equals("|"))
                        break;
                    sb.append(words[i]).append(" "); // get the description
                }
                String description = sb.toString();
                sb = new StringBuilder();

                sb.append(words[count]).append(" ");
                sb.append(words[count+1]);
                tasks.add(new Deadline(description, LocalDateTime.parse(sb.toString(), formatter), isDone));
            }
            else {
                sb = new StringBuilder();
                int count = 2;
                for(int i = 2; i < words.length; i++) {
                    count++;
                    if(words[i].equals("|"))
                        break;
                    sb.append(words[i]).append(" ");
                }
                String description = sb.toString();
                sb = new StringBuilder();

                sb.append(words[count]).append(" ");
                sb.append(words[count+1]);
                tasks.add(new Event(description, LocalDateTime.parse(sb.toString(), formatter), isDone));
            }
        }
        return tasks;
    }

    public int getNumberOfTasks() {
        return items;
    }
}
