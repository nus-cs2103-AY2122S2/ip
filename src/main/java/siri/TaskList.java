package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> list;
    private ArrayList<Deadline> deadlineList;
    private ArrayList<Event> eventList;

    public TaskList() {
        list = new ArrayList<Task>(100);
        deadlineList = new ArrayList<Deadline>();
        eventList = new ArrayList<Event>();
    }

    public TaskList(String loadData) {
        list = new ArrayList<Task>(100);
        deadlineList = new ArrayList<Deadline>();
        eventList = new ArrayList<Event>();
        String[] loadDataSplit = loadData.split("\n");

        for (int i = 0; i < loadDataSplit.length; i++) {
            String curr = loadDataSplit[i].trim();
            startUpAddTask(curr);
        }

        System.out.printf("Data had been loaded!! Current number of task: %d\n", this.list.size());
    }

    /**
     * Adds the passed task into the TaskList.
     *
     * @param task to be added.
     * @return string to be printed after addItem had been completed.
     */
    public String addItem(Task task) {
        list.add(task);
        if (task instanceof Deadline) {
            Deadline tmp = (Deadline) task;
            deadlineList.add(tmp);
        } else if (task instanceof Event) {
            Event tmp = (Event) task;
            eventList.add(tmp);
        }
        return "Got it! I've added this task:\n" + task.getTaskDetails()
                + "\nTotal tasks on the list: " + this.list.size() + "\n";
    }

    /**
     * Deletes item of the given index from TaskList.
     *
     * @param index integer to indicate the index of the item that is selected to be deleted.
     * @return string to be printed after deleteItem had completed.
     */
    public String deleteItem(int index) {
        Task removedTask = list.remove(index);
        if (removedTask instanceof Deadline) {
            Deadline tmp = (Deadline) removedTask;
            deadlineList.remove(tmp);
        } else if (removedTask instanceof Event) {
            Event tmp = (Event) removedTask;
            eventList.remove(tmp);
        }
        return "Successfully removed the following task:\n" + removedTask.getTaskDetails() + "\n"
                + "You have " + this.list.size() + " tasks remaining!!\n";
    }

    /**
     * Prints the TaskList out in order with status of each task.
     * 
     * @return string to be printed after print had been completed.
     */
    public String print() {
        if (list.size() == 0) {
            return "There is currently no item on the list!!\n";
        }

        String printString = "Task List:\n";
        for (int i = 0; i < list.size(); i++) {
            printString = printString + (i + 1) + ". " + list.get(i).getTaskDetails() + "\n";
        }
        return printString;
    }

    /**
     * Marks item of the given index of tasklist done.
     * 
     * @return string to be printed after markItem had been completed.
     */
    public String markItem(int index) throws SiriException {
        if (this.list.size() == 0) {
            throw new SiriException("There is currently no tasks!!");
        }
        
        if (index >= this.list.size() || index < 0) {
            throw new SiriException("Please ENTER a number within the number of tasks!!");
        }

        return list.get(index).markTaskDone();
    }

    /**
     * Marks item of the given index of tasklist undone.
     *
     * @param index integer to indicate the item index to be unmarked.
     * @return string to be printed after unmarkItem had been completed.
     */
    public String unmarkItem(int index) {
        if (this.list.size() == 0) {
            throw new SiriException("There is currently no tasks!!");
        } 

        if (index >= this.list.size() || index < 0) {
            throw new SiriException("Please ENTER a number within the number of tasks!!");
        }

        return list.get(index).markTaskUndone();
    }

    /**
     * Prints the events in the tasklist that falls on the date that is passed in as parameter.
     *
     * @param date the date that the is being referenced to.
     * @return string to be printed after printEventOn had been completed.
     */
    public String printEventOn(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
        if (eventList.size() == 0) {
            return "No event on " + date.format(dtf) + "!!\n";
        }

        ArrayList<Event> tmp = new ArrayList<Event>();

        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).dateCompare(date)) {
                tmp.add(eventList.get(i));
            }
        }

        if (tmp.size() == 0) {
            return "No event on " + date.format(dtf) + "!!\n";
        } 

        String printString = tmp.size() + " events on " + date.format(dtf) + ":\n";
        for (int i = 0; i < tmp.size(); i++) {
            printString = printString + (i + 1) + ". " + tmp.get(i).getTaskDetails() + "\n";
        }
        return printString;
    }

    /**
     * Prints the deadlines in the tasklist that falls on the date that is passed in as parameter.
     *
     * @param date the date that the is being referenced to.
     * @return string to be printed after printDeadlineOn had been completed.
     */
    public String printDeadlineOn(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (deadlineList.size() == 0) {
            return "No deadline on " + date.format(dtf) + "!!\n";
        }

        ArrayList<Deadline> tmp = new ArrayList<Deadline>();

        for (int i = 0; i < deadlineList.size(); i++) {
            if (deadlineList.get(i).dateCompare(date)) {
                tmp.add(deadlineList.get(i));
            }
        }

        if (tmp.size() == 0) {
            return "No deadline on " + date.format(dtf) + "!!\n";
        }
            
        String printString = tmp.size() + " deadline item(s) on " + date.format(dtf) + ":\n";
        for (int i = 0; i < tmp.size(); i++) {
            printString = printString + (i + 1) + ". " + tmp.get(i).getTaskDetails() + "\n";
        }
        return printString;
    }

    /**
     * Prints items which contains the keyword passed.
     *
     * @param keyword to search through the list of tasks.
     * @return string to be printed after find had been completed.
     */
    public String find(String keyword) {
        if (list.size() == 0) {
            return "There is currently no item on the list!!";
        }

        ArrayList<Task> tmp = new ArrayList<Task>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).item.contains(keyword)) {
                tmp.add(list.get(i));
            }
        }

        if (tmp.size() == 0) {
            return "No item on the list contains \"" +  keyword + "\"!!\n";
        }

        String printString = "Here are the matching tasks in you list:\n";
        for (int i = 0; i < tmp.size(); i++) {
            printString = printString + (i + 1) + ". " + tmp.get(i).getTaskDetails() + "\n";
        }

        return printString;
    }

    private Deadline createLoadedDeadline(String[] taskDetails) {
        String[] dlSubSplit = taskDetails[2].split(" /by ", 2);
        String[] dlSubSplit2 = dlSubSplit[1].split(" ", 2);
        if (dlSubSplit2.length == 1 || dlSubSplit2[1].trim().length() == 0) {
            return new Deadline(dlSubSplit[0].trim(), Boolean.valueOf(taskDetails[1].trim()),
                    TaskList.stringToDate(dlSubSplit[1].trim()));
        } 
        return new Deadline(dlSubSplit[0].trim(), Boolean.valueOf(taskDetails[1].trim()),
                TaskList.stringToDate(dlSubSplit2[0]), TaskList.stringToTime(dlSubSplit2[1]));

    }

    private void startUpAddTask(String input) {
        String[] inputSplit = input.split(" ", 3);
        try {
            switch (inputSplit[0]) {
            case "T":
                ToDos todo = new ToDos(inputSplit[2].trim(), Boolean.valueOf(inputSplit[1].trim()));
                list.add(todo);
                break;
            case "D":
                Deadline dl = createLoadedDeadline(inputSplit);
                list.add(dl);
                deadlineList.add(dl);
                break;
            case "E":
                String[] eSubSplit = inputSplit[2].split(" /at ", 2);
                String[] eSubSplit2 = eSubSplit[1].split(" ", 2);
                Event e = new Event(eSubSplit[0].trim(), Boolean.valueOf(inputSplit[1].trim()),
                        TaskList.stringToDate(eSubSplit2[0].trim()), TaskList.stringToTime(eSubSplit2[1].trim()));
                list.add(e);
                eventList.add(e);
                break;
            default:
                throw new SiriException("Error in data format!!");
            }
        } catch (DateTimeParseException dtpe) {
            throw new SiriException("Error in data date/time format!!");
        }

    }

    /**
     * Returns the number of items in the tasklist.
     *
     * @return the number of items in the tasklist.
     */
    public int size() {
        return this.list.size();
    }

    public String saveData() {
        StringBuilder returned = new StringBuilder();

        list.forEach((item) -> returned.append(item.saveData() + "\n"));

        return returned.toString();
    }

    private static LocalDate stringToDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld = LocalDate.parse(dateString, dtf);

        return ld;
    }

    private static LocalTime stringToTime(String timeString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(timeString, dtf);

        return lt;
    }
}
