package duke;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class contains the task list
 * it will handle all task commands
 * @author Kaiyi
 */
public class TaskList {

    ArrayList<Task> listOfInputs = new ArrayList<>();
    String directory;
    String filePath;

    public TaskList (String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;

    }
    Storage storage = new Storage(directory,filePath);

    /**
     * print out the lists of tasks
     */
    public void list() {
        System.out.println("Here are your task(s):");
        for (int i = 0; i < listOfInputs.size(); i++) {
            System.out.println(((i + 1) + ". " + listOfInputs.get(i).message()));
        }
    }

    /**
     * mark the list as done once it is called
     * @param curr_no
     * @throws IOException
     */
    public void mark( int curr_no) throws IOException {
        Task task_done = listOfInputs.get(curr_no);
        System.out.println("Good work!! I have marked it done:\n" + task_done.markedDone());
        storage.updateData(task_done.message(),filePath);
    }

    /**
     * unmark the list as undone once it is called
     * @param curr_no
     * @throws IOException
     */
    public void unMark(int curr_no) throws IOException {
        Task task_undone = listOfInputs.get(curr_no);
        System.out.println("Alrightt! I have marked it undone:\n" + task_undone.markedUndone());
        storage.updateData(task_undone.message(),filePath);
    }

    /**
     * to update on list and display the todo message once it is called
     * @param str
     * @return
     * @throws IOException
     */
    public String toDo(String str) throws IOException {
        Todos todo = new Todos(str);
        listOfInputs.add(todo);
        storage.updateData(todo.message() , filePath);
        return "Okayy!! I've added this task:\n " + todo.message() + "\n You have " + listOfInputs.size() + " tasks in the list.";

    }

    /**
     * to update on list and display the deadline message once it is called
     * @param str
     * @return
     * @throws IOException
     */
    public String deadLine(String str) throws IOException {
        String[] deadL = str.split("/by ", 2);
        DeadLine deadLineTemp = new DeadLine(deadL[0], deadL[1]);
        listOfInputs.add(deadLineTemp);
        storage.updateData(deadLineTemp.message(),filePath);
        return "Deadline for this task:\n " + deadLineTemp.message() + "\n You have " + listOfInputs.size() + " tasks in the list.";

    }

    /**
     * to update on the list and display the event message once it is called
     * @param str
     * @return
     * @throws IOException
     */
    public String event(String str) throws IOException {
        String[] event = str.split("/at ", 2);
        Event eventTemp = new Event(event[0], event[1]);
        listOfInputs.add(eventTemp);
        storage.updateData(eventTemp.message(),filePath);
        return "I have added this task and the event time is:\n " + eventTemp.message() + "\n You have " + listOfInputs.size() + " tasks in the list.";

    }

    /**
     * to delete the task from list and display a message once it is called
     * @param str
     */
    public void delete(String str) {
        int curr_no = Integer.parseInt(str) - 1;
        listOfInputs.remove(curr_no);
        Delete deleteTemp = new Delete(listOfInputs.get(curr_no).description);
        System.out.println(deleteTemp.message() + "\nYou have " + listOfInputs.size() + " tasks in the list.");
    }

    /**
     * to write previously saved data to ArrayList
     * @throws IOException
     */
    public void writeToArrFromPrevData () throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
        String line = bufReader.readLine();
        while (line != null) {
            Task t = new Task(line);
            listOfInputs.add(t);
            line = bufReader.readLine();
        }
        bufReader.close();
    }






}
