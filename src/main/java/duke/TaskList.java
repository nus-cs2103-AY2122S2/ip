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
    private String directory;
    private String filePath;

    private Storage storage = new Storage(directory, filePath);
    /**
     * @param directory
     * @param filePath
     */
    public TaskList (String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;

    }

    /**
     * print out the lists of tasks
     */
    public String list() {
        String response = "";

        if (listOfInputs.size() > 0) {
            for (int i = 0; i < listOfInputs.size(); i++) {
                response = response + "\n" + (((i + 1) + ". " + listOfInputs.get(i).message()));
            }
        } else {
            response = "You got no task";
        }
        return response;
    }

    /**
     * @param word
     */
    public void find(String word) {
        boolean hasWord = false;
        for (int i = 0; i < listOfInputs.size(); i++) {
            if (listOfInputs.get(i).getDescription().contains(word)) {
                System.out.println(listOfInputs.get(i).message());
                hasWord = true;
            }
            if (i == listOfInputs.size() - 1 && !hasWord) {
                System.err.println("No task with the word: " + word);
                break;
            }
        }
    }

    /**
     * @param currNo
     * @throws IOException
     */
    public String mark(int currNo) throws IOException {
        String response = "";
        Task taskDone = listOfInputs.get(currNo);
        response = response + taskDone.markDone(currNo);
        storage.updateData(taskDone.message(), filePath);
        return "Good work!! I have marked it done:\n" + response;
    }

    /**
     * unmark the list as undone once it is called
     * @param currNo
     * @throws IOException
     */
    public String unMark(int currNo) throws IOException {
        Task taskUndone = listOfInputs.get(currNo);
        //System.out.println("Alrightt! I have marked it undone:\n" + taskUndone.markedUndone());
        storage.updateData(taskUndone.message(), filePath);
        return "Alrightt! I have marked it undone:\n" + taskUndone.markedUndone();
    }

    /**
     * to update on list and display the todo message once it is called
     * @param str
     * @return todo message
     * @throws IOException e
     */
    public String toDo(String str) throws IOException {
        Todos todo = new Todos(str);
        listOfInputs.add(todo);
        storage.updateData(todo.message() , filePath);
        return ("Okayy!! I've added this task:\n " + todo.message() + "\n You have "
                + listOfInputs.size() + " tasks in the list.");

    }

    /**
     * to update on list and display the deadline message once it is called
     * @param str
     * @return deadline message
     * @throws IOException
     */
    public String deadLine(String str) throws IOException {
        String[] temp = str.split("deadline ", 2);
        String[] deadL = temp[1].split("/by ", 2);
        String description = deadL[0];
        String by = deadL[1];
        DeadLine deadLineTemp = new DeadLine(description, by);
        listOfInputs.add(deadLineTemp);
        storage.updateData(deadLineTemp.message(), filePath);
        return ("Deadline for this task:\n " + deadLineTemp.message() + "\n You have "
                + listOfInputs.size() + " tasks in the list.");

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
        storage.updateData(eventTemp.message(), filePath);
        return ("I have added this task and the event time is:\n "
                + eventTemp.message() + "\n You have " + listOfInputs.size() + " tasks in the list.");

    }

    /**
     * to delete the task from list and display a message once it is called
     * @param str
     * @return delete message
     */
    public String delete(String str) {
        int currNo = Integer.parseInt(str) - 1;
        listOfInputs.remove(currNo);
        Delete deleteTemp = new Delete(listOfInputs.get(currNo).getDescription());
        return deleteTemp.message() + "\nYou have " + listOfInputs.size() + " tasks in the list.";
    }

    /**
     * to write previously saved data to ArrayList
     * @throws IOException
     */
    public String writeToArrFromPrevData () throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
        String line = bufReader.readLine();
        while (line != null) {
            Task t = new Task(line);
            listOfInputs.add(t);
            line = bufReader.readLine();
        }
        bufReader.close();
        return "";
    }






}
