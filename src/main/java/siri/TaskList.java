package siri;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



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
     */
    public void addItem(Task task) {
        list.add(task);
        if (task instanceof Deadline) {
            Deadline tmp = (Deadline) task;
            deadlineList.add(tmp);
        } else if (task instanceof Event) {
            Event tmp = (Event) task;
            eventList.add(tmp);
        }
        System.out.printf("Got it! I've added this task:\n%s\nTotal tasks on the list: %d\n",
                            task.getTaskDetails(), this.list.size());
    }

    /**
     * Deletes item of the given index from TaskList.
     * 
     * @param index integer to indicate the index of the item that is selected to be deleted.
     */
    public void deleteTask(int index) {
        Task removedTask = list.remove(index);
        if (removedTask instanceof Deadline) {
            Deadline tmp = (Deadline) removedTask;
            deadlineList.remove(tmp);
        } else if (removedTask instanceof Event) {
            Event tmp = (Event) removedTask;
            eventList.remove(tmp);
        }
        System.out.printf("Successfully removed the following task:\n%s\nYou have %d tasks remaining!!\n", removedTask.getTaskDetails(), this.list.size());
    }

    /**
     * Prints the TaskList out in order with status of each task.
     */
    public void print() {
        if (list.size() == 0) {
            System.out.println("There is currently no item on the list!!");
        } else {
            System.out.println("Task List:");
            list.forEach((item) -> System.out.println((list.indexOf(item)+1) + ". " + item.getTaskDetails()));
        }
    }

    /**
     * Marks item of the given index of tasklist done.
     */
    public void markItem(int index) {
        list.get(index).markTaskDone();
    }

    /**
     * Marks item of the given index of tasklist undone.
     * 
     * @param index integer to indicate the item index to be unmarked.
     */
    public void unmarkItem(int index) {
        list.get(index).markTaskUndone();
    }

    /**
     * Prints the events in the tasklist that falls on the date that is passed in as parameter.
     * 
     * @param date the date that the is being referenced to.
     */
    public void printEventOn(LocalDate date) {
        ArrayList<Event> tmp = new ArrayList<Event>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (eventList.size() != 0) {
            for (int i = 0; i < eventList.size(); i++) {
                if (eventList.get(i).dateCompare(date)) {
                    tmp.add(eventList.get(i));
                }
            }

            if (tmp.size() == 0) {
                System.out.printf("No event on %s!!\n", date.format(dtf));
            } else {
                System.out.printf("%d events on %s:\n", tmp.size(), date.format(dtf));
                tmp.forEach((item) -> System.out.println((tmp.indexOf(item) + 1) + ". " + item.getTaskDetails()));
            }
        } else {
                System.out.printf("No event on %s!!\n", date.format(dtf));
        }
    }

    /**
     * Prints the deadlines in the tasklist that falls on the date that is passed in as parameter.
     * 
     * @param date the date that the is being referenced to.
     */
    public void printDeadlineOn(LocalDate date) {
        ArrayList<Deadline> tmp = new ArrayList<Deadline>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (deadlineList.size() != 0) {
            for (int i = 0; i < deadlineList.size(); i++) {
                if (deadlineList.get(i).dateCompare(date)) {
                    tmp.add(deadlineList.get(i));
                }
            }

            if (tmp.size() == 0) {
                System.out.printf("No deadline on %s!!\n", date.format(dtf));
            } else {
                System.out.printf("%d deadline item(s) on %s:\n", tmp.size(), date.format(dtf));
                tmp.forEach((item) -> System.out.println((tmp.indexOf(item) + 1) + ". " + item.getTaskDetails()));
            }
        } else {
            System.out.printf("No deadline on %s!!\n", date.format(dtf));
        }
    }

    public void find(String keyword) {
        ArrayList<Task> tmp = new ArrayList<Task>();

        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).item.contains(keyword)) {
                    tmp.add(list.get(i));
                }
            }

            if (tmp.size() == 0) {
                System.out.printf("No item on the list contains \"%s\"!!\n", keyword);
            } else {
                System.out.println("Here are the matching tasks in you list:");
                tmp.forEach((item) -> System.out.println((tmp.indexOf(item) + 1) + ". " + item.getTaskDetails()));
            }
        } else {
            System.out.println("There are currently no item on the list!!");
        }
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
                    String[] dlSubSplit = inputSplit[2].split(" /by ", 2);
                    String[] dlSubSplit2 = dlSubSplit[1].split(" ", 2);
                    Deadline dl;
                    if (dlSubSplit2.length == 1 || dlSubSplit2[1].trim().length() == 0) {
                        dl = new Deadline(dlSubSplit[0].trim(), Boolean.valueOf(inputSplit[1].trim()), TaskList.stringToDate(dlSubSplit[1].trim()));
                    } else {
                        dl = new Deadline(dlSubSplit[0].trim(), Boolean.valueOf(inputSplit[1].trim()), TaskList.stringToDate(dlSubSplit2[0]), TaskList.stringToTime(dlSubSplit2[1]));
                    }
                    list.add(dl);
                    deadlineList.add(dl);
                    break;
                case "E":
                    String[] eSubSplit = inputSplit[2].split(" /at ", 2);
                    String[] eSubSplit2 = eSubSplit[1].split(" ", 2);
                    Event e = new Event(eSubSplit[0].trim(), Boolean.valueOf(inputSplit[1].trim()), TaskList.stringToDate(eSubSplit2[0].trim()), TaskList.stringToTime(eSubSplit2[1].trim()));
                    list.add(e);
                    eventList.add(e);
                    break;
            }
        } catch (DateTimeParseException dtpe) {
            throw new SiriException("Error LOADING data!!");
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