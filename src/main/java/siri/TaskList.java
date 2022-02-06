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
     */
    public String deleteTask(int index) {
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
     */
    public String print() {
        String printString;
        if (list.size() == 0) {
            printString  = "There is currently no item on the list!!\n";
        } else {
            printString = "Task List:\n";
            for (int i = 0; i < list.size(); i++) {
                printString = printString + (i + 1) + ". " + list.get(i).getTaskDetails() + "\n";
            }
        }
        return printString;
    }

    /**
     * Marks item of the given index of tasklist done.
     */
    public String markItem(int index) {
        return list.get(index).markTaskDone();
    }

    /**
     * Marks item of the given index of tasklist undone.
     *
     * @param index integer to indicate the item index to be unmarked.
     */
    public String unmarkItem(int index) {
        return list.get(index).markTaskUndone();
    }

    /**
     * Prints the events in the tasklist that falls on the date that is passed in as parameter.
     *
     * @param date the date that the is being referenced to.
     */
    public String printEventOn(LocalDate date) {
        ArrayList<Event> tmp = new ArrayList<Event>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
        String printString;

        if (eventList.size() != 0) {
            for (int i = 0; i < eventList.size(); i++) {
                if (eventList.get(i).dateCompare(date)) {
                    tmp.add(eventList.get(i));
                }
            }

            if (tmp.size() == 0) {
                printString = "No event on " + date.format(dtf) + "!!\n";
            } else {
                printString = tmp.size() + " events on " + date.format(dtf) + ":\n";
                for (int i = 0; i < tmp.size(); i++) {
                    printString = printString + (i + 1) + ". " + tmp.get(i).getTaskDetails() + "\n";
                }

            }
        } else {
            printString = "No event on " + date.format(dtf) + "!!\n";
        }

        return printString;
    }

    /**
     * Prints the deadlines in the tasklist that falls on the date that is passed in as parameter.
     *
     * @param date the date that the is being referenced to.
     */
    public String printDeadlineOn(LocalDate date) {
        String printString;
        ArrayList<Deadline> tmp = new ArrayList<Deadline>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (deadlineList.size() != 0) {
            for (int i = 0; i < deadlineList.size(); i++) {
                if (deadlineList.get(i).dateCompare(date)) {
                    tmp.add(deadlineList.get(i));
                }
            }

            if (tmp.size() == 0) {
                printString = "No deadline on " + date.format(dtf) + "!!\n";
            } else {
                printString = tmp.size() + " deadline item(s) on " + date.format(dtf) + ":\n";
                for (int i = 0; i < tmp.size(); i++) {
                    printString = printString + (i + 1) + ". " + tmp.get(i).getTaskDetails() + "\n";
                }
            }
        } else {
            printString = "No deadline on " + date.format(dtf) + "!!\n";
        }

        return printString;
    }

    /**
     * Prints items which contains the keyword passed.
     *
     * @param keyword to search through the list of tasks.
     */
    public String find(String keyword) {
        ArrayList<Task> tmp = new ArrayList<Task>();
        String printString = "";

        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).item.contains(keyword)) {
                    tmp.add(list.get(i));
                }
            }

            if (tmp.size() == 0) {
                printString = "No item on the list contains \"" +  keyword + "\"!!\n";
            } else {
                printString = "Here are the matching tasks in you list:\n";
                for (int i = 0; i < tmp.size(); i++) {
                    printString = printString + (i + 1) + ". " + tmp.get(i).getTaskDetails() + "\n";
                }
            }
        } else {
            System.out.println("There are currently no item on the list!!");
        }

        return printString;
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
                    dl = new Deadline(dlSubSplit[0].trim(), Boolean.valueOf(inputSplit[1].trim()),
                            TaskList.stringToDate(dlSubSplit[1].trim()));
                } else {
                    dl = new Deadline(dlSubSplit[0].trim(), Boolean.valueOf(inputSplit[1].trim()),
                            TaskList.stringToDate(dlSubSplit2[0]), TaskList.stringToTime(dlSubSplit2[1]));
                }
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
