package duke;

import java.util.ArrayList;

/**
 * Represents DukeLCH's history that can be manipulated using a variety of methods.
 */
public class DukeHistory {
    private final ArrayList<Task> records = new ArrayList<>(100); // ArrayList of size 100 by default

    public DukeHistory() { //Empty Constructor
    }

    /**
     * A method that, when called, attempts to initialize a ToDos task using the inputted description and adds it to the
     * ArrayList records.
     * @param description A String description interpreted by Commands.todo().
     * @return Todo_task.
     */
    public ToDos addToDo(String description) {
        ToDos tempToDo = new ToDos(description);
        records.add(tempToDo);
        return tempToDo;
    }

    /**
     * A method that is called only when Duke attempts to load data from a pre-existing duke.txt file in the user's
     * hard drive.
     * It initializes a ToDos task using the mark and description found and adds it to
     * the current instance of ArrayList records.
     * @param mark An integer indicating whether the task is done or not.
     * @param description A String representing the description of the task.
     */
    public void addToDo(int mark, String description) {
        ToDos tempToDo = new ToDos(mark, description);
        records.add(tempToDo);
    }

    /**
     * A method that, when called, attempts to initialize a Deadline task using
     * the inputted description, date and time and adds it to the ArrayList records.
     * @param description A String description interpreted by Commands.deadline().
     * @param date A String date interpreted by Commands.convertToDukeDate().
     * @param time A String time interpreted by Commands.convertToDukeTime().
     * @return Deadline_task response.
     */
    public Deadlines addDeadline(String description, String date, String time) {
        Deadlines tempDeadline = new Deadlines(description, date, time);
        records.add(tempDeadline);
        return tempDeadline;
    }

    /**
     * A method that is called only when Duke attempts to load data from a pre-existing duke.txt file in the user's
     * hard drive.
     * It initializes a Deadline task using the mark, description, date and time found and
     * adds it to the current instance of ArrayList records.
     * @param mark An integer indicating whether the task is done or not
     * @param description A String representing the description of the task.
     * @param date A String representing the date of the task.
     * @param time A String representing the time of the task.
     */
    public void addDeadline(int mark, String description, String date, String time) {
        Deadlines tempDeadline = new Deadlines(mark, description, date, time);
        records.add(tempDeadline);
    }

    /**
     * A method that, when called, attempts to initialize an Event task using
     * the inputted description, date and time and adds it to the ArrayList records.
     * @param description A String description interpreted by Commands.event().
     * @param date A String date interpreted by Commands.convertToDukeDate().
     * @param time A String time interpreted by Commands.convertToDukeTime().
     * @return Event_task.
     */
    public Event addEvent(String description, String date, String time) {
        Event tempEvent = new Event(description, date, time);
        records.add(tempEvent);
        return tempEvent;
    }

    /**
     * A method that is called only when Duke attempts to load data from a pre-existing duke.txt file in the user's
     * hard drive.
     * It initializes an Event task using the mark, description, date and time found
     * and adds it to the current instance of ArrayList records.
     * @param mark An integer indicating whether the task is done or not.
     * @param description A String representing the description of the task.
     * @param date A String representing the date of the task.
     * @param time A String representing the time of the task.
     */
    public void addEvent(int mark, String description, String date, String time) {
        Event tempEvent = new Event(mark, description, date, time);
        records.add(tempEvent);
    }

    /**
     * A method that, when called, builds a String containing all the tasks stored
     * in the current instance of ArrayList records.
     * @return A String containing all the tasks stored in the current instance of ArrayList records.
     */
    public String printAll() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task nextTask : records) {
            if (nextTask instanceof ToDos) {
                ToDos temp = (ToDos) nextTask;
                result.append(count).append(". ").append(temp.getToDo());
            } else if (nextTask instanceof Deadlines) {
                Deadlines temp = (Deadlines) nextTask;
                result.append(count).append(". ").append(temp.getDeadline());
            } else if (nextTask instanceof Event) {
                Event temp = (Event) nextTask;
                result.append(count).append(". ").append(temp.getEvent());
            } else {
                System.out.println("Error occurred while processing " + nextTask.getTask());
            }
            count++;
        }
        if (result.length() == 0) {
            return "There has been no recorded user input!\n";
        } else {
            return result.toString();
        }
    }

    /**
     * Returns the task corresponding to the inputted entry index.
     * @param index An integer indicating the desired entry to return.
     * @return A Task stored in the current instance of ArrayList records.
     */
    public Task getTask(int index) {
        return records.get(index);
    }

    /**
     * A method that, when called, deletes the corresponding entry index in ArrayList records.
     * @param index An integer indicating the desired entry to delete.
     * @return Delete response.
     */
    public Task deleteTask(int index) {
        Task temp = records.remove(index);
        return temp;
    }

    /**
     * A method called when an update request is made for Duke to update the local duke.txt file with the
     * latest records.
     * It will construct a String containing all the tasks in records, appropriately formatted by the getFormattedText()
     * methods of their respective class.
     * @return A String containing all the properly formatted tasks found in ArrayList records.
     */
    String formatRecords() {
        StringBuilder s = new StringBuilder();
        for (Task temp : records) {
            if (temp instanceof ToDos) {
                ToDos tempToDos = (ToDos) temp;
                s.append(tempToDos.getFormattedText()).append("\n");
            } else if (temp instanceof Deadlines) {
                Deadlines tempDeadlines = (Deadlines) temp;
                s.append(tempDeadlines.getFormattedText()).append("\n");
            } else if (temp instanceof Event) {
                Event tempEvent = (Event) temp;
                s.append(tempEvent.getFormattedText()).append("\n");
            } else {
                System.out.println("Error occurred while deleting " + temp.getTask());
            }
        }
        return s.toString();
    }

    /**
     * Returns the size of the current instance of ArrayList records.
     * @return An integer representing the size of ArrayList records.
     */
    public int getSize() {
        return records.size();
    }

    /**
     * A method that, when called, looks through all the tasks in the current instance of ArrayList records
     * and find all the tasks whose description contains the inputted phrase.
     * It then returns a constructed String containing a list of all the identified tasks.
     * @param phrase A String representing the inputted phrase.
     * @return A String that contains a list of all tasks with the inputted phrase in their description.
     * @throws DukeException If no tasks is found matching the inputted phrase.
     */
    public String findPhrase(String phrase) throws DukeException {
        int count = 1;
        StringBuilder s = new StringBuilder();
        for (Task nextTask : records) {
            if (nextTask instanceof ToDos) {
                ToDos temp = (ToDos) nextTask;
                if (temp.getDescription().contains(phrase)) {
                    s.append(count).append(".").append(temp.getToDo());
                    count++;
                }
            } else if (nextTask instanceof Deadlines) {
                Deadlines temp = (Deadlines) nextTask;
                if (temp.getDescription().contains(phrase)) {
                    s.append(count).append(".").append(temp.getDeadline());
                    count++;
                }
            } else if (nextTask instanceof Event) {
                Event temp = (Event) nextTask;
                if (temp.getDescription().contains(phrase)) {
                    s.append(count).append(".").append(temp.getEvent());
                    count++;
                }
            }
        }
        if (count == 1) {
            throw new DukeException("could not find anything matching the phrase");
        }
        return s.toString();
    }
}
