import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    private ArrayList<Task> list;

    Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<Task>();
    }

    ArrayList<Task> load() throws DukeException {
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String input = bufferedReader.readLine();
            while (input != null) {
                String[] task = input.split(" ### ");
                String type = task[0];
                String status = task[1];
                String thing = task[2];
                if (type.equals("T")) {
                    Task newTask = new Todo(thing);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.list.add(newTask);
                } else if (type.equals("D")) {
                    Task newTask = new Deadline(thing, task[3]);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.list.add(newTask);
                } else if (type.equals("E")) {
                    Task newTask = new Event(thing, task[3]);
                    if (status.equals("1")) {
                        newTask.mark();
                    }
                    this.list.add(newTask);
                }
                input = bufferedReader.readLine();
            }
            return this.list;
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new DukeException("Loading error");
        }
    }

    void save(ArrayList<Task> saveList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter br = new BufferedWriter(writer);
            for (int i = 0; i < saveList.size(); i++) {
                br.write(saveList.get(i).saveFormat());
                br.newLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
