package narcibot;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for handling a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(BufferedReader bufferedReader) throws IOException {
        list = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] tokens = line.split("\\|", 4);
            boolean done = tokens[1].equals("1");
            switch(tokens[0]) {
                case "T":
                    list.add(new ToDo(tokens[2], done));
                    break;
                case "D":
                    list.add(new Deadline(tokens[2], tokens[3], done));
                    break;
                case "E":
                    list.add(new Event(tokens[2], tokens[3], done));
                    break;
            }
            line = bufferedReader.readLine();
        }
    }

    /**
     * Stores the tasks into the file given by fileWriter.
     * @param fileWriter
     * @throws IOException
     */
    public void store(FileWriter fileWriter) throws IOException {
        for (Task task: list) {
            fileWriter.write(task.save() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Lists the tasks.
     */
    public void list() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).getStatus());
        }
    }

    /**
     * Mark task via index as done.
     * @param indexString
     * @throws IncorrectFormatException
     */
    public void mark(String indexString) throws IncorrectFormatException {
        list.get(checkValid(indexString) - 1).markDone();
    }

    /**
     * Mark task via index as not done.
     * @param indexString
     * @throws IncorrectFormatException
     */
    public void unmark(String indexString) throws IncorrectFormatException {
        list.get(checkValid(indexString) - 1).markNotDone();
    }

    /**
     * Delete task via index
     * @param indexString
     * @throws IncorrectFormatException
     */
    public void delete(String indexString) throws IncorrectFormatException {
        list.remove(checkValid(indexString) - 1);
    }

    private int checkValid(String indexString) throws IncorrectFormatException {
        int index = Integer.parseInt(indexString);
        if (index < 1 || index > list.size()) {
            throw new IncorrectFormatException("Please enter a valid task number.");
        }
        return index;
    }

    /**
     * Initialize a new todo task.
     * @param task name of the todo
     * @return integer indicating the total number of tasks
     */
    public int todo(String task) {
        list.add(new ToDo(task));
        return list.size();
    }

    /**
     * Initialize a new deadline task
     * @param task name of the deadline
     * @param time deadline of the task
     * @return integer indicating the total number of tasks
     */
    public int deadline(String task, String time) {
        list.add(new Deadline(task, time));
        return list.size();
    }

    /**
     * Initialize a new event task
     * @param task name of the event
     * @param time time of the event
     * @return integer indicating the total number of tasks
     */
    public int event(String task, String time) {
        list.add(new Event(task, time));
        return list.size();
    }

    public void find(String keyword) {
        int matches = 0;
        for (Task task : list) {
            if(task.inName(keyword)) {
                System.out.println((matches + 1) +"." + task.getStatus());
                matches++;
            }
        }
    }
}
