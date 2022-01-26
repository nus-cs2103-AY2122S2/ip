package duke;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> list_of_inputs = new ArrayList<>();
    String directory;
    String filePath;

    public TaskList (String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;

    }
    Storage storage = new Storage(directory,filePath);

    public void list() {
        System.out.println("Here are your task(s):");
        for (int i = 0; i < list_of_inputs.size(); i++) {
            System.out.println(((i + 1) + ". " + list_of_inputs.get(i).message()));
        }
       // storage.readData(filePath);
    }
    public void find(String word) {
        boolean hasWord = false;
        for (int i = 0; i < list_of_inputs.size(); i++) {
            if (list_of_inputs.get(i).description.contains(word)) {
                System.out.println(list_of_inputs.get(i).message());
                hasWord = true;
            }
            if (i == list_of_inputs.size() - 1 && !hasWord) {
                System.err.println("No task with the word: " + word);
                break;
            }
        }
    }

    public void mark( int curr_no) throws IOException {
        Task task_done = list_of_inputs.get(curr_no);
        System.out.println("Good work!! I have marked it done:\n" + task_done.markedDone());
        storage.updateData(task_done.message(),filePath);
    }

    public void unMark(int curr_no) throws IOException {
        Task task_undone = list_of_inputs.get(curr_no);
        System.out.println("Alrightt! I have marked it undone:\n" + task_undone.markedUndone());
        storage.updateData(task_undone.message(),filePath);
    }

    public String toDo(String str) throws IOException {
        Todos todo = new Todos(str);
        list_of_inputs.add(todo);
        storage.updateData(todo.message() , filePath);
        return "Okayy!! I've added this task:\n " + todo.message() + "\n You have " + list_of_inputs.size() + " tasks in the list.";

    }

    public String deadLine(String str) throws IOException {
        String[] deadL = str.split("/by ", 2);
        Deadline deadLineTemp = new Deadline(deadL[0], deadL[1]);
        list_of_inputs.add(deadLineTemp);
        storage.updateData(deadLineTemp.message(),filePath);
        return "Deadline for this task:\n " + deadLineTemp.message() + "\n You have " + list_of_inputs.size() + " tasks in the list.";

    }

    public String event(String str) throws IOException {
        String[] event = str.split("/at ", 2);
        Event eventTemp = new Event(event[0], event[1]);
        list_of_inputs.add(eventTemp);
        storage.updateData(eventTemp.message(),filePath);
        return "I have added this task and the event time is:\n " + eventTemp.message() + "\n You have " + list_of_inputs.size() + " tasks in the list.";

    }

    public void delete(String str) {
        int curr_no = Integer.parseInt(str) - 1;
        list_of_inputs.remove(curr_no);
        Delete deleteTemp = new Delete(list_of_inputs.get(curr_no).description);
        System.out.println(deleteTemp.message() + "\nYou have " + list_of_inputs.size() + " tasks in the list.");
    }

    public void writeToArrFromPrevData () throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
        String line = bufReader.readLine();
        while (line != null) {
            Task t = new Task(line);
            list_of_inputs.add(t);
            line = bufReader.readLine();
        }
        bufReader.close();
    }

    public void addToList(Task t) {
        list_of_inputs.add(t);
    }




}
