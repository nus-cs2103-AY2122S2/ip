package duke;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * this class contains the task list
 * it will handle all task commands
 * @author Kaiyi
 */
public class TaskList {

    private ArrayList<Task> listOfInputs = new ArrayList<>();
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
    public ArrayList<Task> getListOfInputs() {
        return this.listOfInputs;
    }

    /**
     * print out the lists of tasks
     */
    public String list() {
        String response = "";

        if (listOfInputs.size() > 0) {
            for (int i = 0; i < listOfInputs.size(); i++) {
                response = response + (((i + 1) + ". " + listOfInputs.get(i).message())) + "\n";
            }
        } else {
            response = "You got no task";
        }
        return response;
    }


    /**
     * @param keywords
     * @return string that imdicates whether keyword is found
     */
    public String search(String keywords) { //list unable to differentiate backDes after resaving
        boolean hasWord = false;
        String response = "";

        for (int i = 0; i < listOfInputs.size(); i++) {
            if (listOfInputs.get(i).backDescription().indexOf(keywords) == 0) {
                response = response + listOfInputs.get(i).message() + "\n";
                hasWord = true;
            }
            if (i == listOfInputs.size() - 1 && !hasWord) {
                response = "No task with the word: " + "' " + keywords + " ' ";
            }
        }
        return response;
    }
    /**
     * @param word
     */
    public String find(String word) {
        if (word.equals(" ") || word.trim().length() < 1) {
            return "Invalid word, please give an alphabet instead.";
        } else {
            boolean hasWord = false;
            String response = "";
            for (int i = 0; i < listOfInputs.size(); i++) {
                if (listOfInputs.get(i).getDescription().contains(word)) {
                    response = response + listOfInputs.get(i).message() + "\n";
                    hasWord = true;
                }
                if (i == listOfInputs.size() - 1 && !hasWord) {
                    response = "No task with the word: " + word;
                }
            }
            return response;
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
        String markedFinishMessage = "Good work!! I have marked it done:\n" + response;
        return markedFinishMessage;
    }

    /**
     * unmark the list as undone once it is called
     * @param currNo
     * @throws IOException
     */
    public String unMark(int currNo) throws IOException {
        Task taskUndone = listOfInputs.get(currNo);
        storage.updateData(taskUndone.message(), filePath);
        String unMarkFinishMessage = "Alrightt! I have marked it undone:\n" + taskUndone.markedUndone();
        return unMarkFinishMessage;
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
        String message = "Okayy!! I've added this task:\n " + todo.message() + "\n You have "
                + listOfInputs.size() + " tasks in the list.";
        return message;

    }

    /**
     * to update on list and display the deadline message once it is called
     * @param str
     * @return deadline message
     * @throws IOException
     */
    public String deadLine(String str) throws IOException {
        String[] output = new String[2];
        String[] temp = str.split("deadline ", 2);
        String[] deadL = temp[1].split("/by ", 2);
        String description = deadL[0];
        String by = deadL[1];
        try {
            DukeException d = new DukeException();
            d.invalidDate(by);
        } catch (DukeException e) {
            System.err.println(e);
            return "Invalid date format, please rephrase as DD/MM/YYYY";
        }
        DeadLine deadLineTemp = new DeadLine(description, by);
        listOfInputs.add(deadLineTemp);
        storage.updateData(deadLineTemp.message(), filePath);
        String message = "Deadline for this task:\n " + deadLineTemp.message() + "\n You have "
                + listOfInputs.size() + " tasks in the list.";
        return message;

    }

    /**
     * to update on the list and display the event message once it is called
     * @param str
     * @return
     * @throws IOException
     */
    public String event(String str) throws IOException, DukeException {

        String[] event = str.split("/at ", 2);
        try {
            DukeException d = new DukeException();
            d.invalidDate(event[1]);
        } catch (DukeException e) {
            System.err.println(e);
            return "Invalid date format, please rephrase as DD/MM/YYYY";
        }
        DukeException d = new DukeException();
        Event eventTemp = new Event(event[0], event[1]);
        listOfInputs.add(eventTemp);
        storage.updateData(eventTemp.message(), filePath);
        String message = "I have added this task and the event time is:\n "
                + eventTemp.message() + "\n You have " + listOfInputs.size() + " tasks in the list.";
        return message;

    }


    /**
     * to delete the task from list and display a message once it is called
     * @param str
     * @return delete message
     */
    public String delete(String str) {
        try {
            int value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return "Please write the NUMBER of task to be deleted. Kindly refer to the list";
        }
        int currNo = Integer.parseInt(str) - 1;
        try {
            DukeException d = new DukeException();
            d.invalidDelete(currNo, listOfInputs);
        } catch (IndexOutOfBoundsException | DukeException e) {
            System.err.println(e);
            return "You only have " + listOfInputs.size() + " tasks. Please input a number again.";
        }
        listOfInputs.remove(currNo);
        Delete deleteTemp = new Delete(listOfInputs.get(currNo).getDescription());
        String message = deleteTemp.message() + "\nYou have " + listOfInputs.size() + " tasks in the list.";
        return message;
    }

    /**
     * to write previously saved data to ArrayList
     * @throws IOException
     */
    public String writeToArrFromPrevData () throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
        String line = bufReader.readLine();
        while (line != null) {
            if (!line.isEmpty()) {
                String[] str = line.split("] ");
                String description = str[1];
                String symbol = str[0].substring(0, 1);
                Boolean isTaskComplete = str[0].contains("X");
                Task t = recreateTask(symbol, description);

                if (isTaskComplete) {
                    t.setDone();
                }
                listOfInputs.add(t);
            }
            line = bufReader.readLine();
        }
        bufReader.close();
        return "";
    }

    private Task recreateTask (String symbol, String description) {
        if (symbol.equals("T")) {
            return new Todos(description);
        } else if (symbol.equals("E")) {
            return new Event(description);
        } else if (symbol.equals("D")) {
            return new DeadLine(description);
        } else {
            return new Task(description);
        }
    }

}
